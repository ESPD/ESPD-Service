package eu.europa.ec.grow.espd.xml.response.importing;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.xml.common.importing.CriteriaToEspdDocumentPopulator;
import eu.europa.ec.grow.espd.xml.common.importing.EconomicOperatorImplTransformer;
import eu.europa.ec.grow.espd.xml.common.importing.PartyImplTransformer;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Create an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDResponseType}.
 * <p/>
 * Created by ratoico on 1/6/16 at 5:41 PM.
 */
@Component
@Slf4j
public class UblResponseToEspdDocumentTransformer extends UblResponseImporter {

    @Autowired
    public UblResponseToEspdDocumentTransformer(PartyImplTransformer partyImplTransformer,
            EconomicOperatorImplTransformer economicOperatorImplTransformer,
            CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator) {
        super(partyImplTransformer, economicOperatorImplTransformer, criteriaToEspdDocumentPopulator);
    }

    /**
     * Build an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDResponseType}.
     *
     * @param input
     *
     * @return
     */
    public EspdDocument importResponse(ESPDResponseType input) {
        return buildEspdDocument(null, input);
    }

    @Override
    protected List<CriterionType> selectCriteria(ESPDRequestType requestType, ESPDResponseType responseType) {
        return responseType.getCriterion();
    }
}
