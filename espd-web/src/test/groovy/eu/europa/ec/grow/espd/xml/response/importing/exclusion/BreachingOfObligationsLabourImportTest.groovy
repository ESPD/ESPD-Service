package eu.europa.ec.grow.espd.xml.response.importing.exclusion

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.LawCriterion
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/29/16 at 2:05 PM.
 */
class BreachingOfObligationsLabourImportTest extends AbstractXmlFileImport {

    def "11. should import all fields of 'Breaching of obligations in the fields of labour law'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/breaching_of_obligations_labour_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.breachingObligationsLabour.exists == true
        espd.breachingObligationsLabour.answer == true
        espd.breachingObligationsLabour.description == "social description"

        then: "self cleaning"
        espd.breachingObligationsLabour.selfCleaning.answer == true
        espd.breachingObligationsLabour.selfCleaning.description == "social is very clean"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(breachingObligationsLabour: new LawCriterion(exists: true,  answer: true,
                description: "social description",
                selfCleaning: new SelfCleaning(answer: true, description: "social is very clean")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}