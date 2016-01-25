package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class NumberOfManagerialStaffRequestTest extends AbstractSelectionCriteriaFixture {

    def "25. should contain the 'Number of managerial staff' criterion"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then: "CriterionID element"
        request.Criterion.size() == getRequestNumberOfCriteria()
        checkCriterionId(request, idx, "6346959b-e097-4ea1-89cd-d1b4c131ea4d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Number of managerial staff"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator’s number of managerial staff for the last three years were as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 4

        then:
        checkYearNumberGroup1(request.Criterion[idx].RequirementGroup[0])
        checkYearNumberGroup2(request.Criterion[idx].RequirementGroup[1])
        checkYearNumberGroup3(request.Criterion[idx].RequirementGroup[2])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[3])
    }

}