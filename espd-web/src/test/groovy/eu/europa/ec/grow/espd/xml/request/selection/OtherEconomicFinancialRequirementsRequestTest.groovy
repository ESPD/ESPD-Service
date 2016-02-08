package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class OtherEconomicFinancialRequirementsRequestTest extends AbstractSelectionCriteriaFixture {

    def "13. should contain the 'Other economic or financial requirements' criterion"() {
        given:
        def espd = new EspdDocument(otherEconomicFinancialRequirements: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS)

        then: "CriterionID element"
        checkCriterionId(request, idx, "ab0e7f2e-6418-40e2-8870-6713123e41ad")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Other economic or financial requirements"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Concerning the other economic or financial requirements, if any, that may have been specified in the relevant notice or the procurement documents, the economic operator declares that:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "488ca189-bcdb-4bf4-80c7-3ad507fd89fb"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}