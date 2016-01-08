package eu.europa.ec.grow.espd.business.common;

import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.criteria.enums.ExpectedResponseType;
import eu.europa.ec.grow.espd.domain.Amount;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.entities.CcvResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.ResponseType;
import isa.names.specification.ubl.schema.xsd.cev_commonaggregatecomponents_1.EvidenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ExternalReferenceType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * Class that reads values from a UBL {@link ResponseType} based on the UBL requirement response data type.
 * <p/>
 * Created by ratoico on 1/7/16 at 2:04 PM.
 */
final class ResponseValueParsers {

    private static final Map<CcvResponseType, ResponseValueParser> PARSERS = new HashMap<>();

    static {
        PARSERS.put(ExpectedResponseType.INDICATOR, new IndicatorValueParser());
        PARSERS.put(ExpectedResponseType.DATE, new DateValueParser());
        PARSERS.put(ExpectedResponseType.DESCRIPTION, new DescriptionValueParser());
        PARSERS.put(ExpectedResponseType.PERIOD, new PeriodValueParser());
        PARSERS.put(ExpectedResponseType.URL, new UrlValueParser());
        PARSERS.put(ExpectedResponseType.CODE, new CodeValueParser());
        PARSERS.put(ExpectedResponseType.COUNTRY, new CountryValueParser());
        PARSERS.put(ExpectedResponseType.AMOUNT, new AmountValueParser());
        PARSERS.put(ExpectedResponseType.PERCENTAGE, new PercentageValueParser());
    }

    private ResponseValueParsers() {

    }

    public static <T> T parse(CcvCriterionRequirement requirement, ResponseType responseType) {
        ResponseValueParser<T> parser = PARSERS.get(requirement.getResponseType());
        checkArgument(parser != null,
                "Could not find a response value parser for requirement '%s' with id '%s' and expected response type '%s'.",
                requirement, requirement.getId(), requirement.getResponseType());
        return parser.parseValue(responseType);
    }

    private interface ResponseValueParser<T> {

        T parseValue(ResponseType responseType);

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
            return new Amount(responseType.getAmount().getValue().doubleValue(),
                    responseType.getAmount().getCurrencyID());
        }
    }

    private static class PercentageValueParser implements ResponseValueParser<Double> {

        @Override
        public Double parseValue(ResponseType responseType) {
            if (responseType.getPercent() == null || responseType.getPercent().getValue() == null) {
                return null;
            }
            return responseType.getPercent().getValue().doubleValue();
        }
    }
}
