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
import eu.europa.ec.grow.espd.domain.MisconductDistortionCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 1/18/16 at 9:54 AM.
 */
class AgreementsWithEORequestTest extends AbstractExclusionCriteriaFixture {

    def "19. should contain the 'Agreements with other economic operators aimed at distorting competition' criterion"() {
        given:
        def espd = new EspdDocument(agreementsWithOtherEO: new MisconductDistortionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(ExclusionCriterion.AGREEMENTS_WITH_OTHER_EO)

        then: "CriterionID element"
        checkCriterionId(request, idx, "56d13e3d-76e8-4f23-8af6-13e60a2ee356")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "CRITERION.EXCLUSION.MISCONDUCT.MARKET_DISTORTION")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Agreements with other economic operators aimed at distorting competition"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator entered into agreements with other economic operators aimed at distorting competition?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        def g1 = request.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "67362ec7-cec3-4cb8-a38e-5d7a2a31e6d8"
        g1.@pid.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        then: "G1.1"
        def g1_1 = g1.RequirementGroup[0]
        g1_1.ID.text() == "2cbcf978-765c-40aa-996b-b1d082485cef"
        g1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g1_1.RequirementGroup.size() == 1
        g1_1.Requirement.size() == 1
        checkRequirement(g1_1.Requirement[0], "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")

        then: "G1.1.1"
        def g1_1_1 = g1_1.RequirementGroup[0]
        checkSelfCleaningRequirementGroup(g1_1_1)
    }

}