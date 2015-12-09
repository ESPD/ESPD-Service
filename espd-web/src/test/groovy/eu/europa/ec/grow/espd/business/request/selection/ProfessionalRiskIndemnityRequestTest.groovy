package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ProfessionalRiskIndemnityRequestTest extends AbstractEspdXmlMarshalling {

    def "11. should contain the 'Professional risk indemnity insurance' criterion"() {
        given:
        def espd = new EspdDocument(professionalRiskInsurance: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "7604bd40-4462-4086-8763-a50da51a869c")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Professional risk indemnity insurance"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "The insured amount in its professional risk indemnity insurance is the following."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")
    }

}