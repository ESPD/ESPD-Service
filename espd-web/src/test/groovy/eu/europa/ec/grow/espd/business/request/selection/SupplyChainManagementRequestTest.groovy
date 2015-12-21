package eu.europa.ec.grow.espd.business.request.selection

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SupplyChainManagementRequestTest extends AbstractRequestSelectionFixture {

    def "20. should contain the 'Supply chain management' criterion"() {
        given:
        def espd = new EspdDocument(supplyChainManagement: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "dc12a151-7fdf-4733-a8f0-30f667292e66")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Supply chain management"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator will be able to apply the following supply chain management and tracking systems when performing the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "2fd60f39-d484-4862-ba5f-0a8c46da11d8"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}