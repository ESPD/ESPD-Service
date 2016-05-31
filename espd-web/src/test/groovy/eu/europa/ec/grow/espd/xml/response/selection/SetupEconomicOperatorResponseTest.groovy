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
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 1/25/16 at 10:31 AM.
 */
class SetupEconomicOperatorResponseTest extends AbstractSelectionCriteriaFixture {

    def "10. should contain the 'Set up of economic operator' criterion"() {
        given:
        def espd = new EspdDocument(setupEconomicOperator: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SETUP_ECONOMIC_OPERATOR)

        then: "CriterionID element"
        checkCriterionId(response, idx, "77f481ce-ffb6-483f-8e2b-c78db5e68292")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.SELECTION.ECONOMIC_FINANCIAL_STANDING.TURNOVER.SET_UP")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Set up of economic operator"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "In case the information concerning turnover (general or specific) is not available for the entire period required, please state the date on which the economic operator was set up or started trading:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(3)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "e9aa7763-c167-4352-8060-1a3d7d3e2662"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "a18b2c98-8552-45ca-9751-d4c94c05847a", "Please specify", "QUANTITY_YEAR")
    }

    def "check the 'Please specify year' requirements response"() {
        given:
        def espd = new EspdDocument(setupEconomicOperator: new EconomicFinancialStandingCriterion(exists: true,
                year1: 2016))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SETUP_ECONOMIC_OPERATOR)

        then: "Specify year"
        def group = response.Criterion[idx].RequirementGroup[0]
        def req = group.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Quantity.text() == "2016"
        req.Response[0].Quantity.@unitCode.text() == "YEAR"
    }

}