package eu.europa.ec.grow.espd.xml.response.importing.selection
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/8/16 at 2:44 PM.
 */
class GeneralYearlyTurnoverImportTest extends AbstractXmlFileImport {

    def "06. should import all fields of 'General yearly turnover'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/general_yearly_turnover_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.generalYearlyTurnover.exists == true

        then:
        espd.generalYearlyTurnover.year1 == 2016
        espd.generalYearlyTurnover.amount1 == 111.1
        espd.generalYearlyTurnover.currency1 == "RON"

        then:
        espd.generalYearlyTurnover.year2 == 2015
        espd.generalYearlyTurnover.amount2 == 222.2
        espd.generalYearlyTurnover.currency2 == "EUR"

        then:
        espd.generalYearlyTurnover.year3 == 2014
        espd.generalYearlyTurnover.amount3 == 333.3
        espd.generalYearlyTurnover.currency3 == "USD"

        then: "info electronically"
        espd.generalYearlyTurnover.availableElectronically.exists == true
        espd.generalYearlyTurnover.availableElectronically.url == "www.hodor.com"
        espd.generalYearlyTurnover.availableElectronically.code == "GENERAL_TURNOVER"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true,
                year1: 2016, amount1: 111.1, currency1: "RON",
                year2: 2015, amount2: 222.2, currency2: "EUR",
                year3: 2014, amount3: 333.3, currency3: "USD",
                availableElectronically: new AvailableElectronically(exists: true, url: "www.hodor.com", code: "GENERAL_TURNOVER")))

        expect:
        1 == 1
    }

}