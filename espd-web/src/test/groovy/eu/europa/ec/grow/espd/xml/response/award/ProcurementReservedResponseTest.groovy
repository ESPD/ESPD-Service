/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml.response.award

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 * Created by ratoico on 1/20/16 at 10:48 AM.
 */
class ProcurementReservedResponseTest extends AbstractCriteriaFixture {

    def "01. should contain the 'Only in case the procurement is reserved' criterion"() {
        given:
        // exists is false but award criteria should always be present
        def espd = new EspdDocument(procurementReserved: new eu.europa.ec.grow.espd.domain.OtherCriterion(exists: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(OtherCriterion.PROCUREMENT_RESERVED)

        then: "CriterionID element"
        checkCriterionId(response, idx, "2043338f-a38a-490b-b3ec-2607cb25a017")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.OTHER.EO_DATA.SHELTERED_WORKSHOP")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Procurement reserved"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "Only in case the procurement is reserved: is the economic operator a sheltered workshop, a 'social business' or will it provide for the performance of the contract in the context of sheltered employment programmes?"

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 1

        then: "G1"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "6febbe4a-e715-427c-a2b1-19cfabadaef0"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "7f18c64e-ae09-4646-9400-f3666d50af51", "Your answer", "INDICATOR")

        then: "G1.1"
        def g1_1 = g1.RequirementGroup[0]
        g1_1.ID.text() == "a5e33369-e2b5-45f7-9969-ddb1c3ae17c8"
        g1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g1_1.RequirementGroup.size() == 0
        g1_1.Requirement.size() == 2
        checkRequirement(g1_1.Requirement[0], "4e552658-d532-4770-943b-b90efcc9788d", "What is the corresponding percentage of disabled or disadvantaged workers?", "PERCENTAGE")
        checkRequirement(g1_1.Requirement[1], "e01d0929-c7a9-455a-aaf9-e1f7cd966336",
                "If required, please provide details on whether the employees concerned belong to a specific category of disabled or disadvantaged workers?", "DESCRIPTION")
    }

    def "check the 'Indicator' requirement response"() {
        given:
        def espd = new EspdDocument(procurementReserved: new eu.europa.ec.grow.espd.domain.OtherCriterion(exists: true, answer: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(OtherCriterion.PROCUREMENT_RESERVED)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "7f18c64e-ae09-4646-9400-f3666d50af51", "Your answer", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'What is the corresponding percentage of disabled or disadvantaged workers?' requirement response"() {
        given:
        def espd = new EspdDocument(procurementReserved: new eu.europa.ec.grow.espd.domain.OtherCriterion(exists: true,
                doubleValue1: 33.11))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(OtherCriterion.PROCUREMENT_RESERVED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "4e552658-d532-4770-943b-b90efcc9788d", "What is the corresponding percentage of disabled or disadvantaged workers?", "PERCENTAGE")
        req.Response.size() == 1
        req.Response[0].Percent.text() == "33.11"
    }

    def "check the 'If required, please provide details' requirement response"() {
        given:
        def espd = new EspdDocument(procurementReserved: new eu.europa.ec.grow.espd.domain.OtherCriterion(exists: true,
                description1: "Here are some additional details"))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(OtherCriterion.PROCUREMENT_RESERVED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "e01d0929-c7a9-455a-aaf9-e1f7cd966336",
                "If required, please provide details on whether the employees concerned belong to a specific category of disabled or disadvantaged workers?", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "Here are some additional details"
    }

}