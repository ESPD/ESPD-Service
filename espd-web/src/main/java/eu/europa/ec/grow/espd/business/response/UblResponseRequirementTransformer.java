package eu.europa.ec.grow.espd.business.response;

import eu.europa.ec.grow.espd.business.common.UblRequirementTypeTemplate;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.criteria.enums.AwardRequirement;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionRequirement;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterionRequirement;
import eu.europa.ec.grow.espd.domain.*;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.ResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonbasiccomponents_1.IndicatorType;
import isa.names.specification.ubl.schema.xsd.cev_commonaggregatecomponents_1.EvidenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.AttachmentType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ExternalReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PeriodType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by ratoico on 12/22/15 at 4:07 PM.
 */
class UblResponseRequirementTransformer extends UblRequirementTypeTemplate {

    @Override
    protected RequirementType buildRequirementType(CcvCriterionRequirement ccvRequirement, Criterion espdCriterion) {
        RequirementType requirementType = new RequirementType();

        IDType idType = new IDType();
        idType.setValue(ccvRequirement.getId());
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setSchemeID("CriterionRelatedIDs");
        idType.setSchemeVersionID("1.0");
        requirementType.setID(idType);

        requirementType.setDescription(buildDescriptionType(ccvRequirement.getDescription()));

        requirementType.setResponseDataType(ccvRequirement.getResponseType().getCode());

        requirementType.getResponse().add(buildResponse(ccvRequirement, espdCriterion));

        return requirementType;
    }

    private ResponseType buildResponse(CcvCriterionRequirement ccvRequirement, Criterion espdCriterion) {
        ResponseType responseType = new ResponseType();

        fillExclusionCriteria(ccvRequirement, espdCriterion, responseType);
        fillSelectionCriteria(ccvRequirement, espdCriterion, responseType);
        fillAwardCriteria(ccvRequirement, espdCriterion, responseType);

        return responseType;
    }

