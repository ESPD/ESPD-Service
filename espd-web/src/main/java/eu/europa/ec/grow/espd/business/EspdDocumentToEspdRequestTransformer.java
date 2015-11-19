package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Transforms a {@link EspdDocument} into a {@link ESPDRequestType}.
 * <p/>
 * Created by vigi on 11/11/15:10:58 AM.
 */
@Component
class EspdDocumentToEspdRequestTransformer implements Function<EspdDocument, ESPDRequestType> {

    private final ToContractingPartyTransformer contractingPartyTransformer;
    private final CcvCriterionTransformer ccvCriterionTransformer;

    @Autowired
    EspdDocumentToEspdRequestTransformer(ToContractingPartyTransformer contractingPartyTransformer,
            final CcvCriterionTransformer ccvCriterionTransformer) {
        this.contractingPartyTransformer = contractingPartyTransformer;
        this.ccvCriterionTransformer = ccvCriterionTransformer;
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
        addCriterionTypes(espdRequestType);

        return espdRequestType;
    }

    private void addUBLVersionInformation(ESPDRequestType espdRequestType) {
        UBLVersionIDType versionIDType = new UBLVersionIDType();
        versionIDType.setValue("2.1");
        versionIDType.setSchemeAgencyID("OASIS-UBL-TC");
        espdRequestType.setUBLVersionID(versionIDType);
    }

    private void addCustomizationInformation(ESPDRequestType espdRequestType) {
        CustomizationIDType customizationIDType = new CustomizationIDType();
        customizationIDType.setValue("urn:www.cenbii.eu:transaction:biitrns070:ver3.0");
        customizationIDType.setSchemeAgencyID("BII");
        customizationIDType.setSchemeVersionID("3.0");
        customizationIDType.setSchemeName("CustomizationID");
        espdRequestType.setCustomizationID(customizationIDType);
    }

    private void addIdInformation(ESPDRequestType espdRequestType) {
        IDType idType = new IDType();
        idType.setValue(UUID.randomUUID().toString());
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setSchemeAgencyName(Agency.EU_COM_GROW.getLongName());
        idType.setSchemeVersionID("1.1");
        idType.setSchemeID("ISO/IEC 9834-8:2008 - 4UUID");
        espdRequestType.setID(idType);
    }

    private void addCopyIndicatorInformation(ESPDRequestType espdRequestType) {
        CopyIndicatorType copyIndicatorType = new CopyIndicatorType();
        copyIndicatorType.setValue(false);
        espdRequestType.setCopyIndicator(copyIndicatorType);
    }

    private void addVersionIdInformation(ESPDRequestType espdRequestType) {
        VersionIDType versionIDType = new VersionIDType();
        versionIDType.setValue("1");
        versionIDType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        espdRequestType.setVersionID(versionIDType);
    }

    private void addIssueDateAndTimeInformation(ESPDRequestType espdRequestType) {
        Date now = new Date();
        IssueDateType issueDateType = new IssueDateType();
        issueDateType.setValue(new LocalDate(now));
        IssueTimeType issueTimeType = new IssueTimeType();
        issueTimeType.setValue(new LocalTime(now));
        espdRequestType.setIssueTime(issueTimeType);
        espdRequestType.setIssueDate(issueDateType);
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

    private void addCriterionTypes(ESPDRequestType espdRequestType) {
        List<CriterionType> criterionTypes = Lists
                .transform(Arrays.asList(ExclusionCriterion.values()), ccvCriterionTransformer);
        espdRequestType.getCriterion().addAll(criterionTypes);
    }
}
