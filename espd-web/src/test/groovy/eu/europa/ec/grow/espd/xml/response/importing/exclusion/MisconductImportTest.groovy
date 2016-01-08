package eu.europa.ec.grow.espd.xml.response.importing.exclusion

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.MisconductCriterion
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/8/16 at 9:40 AM.
 */
class MisconductImportTest extends AbstractXmlFileImport {

    def "16. should import all fields of 'Guilty of grave professional misconduct'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/guilty_of_grave_professional_misconduct_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.guiltyGrave.exists == true
        espd.guiltyGrave.description == "Hodor is misbehaving"

        then: "self cleaning"
        espd.guiltyGrave.selfCleaning.exists == true
        espd.guiltyGrave.selfCleaning.description == "Hodor is clean"

        then: "info electronically"
        espd.guiltyGrave.availableElectronically.exists == false
        espd.guiltyGrave.availableElectronically.url == null
        espd.guiltyGrave.availableElectronically.code == null
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(guiltyGrave: new MisconductCriterion(exists: true, description: "Hodor is misbehaving",
                selfCleaning: new SelfCleaning(exists: true, description: "Hodor is clean")))

        expect:
        1 == 1
    }

}