package eu.europa.ec.grow.espd.xml.common.exporting;

import eu.europa.ec.grow.espd.domain.enums.other.Country;
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
 * Created by ratoico on 2/15/16 at 11:33 AM.
 */
public final class UblRequirementFactory {

    private UblRequirementFactory() {
        
    }

    public static PeriodType buildPeriodType(String periodLength) {
        if (isBlank(periodLength)) {
            return null;
        }
        PeriodType periodType = new PeriodType();
        periodType.getDescription().add(buildDescriptionType(periodLength));
        return periodType;
    }

    public static TypeCodeType buildCodeType(String code) {
        if (isBlank(code)) {
            return null;
        }
        TypeCodeType typeCodeType = new TypeCodeType();
        typeCodeType.setValue(code);
        return typeCodeType;
    }

    public static DescriptionType buildDescriptionType(String description) {
        if (isBlank(description)) {
            // we don't want empty Description elements
            return null;
        }
        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(description);
        return descriptionType;
    }

    public static QuantityType buildYearType(Integer year) {
        if (year == null) {
            return null;
        }
        QuantityType quantityType = new QuantityType();
        quantityType.setValue(BigDecimal.valueOf(year));
        quantityType.setUnitCode("YEAR");
        return quantityType;
    }

    public static QuantityType buildQuantityType(Double quantity) {
        if (quantity == null) {
            return null;
        }
        QuantityType quantityType = new QuantityType();
        quantityType.setValue(BigDecimal.valueOf(quantity));
        return quantityType;
    }

    public static QuantityType buildQuantityIntegerType(Integer number) {
        if (number == null) {
            return null;
        }
        QuantityType quantityType = new QuantityType();
        quantityType.setValue(BigDecimal.valueOf(number));
        quantityType.setUnitCode("NUMBER");
        return quantityType;
    }

    public static AmountType buildAmountType(Double amount, String currency) {
        if (amount == null) {
            return null;
        }
        AmountType amountType = new AmountType();
        amountType.setValue(BigDecimal.valueOf(amount));
        amountType.setCurrencyID(currency);
        return amountType;
    }

    public static TypeCodeType buildCountryType(Country country) {
        if (country == null) {
            return null;
        }
        TypeCodeType typeCodeType = new TypeCodeType();
        typeCodeType.setValue(country.getIsoCode());
        typeCodeType.setListAgencyID("ISO");
        typeCodeType.setListID(country.getIsoType());
        typeCodeType.setListVersionID("1.0");
        return typeCodeType;
    }

    public static DateType buildDateType(Date date) {
        if (date == null) {
            return null;
        }
        DateType dateType = new DateType();
        dateType.setValue(new LocalDate(date.getTime()));
        return dateType;
    }

    public static PercentType buildPercentType(Double percentage) {
        if (percentage == null) {
            return null;
        }
        PercentType percentType = new PercentType();
        percentType.setValue(BigDecimal.valueOf(percentage));
        return percentType;
    }

    public static IndicatorType buildIndicatorType(boolean value) {
        IndicatorType indicatorType = new IndicatorType();
        indicatorType.setValue(value);
        return indicatorType;
    }

    public static EvidenceType buildEvidenceType(String url) {
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