package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 1:24 PM.
 */
class EarlyTerminationRequestTest extends AbstractEspdXmlMarshalling {

    def "19. should contain the 'Early termination, damages or other comparable sanctions' criterion"() {
        given:
        def espd = new EspdDocument(earlyTermination: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "3293e92b-7f3e-42f1-bee6-a7641bb04251")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Early termination, damages or other comparable sanctions"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator experienced that a prior public contract, a prior contract with a contracting entity or a prior concession contract was terminated early, or that damages or other comparable sanctions were imposed in connection with that prior contract?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }

}