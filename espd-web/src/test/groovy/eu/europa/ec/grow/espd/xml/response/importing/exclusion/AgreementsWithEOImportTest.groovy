package eu.europa.ec.grow.espd.xml.response.importing.exclusion

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.MisconductDistortionCriterion
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/18/16 at 10:41 AM.
 */
class AgreementsWithEOImportTest extends AbstractXmlFileImport {

    def "19. should import all fields of 'Conflict of interest due to its participation in the procurement procedure'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/agreements_with_eo_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.agreementsWithOtherEO.exists == true
        espd.agreementsWithOtherEO.answer == true
        espd.agreementsWithOtherEO.description == "Hodor is distorting"

        then: "self cleaning"
        espd.agreementsWithOtherEO.selfCleaning.answer == true
        espd.agreementsWithOtherEO.selfCleaning.description == "Hodor is clean"

        then: "there should be no info electronically"
        espd.agreementsWithOtherEO.availableElectronically.answer == false
        espd.agreementsWithOtherEO.availableElectronically.url == null
        espd.agreementsWithOtherEO.availableElectronically.code == null
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(agreementsWithOtherEO: new MisconductDistortionCriterion(exists: true,  answer: true,
                description: "Hodor is distorting",
                selfCleaning: new SelfCleaning(answer: true, description: "Hodor is clean")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}