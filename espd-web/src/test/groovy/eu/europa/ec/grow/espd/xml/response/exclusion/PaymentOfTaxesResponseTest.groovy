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

package eu.europa.ec.grow.espd.xml.response.exclusion
import eu.europa.ec.grow.espd.domain.enums.other.Country
import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TaxesCriterion
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
import org.joda.time.LocalDate
/**
 * Created by ratoico on 12/9/15 at 11:58 AM.
 */
class PaymentOfTaxesResponseTest extends AbstractExclusionCriteriaFixture {

    def "07. should contain the 'Payment of taxes' criterion"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then: "CriterionID element"
        checkCriterionId(response, idx, "b61bbeb7-690e-4a40-bc68-d6d4ecfaa3d4")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.EXCLUSION.CONTRIBUTIONS.PAYMENT_OF_TAXES")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Payment of taxes"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "Has the economic operator breached its obligations relating to the payment of taxes, both in the country in which it is established and in Member State of the contracting authority or contracting entity if other than the country of establishment?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "57(2)")

        then: "G1"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "098fd3cc-466e-4233-af1a-affe09471bce"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1

        then: "G1.1"
        def g1_1 = g1.RequirementGroup[0]
        g1_1.ID.text() == "f8499787-f9f8-4355-95e2-9784426f4d7b"
        g1_1.RequirementGroup.size() == 3
        g1_1.Requirement.size() == 2
        g1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"

        then: "G1.1.1"
        def g1_1_1 = g1_1.RequirementGroup[0]
        g1_1_1.ID.text() == "7c2aec9f-4876-4c33-89e6-2ab6d6cf5d02"
        g1_1_1.@pi.text() == ""
        g1_1_1.Requirement.size() == 1
        g1_1_1.RequirementGroup.size() == 1

        then: "G1.1.1.1"
        def g1_1_1_1 = g1_1_1.RequirementGroup[0]
        g1_1_1_1.ID.text() == "3cb7abf1-662a-4756-b61c-7bc716c1fafc"
        g1_1_1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g1_1_1_1.RequirementGroup.size() == 0

        then: "G1.1.2"
        def g1_1_2 = g1_1.RequirementGroup[1]
        g1_1_2.ID.text() == "c882afa4-6971-4b00-8970-0c283eb122cc"
        g1_1_2.@pi.text() == ""
        g1_1_2.Requirement.size() == 1
        g1_1_2.RequirementGroup.size() == 1

        then: "G1.1.2.1"
        def g1_1_2_1 = g1_1_2.RequirementGroup[0]
        g1_1_2_1.ID.text() == "815422d6-f8a1-418a-8bf0-3524f7c8f721"
        g1_1_2_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g1_1_2_1.RequirementGroup.size() == 0
        g1_1_2_1.Requirement.size() == 2

        then: "G1.1.3"
        def g1_1_3 = g1_1.RequirementGroup[2]
        g1_1_3.ID.text() == "fc57e473-d63e-4a04-b589-dcf81cab8052"
        g1_1_3.@pi.text() == ""
        g1_1_3.Requirement.size() == 1
        g1_1_3.RequirementGroup.size() == 1

        then: "G1.1.3.1"
        def g1_1_3_1 = g1_1_3.RequirementGroup[0]
        g1_1_3_1.ID.text() == "6c3609e1-9add-4fa9-9409-62ce72ae4548"
        g1_1_3_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g1_1_3_1.RequirementGroup.size() == 0
        g1_1_3_1.Requirement.size() == 1

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(response.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, answer: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Country member state' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, country: Country.GB))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "6c87d3d4-e8eb-4253-b385-6373020ab886", "Country or member state concerned", "CODE_COUNTRY")
        req.Response.size() == 1
        req.Response[0].Code.text() == "GB"
        req.Response[0].Code.@listAgencyID.text() == "ISO"
        req.Response[0].Code.@listID.text() == "ISO 3166-1"
        req.Response[0].Code.@listVersionID.text() == "1.0"
    }

    def "check the 'Amount concerned' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, amount: BigDecimal.valueOf(445), currency: "RON"))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].Requirement[1]
        checkRequirement(req, "9052cc59-cfe5-41c6-a314-02a7f378ffe8", "Amount concerned", "AMOUNT")
        req.Response.size() == 1
        req.Response.Amount.text() == "445"
        req.Response.Amount.@currencyID.text() == "RON"
    }

    def "check the 'Has this breach of obligations been established by means other than a judicial or administrative decision' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, breachEstablishedOtherThanJudicialDecision: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup[0]
        checkRequirement(subGroup.Requirement[0], "9b4497e6-a166-46f9-8581-7fc39ff975c4", "Has this breach of obligations been established by means other than a judicial or administrative decision?", "INDICATOR")
        subGroup.Requirement[0].Response[0].Indicator.text() == "true"
    }

    def "check the 'Please describe which means were used' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, meansDescription: "Other means were used"))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup[0].RequirementGroup[0]
        checkRequirement(subGroup.Requirement[0], "201f11c3-1fa2-4464-acc0-f021266fd881", "Please describe which means were used", "DESCRIPTION")
        subGroup.Requirement[0].Response[0].Description.text() == "Other means were used"
    }

    def "check the 'If this breach of obligations was established through a judicial or administrative decision, was this decision final and binding' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, decisionFinalAndBinding: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "08b0c984-c5e6-4143-8493-868c39745637",
                "If this breach of obligations was established through a judicial or administrative decision, was this decision final and binding?",
                "INDICATOR")
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Date of conviction' requirement response"() {
        given:
        def now = new Date()
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, dateOfConviction: now))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")
        req.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(now.getTime()))
    }

    def "check the 'Length of the period of exclusion' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, periodLength: "Till the end of the year 2013."))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "PERIOD")
        req.Response[0].Period.Description.text() == "Till the end of the year 2013."
    }

    def "check the 'Has the economic operator fulfilled its obligations by paying or entering into a binding arrangement' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, eoFulfilledObligations: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup[2]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "70f8697b-8953-411a-a489-4ff62e5250d2",
                "Has the economic operator fulfilled its obligations by paying or entering into a binding arrangement with a view to paying the taxes or social security contributions due, including, where applicable, any interest accrued or fines?",
                "INDICATOR")
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, obligationsDescription: "This debt was the result of a miscalculation by our accountability department."))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup[2].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "55905dd0-38f0-4f93-8c74-5ae05a21afc5", "Please describe them", "DESCRIPTION")
        req.Response[0].Description.text() == "This debt was the result of a miscalculation by our accountability department."
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, availableElectronically: new AvailableElectronically(answer: true)))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_07.com")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkEvidence(req.Response[0].Evidence, "http://hodor_07.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_07")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_TAXES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response[0].Code.text() == "HODOR_07"
    }

}