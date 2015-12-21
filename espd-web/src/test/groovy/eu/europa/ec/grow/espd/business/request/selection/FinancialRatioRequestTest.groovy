package eu.europa.ec.grow.espd.business.request.selection

import eu.europa.ec.grow.espd.business.base.AbstractSelectionCriteriaFixture
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class FinancialRatioRequestTest extends AbstractSelectionCriteriaFixture {

    def "10. should contain the 'Financial ratio' criterion"() {
        given:
        def espd = new EspdDocument(financialRatio: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Financial ratio"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Concerning the financial ratios  specified in the relevant notice, the procurement documents or the ESPD, the economic operator declares that the actual values for the required ratios are as follows."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "cf00f7bb-c2cf-4565-91bb-221d78d8dd2f"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 3
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46", "Please provide the the required ratio - ratio between x and y - and the value", "RATIO")

        then: "check year amount currency subgroups"
        checkYearAmountCurrency1Group(request.Criterion[idx].RequirementGroup[0].RequirementGroup[0])
        checkYearAmountCurrency2Group(request.Criterion[idx].RequirementGroup[0].RequirementGroup[1])
        checkYearAmountCurrency3Group(request.Criterion[idx].RequirementGroup[0].RequirementGroup[2])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}