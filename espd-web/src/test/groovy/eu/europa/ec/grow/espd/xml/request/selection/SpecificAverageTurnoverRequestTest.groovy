package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SpecificAverageTurnoverRequestTest extends AbstractSelectionCriteriaFixture {

    def "09. should contain the 'Specific average turnover' criterion"() {
        given:
        def espd = new EspdDocument(specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.SPECIFIC_AVERAGE_TURNOVER)

        then: "CriterionID element"
        request.Criterion.size() == getRequestNumberOfCriteria()
        checkCriterionId(request, idx, "d3dfb714-f558-4512-bbc5-e456fa2339de")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Specific average turnover"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Its specific average yearly turnover in the business area covered by the contract for the number of years required in the relevant notice, the procurement documents or the ESPD is as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "fa29f9e1-dd24-4fe9-873d-1a6dbc720cb0"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 5
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")

        then: "check year amount currency subgroups"
        checkYearAmountCurrencyGroup1(request.Criterion[idx].RequirementGroup[0].RequirementGroup[0])
        checkYearAmountCurrencyGroup2(request.Criterion[idx].RequirementGroup[0].RequirementGroup[1])
        checkYearAmountCurrencyGroup3(request.Criterion[idx].RequirementGroup[0].RequirementGroup[2])
        checkYearAmountCurrencyGroup4(request.Criterion[idx].RequirementGroup[0].RequirementGroup[3])
        checkYearAmountCurrencyGroup5(request.Criterion[idx].RequirementGroup[0].RequirementGroup[4])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}