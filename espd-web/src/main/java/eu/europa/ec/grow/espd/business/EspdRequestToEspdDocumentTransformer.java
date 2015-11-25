package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.domain.*;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import lombok.Builder;
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

    private final ToCriterionFulfillmentTransformer toCriterionFulfillmentTransformer;

    @Autowired
    EspdRequestToEspdDocumentTransformer(ToPartyImplTransformer toPartyImplTransformer) {
        this.toPartyImplTransformer = toPartyImplTransformer;
        toCriterionFulfillmentTransformer = new ToCriterionFulfillmentTransformer();
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

        List<CriterionFulfillment> criteria = Lists.transform(input.getCriterion(), toCriterionFulfillmentTransformer);
        markSelectedExclusionCriteria(espdDocument, criteria);

    }

    private boolean isCriterionSelected(ExclusionCriterion criterion, List<CriterionFulfillment> xmlCriteria) {
        for (CriterionFulfillment cf : xmlCriteria) {
            if (criterion.getUuid().equals(cf.uuid) && cf.fulfillmentIndicator) {
                return true;
            }
        }
        return false;
    }

    private void markSelectedExclusionCriteria(EspdDocument espdDocument, List<CriterionFulfillment> criteria) {
        // beware of the if-then-else statements
        markExclusionSelectedCriminalConvictions(espdDocument, criteria);
        markExclusionSelectedTaxes(espdDocument, criteria);
        markExclusionBreachOfObligations(espdDocument, criteria);
    }

    private void markExclusionSelectedCriminalConvictions(EspdDocument espdDocument,
            List<CriterionFulfillment> criteria) {
        if (isCriterionSelected(ExclusionCriterion.GROUNDS_CRIMINAL_CONVICTIONS, criteria)) {
            espdDocument.setCriminalConvictions(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setCriminalConvictions(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.CORRUPTION, criteria)) {
            espdDocument.setCorruption(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setCorruption(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.FRAUD, criteria)) {
            espdDocument.setFraud(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setFraud(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.TERRORIST_OFFENCES, criteria)) {
            espdDocument.setTerroristOffences(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setTerroristOffences(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.MONEY_LAUNDERING, criteria)) {
            espdDocument.setMoneyLaundering(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setMoneyLaundering(CriminalConvictions.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.CHILD_LABOUR, criteria)) {
            espdDocument.setChildLabour(CriminalConvictions.buildWithExists(true));
        } else {
            espdDocument.setChildLabour(CriminalConvictions.buildWithExists(false));
        }
    }

    private void markExclusionSelectedTaxes(EspdDocument espdDocument, List<CriterionFulfillment> criteria) {
        if (isCriterionSelected(ExclusionCriterion.PAYMENT_OF_TAXES, criteria)) {
            espdDocument.setPaymentTaxes(Taxes.buildWithExists(true));
        } else {
            espdDocument.setPaymentTaxes(Taxes.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.PAYMENT_OF_TAXES, criteria)) {
            espdDocument.setPaymentSocsec(Taxes.buildWithExists(true));
        } else {
            espdDocument.setPaymentSocsec(Taxes.buildWithExists(false));
        }
    }

    private void markExclusionBreachOfObligations(EspdDocument espdDocument, List<CriterionFulfillment> criteria) {
        if (isCriterionSelected(ExclusionCriterion.BREACHING_OF_OBLIGATIONS, criteria)) {
            espdDocument.setBreachingObligations(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setBreachingObligations(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.BANKRUPTCY_INSOLVENCY, criteria)) {
            espdDocument.setBankruptSubject(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setBankruptSubject(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT, criteria)) {
            espdDocument.setGuiltyGrave(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setGuiltyGrave(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.AGREEMENTS_WITH_OTHER_EO, criteria)) {
            espdDocument.setAgreementsEo(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setAgreementsEo(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.CONFLICT_OF_INTEREST, criteria)) {
            espdDocument.setConflictInterest(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setConflictInterest(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.INVOLVEMENT_PROCUREMENT_PROCEDURE, criteria)) {
            espdDocument.setInvolvementPreparation(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setInvolvementPreparation(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.EARLY_TERMINATION, criteria)) {
            espdDocument.setEarlyTermination(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setEarlyTermination(BreachOfObligations.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.GUILTY_OF_MISINTERPRETATION, criteria)) {
            espdDocument.setGuiltyMisinterpretation(BreachOfObligations.buildWithExists(true));
        } else {
            espdDocument.setGuiltyMisinterpretation(BreachOfObligations.buildWithExists(false));
        }
    }

    @Builder
    private static class CriterionFulfillment {

        private final String uuid;

        private final boolean fulfillmentIndicator;
    }

    private static class ToCriterionFulfillmentTransformer implements Function<CriterionType, CriterionFulfillment> {

        @Override
        public CriterionFulfillment apply(CriterionType input) {
            CriterionFulfillment.CriterionFulfillmentBuilder builder = CriterionFulfillment.builder();

            if (input.getCriterionID() != null) {
                builder.uuid(input.getCriterionID().getValue());
            }

            if (CollectionUtils.isNotEmpty(input.getCriterionResponse())) {
                CriterionResponseType responseType = input.getCriterionResponse().get(0);
                if (responseType.getCriterionFulfillmentIndicator() != null) {
                    builder.fulfillmentIndicator(responseType.getCriterionFulfillmentIndicator().isValue());
                }
            }

            return builder.build();
        }
    }
}
