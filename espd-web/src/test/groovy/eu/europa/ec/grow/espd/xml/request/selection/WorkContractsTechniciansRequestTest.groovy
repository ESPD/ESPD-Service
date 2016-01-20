package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class WorkContractsTechniciansRequestTest extends AbstractSelectionCriteriaFixture {

    def "17. should contain the 'For works contracts: technicians or technical bodies to carry out the work' criterion"() {
        given:
        def espd = new EspdDocument(workContractsTechnicians: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES)

        then: "CriterionID element"
        request.Criterion.size() == getRequestNumberOfCriteria()
        checkCriterionId(request, idx, "c599c130-b29f-461e-a187-4e16c7d40db7")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For works contracts: technicians or technical bodies to carry out the work"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "In the case of public works contracts, the economic operator will be able to call on the following technicians or technical bodies to carry out the work:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "162843ae-aa63-47ab-9b05-e5e3e0f284ff"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "3aaca389-4a7b-406b-a4b9-080845d127e7", "Please specify", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}