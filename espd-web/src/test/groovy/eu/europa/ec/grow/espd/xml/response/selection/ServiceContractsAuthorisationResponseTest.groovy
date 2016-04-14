package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SuitabilityCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ServiceContractsAuthorisationResponseTest extends AbstractSelectionCriteriaFixture {

    def "04. should contain the 'For service contracts: authorisation of particular organisation needed' criterion"() {
        given:
        def espd = new EspdDocument(serviceContractsAuthorisation: new SuitabilityCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_AUTHORISATION)

        then: "CriterionID element"
        checkCriterionId(request, idx, "9eeb6d5c-0eb8-48e8-a4c5-5087a7c095a4")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.SUITABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For service contracts: authorisation of particular organisation needed"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Is a particular membership of a particular organisation needed in order to be able to perform the service in question in the country of establishment of the economic operator?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(2)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "a109e144-f65e-469d-bcda-220f1af34b6c"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsAuthorisation: new SuitabilityCriterion(exists: true, answer: false))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_AUTHORISATION)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Please describe' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsAuthorisation: new SuitabilityCriterion(exists: true, answer: true, description: "hodor"))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_AUTHORISATION)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "hodor"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsAuthorisation: new SuitabilityCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_AUTHORISATION)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsAuthorisation: new SuitabilityCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_04.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_AUTHORISATION)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_04.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsAuthorisation: new SuitabilityCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_04")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_AUTHORISATION)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_04"
    }

}