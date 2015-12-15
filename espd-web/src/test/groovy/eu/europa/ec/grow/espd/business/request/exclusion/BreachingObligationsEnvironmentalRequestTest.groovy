package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 1:15 PM.
 */
class BreachingObligationsEnvironmentalRequestTest extends AbstractRequestExclusionFixture {


    def "09. should contain the 'Breaching of obligations in the fields of environmental, social and labour law' criterion"() {
        given:
        def espd = new EspdDocument(breachingObligations: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "a80ddb62-d25b-4e4e-ae22-3968460dc0a9")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.ENVIRONMENTAL")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Breaching of obligations in the fields of environmental, social and labour law"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator, to its knowledge, breached its obligations in the fields of environmental, social and labour law? As referred to for the purposes of this procurement in national law, in the relevant notice or the procurement documents or in Article 18(2) of Directive 2004/18/EU."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }

}