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

package eu.europa.ec.grow.espd.xml.response.exclusion

import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.ConflictInterestCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:23 PM.
 */
class DirectInvolvementPreparationProcurementResponseTest extends AbstractExclusionCriteriaFixture {

    def "21. should contain the 'Direct or indirect involvement in the preparation of this procurement procedure' criterion"() {
        given:
        def espd = new EspdDocument(involvementPreparationProcurement: new ConflictInterestCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE)

        then: "CriterionID element"
        checkCriterionId(response, idx, "61874050-5130-4f1c-a174-720939c7b483")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.EXCLUSION.CONFLICT_OF_INTEREST.PROCEDURE_PREPARATION")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Direct or indirect involvement in the preparation of this procurement procedure"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "Has the economic operator or an undertaking related to it advised the contracting authority or contracting entity or otherwise been involved in the preparation of the procurement procedure?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "57(4)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "77ae3f29-7c5f-4afa-af97-24afec48c5bf"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        then:
        def g1_1 = g1.RequirementGroup[0]
        g1_1.ID.text() == "73f0fe4c-4ed9-4343-8096-d898cf200146"
        g1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g1_1.RequirementGroup.size() == 0
        g1_1.Requirement.size() == 1
        checkRequirement(g1_1.Requirement[0], "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(involvementPreparationProcurement: new ConflictInterestCriterion(exists: true, answer: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(involvementPreparationProcurement: new ConflictInterestCriterion(exists: true,
                description: "bogus description."))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")
        req.Response[0].Description.text() == "bogus description."
    }

}