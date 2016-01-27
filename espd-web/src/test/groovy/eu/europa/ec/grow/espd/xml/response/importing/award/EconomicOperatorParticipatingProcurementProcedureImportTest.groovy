package eu.europa.ec.grow.espd.xml.response.importing.award

import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/20/16 at 3:34 PM.
 */
class EconomicOperatorParticipatingProcurementProcedureImportTest extends AbstractXmlFileImport {

    def "03. should import all fields of 'Economic operator participating procurement procedure'"() {
        given:
        def espdResponseXml = importXmlResponseFile("award/eo_participating_procurement_procedure_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.eoParticipatingProcurementProcedure.exists == true
        espd.eoParticipatingProcurementProcedure.answer == true
        espd.eoParticipatingProcurementProcedure.description1 == "descr 1"
        espd.eoParticipatingProcurementProcedure.description2 == "descr 2"
        espd.eoParticipatingProcurementProcedure.description3 == "descr 3"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(eoParticipatingProcurementProcedure: new AwardCriterion(exists: true, answer: true,
                description1: "descr 1", description2: "descr 2", description3: "descr 3"))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}