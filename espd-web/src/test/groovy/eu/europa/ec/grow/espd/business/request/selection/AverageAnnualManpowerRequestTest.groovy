package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class AverageAnnualManpowerRequestTest extends AbstractEspdXmlMarshalling {

    def "25. should contain the 'Average annual manpower' criterion"() {
        given:
        def espd = new EspdDocument(averageAnnualManpower: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "1f49b3f0-d50f-43f6-8b30-4bafab108b9b")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Average annual manpower"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator’s average annual manpower for the last three years were as follows."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}