package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import org.springframework.stereotype.Component;

/**
 * Transforms a {@link EspdDocument} into a {@link ESPDResponseType}.
 * Created by ratoico on 11/26/15.
 */
@Component
class EspdDocumentToEspdResponseTransformer implements Function<EspdDocument, ESPDResponseType> {

    @Override
    public ESPDResponseType apply(EspdDocument input) {
        ESPDResponseType responseType = new ESPDResponseType();
        return responseType;
    }
}
