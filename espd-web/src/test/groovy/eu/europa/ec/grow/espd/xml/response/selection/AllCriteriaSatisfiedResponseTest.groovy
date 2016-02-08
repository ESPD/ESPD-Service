package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture

/**
 * Created by ratoico on 1/4/16 at 1:24 PM.
 */
class AllCriteriaSatisfiedResponseTest extends AbstractSelectionCriteriaFixture {

    def "01. CA selects 'All section criteria' -> The request contains only 'All selection criteria' and not the individual ones."() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true))
        def idx = getResponseCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        when:
        def request = parseResponseXml(espd)

        then: "CriterionID element"
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
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true, answer: false))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "CA selects 'All section criteria' -> The request contains only 'All selection criteria' and not the individual ones."() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true, answer: true),
                enrolmentTradeRegister: new SuitabilityCriterion(exists: true, answer: true),
                generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true, answer: true),
                workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true, answer: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        then:
        request.Criterion.size() == getMandatoryExclusionCriteriaSize() + 1 + eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.values().length
        checkCriterionId(request, idx, "7e7db838-eeac-46d9-ab39-42927486f22d")

        then: "the next criterion should be the first award criterion"
        checkCriterionId(request, idx + 1, eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.PROCUREMENT_RESERVED.getUuid())
    }

    def "CA select individual selection criteria -> EO sees only the selected ones (and even not the 'All selection criteria')"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: false),
                enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true),
                generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)

        then: "only selected selection criteria are present plus mandatory exclusion plus the award criterion"
        request.Criterion.size() == getMandatoryExclusionCriteriaSize() + 3 + eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.values().length

        then: "check the CriterionID"
        // satisfies all is not selected by the CA so the index of the first selection criterion is one position lower
        def idx = getResponseCriterionIndex(SelectionCriterion.ENROLMENT_PROFESSIONAL_REGISTER)
        checkCriterionId(request, idx, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")
        checkCriterionId(request, idx + 1, "499efc97-2ac1-4af2-9e84-323c2ca67747")
        checkCriterionId(request, idx + 2, "cdd3bb3e-34a5-43d5-b668-2aab86a73822")
    }

}