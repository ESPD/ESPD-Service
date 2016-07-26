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

package eu.europa.ec.grow.espd.xml.response

import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture

/**
 * Created by ratoico on 1/27/16 at 11:54 AM.
 */
class EspdResponseCriteriaTest extends AbstractCriteriaFixture {

    def "no exclusion criteria are mandatory but plus all selection criteria plus all award criteria should be"() {
        given:
        def espd = new EspdDocument()
        def idx = 0

        when:
        def result = parseResponseXml(espd)

        then: "there are no mandatory exclusion criteria anymore"
        result.Criterion.size() == getMandatoryExclusionCriteriaSize() + SelectionCriterion.values().length + OtherCriterion.values().length

        then: "all selection criteria must be present since there were none selected"
        for (SelectionCriterion criterion : SelectionCriterion.values()) {
            checkCriterionId(result, idx++, criterion.getUuid())
        }

        then: "all award criteria must be present"
        for (OtherCriterion criterion : OtherCriterion.values()) {
            checkCriterionId(result, idx++, criterion.getUuid())
        }
    }

    def "exclusion criteria with no 'Your answer' must have a default value of FALSE"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, answer: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "selection criteria with no 'Your answer' must have a default value of TRUE"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true, answer: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "Award criteria with no 'Indicator' don't have a response"() {
        given:
        def espd = new EspdDocument(meetsObjective: new eu.europa.ec.grow.espd.domain.OtherCriterion(exists: true, answer: null))

        when:
        def request = parseResponseXml(espd)
        def idx = getEoCriterionIndex(OtherCriterion.MEETS_OBJECTIVE)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "7f18c64e-ae09-4646-9400-f3666d50af51", "Your answer", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.size() == 0
    }
}