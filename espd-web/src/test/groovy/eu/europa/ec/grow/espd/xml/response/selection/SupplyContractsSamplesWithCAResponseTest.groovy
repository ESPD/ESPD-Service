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
class SupplyContractsSamplesWithCAResponseTest extends AbstractSelectionCriteriaFixture {

    def "30. should contain the 'For supply contracts: samples, descriptions or photographs with certification of authenticity' criterion"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then: "CriterionID element"
        checkCriterionId(response, idx, "7662b7a9-bcb8-4763-a0a7-7505d8e8470d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "For supply contracts: samples, descriptions or photographs with certification of authenticity"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "For public supply contracts: The economic operator will supply the required samples, descriptions or photographs of the products to be supplied and will provide certifications of authenticity where applicable."

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(4)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "cb73544d-e8bb-4cc6-819b-b8e04f1e240e"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(response.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Your answer' requirements response"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true, answer: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_30.com")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_30.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_30")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_30"
    }

}