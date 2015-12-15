package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ToolsPlantRequestTest extends AbstractEspdXmlMarshalling {

    def "26. should contain the 'Tools, plant or technical equipment' criterion"() {
        given:
        def espd = new EspdDocument(toolsPlantTechnicalEquipment: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "cc18c023-211d-484d-a32e-52f3f970285f")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Tools, plant or technical equipment"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The following tools, plant or technical equipment will be available to it for performing the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}