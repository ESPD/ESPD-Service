package eu.europa.ec.grow.espd.xml.response.award

import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractAwardCriteriaFixture
/**
 * Created by ratoico on 1/20/16 at 11:57 AM.
 */
class EconomicOperatorParticipatingProcurementProcedureResponseTest extends AbstractAwardCriteriaFixture {

    def "03. should contain the 'Is the economic operator participating in the procurement procedure together with others?' criterion"() {
        given:
        def espd = new EspdDocument(eoParticipatingProcurementProcedure: new AwardCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_PARTICIPATING_PROCUREMENT_PROCEDURE)

        then: "CriterionID element"
        response.Criterion.size() == getTotalNumberOfCriteria()
        checkCriterionId(response, idx, "ee51100f-8e3e-40c9-8f8b-57d5a15be1f2")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "DATA_ON_ECONOMIC_OPERATOR")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Is the economic operator participating in the procurement procedure together with others?"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == ""

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        response.Criterion[idx].RequirementGroup[0].ID.text() == "d939f2c6-ba25-4dc4-889c-11d1853add19"
        response.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        response.Criterion[idx].RequirementGroup[0].Requirement.size() == 4

        then: "first sub group requirements"
        def r1_0 = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")

        def r1_1 = response.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "907fd62b-02f1-452c-81a8-785bedb0c536", "a) Please indicate the role of the economic operator in the group (leader, responsible for specific tasks...):", "DESCRIPTION")

        def r1_2 = response.Criterion[idx].RequirementGroup[0].Requirement[2]
        checkRequirement(r1_2, "7c267f95-a3a7-49ef-abd9-e121dcd641a9",
                "b) Please identify the other economic operators participating in the procurement procedure together:", "DESCRIPTION")

        def r1_3 = response.Criterion[idx].RequirementGroup[0].Requirement[3]
        checkRequirement(r1_3, "96f38793-4469-4153-aba6-c613282cdbdc",
                "c) Where applicable, name of the participating group:", "DESCRIPTION")

    }

    def "check the 'Indicator' requirement response"() {
        given:
        def espd = new EspdDocument(eoParticipatingProcurementProcedure: new AwardCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_PARTICIPATING_PROCUREMENT_PROCEDURE)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'a) Please indicate the role of the economic operator in the group' requirement response"() {
        given:
        def espd = new EspdDocument(eoParticipatingProcurementProcedure: new AwardCriterion(exists: true, description1: "descr 1"))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_PARTICIPATING_PROCUREMENT_PROCEDURE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "907fd62b-02f1-452c-81a8-785bedb0c536", "a) Please indicate the role of the economic operator in the group (leader, responsible for specific tasks...):", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 1"
    }

    def "check the 'b) Please identify the other economic operators participating in the procurement procedure' requirement response"() {
        given:
        def espd = new EspdDocument(eoParticipatingProcurementProcedure: new AwardCriterion(exists: true, description2: "descr 2"))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_PARTICIPATING_PROCUREMENT_PROCEDURE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[2]
        checkRequirement(req, "7c267f95-a3a7-49ef-abd9-e121dcd641a9",
                "b) Please identify the other economic operators participating in the procurement procedure together:", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 2"
    }

    def "check the 'c) Where applicable, name of the participating group:' requirement response"() {
        given:
        def espd = new EspdDocument(eoParticipatingProcurementProcedure: new AwardCriterion(exists: true, description3: "descr 3"))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_PARTICIPATING_PROCUREMENT_PROCEDURE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[3]
        checkRequirement(req, "96f38793-4469-4153-aba6-c613282cdbdc",
                "c) Where applicable, name of the participating group:", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 3"
    }

}