package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class AverageYearlyTurnoverRequestTest extends AbstractEspdXmlMarshalling {

    def "07. should contain the 'Average yearly turnover' criterion"() {
        given:
        def espd = new EspdDocument(averageYearlyTurnover: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "b16cb9fc-6cb7-4585-9302-9533b415cf48")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Average yearly turnover"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "The economic operator's average yearly turnover for the number of years required in the relevant notice, the procurement documents or the ESPD is as follows."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")
    }

}