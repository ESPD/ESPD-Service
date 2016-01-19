package eu.europa.ec.grow.espd.business.response;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.CriteriaToEspdDocumentPopulator;
import eu.europa.ec.grow.espd.business.common.EconomicOperatorImplTransformer;
import eu.europa.ec.grow.espd.business.common.PartyImplTransformer;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDResponseType}.
 * <p/>
 * Created by ratoico on 1/6/16 at 5:41 PM.
 */
@Component
public class UblResponseToEspdDocumentTransformer implements Function<ESPDResponseType, EspdDocument> {

    private final PartyImplTransformer partyImplTransformer;
    private final EconomicOperatorImplTransformer economicOperatorImplTransformer;
    private final CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator;

    @Autowired
    public UblResponseToEspdDocumentTransformer(PartyImplTransformer partyImplTransformer,
            EconomicOperatorImplTransformer economicOperatorImplTransformer,
            CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator) {
        this.partyImplTransformer = partyImplTransformer;
        this.economicOperatorImplTransformer = economicOperatorImplTransformer;
        this.criteriaToEspdDocumentPopulator = criteriaToEspdDocumentPopulator;
    }

    /**
     * Build an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDResponseType}.
     *
     * @param input
     *
     * @return
     */
    @Override
    public EspdDocument apply(ESPDResponseType input) {
        EspdDocument espdDocument = new EspdDocument();

        addPartyInformation(input, espdDocument);
        addCriteriaInformation(input, espdDocument);

        return espdDocument;
    }

    private void addPartyInformation(ESPDResponseType input, EspdDocument espdDocument) {
        if (input.getContractingParty() != null && input.getContractingParty().getParty() != null) {
            PartyImpl authority = partyImplTransformer.apply(input.getContractingParty().getParty());
            espdDocument.setAuthority(authority);
        }
        if (input.getEconomicOperatorParty() != null) {
            espdDocument.setEconomicOperator(economicOperatorImplTransformer.apply(input.getEconomicOperatorParty()));
        }
    }

    private void addCriteriaInformation(ESPDResponseType input, EspdDocument espdDocument) {
        criteriaToEspdDocumentPopulator.addCriteriaToEspdDocument(espdDocument, input.getCriterion());
    }
}
