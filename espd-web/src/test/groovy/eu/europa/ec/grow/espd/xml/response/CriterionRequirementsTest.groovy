package eu.europa.ec.grow.espd.xml.response

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture

/**
 * Created by ratoico on 1/22/16 at 11:04 AM.
 */
class CriterionRequirementsTest extends AbstractCriteriaFixture {

    // IMPORTANT TESTS that make sure that there are no empty elements when values are not present

    def "a Description empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(arrangementWithCreditors: new BankruptcyCriterion(exists: true,
                description: "    "))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.ARRANGEMENT_WITH_CREDITORS)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response[0].Description.size() == 0
    }

    def "'Is this information available electronically' empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(arrangementWithCreditors: new BankruptcyCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false, url: null, code: "   ")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.ARRANGEMENT_WITH_CREDITORS)
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        then:
        def req1 = subGroup.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Indicator.text() == "false"

        then:
        def req2 = subGroup.Requirement[1]
        req2.Response.Evidence.size() == 0

        then:
        def req3 = subGroup.Requirement[2]
        req3.Response.Code.size() == 0
    }

    def "a Period empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(childLabour: new CriminalConvictionsCriterion(exists: true, periodLength: "  "))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.CHILD_LABOUR)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[4]
        req.Response.size() == 1
        req.Response[0].Period.size() == 0
    }

    def "a Date empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(childLabour: new CriminalConvictionsCriterion(exists: true, dateOfConviction: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.CHILD_LABOUR)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Date.size() == 0
    }

    def "a Country empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, country: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.size() == 0
    }

    def "an Amount empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, amount: null, currency: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[2]
        req.Response.size() == 1
        req.Response.Amount.size() == 0
    }

    def "an Year empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true,
                year1: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion.GENERAL_YEARLY_TURNOVER)

        then:
        def subGroup1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Quantity.size() == 0
    }

    def "a Number empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                number1: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then: "First number"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response[0].Quantity.size() == 0
    }

    def "a Quantity empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                ratio1: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion.FINANCIAL_RATIO)

        then:
        def subGroup1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response[0].Quantity.size() == 0
    }

    def "a Percent empty requirement should have empty response"() {
        given:
        def espd = new EspdDocument(procurementReserved: new AwardCriterion(exists: true,
                doubleValue1: null))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.PROCUREMENT_RESERVED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Percent.size() == 0
    }


}