package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 1:26 PM.
 */
class GuiltyOfMisinterpretationRequestTest extends AbstractRequestExclusionFixture {

    def "20. should contain the 'Guilty of misinterpretation, withheld information, able to provide required documents and obtained confidential information of this procedure' criterion"() {
        given:
        def espd = new EspdDocument(guiltyMisinterpretation: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "696a75b2-6107-428f-8b74-82affb67e184")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Guilty of misinterpretation, withheld information, able to provide required documents and obtained confidential information of this procedure"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Can the economic operator confirm the four exclusion grounds, that it has not been guilty of serious misrepresentation in supplying the information required for the verification of the absence of grounds for exclusion or the fulfilment of the selection criteria, that it has not withheld such information, it has been able, without delay, to submit the supporting documents required by a contracting authority or contracting entity, and it has not undertaken to unduly influence the decision making process of the contracting authority or contracting entity, to obtain confidential information that may confer upon it undue advantages in the procurement procedure or to negligently provide misleading information that may have a material influence on decisions concerning exclusion, selection or award?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }


}