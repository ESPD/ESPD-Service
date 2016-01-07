package eu.europa.ec.grow.espd.xml.response.importing.exclusion

import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TaxesCriterion
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/7/16 at 3:54 PM.
 */
class PaymentOfTaxesImportTest extends AbstractXmlFileImport {

    def "07. should import all fields of 'Payment of taxes'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/payment_of_taxes_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.paymentTaxes.exists == true
        espd.paymentTaxes.country == Country.ROMANIA
        espd.paymentTaxes.amount == 445.0
        espd.paymentTaxes.currency == "RON"

        then: "first subgroup"
        espd.paymentTaxes.breachEstablishedOtherThanJudicialDecision == true
        espd.paymentTaxes.meansDescription == "Other means were used"

        then: "second subgroup"
        espd.paymentTaxes.decisionFinalAndBinding == true
        espd.paymentTaxes.dateOfConviction == LocalDateAdapter.unmarshal("2016-17-01").toDate()
        espd.paymentTaxes.periodLength == "Till the end of the year 2013."

        then: "third subgroup"
        espd.paymentTaxes.eoFulfilledObligations == true
        espd.paymentTaxes.obligationsDescription == "This debt was the result of a miscalculation by our accountability department."

        then: "info electronically"
        espd.paymentTaxes.availableElectronically.exists == true
        espd.paymentTaxes.availableElectronically.url == "http://aeat.es/doc/recibos/792db19f-687c-4402-a6c7-77158c306334.pdf"
        espd.paymentTaxes.availableElectronically.code == "HODOR"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true, country: Country.ROMANIA,
                amount: 445, currency: "RON",
                breachEstablishedOtherThanJudicialDecision: true, meansDescription: "Other means were used",
                decisionFinalAndBinding: true, dateOfConviction: new Date(), periodLength: "Till the end of the year 2013.",
                eoFulfilledObligations: true, obligationsDescription: "This debt was the result of a miscalculation by our accountability department.",
                availableElectronically: new AvailableElectronically(exists: true, url: "http://aeat.es/doc/recibos/792db19f-687c-4402-a6c7-77158c306334.pdf", code: "HODOR")))

        expect:
        1 == 1
    }

}