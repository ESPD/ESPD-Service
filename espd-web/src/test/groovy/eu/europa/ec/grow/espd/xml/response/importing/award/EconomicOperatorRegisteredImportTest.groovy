package eu.europa.ec.grow.espd.xml.response.importing.award

import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/20/16 at 3:30 PM.
 */
class EconomicOperatorRegisteredImportTest extends AbstractXmlFileImport {

    def "02. should import all fields of 'Economic operator registered'"() {
        given:
        def espdResponseXml = importXmlResponseFile("award/eo_registered_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.eoRegistered.exists == true
        espd.eoRegistered.answer == false
        espd.eoRegistered.booleanValue1 == true
        espd.eoRegistered.booleanValue2 == true
        espd.eoRegistered.booleanValue3 == true
        espd.eoRegistered.description1 == "descr 1"
        espd.eoRegistered.description2 == "descr 2"
        espd.eoRegistered.description3 == "descr 3"
        // description 4 field (part e) has been replaced by an indicator stored in booleanValue3
        espd.eoRegistered.description5 == "descr 5"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(eoRegistered: new AwardCriterion(exists: true, answer: false,
                description1: "descr 1", description2: "descr 2", description3: "descr 3", description5: "descr 5",
                booleanValue1: true, booleanValue2: true, booleanValue3: true))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}