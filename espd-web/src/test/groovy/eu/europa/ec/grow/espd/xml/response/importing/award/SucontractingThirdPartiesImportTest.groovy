package eu.europa.ec.grow.espd.xml.response.importing.award

import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 2/29/16 at 1:47 PM.
 */
class SucontractingThirdPartiesImportTest extends AbstractXmlFileImport {

    def "06. should import all fields of 'SucontractingThirdParties'"() {
        given:
        def espdResponseXml = importXmlResponseFile("award/subcontracting_third_parties.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.subcontractingThirdParties.exists == true

        then:
        espd.subcontractingThirdParties.answer == true
        espd.subcontractingThirdParties.description1 == "list of subcontractors"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(subcontractingThirdParties: new AwardCriterion(exists: true, answer: true,
                description1: "list of subcontractors"))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}