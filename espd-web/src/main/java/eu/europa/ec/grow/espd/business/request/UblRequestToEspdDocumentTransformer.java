package eu.europa.ec.grow.espd.business.request;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.PartyImplTransformer;
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
public class UblRequestToEspdDocumentTransformer implements Function<ESPDRequestType, EspdDocument> {

    private final PartyImplTransformer partyImplTransformer;

    @Autowired
    UblRequestToEspdDocumentTransformer(PartyImplTransformer partyImplTransformer) {
        this.partyImplTransformer = partyImplTransformer;
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

        PartyImpl authority = partyImplTransformer.apply(input.getContractingParty().getParty());
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
            espdDocument.setPaymentTaxes(TaxesCriterion.buildWithExists(true));
        } else {
            espdDocument.setPaymentTaxes(TaxesCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY, ublCriteria)) {
            espdDocument.setPaymentSocialSecurity(TaxesCriterion.buildWithExists(true));
        } else {
            espdDocument.setPaymentSocialSecurity(TaxesCriterion.buildWithExists(false));
        }
    }

    private void markExclusionEnvironmental(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL, ublCriteria)) {
            espdDocument.setBreachingObligations(EnvironmentalCriterion.buildWithExists(true));
        } else {
            espdDocument.setBreachingObligations(EnvironmentalCriterion.buildWithExists(false));
        }
    }

    private void markExclusionInsolvency(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.BANKRUPTCY, ublCriteria)) {
            espdDocument.setBankruptcy(BankruptcyCriterion.buildWithExists(true));
        } else {
            espdDocument.setBankruptcy(BankruptcyCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.INSOLVENCY, ublCriteria)) {
            espdDocument.setInsolvency(BankruptcyCriterion.buildWithExists(true));
        } else {
            espdDocument.setInsolvency(BankruptcyCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.ANALOGOUS_SITUATION, ublCriteria)) {
            espdDocument.setAnalogousSituation(BankruptcyCriterion.buildWithExists(true));
        } else {
            espdDocument.setAnalogousSituation(BankruptcyCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.ASSETS_ADMINISTERED_BY_LIQUIDATOR, ublCriteria)) {
            espdDocument.setAssetsAdministeredByLiquidator(BankruptcyCriterion.buildWithExists(true));
        } else {
            espdDocument.setAssetsAdministeredByLiquidator(BankruptcyCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.BUSINESS_ACTIVITIES_SUSPENDED, ublCriteria)) {
            espdDocument.setBusinessActivitiesSuspended(BankruptcyCriterion.buildWithExists(true));
        } else {
            espdDocument.setBusinessActivitiesSuspended(BankruptcyCriterion.buildWithExists(false));
        }
    }

    private void markExclusionMisconduct(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.ARRANGEMENT_WITH_CREDITORS, ublCriteria)) {
            espdDocument.setArrangementWithCreditors(BankruptcyCriterion.buildWithExists(true));
        } else {
            espdDocument.setArrangementWithCreditors(BankruptcyCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT, ublCriteria)) {
            espdDocument.setGuiltyGrave(MisconductCriterion.buildWithExists(true));
        } else {
            espdDocument.setGuiltyGrave(MisconductCriterion.buildWithExists(false));
        }
    }

    private void markExclusionConflictOfInterest(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(ExclusionCriterion.CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE, ublCriteria)) {
            espdDocument.setConflictInterest(ConflictInterestCriterion.buildWithExists(true));
        } else {
            espdDocument.setConflictInterest(ConflictInterestCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE, ublCriteria)) {
            espdDocument.setInvolvementPreparationProcurement(ConflictInterestCriterion.buildWithExists(true));
        } else {
            espdDocument.setInvolvementPreparationProcurement(ConflictInterestCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.EARLY_TERMINATION, ublCriteria)) {
            espdDocument.setEarlyTermination(ConflictInterestCriterion.buildWithExists(true));
        } else {
            espdDocument.setEarlyTermination(ConflictInterestCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.GUILTY_OF_MISINTERPRETATION, ublCriteria)) {
            espdDocument.setGuiltyMisinterpretation(ConflictInterestCriterion.buildWithExists(true));
        } else {
            espdDocument.setGuiltyMisinterpretation(ConflictInterestCriterion.buildWithExists(false));
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
