package eu.europa.ec.grow.espd.business.common;

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.*;
import static eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion.*;

/**
 * Creates a list of UBL {@link CriterionType} elements to be populated in a ESPD Request or Response.
 * <p/>
 * Created by ratoico on 12/22/15 at 10:33 AM.
 */
public abstract class UblCriteriaTemplate {

    private final UblCriterionTypeTemplate ublCriterionTypeTransformer;

    protected UblCriteriaTemplate() {
        this.ublCriterionTypeTransformer = buildCriterionTypeTransformerTemplate();
    }

    /**
     * Builds a list of UBL {@link CriterionType} elements.
     *
     * @param espdDocument The input coming from the ESPD application
     *
     * @return The list of UBL criteria
     */
    public List<CriterionType> apply(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(
                ExclusionCriterion.values().length + SelectionCriterion.values().length + 1);
        criterionTypes.addAll(addExclusionCriteria(espdDocument));
        criterionTypes.addAll(addSelectionCriteria(espdDocument));
        criterionTypes.addAll(buildAwardCriteria(espdDocument));
        return Collections.unmodifiableList(criterionTypes);
    }

    private List<CriterionType> addExclusionCriteria(EspdDocument espdDocument) {
        // we need to do it in a hard coded way right now, unfortunately
        // THE ORDER OF CRITERIA IS VERY IMPORTANT AND IT SHOULD BE COVERED BY THE TESTS
        List<CriterionType> criterionTypes = new ArrayList<>(ExclusionCriterion.values().length + 1);
        markExclusionCriminalConvictions(espdDocument, criterionTypes);
        markExclusionPayment(espdDocument, criterionTypes);
        markExclusionLaw(espdDocument, criterionTypes);
        markExclusionBankruptcyInsolvency(espdDocument, criterionTypes);
        markExclusionMisconductDistortion(espdDocument, criterionTypes);
        markExclusionConflictOfInterest(espdDocument, criterionTypes);
        markExclusionNationalGrounds(espdDocument, criterionTypes);
        return criterionTypes;
    }

    private void markExclusionCriminalConvictions(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(PARTICIPATION_CRIMINAL_ORGANISATION, espdDocument.getCriminalConvictions(), criteria);
        addUblCriterion(CORRUPTION, espdDocument.getCorruption(), criteria);
        addUblCriterion(FRAUD, espdDocument.getFraud(), criteria);
        addUblCriterion(TERRORIST_OFFENCES, espdDocument.getTerroristOffences(), criteria);
        addUblCriterion(MONEY_LAUNDERING, espdDocument.getMoneyLaundering(), criteria);
        addUblCriterion(CHILD_LABOUR, espdDocument.getChildLabour(), criteria);
    }

    private void markExclusionPayment(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(PAYMENT_OF_TAXES, espdDocument.getPaymentTaxes(), criteria);
        addUblCriterion(PAYMENT_OF_SOCIAL_SECURITY, espdDocument.getPaymentSocialSecurity(), criteria);
    }

