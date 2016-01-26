package eu.europa.ec.grow.espd.xml.request

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.BankruptcyCriterion
import eu.europa.ec.grow.espd.domain.ConflictInterestCriterion
import eu.europa.ec.grow.espd.domain.CriminalConvictionsCriterion
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.LawCriterion
import eu.europa.ec.grow.espd.domain.MisconductDistortionCriterion
import eu.europa.ec.grow.espd.domain.PurelyNationalGrounds
import eu.europa.ec.grow.espd.domain.SatisfiesAllCriterion
import eu.europa.ec.grow.espd.domain.SuitabilityCriterion
import eu.europa.ec.grow.espd.domain.TaxesCriterion
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 * Created by ratoico on 1/26/16 at 10:54 AM.
 */
class EspdRequestMixedCriteriaTest extends AbstractCriteriaFixture {

    def "should contain all exclusion and selection criteria"() {
        given:
        def espd = new EspdDocument(
                // exclusion
                criminalConvictions: new CriminalConvictionsCriterion(exists: true),
                corruption: new CriminalConvictionsCriterion(exists: true),
                fraud: new CriminalConvictionsCriterion(exists: true),
                terroristOffences: new CriminalConvictionsCriterion(exists: true),
                moneyLaundering: new CriminalConvictionsCriterion(exists: true),
                childLabour: new CriminalConvictionsCriterion(exists: true),
                paymentTaxes: new TaxesCriterion(exists: true),
                paymentSocialSecurity: new TaxesCriterion(exists: true),
                breachingObligationsEnvironmental: new LawCriterion(exists: true),
                breachingObligationsSocial: new LawCriterion(exists: true),
                breachingObligationsLabour: new LawCriterion(exists: true),
                bankruptcy: new BankruptcyCriterion(exists: true),
                insolvency: new BankruptcyCriterion(exists: true),
                arrangementWithCreditors: new BankruptcyCriterion(exists: true),
                analogousSituation: new BankruptcyCriterion(exists: true),
                assetsAdministeredByLiquidator: new BankruptcyCriterion(exists: true),
                businessActivitiesSuspended: new BankruptcyCriterion(exists: true),
                guiltyGrave: new MisconductDistortionCriterion(exists: true),
                agreementsWithOtherEO: new MisconductDistortionCriterion(exists: true),
                conflictInterest: new ConflictInterestCriterion(exists: true),
                involvementPreparationProcurement: new ConflictInterestCriterion(exists: true),
                earlyTermination: new ConflictInterestCriterion(exists: true),
                guiltyMisinterpretation: new ConflictInterestCriterion(exists: true),
                purelyNationalGrounds: new PurelyNationalGrounds(exists: true),
                // selection
                selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true),
                enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true),
                enrolmentTradeRegister: new SuitabilityCriterion(exists: true),
                serviceContractsAuthorisation: new SuitabilityCriterion(exists: true),
                serviceContractsMembership: new SuitabilityCriterion(exists: true),
                generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                averageYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                specificYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true),
                setupEconomicOperator: new EconomicFinancialStandingCriterion(exists: true),
                financialRatio: new EconomicFinancialStandingCriterion(exists: true),
                professionalRiskInsurance: new EconomicFinancialStandingCriterion(exists: true),
                otherEconomicFinancialRequirements: new EconomicFinancialStandingCriterion(exists: true),
                workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true),
                supplyContractsPerformanceDeliveries: new TechnicalProfessionalCriterion(exists: true),
                serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true),
                techniciansTechnicalBodies: new TechnicalProfessionalCriterion(exists: true),
                workContractsTechnicians: new TechnicalProfessionalCriterion(exists: true),
                technicalFacilitiesMeasures: new TechnicalProfessionalCriterion(exists: true),
                studyResearchFacilities: new TechnicalProfessionalCriterion(exists: true),
                supplyChainManagement: new TechnicalProfessionalCriterion(exists: true),
                allowanceOfChecks: new TechnicalProfessionalCriterion(exists: true),
                educationalProfessionalQualifications: new TechnicalProfessionalCriterion(exists: true),
                environmentalManagementFeatures: new TechnicalProfessionalCriterion(exists: true),
                numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true),
                averageAnnualManpower: new TechnicalProfessionalCriterion(exists: true),
                toolsPlantTechnicalEquipment: new TechnicalProfessionalCriterion(exists: true),
                subcontractingProportion: new TechnicalProfessionalCriterion(exists: true),
                supplyContractsSamplesDescriptionsWithoutCa: new TechnicalProfessionalCriterion(exists: true),
                supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true),
                supplyContractsCertificatesQc: new TechnicalProfessionalCriterion(exists: true),
                certificateIndependentBodiesAboutQa: new TechnicalProfessionalCriterion(exists: true),
                certificateIndependentBodiesAboutEnvironmental: new TechnicalProfessionalCriterion(exists: true),
                meetsObjective: new AwardCriterion(exists: true)
        )

        when:
        def result = parseRequestXml(espd)

        then: "all eclusion and selection criteria, plus meets objective criterion"
        result.Criterion.size() == ExclusionCriterion.values().size() + SelectionCriterion.values().size() + 1
    }

    def "should not fail when a criterion has a null exists flag"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: null))

        when:
        def result = parseRequestXml(espd)

        then:
        result.Criterion.size() == 0
    }

    def "only selected criteria should appear in the request xml"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true),
                enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true))

        when:
        def result = parseRequestXml(espd)

        then:
        result.Criterion.size() == 2
        checkCriterionId(result, 0, "005eb9ed-1347-4ca3-bb29-9bc0db64e1ab")
        checkCriterionId(result, 1, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")
    }

}