package eu.europa.ec.grow.espd.business.request;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.CriteriaToEspdDocumentPopulator;
import eu.europa.ec.grow.espd.business.common.PartyImplTransformer;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ExternalReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Create an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDRequestType}.
 * <p/>
 * Created by ratoico on 11/25/15 11:28 AM.
 */
@Component
public class UblRequestToEspdDocumentTransformer implements Function<ESPDRequestType, EspdDocument> {

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
     * @param input
     *
     * @return
     */
    @Override
    public EspdDocument apply(ESPDRequestType input) {
        EspdDocument espdDocument = new EspdDocument();

        addPartyInformation(input, espdDocument);
        addCriteriaInformation(input, espdDocument);
        addProcurementProcedureInformation(input, espdDocument);

        return espdDocument;
    }

    private void addPartyInformation(ESPDRequestType input, EspdDocument espdDocument) {
        if (input.getContractingParty() == null || input.getContractingParty().getParty() == null) {
            return;
        }

        PartyImpl authority = partyImplTransformer.apply(input.getContractingParty().getParty());
        espdDocument.setAuthority(authority);
    }

    private void addProcurementProcedureInformation(ESPDRequestType input, EspdDocument espdDocument) {
        if (input.getContractFolderID() != null) {
            espdDocument.setFileRefByCA(input.getContractFolderID().getValue());
        }
        if (isNotEmpty(input.getAdditionalDocumentReference())) {
            DocumentReferenceType procurementInfo = input.getAdditionalDocumentReference().get(0);
            if (procurementInfo.getID() != null) {
                espdDocument.setOjsNumber(procurementInfo.getID().getValue());
            }
            if (procurementInfo.getAttachment() != null && procurementInfo.getAttachment().getExternalReference() != null) {
                ExternalReferenceType externalReference = procurementInfo.getAttachment().getExternalReference();
                if (externalReference.getFileName() != null) {
                    espdDocument.setProcedureTitle(externalReference.getFileName().getValue());
                }
                if (isNotEmpty(externalReference.getDescription())) {
                    DescriptionType descriptionType = externalReference.getDescription().get(0);
                    espdDocument.setProcedureShortDesc(descriptionType.getValue());
                }
            }
        }
    }

    private void addCriteriaInformation(ESPDRequestType input, EspdDocument espdDocument) {
        criteriaToEspdDocumentPopulator.addCriteriaToEspdDocument(espdDocument, input.getCriterion());
    }

}
