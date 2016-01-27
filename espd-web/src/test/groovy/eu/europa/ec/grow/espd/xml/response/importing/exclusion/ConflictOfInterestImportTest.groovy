package eu.europa.ec.grow.espd.xml.response.importing.exclusion

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.ConflictInterestCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/8/16 at 10:49 AM.
 */
class ConflictOfInterestImportTest extends AbstractXmlFileImport {

    def "20. should import all fields of 'Conflict of interest due to its participation in the procurement procedure'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/conflict_of_interest_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.conflictInterest.exists == true
        espd.conflictInterest.answer == true
        espd.conflictInterest.description == "Hodor is conflicted"

        then: "self cleaning"
        espd.conflictInterest.selfCleaning.answer == true
        espd.conflictInterest.selfCleaning.description == "Hodor is clean"

        then: "info electronically"
        espd.conflictInterest.availableElectronically.answer == true
        espd.conflictInterest.availableElectronically.url == "www.hodor.com"
        espd.conflictInterest.availableElectronically.code == "HODOR?"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(conflictInterest: new ConflictInterestCriterion(exists: true,  answer: true,
                description: "Hodor is conflicted",
                selfCleaning: new SelfCleaning(answer: true, description: "Hodor is clean"),
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "HODOR?")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}