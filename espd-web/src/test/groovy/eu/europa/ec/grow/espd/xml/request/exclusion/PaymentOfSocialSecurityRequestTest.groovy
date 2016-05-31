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
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TaxesCriterion
/**
 * Created by ratoico on 12/9/15 at 1:13 PM.
 */
class PaymentOfSocialSecurityRequestTest extends AbstractExclusionCriteriaFixture {


    def "08. should contain the 'Payment of social security contributions' criterion"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then: "CriterionID element"
        checkCriterionId(request, idx, "7d85e333-bbab-49c0-be8d-c36d71a72f5e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "CRITERION.EXCLUSION.CONTRIBUTIONS.PAYMENT_OF_SOCIAL_SECURITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Payment of social security contributions"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator breached its obligations relating to the payment social security contributions, both in the country in which it is established and in Member State of the contracting authority or contracting entity if other than the country of establishment?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(2)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "G1"
        def g1 = request.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "098fd3cc-466e-4233-af1a-affe09471bce"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1
        def r1_0 = g1.Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        then: "G1.1"
        def g_1_1 = g1.RequirementGroup[0]
        g_1_1.ID.text() == "f8499787-f9f8-4355-95e2-9784426f4d7b"
        g_1_1.RequirementGroup.size() == 3
        g_1_1.Requirement.size() == 2
        g_1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        def r1_1 = g_1_1.Requirement[0]
        checkRequirement(r1_1, "6c87d3d4-e8eb-4253-b385-6373020ab886", "Country or member state concerned", "CODE_COUNTRY")
        def r1_2 = g_1_1.Requirement[1]
        checkRequirement(r1_2, "9052cc59-cfe5-41c6-a314-02a7f378ffe8", "Amount concerned", "AMOUNT")

        then: "G1.1.1"
        def g_1_1_1 = g_1_1.RequirementGroup[0]
        g_1_1_1.ID.text() == "7c2aec9f-4876-4c33-89e6-2ab6d6cf5d02"
        g_1_1_1.@pi.text() == ""
        g_1_1_1.Requirement.size() == 1
        g_1_1_1.RequirementGroup.size() == 1
        checkRequirement(g_1_1_1.Requirement[0], "9b4497e6-a166-46f9-8581-7fc39ff975c4", "Has this breach of obligations been established by means other than a judicial or administrative decision?", "INDICATOR")

        then: "G1.1.1.1"
        def g_1_1_1_1 = g_1_1_1.RequirementGroup[0]
        g_1_1_1_1.ID.text() == "3cb7abf1-662a-4756-b61c-7bc716c1fafc"
        g_1_1_1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g_1_1_1_1.RequirementGroup.size() == 0
        checkRequirement(g_1_1_1_1.Requirement[0], "201f11c3-1fa2-4464-acc0-f021266fd881", "Please describe which means were used", "DESCRIPTION")

        then: "G1.1.2"
        def g1_1_2 = g_1_1.RequirementGroup[1]
        g1_1_2.ID.text() == "c882afa4-6971-4b00-8970-0c283eb122cc"
        g1_1_2.@pi.text() == ""
        g1_1_2.Requirement.size() == 1
        g1_1_2.RequirementGroup.size() == 1
        checkRequirement(g1_1_2.Requirement[0], "08b0c984-c5e6-4143-8493-868c39745637", "If this breach of obligations was established through a judicial or administrative decision, was this decision final and binding?", "INDICATOR")

        then: "G1.1.2.1"
        def g_1_1_2_1 = g1_1_2.RequirementGroup[0]
        g_1_1_2_1.ID.text() == "815422d6-f8a1-418a-8bf0-3524f7c8f721"
        g_1_1_2_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g_1_1_2_1.RequirementGroup.size() == 0
        g_1_1_2_1.Requirement.size() == 2
        checkRequirement(g_1_1_2_1.Requirement[0], "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")
        checkRequirement(g_1_1_2_1.Requirement[1], "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "PERIOD")

        then: "G1.1.3"
        def g_1_1_3 = g_1_1.RequirementGroup[2]
        g_1_1_3.ID.text() == "fc57e473-d63e-4a04-b589-dcf81cab8052"
        g_1_1_3.@pi.text() == ""
        g_1_1_3.Requirement.size() == 1
        g_1_1_3.RequirementGroup.size() == 1
        checkRequirement(g_1_1_3.Requirement[0], "70f8697b-8953-411a-a489-4ff62e5250d2",
                "Has the economic operator fulfilled its obligations by paying or entering into a binding arrangement with a view to paying the taxes or social security contributions due, including, where applicable, any interest accrued or fines?", "INDICATOR")

        then: "G1.1.3.1"
        def g_1_1_3_1 = g_1_1_3.RequirementGroup[0]
        g_1_1_3_1.ID.text() == "6c3609e1-9add-4fa9-9409-62ce72ae4548"
        g_1_1_3_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g_1_1_3_1.RequirementGroup.size() == 0
        g_1_1_3_1.Requirement.size() == 1
        checkRequirement(g_1_1_3_1.Requirement[0], "55905dd0-38f0-4f93-8c74-5ae05a21afc5", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}