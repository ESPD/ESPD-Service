package eu.europa.ec.grow.espd.xml.response.importing.award

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/20/16 at 3:50 PM.
 */
class MeetsObjectiveImportTest extends AbstractXmlFileImport {

    def "05. should import all fields of 'Meets objective'"() {
        given:
        def espdResponseXml = importXmlResponseFile("award/meets_objective_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.meetsObjective.exists == true

        then: "there is no answer"
        espd.meetsObjective.answer == false
        espd.meetsObjective.description1 == "please describe"

        then: "info electronically"
        espd.meetsObjective.availableElectronically.answer == true
        espd.meetsObjective.availableElectronically.url == "www.hodor.com"
        espd.meetsObjective.availableElectronically.code == "MEETS"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(meetsObjective: new AwardCriterion(exists: true, answer: null,
            description1: "please describe",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "MEETS")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}