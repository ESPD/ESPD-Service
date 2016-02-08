package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 1/25/16 at 10:22 AM.
 */
class SetupEconomicOperatorRequestTest extends AbstractSelectionCriteriaFixture {

    def "10. should contain the 'Set up of economic operator' criterion"() {
        given:
        def espd = new EspdDocument(setupEconomicOperator: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.SETUP_ECONOMIC_OPERATOR)

        then: "CriterionID element"
        checkCriterionId(request, idx, "77f481ce-ffb6-483f-8e2b-c78db5e68292")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Set up of economic operator"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "In case the information concerning turnover (general or specific) is not available for the entire period required, please state the date on which the economic operator was set up or started trading:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "e9aa7763-c167-4352-8060-1a3d7d3e2662"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "a18b2c98-8552-45ca-9751-d4c94c05847a", "Please specify", "QUANTITY_YEAR")
    }

}