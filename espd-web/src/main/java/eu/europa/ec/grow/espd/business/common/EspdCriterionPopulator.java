package eu.europa.ec.grow.espd.business.common;

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionRequirement;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionTypeCode;
import eu.europa.ec.grow.espd.domain.AvailableElectronically;
import eu.europa.ec.grow.espd.domain.CriminalConvictionsCriterion;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.SelfCleaning;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementGroupType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;

import java.util.Date;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Class that reads data coming from UBL {@link CriterionType} and creates the appropriate ESPD criterion objects
 * containing all the data read from the XML.
 * <p/>
 * Created by ratoico on 1/7/16 at 11:16 AM.
 */
class EspdCriterionPopulator {

    <T extends Criterion> T buildEspdCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        if (ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS.equals(ccvCriterion.getCriterionType())) {
            return (T) buildCriminalConvictionsCriterion(ccvCriterion, ublCriteria);
        }
        return null;
    }

    private CriminalConvictionsCriterion buildCriminalConvictionsCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return null;
        }

        boolean yourAnswer = readRequirementValue(ExclusionCriterionRequirement.YOUR_ANSWER, criterionType);
        CriminalConvictionsCriterion criterion = CriminalConvictionsCriterion.buildWithExists(yourAnswer);

        Date dateOfConviction = readRequirementValue(ExclusionCriterionRequirement.DATE_OF_CONVICTION, criterionType);
        criterion.setDateOfConviction(dateOfConviction);
        String reason = readRequirementValue(ExclusionCriterionRequirement.REASON, criterionType);
        criterion.setReason(reason);
        String whoConvicted = readRequirementValue(ExclusionCriterionRequirement.WHO_CONVICTED, criterionType);
        criterion.setConvicted(whoConvicted);
        String periodLength = readRequirementValue(ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION,
                criterionType);
        criterion.setPeriodLength(periodLength);

        criterion.setSelfCleaning(buildSelfCleaningMeasures(criterionType));

        criterion.setAvailableElectronically(buildAvailableElectronically(criterionType));
        return criterion;
    }


    private SelfCleaning buildSelfCleaningMeasures(CriterionType criterionType) {
        boolean selfCleaningAnswer = readRequirementValue(ExclusionCriterionRequirement.MEASURES_SELF_CLEANING,
                criterionType);
        SelfCleaning selfCleaning = new SelfCleaning();
        selfCleaning.setExists(selfCleaningAnswer);
        String description = readRequirementValue(ExclusionCriterionRequirement.PLEASE_DESCRIBE_SELF_CLEANING,
                criterionType);
        selfCleaning.setDescription(description);
        return selfCleaning;
    }

    private AvailableElectronically buildAvailableElectronically(CriterionType criterionType) {
        AvailableElectronically electronically = new AvailableElectronically();
        boolean electronicallyAnswer = readRequirementValue(
                ExclusionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY, criterionType);
        electronically.setExists(electronicallyAnswer);
        String url = readRequirementValue(ExclusionCriterionRequirement.URL, criterionType);
        electronically.setUrl(url);
        String code = readRequirementValue(ExclusionCriterionRequirement.URL_CODE, criterionType);
        electronically.setCode(code);
        return electronically;
    }

    private CriterionType isCriterionPresent(CcvCriterion criterion, List<CriterionType> ublCriteria) {
        for (CriterionType ubl : ublCriteria) {
            if (ubl.getID() != null && criterion.getUuid().equals(ubl.getID().getValue())) {
                return ubl;
            }
        }
        return null;
    }

    private <T> T readRequirementValue(CcvCriterionRequirement requirement, CriterionType criterionType) {
        if (isEmpty(criterionType.getRequirementGroup())) {
            return null;
        }
//        for (RequirementGroupType groupType : criterionType.getRequirementGroup()) {
//            if (isEmpty(groupType.getRequirement())) {
//                return null;
//            }
//            for (RequirementType requirementType : groupType.getRequirement()) {
//                if (requirementType.getID() != null && requirement.getId().equals(requirementType.getID().getValue())) {
//                    if (isEmpty(requirementType.getResponse())) {
//                        return null;
//                    }
//                    return ResponseValueParsers.parse(requirement, requirementType.getResponse().get(0));
//                }
//            }
//        }
        RequirementType requirementType = findRequirementInGroups(requirement, criterionType.getRequirementGroup());
        if (requirementType != null) {
            return ResponseValueParsers.parse(requirement, requirementType.getResponse().get(0));
        }
        return null;
    }

    private RequirementType findRequirementInGroup(CcvCriterionRequirement requirement,
            RequirementGroupType requirementGroup) {
        if (isNotEmpty(requirementGroup.getRequirement())) {
            for (RequirementType requirementType : requirementGroup.getRequirement()) {
                if (requirementType.getID() != null && requirement.getId().equals(requirementType.getID().getValue())) {
                    return requirementType;
                }
            }
        }
        return findRequirementInGroups(requirement, requirementGroup.getRequirementGroup());
    }

    private RequirementType findRequirementInGroups(CcvCriterionRequirement requirement,
            List<RequirementGroupType> requirementGroups) {
        if (isEmpty(requirementGroups)) {
            return null;
        }
        for (RequirementGroupType group : requirementGroups) {
            RequirementType found = findRequirementInGroup(requirement, group);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

}
