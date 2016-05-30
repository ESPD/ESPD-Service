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

package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class FinancialRatioResponseTest extends AbstractSelectionCriteriaFixture {

    def "11. should contain the 'Financial ratio' criterion"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then: "CriterionID element"
        checkCriterionId(response, idx, "e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Financial ratio"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "Concerning the financial ratios  specified in the relevant notice, the procurement documents or the ESPD, the economic operator declares that the actual values for the required ratios are as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(3)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "cf00f7bb-c2cf-4565-91bb-221d78d8dd2f"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 5
        g1.Requirement.size() == 1

        then: "In the financial ratios a requirement is required previous to a subgroup of requirements so we add a dummy sentence like 'please provide the requested data below'"
        checkRequirement(g1.Requirement[0], "3a6fefd4-f458-4d43-97fb-0725fce5dce2", "Please provide the requested data below", "DESCRIPTION")

        then: "check description ratio subgroups"
        checkDescriptionRatioGroup1(g1.RequirementGroup[0])
        checkDescriptionRatioGroup2(g1.RequirementGroup[1])
        checkDescriptionRatioGroup3(g1.RequirementGroup[2])
        checkDescriptionRatioGroup4(g1.RequirementGroup[3])
        checkDescriptionRatioGroup5(g1.RequirementGroup[4])

        then: "info available electronically sub group"
        def g2 = response.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(g2)
    }

    def "check the 'Description' requirements response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                description1: "d1", description2: "d2", description3: "d3", description4: "d4", description5: "d5"))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)
        def g1 = response.Criterion[idx].RequirementGroup[0]

        then: "Description 1"
        def subGroup1 = g1.RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Description.text() == "d1"

        then: "Description 2"
        def subGroup2 = g1.RequirementGroup[1]
        def req2 = subGroup2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Description.text() == "d2"

        then: "Description 3"
        def subGroup3 = g1.RequirementGroup[2]
        def req3 = subGroup3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Description.text() == "d3"

        then: "Description 4"
        def subGroup4 = g1.RequirementGroup[3]
        def req4 = subGroup4.Requirement[0]
        req4.Response.size() == 1
        req4.Response[0].Description.text() == "d4"

        then: "Description 5"
        def subGroup5 = g1.RequirementGroup[4]
        def req5 = subGroup5.Requirement[0]
        req5.Response.size() == 1
        req5.Response[0].Description.text() == "d5"
    }

    def "check the 'Ratio' requirements response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                ratio1: 11.1, ratio2: 22.2, ratio3: 33.3, ratio4: 44.4, ratio5: 55.5))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)
        def g1 = response.Criterion[idx].RequirementGroup[0]

        then: "Ratio 1"
        def subGroup1 = g1.RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response[0].Quantity.text() == "11.1"

        then: "Ratio 2"
        def subGroup2 = g1.RequirementGroup[1]
        def req2 = subGroup2.Requirement[1]
        req2.Response.size() == 1
        req2.Response[0].Quantity.text() == "22.2"

        then: "Ratio 3"
        def subGroup3 = g1.RequirementGroup[2]
        def req3 = subGroup3.Requirement[1]
        req3.Response.size() == 1
        req3.Response[0].Quantity.text() == "33.3"

        then: "Ratio 4"
        def subGroup4 = g1.RequirementGroup[3]
        def req4 = subGroup4.Requirement[1]
        req4.Response.size() == 1
        req4.Response[0].Quantity.text() == "44.4"

        then: "Ratio 5"
        def subGroup5 = g1.RequirementGroup[4]
        def req5 = subGroup5.Requirement[1]
        req5.Response.size() == 1
        req5.Response[0].Quantity.text() == "55.5"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_10.com")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_10.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_10")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.FINANCIAL_RATIO)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_10"
    }

}