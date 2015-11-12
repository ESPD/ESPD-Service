package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ProcurementProjectLotType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CustomizationIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.UBLVersionIDType;
import org.springframework.stereotype.Component;

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

    private void addProcurementProjectLots(final ESPDRequestType espdRequestType) {
        ProcurementProjectLotType procurementProjectLotType = new ProcurementProjectLotType();
        IDType idType = new IDType();
        idType.setValue("0");
        procurementProjectLotType.setID(idType);
        espdRequestType.getProcurementProjectLot().add(procurementProjectLotType);
    }
}
