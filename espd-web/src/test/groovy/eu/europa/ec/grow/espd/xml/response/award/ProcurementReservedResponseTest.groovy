package eu.europa.ec.grow.espd.xml.response.award
import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 * Created by ratoico on 1/20/16 at 10:48 AM.
 */
class ProcurementReservedResponseTest extends AbstractCriteriaFixture {

    def "01. should contain the 'Only in case the procurement is reserved' criterion"() {
        given:
        // exists is false but award criteria should always be present
        def espd = new EspdDocument(procurementReserved: new AwardCriterion(exists: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.PROCUREMENT_RESERVED)

        then: "CriterionID element"
        checkCriterionId(response, idx, "2043338f-a38a-490b-b3ec-2607cb25a017")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "DATA_ON_ECONOMIC_OPERATOR")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Only in case the procurement is reserved: is the economic operator a sheltered workshop, a 'social business' or will it provide for the performance of the contract in the context of sheltered employment programmes?"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == ""

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        response.Criterion[idx].RequirementGroup[0].ID.text() == "6febbe4a-e715-427c-a2b1-19cfabadaef0"
        response.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        response.Criterion[idx].RequirementGroup[0].Requirement.size() == 3

        then: "main sub group requirements"
        def r1_0 = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")

        def r1_1 = response.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "4e552658-d532-4770-943b-b90efcc9788d", "What is the corresponding percentage of disabled or disadvantaged workers?", "PERCENTAGE")

        def r1_2 = response.Criterion[idx].RequirementGroup[0].Requirement[2]
        checkRequirement(r1_2, "e01d0929-c7a9-455a-aaf9-e1f7cd966336",
                "If required, please provide details on whether the employees concerned belong to a specific category of disabled or disadvantaged workers?", "DESCRIPTION")
    }

    def "check the 'Indicator' requirement response"() {
        given:
        def espd = new EspdDocument(procurementReserved: new AwardCriterion(exists: true, answer: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.PROCUREMENT_RESERVED)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'What is the corresponding percentage of disabled or disadvantaged workers?' requirement response"() {
        given:
        def espd = new EspdDocument(procurementReserved: new AwardCriterion(exists: true,
                doubleValue1: 33.11))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.PROCUREMENT_RESERVED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "4e552658-d532-4770-943b-b90efcc9788d", "What is the corresponding percentage of disabled or disadvantaged workers?", "PERCENTAGE")
        req.Response.size() == 1
        req.Response[0].Percent.text() == "33.11"
    }

    def "check the 'If required, please provide details' requirement response"() {
        given:
        def espd = new EspdDocument(procurementReserved: new AwardCriterion(exists: true,
                description1: "Here are some additional details"))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.PROCUREMENT_RESERVED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[2]
        checkRequirement(req, "e01d0929-c7a9-455a-aaf9-e1f7cd966336",
                "If required, please provide details on whether the employees concerned belong to a specific category of disabled or disadvantaged workers?", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "Here are some additional details"
    }

}