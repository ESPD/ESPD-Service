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
class ProfessionalRiskIndemnityResponseTest extends AbstractSelectionCriteriaFixture {

    def "12. should contain the 'Professional risk indemnity insurance' criterion"() {
        given:
        def espd = new EspdDocument(professionalRiskInsurance: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.PROFESSIONAL_RISK_INSURANCE)

        then: "CriterionID element"
        checkCriterionId(response, idx, "7604bd40-4462-4086-8763-a50da51a869c")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.SELECTION.ECONOMIC_FINANCIAL_STANDING.RISK_INDEMNITY_INSURANCE")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Professional risk indemnity insurance"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "The insured amount in its professional risk indemnity insurance is the following:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(3)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "42dc8062-974d-4201-91ba-7f2ea90338fd"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = g1.Requirement[0]
        checkRequirement(r1_0, "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(response.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Amount' requirements response"() {
        given:
        def espd = new EspdDocument(professionalRiskInsurance: new EconomicFinancialStandingCriterion(exists: true,
                amount1: 11.11, currency1: "EUR"))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.PROFESSIONAL_RISK_INSURANCE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]
        
        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response.Amount.text() == "11.11"
        req.Response.Amount.@currencyID.text() == "EUR"
    }

    def "check empty 'Amount' requirements response"() {
        given:
        def espd = new EspdDocument(professionalRiskInsurance: new EconomicFinancialStandingCriterion(exists: true,
                amount1: null, currency1: "EUR"))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.PROFESSIONAL_RISK_INSURANCE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]
        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response.Amount.size() == 0
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(professionalRiskInsurance: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.PROFESSIONAL_RISK_INSURANCE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(professionalRiskInsurance: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_11.com")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.PROFESSIONAL_RISK_INSURANCE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_11.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(professionalRiskInsurance: new EconomicFinancialStandingCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_11")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.PROFESSIONAL_RISK_INSURANCE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_11"
    }

}