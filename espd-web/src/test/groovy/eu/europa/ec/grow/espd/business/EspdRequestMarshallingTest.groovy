package eu.europa.ec.grow.espd.business
import eu.europa.ec.grow.espd.config.JaxbConfiguration
import eu.europa.ec.grow.espd.constants.Country
import eu.europa.ec.grow.espd.domain.EspdDocument
import groovy.util.slurpersupport.GPathResult
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import spock.lang.Shared
import spock.lang.Specification
/**
 * Created by vigi on 11/11/15:3:31 PM.
 */
class EspdRequestMarshallingTest extends Specification {

    @Shared
    Jaxb2Marshaller jaxb2Marshaller

    @Shared
    EspdDocumentToEspdRequestTransformer toEspdRequestTransformer

    @Shared
    EspdExchangeMarshaller marshaller

    StringWriter out

    void setupSpec() {
        jaxb2Marshaller = new JaxbConfiguration().jaxb2Marshaller()
        def contractingPartyTransformer = new ToContractingPartyTransformer()
        toEspdRequestTransformer = new EspdDocumentToEspdRequestTransformer(contractingPartyTransformer)
        marshaller = new EspdExchangeMarshaller(jaxb2Marshaller, toEspdRequestTransformer)
    }

    void cleanupSpec() {
        marshaller = null
        toEspdRequestTransformer = null
        jaxb2Marshaller = null
    }

    void setup() {
        out = new StringWriter()
    }

    void cleanup() {
        out = null
    }

    def "should make sure that we use the correct XML namespaces"() {
        when:
        def result = parseXml()

        then:
        result.lookupNamespace('cbc') == 'urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2'
        result.lookupNamespace('cac') == 'urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2'
        result.lookupNamespace('ccv') == 'urn:isa:names:specification:ubl:schema:xsd:CCV-CommonAggregateComponents-1'
//        result.list()[0].namespacePrefix.text() == 'urn:grow:names:specification:ubl:schema:xsd:ESPDRequest-1'
    }

    def "should contain UBLVersionID element information"() {
        when:
        def result = parseXml()

        then:
        result.UBLVersionID.text() == "2.1"
        result.UBLVersionID.@schemeAgencyID.text() == "OASIS-UBL-TC"
    }

    def "should contain CustomizationID element information"() {
        when:
        def result = parseXml()

        then:
        result.CustomizationID.text() == "urn:www.cenbii.eu:transaction:biitrns070:ver3.0"
        result.CustomizationID.@schemeAgencyID.text() == "BII"
        result.CustomizationID.@schemeVersionID.text() == "3.0"
        result.CustomizationID.@schemeName.text() == "CustomizationID"
    }

    def "should contain ID element information"() {
        when:
        def result = parseXml()

        then: "id is an UUID"
        result.ID.text().length() == 36

        then:
        result.ID.@schemeAgencyID.text() == "COM-DG-GROW"
        result.ID.@schemeAgencyName.text() == "European Commission, Directorate-General GROWTH, Internal Market, Industry, Entrepreneurship and SMEs"
        result.ID.@schemeVersionID.text() == "1.1"
        result.ID.@schemeID.text() == "ISO/IEC 9834-8:2008 - 4UUID"
    }

    def "should contain CopyIndicator element information"() {
        when:
        def result = parseXml()

        then:
        result.CopyIndicator.size() == 1
        result.CopyIndicator.toBoolean() == false
    }

    def "should contain VersionID element information"() {
        when:
        def result = parseXml()

        then:
        result.VersionID.text() == "1"
        result.VersionID.@schemeAgencyID.text() == "COM-DG-GROW"
    }

    def "should contain IssueDate element information"() {
        when:
        def result = parseXml()

        then: "issue date must match the date format YYYY-MM-dd"
        result.IssueDate.text() ==~ "\\d{4}-\\d{2}-\\d{2}"
    }

    def "should contain IssueTime element information"() {
        when:
        def result = parseXml()

        then: "issue time must match the time format HH:mm:ss"
        result.IssueTime.text() ==~ "\\d{2}:\\d{2}:\\d{2}"
    }

    def "should contain ContractFolderID element information"() {
        when:
        def result = parseXml()

        then:
        result.ContractFolderID.text() == "SMART 2015/0065"
        result.ContractFolderID.@schemeAgencyID.text() == "TeD"
    }

    def "should transform ContractingParty element information"() {
        given:
        def espd = new EspdDocument(authorityName: "Hodor authority", natRegNumber: "Hodor national reg number",
        streetAndNumber: "Hodor street", postcode: "Hodor postcode", city: "Hodor city", country: Country.ROMANIA,
        contactPerson: "Hodor contact person", email: "hodor@hodor.com", telephone: "555-HODOR",
        website: "www.hodor.com")

        when:
        marshaller.generateEspdRequest(espd, out)
        def result = new XmlSlurper().parseText(out.toString())

        then:
        result.ContractingParty.Party.PartyName.Name.text() == "Hodor authority"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.text() == "RO"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listAgencyID.text() == "ISO"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listName.text() == "ISO 3166-1"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listVersionID.text() == "1.0"
    }


    def "should contain ProcurementProjectLot element information when there are no lots"() {
        when:
        def result = parseXml()
        println out.toString()

        then: "In a Procurement Project with no Lots one ProcurementProjectLot element, and only one, MUST be included in the XML instance"
        result.ProcurementProjectLot.size() == 1

        then: "The identifier for this single ProcurementProjectLot MUST be the number 0"
        result.ProcurementProjectLot.ID.text() == "0"
    }

    private GPathResult parseXml() {
        marshaller.generateEspdRequest(new EspdDocument(), out)
        new XmlSlurper().parseText(out.toString())
    }

}
