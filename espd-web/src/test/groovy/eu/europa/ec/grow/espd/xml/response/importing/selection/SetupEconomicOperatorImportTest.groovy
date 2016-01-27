package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/25/16 at 10:42 AM.
 */
class SetupEconomicOperatorImportTest extends AbstractXmlFileImport {

    def "10. should import all fields of 'Set up of economic operator'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/setup_economic_operator_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.setupEconomicOperator.exists == true
        espd.setupEconomicOperator.answer == false // has no answer criterion
        espd.setupEconomicOperator.year1 == 2016
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(setupEconomicOperator: new EconomicFinancialStandingCriterion(exists: true, answer: true,
                year1: 2016))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}