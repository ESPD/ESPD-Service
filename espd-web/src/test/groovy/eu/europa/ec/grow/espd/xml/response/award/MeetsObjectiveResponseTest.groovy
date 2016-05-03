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
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 * Created by ratoico on 1/20/16 at 1:26 PM.
 */
class MeetsObjectiveResponseTest extends AbstractCriteriaFixture {

    def "05. should contain the 'If applicable, is the economic operator registered' criterion"() {
        given:
        // exists is false but award criteria should always be present
        def espd = new EspdDocument(meetsObjective: new AwardCriterion(exists: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.MEETS_OBJECTIVE)

        then: "CriterionID element"
        checkCriterionId(response, idx, "9c70375e-1264-407e-8b50-b9736bc08901")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "REDUCTION_OF_CANDIDATES")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "It meets the objective and non discriminatory criteria or rules to be applied in order to limit the number of candidates in the following way: In case certain certificates or other forms of documentary evidence are required, please indicate for each whether the economic operator has the required documents:"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "If some of these certificates or forms of documentary evidence are available electronically, please indicate for each:"

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        response.Criterion[idx].RequirementGroup[0].ID.text() == "3e5c2859-68a7-4312-92e4-01ae79c00cb8"
        response.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        response.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "first sub group requirements"
        def r1_0 = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")

        def r1_1 = response.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "323f19b5-3308-4873-b2d1-767963cc81e9", "Please describe them", "DESCRIPTION")

        then: "second subgroup"
        response.Criterion[idx].RequirementGroup[1].ID.text() == "ab335516-73a4-41f7-977b-a98c13a51060"
        response.Criterion[idx].RequirementGroup[1].RequirementGroup.size() == 0
        response.Criterion[idx].RequirementGroup[1].Requirement.size() == 3

        then: "second subgroup requirements"
        def r2_0 = response.Criterion[idx].RequirementGroup[1].Requirement[0]
        checkRequirement(r2_0, "0622bbd1-7378-45e1-8fb9-25429740ac22",
                "Is this information available electronically?", "INDICATOR")

        def r2_1 = response.Criterion[idx].RequirementGroup[1].Requirement[1]
        checkRequirement(r2_1, "ee1ee1cd-3791-4855-8b8b-28d4f4c5c007", "URL", "EVIDENCE_URL")

        def r2_2 = response.Criterion[idx].RequirementGroup[1].Requirement[2]
        checkRequirement(r2_2, "1e55ff14-c643-4abc-91d7-2f4dfcdf2409", "Code", "CODE")
    }

    def "check the 'Indicator' requirement response"() {
        given:
        def espd = new EspdDocument(meetsObjective: new AwardCriterion(exists: true, answer: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.MEETS_OBJECTIVE)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(meetsObjective: new AwardCriterion(exists: true, description1: "descr 1"))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.MEETS_OBJECTIVE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "323f19b5-3308-4873-b2d1-767963cc81e9", "Please describe them", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "descr 1"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(meetsObjective: new AwardCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.MEETS_OBJECTIVE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(meetsObjective: new AwardCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_07.com")))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.MEETS_OBJECTIVE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_07.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(meetsObjective: new AwardCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_05")))

        when:
        def response = parseResponseXml(espd)
        def idx = getEoCriterionIndex(eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.MEETS_OBJECTIVE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_05"
    }

}