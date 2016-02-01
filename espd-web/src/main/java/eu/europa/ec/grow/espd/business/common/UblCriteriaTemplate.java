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
        addSelectedUblCriterion(PARTICIPATION_CRIMINAL_ORGANISATION, espdDocument.getCriminalConvictions(), criteria);
        addSelectedUblCriterion(CORRUPTION, espdDocument.getCorruption(), criteria);
        addSelectedUblCriterion(FRAUD, espdDocument.getFraud(), criteria);
        addSelectedUblCriterion(TERRORIST_OFFENCES, espdDocument.getTerroristOffences(), criteria);
        addSelectedUblCriterion(MONEY_LAUNDERING, espdDocument.getMoneyLaundering(), criteria);
        addSelectedUblCriterion(CHILD_LABOUR, espdDocument.getChildLabour(), criteria);
    }

    private void markExclusionPayment(EspdDocument espdDocument, List<CriterionType> criteria) {
        addSelectedUblCriterion(PAYMENT_OF_TAXES, espdDocument.getPaymentTaxes(), criteria);
        addSelectedUblCriterion(PAYMENT_OF_SOCIAL_SECURITY, espdDocument.getPaymentSocialSecurity(), criteria);
    }

    private void markExclusionLaw(EspdDocument espdDocument, List<CriterionType> criteria) {
        addSelectedUblCriterion(BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL,
                espdDocument.getBreachingObligationsEnvironmental(),
                criteria);
        addSelectedUblCriterion(BREACHING_OF_OBLIGATIONS_SOCIAL, espdDocument.getBreachingObligationsSocial(),
                criteria);
        addSelectedUblCriterion(BREACHING_OF_OBLIGATIONS_LABOUR, espdDocument.getBreachingObligationsLabour(),
                criteria);
    }

    private void markExclusionBankruptcyInsolvency(EspdDocument espdDocument, List<CriterionType> criteria) {
        addSelectedUblCriterion(BANKRUPTCY, espdDocument.getBankruptcy(), criteria);
        addSelectedUblCriterion(INSOLVENCY, espdDocument.getInsolvency(), criteria);
        addSelectedUblCriterion(ARRANGEMENT_WITH_CREDITORS, espdDocument.getArrangementWithCreditors(), criteria);
        addSelectedUblCriterion(ANALOGOUS_SITUATION, espdDocument.getAnalogousSituation(), criteria);
        addSelectedUblCriterion(ASSETS_ADMINISTERED_BY_LIQUIDATOR, espdDocument.getAssetsAdministeredByLiquidator(),
                criteria);
        addSelectedUblCriterion(BUSINESS_ACTIVITIES_SUSPENDED, espdDocument.getBusinessActivitiesSuspended(), criteria);
    }

    private void markExclusionMisconductDistortion(EspdDocument espdDocument, List<CriterionType> criteria) {
        addSelectedUblCriterion(GUILTY_OF_PROFESSIONAL_MISCONDUCT, espdDocument.getGuiltyGrave(), criteria);
        addSelectedUblCriterion(AGREEMENTS_WITH_OTHER_EO, espdDocument.getAgreementsWithOtherEO(), criteria);
    }

    private void markExclusionConflictOfInterest(EspdDocument espdDocument, List<CriterionType> criteria) {
        addSelectedUblCriterion(CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE, espdDocument.getConflictInterest(),
                criteria);
        addSelectedUblCriterion(DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE,
                espdDocument.getInvolvementPreparationProcurement(),
                criteria);
        addSelectedUblCriterion(EARLY_TERMINATION, espdDocument.getEarlyTermination(), criteria);
        addSelectedUblCriterion(GUILTY_OF_MISINTERPRETATION, espdDocument.getGuiltyMisinterpretation(), criteria);
    }

    private void markExclusionNationalGrounds(EspdDocument espdDocument, List<CriterionType> criteria) {
        addSelectedUblCriterion(NATIONAL_EXCLUSION_GROUNDS, espdDocument.getPurelyNationalGrounds(), criteria);
    }

    private List<CriterionType> addSelectionCriteria(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(SelectionCriterion.values().length + 1);
        addSelectedUblCriterion(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED,
                espdDocument.getSelectionSatisfiesAll(), criterionTypes);
        markSelectionSuitability(espdDocument, criterionTypes);
        markSelectionEconomicFinancialStanding(espdDocument, criterionTypes);
        markSelectionTechnicalProfessionalAbility(espdDocument, criterionTypes);
        return criterionTypes;
    }

    private void markSelectionSuitability(EspdDocument espdDocument, List<CriterionType> criteria) {
        addSelectedUblCriterion(ENROLMENT_PROFESSIONAL_REGISTER, espdDocument.getEnrolmentProfessionalRegister(),
                criteria);
        addSelectedUblCriterion(ENROLMENT_TRADE_REGISTER, espdDocument.getEnrolmentTradeRegister(), criteria);
        addSelectedUblCriterion(SERVICE_CONTRACTS_AUTHORISATION, espdDocument.getServiceContractsAuthorisation(),
                criteria);
        addSelectedUblCriterion(SERVICE_CONTRACTS_MEMBERSHIP, espdDocument.getServiceContractsMembership(), criteria);
    }

    private void markSelectionEconomicFinancialStanding(EspdDocument espdDocument, List<CriterionType> criteria) {
        addSelectedUblCriterion(GENERAL_YEARLY_TURNOVER, espdDocument.getGeneralYearlyTurnover(), criteria);
        addSelectedUblCriterion(AVERAGE_YEARLY_TURNOVER, espdDocument.getAverageYearlyTurnover(), criteria);
        addSelectedUblCriterion(SPECIFIC_YEARLY_TURNOVER, espdDocument.getSpecificYearlyTurnover(), criteria);
        addSelectedUblCriterion(SPECIFIC_AVERAGE_TURNOVER, espdDocument.getSpecificAverageTurnover(), criteria);
        addSelectedUblCriterion(SETUP_ECONOMIC_OPERATOR, espdDocument.getSetupEconomicOperator(), criteria);
        addSelectedUblCriterion(FINANCIAL_RATIO, espdDocument.getFinancialRatio(), criteria);
        addSelectedUblCriterion(PROFESSIONAL_RISK_INSURANCE, espdDocument.getProfessionalRiskInsurance(), criteria);
        addSelectedUblCriterion(OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS,
                espdDocument.getOtherEconomicFinancialRequirements(),
                criteria);
    }

    private void markSelectionTechnicalProfessionalAbility(EspdDocument espdDocument, List<CriterionType> criteria) {
        addSelectedUblCriterion(WORK_CONTRACTS_PERFORMANCE_OF_WORKS, espdDocument.getWorkContractsPerformanceOfWorks(),
                criteria);
        addSelectedUblCriterion(SUPPLY_CONTRACTS_PERFORMANCE_OF_DELIVERIES,
                espdDocument.getSupplyContractsPerformanceDeliveries(), criteria);
        addSelectedUblCriterion(SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES,
                espdDocument.getServiceContractsPerformanceServices(), criteria);
        addSelectedUblCriterion(TECHNICIANS_OR_TECHNICAL_BODIES, espdDocument.getTechniciansTechnicalBodies(),
                criteria);
        addSelectedUblCriterion(WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES,
                espdDocument.getWorkContractsTechnicians(),
                criteria);
        addSelectedUblCriterion(TECHNICAL_FACILITIES_AND_MEASURES, espdDocument.getTechnicalFacilitiesMeasures(),
                criteria);
        addSelectedUblCriterion(STUDY_AND_RESEARCH_FACILITIES, espdDocument.getStudyResearchFacilities(), criteria);
        addSelectedUblCriterion(SUPPLY_CHAIN_MANAGEMENT, espdDocument.getSupplyChainManagement(), criteria);
        addSelectedUblCriterion(ALLOWANCE_OF_CHECKS, espdDocument.getAllowanceOfChecks(), criteria);
        addSelectedUblCriterion(EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS,
                espdDocument.getEducationalProfessionalQualifications(), criteria);
        addSelectedUblCriterion(ENVIRONMENTAL_MANAGEMENT_FEATURES, espdDocument.getEnvironmentalManagementFeatures(),
                criteria);
        addSelectedUblCriterion(NUMBER_OF_MANAGERIAL_STAFF, espdDocument.getNumberManagerialStaff(), criteria);
        addSelectedUblCriterion(AVERAGE_ANNUAL_MANPOWER, espdDocument.getAverageAnnualManpower(), criteria);
        addSelectedUblCriterion(TOOLS_PLANT_TECHNICAL_EQUIPMENT, espdDocument.getToolsPlantTechnicalEquipment(),
                criteria);
        addSelectedUblCriterion(SUBCONTRACTING_PROPORTION, espdDocument.getSubcontractingProportion(), criteria);
        addSelectedUblCriterion(SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA,
                espdDocument.getSupplyContractsSamplesDescriptionsWithoutCa(), criteria);
        addSelectedUblCriterion(SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA,
                espdDocument.getSupplyContractsSamplesDescriptionsWithCa(), criteria);
        addSelectedUblCriterion(SUPPLY_CONTRACTS_CERTIFICATES_QC, espdDocument.getSupplyContractsCertificatesQc(),
                criteria);
        addSelectedUblCriterion(CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA,
                espdDocument.getCertificateIndependentBodiesAboutQa(),
                criteria);
        addSelectedUblCriterion(CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL,
                espdDocument.getCertificateIndependentBodiesAboutEnvironmental(), criteria);
    }

    /**
     * Add a UBL criterion only if it was selected (exists) by the CA.
     *
     * @param ccvCriterion
     * @param espdCriterion
     * @param ublCriteria
     */
    protected final void addSelectedUblCriterion(CcvCriterion ccvCriterion, Criterion espdCriterion,
            List<CriterionType> ublCriteria) {
        if (isCriterionSelectedByTheCA(espdCriterion)) {
            ublCriteria.add(ublCriterionTypeTransformer.buildCriterionType(ccvCriterion, espdCriterion));
        }
    }

    /**
     * Add a UBL criterion no matter the exists flag (needed by award criteria which always need to be present).
     *
     * @param ccvCriterion
     * @param espdCriterion
     * @param ublCriteria
     */
    protected final void addAlwaysUblCriterion(CcvCriterion ccvCriterion, Criterion espdCriterion,
            List<CriterionType> ublCriteria) {
        ublCriteria.add(ublCriterionTypeTransformer.buildCriterionType(ccvCriterion, espdCriterion));
    }

    private boolean isCriterionSelectedByTheCA(Criterion espdCriterion) {
        return espdCriterion != null && espdCriterion.getExists();
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
