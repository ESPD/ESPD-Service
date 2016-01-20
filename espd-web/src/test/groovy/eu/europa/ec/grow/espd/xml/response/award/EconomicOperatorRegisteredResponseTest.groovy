package eu.europa.ec.grow.espd.xml.response.award

import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractAwardCriteriaFixture

/**
 * Created by ratoico on 1/20/16 at 11:22 AM.
 */
class EconomicOperatorRegisteredResponseTest extends AbstractAwardCriteriaFixture {

    def "02. should contain the 'If applicable, is the economic operator registered' criterion"() {
        given:
        def espd = new EspdDocument(eoRegistered: new AwardCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_REGISTERED)

        then: "CriterionID element"
        response.Criterion.size() == getTotalNumberOfCriteria()
        checkCriterionId(response, idx, "9b19e869-6c89-4cc4-bd6c-ac9ca8602165")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "DATA_ON_ECONOMIC_OPERATOR")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "If applicable, is the economic operator registered on an official list of approved economic operators or does it have an equivalent certificate (e.g. under a national (pre)qualification system)?"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == ""

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        response.Criterion[idx].RequirementGroup[0].ID.text() == "64162276-7014-408f-a9af-080426bfe1fd"
        response.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 1
        response.Criterion[idx].RequirementGroup[0].Requirement.size() == 4

        then: "first sub group requirements"
        def r1_0 = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")

        def r1_1 = response.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "30064ad3-fc11-4579-8528-fdd0b9a5ba75", "a) Please provide the relevant registration or certification number, if applicable:", "DESCRIPTION")

        def r1_2 = response.Criterion[idx].RequirementGroup[0].Requirement[2]
        checkRequirement(r1_2, "b3403349-cbc0-4d84-879e-fc0f2d90ecbd",
                "b) If the certificate of registration or certification is available electronically, please state:", "DESCRIPTION")

        def r1_3 = response.Criterion[idx].RequirementGroup[0].Requirement[3]
        checkRequirement(r1_3, "792ff522-6f3f-4a62-ab6e-a8b272bc290e",
                "c) Please state the references on which the registration or certification is based, and, where applicable, the classification obtained in the official list:", "DESCRIPTION")

        then: "first sub group sub group"
        response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup.size() == 0
        response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].Requirement.size() == 1
        def r1_1_0 = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_1_0, "d9996ef5-49f9-4cf8-a2f5-31c9f4efd894", "d) Does the registration or certification cover all of the required selection criteria?", "INDICATOR")

        then: "second subgroup"
        response.Criterion[idx].RequirementGroup[1].ID.text() == "59e6f3ef-15cd-4e21-82ac-ea497ccd44e2"
        response.Criterion[idx].RequirementGroup[1].RequirementGroup.size() == 0
        response.Criterion[idx].RequirementGroup[1].Requirement.size() == 2

        then: "second subgroup requirements"
        def r2_0 = response.Criterion[idx].RequirementGroup[1].Requirement[0]
        checkRequirement(r2_0, "0e71abd3-198e-49c5-8128-5708617bb191",
                "e) Will the economic operator be able to provide a certificate with regard to the payment of social security contributions and taxes or provide information enabling the contracting authority or contracting entity to obtaining it directly by accessing a national database in any Member State that is available free of charge?",
                "DESCRIPTION")

        def r2_1 = response.Criterion[idx].RequirementGroup[1].Requirement[1]
        checkRequirement(r2_1, "caa72cea-5443-49fb-84ba-ab6c64427f77",
                "If the relevant documentation is available electronically, please indicate:", "DESCRIPTION")
    }

    def "check the 'Indicator' requirement response"() {
        given:
        def espd = new EspdDocument(eoRegistered: new AwardCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_REGISTERED)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'a) Please provide the relevant registration or certification number' requirement response"() {
        given:
        def espd = new EspdDocument(eoRegistered: new AwardCriterion(exists: true, description1: "descr 1"))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_REGISTERED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "30064ad3-fc11-4579-8528-fdd0b9a5ba75", "a) Please provide the relevant registration or certification number, if applicable:", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 1"
    }

    def "check the 'b) If the certificate of registration or certification is available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(eoRegistered: new AwardCriterion(exists: true, description2: "descr 2"))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_REGISTERED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[2]
        checkRequirement(req, "b3403349-cbc0-4d84-879e-fc0f2d90ecbd",
                "b) If the certificate of registration or certification is available electronically, please state:", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 2"
    }

    def "check the 'c) Please state the references on which the registration or certification is based, and, where applicable, the classification obtained in the official list:' requirement response"() {
        given:
        def espd = new EspdDocument(eoRegistered: new AwardCriterion(exists: true, description3: "descr 3"))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_REGISTERED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[3]
        checkRequirement(req, "792ff522-6f3f-4a62-ab6e-a8b272bc290e",
                "c) Please state the references on which the registration or certification is based, and, where applicable, the classification obtained in the official list:", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 3"
    }

    def "check the 'd) Does the registration or certification cover all of the required selection criteria?' requirement response"() {
        given:
        def espd = new EspdDocument(eoRegistered: new AwardCriterion(exists: true, booleanValue1: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_REGISTERED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "d9996ef5-49f9-4cf8-a2f5-31c9f4efd894",
                "d) Does the registration or certification cover all of the required selection criteria?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'e) Will the economic operator be able to provide a certificate with regard to the payment of social security contributions' requirement response"() {
        given:
        def espd = new EspdDocument(eoRegistered: new AwardCriterion(exists: true, description4: "descr 4"))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_REGISTERED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "0e71abd3-198e-49c5-8128-5708617bb191",
                "e) Will the economic operator be able to provide a certificate with regard to the payment of social security contributions and taxes or provide information enabling the contracting authority or contracting entity to obtaining it directly by accessing a national database in any Member State that is available free of charge?",
                "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 4"
    }

    def "check the 'If the relevant documentation is available electronically, please indicate:' requirement response"() {
        given:
        def espd = new EspdDocument(eoRegistered: new AwardCriterion(exists: true, description5: "descr 5"))

        when:
        def response = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.EO_REGISTERED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "caa72cea-5443-49fb-84ba-ab6c64427f77",
                "If the relevant documentation is available electronically, please indicate:", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 5"
    }

}