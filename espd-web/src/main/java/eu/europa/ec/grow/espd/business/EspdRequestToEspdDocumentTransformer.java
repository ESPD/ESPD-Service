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
        markExclusionBreachOfObligations(espdDocument, ublCriteria);
        markExclusionPurelyNational(espdDocument, ublCriteria);
    }

    private void markExclusionSelectedCriminalConvictions(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.GROUNDS_CRIMINAL_CONVICTIONS, ublCriteria)) {
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
        if (isCriterionSelected(ExclusionCriterion.PAYMENT_OF_TAXES, ublCriteria)) {
            espdDocument.setPaymentSocsec(Taxes.buildWithExists(true));
        } else {
            espdDocument.setPaymentSocsec(Taxes.buildWithExists(false));
        }
    }

    private void markExclusionBreachOfObligations(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.BREACHING_OF_OBLIGATIONS, ublCriteria)) {
            espdDocument.setBreachingObligations(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setBreachingObligations(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.BANKRUPTCY_INSOLVENCY, ublCriteria)) {
            espdDocument.setBankruptSubject(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setBankruptSubject(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT, ublCriteria)) {
            espdDocument.setGuiltyGrave(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setGuiltyGrave(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.AGREEMENTS_WITH_OTHER_EO, ublCriteria)) {
            espdDocument.setAgreementsEo(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setAgreementsEo(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.CONFLICT_OF_INTEREST, ublCriteria)) {
            espdDocument.setConflictInterest(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setConflictInterest(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.INVOLVEMENT_PROCUREMENT_PROCEDURE, ublCriteria)) {
            espdDocument.setInvolvementPreparation(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setInvolvementPreparation(BreachOfObligations.buildWithExists(false));
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
            espdDocument.setPurelyNationalGrounds(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setPurelyNationalGrounds(BreachOfObligations.buildWithExists(false));
        }
    }

    private boolean isCriterionSelected(ExclusionCriterion criterion, List<CriterionType> ublCriteria) {
        for (CriterionType ubl : ublCriteria) {
            if (ubl.getCriterionID() != null && criterion.getUuid().equals(ubl.getCriterionID().getValue())) {
                return true;
            }
        }
        return false;
    }

}
