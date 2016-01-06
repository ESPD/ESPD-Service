package eu.europa.ec.grow.espd.business.response;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.CriteriaToEspdDocumentPopulator;
import eu.europa.ec.grow.espd.business.common.PartyImplTransformer;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ratoico on 1/6/16 at 5:41 PM.
 */
@Component
public class UblResponseToEspdDocumentTransformer implements Function<ESPDResponseType, EspdDocument> {

    private final PartyImplTransformer partyImplTransformer;
    private final CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator;

    @Autowired
    public UblResponseToEspdDocumentTransformer(PartyImplTransformer partyImplTransformer,
            CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator) {
        this.partyImplTransformer = partyImplTransformer;
        this.criteriaToEspdDocumentPopulator = criteriaToEspdDocumentPopulator;
    }

    @Override
    public EspdDocument apply(ESPDResponseType input) {
        EspdDocument espdDocument = new EspdDocument();

        addPartyInformation(input, espdDocument);
        addCriteriaInformation(input, espdDocument);

        return espdDocument;
    }

    private void addPartyInformation(ESPDResponseType input, EspdDocument espdDocument) {
        if (input.getContractingParty() == null || input.getContractingParty().getParty() == null) {
            return;
        }

        PartyImpl authority = partyImplTransformer.apply(input.getContractingParty().getParty());
        espdDocument.setAuthority(authority);
    }

    private void addCriteriaInformation(ESPDResponseType input, EspdDocument espdDocument) {
        criteriaToEspdDocumentPopulator.addCriteriaToEspdDocument(espdDocument, input.getCriterion());
    }
}
