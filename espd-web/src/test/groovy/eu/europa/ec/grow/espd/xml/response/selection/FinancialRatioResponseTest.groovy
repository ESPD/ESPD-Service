package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class FinancialRatioResponseTest extends AbstractSelectionCriteriaFixture {

    def "11. should contain the 'Financial ratio' criterion"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then: "CriterionID element"
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
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 5
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "In the financial ratios a requirement is required previous to a subgroup of requirements so we add a dummy sentence like 'please provide the requested data below'"
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "3a6fefd4-f458-4d43-97fb-0725fce5dce2", "Please provide the requested data below", "DESCRIPTION")

        then: "check year amount currency subgroups"
        checkDescriptionRatioGroup1(request.Criterion[idx].RequirementGroup[0].RequirementGroup[0])
        checkDescriptionRatioGroup2(request.Criterion[idx].RequirementGroup[0].RequirementGroup[1])
        checkDescriptionRatioGroup3(request.Criterion[idx].RequirementGroup[0].RequirementGroup[2])
        checkDescriptionRatioGroup4(request.Criterion[idx].RequirementGroup[0].RequirementGroup[3])
        checkDescriptionRatioGroup5(request.Criterion[idx].RequirementGroup[0].RequirementGroup[4])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Description' requirements response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                description1: "d1", description2: "d2", description3: "d3", description4: "d4", description5: "d5"))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then: "Description 1"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Description.text() == "d1"

        then: "Description 2"
        def subGroup2 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]
        def req2 = subGroup2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Description.text() == "d2"

        then: "Description 3"
        def subGroup3 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[2]
        def req3 = subGroup3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Description.text() == "d3"

        then: "Description 4"
        def subGroup4 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[3]
        def req4 = subGroup4.Requirement[0]
        req4.Response.size() == 1
        req4.Response[0].Description.text() == "d4"

        then: "Description 5"
        def subGroup5 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[4]
        def req5 = subGroup5.Requirement[0]
        req5.Response.size() == 1
        req5.Response[0].Description.text() == "d5"
    }

    def "check the 'Ratio' requirements response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                ratio1: 11.1, ratio2: 22.2, ratio3: 33.3, ratio4: 44.4, ratio5: 55.5))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then: "Ratio 1"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response[0].Quantity.text() == "11.1"

        then: "Ratio 2"
        def subGroup2 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]
        def req2 = subGroup2.Requirement[1]
        req2.Response.size() == 1
        req2.Response[0].Quantity.text() == "22.2"

        then: "Ratio 3"
        def subGroup3 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[2]
        def req3 = subGroup3.Requirement[1]
        req3.Response.size() == 1
        req3.Response[0].Quantity.text() == "33.3"

        then: "Ratio 4"
        def subGroup4 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[3]
        def req4 = subGroup4.Requirement[1]
        req4.Response.size() == 1
        req4.Response[0].Quantity.text() == "44.4"

        then: "Ratio 5"
        def subGroup5 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[4]
        def req5 = subGroup5.Requirement[1]
        req5.Response.size() == 1
        req5.Response[0].Quantity.text() == "55.5"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_10.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_10.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_10")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_10"
    }

}