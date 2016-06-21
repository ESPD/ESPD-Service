/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

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

    public static QuantityType buildQuantityType(BigDecimal quantity) {
        if (quantity == null) {
            return null;
        }
        QuantityType quantityType = new QuantityType();
        quantityType.setValue(quantity);
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

    public static AmountType buildAmountType(BigDecimal amount, String currency) {
        if (amount == null) {
            return null;
        }
        AmountType amountType = new AmountType();
        amountType.setValue(amount);
        amountType.setCurrencyID(currency);
        return amountType;
    }

    public static TypeCodeType buildCountryType(Country country) {
        if (country == null) {
            return null;
        }
        TypeCodeType typeCodeType = new TypeCodeType();
        typeCodeType.setValue(country.getIso2Code());
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

    public static PercentType buildPercentType(BigDecimal percentage) {
        if (percentage == null) {
            return null;
        }
        PercentType percentType = new PercentType();
        percentType.setValue(percentage);
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
