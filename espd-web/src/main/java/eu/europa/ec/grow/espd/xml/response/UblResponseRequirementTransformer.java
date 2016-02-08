package eu.europa.ec.grow.espd.xml.response;

import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.criteria.enums.ExpectedResponseType;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.xml.common.UblRequirementTypeTemplate;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.ResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonbasiccomponents_1.IndicatorType;
import isa.names.specification.ubl.schema.xsd.cev_commonaggregatecomponents_1.EvidenceType;
import lombok.extern.slf4j.Slf4j;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.AttachmentType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ExternalReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PeriodType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;

import org.apache.commons.beanutils.PropertyUtils;
import org.joda.time.LocalDate;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by ratoico on 12/22/15 at 4:07 PM.
 */
@Slf4j
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

        addRequirementValueOnResponse(ccvRequirement, espdCriterion, responseType);

        return responseType;
    }

    private void addRequirementValueOnResponse(CcvCriterionRequirement ccvRequirement, Criterion espdCriterion,
            ResponseType responseType) {
        if (espdCriterion == null) {
            return;
        }

        ExpectedResponseType type = (ExpectedResponseType) ccvRequirement.getResponseType();
        switch (type) {
        case INDICATOR:
            Boolean answer = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setIndicator(buildIndicatorType(answer));
            break;
        case DATE:
            Date date = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setDate(buildDateType(date));
            break;
        case DESCRIPTION:
            String description = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setDescription(buildDescriptionType(description));
            break;
        case EVIDENCE_URL:
            String url = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.getEvidence().add(buildEvidenceType(url));
            break;
        case QUANTITY:
            Double quantity = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setQuantity(buildQuantityType(quantity));
            break;
        case QUANTITY_YEAR:
            Integer year = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setQuantity(buildYearType(year));
            break;
        case QUANTITY_INTEGER:
            Integer value = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setQuantity(buildQuantityIntegerType(value));
            break;
        case AMOUNT:
            Double amount = readRequirementFirstValue(ccvRequirement, espdCriterion);
            String currency = readRequirementSecondValue(ccvRequirement, espdCriterion);
            responseType.setAmount(buildAmountType(amount, currency));
            break;
        case CODE_COUNTRY:
            Country country = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setCode(buildCountryType(country));
            break;
        case PERCENTAGE:
            Double percentage = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setPercent(buildPercentType(percentage));
            break;
        case PERIOD:
            String period = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setPeriod(buildPeriodType(period));
            break;
        case CODE:
            String code = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setCode(buildCodeType(code));
            break;
        default:
            throw new IllegalArgumentException(String.format(
                    "Could not save the requirement '%s' with id '%s' and expected response type '%s' on the ESPD Response.",
                    ccvRequirement, ccvRequirement.getId(), type));
        }

    }

    private <T> T readRequirementFirstValue(CcvCriterionRequirement requirement, Criterion espdCriterion) {
        // most requirements are mapped to only one ESPD field
        return readRequirementValueAtPosition(requirement, espdCriterion, 0);
    }

    private <T> T readRequirementSecondValue(CcvCriterionRequirement requirement, Criterion espdCriterion) {
        // this method is needed by requirements of type AMOUNT which are mapped to two fields (amount and currency)
        return readRequirementValueAtPosition(requirement, espdCriterion, 1);
    }

    @SuppressWarnings("unchecked")
    private <T> T readRequirementValueAtPosition(CcvCriterionRequirement requirement, Criterion espdCriterion,
            int position) {
        if (requirement.getEspdCriterionFields().get(position) == null) {
            // there is one criterion which is not mapped to any field (3a6fefd4-f458-4d43-97fb-0725fce5dce2) financial ratio
            return null;
        }
        try {
            // all requirements except the ones representing an AMOUNT are mapped to a single ESPD field
            return (T) PropertyUtils.getProperty(espdCriterion, requirement.getEspdCriterionFields().get(position));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(e.getMessage(), e);
            return null;
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

    private QuantityType buildQuantityIntegerType(Integer number) {
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
