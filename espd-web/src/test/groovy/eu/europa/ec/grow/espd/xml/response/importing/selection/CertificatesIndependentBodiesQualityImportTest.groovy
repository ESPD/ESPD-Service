package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
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
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.certificateIndependentBodiesAboutQa.exists == true
        espd.certificateIndependentBodiesAboutQa.answer == true
        espd.certificateIndependentBodiesAboutQa.description == "Another description"

        then: "info electronically"
        espd.certificateIndependentBodiesAboutQa.availableElectronically.answer == true
        espd.certificateIndependentBodiesAboutQa.availableElectronically.url == "www.hodor.com"
        espd.certificateIndependentBodiesAboutQa.availableElectronically.code == "TECHNICAL_QUALITY"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutQa: new TechnicalProfessionalCriterion(exists: true,
                answer: true, description: "Another description",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "TECHNICAL_QUALITY")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}