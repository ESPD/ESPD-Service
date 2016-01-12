package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class FinancialRatioResponseTest extends AbstractSelectionCriteriaFixture {

    def "10. should contain the 'Financial ratio' criterion"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Financial ratio"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Concerning the financial ratios  specified in the relevant notice, the procurement documents or the ESPD, the economic operator declares that the actual values for the required ratios are as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "cf00f7bb-c2cf-4565-91bb-221d78d8dd2f"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 3
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46", "Please provide the the required ratio - ratio between x and y - and the value", "RATIO")

        then: "check year amount currency subgroups"
        checkYearAmountCurrency1Group(request.Criterion[idx].RequirementGroup[0].RequirementGroup[0])
        checkYearAmountCurrency2Group(request.Criterion[idx].RequirementGroup[0].RequirementGroup[1])
        checkYearAmountCurrency3Group(request.Criterion[idx].RequirementGroup[0].RequirementGroup[2])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    // TODO ratio

    def "check the 'Year' requirements response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                year1: 2016, year2: 2015, year3: 2014))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "First year"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Quantity.text() == "2016"
        req1.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Second year"
        def subGroup2 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]
        def req2 = subGroup2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Quantity.text() == "2015"
        req2.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Third year"
        def subGroup3 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[2]
        def req3 = subGroup3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Quantity.text() == "2014"
        req3.Response[0].Quantity.@unitCode.text() == "YEAR"
    }

    def "check empty 'Year' requirements response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                year1: null, year2: null, year3: null))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "First year"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Quantity.size() == 0

        then: "Second year"
        def subGroup2 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]
        def req2 = subGroup2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Quantity.size() == 0

        then: "Third year"
        def subGroup3 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[2]
        def req3 = subGroup3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Quantity.size() == 0
    }

    def "check the 'Amount' requirements response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                amount1: 11.11, amount2: 22.22, amount3: 33.33, currency1: "EUR", currency2: "RON", currency3: "USD"))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "First amount"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response.Amount.text() == "11.11"
        req1.Response.Amount.@currencyID.text() == "EUR"

        then: "Second amount"
        def subGroup2 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]
        def req2 = subGroup2.Requirement[1]
        req2.Response.size() == 1
        req2.Response.Amount.text() == "22.22"
        req2.Response.Amount.@currencyID.text() == "RON"

        then: "Third amount"
        def subGroup3 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[2]
        def req3 = subGroup3.Requirement[1]
        req3.Response.size() == 1
        req3.Response.Amount.text() == "33.33"
        req3.Response.Amount.@currencyID == "USD"
    }

    def "check empty 'Amount' requirements response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                amount1: null, amount2: null, amount3: null, currency1: "EUR", currency2: "RON", currency3: "USD"))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "First amount"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response.Amount.size() == 0

        then: "Second amount"
        def subGroup2 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]
        def req2 = subGroup2.Requirement[1]
        req2.Response.size() == 1
        req2.Response.Amount.size() == 0

        then: "Third amount"
        def subGroup3 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[2]
        def req3 = subGroup3.Requirement[1]
        req3.Response.size() == 1
        req3.Response.Amount.size() == 0
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_10.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "http://hodor_10.com"
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_10")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_10"
    }

}