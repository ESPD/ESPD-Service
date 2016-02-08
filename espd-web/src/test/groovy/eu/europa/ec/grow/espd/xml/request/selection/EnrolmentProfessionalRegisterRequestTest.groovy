package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SuitabilityCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class EnrolmentProfessionalRegisterRequestTest extends AbstractSelectionCriteriaFixture {

    def "02. should contain the 'Enrolment in a relevant professional register' criterion"() {
        given:
        def espd = new EspdDocument(enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.ENROLMENT_PROFESSIONAL_REGISTER)

        then: "CriterionID element"
        checkCriterionId(request, idx, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.SUITABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Enrolment in a relevant professional register"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "It is enrolled in relevant professional registers kept in the Member State of its establishment as described in Annex XI of Directive 2014/24/EU; economic operators from certain Member States may have to comply with other requirements set out in that Annex."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(2)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "1768de86-a6c8-48e4-bd8e-de2f2f7424d0"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}