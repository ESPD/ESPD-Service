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

package eu.europa.ec.grow.espd.domain.enums.criteria;

import eu.europa.ec.grow.espd.domain.enums.other.Country;
import eu.europa.ec.grow.espd.domain.ubl.CcvResponseType;
import eu.europa.ec.grow.espd.domain.ubl.ResponseValueParser;
import eu.europa.ec.grow.espd.util.Amount;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.ResponseType;
import isa.names.specification.ubl.schema.xsd.cev_commonaggregatecomponents_1.EvidenceType;
import lombok.Getter;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ExternalReferenceType;

import java.math.BigDecimal;
import java.util.Date;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * Created by ratoico on 12/4/15 at 1:58 PM.
 */
@Getter
public enum ExpectedResponseType implements CcvResponseType {

    INDICATOR(new IndicatorValueParser()),
    DATE(new DateValueParser()),
    DESCRIPTION(new DescriptionValueParser()),
    EVIDENCE_URL(new UrlValueParser()),
    QUANTITY(new QuantityValueParser()),
    QUANTITY_YEAR(new QuantityIntegerValueParser()),
    QUANTITY_INTEGER(new QuantityIntegerValueParser()),
    AMOUNT(new AmountValueParser()),
    CODE_COUNTRY(new CountryValueParser()),
    PERCENTAGE(new PercentageValueParser()),
    PERIOD(new PeriodValueParser()),
    CODE(new CodeValueParser());

    private final ResponseValueParser valueParser;

    ExpectedResponseType(ResponseValueParser valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public Object parseValue(ResponseType responseType) {
        return this.valueParser.parseValue(responseType);
    }

    private static class IndicatorValueParser implements ResponseValueParser<Boolean> {

        @Override
        public Boolean parseValue(ResponseType responseType) {
            return responseType.getIndicator() != null && responseType.getIndicator().isValue();
        }
    }

    private static class DateValueParser implements ResponseValueParser<Date> {

        @Override
        public Date parseValue(ResponseType responseType) {
            if (responseType.getDate() == null || responseType.getDate().getValue() == null) {
                return null;
            }
            return responseType.getDate().getValue().toDate();
        }
    }

    private static class DescriptionValueParser implements ResponseValueParser<String> {

        @Override
        public String parseValue(ResponseType responseType) {
            if (responseType.getDescription() == null) {
                return null;
            }
            return responseType.getDescription().getValue();
        }
    }

    private static class PeriodValueParser implements ResponseValueParser<String> {

        @Override
        public String parseValue(ResponseType responseType) {
            if (responseType.getPeriod() == null || isEmpty(responseType.getPeriod().getDescription())) {
                return null;
            }
            return responseType.getPeriod().getDescription().get(0).getValue();
        }
    }

    private static class UrlValueParser implements ResponseValueParser<String> {

        @Override
        public String parseValue(ResponseType responseType) {
            if (isEmpty(responseType.getEvidence())) {
                return null;
            }

            EvidenceType evidence = responseType.getEvidence().get(0);
            if (evidence == null || isEmpty(evidence.getEvidenceDocumentReference())) {
                return null;
            }

            DocumentReferenceType reference = evidence.getEvidenceDocumentReference().get(0);
            if (reference == null || reference.getAttachment() == null) {
                return null;
            }

            ExternalReferenceType externalReference = reference.getAttachment().getExternalReference();
            if (externalReference == null || externalReference.getURI() == null) {
                return null;
            }

            return externalReference.getURI().getValue();
        }
    }

    private static class CodeValueParser implements ResponseValueParser<String> {

        @Override
        public String parseValue(ResponseType responseType) {
            if (responseType.getCode() == null) {
                return null;
            }
            return responseType.getCode().getValue();
        }
    }

    private static class CountryValueParser implements ResponseValueParser<Country> {

        @Override
        public Country parseValue(ResponseType responseType) {
            if (responseType.getCode() == null) {
                return null;
            }
            return Country.findByIso2Code(responseType.getCode().getValue());
        }
    }

    private static class AmountValueParser implements ResponseValueParser<Amount> {

        @Override
        public Amount parseValue(ResponseType responseType) {
            if (responseType.getAmount() == null || responseType.getAmount().getValue() == null) {
                return null;
            }
            return new Amount(responseType.getAmount().getValue(), responseType.getAmount().getCurrencyID());
        }
    }

    private static class PercentageValueParser implements ResponseValueParser<BigDecimal> {

        @Override
        public BigDecimal parseValue(ResponseType responseType) {
            if (responseType.getPercent() == null || responseType.getPercent().getValue() == null) {
                return null;
            }
            return responseType.getPercent().getValue();
        }
    }

    private static class QuantityValueParser implements ResponseValueParser<BigDecimal> {

        @Override
        public BigDecimal parseValue(ResponseType responseType) {
            if (responseType.getQuantity() == null || responseType.getQuantity().getValue() == null) {
                return null;
            }
            return responseType.getQuantity().getValue();
        }
    }

    private static class QuantityIntegerValueParser implements ResponseValueParser<Integer> {

        @Override
        public Integer parseValue(ResponseType responseType) {
            if (responseType.getQuantity() == null || responseType.getQuantity().getValue() == null) {
                return null;
            }
            return responseType.getQuantity().getValue().intValue();
        }
    }
}