    private void fillExclusionCriteria(CcvCriterionRequirement ccvRequirement, Criterion espdCriterion,
            ResponseType responseType) {
        if (espdCriterion == null) {
            return;
        }
        if (ExclusionCriterionRequirement.YOUR_ANSWER.equals(ccvRequirement)) {
            responseType.setIndicator(buildIndicatorType(espdCriterion.getAnswer()));
        } else if (ExclusionCriterionRequirement.DATE_OF_CONVICTION.equals(ccvRequirement)) {
            ConvictionHolder exclusionCriterion = (ConvictionHolder) espdCriterion;
            responseType.setDate(buildDateType(exclusionCriterion.getDateOfConviction()));
        } else if (ExclusionCriterionRequirement.REASON.equals(ccvRequirement)) {
            CriminalConvictionsCriterion exclusionCriterion = (CriminalConvictionsCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(exclusionCriterion.getReason()));
        } else if (ExclusionCriterionRequirement.WHO_CONVICTED.equals(ccvRequirement)) {
            CriminalConvictionsCriterion crit = (CriminalConvictionsCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(crit.getConvicted()));
        } else if (ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION.equals(ccvRequirement)) {
            ConvictionHolder exclusionCriterion = (ConvictionHolder) espdCriterion;
            responseType.setPeriod(buildPeriodType(exclusionCriterion.getPeriodLength()));
        } else if (ExclusionCriterionRequirement.MEASURES_SELF_CLEANING.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            responseType.setIndicator(buildIndicatorType(exclusionCriterion.getSelfCleaningAnswer()));
        } else if (ExclusionCriterionRequirement.PLEASE_DESCRIBE_SELF_CLEANING.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(exclusionCriterion.getSelfCleaningDescription()));
        } else if (ExclusionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            responseType.setIndicator(buildIndicatorType(exclusionCriterion.getInfoElectronicallyAnswer()));
        } else if (ExclusionCriterionRequirement.URL.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            EvidenceType evidenceType = buildEvidenceType(exclusionCriterion.getInfoElectronicallyUrl());
            responseType.getEvidence().add(evidenceType);
        } else if (ExclusionCriterionRequirement.URL_CODE.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            responseType.setCode(buildCodeType(exclusionCriterion.getInfoElectronicallyCode()));
        } else if (ExclusionCriterionRequirement.COUNTRY_MS.equals(ccvRequirement)) {
            TaxesCriterion exclusionCriterion = (TaxesCriterion) espdCriterion;
            responseType.setCode(buildCountryType(exclusionCriterion.getCountry()));
        } else if (ExclusionCriterionRequirement.AMOUNT.equals(ccvRequirement)) {
            TaxesCriterion exclusionCriterion = (TaxesCriterion) espdCriterion;
            responseType.setAmount(buildAmountType(exclusionCriterion.getAmount(), exclusionCriterion.getCurrency()));
        } else if (ExclusionCriterionRequirement.BREACH_OF_OBLIGATIONS_OTHER_THAN.equals(ccvRequirement)) {
            TaxesCriterion taxesCriterionCriterion = (TaxesCriterion) espdCriterion;
            responseType
                    .setIndicator(
                            buildIndicatorType(taxesCriterionCriterion.isBreachEstablishedOtherThanJudicialDecision()));
        } else if (ExclusionCriterionRequirement.DESCRIBE_MEANS.equals(ccvRequirement)) {
            TaxesCriterion taxesCriterionCriterion = (TaxesCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(taxesCriterionCriterion.getMeansDescription()));
        } else if (ExclusionCriterionRequirement.DECISION_FINAL_AND_BINDING.equals(ccvRequirement)) {
            TaxesCriterion taxesCriterionCriterion = (TaxesCriterion) espdCriterion;
            responseType.setIndicator(buildIndicatorType(taxesCriterionCriterion.isDecisionFinalAndBinding()));
        } else if (ExclusionCriterionRequirement.EO_FULFILLED_OBLIGATION.equals(ccvRequirement)) {
            TaxesCriterion taxesCriterionCriterion = (TaxesCriterion) espdCriterion;
            responseType.setIndicator(buildIndicatorType(taxesCriterionCriterion.isEoFulfilledObligations()));
        } else if (ExclusionCriterionRequirement.DESCRIBE_OBLIGATIONS.equals(ccvRequirement)) {
            TaxesCriterion taxesCriterionCriterion = (TaxesCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(taxesCriterionCriterion.getObligationsDescription()));
        } else if (ExclusionCriterionRequirement.PLEASE_DESCRIBE.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(exclusionCriterion.getDescription()));
        } else if (ExclusionCriterionRequirement.REASONS_NEVERTHELESS_CONTRACT.equals(ccvRequirement)) {
            BankruptcyCriterion exclusionCriterion = (BankruptcyCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(exclusionCriterion.getReason()));
        }
    }

    private void fillSelectionCriteria(CcvCriterionRequirement ccvRequirement, Criterion espdCriterion,
            ResponseType responseType) {
        if (espdCriterion == null) {
            return;
        }
        if (SelectionCriterionRequirement.YOUR_ANSWER.equals(ccvRequirement) ||
                SelectionCriterionRequirement.ALLOW_CHECKS.equals(ccvRequirement)) {
            responseType.setIndicator(buildIndicatorType(espdCriterion.getAnswer()));
        } else if (SelectionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY.equals(ccvRequirement)) {
            SelectionCriterion selectionCriterion = (SelectionCriterion) espdCriterion;
            responseType.setIndicator(buildIndicatorType(selectionCriterion.getInfoElectronicallyAnswer()));
        } else if (SelectionCriterionRequirement.URL.equals(ccvRequirement)) {
            SelectionCriterion selectionCriterion = (SelectionCriterion) espdCriterion;
            EvidenceType evidenceType = buildEvidenceType(selectionCriterion.getInfoElectronicallyUrl());
            responseType.getEvidence().add(evidenceType);
        } else if (SelectionCriterionRequirement.URL_CODE.equals(ccvRequirement)) {
            SelectionCriterion selectionCriterion = (SelectionCriterion) espdCriterion;
            responseType.setCode(buildCodeType(selectionCriterion.getInfoElectronicallyCode()));
        } else if (SelectionCriterionRequirement.YEAR_1.equals(ccvRequirement)) {
            MultipleYearHolder selectionCriterion = (MultipleYearHolder) espdCriterion;
            responseType.setQuantity(buildYearType(selectionCriterion.getYear1()));
        } else if (SelectionCriterionRequirement.YEAR_2.equals(ccvRequirement)) {
            MultipleYearHolder selectionCriterion = (MultipleYearHolder) espdCriterion;
            responseType.setQuantity(buildYearType(selectionCriterion.getYear2()));
        } else if (SelectionCriterionRequirement.YEAR_3.equals(ccvRequirement)) {
            MultipleYearHolder selectionCriterion = (MultipleYearHolder) espdCriterion;
            responseType.setQuantity(buildYearType(selectionCriterion.getYear3()));
        } else if (SelectionCriterionRequirement.YEAR_4.equals(ccvRequirement)) {
            MultipleYearHolder selectionCriterion = (MultipleYearHolder) espdCriterion;
            responseType.setQuantity(buildYearType(selectionCriterion.getYear4()));
        } else if (SelectionCriterionRequirement.YEAR_5.equals(ccvRequirement)) {
            MultipleYearHolder selectionCriterion = (MultipleYearHolder) espdCriterion;
            responseType.setQuantity(buildYearType(selectionCriterion.getYear5()));
        } else if (SelectionCriterionRequirement.AMOUNT_1.equals(ccvRequirement)) {
            MultipleAmountHolder selectionCriterion = (MultipleAmountHolder) espdCriterion;
            responseType.setAmount(buildAmountType(selectionCriterion.getAmount1(), selectionCriterion.getCurrency1()));
        } else if (SelectionCriterionRequirement.AMOUNT_2.equals(ccvRequirement)) {
            MultipleAmountHolder selectionCriterion = (MultipleAmountHolder) espdCriterion;
            responseType.setAmount(buildAmountType(selectionCriterion.getAmount2(), selectionCriterion.getCurrency2()));
        } else if (SelectionCriterionRequirement.AMOUNT_3.equals(ccvRequirement)) {
            MultipleAmountHolder selectionCriterion = (MultipleAmountHolder) espdCriterion;
            responseType.setAmount(buildAmountType(selectionCriterion.getAmount3(), selectionCriterion.getCurrency3()));
        } else if (SelectionCriterionRequirement.AMOUNT_4.equals(ccvRequirement)) {
            MultipleAmountHolder selectionCriterion = (MultipleAmountHolder) espdCriterion;
            responseType.setAmount(buildAmountType(selectionCriterion.getAmount4(), selectionCriterion.getCurrency4()));
        } else if (SelectionCriterionRequirement.AMOUNT_5.equals(ccvRequirement)) {
            MultipleAmountHolder selectionCriterion = (MultipleAmountHolder) espdCriterion;
            responseType.setAmount(buildAmountType(selectionCriterion.getAmount5(), selectionCriterion.getCurrency5()));
        } else if (SelectionCriterionRequirement.PLEASE_DESCRIBE.equals(ccvRequirement) ||
                SelectionCriterionRequirement.EXPLAIN_SUPPLY_CONTRACTS_QC.equals(ccvRequirement) ||
                SelectionCriterionRequirement.EXPLAIN_CERTIFICATES_QA.equals(ccvRequirement) ||
                SelectionCriterionRequirement.EXPLAIN_CERTIFICATES_ENVIRONMENTAL.equals(ccvRequirement)) {
            DescriptionHolder selectionCriterion = (DescriptionHolder) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getDescription()));
        } else if (SelectionCriterionRequirement.PLEASE_SPECIFY.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getSpecify()));
        } else if (SelectionCriterionRequirement.DESCRIPTION_1.equals(ccvRequirement)) {
            MultipleDescriptionHolder selectionCriterion = (MultipleDescriptionHolder) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getDescription1()));
        } else if (SelectionCriterionRequirement.DESCRIPTION_2.equals(ccvRequirement)) {
            MultipleDescriptionHolder selectionCriterion = (MultipleDescriptionHolder) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getDescription2()));
        } else if (SelectionCriterionRequirement.DESCRIPTION_3.equals(ccvRequirement)) {
            MultipleDescriptionHolder selectionCriterion = (MultipleDescriptionHolder) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getDescription3()));
        } else if (SelectionCriterionRequirement.DESCRIPTION_4.equals(ccvRequirement)) {
            MultipleDescriptionHolder selectionCriterion = (MultipleDescriptionHolder) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getDescription4()));
        } else if (SelectionCriterionRequirement.DESCRIPTION_5.equals(ccvRequirement)) {
            MultipleDescriptionHolder selectionCriterion = (MultipleDescriptionHolder) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getDescription5()));
        } else if (SelectionCriterionRequirement.RATIO_1.equals(ccvRequirement)) {
            EconomicFinancialStandingCriterion selectionCriterion = (EconomicFinancialStandingCriterion) espdCriterion;
            responseType.setQuantity(buildQuantityType(selectionCriterion.getRatio1()));
        } else if (SelectionCriterionRequirement.RATIO_2.equals(ccvRequirement)) {
            EconomicFinancialStandingCriterion selectionCriterion = (EconomicFinancialStandingCriterion) espdCriterion;
            responseType.setQuantity(buildQuantityType(selectionCriterion.getRatio2()));
        } else if (SelectionCriterionRequirement.RATIO_3.equals(ccvRequirement)) {
            EconomicFinancialStandingCriterion selectionCriterion = (EconomicFinancialStandingCriterion) espdCriterion;
            responseType.setQuantity(buildQuantityType(selectionCriterion.getRatio3()));
        } else if (SelectionCriterionRequirement.RATIO_4.equals(ccvRequirement)) {
            EconomicFinancialStandingCriterion selectionCriterion = (EconomicFinancialStandingCriterion) espdCriterion;
            responseType.setQuantity(buildQuantityType(selectionCriterion.getRatio4()));
        } else if (SelectionCriterionRequirement.RATIO_5.equals(ccvRequirement)) {
            EconomicFinancialStandingCriterion selectionCriterion = (EconomicFinancialStandingCriterion) espdCriterion;
            responseType.setQuantity(buildQuantityType(selectionCriterion.getRatio5()));
        } else if (SelectionCriterionRequirement.DATE_1.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDate(buildDateType(selectionCriterion.getDate1()));
        } else if (SelectionCriterionRequirement.DATE_2.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDate(buildDateType(selectionCriterion.getDate2()));
        } else if (SelectionCriterionRequirement.DATE_3.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDate(buildDateType(selectionCriterion.getDate3()));
        } else if (SelectionCriterionRequirement.DATE_4.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDate(buildDateType(selectionCriterion.getDate4()));
        } else if (SelectionCriterionRequirement.DATE_5.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDate(buildDateType(selectionCriterion.getDate5()));
        } else if (SelectionCriterionRequirement.RECIPIENTS_1.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getRecipients1()));
        } else if (SelectionCriterionRequirement.RECIPIENTS_2.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getRecipients2()));
        } else if (SelectionCriterionRequirement.RECIPIENTS_3.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getRecipients3()));
        } else if (SelectionCriterionRequirement.RECIPIENTS_4.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getRecipients4()));
        } else if (SelectionCriterionRequirement.RECIPIENTS_5.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(selectionCriterion.getRecipients5()));
        } else if (SelectionCriterionRequirement.PERCENTAGE.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setPercent(buildPercentType(selectionCriterion.getPercentage()));
        } else if (SelectionCriterionRequirement.NUMBER_1.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setQuantity(buildNumberType(selectionCriterion.getNumber1()));
        } else if (SelectionCriterionRequirement.NUMBER_2.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setQuantity(buildNumberType(selectionCriterion.getNumber2()));
        } else if (SelectionCriterionRequirement.NUMBER_3.equals(ccvRequirement)) {
            TechnicalProfessionalCriterion selectionCriterion = (TechnicalProfessionalCriterion) espdCriterion;
            responseType.setQuantity(buildNumberType(selectionCriterion.getNumber3()));
        } else if (SelectionCriterionRequirement.SPECIFY_YEAR.equals(ccvRequirement)) {
            EconomicFinancialStandingCriterion selectionCriterion = (EconomicFinancialStandingCriterion) espdCriterion;
            responseType.setQuantity(buildYearType(selectionCriterion.getYear1()));
        }
    }

    private void fillAwardCriteria(CcvCriterionRequirement ccvRequirement, Criterion espdCriterion,
            ResponseType responseType) {
        if (espdCriterion == null) {
            return;
        }

        if (AwardRequirement.INDICATOR.equals(ccvRequirement)) {
            responseType.setIndicator(buildIndicatorType(espdCriterion.getAnswer()));
        } else if (AwardRequirement.REGISTRATION_COVERS_SELECTION_CRITERIA.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setIndicator(buildIndicatorType(awardCriterion.getBooleanValue1()));
        } else if (AwardRequirement.CORRESPONDING_PERCENTAGE.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setPercent(buildPercentType(awardCriterion.getDoubleValue1()));
        } else if (AwardRequirement.DETAILS_EMPLOYEES_CATEGORY.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription1()));
        } else if (AwardRequirement.PROVIDE_REGISTRATION_NUMBER.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription1()));
        } else if (AwardRequirement.REG_NO_AVAILABLE_ELECTRONICALLY.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription2()));
        } else if (AwardRequirement.REFERENCES_REGISTRATION.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription3()));
        } else if (AwardRequirement.EO_ABLE_PROVIDE_CERTIFICATE.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription4()));
        } else if (AwardRequirement.DOC_AVAILABLE_ELECTRONICALLY.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription5()));
        } else if (AwardRequirement.ECONOMIC_OPERATOR_ROLE.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription1()));
        } else if (AwardRequirement.OTHER_ECONOMIC_OPERATORS.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription2()));
        } else if (AwardRequirement.PARTICIPANT_GROUP_NAME.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription3()));
        } else if (AwardRequirement.PLEASE_DESCRIBE.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setDescription(buildDescriptionType(awardCriterion.getDescription1()));
        } else if (AwardRequirement.INFO_AVAILABLE_ELECTRONICALLY.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setIndicator(buildIndicatorType(awardCriterion.getInfoElectronicallyAnswer()));
        } else if (AwardRequirement.URL.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            EvidenceType evidenceType = buildEvidenceType(awardCriterion.getInfoElectronicallyUrl());
            responseType.getEvidence().add(evidenceType);
        } else if (AwardRequirement.URL_CODE.equals(ccvRequirement)) {
            AwardCriterion awardCriterion = (AwardCriterion) espdCriterion;
            responseType.setCode(buildCodeType(awardCriterion.getInfoElectronicallyCode()));
        }
    }

    private PeriodType buildPeriodType(String periodLength) {
        if (isBlank(periodLength)) {
            return null;
        }
        PeriodType periodType = new PeriodType();
        periodType.getDescription().add(buildDescriptionType(periodLength));
        return periodType;
    }

    private TypeCodeType buildCodeType(String code) {
        if (isBlank(code)) {
            return null;
        }
        TypeCodeType typeCodeType = new TypeCodeType();
        typeCodeType.setValue(code);
        return typeCodeType;
    }

    private DescriptionType buildDescriptionType(String description) {
        if (isBlank(description)) {
            // we don't want empty Description elements
            return null;
        }
        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(description);
        return descriptionType;
    }

    private QuantityType buildYearType(Integer year) {
        if (year == null) {
            return null;
        }
        QuantityType quantityType = new QuantityType();
        quantityType.setValue(BigDecimal.valueOf(year));
        quantityType.setUnitCode("YEAR");
        return quantityType;
    }

    private QuantityType buildQuantityType(Double quantity) {
        if (quantity == null) {
            return null;
        }
        QuantityType quantityType = new QuantityType();
        quantityType.setValue(BigDecimal.valueOf(quantity));
        return quantityType;
    }

    private QuantityType buildNumberType(Integer number) {
        if (number == null) {
            return null;
        }
        QuantityType quantityType = new QuantityType();
        quantityType.setValue(BigDecimal.valueOf(number));
        quantityType.setUnitCode("NUMBER");
        return quantityType;
    }

    private AmountType buildAmountType(Double amount, String currency) {
        if (amount == null) {
            return null;
        }
        AmountType amountType = new AmountType();
        amountType.setValue(BigDecimal.valueOf(amount));
        amountType.setCurrencyID(currency);
        return amountType;
    }

    private TypeCodeType buildCountryType(Country country) {
        if (country == null) {
            return null;
        }
        TypeCodeType typeCodeType = new TypeCodeType();
        typeCodeType.setValue(country.getIso2Code());
        typeCodeType.setListAgencyID("ISO");
        typeCodeType.setListID("ISO 3166-2");
        typeCodeType.setListVersionID("1.0");
        return typeCodeType;
    }

    private DateType buildDateType(Date date) {
        if (date == null) {
            return null;
        }
        DateType dateType = new DateType();
        dateType.setValue(new LocalDate(date.getTime()));
        return dateType;
    }

    private PercentType buildPercentType(Double percentage) {
        if (percentage == null) {
            return null;
        }
        PercentType percentType = new PercentType();
        percentType.setValue(BigDecimal.valueOf(percentage));
        return percentType;
    }

    private IndicatorType buildIndicatorType(boolean value) {
        IndicatorType indicatorType = new IndicatorType();
        indicatorType.setValue(value);
        return indicatorType;
    }

    private EvidenceType buildEvidenceType(String url) {
        if (isBlank(url)) {
            return null;
        }
        EvidenceType evidenceType = new EvidenceType();
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();
        AttachmentType attachmentType = new AttachmentType();
        ExternalReferenceType externalReferenceType = new ExternalReferenceType();
        attachmentType.setExternalReference(externalReferenceType);
        URIType uriType = new URIType();
        uriType.setValue(url);
        // id is mandatory for EvidenceDocumentReference
        IDType idType = new IDType();
        idType.setValue(UUID.randomUUID().toString());
        documentReferenceType.setID(idType);
        externalReferenceType.setURI(uriType);
        documentReferenceType.setAttachment(attachmentType);
        evidenceType.getEvidenceDocumentReference().add(documentReferenceType);
        return evidenceType;
    }

}
