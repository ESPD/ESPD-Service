package eu.europa.ec.grow.espd.business
import eu.europa.ec.grow.espd.config.EspdApplication
import eu.europa.ec.grow.espd.domain.EspdDocument
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
        jaxb2Marshaller = new EspdApplication().jaxb2Marshaller()
        toEspdRequestTransformer = new EspdDocumentToEspdRequestTransformer()
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

    def "should contain UBLVersionID element information"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        def result = new XmlSlurper().parseText(out.toString())

        then:
        result.UBLVersionID.text() == "2.1"
        result.UBLVersionID.@schemeAgencyID.text() == "OASIS-UBL-TC"
    }

    def "should contain CustomizationID element information"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        def result = new XmlSlurper().parseText(out.toString())

        then:
        result.CustomizationID.text() == "urn:www.cenbii.eu:transaction:biitrns070:ver3.0"
        result.CustomizationID.@schemeAgencyID.text() == "BII"
        result.CustomizationID.@schemeVersionID.text() == "3.0"
        result.CustomizationID.@schemeName.text() == "CustomizationID"
    }

    def "should contain ID element information"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        def result = new XmlSlurper().parseText(out.toString())

        then:
        result.ID.text().startsWith("ESPDREQ")
        result.ID.@schemeAgencyID.text() == "COM-DG-CNNECT"
        result.ID.@schemeAgencyName.text() == "European Commission, Directorate-General for Communications Networks, Content and Technology"
    }

    def "should contain CopyIndicator element information"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        def result = new XmlSlurper().parseText(out.toString())

        then:
        result.CopyIndicator.size() == 1
        result.CopyIndicator.toBoolean() == false
    }

    def "should contain UUID element information"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        def result = new XmlSlurper().parseText(out.toString())

        then:
        result.UUID.text() == "b9d2a2d2-4108-11e5-a151-feff819cdc9f"
        result.UUID.@schemeAgencyID.text() == "COM-DG-GROW"
    }

    def "should contain VersionID element information"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        def result = new XmlSlurper().parseText(out.toString())

        then:
        result.VersionID.text() == "1"
        result.VersionID.@schemeAgencyID.text() == "COM-DG-GROW"
    }

    def "should contain IssueDate element information"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        def result = new XmlSlurper().parseText(out.toString())

        then: "issue date must match the date format YYYY-MM-dd"
        (result.IssueDate.text() ==~ "\\d{4}-\\d{2}-\\d{2}") == true
    }

    def "should contain IssueTime element information"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        def result = new XmlSlurper().parseText(out.toString())

        then: "issue time must match the time format HH:mm:ss"
        (result.IssueTime.text() ==~ "\\d{2}:\\d{2}:\\d{2}") == true
    }

    def "should contain ContractFolderID element information"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        def result = new XmlSlurper().parseText(out.toString())

        then:
        result.ContractFolderID.text() == "SMART 2015/0065"
        result.ContractFolderID.@schemeAgencyID.text() == "TeD"
    }

    def "should contain ProcurementProjectLot element information when there are no lots"() {
        when:
        marshaller.generateEspdRequest(new EspdDocument(), out)
        println out.toString()
        def result = new XmlSlurper().parseText(out.toString())

        then: "In a Procurement Project with no Lots one ProcurementProjectLot element, and only one, MUST be included in the XML instance"
        result.ProcurementProjectLot.size() == 1

        then: "The identifier for this single ProcurementProjectLot MUST be the number 0"
        result.ProcurementProjectLot.ID.text() == "0"
    }

}
