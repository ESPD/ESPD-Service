package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class OtherEconomicFinancialRequirementsResponseTest extends AbstractSelectionCriteriaFixture {

    def "13. should contain the 'Other economic or financial requirements' criterion"() {
        given:
        def espd = new EspdDocument(otherEconomicFinancialRequirements: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "ab0e7f2e-6418-40e2-8870-6713123e41ad")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Other economic or financial requirements"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Concerning the other economic or financial requirements, if any, that may have been specified in the relevant notice or the procurement documents, the economic operator declares that:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "488ca189-bcdb-4bf4-80c7-3ad507fd89fb"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Please describe them' requirements response"() {
        given:
        def espd = new EspdDocument(otherEconomicFinancialRequirements: new EconomicFinancialStandingCriterion(exists: true,
                description: "other economic or financial description"))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Description.text() == "other economic or financial description"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(otherEconomicFinancialRequirements: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(otherEconomicFinancialRequirements: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_12.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_12.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(otherEconomicFinancialRequirements: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_12")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_12"
    }

}