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
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SatisfiesAllCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 1/6/16 at 3:26 PM.
 */
class AllCriteriaSatisfiedRequestTest extends AbstractSelectionCriteriaFixture {

    def "01. 'All selection criteria satisfied' criterion"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true))
        def idx = getRequestCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        when:
        def request = parseRequestXml(espd)

        then: "CriterionID element"
        checkCriterionId(request, idx, "7e7db838-eeac-46d9-ab39-42927486f22d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ALL_CRITERIA_SATISFIED")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "All selection criteria will be satisfied"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "It satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice."

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        def g1 = request.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "f3a6836d-2de2-4cd1-81ca-fb06178d05c5"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 1
    }

}