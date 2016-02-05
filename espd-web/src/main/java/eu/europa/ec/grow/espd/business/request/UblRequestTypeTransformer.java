package eu.europa.ec.grow.espd.business.request;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.CommonUblFactory;
import eu.europa.ec.grow.espd.business.common.UblContractingPartyTypeTransformer;
import eu.europa.ec.grow.espd.business.common.UblCriteriaTemplate;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractingPartyType;
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
        addProcurementProjectLots(espdDocument, espdRequestType);
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
        espdRequestType.setID(commonUblFactory.buildDocumentIdentifierType());
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
        espdRequestType.setContractFolderID(commonUblFactory.buildContractFolderType(espdDocument.getFileRefByCA()));
    }

    private void addContractingPartyInformation(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        ContractingPartyType contractingPartyType = contractingPartyTransformer.apply(espdDocument.getAuthority());
        espdRequestType.setContractingParty(contractingPartyType);
    }

    private void addProcurementProjectLots(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        espdRequestType.getProcurementProjectLot()
                .add(commonUblFactory.buildProcurementProjectLot(espdDocument.getLotConcerned()));
    }

    private void addAdditionalDocumentReference(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        espdRequestType.getAdditionalDocumentReference()
                .add(commonUblFactory.buildProcurementProcedureType(espdDocument));
    }

    private void addCriteria(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        espdRequestType.getCriterion().addAll(criteriaTransformer.apply(espdDocument));
    }

}