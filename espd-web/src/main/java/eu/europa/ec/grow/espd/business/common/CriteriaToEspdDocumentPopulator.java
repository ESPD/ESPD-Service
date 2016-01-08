package eu.europa.ec.grow.espd.business.common;

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.*;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Given a {@link EspdDocument} and a list of UBL {@link CriterionType}, read the UBL data regarding the
 * criteria and update it on the ESPD document.
 * <p/>
 * Created by ratoico on 1/6/16 at 4:52 PM.
 */
@Component
public class CriteriaToEspdDocumentPopulator {

    private final EspdCriterionFactory criterionFactory = new EspdCriterionFactory();

    /**
     * Update criteria information on the given ESPD document.
     * <p></p>
     * <b>
     * Please be aware that this method mutates the ESPD document!
     * </b>
     *
     * @param espdDocument The given ESPD document to be updated with criteria information
     * @param ublCriteria  UBL criteria from which we read the information
     */
    public void addCriteriaToEspdDocument(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (CollectionUtils.isEmpty(ublCriteria)) {
            return;
        }

        markSelectedExclusionCriteria(espdDocument, ublCriteria);
        markSelectedSelectionCriteria(espdDocument, ublCriteria);
    }

    private void markSelectedExclusionCriteria(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        // beware of the if-then-else statements
        markExclusionSelectedCriminalConvictions(espdDocument, ublCriteria);
        markExclusionSelectedTaxes(espdDocument, ublCriteria);
        markExclusionEnvironmental(espdDocument, ublCriteria);
        markExclusionBankruptcyInsolvency(espdDocument, ublCriteria);
        markExclusionMisconduct(espdDocument, ublCriteria);
        markExclusionConflictOfInterest(espdDocument, ublCriteria);
        markExclusionPurelyNational(espdDocument, ublCriteria);
    }

    private void markExclusionSelectedCriminalConvictions(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        espdDocument.setCriminalConvictions(criterionFactory.<CriminalConvictionsCriterion>buildEspdCriterion(
                ExclusionCriterion.PARTICIPATION_CRIMINAL_ORGANISATION, ublCriteria));
        espdDocument.setCorruption(criterionFactory.<CriminalConvictionsCriterion>buildEspdCriterion(
                ExclusionCriterion.CORRUPTION, ublCriteria));
        espdDocument.setFraud(criterionFactory.<CriminalConvictionsCriterion>buildEspdCriterion(
                ExclusionCriterion.FRAUD, ublCriteria));
        espdDocument.setTerroristOffences(criterionFactory.<CriminalConvictionsCriterion>buildEspdCriterion(
                ExclusionCriterion.TERRORIST_OFFENCES, ublCriteria));
        espdDocument.setMoneyLaundering(criterionFactory.<CriminalConvictionsCriterion>buildEspdCriterion(
                ExclusionCriterion.MONEY_LAUNDERING, ublCriteria));
        espdDocument.setChildLabour(criterionFactory.<CriminalConvictionsCriterion>buildEspdCriterion(
                ExclusionCriterion.CHILD_LABOUR, ublCriteria));
    }

    private void markExclusionSelectedTaxes(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        espdDocument.setPaymentTaxes(
                (TaxesCriterion) criterionFactory
                        .buildEspdCriterion(ExclusionCriterion.PAYMENT_OF_TAXES, ublCriteria));
        espdDocument.setPaymentSocialSecurity(
                (TaxesCriterion) criterionFactory
                        .buildEspdCriterion(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY, ublCriteria));
    }

    private void markExclusionEnvironmental(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        espdDocument.setBreachingObligations((EnvironmentalCriterion) criterionFactory
                .buildEspdCriterion(ExclusionCriterion.BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL, ublCriteria));
    }

    private void markExclusionBankruptcyInsolvency(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        espdDocument.setBankruptcy(
                (BankruptcyCriterion) criterionFactory.buildEspdCriterion(ExclusionCriterion.BANKRUPTCY, ublCriteria));
        espdDocument.setInsolvency(
                (BankruptcyCriterion) criterionFactory.buildEspdCriterion(ExclusionCriterion.INSOLVENCY, ublCriteria));
        espdDocument.setAnalogousSituation(
                (BankruptcyCriterion) criterionFactory
                        .buildEspdCriterion(ExclusionCriterion.ANALOGOUS_SITUATION, ublCriteria));
        espdDocument.setAssetsAdministeredByLiquidator(
                (BankruptcyCriterion) criterionFactory
                        .buildEspdCriterion(ExclusionCriterion.ASSETS_ADMINISTERED_BY_LIQUIDATOR, ublCriteria));
        espdDocument.setBusinessActivitiesSuspended(
                (BankruptcyCriterion) criterionFactory
                        .buildEspdCriterion(ExclusionCriterion.BUSINESS_ACTIVITIES_SUSPENDED, ublCriteria));
        espdDocument.setArrangementWithCreditors(
                (BankruptcyCriterion) criterionFactory
                        .buildEspdCriterion(ExclusionCriterion.ARRANGEMENT_WITH_CREDITORS, ublCriteria));
    }

    private void markExclusionMisconduct(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        espdDocument.setGuiltyGrave(criterionFactory
                .<MisconductCriterion>buildEspdCriterion(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT,
                        ublCriteria));
    }

