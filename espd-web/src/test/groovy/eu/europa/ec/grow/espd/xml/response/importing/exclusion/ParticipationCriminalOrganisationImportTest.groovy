package eu.europa.ec.grow.espd.xml.response.importing.exclusion

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.CriminalConvictionsCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/7/16 at 10:41 AM.
 */
class ParticipationCriminalOrganisationImportTest extends AbstractXmlFileImport {

    def "01. should import all fields of 'Participation in a criminal organisation'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/participation_criminal_organisation_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml))

        then:
        espd.criminalConvictions.exists == true
        espd.criminalConvictions.answer == true
        espd.criminalConvictions.dateOfConviction == LocalDateAdapter.unmarshal("2016-01-17").toDate()
        espd.criminalConvictions.reason == "Reason here"
        espd.criminalConvictions.convicted == "Hodor was convicted"
        espd.criminalConvictions.periodLength == "7 years"

        then: "self cleaning"
        espd.criminalConvictions.selfCleaning.answer == true
        espd.criminalConvictions.selfCleaning.description == "Hodor is clean"

        then: "info electronically"
        espd.criminalConvictions.availableElectronically.answer == true
        espd.criminalConvictions.availableElectronically.url == "www.hodor.com"
        espd.criminalConvictions.availableElectronically.code == "INTERNATIONAL"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: false,  answer: true,
                dateOfConviction: new Date(),
                reason: "Reason here", convicted: "Hodor was convicted", periodLength: "7 years",
                selfCleaning: new SelfCleaning(answer: true, description: "Hodor is clean"),
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "INTERNATIONAL")))

        expect:
        1 == 1
    }

}