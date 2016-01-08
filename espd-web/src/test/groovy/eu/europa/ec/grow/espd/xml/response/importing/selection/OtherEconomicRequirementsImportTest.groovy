package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/8/16 at 3:32 PM.
 */
class OtherEconomicRequirementsImportTest extends AbstractXmlFileImport {

    def "12. should import all fields of 'Other economic or financial requirements'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/other_economic_requirements_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.otherEconomicFinancialRequirements.exists == true
        espd.otherEconomicFinancialRequirements.description == "Other economic Hodor"

        then: "info electronically"
        espd.otherEconomicFinancialRequirements.availableElectronically.exists == true
        espd.otherEconomicFinancialRequirements.availableElectronically.url == "www.hodor.com"
        espd.otherEconomicFinancialRequirements.availableElectronically.code == "OTHER_ECONOMIC"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(otherEconomicFinancialRequirements: new EconomicFinancialStandingCriterion(exists: true,
                description: "Other economic Hodor",
                availableElectronically: new AvailableElectronically(exists: true, url: "www.hodor.com", code: "OTHER_ECONOMIC")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}