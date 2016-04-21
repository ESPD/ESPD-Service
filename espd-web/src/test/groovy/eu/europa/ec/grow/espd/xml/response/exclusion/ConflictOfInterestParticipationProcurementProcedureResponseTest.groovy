package eu.europa.ec.grow.espd.xml.response.exclusion

import eu.europa.ec.grow.espd.domain.ConflictInterestCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:22 PM.
 */
class ConflictOfInterestParticipationProcurementProcedureResponseTest extends AbstractExclusionCriteriaFixture {

    def "20. should contain the 'Conflict of interest due to its participation in the procurement procedure' criterion"() {
        given:
        def espd = new EspdDocument(conflictInterest: new ConflictInterestCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE)

        then: "CriterionID element"
        checkCriterionId(request, idx, "b1b5ac18-f393-4280-9659-1367943c1a2e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Conflict of interest due to its participation in the procurement procedure"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Is the economic operator aware of any conflict of interest, as indicated in national law, the relevant notice or the procurement documents due to its participation in the procurement procedure?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "30450436-f559-4dfa-98ba-f0842ed9d2a0"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(conflictInterest: new ConflictInterestCriterion(exists: true, answer: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE)

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }
}