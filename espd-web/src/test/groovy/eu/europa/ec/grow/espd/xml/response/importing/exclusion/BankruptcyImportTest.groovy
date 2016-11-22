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
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.BankruptcyCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/8/16 at 9:26 AM.
 */
class BankruptcyImportTest extends AbstractXmlFileImport {

    def "10. should import all fields of 'Bankruptcy'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/bankruptcy_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.bankruptcy.exists == true
        espd.bankruptcy.answer == false
        espd.bankruptcy.description == "Bankruptcy description"
        espd.bankruptcy.reason == "We lost all our money at poker."

        then: "info electronically"
        espd.bankruptcy.availableElectronically.answer == true
        espd.bankruptcy.availableElectronically.url == "www.hodor.com"
        espd.bankruptcy.availableElectronically.code == "INTERNATIONAL"
        espd.bankruptcy.availableElectronically.issuer == "HODOR"

    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true, answer: false,
                description: "Bankruptcy description",
                reason: "We lost all our money at poker.",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "INTERNATIONAL", issuer: "HODOR")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}