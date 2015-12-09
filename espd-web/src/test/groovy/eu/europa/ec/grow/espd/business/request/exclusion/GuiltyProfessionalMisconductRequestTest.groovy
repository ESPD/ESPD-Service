package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 1:21 PM.
 */
class GuiltyProfessionalMisconductRequestTest extends AbstractEspdXmlMarshalling {

    def "16. should contain the 'Guilty of grave professional misconduct' criterion"() {
        given:
        def espd = new EspdDocument(guiltyGrave: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "514d3fde-1e3e-4dcd-b02a-9f984d5bbda3")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.MISCONDUCT")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Guilty of grave professional misconduct"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "Is the economic operator  guilty of grave professional misconduct? Where applicable, see definitions in national law, the relevant notice or the procurement documents."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }

}