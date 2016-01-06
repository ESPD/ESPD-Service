package eu.europa.ec.grow.espd.xml.request.selection
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SatisfiesAllCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 1/6/16 at 3:26 PM.
 */
class AllCriteriaSatisfiedRequestTest extends AbstractSelectionCriteriaFixture {

    def "01. should contain the 'All selection criteria will be satisfied' criterion"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true))
        def idx = 0

        when:
        def request = parseRequestXml(espd)
        def file = new File("/home/ratoico/Downloads/espd-request.xml")
        file.text = xmlOutput

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "7e7db838-eeac-46d9-ab39-42927486f22d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ALL_CRITERIA_SATISFIED")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "All selection criteria will be satisfied"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice."

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "f3a6836d-2de2-4cd1-81ca-fb06178d05c5"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

    }

}