package eu.europa.ec.grow.espd.xml.response.award
import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 * Created by ratoico on 1/20/16 at 1:24 PM.
 */
class EconomicOperatorReliesCapacitiesResponseTest extends AbstractCriteriaFixture {

    def "04. should contain the 'Does the economic operator rely on the capacities of other entities' criterion"() {
        given:
        // exists is false but award criteria should always be present
        def espd = new EspdDocument(eoReliesCapacities: new AwardCriterion(exists: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.EO_RELIES_CAPACITIES)

        then: "CriterionID element"
        checkCriterionId(response, idx, "0d62c6ed-f074-4fcf-8e9f-f691351d52ad")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "DATA_ON_ECONOMIC_OPERATOR")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Does the economic operator rely on the capacities of other entities in order to meet the selection criteria set out under Part IV and the criteria and rules (if any) set out under Part V below?"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == ""

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        response.Criterion[idx].RequirementGroup[0].ID.text() == "e688f7d6-dcef-4726-bc61-052e63ead60f"
        response.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        response.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "first sub group requirements"
        def r1_0 = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")
    }

    def "check the 'Indicator' requirement response"() {
        given:
        def espd = new EspdDocument(eoReliesCapacities: new AwardCriterion(exists: true, answer: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.EO_RELIES_CAPACITIES)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

}