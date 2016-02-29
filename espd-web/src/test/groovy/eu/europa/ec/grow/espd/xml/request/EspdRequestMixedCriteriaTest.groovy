package eu.europa.ec.grow.espd.xml.request
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 * Created by ratoico on 1/26/16 at 10:54 AM.
 */
class EspdRequestMixedCriteriaTest extends AbstractCriteriaFixture {

    def "should contain all exclusion and selection criteria and the only request award criterion"() {
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
                // selection satisfies all has special meaning
                selectionSatisfiesAll: new SatisfiesAllCriterion(exists: false),
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
                certificateIndependentBodiesAboutQa: new QualityAssuranceCriterion(exists: true),
                certificateIndependentBodiesAboutEnvironmental: new QualityAssuranceCriterion(exists: true),
                meetsObjective: new AwardCriterion(exists: true)
        )

        when:
        def result = parseRequestXml(espd)

        then: "all eclusion and selection criteria (minus satsifies all), plus meets objective criterion"
        result.Criterion.size() == ExclusionCriterion.values().size() + SelectionCriterion.values().size()
    }

    def "should contain mandatory exclusion criteria plus all selection criteria plus the only request award criterion"() {
        given:
        def espd = new EspdDocument()
        def idx

        when:
        def result = parseRequestXml(espd)

        then:
        result.Criterion.size() == getMandatoryExclusionCriteriaSize() + SelectionCriterion.values().length + 1

        then: "all exclusion criteria are mandatory (except 'purely national'"
        for (ExclusionCriterion criterion : ExclusionCriterion.values()) {
            idx = getRequestCriterionIndex(criterion)
            if (ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS.equals(criterion)) {
                continue
            }
            checkCriterionId(result, idx, criterion.getUuid())
        }

        then: "all selection criteria must be present since there were none selected"
        for (SelectionCriterion criterion : SelectionCriterion.values()) {
            checkCriterionId(result, idx++, criterion.getUuid())
        }

        then: "and the only request award criterion"
        checkCriterionId(result, idx, "9c70375e-1264-407e-8b50-b9736bc08901")
    }

}