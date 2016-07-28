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

import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture

/**
 * Created by ratoico on 1/4/16 at 1:24 PM.
 */
class AllCriteriaSatisfiedResponseTest extends AbstractSelectionCriteriaFixture {

    def "01. CA selects 'All section criteria' -> The response contains only 'All selection criteria' and not the individual ones."() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true, answer: true))
        def idx = getResponseCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        when:
        def response = parseResponseXml(espd)

        then: "CriterionID element"
        checkCriterionId(response, idx, "7e7db838-eeac-46d9-ab39-42927486f22d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.SELECTION.ALL_SATISFIED")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "All selection criteria will be satisfied"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "It satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice."

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "f3a6836d-2de2-4cd1-81ca-fb06178d05c5"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 0
        g1.Requirement.size() == 1
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true, answer: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "when a EO answers 'NO' to 'Satisfies all' criterion, the criterion should still appear in the response"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true, answer: false))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "CA selects 'All section criteria' -> The response contains only 'All selection criteria' and not the individual ones."() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true, answer: true),
                enrolmentTradeRegister: new SuitabilityCriterion(exists: true, answer: true),
                generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true, answer: true),
                workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true, answer: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        then:
        response.Criterion.size() == getMandatoryExclusionCriteriaSize() + 1 + OtherCriterion.values().length
        checkCriterionId(response, idx, "7e7db838-eeac-46d9-ab39-42927486f22d")

        then: "the next criterion should be the first award criterion"
        checkCriterionId(response, idx + 1, OtherCriterion.PROCUREMENT_RESERVED.getUuid())
    }

    def "CA select individual selection criteria -> EO sees only the selected ones (and even not the 'All selection criteria')"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: false),
                enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true),
                generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)

        then: "only selected selection criteria are present plus mandatory exclusion plus the award criterion"
        response.Criterion.size() == getMandatoryExclusionCriteriaSize() + 3 + OtherCriterion.values().length

        then: "check the CriterionID"
        // satisfies all is not selected by the CA so the index of the first selection criterion is one position lower
        def idx = getResponseCriterionIndex(SelectionCriterion.ENROLMENT_PROFESSIONAL_REGISTER)
        checkCriterionId(response, idx, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")
        checkCriterionId(response, idx + 1, "499efc97-2ac1-4af2-9e84-323c2ca67747")
        checkCriterionId(response, idx + 2, "cdd3bb3e-34a5-43d5-b668-2aab86a73822")
    }

    def "CA select individual selection criteria -> EO sees only the selected ones (but also 'All selection criteria' if exists is true and answer is no)"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true, answer: false),
                enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true),
                generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)

        then: "only selected selection criteria are present plus mandatory exclusion plus the award criterion"
        response.Criterion.size() == getMandatoryExclusionCriteriaSize() + 4 + OtherCriterion.values().length

        then: "check the CriterionID"
        def idx = getResponseCriterionIndex(SelectionCriterion.ENROLMENT_PROFESSIONAL_REGISTER)
        checkCriterionId(response, idx, "7e7db838-eeac-46d9-ab39-42927486f22d")
        checkCriterionId(response, idx + 1, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")
        checkCriterionId(response, idx + 2, "499efc97-2ac1-4af2-9e84-323c2ca67747")
        checkCriterionId(response, idx + 3, "cdd3bb3e-34a5-43d5-b668-2aab86a73822")
    }

}