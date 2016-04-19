package eu.europa.ec.grow.espd.xml.request.importing;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.EspdRequestMetadata;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import eu.europa.ec.grow.espd.domain.enums.other.DocumentTypeCode;
import eu.europa.ec.grow.espd.xml.common.importing.CommonUblImporter;
import eu.europa.ec.grow.espd.xml.common.importing.CriteriaToEspdDocumentPopulator;
import eu.europa.ec.grow.espd.xml.common.importing.PartyImplTransformer;
import eu.europa.ec.grow.espd.xml.common.importing.UblDocumentReferences;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Create an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDRequestType}.
 * <p/>
 * Created by ratoico on 11/25/15 11:28 AM.
 */
@Component
public class UblRequestToEspdDocumentTransformer {

    private final PartyImplTransformer partyImplTransformer;
    private final CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator;

    @Autowired
    UblRequestToEspdDocumentTransformer(PartyImplTransformer partyImplTransformer,
            CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator) {
        this.partyImplTransformer = partyImplTransformer;
        this.criteriaToEspdDocumentPopulator = criteriaToEspdDocumentPopulator;
    }

    /**
     * Build an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDRequestType}.
     *
     * @param input The XML object structure of an ESPD Request
     *
     * @return An {@link EspdDocument} entity containing the information coming from the XML request file.
     */
    public EspdDocument buildRequest(ESPDRequestType input) {
        EspdDocument espdDocument = new EspdDocument();

        addPartyInformation(input, espdDocument);
        addCriteriaInformation(input, espdDocument);
        addTedInformation(input, espdDocument);
        addEspdRequestInformation(input, espdDocument);
        addProjectLotInformation(input, espdDocument);

        return espdDocument;
    }

    private void addPartyInformation(ESPDRequestType input, EspdDocument espdDocument) {
        if (input.getContractingParty() == null || input.getContractingParty().getParty() == null) {
            return;
        }

        PartyImpl authority = partyImplTransformer.apply(input.getContractingParty().getParty());
        espdDocument.setAuthority(authority);
    }

    private void addEspdRequestInformation(ESPDRequestType input, EspdDocument espdDocument) {
        EspdRequestMetadata metadata = new EspdRequestMetadata();
        metadata.setId(readRequestId(input));
        metadata.setIssueDate(CommonUblImporter.readIssueDate(input.getIssueDate(), input.getIssueTime()));
        metadata.setDescription(readRequestDescription(input));
        // TODO build URL of the request
        espdDocument.setRequestMetadata(metadata);
    }

    private String readRequestId(ESPDRequestType input) {
        if (input.getID() == null) {
            return null;
        }
        return input.getID().getValue();
    }

    private String readRequestDescription(ESPDRequestType input) {
        if (input.getContractFolderID() == null || isBlank(input.getContractFolderID().getValue())) {
            return null;
        }
        return "ESPDRequest " + input.getContractFolderID().getValue();
    }

    private void addTedInformation(ESPDRequestType input, EspdDocument espdDocument) {
        if (input.getContractFolderID() != null) {
            espdDocument.setFileRefByCA(input.getContractFolderID().getValue());
        }

        List<DocumentReferenceType> tedContractNumbers = UblDocumentReferences
                .filterByTypeCode(input.getAdditionalDocumentReference(), DocumentTypeCode.TED_CN);
        if (isNotEmpty(tedContractNumbers)) {
            DocumentReferenceType procurementInfo = tedContractNumbers.get(0);
            espdDocument.setOjsNumber(UblDocumentReferences.readIdValue(procurementInfo));
            espdDocument.setProcedureTitle(UblDocumentReferences.readFileNameValue(procurementInfo));
            espdDocument.setProcedureShortDesc(UblDocumentReferences.readDescriptionValue(procurementInfo));
            espdDocument.setTedUrl(UblDocumentReferences.readUrlValue(procurementInfo));
            // read the tedReceptionId as a second description
            espdDocument.setTedReceptionId(UblDocumentReferences.readDescriptionValue(procurementInfo, 1));
        }
    }

    private void addCriteriaInformation(ESPDRequestType input, EspdDocument espdDocument) {
        criteriaToEspdDocumentPopulator.addCriteriaToEspdDocument(espdDocument, input.getCriterion());
    }

    private void addProjectLotInformation(ESPDRequestType input, EspdDocument espdDocument) {
        espdDocument.setLotConcerned(CommonUblImporter.readLot(input.getProcurementProjectLot()));
    }

}
