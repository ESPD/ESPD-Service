package eu.europa.ec.grow.espd.xml.response.importing.selection
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/8/16 at 1:15 PM.
 */
class TechniciansTechnicalBodiesImportTest extends AbstractXmlFileImport {

    def "16. should import all fields of 'Technicians or technical bodies for quality control'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/technicians_technical_bodies_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.techniciansTechnicalBodies.exists == true
        espd.techniciansTechnicalBodies.specify == "specify something"

        then: "info electronically"
        espd.techniciansTechnicalBodies.availableElectronically.exists == true
        espd.techniciansTechnicalBodies.availableElectronically.url == "www.hodor.com"
        espd.techniciansTechnicalBodies.availableElectronically.code == "TECHNICAL_BODIES"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(techniciansTechnicalBodies: new TechnicalProfessionalCriterion(exists: true,
                specify: "specify something",
                availableElectronically: new AvailableElectronically(exists: true, url: "www.hodor.com", code: "TECHNICAL_BODIES")))
        //        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}