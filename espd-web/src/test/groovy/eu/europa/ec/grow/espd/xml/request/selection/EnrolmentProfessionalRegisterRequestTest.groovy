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
import eu.europa.ec.grow.espd.domain.SuitabilityCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class EnrolmentProfessionalRegisterRequestTest extends AbstractSelectionCriteriaFixture {

    def "02. should contain the 'Enrolment in a relevant professional register' criterion"() {
        given:
        def espd = new EspdDocument(enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.ENROLMENT_PROFESSIONAL_REGISTER)

        then: "CriterionID element"
        checkCriterionId(request, idx, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.SUITABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Enrolment in a relevant professional register"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "It is enrolled in relevant professional registers kept in the Member State of its establishment as described in Annex XI of Directive 2014/24/EU; economic operators from certain Member States may have to comply with other requirements set out in that Annex."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(2)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        def g1 = request.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "1768de86-a6c8-48e4-bd8e-de2f2f7424d0"
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 1

        then: "main sub group requirements"
        checkRequirement(g1.Requirement[0], "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")

        then: "info available electronically sub group"
        def g2 = request.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(g2)
    }

}