    private void markExclusionLaw(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL, espdDocument.getBreachingObligationsEnvironmental(),
                criteria);
        addUblCriterion(BREACHING_OF_OBLIGATIONS_SOCIAL, espdDocument.getBreachingObligationsSocial(), criteria);
        addUblCriterion(BREACHING_OF_OBLIGATIONS_LABOUR, espdDocument.getBreachingObligationsLabour(), criteria);
    }

    private void markExclusionBankruptcyInsolvency(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(BANKRUPTCY, espdDocument.getBankruptcy(), criteria);
        addUblCriterion(INSOLVENCY, espdDocument.getInsolvency(), criteria);
        addUblCriterion(ARRANGEMENT_WITH_CREDITORS, espdDocument.getArrangementWithCreditors(), criteria);
        addUblCriterion(ANALOGOUS_SITUATION, espdDocument.getAnalogousSituation(), criteria);
        addUblCriterion(ASSETS_ADMINISTERED_BY_LIQUIDATOR, espdDocument.getAssetsAdministeredByLiquidator(), criteria);
        addUblCriterion(BUSINESS_ACTIVITIES_SUSPENDED, espdDocument.getBusinessActivitiesSuspended(), criteria);
    }

    private void markExclusionMisconductDistortion(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(GUILTY_OF_PROFESSIONAL_MISCONDUCT, espdDocument.getGuiltyGrave(), criteria);
        addUblCriterion(AGREEMENTS_WITH_OTHER_EO, espdDocument.getAgreementsWithOtherEO(), criteria);
    }

    private void markExclusionConflictOfInterest(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE, espdDocument.getConflictInterest(), criteria);
        addUblCriterion(DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE, espdDocument.getInvolvementPreparationProcurement(),
                criteria);
        addUblCriterion(EARLY_TERMINATION, espdDocument.getEarlyTermination(), criteria);
        addUblCriterion(GUILTY_OF_MISINTERPRETATION, espdDocument.getGuiltyMisinterpretation(), criteria);
    }

    private void markExclusionNationalGrounds(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(NATIONAL_EXCLUSION_GROUNDS, espdDocument.getPurelyNationalGrounds(), criteria);
    }

    private List<CriterionType> addSelectionCriteria(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(SelectionCriterion.values().length + 1);
        addUblCriterion(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED,
                espdDocument.getSelectionSatisfiesAll(), criterionTypes);
        markSelectionSuitability(espdDocument, criterionTypes);
        markSelectionEconomicFinancialStanding(espdDocument, criterionTypes);
        markSelectionTechnicalProfessionalAbility(espdDocument, criterionTypes);
        return criterionTypes;
    }

    private void markSelectionSuitability(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(ENROLMENT_PROFESSIONAL_REGISTER, espdDocument.getEnrolmentProfessionalRegister(),
                criteria);
        addUblCriterion(ENROLMENT_TRADE_REGISTER, espdDocument.getEnrolmentTradeRegister(), criteria);
        addUblCriterion(SERVICE_CONTRACTS_AUTHORISATION, espdDocument.getServiceContractsAuthorisation(),
                criteria);
        addUblCriterion(SERVICE_CONTRACTS_MEMBERSHIP, espdDocument.getServiceContractsMembership(), criteria);
    }

    private void markSelectionEconomicFinancialStanding(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(GENERAL_YEARLY_TURNOVER, espdDocument.getGeneralYearlyTurnover(), criteria);
        addUblCriterion(AVERAGE_YEARLY_TURNOVER, espdDocument.getAverageYearlyTurnover(), criteria);
        addUblCriterion(SPECIFIC_YEARLY_TURNOVER, espdDocument.getSpecificYearlyTurnover(), criteria);
        addUblCriterion(SPECIFIC_AVERAGE_TURNOVER, espdDocument.getSpecificAverageTurnover(), criteria);
        addUblCriterion(FINANCIAL_RATIO, espdDocument.getFinancialRatio(), criteria);
        addUblCriterion(PROFESSIONAL_RISK_INSURANCE, espdDocument.getProfessionalRiskInsurance(), criteria);
        addUblCriterion(OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS, espdDocument.getOtherEconomicFinancialRequirements(),
                criteria);
    }

    private void markSelectionTechnicalProfessionalAbility(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterion(WORK_CONTRACTS_PERFORMANCE_OF_WORKS, espdDocument.getWorkContractsPerformanceOfWorks(),
                criteria);
        addUblCriterion(SUPPLY_CONTRACTS_PERFORMANCE_OF_DELIVERIES,
                espdDocument.getSupplyContractsPerformanceDeliveries(), criteria);
        addUblCriterion(SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES,
                espdDocument.getServiceContractsPerformanceServices(), criteria);
        addUblCriterion(TECHNICIANS_OR_TECHNICAL_BODIES, espdDocument.getTechniciansTechnicalBodies(), criteria);
        addUblCriterion(WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES, espdDocument.getWorkContractsTechnicians(),
                criteria);
        addUblCriterion(TECHNICAL_FACILITIES_AND_MEASURES, espdDocument.getTechnicalFacilitiesMeasures(), criteria);
        addUblCriterion(STUDY_AND_RESEARCH_FACILITIES, espdDocument.getStudyResearchFacilities(), criteria);
        addUblCriterion(SUPPLY_CHAIN_MANAGEMENT, espdDocument.getSupplyChainManagement(), criteria);
        addUblCriterion(ALLOWANCE_OF_CHECKS, espdDocument.getAllowanceOfChecks(), criteria);
        addUblCriterion(EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS,
                espdDocument.getEducationalProfessionalQualifications(), criteria);
        addUblCriterion(ENVIRONMENTAL_MANAGEMENT_FEATURES, espdDocument.getEnvironmentalManagementFeatures(), criteria);
        addUblCriterion(NUMBER_OF_MANAGERIAL_STAFF, espdDocument.getNumberManagerialStaff(), criteria);
        addUblCriterion(AVERAGE_ANNUAL_MANPOWER, espdDocument.getAverageAnnualManpower(), criteria);
        addUblCriterion(TOOLS_PLANT_TECHNICAL_EQUIPMENT, espdDocument.getToolsPlantTechnicalEquipment(), criteria);
        addUblCriterion(SUBCONTRACTING_PROPORTION, espdDocument.getSubcontractingProportion(), criteria);
        addUblCriterion(SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA,
                espdDocument.getSupplyContractsSamplesDescriptionsWithoutCa(), criteria);
        addUblCriterion(SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA,
                espdDocument.getSupplyContractsSamplesDescriptionsWithCa(), criteria);
        addUblCriterion(SUPPLY_CONTRACTS_CERTIFICATES_QC, espdDocument.getSupplyContractsCertificatesQc(), criteria);
        addUblCriterion(CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA, espdDocument.getCertificateIndependentBodiesAboutQa(),
                criteria);
        addUblCriterion(CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL,
                espdDocument.getCertificateIndependentBodiesAboutEnvironmental(), criteria);
    }

    protected final void addUblCriterion(CcvCriterion ccvCriterion, Criterion espdCriterion, List<CriterionType> ublCriteria) {
        ublCriteria.add(ublCriterionTypeTransformer.buildCriterionType(ccvCriterion, espdCriterion));
    }

    /**
     * Construct a class that can build UBL {@link CriterionType}.
     *
     * @return An instance of a class that can build {@link CriterionType}
     */
    protected abstract UblCriterionTypeTemplate buildCriterionTypeTransformerTemplate();

    /**
     * Build the list of economic operator award criteria.
     *
     * @param espdDocument The model containing information coming from the ESPD UI
     *
     * @return The list of economic operator criteria
     */
    protected abstract List<CriterionType> buildAwardCriteria(EspdDocument espdDocument);
}
