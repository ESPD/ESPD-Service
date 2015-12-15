package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SpecificYearlyTurnoverRequestTest extends AbstractRequestSelectionFixture {

    def "08. should contain the 'Specific yearly turnover' criterion"() {
        given:
        def espd = new EspdDocument(specificYearlyTurnover: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "074f6031-55f9-4e99-b9a4-c4363e8bc315")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Specific yearly turnover"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator's specific yearly turnover in the business area covered by the contract for the number of financial years required in the relevant notice, the procurement documents or the ESPD is as follows."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")
    }

}