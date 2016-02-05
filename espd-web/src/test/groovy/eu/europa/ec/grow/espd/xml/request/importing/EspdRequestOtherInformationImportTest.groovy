package eu.europa.ec.grow.espd.xml.request.importing

import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.LocalTimeAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import spock.lang.Shared

/**
 * Created by ratoico on 1/18/16 at 2:56 PM.
 */
class EspdRequestOtherInformationImportTest extends AbstractXmlFileImport {

    @Shared
    static def espdRequestFullXml
    @Shared
    static def espdRequestMinimalXml

    @Shared
    static EspdDocument espdFull
    @Shared
    static EspdDocument espdMinimal

    void setupSpec() {
        // init objects run before the first feature method
        espdRequestFullXml = importXmlRequestFile("request_other_information_full_import.xml")
        espdRequestMinimalXml = importXmlRequestFile("request_other_information_minimal_import.xml")
        espdFull = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestFullXml))
        espdMinimal = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestMinimalXml))
    }

    void cleanupSpec() {
        espdRequestFullXml = null
        espdRequestMinimalXml = null
        espdFull = null
        espdMinimal = null
    }

    def "should parse full authority information"() {
        expect:
        espdFull.authority.name == "hodor"
        espdFull.authority.website == "www.hodor.com"
        espdFull.authority.vatNumber == "43354d43323"
        espdFull.authority.street == "elm street"
        espdFull.authority.postalCode == "1500"
        espdFull.authority.city == "drubetis"
        espdFull.authority.country == Country.ROMANIA
        espdFull.authority.contactName == "gogu"
        espdFull.authority.contactPhone == "+43435543"
        espdFull.authority.contactEmail == "gogu@gogu.com"
        espdFull.lotConcerned == "hodor lot"
    }

    def "should parse minimal authority information"() {
        expect:
        espdMinimal.authority.name == "hodor"
        espdMinimal.authority.website == null
        espdMinimal.authority.vatNumber == null
        espdMinimal.authority.street == null
        espdMinimal.authority.postalCode == null
        espdMinimal.authority.city == null
        espdMinimal.authority.country == Country.ROMANIA
        espdMinimal.authority.contactName == null
        espdMinimal.authority.contactPhone == null
        espdMinimal.authority.contactEmail == null
        espdMinimal.lotConcerned == null
    }

    def "should parse procurement procedure information"() {
        expect:
        espdFull.fileRefByCA == "SMART 2016/0069"
        espdFull.ojsNumber == "S206|2015-10-23|PN33|2015/S206-373035"
        espdFull.procedureTitle == "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015"
        espdFull.procedureShortDesc == "Service category No 11: Management consulting services [6] and related services."
        espdFull.tedUrl == "http://ted.europa.eu/udl?uri=TED:NOTICE:373035-2015:TEXT:EN:HTML"
    }

    def "should import espd request full information"() {
        expect:
        espdFull.requestMetadata.id == "c0b5454f-c8bf-465f-afe7-6ba3f50c6589"
        espdFull.requestMetadata.description == "ESPDRequest SMART 2016/0069"
        LocalDateAdapter.marshal(new LocalDate(espdFull.requestMetadata.issueDate)) == "2015-01-13"

        and: "no url at the moment"
        espdFull.requestMetadata.url == null

        and: "the time is not present in the xml"
        LocalTimeAdapter.marshal(new LocalTime(espdFull.requestMetadata.issueDate)) == "00:00:00"
    }

}