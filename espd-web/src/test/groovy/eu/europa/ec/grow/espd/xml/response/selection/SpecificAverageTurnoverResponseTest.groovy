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
class SpecificAverageTurnoverResponseTest extends AbstractSelectionCriteriaFixture {

    def "09. should contain the 'Specific average turnover' criterion"() {
        given:
        def espd = new EspdDocument(specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SPECIFIC_AVERAGE_TURNOVER)

        then: "CriterionID element"
        checkCriterionId(response, idx, "d3dfb714-f558-4512-bbc5-e456fa2339de")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.SELECTION.ECONOMIC_FINANCIAL_STANDING.TURNOVER.SPECIFIC_AVERAGE")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Specific average turnover"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "Its specific average yearly turnover in the business area covered by the contract for the number of years required in the relevant notice, the procurement documents or the ESPD is as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(3)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "G1"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "e1886054-ada4-473c-9afc-2fde82c24cf4"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 2
        checkRequirement(g1.Requirement[0], "b98ffd05-6572-4b07-a521-693a1754ed46", "Number of years", "QUANTITY_INTEGER")
        checkRequirement(g1.Requirement[1], "217637ba-6bdb-4c73-a38f-27fe0e71d9be", "Average turnover", "AMOUNT")

        then: "info available electronically sub group"
        def g2 = response.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(g2)
    }

    def "check the 'Number of years' requirement response"() {
        given:
        def espd = new EspdDocument(specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true,
                answer: true, numberOfYears: 32))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SPECIFIC_AVERAGE_TURNOVER)

        then:
        def g1 = response.Criterion[idx].RequirementGroup[0]
        def req = g1.Requirement[0]
        req.Response[0].Quantity.text() == "32"
        req.Response[0].Quantity.@unitCode.text() == "NUMBER"
    }

    def "check the 'Average turnover' requirements response"() {
        given:
        def espd = new EspdDocument(specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true,
                averageTurnover: 11.1123, averageTurnoverCurrency: "RON"))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)
        def g1 = response.Criterion[idx].RequirementGroup[0]

        then: "First amount"
        def req1 = g1.Requirement[1]
        req1.Response.size() == 1
        req1.Response.Amount.text() == "11.1123"
        req1.Response.Amount.@currencyID.text() == "RON"
    }

    def "check empty 'Average turnover' requirements response"() {
        given:
        def espd = new EspdDocument(specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true,
                averageTurnover: null, averageTurnoverCurrency: "ALB"))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)
        def g1 = response.Criterion[idx].RequirementGroup[0]

        then: "First amount"
        def req1 = g1.Requirement[1]
        req1.Response.size() == 1
        req1.Response.Amount.size() == 0
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SPECIFIC_AVERAGE_TURNOVER)

        then:
        def g2 = response.Criterion[idx].RequirementGroup[1]
        def req = g2.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_09.com")))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SPECIFIC_AVERAGE_TURNOVER)

        then:
        def g2 = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]
        def req = g2.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_09.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_09")))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SPECIFIC_AVERAGE_TURNOVER)

        then:
        def g2 = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]
        def req = g2.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_09"
    }

}