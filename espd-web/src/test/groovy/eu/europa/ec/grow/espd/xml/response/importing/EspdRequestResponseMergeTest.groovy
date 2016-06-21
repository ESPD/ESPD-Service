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

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.enums.other.Country
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.LocalTimeAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import spock.lang.Shared

/**
 * Created by ratoico on 3/8/16 at 4:11 PM.
 */
class EspdRequestResponseMergeTest extends AbstractXmlFileImport {

    @Shared
    static EspdDocument espd

    void setupSpec() {
        // init objects run before the first feature method
        def espdRequestXml = importXmlRequestFile("../response/merging/request_to_merge.xml")
        def espdResponseXml = importXmlResponseFile("merging/response_to_merge.xml")
        espd = marshaller.mergeEspdRequestAndResponse(IOUtils.toInputStream(espdRequestXml), IOUtils.toInputStream(espdResponseXml)).get()
    }

    void cleanupSpec() {
        espd = null
    }

    def "a merge between Request and Response should contain the criteria from the Request'"() {
        expect: "the Response contains three criteria but the Request only one so only the criterion from the Request should be present"
        espd.criminalConvictions.exists == true
        espd.criminalConvictions.answer == true
        espd.criminalConvictions.reason == "Participation in a criminal organisation reason"

        and: "payment of taxes"
        espd.paymentTaxes.exists == true
        espd.paymentTaxes.answer == false

        and: "enrolment professional register"
        espd.enrolmentProfessionalRegister.exists == true
        espd.enrolmentProfessionalRegister.answer == true

        and: "the other criteria from the response should not be present because they were not required in the request"
        espd.corruption == null
        espd.generalYearlyTurnover == null
    }

    def "should contain the economic operator criteria set in the response"() {
        expect:
        espd.meetsObjective.exists == true

        and: "there is no answer"
        espd.meetsObjective.answer == false
        espd.meetsObjective.description1 == "please describe"

        and: "info electronically"
        espd.meetsObjective.availableElectronically.answer == true
        espd.meetsObjective.availableElectronically.url == "www.hodor.com"
        espd.meetsObjective.availableElectronically.code == "MEETS"
    }

    def "a merge between Request and Response should get the EO information from the Response"() {
        expect:
        espd.economicOperator.name == "ACME Corp."
        espd.economicOperator.website == "www.hodor.com"
        espd.economicOperator.vatNumber == "B207781243"
        espd.economicOperator.anotherNationalId == "eo another national identification number"
        espd.economicOperator.street == "Vitruvio"
        espd.economicOperator.postalCode == "28006"
        espd.economicOperator.city == "Madrid"
        espd.economicOperator.country == Country.ES
        espd.economicOperator.contactName == "hodor"
        espd.economicOperator.contactPhone == "+666"
        espd.economicOperator.contactEmail == "hodor@hodor.com"
        espd.economicOperator.isSmallSizedEnterprise == false
        espd.lotConcerned == "hodor lot"
    }

    def "should import economic operator representative full information"() {
        expect:
        espd.economicOperator.representative.firstName == "Emilio"
        espd.economicOperator.representative.lastName == "García De Tres Torres"
        espd.economicOperator.representative.dateOfBirth == LocalDateAdapter.unmarshal("1960-01-19").toDate()
        espd.economicOperator.representative.placeOfBirth == "València, Spain"
        espd.economicOperator.representative.street == "Vitruvio"
        espd.economicOperator.representative.postalCode == "28006"
        espd.economicOperator.representative.city == "Madrid"
        espd.economicOperator.representative.country == Country.ES
        espd.economicOperator.representative.email == "emilio.garcia3torres@acme.com"
        espd.economicOperator.representative.phone == "+34 96 123 456"
        espd.economicOperator.representative.position == "Empowered to represent the Consortium"
        espd.economicOperator.representative.additionalInfo == "Can represent ACME, Corp. and the Consortia to which ACME, Corp"
    }

    def "should import espd request full information"() {
        expect:
        espd.requestMetadata.id == "4a1a633c-25fa-4c4d-abd8-89c623f9e9ec"
        espd.requestMetadata.url == "http://europa.ec.eu/espd/request/4a1a633c-25fa-4c4d-abd8-89c623f9e9ec"
        espd.requestMetadata.description == "ESPDRequest SMART 2015/0065"
        LocalDateAdapter.marshal(new LocalDate(espd.requestMetadata.issueDate)) == "2015-12-18"
        LocalTimeAdapter.marshal(new LocalTime(espd.requestMetadata.issueDate)) == "17:46:54"
    }

    def "should parse TED procurement procedure information"() {
        expect:
        espd.fileRefByCA == "SMART 2015/0065"
        espd.ojsNumber == "6d48f751-53cc-4d7f-9dfb-21c3e802b2e0"
        espd.procedureTitle == "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015"
        espd.procedureShortDesc == "Service category No 11: Management consulting services [6] and related services."
        espd.tedUrl == "http://ted.europa.eu/udl?uri=TED:NOTICE:373035-2015:TEXT:EN:HTML"
    }

}