package eu.europa.ec.grow.espd.xml.request.exclusion
import eu.europa.ec.grow.espd.domain.BankruptcyCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:15 PM.
 */
class BankruptcyRequestTest extends AbstractExclusionCriteriaFixture {

    def "12. should contain the 'Bankruptcy' criterion"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "d3732c09-7d62-4edc-a172-241da6636e7c")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.BANKRUPTCY_INSOLVENCY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Bankruptcy"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Is the economic operator bankrupt? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "d91c11a1-f19e-4b83-8ade-c4be2bf00555"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 3

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")

        def r1_2 = request.Criterion[idx].RequirementGroup[0].Requirement[2]
        checkRequirement(r1_2, "4e3f468a-86c4-4c99-bd15-c8b221229348", "Indicate reasons for being nevertheless to perform the contract", "DESCRIPTION")

        then: "check second sub group"
        def sub2 = request.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(sub2)
    }

}