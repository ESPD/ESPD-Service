package eu.europa.ec.grow.espd.business.response;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.CommonUblFactory;
import eu.europa.ec.grow.espd.business.common.UblContractingPartyTypeTransformer;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractingPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ProcurementProjectLotType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ContractFolderIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Transforms a {@link EspdDocument} into a {@link ESPDResponseType}.
 * Created by ratoico on 11/26/15.
 */
@Component
public class UblResponseTypeTransformer implements Function<EspdDocument, ESPDResponseType> {

    private final CommonUblFactory commonUblFactory;
    private final UblContractingPartyTypeTransformer contractingPartyTransformer;
    private final UblResponseCriteriaTransformer criteriaTransformer;

    @Autowired
    UblResponseTypeTransformer(CommonUblFactory commonUblFactory,
            UblContractingPartyTypeTransformer contractingPartyTransformer) {
        this.commonUblFactory = commonUblFactory;
        this.contractingPartyTransformer = contractingPartyTransformer;
        this.criteriaTransformer = new UblResponseCriteriaTransformer();
    }

    @Override
    public ESPDResponseType apply(EspdDocument input) {
        ESPDResponseType responseType = new ESPDResponseType();

        addUBLVersionInformation(responseType);
        addCustomizationInformation(responseType);
        addIdInformation(responseType);
        addCopyIndicatorInformation(responseType);
        addVersionIdInformation(responseType);
        addIssueDateAndTimeInformation(responseType);
        addContractFolderIdInformation(responseType);
        addContractingPartyInformation(input, responseType);
        addProcurementProjectLots(responseType);
        addCriteria(input, responseType);
        
        return responseType;
    }

    private void addUBLVersionInformation(ESPDResponseType responseType) {
        responseType.setUBLVersionID(commonUblFactory.buildUblVersionIDType());
    }

    private void addCustomizationInformation(ESPDResponseType responseType) {
        responseType
                .setCustomizationID(commonUblFactory.buildCustomizationIDType(CommonUblFactory.EspdType.ESPD_RESPONSE));
    }

    private void addIdInformation(ESPDResponseType responseType) {
        responseType.setID(commonUblFactory.buildIdType());
    }

    private void addCopyIndicatorInformation(ESPDResponseType responseType) {
        responseType.setCopyIndicator(commonUblFactory.buildCopyIndicatorType(false));
    }

    private void addVersionIdInformation(ESPDResponseType responseType) {
        responseType.setVersionID(commonUblFactory.buildVersionIDType());
    }

    private void addIssueDateAndTimeInformation(ESPDResponseType responseType) {
        Date now = new Date();
        responseType.setIssueTime(commonUblFactory.buildIssueTimeType(now));
        responseType.setIssueDate(commonUblFactory.buildIssueDateType(now));
    }

    private void addContractFolderIdInformation(ESPDResponseType responseType) {
        ContractFolderIDType contractFolderIDType = new ContractFolderIDType();
        // TODO update this
        contractFolderIDType.setValue("SMART 2015/0065");
        contractFolderIDType.setSchemeAgencyID("TeD");
        responseType.setContractFolderID(contractFolderIDType);
    }

    private void addContractingPartyInformation(EspdDocument espdDocument, ESPDResponseType responseType) {
        ContractingPartyType contractingPartyType = contractingPartyTransformer.apply(espdDocument.getAuthority());
        responseType.setContractingParty(contractingPartyType);
    }

    private void addProcurementProjectLots(ESPDResponseType responseType) {
        ProcurementProjectLotType procurementProjectLotType = new ProcurementProjectLotType();
        IDType idType = new IDType();
        idType.setValue("0");
        procurementProjectLotType.setID(idType);
        responseType.getProcurementProjectLot().add(procurementProjectLotType);
    }

    private void addCriteria(EspdDocument espdDocument, ESPDResponseType responseType) {
        responseType.getCriterion().addAll(criteriaTransformer.apply(espdDocument));
    }


}
