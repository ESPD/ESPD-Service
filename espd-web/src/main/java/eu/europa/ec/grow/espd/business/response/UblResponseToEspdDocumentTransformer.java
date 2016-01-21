package eu.europa.ec.grow.espd.business.response;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.CriteriaToEspdDocumentPopulator;
import eu.europa.ec.grow.espd.business.common.EconomicOperatorImplTransformer;
import eu.europa.ec.grow.espd.business.common.PartyImplTransformer;
import eu.europa.ec.grow.espd.business.common.UblDocumentReferences;
import eu.europa.ec.grow.espd.constants.enums.DocumentTypeCode;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.EspdRequestMetadata;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import lombok.extern.slf4j.Slf4j;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ProcurementProjectLotType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Create an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDResponseType}.
 * <p/>
 * Created by ratoico on 1/6/16 at 5:41 PM.
 */
@Component
@Slf4j
public class UblResponseToEspdDocumentTransformer implements Function<ESPDResponseType, EspdDocument> {

    private final PartyImplTransformer partyImplTransformer;
    private final EconomicOperatorImplTransformer economicOperatorImplTransformer;
    private final CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator;
    private final UblDocumentReferences ublDocumentReferences;

    @Autowired
    public UblResponseToEspdDocumentTransformer(PartyImplTransformer partyImplTransformer,
            EconomicOperatorImplTransformer economicOperatorImplTransformer,
            CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator,
            UblDocumentReferences ublDocumentReferences) {
        this.partyImplTransformer = partyImplTransformer;
        this.economicOperatorImplTransformer = economicOperatorImplTransformer;
        this.criteriaToEspdDocumentPopulator = criteriaToEspdDocumentPopulator;
        this.ublDocumentReferences = ublDocumentReferences;
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
        addOtherInformation(input, espdDocument);

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

    private void addOtherInformation(ESPDResponseType input, EspdDocument espdDocument) {
        addProjectLotInformation(input, espdDocument);
        addEspdRequestInformation(input, espdDocument);
        addTedInformation(input, espdDocument);
    }

    private void addProjectLotInformation(ESPDResponseType input, EspdDocument espdDocument) {
        if (isEmpty(input.getProcurementProjectLot())) {
            return;
        }

        ProcurementProjectLotType lotType = input.getProcurementProjectLot().get(0);
        if (lotType.getID() != null) {
            espdDocument.setLotConcerned(lotType.getID().getValue());
        }
    }

    private void addEspdRequestInformation(ESPDResponseType input, EspdDocument espdDocument) {
        EspdRequestMetadata metadata = new EspdRequestMetadata();
        List<DocumentReferenceType> requestReferences = ublDocumentReferences
                .filterByTypeCode(input.getAdditionalDocumentReference(), DocumentTypeCode.ESPD_REQUEST);
        if (isNotEmpty(requestReferences)) {
            DocumentReferenceType requestInfo = requestReferences.get(0);
            metadata.setId(ublDocumentReferences.readIdValue(requestInfo));
            metadata.setIssueDate(ublDocumentReferences.readIssueDate(requestInfo));
            metadata.setDescription(ublDocumentReferences.readDocumentDescriptionValue(requestInfo));
            metadata.setUrl(ublDocumentReferences.readUrlValue(requestInfo));
        } else {
            log.warn("No ESPD Request information found for response '{}'.", getResponseId(input));
        }
        espdDocument.setRequestMetadata(metadata);
    }

    private void addTedInformation(ESPDResponseType input, EspdDocument espdDocument) {
        if (input.getContractFolderID() != null) {
            espdDocument.setFileRefByCA(input.getContractFolderID().getValue());
        }

        List<DocumentReferenceType> tedContractNumbers = ublDocumentReferences
                .filterByTypeCode(input.getAdditionalDocumentReference(), DocumentTypeCode.TED_CN);
        if (isNotEmpty(tedContractNumbers)) {
            DocumentReferenceType procurementInfo = tedContractNumbers.get(0);
            espdDocument.setOjsNumber(ublDocumentReferences.readIdValue(procurementInfo));
            espdDocument.setProcedureTitle(ublDocumentReferences.readFileNameValue(procurementInfo));
            espdDocument.setProcedureShortDesc(ublDocumentReferences.readDescriptionValue(procurementInfo));
        } else {
            log.warn("No TED information found for response '{}'.", getResponseId(input));
        }
    }

    private String getResponseId(ESPDResponseType input) {
        if (input == null || input.getID() == null) {
            return "";
        }
        return input.getID().getValue();
    }
}
