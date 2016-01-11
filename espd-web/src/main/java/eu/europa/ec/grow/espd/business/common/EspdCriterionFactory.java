package eu.europa.ec.grow.espd.business.common;

import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionRequirement;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionTypeCode;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterionRequirement;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterionTypeCode;
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
     * @param ccvCriterion The desired criterion containing this metadata
     * @param ublCriteria List of UBL criteria to be matched
     * @param <T>
     *
     * @return
     * @throws IllegalArgumentException If the criterion type is not recognized
     */
    <T extends Criterion> T buildEspdCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        if (ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS.equals(ccvCriterion.getCriterionType())) {
            return (T) buildCriminalConvictionsCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.PAYMENT_OF_TAXES.equals(ccvCriterion.getCriterionType()) ||
                ExclusionCriterionTypeCode.PAYMENT_OF_SOCIAL_SECURITY.equals(ccvCriterion.getCriterionType())) {
            return (T) buildTaxesCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.ENVIRONMENTAL_LAW.equals(ccvCriterion.getCriterionType()) ||
                ExclusionCriterionTypeCode.SOCIAL_LAW.equals(ccvCriterion.getCriterionType()) ||
                ExclusionCriterionTypeCode.LABOUR_LAW.equals(ccvCriterion.getCriterionType())) {
            return (T) buildLawCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.BANKRUPTCY_INSOLVENCY.equals(ccvCriterion.getCriterionType())) {
            return (T) buildBankruptcyCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.MISCONDUCT.equals(ccvCriterion.getCriterionType())) {
            return (T) buildMisconductCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST.equals(ccvCriterion.getCriterionType())) {
            return (T) buildConflictOfInterestCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.OTHER.equals(ccvCriterion.getCriterionType())) {
            return (T) buildPurelyNationalGrounds(ccvCriterion, ublCriteria);
        } else if (SelectionCriterionTypeCode.SUITABILITY.equals(ccvCriterion.getCriterionType())) {
            return (T) buildSuitabilityCriterion(ccvCriterion, ublCriteria);
        } else if (SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING.equals(ccvCriterion.getCriterionType())) {
            return (T) buildEconomicFinancialStandingCriterion(ccvCriterion, ublCriteria);
        } else if (SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY.equals(ccvCriterion.getCriterionType())) {
            return (T) buildTechnicalProfessionalCriterion(ccvCriterion, ublCriteria);
        }
        throw new IllegalArgumentException(
                String.format("Could not build criterion '%s' with id '%s' having type code '%s'.",
                        ccvCriterion.getName(), ccvCriterion.getUuid(), ccvCriterion.getCriterionType()));
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

        criterion.setAvailableElectronically(buildExclusionAvailableElectronically(criterionType));
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

        criterion.setAvailableElectronically(buildExclusionAvailableElectronically(criterionType));

        return criterion;
    }

    private LawCriterion buildLawCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return LawCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);

        LawCriterion criterion = LawCriterion.buildWithExists(yourAnswer);
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

        criterion.setAvailableElectronically(buildExclusionAvailableElectronically(criterionType));

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
        criterion.setAvailableElectronically(buildExclusionAvailableElectronically(criterionType));

        return criterion;
    }

    private ConflictInterestCriterion buildConflictOfInterestCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return ConflictInterestCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);

        ConflictInterestCriterion criterion = ConflictInterestCriterion.buildWithExists(yourAnswer);
        String description = readRequirementValue(ExclusionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(description);

        criterion.setSelfCleaning(buildSelfCleaningMeasures(criterionType));
        criterion.setAvailableElectronically(buildExclusionAvailableElectronically(criterionType));

        return criterion;
    }

    private PurelyNationalGrounds buildPurelyNationalGrounds(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return PurelyNationalGrounds.buildWithExists(false);
        }

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);

        PurelyNationalGrounds criterion = PurelyNationalGrounds.buildWithExists(yourAnswer);
        String description = readRequirementValue(ExclusionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(description);

        criterion.setAvailableElectronically(buildExclusionAvailableElectronically(criterionType));

        return criterion;
    }

    private SuitabilityCriterion buildSuitabilityCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return SuitabilityCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readSelectionCriterionAnswer(criterionType);

        SuitabilityCriterion criterion = SuitabilityCriterion.buildWithExists(yourAnswer);

        criterion.setAvailableElectronically(buildSelectionAvailableElectronically(criterionType));

        return criterion;
    }

    private EconomicFinancialStandingCriterion buildEconomicFinancialStandingCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return EconomicFinancialStandingCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readSelectionCriterionAnswer(criterionType);

        EconomicFinancialStandingCriterion criterion = EconomicFinancialStandingCriterion.buildWithExists(yourAnswer);

        Integer year1 = readRequirementValue(SelectionCriterionRequirement.YEAR_1, criterionType);
        criterion.setYear1(year1);
        Amount amount1 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_1, criterionType);
        if (amount1 != null) {
            criterion.setAmount1(amount1.getAmount());
            criterion.setCurrency1(amount1.getCurrency());
        }

        Integer year2 = readRequirementValue(SelectionCriterionRequirement.YEAR_2, criterionType);
        criterion.setYear2(year2);
        Amount amount2 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_2, criterionType);
        if (amount2 != null) {
            criterion.setAmount2(amount2.getAmount());
            criterion.setCurrency2(amount2.getCurrency());
        }

        Integer year3 = readRequirementValue(SelectionCriterionRequirement.YEAR_3, criterionType);
        criterion.setYear3(year3);
        Amount amount3 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_3, criterionType);
        if (amount3 != null) {
            criterion.setAmount3(amount3.getAmount());
            criterion.setCurrency3(amount3.getCurrency());
        }

        String description = readRequirementValue(SelectionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(description);

        criterion.setAvailableElectronically(buildSelectionAvailableElectronically(criterionType));

        return criterion;
    }

    private TechnicalProfessionalCriterion buildTechnicalProfessionalCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return TechnicalProfessionalCriterion.buildWithExists(false);
        }

        boolean yourAnswer = readSelectionCriterionAnswer(criterionType);

        TechnicalProfessionalCriterion criterion = TechnicalProfessionalCriterion.buildWithExists(yourAnswer);

        String description1 = readRequirementValue(SelectionCriterionRequirement.DESCRIPTION_1, criterionType);
        criterion.setDescription1(description1);
        Amount amount1 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_1, criterionType);
        if (amount1 != null) {
            criterion.setAmount1(amount1.getAmount());
            criterion.setCurrency1(amount1.getCurrency());
        }
        Date date1 = readRequirementValue(SelectionCriterionRequirement.DATE_1, criterionType);
        criterion.setDate1(date1);
        String recipients1 = readRequirementValue(SelectionCriterionRequirement.RECIPIENTS_1, criterionType);
        criterion.setRecipients1(recipients1);

        String description2 = readRequirementValue(SelectionCriterionRequirement.DESCRIPTION_2, criterionType);
        criterion.setDescription2(description2);
        Amount amount2 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_2, criterionType);
        if (amount2 != null) {
            criterion.setAmount2(amount2.getAmount());
            criterion.setCurrency2(amount2.getCurrency());
        }
        Date date2 = readRequirementValue(SelectionCriterionRequirement.DATE_2, criterionType);
        criterion.setDate2(date2);
        String recipients2 = readRequirementValue(SelectionCriterionRequirement.RECIPIENTS_2, criterionType);
        criterion.setRecipients2(recipients2);

        String description3 = readRequirementValue(SelectionCriterionRequirement.DESCRIPTION_3, criterionType);
        criterion.setDescription3(description3);
        Amount amount3 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_3, criterionType);
        if (amount3 != null) {
            criterion.setAmount3(amount3.getAmount());
            criterion.setCurrency3(amount3.getCurrency());
        }
        Date date3 = readRequirementValue(SelectionCriterionRequirement.DATE_3, criterionType);
        criterion.setDate3(date3);
        String recipients3 = readRequirementValue(SelectionCriterionRequirement.RECIPIENTS_3, criterionType);
        criterion.setRecipients3(recipients3);

        String specify = readRequirementValue(SelectionCriterionRequirement.PLEASE_SPECIFY, criterionType);
        criterion.setSpecify(specify);

        String pleaseDescribe = readRequirementValue(SelectionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(pleaseDescribe);

        Double percentage = readRequirementValue(SelectionCriterionRequirement.PERCENTAGE, criterionType);
        criterion.setPercentage(percentage);

        criterion.setAvailableElectronically(buildSelectionAvailableElectronically(criterionType));

        return criterion;
    }

    private boolean readExclusionCriterionAnswer(CriterionType criterionType) {
        return readCriterionAnswer(criterionType, ExclusionCriterionRequirement.YOUR_ANSWER);
    }

    private boolean readSelectionCriterionAnswer(CriterionType criterionType) {
        return readCriterionAnswer(criterionType, SelectionCriterionRequirement.YOUR_ANSWER);
    }

    private boolean readCriterionAnswer(CriterionType criterionType, CcvCriterionRequirement answerReq) {
        Boolean yourAnswer = readRequirementValue(answerReq, criterionType);
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

    private AvailableElectronically buildExclusionAvailableElectronically(CriterionType criterionType) {
        return buildAvailableElectronically(criterionType, ExclusionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY,
                ExclusionCriterionRequirement.URL, ExclusionCriterionRequirement.URL_CODE);
    }

    private AvailableElectronically buildSelectionAvailableElectronically(CriterionType criterionType) {
        return buildAvailableElectronically(criterionType, SelectionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY,
                SelectionCriterionRequirement.URL, SelectionCriterionRequirement.URL_CODE);
    }

    private AvailableElectronically buildAvailableElectronically(CriterionType criterionType,
            CcvCriterionRequirement answerReq, CcvCriterionRequirement urlReq, CcvCriterionRequirement urlCodeReq) {
        AvailableElectronically electronically = new AvailableElectronically();
        boolean electronicallyAnswer = readBooleanRequirement(answerReq, criterionType);
        electronically.setExists(electronicallyAnswer);
        String url = readRequirementValue(urlReq, criterionType);
        electronically.setUrl(url);
        String code = readRequirementValue(urlCodeReq, criterionType);
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
