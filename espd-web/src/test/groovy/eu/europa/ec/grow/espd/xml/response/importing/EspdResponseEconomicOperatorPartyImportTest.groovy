package eu.europa.ec.grow.espd.xml.response.importing
import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/19/16 at 10:20 AM.
 */
class EspdResponseEconomicOperatorPartyImportTest extends AbstractXmlFileImport {

    def "should import economic operator party full information"() {
        given:
        def espdRequestXml = importXmlResponseFile("economic_operator_party_full_import.xml")

        when:
        def espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdRequestXml))

        then:
        espd.economicOperator.name == "ACME Corp."
        espd.economicOperator.website == "www.hodor.com"
        espd.economicOperator.vatNumber == "B207781243"
        espd.economicOperator.street == "Vitruvio"
        espd.economicOperator.postalCode == "28006"
        espd.economicOperator.city == "Madrid"
        espd.economicOperator.country == Country.SPAIN
        espd.economicOperator.contactName == "hodor"
        espd.economicOperator.contactPhone == "+666"
        espd.economicOperator.contactEmail == "hodor@hodor.com"
        espd.economicOperator.isSmallSizedEnterprise == false

        then:
        espd.lotConcerned == "hodor lot"
    }

    def "should import economic operator party minimal information"() {
        given:
        def espdRequestXml = importXmlResponseFile("economic_operator_party_minimal_import.xml")

        when:
        def espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdRequestXml))

        then:
        espd.economicOperator.name == null
        espd.economicOperator.website == null
        espd.economicOperator.vatNumber == null
        espd.economicOperator.street == null
        espd.economicOperator.postalCode == null
        espd.economicOperator.city == null
        espd.economicOperator.country == null
        espd.economicOperator.contactName == null
        espd.economicOperator.contactPhone == null
        espd.economicOperator.contactEmail == null
        espd.economicOperator.isSmallSizedEnterprise == null
    }

    def "should import economic operator representative full information"() {
        given:
        def espdRequestXml = importXmlResponseFile("economic_operator_party_full_import.xml")

        when:
        def espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdRequestXml))

        then:
        espd.economicOperator.representative.firstName == "Emilio"
        espd.economicOperator.representative.lastName == "García De Tres Torres"
        espd.economicOperator.representative.dateOfBirth == LocalDateAdapter.unmarshal("1960-19-01").toDate()
        espd.economicOperator.representative.placeOfBirth == "València, Spain"
        espd.economicOperator.representative.street == "Vitruvio"
        espd.economicOperator.representative.postalCode == "28006"
        espd.economicOperator.representative.city == "Madrid"
        espd.economicOperator.representative.country == Country.SPAIN
        espd.economicOperator.representative.email == "emilio.garcia3torres@acme.com"
        espd.economicOperator.representative.phone == "+34 96 123 456"
        espd.economicOperator.representative.position == "Empowered to represent the Consortium"
        espd.economicOperator.representative.additionalInfo == "Can represent ACME, Corp. and the Consortia to which ACME, Corp"
    }

    def "should import economic operator representative minimal information"() {
        given:
        def espdRequestXml = importXmlResponseFile("economic_operator_party_minimal_import.xml")

        when:
        def espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdRequestXml))

        then:
        espd.economicOperator.representative.firstName == null
        espd.economicOperator.representative.lastName == null
        espd.economicOperator.representative.dateOfBirth == null
        espd.economicOperator.representative.placeOfBirth == null
        espd.economicOperator.representative.street == null
        espd.economicOperator.representative.postalCode == null
        espd.economicOperator.representative.city == null
        espd.economicOperator.representative.country == null
        espd.economicOperator.representative.email == null
        espd.economicOperator.representative.phone == null
        espd.economicOperator.representative.position == null
        espd.economicOperator.representative.additionalInfo == null
    }

}