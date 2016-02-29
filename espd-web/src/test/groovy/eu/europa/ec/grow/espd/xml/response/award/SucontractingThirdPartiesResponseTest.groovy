package eu.europa.ec.grow.espd.xml.response.award

import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 * Created by ratoico on 2/29/16 at 11:53 AM.
 */
class SucontractingThirdPartiesResponseTest extends AbstractCriteriaFixture {

    def "06. should contain the 'Does the economic operator intend to subcontract any share of the contract to third parties' criterion"() {
        given:
        // exists is false but award criteria should always be present
        def espd = new EspdDocument(subcontractingThirdParties: new AwardCriterion(exists: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.SUBCONTRACTING_THIRD_PARTIES)

        then: "CriterionID element"
        checkCriterionId(response, idx, "72c0c4b1-ca50-4667-9487-461f3eed4ed7")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "DATA_ON_ECONOMIC_OPERATOR")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Does the economic operator intend to subcontract any share of the contract to third parties?"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == ""

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        response.Criterion[idx].RequirementGroup[0].ID.text() == "d5fe5a71-7fd3-4910-b6f4-5cd2a4d23524"
        response.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        response.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "first sub group requirements"
        def r1_0 = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")

        def r1_1 = response.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "999c7fe2-61cd-4e86-b76f-e280304dc8c9", "If yes and in so far as known, please list the proposed subcontractors:", "DESCRIPTION")
    }

    def "check the 'Indicator' requirement response"() {
        given:
        def espd = new EspdDocument(subcontractingThirdParties: new AwardCriterion(exists: true, answer: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.SUBCONTRACTING_THIRD_PARTIES)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(subcontractingThirdParties: new AwardCriterion(exists: true, description1: "descr 1"))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.SUBCONTRACTING_THIRD_PARTIES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "999c7fe2-61cd-4e86-b76f-e280304dc8c9", "If yes and in so far as known, please list the proposed subcontractors:", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 1"
    }

}