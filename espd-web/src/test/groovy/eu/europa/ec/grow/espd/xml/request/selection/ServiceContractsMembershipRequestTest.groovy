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
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SuitabilityCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ServiceContractsMembershipRequestTest extends AbstractSelectionCriteriaFixture {

    def "05. should contain the 'For service contracts: membership of particular organisation needed' criterion"() {
        given:
        def espd = new EspdDocument(serviceContractsMembership: new SuitabilityCriterion(exists: true))

        when:
        def response = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_MEMBERSHIP)

        then: "CriterionID element"
        checkCriterionId(response, idx, "73f10e36-ed7a-412e-995c-aa76463e3776")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "SELECTION.SUITABILITY")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "For service contracts: membership of particular organisation needed"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "Is a particular membership of a particular organisation needed in order to be able to perform the service in question in the country of establishment of the economic operator?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(2)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "G1"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "a109e144-f65e-469d-bcda-220f1af34b6c"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")

        then: "G1.1"
        def g1_1 = g1.RequirementGroup[0]
        g1_1.ID.text() == "7696fb3f-9722-43b8-9b91-ad59bb4b8ad2"
        g1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        checkRequirement(g1_1.Requirement[0], "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        def g2 = response.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(g2)
    }

}