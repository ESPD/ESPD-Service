package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class StudyResearchFacilitiesRequestTest extends AbstractEspdXmlMarshalling {

    def "19. should contain the 'Study and research facilities' criterion"() {
        given:
        def espd = new EspdDocument(studyResearchFacilities: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "90a2e100-44cc-45d3-9970-69d6714f1596")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Study and research facilities"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "The economic operator uses the following study and research facilities are as follows."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}