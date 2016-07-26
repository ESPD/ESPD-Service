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

package eu.europa.ec.grow.espd.xml.request.award

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 * Created by ratoico on 5/26/16.
 */
class EconomicOperatorRegisteredRequestTest extends AbstractCriteriaFixture {

    def "02. should contain the 'If applicable, is the economic operator registered' criterion"() {
        given:
        // exists is false but award criteria should always be present
        def espd = new EspdDocument(eoRegistered: new eu.europa.ec.grow.espd.domain.OtherCriterion(exists: false))

        when:
        def request = parseRequestXml(espd)
        def idx = getEoCriterionIndex(OtherCriterion.EO_REGISTERED)

        then: "CriterionID element"
        checkCriterionId(request, idx, "9b19e869-6c89-4cc4-bd6c-ac9ca8602165")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "CRITERION.OTHER.EO_DATA.REGISTERED_IN_OFFICIAL_LIST")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "EO registered"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "If applicable, is the economic operator registered on an official list of approved economic operators or does it have an equivalent certificate (e.g. under a national (pre)qualification system)?"

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "G1"
        def g1 = request.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "64162276-7014-408f-a9af-080426bfe1fd"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "67fd1dde-2a0a-486e-9469-79c78796fc22", "Not applicable", "INDICATOR")

        then: "G1.1"
        def g1_1 = g1.RequirementGroup[0]
        g1_1.ID.text() == "ecb5127b-9018-4fb8-8327-a6a7a2c73195"
        g1_1.@pi.text() == "GROUP_FULFILLED.ON_FALSE"
        g1_1.RequirementGroup.size() == 2
        g1_1.Requirement.size() == 1
        checkRequirement(g1_1.Requirement[0], "7f18c64e-ae09-4646-9400-f3666d50af51", "Your answer", "INDICATOR")

        then: "G1.1.1"
        def g1_1_1 = g1_1.RequirementGroup[0]
        g1_1_1.ID.text() == "dc4acf0c-c761-40d0-b031-4ee1f224be5c"
        g1_1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g1_1_1.RequirementGroup.size() == 1
        g1_1_1.Requirement.size() == 3
        checkRequirement(g1_1_1.Requirement[0], "30064ad3-fc11-4579-8528-fdd0b9a5ba75", "a) Please provide the relevant registration or certification number, if applicable:", "DESCRIPTION")
        checkRequirement(g1_1_1.Requirement[1], "b3403349-cbc0-4d84-879e-fc0f2d90ecbd",
                "b) If the certificate of registration or certification is available electronically, please state:", "DESCRIPTION")
        checkRequirement(g1_1_1.Requirement[2], "792ff522-6f3f-4a62-ab6e-a8b272bc290e",
                "c) Please state the references on which the registration or certification is based, and, where applicable, the classification obtained in the official list:", "DESCRIPTION")

        then: "G1.1.1.1"
        def g1_1_1_1 = g1_1_1.RequirementGroup[0]
        g1_1_1_1.ID.text() == "92e44d3b-af8e-4a29-91a8-24d27aa27fee"
        g1_1_1_1.RequirementGroup.size() == 0
        g1_1_1_1.Requirement.size() == 1
        checkRequirement(g1_1_1_1.Requirement[0], "d9996ef5-49f9-4cf8-a2f5-31c9f4efd894", "d) Does the registration or certification cover all of the required selection criteria?", "INDICATOR")

        then: "G1.1.2"
        def g1_1_2 = g1_1.RequirementGroup[1]
        g1_1_2.ID.text() == "59e6f3ef-15cd-4e21-82ac-ea497ccd44e2"
        g1_1_2.@pi.text() == "GROUP_FULFILLED.ON_FALSE"
        g1_1_2.RequirementGroup.size() == 0
        g1_1_2.Requirement.size() == 2
        checkRequirement(g1_1_2.Requirement[0], "0e71abd3-198e-49c5-8128-5708617bb191",
                "e) Will the economic operator be able to provide a certificate with regard to the payment of social security contributions and taxes or provide information enabling the contracting authority or contracting entity to obtaining it directly by accessing a national database in any Member State that is available free of charge?",
                "INDICATOR")
        checkRequirement(g1_1_2.Requirement[1], "caa72cea-5443-49fb-84ba-ab6c64427f77",
                "If the relevant documentation is available electronically, please indicate:", "DESCRIPTION")
    }

}