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
 * Created by ratoico on 1/13/16 at 11:12 AM.
 */
class CertificatesIndependentBodiesQualityImportTest extends AbstractXmlFileImport {

    def "31. should import all fields of 'Certificates by independent bodies about quality assurance standards'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/certificates_independent_bodies_quality_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.certificateIndependentBodiesAboutQa.exists == true
        espd.certificateIndependentBodiesAboutQa.answer == true
        espd.certificateIndependentBodiesAboutQa.description == "Another description"

        then: "info electronically"
        espd.certificateIndependentBodiesAboutQa.availableElectronically.answer == true
        espd.certificateIndependentBodiesAboutQa.availableElectronically.url == "www.hodor.com"
        espd.certificateIndependentBodiesAboutQa.availableElectronically.code == "TECHNICAL_QUALITY"
        espd.certificateIndependentBodiesAboutQa.availableElectronically.issuer == "HODOR"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutQa: new QualityAssuranceCriterion(exists: true,
                answer: true, description: "Another description",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "TECHNICAL_QUALITY", issuer: "HODOR")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}