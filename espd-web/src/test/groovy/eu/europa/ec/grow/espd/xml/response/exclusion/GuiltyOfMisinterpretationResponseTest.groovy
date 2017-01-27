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

import eu.europa.ec.grow.espd.domain.ConflictInterestCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:26 PM.
 */
class GuiltyOfMisinterpretationResponseTest extends AbstractExclusionCriteriaFixture {

    def "23. should contain the 'Guilty of misinterpretation, withheld information, able to provide required documents and obtained confidential information of this procedure' criterion"() {
        given:
        def espd = new EspdDocument(guiltyMisinterpretation: new ConflictInterestCriterion(exists: true))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.GUILTY_OF_MISINTERPRETATION)

        then: "CriterionID element"
        checkCriterionId(response, idx, "696a75b2-6107-428f-8b74-82affb67e184")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.EXCLUSION.CONFLICT_OF_INTEREST.MISINTERPRETATION")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Guilty of misinterpretation, withheld information, unable to provide required documents and obtained confidential information of this procedure"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "Can the economic operator confirm the four exclusion grounds, that it has not been guilty of serious misrepresentation in supplying the information required for the verification of the absence of grounds for exclusion or the fulfilment of the selection criteria, that it has not withheld such information, it has been able, without delay, to submit the supporting documents required by a contracting authority or contracting entity, and it has not undertaken to unduly influence the decision making process of the contracting authority or contracting entity, to obtain confidential information that may confer upon it undue advantages in the procurement procedure or to negligently provide misleading information that may have a material influence on decisions concerning exclusion, selection or award?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "57(4)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        response.Criterion[idx].RequirementGroup[0].ID.text() == "30450436-f559-4dfa-98ba-f0842ed9d2a0"
        response.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        response.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(guiltyMisinterpretation: new ConflictInterestCriterion(exists: true, answer: null))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.GUILTY_OF_MISINTERPRETATION)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

}