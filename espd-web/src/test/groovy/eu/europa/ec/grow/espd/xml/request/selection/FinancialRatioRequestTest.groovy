package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class FinancialRatioRequestTest extends AbstractSelectionCriteriaFixture {

    def "11. should contain the 'Financial ratio' criterion"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then: "CriterionID element"
        request.Criterion.size() == getRequestNumberOfCriteria()
        checkCriterionId(request, idx, "e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Financial ratio"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Concerning the financial ratios  specified in the relevant notice, the procurement documents or the ESPD, the economic operator declares that the actual values for the required ratios are as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "cf00f7bb-c2cf-4565-91bb-221d78d8dd2f"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 5
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "In the financial ratios a requirement is required previous to a subgroup of requirements so we add a dummy sentence like 'please provide the requested data below'"
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "3a6fefd4-f458-4d43-97fb-0725fce5dce2", "Please provide the requested data below", "DESCRIPTION")

        then: "check description ratio subgroups"
        checkDescriptionRatioGroup1(request.Criterion[idx].RequirementGroup[0].RequirementGroup[0])
        checkDescriptionRatioGroup2(request.Criterion[idx].RequirementGroup[0].RequirementGroup[1])
        checkDescriptionRatioGroup3(request.Criterion[idx].RequirementGroup[0].RequirementGroup[2])
        checkDescriptionRatioGroup4(request.Criterion[idx].RequirementGroup[0].RequirementGroup[3])
        checkDescriptionRatioGroup5(request.Criterion[idx].RequirementGroup[0].RequirementGroup[4])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}