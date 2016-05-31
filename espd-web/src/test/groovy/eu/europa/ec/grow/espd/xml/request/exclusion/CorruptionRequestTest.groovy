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
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
import eu.europa.ec.grow.espd.domain.CriminalConvictionsCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 11:35 AM.
 */
class CorruptionRequestTest extends AbstractExclusionCriteriaFixture {

    def "02. should contain the 'Corruption' criterion"() {
        given:
        def espd = new EspdDocument(corruption: new CriminalConvictionsCriterion(exists: true))
        def idx = getRequestCriterionIndex(ExclusionCriterion.CORRUPTION)

        when:
        def request = parseRequestXml(espd)

        then: "CriterionID element"
        checkCriterionId(request, idx, "c27b7c4e-c837-4529-b867-ed55ce639db5")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "CRITERION.EXCLUSION.CONVICTIONS.CORRUPTION")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Corruption"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for corruption, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 3 of the Convention on the fight against corruption involving officials of the European Communities or officials of Member States of the European Union, OJ C 195, 25.6.1997, p. 1, and in Article 2(1) of Council Framework Decision 2003/568/JHA of 22 July 2003 on combating corruption in the private sector (OJ L 192, 31.7.2003, p. 54). This exclusion ground also includes corruption as defined in the national law of the contracting authority (contracting entity) or the economic operator."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(1)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        def g1 = request.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "7c637c0c-7703-4389-ba52-02997a055bd7"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = g1.Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        then:
        def g1_1 = g1.RequirementGroup[0]
        g1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g1_1.Requirement.size() == 4

        def r1_1 = g1_1.Requirement[0]
        checkRequirement(r1_1, "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")

        def r1_2 = g1_1.Requirement[1]
        checkRequirement(r1_2, "7d35fb7c-da5b-4830-b598-4f347a04dceb", "Reason", "DESCRIPTION")

        def r1_3 = g1_1.Requirement[2]
        checkRequirement(r1_3, "c5012430-14da-454c-9d01-34cedc6a7ded", "Who has been convicted", "DESCRIPTION")

        def r1_4 = g1_1.Requirement[3]
        checkRequirement(r1_4, "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "PERIOD")

        then: "check the self-cleaning sub group"
        def g1_1_1 = g1_1.RequirementGroup[0]
        g1_1_1.@pi.text() == ""
        checkSelfCleaningRequirementGroup(g1_1_1)

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}