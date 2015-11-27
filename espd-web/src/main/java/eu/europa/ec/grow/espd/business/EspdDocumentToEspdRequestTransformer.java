package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractingPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ProcurementProjectLotType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ContractFolderIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Transforms a {@link EspdDocument} into a {@link ESPDRequestType}.
 * <p/>
 * Created by vigi on 11/11/15:10:58 AM.
 */
@Component
class EspdDocumentToEspdRequestTransformer implements Function<EspdDocument, ESPDRequestType> {

    private final CommonUblFactory commonUblFactory;
    private final ToContractingPartyTransformer contractingPartyTransformer;
    private final EspdRequestCriteriaTransformer criteriaTransformer;

    @Autowired
    EspdDocumentToEspdRequestTransformer(CommonUblFactory commonUblFactory,
            ToContractingPartyTransformer contractingPartyTransformer,
            EspdRequestCriteriaTransformer criteriaTransformer) {
        this.commonUblFactory = commonUblFactory;
        this.contractingPartyTransformer = contractingPartyTransformer;
        this.criteriaTransformer = criteriaTransformer;
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
        addContractFolderIdInformation(espdRequestType);
        addContractingPartyInformation(espdDocument, espdRequestType);
        addProcurementProjectLots(espdRequestType);
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

    private void addContractFolderIdInformation(ESPDRequestType espdRequestType) {
        ContractFolderIDType contractFolderIDType = new ContractFolderIDType();
        contractFolderIDType.setValue("SMART 2015/0065");
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

    private void addCriteria(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        espdRequestType.getCriterion().addAll(criteriaTransformer.apply(espdDocument));
    }

}
