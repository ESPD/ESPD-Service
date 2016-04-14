package eu.europa.ec.grow.espd.xml.response.importing.award

import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/20/16 at 2:42 PM.
 */
class ProcurementReservedImportTest extends AbstractXmlFileImport {

    def "01. should import all fields of 'Procurement reserved'"() {
        given:
        def espdResponseXml = importXmlResponseFile("award/procurement_reserved_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.procurementReserved.exists == true
        espd.procurementReserved.answer == true
        espd.procurementReserved.doubleValue1 == 11.11
        espd.procurementReserved.description1 == "Hodor"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(procurementReserved: new AwardCriterion(exists: true, description1: "Hodor",
                answer: true, doubleValue1: 11.11))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }


}