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

package eu.europa.ec.grow.espd.domain

import spock.lang.Specification


/**
 * Created by ratoico on 12/6/16.
 */
class CriterionDefaultValuesTest extends Specification {

    def "bankruptcy criterion should have a default answer of NO"() {
        given:
        def criterion = new BankruptcyCriterion()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "conflict of interest criterion should have a default answer of NO"() {
        given:
        def criterion = new ConflictInterestCriterion()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "criminal convictions criterion should have a default answer of NO"() {
        given:
        def criterion = new CriminalConvictionsCriterion()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "law criterion should have a default answer of NO"() {
        given:
        def criterion = new LawCriterion()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "misconduct distortion criterion should have a default answer of NO"() {
        given:
        def criterion = new MisconductDistortionCriterion()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "purely national grounds criterion should have a default answer of NO"() {
        given:
        def criterion = new PurelyNationalGrounds()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "taxes criterion should have a default answer of NO"() {
        given:
        def criterion = new TaxesCriterion()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "economic financial standing selection criterion should have a default answer of YES"() {
        given:
        def criterion = new EconomicFinancialStandingCriterion()

        when:
        def result = criterion.answer

        then:
        result == true
    }

    def "quality assurance criterion criterion should have a default answer of YES"() {
        given:
        def criterion = new QualityAssuranceCriterion()

        when:
        def result = criterion.answer

        then:
        result == true
    }

    def "satisfies all criterion criterion should have a default answer of NULL"() {
        given:
        def criterion = new SatisfiesAllCriterion()

        when:
        def result = criterion.answer

        then:
        result == null
    }

    def "suitability criterion should have a default answer of YES"() {
        given:
        def criterion = new SuitabilityCriterion()

        when:
        def result = criterion.answer

        then:
        result == true
    }

    def "technical professional criterion should have a default answer of YES"() {
        given:
        def criterion = new TechnicalProfessionalCriterion()

        when:
        def result = criterion.answer

        then:
        result == true
    }

    def "other (economic operator) criterion should have a default answer of NO"() {
        given:
        def criterion = new OtherCriterion()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "other (economic operator) criterion boolean values should have a default answer of NO"() {
        given:
        def criterion = new OtherCriterion()

        expect:
        criterion.booleanValue1 == false
        criterion.booleanValue3 == false
    }

    def "self cleaning should have a default answer of NO"() {
        given:
        def criterion = new SelfCleaning()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "info available electronically should have a default answer of NO"() {
        given:
        def criterion = new AvailableElectronically()

        when:
        def result = criterion.answer

        then:
        result == false
    }

    def "Is economic operator a SME should have a default answer of NO"() {
        given:
        def espd = new EspdDocument(economicOperator: new EconomicOperatorImpl())

        when:
        def result = espd.economicOperator

        then:
        result.isSmallSizedEnterprise == false
    }

    def "Exclusion criteria related to taxes and contributions should have default values of NO"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(), paymentSocialSecurity: new TaxesCriterion())

        expect:
        espd.paymentTaxes.breachEstablishedOtherThanJudicialDecision == false
        espd.paymentTaxes.decisionFinalAndBinding == false
        espd.paymentTaxes.eoFulfilledObligations == false

        and:
        espd.paymentSocialSecurity.breachEstablishedOtherThanJudicialDecision == false
        espd.paymentSocialSecurity.decisionFinalAndBinding == false
        espd.paymentSocialSecurity.eoFulfilledObligations == false
    }

}