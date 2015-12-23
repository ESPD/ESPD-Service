package eu.europa.ec.grow.espd.business.response;

import eu.europa.ec.grow.espd.business.common.UblRequirementTypeTemplate;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionRequirement;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterionRequirement;
import eu.europa.ec.grow.espd.domain.CriminalConvictions;
import eu.europa.ec.grow.espd.domain.Criterion;
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

        if (ExclusionCriterionRequirement.YOUR_ANSWER.equals(ccvRequirement)
                || SelectionCriterionRequirement.YOUR_ANSWER.equals(ccvRequirement)) {
            IndicatorType indicatorType = new IndicatorType();
            indicatorType.setValue(espdCriterion.getExists());
            responseType.setIndicator(indicatorType);
        } else if (ExclusionCriterionRequirement.DATE_OF_CONVICTION.equals(ccvRequirement)) {
            DateType dateType = new DateType();
            LocalDate date = getDateOfConviction(espdCriterion);
            if (date != null) {
                dateType.setValue(date);
                responseType.setDate(dateType);
            }
        } else if (ExclusionCriterionRequirement.REASON.equals(ccvRequirement)) {
            CriminalConvictions crit = (CriminalConvictions) espdCriterion;
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(crit.getReason());
            responseType.setDescription(descriptionType);
        } else if (ExclusionCriterionRequirement.WHO_CONVICTED.equals(ccvRequirement)) {
            CriminalConvictions crit = (CriminalConvictions) espdCriterion;
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(crit.getConvicted());
            responseType.setDescription(descriptionType);
        } else if (ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION.equals(ccvRequirement)) {
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(getPeriodLength(espdCriterion));
            PeriodType periodType = new PeriodType();
            periodType.getDescription().add(descriptionType);
            responseType.setPeriod(periodType);
        } else if (ExclusionCriterionRequirement.MEASURES_SELF_CLEANING.equals(ccvRequirement)) {
            IndicatorType indicatorType = new IndicatorType();
            indicatorType.setValue(getSelfCleaningAnswer(espdCriterion));
            responseType.setIndicator(indicatorType);
        } else if (ExclusionCriterionRequirement.PLEASE_DESCRIBE.equals(ccvRequirement)) {
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(getSelfCleaningDescription(espdCriterion));
            responseType.setDescription(descriptionType);
        } else if (ExclusionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY.equals(ccvRequirement)) {
            IndicatorType indicatorType = new IndicatorType();
            indicatorType.setValue(infoElectronicallyAnswer(espdCriterion));
            responseType.setIndicator(indicatorType);
        } else if (ExclusionCriterionRequirement.URL.equals(ccvRequirement)) {
            EvidenceType evidenceType = buildEvidenceType(espdCriterion);
            responseType.getEvidence().add(evidenceType);
        } else if (ExclusionCriterionRequirement.URL_CODE.equals(ccvRequirement)) {
            // TODO
        } else if (ExclusionCriterionRequirement.COUNTRY_MS.equals(ccvRequirement)) {
            Country country = getCountry(espdCriterion);
            if (country != null) {
                TypeCodeType typeCodeType = new TypeCodeType();
                typeCodeType.setValue(country.getIso2Code());
                typeCodeType.setListAgencyID("ISO");
                typeCodeType.setListID("ISO 3166-2");
                typeCodeType.setListVersionID("1.0");
                responseType.setCode(typeCodeType);
            }
        } else if (ExclusionCriterionRequirement.AMOUNT.equals(ccvRequirement)) {
            Integer amount = getAmount(espdCriterion);
            if (amount != null) {
                AmountType amountType = new AmountType();
                amountType.setValue(BigDecimal.valueOf(amount));
                amountType.setCurrencyID(getCurrency(espdCriterion));
                responseType.setAmount(amountType);
            }
        }

        return responseType;
    }

    private LocalDate getDateOfConviction(Criterion espdCriterion) {
        Date when = null;
        if (espdCriterion instanceof CriminalConvictions) {
            when = ((CriminalConvictions) espdCriterion).getDateOfConviction();
        }
        if (when != null) {
            return new LocalDate(when);
        }
        return null;
    }

    private String getPeriodLength(Criterion espdCriterion) {
        if (espdCriterion instanceof CriminalConvictions) {
            return ((CriminalConvictions) espdCriterion).getPeriodLength();
        }
        return null;
    }

    private String getSelfCleaningDescription(Criterion espdCriterion) {
        if (espdCriterion instanceof CriminalConvictions) {
            return ((CriminalConvictions) espdCriterion).getSelfCleaning() != null ?
                    ((CriminalConvictions) espdCriterion).getSelfCleaning().getDescription() : null;
        }
        return null;
    }

    private boolean infoElectronicallyAnswer(Criterion espdCriterion) {
        if (espdCriterion instanceof CriminalConvictions) {
            return ((CriminalConvictions) espdCriterion).getAvailableElectronically() != null && Boolean.TRUE
                    .equals(((CriminalConvictions) espdCriterion).getAvailableElectronically().getExists());
        }
        return false;
    }

    private String geUrl(Criterion espdCriterion) {
        if (espdCriterion instanceof CriminalConvictions) {
            return ((CriminalConvictions) espdCriterion).getAvailableElectronically() != null ?
                    ((CriminalConvictions) espdCriterion).getAvailableElectronically().getDescription() : null;
        }
        return null;
    }

    private Country getCountry(Criterion espdCriterion) {
        if (espdCriterion instanceof Taxes) {
            return ((Taxes) espdCriterion).getCountry();
        }
        return null;
    }

    private Integer getAmount(Criterion espdCriterion) {
        if (espdCriterion instanceof Taxes) {
            return ((Taxes) espdCriterion).getAmount();
        }
        return null;
    }

    private String getCurrency(Criterion espdCriterion) {
        if (espdCriterion instanceof Taxes) {
            return ((Taxes) espdCriterion).getCurrency();
        }
        return null;
    }

    private boolean getSelfCleaningAnswer(Criterion espdCriterion) {
        if (espdCriterion instanceof CriminalConvictions) {
            return ((CriminalConvictions) espdCriterion).getSelfCleaning() != null &&
                    Boolean.TRUE.equals(((CriminalConvictions) espdCriterion).getSelfCleaning().getExists());
        }
        return false;
    }

    private EvidenceType buildEvidenceType(Criterion espdCriterion) {
        EvidenceType evidenceType = new EvidenceType();
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();
        AttachmentType attachmentType = new AttachmentType();
        ExternalReferenceType externalReferenceType = new ExternalReferenceType();
        attachmentType.setExternalReference(externalReferenceType);
        URIType uriType = new URIType();
        uriType.setValue(geUrl(espdCriterion));
        externalReferenceType.setURI(uriType);
        documentReferenceType.setAttachment(attachmentType);
        evidenceType.getEvidenceDocumentReference().add(documentReferenceType);
        return evidenceType;
    }
}
