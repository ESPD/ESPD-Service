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
import eu.europa.ec.grow.espd.domain.QualityAssuranceCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/13/16 at 11:18 AM.
 */
class CertificatesIndependentBodiesEnvironmentalImportTest extends AbstractXmlFileImport {

    def "32. should import all fields of 'Certificates by independent bodies about environmental management systems or standards'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/certificates_independent_bodies_environmental_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.certificateIndependentBodiesAboutEnvironmental.exists == true
        espd.certificateIndependentBodiesAboutEnvironmental.answer == true
        espd.certificateIndependentBodiesAboutEnvironmental.description == "Another description"

        then: "info electronically"
        espd.certificateIndependentBodiesAboutEnvironmental.availableElectronically.answer == true
        espd.certificateIndependentBodiesAboutEnvironmental.availableElectronically.url == "www.hodor.com"
        espd.certificateIndependentBodiesAboutEnvironmental.availableElectronically.code == "TECHNICAL_QUALITY"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutEnvironmental: new QualityAssuranceCriterion(
                exists: true, answer: true, description: "Another description",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "TECHNICAL_QUALITY")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}