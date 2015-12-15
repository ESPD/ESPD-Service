package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class TechnicalFacilitiesRequestTest extends AbstractEspdXmlMarshalling {

    def "18. should contain the 'Technical facilities and measures for ensuring quality' criterion"() {
        given:
        def espd = new EspdDocument(technicalFacilitiesMeasures: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "4bf996d9-439c-40c6-9ab9-980a48cb55a1")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Technical facilities and measures for ensuring quality"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator uses the following technical facilities and measures for ensuring quality are as follows."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}