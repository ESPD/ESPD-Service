package eu.europa.ec.grow.espd.business.request;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.CommonUblFactory;
import eu.europa.ec.grow.espd.business.common.UblContractingPartyTypeTransformer;
import eu.europa.ec.grow.espd.business.common.UblCriteriaTemplate;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Transforms a {@link EspdDocument} into a {@link ESPDRequestType}.
 * <p/>
 * Created by vigi on 11/11/15:10:58 AM.
 */
@Component
public class UblRequestTypeTransformer implements Function<EspdDocument, ESPDRequestType> {

    private final CommonUblFactory commonUblFactory;
    private final UblContractingPartyTypeTransformer contractingPartyTransformer;
    private final UblCriteriaTemplate criteriaTransformer;

    @Autowired
    UblRequestTypeTransformer(CommonUblFactory commonUblFactory,
            UblContractingPartyTypeTransformer contractingPartyTransformer) {
        this.commonUblFactory = commonUblFactory;
        this.contractingPartyTransformer = contractingPartyTransformer;
        // TODO use template method here as well
        this.criteriaTransformer = new UblRequestCriteriaTransformer();
    }

    @Override
    public ESPDRequestType apply(EspdDocument espdDocument) {
        ESPDRequestType espdRequestType = new ESPDRequestType();

        addUBLVersionInformation(espdRequestType);
        addCustomizationInformation(espdRequestType);
        addIdInformation(espdRequestType);
        addCopyIndicatorInformation(espdRequestType);
        addVersionIdInformation(espdRequestType);
        addIssueDateAndTimeInformation(espdRequestType);
        addContractFolderIdInformation(espdDocument, espdRequestType);
        addContractingPartyInformation(espdDocument, espdRequestType);
        addProcurementProjectLots(espdRequestType);
        addAdditionalDocumentReference(espdDocument, espdRequestType);
        addCriteria(espdDocument, espdRequestType);

        return espdRequestType;
    }

    private void addUBLVersionInformation(ESPDRequestType espdRequestType) {
        espdRequestType.setUBLVersionID(commonUblFactory.buildUblVersionIDType());
    }

    private void addCustomizationInformation(ESPDRequestType espdRequestType) {
        espdRequestType
                .setCustomizationID(commonUblFactory.buildCustomizationIDType(CommonUblFactory.EspdType.ESPD_REQUEST));
    }

    private void addIdInformation(ESPDRequestType espdRequestType) {
        espdRequestType.setID(commonUblFactory.buildIdType());
    }

    private void addCopyIndicatorInformation(ESPDRequestType espdRequestType) {
        espdRequestType.setCopyIndicator(commonUblFactory.buildCopyIndicatorType(false));
    }

    private void addVersionIdInformation(ESPDRequestType espdRequestType) {
        espdRequestType.setVersionID(commonUblFactory.buildVersionIDType());
    }

    private void addIssueDateAndTimeInformation(ESPDRequestType espdRequestType) {
        Date now = new Date();
        espdRequestType.setIssueTime(commonUblFactory.buildIssueTimeType(now));
        espdRequestType.setIssueDate(commonUblFactory.buildIssueDateType(now));
    }

    private void addContractFolderIdInformation(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        ContractFolderIDType contractFolderIDType = new ContractFolderIDType();
        contractFolderIDType.setValue(espdDocument.getFileRefByCA());
        contractFolderIDType.setSchemeAgencyID("TeD");
        espdRequestType.setContractFolderID(contractFolderIDType);
    }

    private void addContractingPartyInformation(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        ContractingPartyType contractingPartyType = contractingPartyTransformer.apply(espdDocument.getAuthority());
        espdRequestType.setContractingParty(contractingPartyType);
    }

    private void addProcurementProjectLots(ESPDRequestType espdRequestType) {
        ProcurementProjectLotType procurementProjectLotType = new ProcurementProjectLotType();
        IDType idType = new IDType();
        idType.setValue("0");
        procurementProjectLotType.setID(idType);
        espdRequestType.getProcurementProjectLot().add(procurementProjectLotType);
    }

    /**
     * Reference to the Contract Notice in TeD.
     * <p></p>
     * For procurement projects above the threshold it is compulsory to specify the following data,
     * by means of an AdditionalDocumentReference element, about the Contract Notice published in TeD:
     * the OJEU S number[], date[], page[], Notice number in OJS: YYYY/S [][][]-[][][][][][],
     * Title and Description of the Procurement Project
     *
     * @param espdDocument
     * @param espdRequestType
     */
    private void addAdditionalDocumentReference(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();
        IDType idType = new IDType();
        idType.setSchemeID("ISO/IEC 9834-8:2008 - 4UUID");
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setSchemeAgencyName(Agency.EU_COM_GROW.getLongName());
        idType.setSchemeVersionID("1.1");
        idType.setValue(espdDocument.getOjsNumber());
        documentReferenceType.setID(idType);

        // A reference to a Contract Notice published in the TeD platform (European Commission, Office of Publications).
        DocumentTypeCodeType documentTypeCode = new DocumentTypeCodeType();
        documentTypeCode.setListAgencyID(Agency.EU_COM_GROW.getIdentifier());
        documentTypeCode.setListID("ReferencesTypeCodes");
        documentTypeCode.setListVersionID("1.0");
        documentTypeCode.setValue("TeD_CN");
        documentReferenceType.setDocumentTypeCode(documentTypeCode);

        AttachmentType attachmentType = new AttachmentType();
        ExternalReferenceType externalReferenceType = new ExternalReferenceType();

        FileNameType fileNameType = new FileNameType();
        fileNameType.setValue(espdDocument.getProcedureTitle());
        externalReferenceType.setFileName(fileNameType);

        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(espdDocument.getProcedureShortDesc());
        externalReferenceType.getDescription().add(descriptionType);

        attachmentType.setExternalReference(externalReferenceType);
        documentReferenceType.setAttachment(attachmentType);

        espdRequestType.getAdditionalDocumentReference().add(documentReferenceType);
    }

    private void addCriteria(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        espdRequestType.getCriterion().addAll(criteriaTransformer.apply(espdDocument));
    }

}