package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class AverageAnnualManpowerRequestTest extends AbstractSelectionCriteriaFixture {

    def "25. should contain the 'Average annual manpower' criterion"() {
        given:
        def espd = new EspdDocument(averageAnnualManpower: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.AVERAGE_ANNUAL_MANPOWER)

        then: "CriterionID element"
        request.Criterion.size() == getTotalNumberOfCriteria()
        checkCriterionId(request, idx, "1f49b3f0-d50f-43f6-8b30-4bafab108b9b")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Average annual manpower"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator’s average annual manpower for the last three years were as follows:"

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