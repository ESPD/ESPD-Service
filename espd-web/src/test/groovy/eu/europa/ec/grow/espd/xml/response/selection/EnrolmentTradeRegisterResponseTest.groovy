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

package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SuitabilityCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class EnrolmentTradeRegisterResponseTest extends AbstractSelectionCriteriaFixture {

    def "03. should contain the 'Enrolment in a trade register' criterion"() {
        given:
        def espd = new EspdDocument(enrolmentTradeRegister: new SuitabilityCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ENROLMENT_TRADE_REGISTER)

        then: "CriterionID element"
        checkCriterionId(request, idx, "87b3fa26-3549-4f92-b8e0-3fd8f04bf5c7")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.SUITABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Enrolment in a trade register"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "It is enrolled in trade registers kept in the Member State of its establishment as described in Annex XI of Directive 2014/24/EU; economic operators from certain Member States may have to comply with other requirements set out in that Annex."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(2)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        def g1 = request.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "1768de86-a6c8-48e4-bd8e-de2f2f7424d0"
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 1

        then: "main sub group requirements"
        checkRequirement(g1.Requirement[0], "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")

        then: "info available electronically sub group"
        def g2 = request.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(g2)
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(enrolmentTradeRegister: new SuitabilityCriterion(exists: true, answer: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ENROLMENT_TRADE_REGISTER)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }


    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(enrolmentTradeRegister: new SuitabilityCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ENROLMENT_TRADE_REGISTER)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(enrolmentTradeRegister: new SuitabilityCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_03.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ENROLMENT_TRADE_REGISTER)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_03.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(enrolmentTradeRegister: new SuitabilityCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_03")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ENROLMENT_TRADE_REGISTER)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_03"
    }

}