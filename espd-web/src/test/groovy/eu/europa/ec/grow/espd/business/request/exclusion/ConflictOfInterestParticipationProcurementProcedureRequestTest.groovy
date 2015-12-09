package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 1:22 PM.
 */
class ConflictOfInterestParticipationProcurementProcedureRequestTest extends AbstractEspdXmlMarshalling {

    def "17. should contain the 'Conflict of interest due to its participation in the procurement procedure' criterion"() {
        given:
        def espd = new EspdDocument(conflictInterest: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "b1b5ac18-f393-4280-9659-1367943c1a2e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Conflict of interest due to its participation in the procurement procedure"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "Is the economic operator aware of any conflict of interest, as indicated in national law, the relevant notice or the procurement documents due to its participation in the procurement procedure?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }

}