package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/8/16 at 1:49 PM.
 */
class TechnicalFacilitiesQualityImportTest extends AbstractXmlFileImport {

    def "18. should import all fields of 'Technical facilities and measures for ensuring quality'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/technical_facilities_quality_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.technicalFacilitiesMeasures.exists == true
        espd.technicalFacilitiesMeasures.description == "Another description"

        then: "info electronically"
        espd.technicalFacilitiesMeasures.availableElectronically.exists == true
        espd.technicalFacilitiesMeasures.availableElectronically.url == "www.hodor.com"
        espd.technicalFacilitiesMeasures.availableElectronically.code == "TECHNICAL_QUALITY"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(technicalFacilitiesMeasures: new TechnicalProfessionalCriterion(exists: true,
                description: "Another description",
                availableElectronically: new AvailableElectronically(exists: true, url: "www.hodor.com", code: "TECHNICAL_QUALITY")))
        //        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}