package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class NumberOfManagerialStaffRequestTest extends AbstractEspdXmlMarshalling {

    def "24. should contain the 'Number of managerial staff' criterion"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "6346959b-e097-4ea1-89cd-d1b4c131ea4d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Number of managerial staff"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "The economic operator’s number of managerial staff for the last three years were as follows."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}