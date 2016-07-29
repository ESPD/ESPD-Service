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

package eu.europa.ec.grow.espd.xml.request.award

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 * Created by ratoico on 5/27/16.
 */
class EconomicOperatorReliesCapacitiesRequestTest extends AbstractCriteriaFixture {

    def "04. should contain the 'Does the economic operator rely on the capacities of other entities' criterion"() {
        given:
        // exists is false but award criteria should always be present
        def espd = new EspdDocument(eoReliesCapacities: new eu.europa.ec.grow.espd.domain.OtherCriterion(exists: false))

        when:
        def request = parseRequestXml(espd)
        def idx = getEoCriterionIndex(OtherCriterion.EO_RELIES_CAPACITIES)

        then: "CriterionID element"
        checkCriterionId(request, idx, "0d62c6ed-f074-4fcf-8e9f-f691351d52ad")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "CRITERION.OTHER.EO_DATA.RELIES_ON_OTHER_CAPACITIES")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "EO relies capacities"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Does the economic operator rely on the capacities of other entities in order to meet the selection criteria set out under Part IV and the criteria and rules (if any) set out under Part V below?"

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        def g1 = request.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "e688f7d6-dcef-4726-bc61-052e63ead60f"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "7f18c64e-ae09-4646-9400-f3666d50af51", "Your answer", "INDICATOR")
    }

}