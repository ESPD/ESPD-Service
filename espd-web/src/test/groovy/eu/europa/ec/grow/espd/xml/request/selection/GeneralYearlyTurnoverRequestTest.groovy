package eu.europa.ec.grow.espd.xml.request.selection
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class GeneralYearlyTurnoverRequestTest extends AbstractSelectionCriteriaFixture {

    def "06. should contain the 'General yearly turnover' criterion"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "499efc97-2ac1-4af2-9e84-323c2ca67747")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "General yearly turnover"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Its general yearly turnover for the number of financial years required in the relevant notice, the procurement documents or the ESPD is as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "e1886054-ada4-473c-9afc-2fde82c24cf4"
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