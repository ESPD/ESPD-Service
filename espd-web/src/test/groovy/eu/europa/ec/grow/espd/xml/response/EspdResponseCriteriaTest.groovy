package eu.europa.ec.grow.espd.xml.response

import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture

/**
 * Created by ratoico on 1/27/16 at 11:54 AM.
 */
class EspdResponseCriteriaTest extends AbstractCriteriaFixture {

    def "should contain mandatory exclusion criteria plus all selection criteria plus all award criteria"() {
        given:
        def espd = new EspdDocument()
        def idx

        when:
        def result = parseResponseXml(espd)

        then:
        result.Criterion.size() == getMandatoryExclusionCriteriaSize() + SelectionCriterion.values().length + AwardCriterion.values().length

        then: "all exclusion criteria are mandatory (except 'purely national'"
        for (ExclusionCriterion criterion : ExclusionCriterion.values()) {
            idx = getRequestCriterionIndex(criterion)
            if (ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS.equals(criterion)) {
                continue
            }
            checkCriterionId(result, idx, criterion.getUuid())
        }

        then: "all selection criteria must be present since there were none selected"
        for (SelectionCriterion criterion : SelectionCriterion.values()) {
            checkCriterionId(result, idx++, criterion.getUuid())
        }

        then: "all award criteria must be present"
        for (AwardCriterion criterion : AwardCriterion.values()) {
            checkCriterionId(result, idx++, criterion.getUuid())
        }
    }

    def "exclusion criteria with no 'Your answer' must have a default value of FALSE"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, answer: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "selection criteria with no 'Your answer' must have a default value of TRUE"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true, answer: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "Award criteria with no 'Indicator' don't have a response"() {
        given:
        def espd = new EspdDocument(meetsObjective: new eu.europa.ec.grow.espd.domain.AwardCriterion(exists: true, answer: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(AwardCriterion.MEETS_OBJECTIVE)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.size() == 0
    }
}