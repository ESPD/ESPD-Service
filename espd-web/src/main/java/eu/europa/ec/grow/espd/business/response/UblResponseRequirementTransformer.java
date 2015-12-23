package eu.europa.ec.grow.espd.business.response;

import eu.europa.ec.grow.espd.business.common.UblRequirementTypeTemplate;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionRequirement;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterionRequirement;
import eu.europa.ec.grow.espd.domain.CriminalConvictions;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.ExclusionCriterion;
import eu.europa.ec.grow.espd.domain.Taxes;
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

        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(ccvRequirement.getDescription());
        requirementType.setDescription(descriptionType);

        requirementType.setResponseDataType(ccvRequirement.getResponseType().getCode());

        requirementType.getResponse().add(buildResponse(ccvRequirement, espdCriterion));

        return requirementType;
    }

    private ResponseType buildResponse(CcvCriterionRequirement ccvRequirement, Criterion espdCriterion) {
        ResponseType responseType = new ResponseType();

        if (isIndicatorRequirement(ccvRequirement)) {
            responseType.setIndicator(buildIndicatorType(espdCriterion.getExists()));
        } else if (ExclusionCriterionRequirement.DATE_OF_CONVICTION.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            Date date = exclusionCriterion.getDateOfConviction();
            if (date != null) {
                DateType dateType = new DateType();
                dateType.setValue(new LocalDate(date.getTime()));
                responseType.setDate(dateType);
            }
        } else if (ExclusionCriterionRequirement.REASON.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(exclusionCriterion.getReason());
            responseType.setDescription(descriptionType);
        } else if (ExclusionCriterionRequirement.WHO_CONVICTED.equals(ccvRequirement)) {
            CriminalConvictions crit = (CriminalConvictions) espdCriterion;
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(crit.getConvicted());
            responseType.setDescription(descriptionType);
        } else if (ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(exclusionCriterion.getPeriodLength());
            PeriodType periodType = new PeriodType();
            periodType.getDescription().add(descriptionType);
            responseType.setPeriod(periodType);
        } else if (ExclusionCriterionRequirement.MEASURES_SELF_CLEANING.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            responseType.setIndicator(buildIndicatorType(exclusionCriterion.getSelfCleaningAnswer()));
        } else if (ExclusionCriterionRequirement.PLEASE_DESCRIBE.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(exclusionCriterion.getSelfCleaningDescription());
            responseType.setDescription(descriptionType);
        } else if (ExclusionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            responseType.setIndicator(buildIndicatorType(exclusionCriterion.getInfoElectronicallyAnswer()));
        } else if (ExclusionCriterionRequirement.URL.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            EvidenceType evidenceType = buildEvidenceType(exclusionCriterion);
            responseType.getEvidence().add(evidenceType);
        } else if (ExclusionCriterionRequirement.URL_CODE.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            TypeCodeType typeCodeType = new TypeCodeType();
            typeCodeType.setValue(exclusionCriterion.getInfoElectronicallyCode());
            responseType.setCode(typeCodeType);
        } else if (ExclusionCriterionRequirement.COUNTRY_MS.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            Country country = exclusionCriterion.getCountry();
            if (country != null) {
                TypeCodeType typeCodeType = new TypeCodeType();
                typeCodeType.setValue(country.getIso2Code());
                typeCodeType.setListAgencyID("ISO");
                typeCodeType.setListID("ISO 3166-2");
                typeCodeType.setListVersionID("1.0");
                responseType.setCode(typeCodeType);
            }
        } else if (ExclusionCriterionRequirement.AMOUNT.equals(ccvRequirement)) {
            ExclusionCriterion exclusionCriterion = (ExclusionCriterion) espdCriterion;
            Integer amount = exclusionCriterion.getAmount();
            if (amount != null) {
                AmountType amountType = new AmountType();
                amountType.setValue(BigDecimal.valueOf(amount));
                amountType.setCurrencyID(exclusionCriterion.getCurrency());
                responseType.setAmount(amountType);
            }
        } else if (ExclusionCriterionRequirement.BREACH_OF_OBLIGATIONS_OTHER_THAN.equals(ccvRequirement)) {
            Taxes taxesCriterion = (Taxes) espdCriterion;
            responseType.setIndicator(buildIndicatorType(taxesCriterion.isBreachEstablishedOtherThanJudicialDecision()));
        } else if (ExclusionCriterionRequirement.DESCRIBE_MEANS.equals(ccvRequirement)) {
            Taxes taxesCriterion = (Taxes) espdCriterion;
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(taxesCriterion.getMeansDescription());
            responseType.setDescription(descriptionType);
        } else if (ExclusionCriterionRequirement.DECISION_FINAL_AND_BINDING.equals(ccvRequirement)) {
            Taxes taxesCriterion = (Taxes) espdCriterion;
            responseType.setIndicator(buildIndicatorType(taxesCriterion.isDecisionFinalAndBinding()));
        } else if (ExclusionCriterionRequirement.EO_FULFILLED_OBLIGATION.equals(ccvRequirement)) {
            Taxes taxesCriterion = (Taxes) espdCriterion;
            responseType.setIndicator(buildIndicatorType(taxesCriterion.isEoFulfilledObligations()));
        } else if (ExclusionCriterionRequirement.DESCRIBE_OBLIGATIONS.equals(ccvRequirement)) {
            Taxes taxesCriterion = (Taxes) espdCriterion;
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(taxesCriterion.getObligationsDescription());
            responseType.setDescription(descriptionType);
        }

        return responseType;
    }

    private boolean isIndicatorRequirement(CcvCriterionRequirement ccvRequirement) {
        return ExclusionCriterionRequirement.YOUR_ANSWER.equals(ccvRequirement) ||
                SelectionCriterionRequirement.YOUR_ANSWER.equals(ccvRequirement);
    }

    private IndicatorType buildIndicatorType(boolean value) {
        IndicatorType indicatorType = new IndicatorType();
        indicatorType.setValue(value);
        return indicatorType;
    }

    private EvidenceType buildEvidenceType(ExclusionCriterion espdCriterion) {
        EvidenceType evidenceType = new EvidenceType();
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();
        AttachmentType attachmentType = new AttachmentType();
        ExternalReferenceType externalReferenceType = new ExternalReferenceType();
        attachmentType.setExternalReference(externalReferenceType);
        URIType uriType = new URIType();
        uriType.setValue(espdCriterion.getInfoElectronicallyUrl());
        externalReferenceType.setURI(uriType);
        documentReferenceType.setAttachment(attachmentType);
        evidenceType.getEvidenceDocumentReference().add(documentReferenceType);
        return evidenceType;
    }
}
