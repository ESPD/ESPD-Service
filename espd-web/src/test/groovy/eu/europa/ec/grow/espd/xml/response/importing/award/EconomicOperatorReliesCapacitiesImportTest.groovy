package eu.europa.ec.grow.espd.xml.response.importing.award

import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/20/16 at 3:45 PM.
 */
class EconomicOperatorReliesCapacitiesImportTest extends AbstractXmlFileImport {

    def "04. should import all fields of 'Economic operator relies capacities'"() {
        given:
        def espdResponseXml = importXmlResponseFile("award/eo_relies_capacities_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.eoReliesCapacities.exists == true
        espd.eoReliesCapacities.answer == true
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(eoReliesCapacities: new AwardCriterion(exists: true))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }


}