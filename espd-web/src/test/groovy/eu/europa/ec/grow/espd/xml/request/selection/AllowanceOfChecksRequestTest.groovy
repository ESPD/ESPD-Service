package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class AllowanceOfChecksRequestTest extends AbstractSelectionCriteriaFixture {

    def "21. should contain the 'Allowance of checks' criterion"() {
        given:
        def espd = new EspdDocument(allowanceOfChecks: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "c8809aa1-29b6-4f27-ae2f-27e612e394db")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Allowance of checks"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "For complex products or services to be supplied or, exceptionally, for products or services which are required for a special purpose: The economic operator will allow checks  to be conducted on the production capacities or the technical capacity of the economic operator and, where necessary, on the means of study and research which are available to it and on the quality control measures? The check is to be performed by the contracting authority or, in case the latter consents to this, on its behalf by a competent official body of the country in which the supplier or service provider is established."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "d7721546-9106-43a7-8d31-2fe08a862b00"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}