package eu.europa.ec.grow.espd.xml.request.selection
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SpecificYearlyTurnoverRequestTest extends AbstractSelectionCriteriaFixture {

    def "08. should contain the 'Specific yearly turnover' criterion"() {
        given:
        def espd = new EspdDocument(specificYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "074f6031-55f9-4e99-b9a4-c4363e8bc315")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Specific yearly turnover"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Its specific yearly turnover in the business area covered by the contract for the number of financial years required in the relevant notice, the procurement documents or the ESPD is as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "ee1fdbab-f54e-4579-bcb8-060fe45178e9"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 3
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")

        then: "check year amount currency subgroups"
        checkYearAmountCurrency1Group(request.Criterion[idx].RequirementGroup[0].RequirementGroup[0])
        checkYearAmountCurrency2Group(request.Criterion[idx].RequirementGroup[0].RequirementGroup[1])
        checkYearAmountCurrency3Group(request.Criterion[idx].RequirementGroup[0].RequirementGroup[2])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}