package eu.europa.ec.grow.espd.xml.request.exclusion

import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 1:23 PM.
 */
class DirectInvolvementPreparationProcurementResponseTest extends AbstractExclusionCriteriaFixture {

    def "18. should contain the 'Direct or indirect involvement in the preparation of this procurement procedure' criterion"() {
        given:
        def espd = new EspdDocument(involvementPreparationProcurement: new BreachOfObligations(exists: true))

        when:
        def request = parseResponseXml(espd)
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

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "77ae3f29-7c5f-4afa-af97-24afec48c5bf"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "7b07904f-e080-401a-a3a1-9a3efeeda54b", "Please describe them", "DESCRIPTION")
    }

}