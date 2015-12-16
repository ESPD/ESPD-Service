package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 1:18 PM.
 */
class AnalogousSituationRequestTest extends AbstractRequestExclusionFixture {

    def "13. should contain the 'Analogous situation like bankruptcy under national law' criterion"() {
        given:
        def espd = new EspdDocument(analogousSituation: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "daffa2a9-9f8f-4568-8be8-7b8bf306d096")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.BANKRUPTCY_INSOLVENCY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Analogous situation like bankruptcy under national law"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Is the economic operator in in any analogous situation like bankruptcy arising from a similar procedure under national laws and regulations? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "Your answer"
        request.Criterion[idx].Requirement.size() == 1
        checkRequirement(request.Criterion[idx].Requirement[0], "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "CRITERION_INDICATOR")

        then: "check all the sub groups"
        request.Criterion[idx].SubCriterion.size() == 2

        then: "main sub group"
        request.Criterion[idx].SubCriterion[0].ID.text() == "8dea9e4d-0e51-4851-8942-a26a83c19e02"
        request.Criterion[idx].SubCriterion[0].SubCriterion.size() == 0
        request.Criterion[idx].SubCriterion[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_1 = request.Criterion[idx].SubCriterion[0].Requirement[0]
        checkRequirement(r1_1, "7b07904f-e080-401a-a3a1-9a3efeeda54b", "Please describe them", "DESCRIPTION")

        def r1_2 = request.Criterion[idx].SubCriterion[0].Requirement[1]
        checkRequirement(r1_2, "4e3f468a-86c4-4c99-bd15-c8b221229348", "Indicate reasons for being nevertheless to perform the contract", "DESCRIPTION")

        then: "check second sub group"
        def sub2 = request.Criterion[idx].SubCriterion[1]
        checkInfoAvailableElectronicallySubCriterion(sub2)
    }

}