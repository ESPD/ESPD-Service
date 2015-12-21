package eu.europa.ec.grow.espd.business.request.selection

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SupplyContractsSamplesWithoutCARequestTest extends AbstractRequestSelectionFixture {

    def "28. should contain the 'For supply contracts: samples, descriptions or photographs without certification of authenticity' criterion"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithoutCa: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "bdf0601d-2480-4250-b870-658d0ee95be6")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For supply contracts: samples, descriptions or photographs without certification of authenticity"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator will supply the required samples, descriptions or photographs of the products to be supplied, which do not need to be accompanied by certifications of authenticity."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "cb73544d-e8bb-4cc6-819b-b8e04f1e240e"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}