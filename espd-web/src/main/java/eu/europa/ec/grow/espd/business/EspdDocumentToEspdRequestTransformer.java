package eu.europa.ec.grow.espd.business;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.util.Function;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import org.springframework.stereotype.Component;

/**
 * Transforms a {@link EspdDocument} into a {@link ESPDRequestType}.
 *
 * Created by vigi on 11/11/15:10:58 AM.
 */
@Component
public class EspdDocumentToEspdRequestTransformer implements Function<EspdDocument, ESPDRequestType> {

    @Override
    public ESPDRequestType apply(final EspdDocument espdDocument) {
        return new ESPDRequestType();
    }
}