    private void markExclusionConflictOfInterest(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        espdDocument.setConflictInterest((ConflictInterestCriterion) criterionFactory
                .buildEspdCriterion(ExclusionCriterion.CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE, ublCriteria));
        espdDocument.setInvolvementPreparationProcurement((ConflictInterestCriterion) criterionFactory
                .buildEspdCriterion(ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE, ublCriteria));
        espdDocument.setEarlyTermination((ConflictInterestCriterion) criterionFactory
                .buildEspdCriterion(ExclusionCriterion.EARLY_TERMINATION, ublCriteria));
        espdDocument.setGuiltyMisinterpretation((ConflictInterestCriterion) criterionFactory
                .buildEspdCriterion(ExclusionCriterion.GUILTY_OF_MISINTERPRETATION, ublCriteria));
    }

    private void markExclusionPurelyNational(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        espdDocument.setPurelyNationalGrounds((PurelyNationalGrounds) criterionFactory
                .buildEspdCriterion(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS, ublCriteria));
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
        espdDocument.setEnrolmentProfessionalRegister((SuitabilityCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.ENROLMENT_PROFESSIONAL_REGISTER, ublCriteria));
        espdDocument.setEnrolmentTradeRegister((SuitabilityCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.ENROLMENT_TRADE_REGISTER, ublCriteria));
        espdDocument.setServiceContractsAuthorisation((SuitabilityCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.SERVICE_CONTRACTS_AUTHORISATION, ublCriteria));
        espdDocument.setServiceContractsMembership((SuitabilityCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.SERVICE_CONTRACTS_MEMBERSHIP, ublCriteria));
    }

    private void markSelectionSelectedEconomicFinancial(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (isCriterionSelected(SelectionCriterion.GENERAL_YEARLY_TURNOVER,
                ublCriteria)) {
            espdDocument.setGeneralYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setGeneralYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.AVERAGE_YEARLY_TURNOVER,
                ublCriteria)) {
            espdDocument.setAverageYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setAverageYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SPECIFIC_YEARLY_TURNOVER,
                ublCriteria)) {
            espdDocument.setSpecificYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setSpecificYearlyTurnover(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.SPECIFIC_AVERAGE_TURNOVER,
                ublCriteria)) {
            espdDocument.setSpecificAverageTurnover(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setSpecificAverageTurnover(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.FINANCIAL_RATIO,
                ublCriteria)) {
            espdDocument.setFinancialRatio(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setFinancialRatio(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(SelectionCriterion.PROFESSIONAL_RISK_INSURANCE,
                ublCriteria)) {
            espdDocument.setProfessionalRiskInsurance(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setProfessionalRiskInsurance(EconomicFinancialStandingCriterion.buildWithExists(false));
        }
        if (isCriterionSelected(
                SelectionCriterion.OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS,
                ublCriteria)) {
            espdDocument
                    .setOtherEconomicFinancialRequirements(EconomicFinancialStandingCriterion.buildWithExists(true));
        } else {
            espdDocument.setOtherEconomicFinancialRequirements(
                    EconomicFinancialStandingCriterion.buildWithExists(false));
        }
    }

    private void markSelectionSelectedTechnicalProfessionalAbility(EspdDocument espdDocument,
            List<CriterionType> ublCriteria) {
        espdDocument.setWorkContractsPerformanceOfWorks((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.WORK_CONTRACTS_PERFORMANCE_OF_WORKS, ublCriteria));
        espdDocument.setSupplyContractsPerformanceDeliveries((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.SUPPLY_CONTRACTS_PERFORMANCE_OF_DELIVERIES, ublCriteria));
        espdDocument.setServiceContractsPerformanceServices((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES, ublCriteria));
        espdDocument.setTechniciansTechnicalBodies((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.TECHNICIANS_OR_TECHNICAL_BODIES, ublCriteria));
        espdDocument.setWorkContractsTechnicians((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES, ublCriteria));
        espdDocument.setTechnicalFacilitiesMeasures((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.TECHNICAL_FACILITIES_AND_MEASURES, ublCriteria));
        espdDocument.setStudyResearchFacilities((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.STUDY_AND_RESEARCH_FACILITIES, ublCriteria));
        espdDocument.setSupplyChainManagement((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.SUPPLY_CHAIN_MANAGEMENT, ublCriteria));
        espdDocument.setAllowanceOfChecks((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.ALLOWANCE_OF_CHECKS, ublCriteria));
        espdDocument.setEducationalProfessionalQualifications((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS, ublCriteria));
        espdDocument.setEnvironmentalManagementFeatures((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.ENVIRONMENTAL_MANAGEMENT_FEATURES, ublCriteria));
        espdDocument.setNumberManagerialStaff((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF, ublCriteria));
        espdDocument.setAverageAnnualManpower((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.AVERAGE_ANNUAL_MANPOWER, ublCriteria));
        espdDocument.setToolsPlantTechnicalEquipment((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.TOOLS_PLANT_TECHNICAL_EQUIPMENT, ublCriteria));
        espdDocument.setSubcontractingProportion((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.SUBCONTRACTING_PROPORTION, ublCriteria));
        espdDocument.setSupplyContractsSamplesDescriptionsWithoutCa((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA, ublCriteria));
        espdDocument.setSupplyContractsSamplesDescriptionsWithCa((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA, ublCriteria));
        espdDocument.setSupplyContractsCertificatesQc((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.SUPPLY_CONTRACTS_CERTIFICATES_QC, ublCriteria));
        espdDocument.setCertificateIndependentBodiesAboutQa((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA, ublCriteria));
        espdDocument.setCertificateIndependentBodiesAboutEnvironmental((TechnicalProfessionalCriterion) criterionFactory
                .buildEspdCriterion(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA, ublCriteria));
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
