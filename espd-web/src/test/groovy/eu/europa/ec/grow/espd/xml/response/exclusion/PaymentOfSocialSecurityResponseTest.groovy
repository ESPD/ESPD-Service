package eu.europa.ec.grow.espd.xml.response.exclusion
import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TaxesCriterion
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
import org.joda.time.LocalDate
/**
 * Created by ratoico on 12/9/15 at 1:13 PM.
 */
class PaymentOfSocialSecurityResponseTest extends AbstractExclusionCriteriaFixture {


    def "08. should contain the 'Payment of social security contributions' criterion"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "7d85e333-bbab-49c0-be8d-c36d71a72f5e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.PAYMENT_OF_SOCIAL_SECURITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Payment of social security contributions"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator breached its obligations relating to the payment social security contributions, both in the country in which it is established and in Member State of the contracting authority or contracting entity if other than the country of establishment?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(2)")


        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "e0b0dedc-19d7-4d12-9542-1ca656b6f4f8"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 3
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 3

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "6c87d3d4-e8eb-4253-b385-6373020ab886", "Country or member state concerned", "COUNTRY")

        def r1_2 = request.Criterion[idx].RequirementGroup[0].Requirement[2]
        checkRequirement(r1_2, "9052cc59-cfe5-41c6-a314-02a7f378ffe8", "Amount concerned", "AMOUNT")

        then: "check first sub group"
        def sub1_1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        sub1_1.ID.text() == "7c2aec9f-4876-4c33-89e6-2ab6d6cf5d02"
        sub1_1.Requirement.size() == 2

        checkRequirement(sub1_1.Requirement[0], "9b4497e6-a166-46f9-8581-7fc39ff975c4", "Has this breach of obligations been established by means other than a judicial or administrative decision?", "INDICATOR")
        checkRequirement(sub1_1.Requirement[1], "201f11c3-1fa2-4464-acc0-f021266fd881", "Please describe which means were used", "DESCRIPTION")

        then: "check second sub group"
        def sub1_2 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]
        sub1_2.ID.text() == "c882afa4-6971-4b00-8970-0c283eb122cc"
        sub1_2.Requirement.size() == 3

        checkRequirement(sub1_2.Requirement[0], "08b0c984-c5e6-4143-8493-868c39745637", "If this breach of obligations was established through a judicial or administrative decision, was this decision final and binding?", "INDICATOR")
        checkRequirement(sub1_2.Requirement[1], "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")
        checkRequirement(sub1_2.Requirement[2], "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "PERIOD")

        then: "check third sub group"
        def sub1_3 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[2]
        sub1_3.ID.text() == "fc57e473-d63e-4a04-b589-dcf81cab8052"
        sub1_3.Requirement.size() == 2

        checkRequirement(sub1_3.Requirement[0], "70f8697b-8953-411a-a489-4ff62e5250d2",
                "Has the economic operator fulfilled its obligations by paying or entering into a binding arrangement with a view to paying the taxes or social security contributions due, including, where applicable, any interest accrued or fines?", "INDICATOR")
        checkRequirement(sub1_3.Requirement[1], "55905dd0-38f0-4f93-8c74-5ae05a21afc5", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Country member state' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, country: Country.ROMANIA))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(req, "6c87d3d4-e8eb-4253-b385-6373020ab886", "Country or member state concerned", "COUNTRY")
        req.Response.size() == 1
        req.Response[0].Code.text() == "RO"
        req.Response[0].Code.@listAgencyID.text() == "ISO"
        req.Response[0].Code.@listID.text() == "ISO 3166-2"
        req.Response[0].Code.@listVersionID.text() == "1.0"
    }

    def "check the 'Amount concerned' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, amount: 445, currency: "RON"))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[2]
        checkRequirement(req, "9052cc59-cfe5-41c6-a314-02a7f378ffe8", "Amount concerned", "AMOUNT")
        req.Response.size() == 1
        req.Response.Amount.text() == "445.0"
        req.Response.Amount.@currencyID.text() == "RON"
    }

    def "check the 'Has this breach of obligations been established by means other than a judicial or administrative decision' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, breachEstablishedOtherThanJudicialDecision: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        checkRequirement(subGroup.Requirement[0], "9b4497e6-a166-46f9-8581-7fc39ff975c4", "Has this breach of obligations been established by means other than a judicial or administrative decision?", "INDICATOR")
        subGroup.Requirement[0].Response[0].Indicator.text() == "true"
    }

    def "check the 'Please describe which means were used' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, meansDescription: "Other means were used"))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        checkRequirement(subGroup.Requirement[1], "201f11c3-1fa2-4464-acc0-f021266fd881", "Please describe which means were used", "DESCRIPTION")
        subGroup.Requirement[1].Response[0].Description.text() == "Other means were used"
    }

    def "check the 'If this breach of obligations was established through a judicial or administrative decision, was this decision final and binding' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, decisionFinalAndBinding: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "08b0c984-c5e6-4143-8493-868c39745637",
                "If this breach of obligations was established through a judicial or administrative decision, was this decision final and binding?",
                "INDICATOR")
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Date of conviction' requirement response"() {
        given:
        def now = new Date()
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, dateOfConviction: now))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")
        req.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(now.getTime()))
    }

    def "check the 'Length of the period of exclusion' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, periodLength: "Till the end of the year 2013."))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        checkRequirement(req, "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "PERIOD")
        req.Response[0].Period.Description.text() == "Till the end of the year 2013."
    }

    def "check the 'Has the economic operator fulfilled its obligations by paying or entering into a binding arrangement' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, eoFulfilledObligations: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[2]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "70f8697b-8953-411a-a489-4ff62e5250d2",
                "Has the economic operator fulfilled its obligations by paying or entering into a binding arrangement with a view to paying the taxes or social security contributions due, including, where applicable, any interest accrued or fines?",
                "INDICATOR")
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, obligationsDescription: "This debt was the result of a miscalculation by our accountability department."))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[2]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "55905dd0-38f0-4f93-8c74-5ae05a21afc5", "Please describe them", "DESCRIPTION")
        req.Response[0].Description.text() == "This debt was the result of a miscalculation by our accountability department."
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, availableElectronically: new AvailableElectronically(exists: true)))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_08.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        checkEvidence(req.Response[0].Evidence, "http://hodor_08.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new TaxesCriterion(exists: true, availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_08")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response[0].Code.text() == "HODOR_08"
    }

}