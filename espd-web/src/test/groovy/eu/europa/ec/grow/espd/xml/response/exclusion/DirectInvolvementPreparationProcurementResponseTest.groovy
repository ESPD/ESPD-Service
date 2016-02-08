package eu.europa.ec.grow.espd.xml.response.exclusion

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.ConflictInterestCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:23 PM.
 */
class DirectInvolvementPreparationProcurementResponseTest extends AbstractExclusionCriteriaFixture {

    def "21. should contain the 'Direct or indirect involvement in the preparation of this procurement procedure' criterion"() {
        given:
        def espd = new EspdDocument(involvementPreparationProcurement: new ConflictInterestCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE)

        then: "CriterionID element"
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
        checkRequirement(r1_1, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(involvementPreparationProcurement: new ConflictInterestCriterion(exists: true, answer: false))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE)

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(involvementPreparationProcurement: new ConflictInterestCriterion(exists: true,
                description: "bogus description."))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")
        req.Response[0].Description.text() == "bogus description."
    }

}