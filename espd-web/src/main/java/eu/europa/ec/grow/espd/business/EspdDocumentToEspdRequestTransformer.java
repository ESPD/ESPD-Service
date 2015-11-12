package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ProcurementProjectLotType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Transforms a {@link EspdDocument} into a {@link ESPDRequestType}.
 *
 * Created by vigi on 11/11/15:10:58 AM.
 */
@Component
class EspdDocumentToEspdRequestTransformer implements Function<EspdDocument, ESPDRequestType> {

    @Override
    public ESPDRequestType apply(final EspdDocument espdDocument) {
        ESPDRequestType espdRequestType = new ESPDRequestType();
        addUBLVersionInformation(espdRequestType);
        addCustomizationInformation(espdRequestType);
        addIdInformation(espdRequestType);
        addCopyIndicatorInformation(espdRequestType);
        addUuidInformation(espdRequestType);
        addVersionIdInformation(espdRequestType);
        addIssueDateAndTimeInformation(espdRequestType);
        addContractFolderIdInformation(espdRequestType);
        addProcurementProjectLots(espdRequestType);
        return espdRequestType;
    }

    private void addUBLVersionInformation(final ESPDRequestType espdRequestType) {
        UBLVersionIDType versionIDType = new UBLVersionIDType();
        versionIDType.setValue("2.1");
        versionIDType.setSchemeAgencyID("OASIS-UBL-TC");
        espdRequestType.setUBLVersionID(versionIDType);
    }

    private void addCustomizationInformation(final ESPDRequestType espdRequestType) {
        CustomizationIDType customizationIDType = new CustomizationIDType();
        customizationIDType.setValue("urn:www.cenbii.eu:transaction:biitrns070:ver3.0");
        customizationIDType.setSchemeAgencyID("BII");
        customizationIDType.setSchemeVersionID("3.0");
        customizationIDType.setSchemeName("CustomizationID");
        espdRequestType.setCustomizationID(customizationIDType);
    }

    private void addIdInformation(final ESPDRequestType espdRequestType) {
        IDType idType = new IDType();
        // TODO see how to calculate the id
        idType.setValue("ESPDREQ-1-20151010");
        idType.setSchemeAgencyID("COM-DG-CNNECT");
        idType.setSchemeAgencyName("European Commission, Directorate-General for Communications Networks, Content and Technology");
        espdRequestType.setID(idType);
    }

    private void addCopyIndicatorInformation(final ESPDRequestType espdRequestType) {
        CopyIndicatorType copyIndicatorType = new CopyIndicatorType();
        copyIndicatorType.setValue(false);
        espdRequestType.setCopyIndicator(copyIndicatorType);
    }

    private void addUuidInformation(final ESPDRequestType espdRequestType) {
        UUIDType uuidType = new UUIDType();
        uuidType.setValue("b9d2a2d2-4108-11e5-a151-feff819cdc9f");
        uuidType.setSchemeAgencyID("COM-DG-GROW");
        espdRequestType.setUUID(uuidType);
    }

    private void addVersionIdInformation(final ESPDRequestType espdRequestType) {
        VersionIDType versionIDType = new VersionIDType();
        versionIDType.setValue("1");
        versionIDType.setSchemeAgencyID("COM-DG-GROW");
        espdRequestType.setVersionID(versionIDType);
    }

    private void addIssueDateAndTimeInformation(final ESPDRequestType espdRequestType) {
        Date now = new Date();
        IssueDateType issueDateType = new IssueDateType();
        issueDateType.setValue(new LocalDate(now));
        IssueTimeType issueTimeType = new IssueTimeType();
        issueTimeType.setValue(new LocalTime(now));
        espdRequestType.setIssueTime(issueTimeType);
        espdRequestType.setIssueDate(issueDateType);
    }

    private void addContractFolderIdInformation(final ESPDRequestType espdRequestType) {
        ContractFolderIDType contractFolderIDType = new ContractFolderIDType();
        contractFolderIDType.setValue("SMART 2015/0065");
        contractFolderIDType.setSchemeAgencyID("TeD");
        espdRequestType.setContractFolderID(contractFolderIDType);
    }

    private void addProcurementProjectLots(final ESPDRequestType espdRequestType) {
        ProcurementProjectLotType procurementProjectLotType = new ProcurementProjectLotType();
        IDType idType = new IDType();
        idType.setValue("0");
        procurementProjectLotType.setID(idType);
        espdRequestType.getProcurementProjectLot().add(procurementProjectLotType);
    }
}
