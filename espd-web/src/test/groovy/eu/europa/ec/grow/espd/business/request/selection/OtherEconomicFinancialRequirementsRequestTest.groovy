package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class OtherEconomicFinancialRequirementsRequestTest extends AbstractEspdXmlMarshalling {

    def "12. should contain the 'Other economic or financial requirements' criterion"() {
        given:
        def espd = new EspdDocument(otherEconomicFinancialRequirements: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "ab0e7f2e-6418-40e2-8870-6713123e41ad")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Other economic or financial requirements"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Concerning the other economic or financial requirements, if any, that may have been specified in the relevant notice or the procurement documents, please specify which apply to this economic operator."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")
    }

}