package eu.europa.ec.grow.espd.xml.response.importing.exclusion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.PurelyNationalGrounds
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/8/16 at 10:59 AM.
 */
class NationalExclusionGroundsImportTest extends AbstractXmlFileImport {

    def "21. should import all fields of 'Purely national exclusion grounds'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/purely_national_grounds_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.purelyNationalGrounds.exists == true
        espd.purelyNationalGrounds.answer == true
        espd.purelyNationalGrounds.description == "Hodor is national"

        then: "info electronically"
        espd.purelyNationalGrounds.availableElectronically.answer == true
        espd.purelyNationalGrounds.availableElectronically.url == "www.hodor.com"
        espd.purelyNationalGrounds.availableElectronically.code == "NATIONAL"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new PurelyNationalGrounds(exists: true,  answer: true,
                description: "Hodor is national",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "NATIONAL")))

        expect:
        1 == 1
    }

}