package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vigi on 11/25/15:11:28 AM.
 */
@Component
class EspdRequestToEspdDocumentTransformer implements Function<ESPDRequestType, EspdDocument> {

    private final ToPartyImplTransformer toPartyImplTransformer;

    @Autowired
    EspdRequestToEspdDocumentTransformer(ToPartyImplTransformer toPartyImplTransformer) {
        this.toPartyImplTransformer = toPartyImplTransformer;
    }

    @Override
    public EspdDocument apply(ESPDRequestType input) {
        EspdDocument espdDocument = new EspdDocument();

        addPartyInformation(input, espdDocument);

        return espdDocument;
    }

    private void addPartyInformation(ESPDRequestType input, EspdDocument espdDocument) {
        if (input.getContractingParty() == null || input.getContractingParty().getParty() == null) {
            return;
        }

        PartyImpl authority = toPartyImplTransformer.apply(input.getContractingParty().getParty());
        espdDocument.setAuthority(authority);
    }
}
