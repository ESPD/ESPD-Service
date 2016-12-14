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

package eu.europa.ec.grow.espd.xml.response.importing.exclusion

import eu.europa.ec.grow.espd.domain.enums.other.Country
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
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.paymentTaxes.exists == true
        espd.paymentTaxes.answer == true
        espd.paymentTaxes.country == Country.RO
        espd.paymentTaxes.amount == 445.0
        espd.paymentTaxes.currency == "RON"

        then: "first subgroup"
        espd.paymentTaxes.breachEstablishedOtherThanJudicialDecision == true
        espd.paymentTaxes.meansDescription == "Other means were used"

        then: "second subgroup"
        espd.paymentTaxes.decisionFinalAndBinding == true
        espd.paymentTaxes.dateOfConviction == LocalDateAdapter.unmarshal("2016-01-17").toDate()
        espd.paymentTaxes.periodLength == "Till the end of the year 2013."

        then: "third subgroup"
        espd.paymentTaxes.eoFulfilledObligations == true
        espd.paymentTaxes.obligationsDescription == "This debt was the result of a miscalculation by our accountability department."

        then: "info electronically"
        espd.paymentTaxes.availableElectronically.answer == true
        espd.paymentTaxes.availableElectronically.url == "http://aeat.es/doc/recibos/792db19f-687c-4402-a6c7-77158c306334.pdf"
        espd.paymentTaxes.availableElectronically.code == "TAXES"
        espd.paymentTaxes.availableElectronically.issuer == "HODOR"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(paymentTaxes: new TaxesCriterion(exists: true,  answer: true,
                country: Country.RO,
                amount: 445, currency: "RON",
                breachEstablishedOtherThanJudicialDecision: true, meansDescription: "Other means were used",
                decisionFinalAndBinding: true, dateOfConviction: new Date(), periodLength: "Till the end of the year 2013.",
                eoFulfilledObligations: true, obligationsDescription: "This debt was the result of a miscalculation by our accountability department.",
                availableElectronically: new AvailableElectronically(answer: true, url: "http://aeat.es/doc/recibos/792db19f-687c-4402-a6c7-77158c306334.pdf", code: "HODOR")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}