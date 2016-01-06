package eu.europa.ec.grow.espd.business.request;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.PartyImplTransformer;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.*;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
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
        markSelectedSelectionCriteria(espdDocument, input.getCriterion());

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
            espdDocument.setCriminalConvictions(CriminalConvictionsCriterion.buildWithExists(true));
        } else {
            espdDocument.setCriminalConvictions(CriminalConvictionsCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.CORRUPTION, ublCriteria)) {
            espdDocument.setCorruption(CriminalConvictionsCriterion.buildWithExists(true));
        } else {
            espdDocument.setCorruption(CriminalConvictionsCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.FRAUD, ublCriteria)) {
            espdDocument.setFraud(CriminalConvictionsCriterion.buildWithExists(true));
        } else {
            espdDocument.setFraud(CriminalConvictionsCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.TERRORIST_OFFENCES, ublCriteria)) {
            espdDocument.setTerroristOffences(CriminalConvictionsCriterion.buildWithExists(true));
        } else {
            espdDocument.setTerroristOffences(CriminalConvictionsCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.MONEY_LAUNDERING, ublCriteria)) {
            espdDocument.setMoneyLaundering(CriminalConvictionsCriterion.buildWithExists(true));
        } else {
            espdDocument.setMoneyLaundering(CriminalConvictionsCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(ExclusionCriterion.CHILD_LABOUR, ublCriteria)) {
            espdDocument.setChildLabour(CriminalConvictionsCriterion.buildWithExists(true));
        } else {
            espdDocument.setChildLabour(CriminalConvictionsCriterion.buildWithExists(false));
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

    private void markSelectedSelectionCriteria(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        markSelectionSelectedAllCriteriaSatisfied(espdDocument, ublCriteria);
        markSelectionSelectedSuitability(espdDocument, ublCriteria);
        markSelectionSelectedEconomicFinancial(espdDocument, ublCriteria);
        markSelectionSelectedTechnicalProfessionalAbility(espdDocument, ublCriteria);
    }

    private void markSelectionSelectedAllCriteriaSatisfied(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        boolean selected = isCriterionSelected(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED, ublCriteria);
        espdDocument.setSelectionSatisfiesAll(SatisfiesAllCriterion.buildWithExists(selected));
    }

    private void markSelectionSelectedSuitability(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(SelectionCriterion.ENROLMENT_PROFESSIONAL_REGISTER, ublCriteria)) {
            espdDocument.setEnrolmentProfessionalRegister(SuitabilityCriterion.buildWithExists(true));
        } else {
            espdDocument.setEnrolmentProfessionalRegister(SuitabilityCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.ENROLMENT_TRADE_REGISTER, ublCriteria)) {
            espdDocument.setEnrolmentTradeRegister(SuitabilityCriterion.buildWithExists(true));
        } else {
            espdDocument.setEnrolmentTradeRegister(SuitabilityCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SERVICE_CONTRACTS_AUTHORISATION, ublCriteria)) {
            espdDocument.setServiceContractsAuthorisation(SuitabilityCriterion.buildWithExists(true));
        } else {
            espdDocument.setServiceContractsAuthorisation(SuitabilityCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SERVICE_CONTRACTS_MEMBERSHIP, ublCriteria)) {
            espdDocument.setServiceContractsMembership(SuitabilityCriterion.buildWithExists(true));
        } else {
            espdDocument.setServiceContractsMembership(SuitabilityCriterion.buildWithExists(false));
        }
    }

    private void markSelectionSelectedEconomicFinancial(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(SelectionCriterion.GENERAL_YEARLY_TURNOVER, ublCriteria)) {
            espdDocument.setGeneralYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setGeneralYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.AVERAGE_YEARLY_TURNOVER, ublCriteria)) {
            espdDocument.setAverageYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setAverageYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SPECIFIC_YEARLY_TURNOVER, ublCriteria)) {
            espdDocument.setSpecificYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setSpecificYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SPECIFIC_AVERAGE_TURNOVER, ublCriteria)) {
            espdDocument.setSpecificAverageTurnover(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setSpecificAverageTurnover(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.FINANCIAL_RATIO, ublCriteria)) {
            espdDocument.setFinancialRatio(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setFinancialRatio(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.PROFESSIONAL_RISK_INSURANCE, ublCriteria)) {
            espdDocument.setProfessionalRiskInsurance(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setProfessionalRiskInsurance(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS, ublCriteria)) {
            espdDocument
                    .setOtherEconomicFinancialRequirements(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setOtherEconomicFinancialRequirements(
                    EconomicFinancialStandingCriterion.buildWithExists(false));
        }
    }

    private void markSelectionSelectedTechnicalProfessionalAbility(EspdDocument espdDocument,
            List<CriterionType> ublCriteria) {
        if (isCriterionSelected(SelectionCriterion.WORK_CONTRACTS_PERFORMANCE_OF_WORKS, ublCriteria)) {
            espdDocument.setWorkContractsPerformanceOfWorks(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setWorkContractsPerformanceOfWorks(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SUPPLY_CONTRACTS_PERFORMANCE_OF_DELIVERIES, ublCriteria)) {
            espdDocument.setSupplyContractsPerformanceDeliveries(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setSupplyContractsPerformanceDeliveries(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES, ublCriteria)) {
            espdDocument.setServiceContractsPerformanceServices(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setServiceContractsPerformanceServices(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.TECHNICIANS_OR_TECHNICAL_BODIES, ublCriteria)) {
            espdDocument.setTechniciansTechnicalBodies(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setTechniciansTechnicalBodies(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES, ublCriteria)) {
            espdDocument.setWorkContractsTechnicians(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setWorkContractsTechnicians(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.TECHNICAL_FACILITIES_AND_MEASURES, ublCriteria)) {
            espdDocument.setTechnicalFacilitiesMeasures(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setTechnicalFacilitiesMeasures(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.STUDY_AND_RESEARCH_FACILITIES, ublCriteria)) {
            espdDocument.setStudyResearchFacilities(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setStudyResearchFacilities(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SUPPLY_CHAIN_MANAGEMENT, ublCriteria)) {
            espdDocument.setSupplyChainManagement(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setSupplyChainManagement(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.ALLOWANCE_OF_CHECKS, ublCriteria)) {
            espdDocument.setAllowanceOfChecks(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setAllowanceOfChecks(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS, ublCriteria)) {
            espdDocument.setEducationalProfessionalQualifications(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument
                    .setEducationalProfessionalQualifications(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.ENVIRONMENTAL_MANAGEMENT_FEATURES, ublCriteria)) {
            espdDocument.setEnvironmentalManagementFeatures(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setEnvironmentalManagementFeatures(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF, ublCriteria)) {
            espdDocument.setNumberManagerialStaff(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setNumberManagerialStaff(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.AVERAGE_ANNUAL_MANPOWER, ublCriteria)) {
            espdDocument.setAverageAnnualManpower(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setAverageAnnualManpower(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.TOOLS_PLANT_TECHNICAL_EQUIPMENT, ublCriteria)) {
            espdDocument.setToolsPlantTechnicalEquipment(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setToolsPlantTechnicalEquipment(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SUBCONTRACTING_PROPORTION, ublCriteria)) {
            espdDocument.setSubcontractingProportion(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setSubcontractingProportion(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA, ublCriteria)) {
            espdDocument.setSupplyContractsSamplesDescriptionsWithoutCa(
                    TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setSupplyContractsSamplesDescriptionsWithoutCa(
                    TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA, ublCriteria)) {
            espdDocument.setSupplyContractsSamplesDescriptionsWithCa(
                    TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setSupplyContractsSamplesDescriptionsWithCa(
                    TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SUPPLY_CONTRACTS_CERTIFICATES_QC, ublCriteria)) {
            espdDocument.setSupplyContractsCertificatesQc(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setSupplyContractsCertificatesQc(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA, ublCriteria)) {
            espdDocument.setCertificateIndependentBodiesAboutQa(TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setCertificateIndependentBodiesAboutQa(TechnicalProfessionalCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL, ublCriteria)) {
            espdDocument.setCertificateIndependentBodiesAboutEnvironmental(
                    TechnicalProfessionalCriterion.buildWithExists(true));
        } else {
            espdDocument.setCertificateIndependentBodiesAboutEnvironmental(
                    TechnicalProfessionalCriterion.buildWithExists(false));
        }
    }

    private boolean isCriterionSelected(CcvCriterion criterion, List<CriterionType> ublCriteria) {
        for (CriterionType ubl : ublCriteria) {
            if (ubl.getID() != null && criterion.getUuid().equals(ubl.getID().getValue())) {
                return true;
            }
        }
        return false;
    }

}
