package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import isa.names.specification.ubl.schema.xsd.ccv_commonbasiccomponents_1.IndicatorType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractingPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ProcurementProjectLotType;
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
            CcvCriterionTransformer ccvCriterionTransformer) {
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
        addExclusionCriteria(espdDocument, espdRequestType);
        addSelectionCriteria(espdDocument, espdRequestType);

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

    private void addExclusionCriteria(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        List<CriterionType> criterionTypes = Lists
                .transform(Arrays.asList(ExclusionCriterion.values()), ccvCriterionTransformer);
        espdRequestType.getCriterion().addAll(criterionTypes);

        markSelectedExclusionCriteria(espdDocument, espdRequestType);
    }

    private void markSelectedExclusionCriteria(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        // we need to do it in a hard coded way right now, unfortunately
        if (isCriterionSelectedInEspd(espdDocument.getCriminalConvictions())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.GROUNDS_CRIMINAL_CONVICTIONS, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getCorruption())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.CORRUPTION, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getFraud())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.FRAUD, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getTerroristOffences())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.TERRORIST_OFFENCES, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getMoneyLaundering())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.MONEY_LAUNDERING, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getChildLabour())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.CHILD_LABOUR, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getPaymentTaxes())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.PAYMENT_OF_TAXES, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getPaymentSocsec())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getBreachingObligations())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.BREACHING_OF_OBLIGATIONS, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getBankruptSubject())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.BANKRUPTCY_INSOLVENCY, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getGuiltyGrave())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getAgreementsEo())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.AGREEMENTS_WITH_OTHER_EO, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getConflictInterest())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.CONFLICT_OF_INTEREST, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getInvolvementPreparation())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.INVOLVEMENT_PROCUREMENT_PROCEDURE, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getEarlyTermination())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.EARLY_TERMINATION, espdRequestType);
        }
        if (isCriterionSelectedInEspd(espdDocument.getGuiltyMisinterpretation())) {
            tryToMarkCriterionAsSelected(ExclusionCriterion.GUILTY_OF_MISINTERPRETATION, espdRequestType);
        }
    }

    private boolean isCriterionSelectedInEspd(Criterion criterion) {
        return criterion != null && criterion.getExists();
    }

    private void tryToMarkCriterionAsSelected(CcvCriterion exclusionCriterion, ESPDRequestType espdRequestType) {
        for (CriterionType criterionType : espdRequestType.getCriterion()) {
            if (criterionMatches(exclusionCriterion, criterionType)) {
                markCriterionAsSelected(criterionType, true);
            }
        }
    }

    private boolean criterionMatches(CcvCriterion exclusionCriterion, CriterionType criterionType) {
        return criterionType.getCriterionID() != null && exclusionCriterion.getUuid()
                .equals(criterionType.getCriterionID().getValue());
    }

    private void markCriterionAsSelected(CriterionType criterionType, boolean selected) {
        CriterionResponseType responseType = new CriterionResponseType();
        IndicatorType selectionIndicator = new IndicatorType();
        selectionIndicator.setValue(selected);
        responseType.setCriterionFulfillmentIndicator(selectionIndicator);
        criterionType.getCriterionResponse().add(responseType);
    }

    private void addSelectionCriteria(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        if (satisfiesAllCriteria(espdDocument)) {
            espdRequestType.getCriterion()
                    .add(ccvCriterionTransformer.apply(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED));
            tryToMarkCriterionAsSelected(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED, espdRequestType);
        }
    }

    private boolean satisfiesAllCriteria(final EspdDocument espdDocument) {
        return espdDocument.getSelectionSatisfiesAll() != null && espdDocument.getSelectionSatisfiesAll().getExists();
    }
}
