package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.Taxes

/**
 * Created by ratoico on 12/9/15 at 1:13 PM.
 */
class PaymentOfSocialSecurityRequestTest extends AbstractEspdXmlMarshalling {


    def "08. should contain the 'Payment of social security contributions' criterion"() {
        given:
        def espd = new EspdDocument(paymentSocialSecurity: new Taxes(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "7d85e333-bbab-49c0-be8d-c36d71a72f5e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.PAYMENT_OF_SOCIAL_SECURITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Payment of social security contributions"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator breached its obligations relating to the payment social security contributions, both in the country in which it is established and in Member State of the contracting authority or contracting entity if other than the country of establishment?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(2)")
    }

}