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

package eu.europa.ec.grow.espd.xml.request.exclusion

import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.LawCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 1/11/16 at 10:59 AM.
 */
class BreachingObligationsSocialRequestTest extends AbstractExclusionCriteriaFixture {

    def "10. should contain the 'Breaching of obligations in the fields of social law' criterion"() {
        given:
        def espd = new EspdDocument(breachingObligationsSocial: new LawCriterion(exists: true))

        when:
        def request = generateRequestXml(espd)
        def idx = getRequestCriterionIndex(ExclusionCriterion.BREACHING_OF_OBLIGATIONS_SOCIAL)

        then: "CriterionID element"
        checkCriterionId(request, idx, "a261a395-ed17-4939-9c75-b9ff1109ca6e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "CRITERION.EXCLUSION.SOCIAL.SOCIAL_LAW")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Breaching of obligations in the fields of social law"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator, to its knowledge, breached its obligations in the fields of social law? As referred to for the purposes of this procurement in national law, in the relevant notice or the procurement documents or in Article 18(2) of Directive 2014/24/EU."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "G1"
        def g1 = request.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "976b5acb-c00f-46ca-8f83-5ce6abfdfe43"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1
        def r1_0 = g1.Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        then:
        def g1_1 = g1.RequirementGroup[0]
        g1_1.ID.text() == "64a2102c-4af1-4ecb-97b3-0c41907ec0f6"
        g1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        def r1_1 = g1_1.Requirement[0]
        checkRequirement(r1_1, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")

        then: "check first sub group"
        def sub1_1 = g1_1.RequirementGroup[0]
        checkSelfCleaningRequirementGroup(sub1_1)
    }

}