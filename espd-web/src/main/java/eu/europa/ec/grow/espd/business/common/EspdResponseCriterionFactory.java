package eu.europa.ec.grow.espd.business.common;

import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.criteria.enums.*;
import eu.europa.ec.grow.espd.domain.*;
import eu.europa.ec.grow.espd.domain.AwardCriterion;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementGroupType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;

import java.util.Date;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Class that reads data coming from UBL {@link CriterionType} and creates the appropriate ESPD criterion objects
 * containing all the data read from the XML.
 * <p/>
 * Created by ratoico on 1/7/16 at 11:16 AM.
 */
class EspdResponseCriterionFactory {

    /**
     * Create a ESPD {@link Criterion} instance containing the appropriate information provided as UBL criteria.
     *
     * @param ccvCriterion The desired criterion containing this metadata
     * @param ublCriteria  List of UBL criteria to be matched
     * @param <T>
     *
     * @return
     *
     * @throws IllegalArgumentException If the criterion type is not recognized
     */
    @SuppressWarnings("unchecked")
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
        } else if (ExclusionCriterionTypeCode.MISCONDUCT.equals(ccvCriterion.getCriterionType()) ||
                ExclusionCriterionTypeCode.DISTORTING_MARKET.equals(ccvCriterion.getCriterionType())) {
            return (T) buildMisconductCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST.equals(ccvCriterion.getCriterionType())) {
            return (T) buildConflictOfInterestCriterion(ccvCriterion, ublCriteria);
        } else if (ExclusionCriterionTypeCode.OTHER.equals(ccvCriterion.getCriterionType())) {
            return (T) buildPurelyNationalGrounds(ccvCriterion, ublCriteria);
        } else if (SelectionCriterionTypeCode.ALL_CRITERIA_SATISFIED.equals(ccvCriterion.getCriterionType())) {
            return (T) buildSatisfiesAllCriterion(ccvCriterion, ublCriteria);
        } else if (SelectionCriterionTypeCode.SUITABILITY.equals(ccvCriterion.getCriterionType())) {
            return (T) buildSuitabilityCriterion(ccvCriterion, ublCriteria);
        } else if (SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING.equals(ccvCriterion.getCriterionType())) {
            return (T) buildEconomicFinancialStandingCriterion(ccvCriterion, ublCriteria);
        } else if (SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY.equals(ccvCriterion.getCriterionType())) {
            return (T) buildTechnicalProfessionalCriterion(ccvCriterion, ublCriteria);
        } else if (AwardCriterionTypeCode.DATA_ON_ECONOMIC_OPERATOR.equals(ccvCriterion.getCriterionType()) ||
                AwardCriterionTypeCode.REDUCTION_OF_CANDIDATES.equals(ccvCriterion.getCriterionType())) {
            return (T) buildAwardCriterion(ccvCriterion, ublCriteria);
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

        CriminalConvictionsCriterion criterion = CriminalConvictionsCriterion.buildWithExists(true);

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

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

        TaxesCriterion criterion = TaxesCriterion.buildWithExists(true);

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

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

        LawCriterion criterion = LawCriterion.buildWithExists(true);
        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

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

        BankruptcyCriterion criterion = BankruptcyCriterion.buildWithExists(true);

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

        String description = readRequirementValue(ExclusionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(description);
        String reasonContract = readRequirementValue(ExclusionCriterionRequirement.REASONS_NEVERTHELESS_CONTRACT,
                criterionType);
        criterion.setReason(reasonContract);

        criterion.setAvailableElectronically(buildExclusionAvailableElectronically(criterionType));

        return criterion;
    }

    private MisconductDistortionCriterion buildMisconductCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return MisconductDistortionCriterion.buildWithExists(false);
        }

        MisconductDistortionCriterion criterion = MisconductDistortionCriterion.buildWithExists(true);

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

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

        ConflictInterestCriterion criterion = ConflictInterestCriterion.buildWithExists(true);

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

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
        PurelyNationalGrounds criterion = PurelyNationalGrounds.buildWithExists(true);

        boolean yourAnswer = readExclusionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

        String description = readRequirementValue(ExclusionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(description);

        criterion.setAvailableElectronically(buildExclusionAvailableElectronically(criterionType));

        return criterion;
    }

    private SatisfiesAllCriterion buildSatisfiesAllCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return SatisfiesAllCriterion.buildWithExists(false);
        }

        SatisfiesAllCriterion criterion =  SatisfiesAllCriterion.buildWithExists(true);

        boolean yourAnswer = readSelectionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

        return criterion;
    }

    private SuitabilityCriterion buildSuitabilityCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return SuitabilityCriterion.buildWithExists(false);
        }

        SuitabilityCriterion criterion = SuitabilityCriterion.buildWithExists(true);

        boolean yourAnswer = readSelectionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);


        criterion.setAvailableElectronically(buildSelectionAvailableElectronically(criterionType));

        return criterion;
    }

    private EconomicFinancialStandingCriterion buildEconomicFinancialStandingCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return EconomicFinancialStandingCriterion.buildWithExists(false);
        }

        EconomicFinancialStandingCriterion criterion = EconomicFinancialStandingCriterion.buildWithExists(true);

        boolean yourAnswer = readSelectionCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

        addMultipleYears(criterionType, criterion);
        addMultipleAmounts(criterionType, criterion);
        addMultipleDescriptions(criterionType, criterion);
        addMultipleRatios(criterionType, criterion);

        String description = readRequirementValue(SelectionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        criterion.setDescription(description);
        // year 1 is overloaded because of criterion 10. Set up of economic operator
        Integer year1 = readRequirementValue(SelectionCriterionRequirement.SPECIFY_YEAR, criterionType);
        if (year1 != null) {
            criterion.setYear1(year1);
        }

        criterion.setAvailableElectronically(buildSelectionAvailableElectronically(criterionType));

        return criterion;
    }

    private void addMultipleYears(CriterionType criterionType, MultipleYearHolder criterion) {
        Integer year1 = readRequirementValue(SelectionCriterionRequirement.YEAR_1, criterionType);
        if (year1 != null) {
            criterion.setYear1(year1);
        }
        Integer year2 = readRequirementValue(SelectionCriterionRequirement.YEAR_2, criterionType);
        if (year2 != null) {
            criterion.setYear2(year2);
        }
        Integer year3 = readRequirementValue(SelectionCriterionRequirement.YEAR_3, criterionType);
        if (year3 != null) {
            criterion.setYear3(year3);
        }
        Integer year4 = readRequirementValue(SelectionCriterionRequirement.YEAR_4, criterionType);
        if (year4 != null) {
            criterion.setYear4(year4);
        }
        Integer year5 = readRequirementValue(SelectionCriterionRequirement.YEAR_5, criterionType);
        if (year5 != null) {
            criterion.setYear5(year5);
        }
    }

    private void addMultipleRatios(CriterionType criterionType, EconomicFinancialStandingCriterion criterion) {
        Double ratio1 = readRequirementValue(SelectionCriterionRequirement.RATIO_1, criterionType);
        criterion.setRatio1(ratio1);
        Double ratio2 = readRequirementValue(SelectionCriterionRequirement.RATIO_2, criterionType);
        criterion.setRatio2(ratio2);
        Double ratio3 = readRequirementValue(SelectionCriterionRequirement.RATIO_3, criterionType);
        criterion.setRatio3(ratio3);
        Double ratio4 = readRequirementValue(SelectionCriterionRequirement.RATIO_4, criterionType);
        criterion.setRatio4(ratio4);
        Double ratio5 = readRequirementValue(SelectionCriterionRequirement.RATIO_5, criterionType);
        criterion.setRatio5(ratio5);
    }

    private TechnicalProfessionalCriterion buildTechnicalProfessionalCriterion(CcvCriterion ccvCriterion,
            List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        if (criterionType == null) {
            return TechnicalProfessionalCriterion.buildWithExists(false);
        }

        TechnicalProfessionalCriterion criterion = TechnicalProfessionalCriterion.buildWithExists(true);

        // because of allow checks requirement we are overloading the 'answer' field
        // this is a workaround in order to not add one additional boolean field 'allowChecks' that is used by only technical professional criterion
        Boolean allowChecks = readRequirementValue(SelectionCriterionRequirement.ALLOW_CHECKS, criterionType);
        if (allowChecks != null) {
            criterion.setAnswer(allowChecks);
        }
        Boolean yourAnswer = readRequirementValue(SelectionCriterionRequirement.YOUR_ANSWER, criterionType);
        if (yourAnswer != null) {
            criterion.setAnswer(yourAnswer);
        }

        addMultipleDescriptions(criterionType, criterion);
        addMultipleAmounts(criterionType, criterion);
        addMultipleDates(criterionType, criterion);
        addMultipleRecipients(criterionType, criterion);
        addMultipleYears(criterionType, criterion);
        addMultipleNumbers(criterionType, criterion);

        addDescriptionForTechnicalProfessional(criterionType, criterion);

        String specify = readRequirementValue(SelectionCriterionRequirement.PLEASE_SPECIFY, criterionType);
        criterion.setSpecify(specify);

        Double percentage = readRequirementValue(SelectionCriterionRequirement.PERCENTAGE, criterionType);
        criterion.setPercentage(percentage);

        criterion.setAvailableElectronically(buildSelectionAvailableElectronically(criterionType));

        return criterion;
    }

    private void addDescriptionForTechnicalProfessional(CriterionType criterionType,
            TechnicalProfessionalCriterion criterion) {
        // description is overloaded so we cannot have all these fields set at the same time
        // the design is not very good
        String explainSupplyQc = readRequirementValue(SelectionCriterionRequirement.EXPLAIN_SUPPLY_CONTRACTS_QC,
                criterionType);
        if (isNotBlank(explainSupplyQc)) {
            criterion.setDescription(explainSupplyQc);
        }
        String explainCertificatesQa = readRequirementValue(SelectionCriterionRequirement.EXPLAIN_CERTIFICATES_QA,
                criterionType);
        if (isNotBlank(explainCertificatesQa)) {
            criterion.setDescription(explainCertificatesQa);
        }
        String explainCertificatesEnvironmental = readRequirementValue(
                SelectionCriterionRequirement.EXPLAIN_CERTIFICATES_ENVIRONMENTAL, criterionType);
        if (isNotBlank(explainCertificatesEnvironmental)) {
            criterion.setDescription(explainCertificatesEnvironmental);
        }
        String pleaseDescribe = readRequirementValue(SelectionCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        if (isNotBlank(pleaseDescribe)) {
            criterion.setDescription(pleaseDescribe);
        }
    }

    private void addMultipleNumbers(CriterionType criterionType, TechnicalProfessionalCriterion criterion) {
        Integer number1 = readRequirementValue(SelectionCriterionRequirement.NUMBER_1, criterionType);
        if (number1 != null) {
            criterion.setNumber1(number1);
        }
        Integer number2 = readRequirementValue(SelectionCriterionRequirement.NUMBER_2, criterionType);
        if (number2 != null) {
            criterion.setNumber2(number2);
        }
        Integer number3 = readRequirementValue(SelectionCriterionRequirement.NUMBER_3, criterionType);
        if (number3 != null) {
            criterion.setNumber3(number3);
        }

    }

    private void addMultipleDescriptions(CriterionType criterionType, MultipleDescriptionHolder criterion) {
        String description1 = readRequirementValue(SelectionCriterionRequirement.DESCRIPTION_1, criterionType);
        criterion.setDescription1(description1);
        String description2 = readRequirementValue(SelectionCriterionRequirement.DESCRIPTION_2, criterionType);
        criterion.setDescription2(description2);
        String description3 = readRequirementValue(SelectionCriterionRequirement.DESCRIPTION_3, criterionType);
        criterion.setDescription3(description3);
        String description4 = readRequirementValue(SelectionCriterionRequirement.DESCRIPTION_4, criterionType);
        criterion.setDescription4(description4);
        String description5 = readRequirementValue(SelectionCriterionRequirement.DESCRIPTION_5, criterionType);
        criterion.setDescription5(description5);
    }

    private void addMultipleAmounts(CriterionType criterionType, MultipleAmountHolder criterion) {
        Amount amount1 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_1, criterionType);
        if (amount1 != null) {
            criterion.setAmount1(amount1.getAmount());
            criterion.setCurrency1(amount1.getCurrency());
        }
        Amount amount2 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_2, criterionType);
        if (amount2 != null) {
            criterion.setAmount2(amount2.getAmount());
            criterion.setCurrency2(amount2.getCurrency());
        }
        Amount amount3 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_3, criterionType);
        if (amount3 != null) {
            criterion.setAmount3(amount3.getAmount());
            criterion.setCurrency3(amount3.getCurrency());
        }
        Amount amount4 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_4, criterionType);
        if (amount4 != null) {
            criterion.setAmount4(amount4.getAmount());
            criterion.setCurrency4(amount4.getCurrency());
        }
        Amount amount5 = readRequirementValue(SelectionCriterionRequirement.AMOUNT_5, criterionType);
        if (amount5 != null) {
            criterion.setAmount5(amount5.getAmount());
            criterion.setCurrency5(amount5.getCurrency());
        }
    }

    private void addMultipleDates(CriterionType criterionType, TechnicalProfessionalCriterion criterion) {
        Date date1 = readRequirementValue(SelectionCriterionRequirement.DATE_1, criterionType);
        criterion.setDate1(date1);
        Date date2 = readRequirementValue(SelectionCriterionRequirement.DATE_2, criterionType);
        criterion.setDate2(date2);
        Date date3 = readRequirementValue(SelectionCriterionRequirement.DATE_3, criterionType);
        criterion.setDate3(date3);
        Date date4 = readRequirementValue(SelectionCriterionRequirement.DATE_4, criterionType);
        criterion.setDate4(date4);
        Date date5 = readRequirementValue(SelectionCriterionRequirement.DATE_5, criterionType);
        criterion.setDate5(date5);
    }

    private void addMultipleRecipients(CriterionType criterionType, TechnicalProfessionalCriterion criterion) {
        String recipients1 = readRequirementValue(SelectionCriterionRequirement.RECIPIENTS_1, criterionType);
        criterion.setRecipients1(recipients1);
        String recipients2 = readRequirementValue(SelectionCriterionRequirement.RECIPIENTS_2, criterionType);
        criterion.setRecipients2(recipients2);
        String recipients3 = readRequirementValue(SelectionCriterionRequirement.RECIPIENTS_3, criterionType);
        criterion.setRecipients3(recipients3);
        String recipients4 = readRequirementValue(SelectionCriterionRequirement.RECIPIENTS_4, criterionType);
        criterion.setRecipients4(recipients4);
        String recipients5 = readRequirementValue(SelectionCriterionRequirement.RECIPIENTS_5, criterionType);
        criterion.setRecipients5(recipients5);
    }

    private boolean readExclusionCriterionAnswer(CriterionType criterionType) {
        return readCriterionAnswer(criterionType, ExclusionCriterionRequirement.YOUR_ANSWER);
    }

    private boolean readSelectionCriterionAnswer(CriterionType criterionType) {
        return readCriterionAnswer(criterionType, SelectionCriterionRequirement.YOUR_ANSWER);
    }

    private boolean readAwardCriterionAnswer(CriterionType criterionType) {
        return readCriterionAnswer(criterionType, AwardCriterionRequirement.INDICATOR);
    }

    private boolean readCriterionAnswer(CriterionType criterionType, CcvCriterionRequirement answerReq) {
        return Boolean.TRUE.equals(readRequirementValue(answerReq, criterionType));
    }

    private SelfCleaning buildSelfCleaningMeasures(CriterionType criterionType) {
        boolean selfCleaningAnswer = readBooleanRequirement(ExclusionCriterionRequirement.MEASURES_SELF_CLEANING,
                criterionType);
        SelfCleaning selfCleaning = new SelfCleaning();
        selfCleaning.setAnswer(selfCleaningAnswer);
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

    private AvailableElectronically buildAwardAvailableElectronically(CriterionType criterionType) {
        return buildAvailableElectronically(criterionType, AwardCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY,
                AwardCriterionRequirement.URL, AwardCriterionRequirement.URL_CODE);
    }

    private AvailableElectronically buildAvailableElectronically(CriterionType criterionType,
            CcvCriterionRequirement answerReq, CcvCriterionRequirement urlReq, CcvCriterionRequirement urlCodeReq) {
        AvailableElectronically electronically = new AvailableElectronically();
        boolean electronicallyAnswer = readBooleanRequirement(answerReq, criterionType);
        electronically.setAnswer(electronicallyAnswer);
        String url = readRequirementValue(urlReq, criterionType);
        electronically.setUrl(url);
        String code = readRequirementValue(urlCodeReq, criterionType);
        electronically.setCode(code);
        return electronically;
    }

    private AwardCriterion buildAwardCriterion(CcvCriterion ccvCriterion, List<CriterionType> ublCriteria) {
        CriterionType criterionType = isCriterionPresent(ccvCriterion, ublCriteria);
        AwardCriterion criterion = AwardCriterion.build();

        if (criterionType == null) {
            return criterion;
        }

        boolean yourAnswer = readAwardCriterionAnswer(criterionType);
        criterion.setAnswer(yourAnswer);

        // description1 is overloaded by multiple fields but it should not be a problem since they are coming from different criteria
        String detailsCategory = readRequirementValue(AwardCriterionRequirement.DETAILS_EMPLOYEES_CATEGORY, criterionType);
        if (isNotBlank(detailsCategory)) {
            criterion.setDescription1(detailsCategory);
        }
        String regNumber = readRequirementValue(AwardCriterionRequirement.PROVIDE_REGISTRATION_NUMBER, criterionType);
        if (isNotBlank(regNumber)) {
            criterion.setDescription1(regNumber);
        }
        String eoRole = readRequirementValue(AwardCriterionRequirement.ECONOMIC_OPERATOR_ROLE, criterionType);
        if (isNotBlank(eoRole)) {
            criterion.setDescription1(eoRole);
        }
        String describe = readRequirementValue(AwardCriterionRequirement.PLEASE_DESCRIBE, criterionType);
        if (isNotBlank(describe)) {
            criterion.setDescription1(describe);
        }

        String regNumberElectronically = readRequirementValue(AwardCriterionRequirement.REG_NO_AVAILABLE_ELECTRONICALLY, criterionType);
        if (isNotBlank(regNumberElectronically)) {
            criterion.setDescription2(regNumberElectronically);
        }
        String otherEos = readRequirementValue(AwardCriterionRequirement.OTHER_ECONOMIC_OPERATORS, criterionType);
        if (isNotBlank(otherEos)) {
            criterion.setDescription2(otherEos);
        }

        String referencesRegistration = readRequirementValue(AwardCriterionRequirement.REFERENCES_REGISTRATION, criterionType);
        if (isNotBlank(referencesRegistration)) {
            criterion.setDescription3(referencesRegistration);
        }
        String participantGroupName = readRequirementValue(AwardCriterionRequirement.PARTICIPANT_GROUP_NAME, criterionType);
        if (isNotBlank(participantGroupName)) {
            criterion.setDescription3(participantGroupName);
        }


        String eoProvideCertificate = readRequirementValue(AwardCriterionRequirement.EO_ABLE_PROVIDE_CERTIFICATE, criterionType);
        if (isNotBlank(eoProvideCertificate)) {
            criterion.setDescription4(eoProvideCertificate);
        }
        String docElectronically = readRequirementValue(AwardCriterionRequirement.DOC_AVAILABLE_ELECTRONICALLY, criterionType);
        if (isNotBlank(docElectronically)) {
            criterion.setDescription5(docElectronically);
        }

        boolean coversAllSelectionCriteria = readBooleanRequirement(
                AwardCriterionRequirement.REGISTRATION_COVERS_SELECTION_CRITERIA, criterionType);
        criterion.setBooleanValue1(coversAllSelectionCriteria);
        Double percentage = readRequirementValue(AwardCriterionRequirement.CORRESPONDING_PERCENTAGE, criterionType);
        criterion.setDoubleValue1(percentage);

        criterion.setAvailableElectronically(buildAwardAvailableElectronically(criterionType));

        return criterion;
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
