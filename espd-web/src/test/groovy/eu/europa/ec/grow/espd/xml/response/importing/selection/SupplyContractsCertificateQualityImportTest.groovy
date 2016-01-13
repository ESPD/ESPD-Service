package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/13/16 at 10:57 AM.
 */
class SupplyContractsCertificateQualityImportTest extends AbstractXmlFileImport {

    def "30. should import all fields of 'For supply contracts: certificates by quality control institutes'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/supply_contracts_certificates_quality_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.supplyContractsCertificatesQc.exists == true
        espd.supplyContractsCertificatesQc.description == "Another description"

        then: "info electronically"
        espd.supplyContractsCertificatesQc.availableElectronically.exists == true
        espd.supplyContractsCertificatesQc.availableElectronically.url == "www.hodor.com"
        espd.supplyContractsCertificatesQc.availableElectronically.code == "TECHNICAL_QUALITY"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(supplyContractsCertificatesQc: new TechnicalProfessionalCriterion(exists: true,
                description: "Another description",
                availableElectronically: new AvailableElectronically(exists: true, url: "www.hodor.com", code: "TECHNICAL_QUALITY")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}