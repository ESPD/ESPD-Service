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

import eu.europa.ec.grow.espd.domain.DynamicRequirementGroup
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class GeneralYearlyTurnoverResponseTest extends AbstractSelectionCriteriaFixture {

    def "06. should contain the 'General yearly turnover' criterion"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)

        then: "CriterionID element"
        checkCriterionId(response, idx, "499efc97-2ac1-4af2-9e84-323c2ca67747")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.SELECTION.ECONOMIC_FINANCIAL_STANDING.TURNOVER.GENERAL_YEARLY")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "General yearly turnover"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "Its general yearly turnover for the number of financial years required in the relevant notice, the procurement documents or the ESPD is as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(3)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "G1"
        def crit = response.Criterion[idx]

        then: "check year amount currency subgroups"
        checkYearAmountCurrencyGroup1(crit.RequirementGroup[0])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(crit.RequirementGroup[1])
    }

    def "check the 'Year' requirements response"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true,
                unboundedGroups: [
                        new DynamicRequirementGroup("year": 2016),
                        new DynamicRequirementGroup("year": 2015),
                        new DynamicRequirementGroup("year": 2014),
                        new DynamicRequirementGroup("year": 2013),
                        new DynamicRequirementGroup("year": 2012)
                ]))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)
        def crit = response.Criterion[idx]

        then: "First year"
        def g1_1 = crit.RequirementGroup[0]
        def req1 = g1_1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Quantity.text() == "2016"
        req1.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Second year"
        def g1_2 = crit.RequirementGroup[1]
        def req2 = g1_2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Quantity.text() == "2015"
        req2.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Third year"
        def g1_3 = crit.RequirementGroup[2]
        def req3 = g1_3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Quantity.text() == "2014"
        req3.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Fourth year"
        def g1_4 = crit.RequirementGroup[3]
        def req4 = g1_4.Requirement[0]
        req4.Response.size() == 1
        req4.Response[0].Quantity.text() == "2013"
        req4.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Fifth year"
        def g1_5 = crit.RequirementGroup[4]
        def req5 = g1_5.Requirement[0]
        req5.Response.size() == 1
        req5.Response[0].Quantity.text() == "2012"
        req5.Response[0].Quantity.@unitCode.text() == "YEAR"
    }

    def "check empty 'Year' requirements response"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true,
                unboundedGroups: [
                        new DynamicRequirementGroup("year": null),
                        new DynamicRequirementGroup("year": null),
                        new DynamicRequirementGroup("year": null),
                        new DynamicRequirementGroup("year": null),
                        new DynamicRequirementGroup("year": null)
                ]))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)
        def crit = response.Criterion[idx]

        then: "First year"
        def g1_1 = crit.RequirementGroup[0]
        def req1 = g1_1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Quantity.size() == 0

        then: "Second year"
        def g1_2 = crit.RequirementGroup[1]
        def req2 = g1_2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Quantity.size() == 0

        then: "Third year"
        def g1_3 = crit.RequirementGroup[2]
        def req3 = g1_3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Quantity.size() == 0

        then: "Fourth year"
        def g1_4 = crit.RequirementGroup[3]
        def req4 = g1_4.Requirement[0]
        req4.Response.size() == 1
        req4.Response[0].Quantity.size() == 0

        then: "Fifth year"
        def g1_5 = crit.RequirementGroup[4]
        def req5 = g1_5.Requirement[0]
        req5.Response.size() == 1
        req5.Response[0].Quantity.size() == 0
    }

    def "check the 'Amount' requirements response"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true,
                unboundedGroups: [
                        new DynamicRequirementGroup("amount": 11.11, "currency": "EUR"),
                        new DynamicRequirementGroup("amount": 22.22, "currency": "RON"),
                        new DynamicRequirementGroup("amount": 33.33, "currency": "USD"),
                        new DynamicRequirementGroup("amount": 44.44, "currency": "CHF"),
                        new DynamicRequirementGroup("amount": 55.55, "currency": "ALD"),
                ]))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)
        def crit = response.Criterion[idx]

        then: "First amount"
        def g1_1 = crit.RequirementGroup[0]
        def req1 = g1_1.Requirement[1]
        req1.Response.size() == 1
        req1.Response.Amount.text() == "11.11"
        req1.Response.Amount.@currencyID.text() == "EUR"

        then: "Second amount"
        def g1_2 = crit.RequirementGroup[1]
        def req2 = g1_2.Requirement[1]
        req2.Response.size() == 1
        req2.Response.Amount.text() == "22.22"
        req2.Response.Amount.@currencyID.text() == "RON"

        then: "Third amount"
        def g1_3 = crit.RequirementGroup[2]
        def req3 = g1_3.Requirement[1]
        req3.Response.size() == 1
        req3.Response.Amount.text() == "33.33"
        req3.Response.Amount.@currencyID == "USD"

        then: "Fourth amount"
        def g1_4 = crit.RequirementGroup[3]
        def req4 = g1_4.Requirement[1]
        req4.Response.size() == 1
        req4.Response.Amount.text() == "44.44"
        req4.Response.Amount.@currencyID == "CHF"

        then: "Fifth amount"
        def g1_5 = crit.RequirementGroup[4]
        def req5 = g1_5.Requirement[1]
        req5.Response.size() == 1
        req5.Response.Amount.text() == "55.55"
        req5.Response.Amount.@currencyID == "ALD"
    }

    def "check empty 'Amount' requirements response"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true,
                unboundedGroups: [
                        new DynamicRequirementGroup("amount": null, "currency": "EUR"),
                        new DynamicRequirementGroup("amount": null, "currency": "RON"),
                        new DynamicRequirementGroup("amount": null, "currency": "USD"),
                        new DynamicRequirementGroup("amount": null, "currency": "CHF"),
                        new DynamicRequirementGroup("amount": null, "currency": "ALD"),
                ]))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)
        def crit = response.Criterion[idx]

        then: "First amount"
        def g1_1 = crit.RequirementGroup[0]
        def req1 = g1_1.Requirement[1]
        req1.Response.size() == 1
        req1.Response.Amount.size() == 0

        then: "Second amount"
        def g1_2 = crit.RequirementGroup[1]
        def req2 = g1_2.Requirement[1]
        req2.Response.size() == 1
        req2.Response.Amount.size() == 0

        then: "Third amount"
        def g1_3 = crit.RequirementGroup[2]
        def req3 = g1_3.Requirement[1]
        req3.Response.size() == 1
        req3.Response.Amount.size() == 0

        then: "Fourth amount"
        def g1_4 = crit.RequirementGroup[3]
        def req4 = g1_4.Requirement[1]
        req4.Response.size() == 1
        req4.Response.Amount.size() == 0

        then: "Fifth amount"
        def g1_5 = crit.RequirementGroup[4]
        def req5 = g1_5.Requirement[1]
        req5.Response.size() == 1
        req5.Response.Amount.size() == 0
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)

        then:
        def g6 = response.Criterion[idx].RequirementGroup[1]
        def req = g6.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_06.com")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)

        then:
        def g6_1 = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]
        def req = g6_1.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_06.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_06")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)

        then:
        def g6_1 = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]
        def req = g6_1.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_06"
    }

}