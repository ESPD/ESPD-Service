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

package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SuitabilityCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 2/29/16 at 4:46 PM.
 */
class ServiceContractsAuthorisationImportTest extends AbstractXmlFileImport {

    def "04. should import all fields of 'For service contracts: authorisation of particular organisation needed'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/service_contracts_authorisation_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.serviceContractsAuthorisation.exists == true

        then: "selection criteria with no answer have a default value of true"
        espd.serviceContractsAuthorisation.answer == true
        espd.serviceContractsAuthorisation.description == "my kingdom"

        then: "info electronically"
        espd.serviceContractsAuthorisation.availableElectronically.answer == true
        espd.serviceContractsAuthorisation.availableElectronically.url == "www.hodor.com"
        espd.serviceContractsAuthorisation.availableElectronically.code == "SERVICE_CONTRACTS_AUTH"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(serviceContractsAuthorisation: new SuitabilityCriterion(exists: true, answer: true,
                description: "my kingdom",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "SERVICE_CONTRACTS_AUTH")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}