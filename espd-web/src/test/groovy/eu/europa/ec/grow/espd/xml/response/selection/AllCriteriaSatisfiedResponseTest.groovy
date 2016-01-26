package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SatisfiesAllCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 1/4/16 at 1:24 PM.
 */
class AllCriteriaSatisfiedResponseTest extends AbstractSelectionCriteriaFixture {

    def "01. should contain the 'All selection criteria will be satisfied' criterion"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true))
        def idx = getResponseCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        when:
        def request = parseResponseXml(espd)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "7e7db838-eeac-46d9-ab39-42927486f22d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ALL_CRITERIA_SATISFIED")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "All selection criteria will be satisfied"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "It satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice."

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "f3a6836d-2de2-4cd1-81ca-fb06178d05c5"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

}