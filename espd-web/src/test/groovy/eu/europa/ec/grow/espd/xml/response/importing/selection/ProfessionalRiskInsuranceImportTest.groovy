package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/8/16 at 3:25 PM.
 */
class ProfessionalRiskInsuranceImportTest extends AbstractXmlFileImport {

    def "12. should import all fields of 'Professional risk indemnity insurance'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/professional_risk_insurance_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.professionalRiskInsurance.exists == true

        then: "selection criteria with no answer have a default value of true"
        espd.professionalRiskInsurance.answer == true

        then:
        espd.professionalRiskInsurance.amount1 == 777.7
        espd.professionalRiskInsurance.currency1 == "RON"

        then: "info electronically"
        espd.professionalRiskInsurance.availableElectronically.answer == true
        espd.professionalRiskInsurance.availableElectronically.url == "www.hodor.com"
        espd.professionalRiskInsurance.availableElectronically.code == "RISK_INSURANCE"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(professionalRiskInsurance: new EconomicFinancialStandingCriterion(exists: true, answer: true,
                amount1: 777.7, currency1: "RON",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "RISK_INSURANCE")))
        //        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}