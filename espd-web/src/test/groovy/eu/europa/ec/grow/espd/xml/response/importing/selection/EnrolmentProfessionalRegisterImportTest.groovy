package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SuitabilityCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/8/16 at 11:22 AM.
 */
class EnrolmentProfessionalRegisterImportTest extends AbstractXmlFileImport {

    def "02. should import all fields of 'Enrolment in a relevant professional register'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/enrolment_professional_register_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.enrolmentProfessionalRegister.exists == true
        espd.enrolmentProfessionalRegister.answer == true

        then: "info electronically"
        espd.enrolmentProfessionalRegister.availableElectronically.answer == true
        espd.enrolmentProfessionalRegister.availableElectronically.url == "www.hodor.com"
        espd.enrolmentProfessionalRegister.availableElectronically.code == "PROF_REGISTER"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true, answer: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "PROF_REGISTER")))
        //        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}