package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 1:23 PM.
 */
class DirectInvolvementPreparationProcurementRequestTest extends AbstractRequestExclusionFixture {

    def "18. should contain the 'Direct or indirect involvement in the preparation of this procurement procedure' criterion"() {
        given:
        def espd = new EspdDocument(involvementPreparationProcurement: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "61874050-5130-4f1c-a174-720939c7b483")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Direct or indirect involvement in the preparation of this procurement procedure"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator or an undertaking related to it advised the contracting authority or contracting entity or otherwise been involved in the preparation of the procurement procedure?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }


}