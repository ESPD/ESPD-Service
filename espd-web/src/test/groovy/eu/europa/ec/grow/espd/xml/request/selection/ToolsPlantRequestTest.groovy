package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ToolsPlantRequestTest extends AbstractSelectionCriteriaFixture {

    def "26. should contain the 'Tools, plant or technical equipment' criterion"() {
        given:
        def espd = new EspdDocument(toolsPlantTechnicalEquipment: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.TOOLS_PLANT_TECHNICAL_EQUIPMENT)

        then: "CriterionID element"
        request.Criterion.size() == getRequestNumberOfCriteria()
        checkCriterionId(request, idx, "cc18c023-211d-484d-a32e-52f3f970285f")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Tools, plant or technical equipment"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The following tools, plant or technical equipment will be available to it for performing the contract:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "eb18b241-7a11-415d-a04f-94fe0dae8e77"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}