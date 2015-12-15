package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.Taxes

/**
 * Created by ratoico on 12/9/15 at 11:58 AM.
 */
class PaymentOfTaxesRequestTest extends AbstractEspdXmlMarshalling {


    def "07. should contain the 'Payment of taxes' criterion"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new Taxes(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "b61bbeb7-690e-4a40-bc68-d6d4ecfaa3d4")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.PAYMENT_OF_TAXES")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Payment of taxes"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator breached its obligations relating to the payment of taxes, both in the country in which it is established and in Member State of the contracting authority or contracting entity if other than the country of establishment?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(2)")
    }

}