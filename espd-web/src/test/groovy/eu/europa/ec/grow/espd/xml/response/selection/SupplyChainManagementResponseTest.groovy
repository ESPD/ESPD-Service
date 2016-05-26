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
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SupplyChainManagementResponseTest extends AbstractSelectionCriteriaFixture {

    def "21. should contain the 'Supply chain management' criterion"() {
        given:
        def espd = new EspdDocument(supplyChainManagement: new TechnicalProfessionalCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CHAIN_MANAGEMENT)

        then: "CriterionID element"
        checkCriterionId(response, idx, "dc12a151-7fdf-4733-a8f0-30f667292e66")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Supply chain management"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "It will be able to apply the following supply chain management and tracking systems when performing the contract:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(4)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "488ca189-bcdb-4bf4-80c7-3ad507fd89fb"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        def g2 = response.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(g2)
    }

    def "check the 'Please describe them' requirements response"() {
        given:
        def espd = new EspdDocument(supplyChainManagement: new TechnicalProfessionalCriterion(exists: true,
                description: "technical description"))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CHAIN_MANAGEMENT)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Description.text() == "technical description"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(supplyChainManagement: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CHAIN_MANAGEMENT)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]
        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(supplyChainManagement: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_20.com")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CHAIN_MANAGEMENT)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]
        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_20.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(supplyChainManagement: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_20")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CHAIN_MANAGEMENT)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]
        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_20"
    }

}