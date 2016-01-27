package eu.europa.ec.grow.espd.xml.response.importing.selection
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/12/16 at 5:24 PM.
 */
class AllowanceOfChecksImportTest extends AbstractXmlFileImport {

    def "22. should import all fields of 'Allowance of checks'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/allowance_of_checks_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.allowanceOfChecks.exists == true
        espd.allowanceOfChecks.answer == true
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(allowanceOfChecks: new TechnicalProfessionalCriterion(exists: true, answer: true))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}