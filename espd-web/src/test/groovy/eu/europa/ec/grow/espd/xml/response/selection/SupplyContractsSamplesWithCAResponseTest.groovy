package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SupplyContractsSamplesWithCAResponseTest extends AbstractSelectionCriteriaFixture {

    def "30. should contain the 'For supply contracts: samples, descriptions or photographs with certification of authenticity' criterion"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "7662b7a9-bcb8-4763-a0a7-7505d8e8470d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For supply contracts: samples, descriptions or photographs with certification of authenticity"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "For public supply contracts: The economic operator will supply the required samples, descriptions or photographs of the products to be supplied and will provide certifications of authenticity where applicable."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "511ddbf6-2c53-4fea-a469-3edc9941e603"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Your naswer' requirements response"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true, answer: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_29.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_29.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_29")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_29"
    }

}