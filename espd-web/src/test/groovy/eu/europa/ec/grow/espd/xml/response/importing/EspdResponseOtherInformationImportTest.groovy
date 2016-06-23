/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml.response.importing
import eu.europa.ec.grow.espd.domain.enums.other.Country
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.LocalTimeAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import spock.lang.Shared

/**
 * Created by ratoico on 1/19/16 at 10:20 AM.
 */
class EspdResponseOtherInformationImportTest extends AbstractXmlFileImport {

    @Shared
    static EspdDocument espdFull
    @Shared
    static EspdDocument espdMinimal

    void setupSpec() {
        // init objects run before the first feature method
        espdFull = parseXmlResponseFile("response_other_information_full_import.xml")
        espdMinimal = parseXmlResponseFile("response_other_information_minimal_import.xml")
    }

    void cleanupSpec() {
        espdFull = null
        espdMinimal = null
    }

    def "should import economic operator party full information"() {
        expect:
        espdFull.economicOperator.name == "ACME Corp."
        espdFull.economicOperator.website == "www.hodor.com"
        espdFull.economicOperator.vatNumber == "B207781243"
        espdFull.economicOperator.anotherNationalId == "eo another national identification number"
        espdFull.economicOperator.street == "Vitruvio"
        espdFull.economicOperator.postalCode == "28006"
        espdFull.economicOperator.city == "Madrid"
        espdFull.economicOperator.country == Country.ES
        espdFull.economicOperator.contactName == "hodor"
        espdFull.economicOperator.contactPhone == "+666"
        espdFull.economicOperator.contactEmail == "hodor@hodor.com"
        espdFull.economicOperator.isSmallSizedEnterprise == false
        espdFull.lotConcerned == "hodor lot"
    }

    def "should import economic operator party minimal information"() {
        expect:
        espdMinimal.economicOperator.name == null
        espdMinimal.economicOperator.website == null
        espdMinimal.economicOperator.vatNumber == null
        espdMinimal.economicOperator.anotherNationalId == null
        espdMinimal.economicOperator.street == null
        espdMinimal.economicOperator.postalCode == null
        espdMinimal.economicOperator.city == null
        espdMinimal.economicOperator.country == null
        espdMinimal.economicOperator.contactName == null
        espdMinimal.economicOperator.contactPhone == null
        espdMinimal.economicOperator.contactEmail == null
        espdMinimal.economicOperator.isSmallSizedEnterprise == null
        espdMinimal.lotConcerned == null
    }

    def "should import economic operator representative full information"() {
        expect:
        espdFull.economicOperator.representative.firstName == "Emilio"
        espdFull.economicOperator.representative.lastName == "García De Tres Torres"
        espdFull.economicOperator.representative.dateOfBirth == LocalDateAdapter.unmarshal("1960-01-19").toDate()
        espdFull.economicOperator.representative.placeOfBirth == "València, Spain"
        espdFull.economicOperator.representative.street == "Vitruvio"
        espdFull.economicOperator.representative.postalCode == "28006"
        espdFull.economicOperator.representative.city == "Edinborough"
        espdFull.economicOperator.representative.country == Country.GB
        espdFull.economicOperator.representative.email == "emilio.garcia3torres@acme.com"
        espdFull.economicOperator.representative.phone == "+34 96 123 456"
        espdFull.economicOperator.representative.position == "Empowered to represent the Consortium"
        espdFull.economicOperator.representative.additionalInfo == "Can represent ACME, Corp. and the Consortia to which ACME, Corp"
    }

    def "should import economic operator representative minimal information"() {
        expect:
        espdMinimal.economicOperator.representative.firstName == null
        espdMinimal.economicOperator.representative.lastName == null
        espdMinimal.economicOperator.representative.dateOfBirth == null
        espdMinimal.economicOperator.representative.placeOfBirth == null
        espdMinimal.economicOperator.representative.street == null
        espdMinimal.economicOperator.representative.postalCode == null
        espdMinimal.economicOperator.representative.city == null
        espdMinimal.economicOperator.representative.country == null
        espdMinimal.economicOperator.representative.email == null
        espdMinimal.economicOperator.representative.phone == null
        espdMinimal.economicOperator.representative.position == null
        espdMinimal.economicOperator.representative.additionalInfo == null
    }

    def "should import lots information"() {
        expect:
        espdFull.lotConcerned == "hodor lot"
    }

    def "should import espd request full information"() {
        expect:
        espdFull.requestMetadata.id == "4a1a633c-25fa-4c4d-abd8-89c623f9e9ec"
        espdFull.requestMetadata.url == "http://europa.ec.eu/espd/request/4a1a633c-25fa-4c4d-abd8-89c623f9e9ec"
        espdFull.requestMetadata.description == "ESPDRequest SMART 2015/0065"
        LocalDateAdapter.marshal(new LocalDate(espdFull.requestMetadata.issueDate)) == "2015-12-18"
        LocalTimeAdapter.marshal(new LocalTime(espdFull.requestMetadata.issueDate)) == "17:46:54"
    }

    def "should import espd request minimal information"() {
        expect:
        espdMinimal.requestMetadata.id == null
        espdMinimal.requestMetadata.url == null
        espdMinimal.requestMetadata.description == null
        espdMinimal.requestMetadata.issueDate == null
    }

    def "should parse TED procurement procedure information"() {
        expect:
        espdFull.fileRefByCA == "SMART 2015/0065"
        espdFull.ojsNumber == "6d48f751-53cc-4d7f-9dfb-21c3e802b2e0"
        espdFull.procedureTitle == "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015"
        espdFull.procedureShortDesc == "Service category No 11: Management consulting services [6] and related services."
        espdFull.tedUrl == "http://ted.europa.eu/udl?uri=TED:NOTICE:373035-2015:TEXT:EN:HTML"
    }

    def "we should not load the ojs number if it is marked as a temporary one in the scheme id attribute of the id"() {
        given:
        def espdXml = importXmlResponseFile("response_temporary_ojs_number_import.xml")
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdXml)).get()

        expect:
        espd.fileRefByCA == "SMART 2015/0065"
        espd.ojsNumber == null
        espd.procedureTitle == "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015"
        espd.procedureShortDesc == "Service category No 11: Management consulting services [6] and related services."
        espd.tedUrl == "http://ted.europa.eu/udl?uri=TED:NOTICE:373035-2015:TEXT:EN:HTML"
    }

    def "should import issue date"() {
        expect:
        LocalDateAdapter.marshal(new LocalDate(espdFull.documentDate)) == "2015-11-25"
    }

    def "should import signature information"() {
        expect:
        espdFull.location == "Eastwatch by the Sea"
    }

}