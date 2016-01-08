package eu.europa.ec.grow.espd.business.common;

import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionRequirement;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionTypeCode;
import eu.europa.ec.grow.espd.domain.*;
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
class EspdCriterionFactory {

    /**
     * Create a ESPD {@link Criterion} instance containing the appropriate information provided as UBL criteria.
     *
     * @param ccvCriterion
     * @param ublCriteria
     * @param <T>
     *
     * @return
     */
    <T extends Criterion> T buildEspdCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        if (ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS.equals(ccvCriterion.getCriterionType())) {
            return (T) buildCriminalConvictionsCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.PAYMENT_OF_TAXES.equals(ccvCriterion.getCriterionType()) ||
                ExclusionCriterionTypeCode.PAYMENT_OF_SOCIAL_SECURITY.equals(ccvCriterion.getCriterionType())) {
            return (T) buildTaxesCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.ENVIRONMENTAL.equals(ccvCriterion.getCriterionType()) ||
                ExclusionCriterionTypeCode.PAYMENT_OF_SOCIAL_SECURITY.equals(ccvCriterion.getCriterionType())) {
            return (T) buildEnvironmentalCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.BANKRUPTCY_INSOLVENCY.equals(ccvCriterion.getCriterionType())) {
            return (T) buildBankruptcyCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.MISCONDUCT.equals(ccvCriterion.getCriterionType())) {
            return (T) buildMisconductCriterion(ccvCriterion, ublCriteria);
        }
        return null;
    }

    private CriminalConvictionsCriterion buildCriminalConvictionsCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return CriminalConvictionsCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);
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

    private TaxesCriterion buildTaxesCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return TaxesCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);

        TaxesCriterion criterion = TaxesCriterion.buildWithExists(yourAnswer);

        Country country = readRequirementValue(ExclusionCriterionRequirement.COUNTRY_MS, criterionType);
        criterion.setCountry(country);
        Amount amount = readRequirementValue(ExclusionCriterionRequirement.AMOUNT, criterionType);
        if (amount != null) {
            criterion.setAmount(amount.getAmount());
            criterion.setCurrency(amount.getCurrency());
        }

        boolean breach = readBooleanRequirement(ExclusionCriterionRequirement.BREACH_OF_OBLIGATIONS_OTHER_THAN,
                criterionType);
        criterion.setBreachEstablishedOtherThanJudicialDecision(breach);
        String meansDescription = readRequirementValue(ExclusionCriterionRequirement.DESCRIBE_MEANS, criterionType);
        criterion.setMeansDescription(meansDescription);

        boolean finalAndBinding = readBooleanRequirement(ExclusionCriterionRequirement.DECISION_FINAL_AND_BINDING,
                criterionType);
        criterion.setDecisionFinalAndBinding(finalAndBinding);
        Date dateOfConviction = readRequirementValue(ExclusionCriterionRequirement.DATE_OF_CONVICTION, criterionType);
        criterion.setDateOfConviction(dateOfConviction);
        String periodLength = readRequirementValue(ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION,
                criterionType);
        criterion.setPeriodLength(periodLength);

        boolean fulfilledObligation = readBooleanRequirement(ExclusionCriterionRequirement.EO_FULFILLED_OBLIGATION,
                criterionType);
        criterion.setEoFulfilledObligations(fulfilledObligation);
        String obligationDescription = readRequirementValue(ExclusionCriterionRequirement.DESCRIBE_OBLIGATIONS,
                criterionType);
        criterion.setObligationsDescription(obligationDescription);

        criterion.setAvailableElectronically(buildAvailableElectronically(criterionType));

        return criterion;
    }

    private EnvironmentalCriterion buildEnvironmentalCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return EnvironmentalCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);

        EnvironmentalCriterion criterion = EnvironmentalCriterion.buildWithExists(yourAnswer);
        String description = readRequirementValue(ExclusionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(description);

        criterion.setSelfCleaning(buildSelfCleaningMeasures(criterionType));

        return criterion;
    }

    private BankruptcyCriterion buildBankruptcyCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return BankruptcyCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);

        BankruptcyCriterion criterion = BankruptcyCriterion.buildWithExists(yourAnswer);
        String description = readRequirementValue(ExclusionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(description);
        String reasonContract = readRequirementValue(ExclusionCriterionRequirement.REASONS_NEVERTHELESS_CONTRACT,
                criterionType);
        criterion.setReason(reasonContract);

        criterion.setAvailableElectronically(buildAvailableElectronically(criterionType));

        return criterion;
    }

    private MisconductCriterion buildMisconductCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return MisconductCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);

        MisconductCriterion criterion = MisconductCriterion.buildWithExists(yourAnswer);
        String description = readRequirementValue(ExclusionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(description);

        criterion.setSelfCleaning(buildSelfCleaningMeasures(criterionType));
        criterion.setAvailableElectronically(buildAvailableElectronically(criterionType));

        return criterion;
    }

    private boolean readExclusionCriterionAnswer(CriterionType criterionType) {
        Boolean yourAnswer = readRequirementValue(ExclusionCriterionRequirement.YOUR_ANSWER, criterionType);
        if (yourAnswer == null) {
            // could come from a ESPD Request where we only have the criterion present without any response
            return criterionType != null;
        }
        return yourAnswer;
    }

    private SelfCleaning buildSelfCleaningMeasures(CriterionType criterionType) {
        boolean selfCleaningAnswer = readBooleanRequirement(ExclusionCriterionRequirement.MEASURES_SELF_CLEANING,
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
        boolean electronicallyAnswer = readBooleanRequirement(
                ExclusionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY, criterionType);
        electronically.setExists(electronicallyAnswer);
        String url = readRequirementValue(ExclusionCriterionRequirement.URL, criterionType);
        electronically.setUrl(url);
        String code = readRequirementValue(ExclusionCriterionRequirement.URL_CODE, criterionType);
        electronically.setCode(code);
        return electronically;
    }

    private boolean readBooleanRequirement(CcvCriterionRequirement requirement, CriterionType criterionType) {
        // special case to avoid null pointer exceptions
        return Boolean.TRUE.equals(readRequirementValue(requirement, criterionType));
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

        RequirementType requirementType = findRequirementInGroups(requirement, criterionType.getRequirementGroup());
        if (requirementType != null && isNotEmpty(requirementType.getResponse())) {
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
