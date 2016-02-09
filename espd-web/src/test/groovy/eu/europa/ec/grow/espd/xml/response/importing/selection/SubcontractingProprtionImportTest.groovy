package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/8/16 at 1:54 PM.
 */
class SubcontractingProprtionImportTest extends AbstractXmlFileImport {

    def "28. should import all fields of 'Subcontracting proportion'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/subcontracting_proportion_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.subcontractingProportion.exists == true

        then: "answer is null and it is a selection criterion so the default value should be true"
        espd.subcontractingProportion.answer == true
        espd.subcontractingProportion.percentage == 66.6

        then: "info electronically"
        espd.subcontractingProportion.availableElectronically.answer == true
        espd.subcontractingProportion.availableElectronically.url == "www.hodor.com"
        espd.subcontractingProportion.availableElectronically.code == "SUBCONTRACTING"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(subcontractingProportion: new TechnicalProfessionalCriterion(exists: true, answer: true,
                percentage: 66.6,
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "SUBCONTRACTING")))
        //        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}