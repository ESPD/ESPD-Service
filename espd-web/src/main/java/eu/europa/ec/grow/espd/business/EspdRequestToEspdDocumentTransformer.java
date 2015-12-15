package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.domain.*;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ratoico on 11/25/15 11:28 AM.
 */
@Component
class EspdRequestToEspdDocumentTransformer implements Function<ESPDRequestType, EspdDocument> {

    private final ToPartyImplTransformer toPartyImplTransformer;

    @Autowired
    EspdRequestToEspdDocumentTransformer(ToPartyImplTransformer toPartyImplTransformer) {
        this.toPartyImplTransformer = toPartyImplTransformer;
    }

    @Override
    public EspdDocument apply(ESPDRequestType input) {
        EspdDocument espdDocument = new EspdDocument();

        addPartyInformation(input, espdDocument);
        addCriteriaInformation(input, espdDocument);

        return espdDocument;
    }

    private void addPartyInformation(ESPDRequestType input, EspdDocument espdDocument) {
        if (input.getContractingParty() == null || input.getContractingParty().getParty() == null) {
            return;
        }

        PartyImpl authority = toPartyImplTransformer.apply(input.getContractingParty().getParty());
        espdDocument.setAuthority(authority);
    }

    private void addCriteriaInformation(ESPDRequestType input, EspdDocument espdDocument) {
        if (CollectionUtils.isEmpty(input.getCriterion())) {
            return;
        }

        markSelectedExclusionCriteria(espdDocument, input.getCriterion());

    }

    private void markSelectedExclusionCriteria(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        // beware of the if-then-else statements
        markExclusionSelectedCriminalConvictions(espdDocument, ublCriteria);
        markExclusionSelectedTaxes(espdDocument, ublCriteria);
        markExclusionEnvironmental(espdDocument, ublCriteria);
        markExclusionInsolvency(espdDocument, ublCriteria);
        markExclusionMisconduct(espdDocument, ublCriteria);
        markExclusionConflictOfInterest(espdDocument, ublCriteria);
        markExclusionPurelyNational(espdDocument, ublCriteria);
    }

    private void markExclusionSelectedCriminalConvictions(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.PARTICIPATION_CRIMINAL_ORGANISATION, ublCriteria)) {
            espdDocument.setCriminalConvictions(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setCriminalConvictions(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.CORRUPTION, ublCriteria)) {
            espdDocument.setCorruption(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setCorruption(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.FRAUD, ublCriteria)) {
            espdDocument.setFraud(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setFraud(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.TERRORIST_OFFENCES, ublCriteria)) {
            espdDocument.setTerroristOffences(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setTerroristOffences(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.MONEY_LAUNDERING, ublCriteria)) {
            espdDocument.setMoneyLaundering(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setMoneyLaundering(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.CHILD_LABOUR, ublCriteria)) {
            espdDocument.setChildLabour(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setChildLabour(CriminalConvictions.buildWithExists(false));
        }
    }

    private void markExclusionSelectedTaxes(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.PAYMENT_OF_TAXES, ublCriteria)) {
            espdDocument.setPaymentTaxes(Taxes.buildWithExists(true));
        } else {
            espdDocument.setPaymentTaxes(Taxes.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY, ublCriteria)) {
            espdDocument.setPaymentSocialSecurity(Taxes.buildWithExists(true));
        } else {
            espdDocument.setPaymentSocialSecurity(Taxes.buildWithExists(false));
        }
    }

    private void markExclusionEnvironmental(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL, ublCriteria)) {
            espdDocument.setBreachingObligations(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setBreachingObligations(BreachOfObligations.buildWithExists(false));
        }
    }

    private void markExclusionInsolvency(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.BANKRUPTCY, ublCriteria)) {
            espdDocument.setBankruptcy(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setBankruptcy(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.INSOLVENCY, ublCriteria)) {
            espdDocument.setInsolvency(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setInsolvency(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.ANALOGOUS_SITUATION, ublCriteria)) {
            espdDocument.setAnalogousSituation(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setAnalogousSituation(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.ASSETS_ADMINISTERED_BY_LIQUIDATOR, ublCriteria)) {
            espdDocument.setAssetsAdministeredByLiquidator(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setAssetsAdministeredByLiquidator(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.BUSINESS_ACTIVITIES_SUSPENDED, ublCriteria)) {
            espdDocument.setBusinessActivitiesSuspended(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setBusinessActivitiesSuspended(BreachOfObligations.buildWithExists(false));
        }
    }

    private void markExclusionMisconduct(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.ARRANGEMENT_WITH_CREDITORS, ublCriteria)) {
            espdDocument.setArrangementWithCreditors(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setArrangementWithCreditors(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT, ublCriteria)) {
            espdDocument.setGuiltyGrave(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setGuiltyGrave(BreachOfObligations.buildWithExists(false));
        }
    }

    private void markExclusionConflictOfInterest(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE, ublCriteria)) {
            espdDocument.setConflictInterest(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setConflictInterest(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE, ublCriteria)) {
            espdDocument.setInvolvementPreparationProcurement(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setInvolvementPreparationProcurement(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.EARLY_TERMINATION, ublCriteria)) {
            espdDocument.setEarlyTermination(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setEarlyTermination(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.GUILTY_OF_MISINTERPRETATION, ublCriteria)) {
            espdDocument.setGuiltyMisinterpretation(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setGuiltyMisinterpretation(BreachOfObligations.buildWithExists(false));
        }
    }

    private void markExclusionPurelyNational(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS, ublCriteria)) {
            espdDocument.setPurelyNationalGrounds(PurelyNationalGrounds.buildWithExists(true));
        } else {
            espdDocument.setPurelyNationalGrounds(PurelyNationalGrounds.buildWithExists(false));
        }
    }

    private boolean isCriterionSelected(ExclusionCriterion criterion, List<CriterionType> ublCriteria) {
        for (CriterionType ubl : ublCriteria) {
            if (ubl.getID() != null && criterion.getUuid().equals(ubl.getID().getValue())) {
                return true;
            }
        }
        return false;
    }

}
