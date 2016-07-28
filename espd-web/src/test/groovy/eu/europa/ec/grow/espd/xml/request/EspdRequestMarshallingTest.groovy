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

package eu.europa.ec.grow.espd.xml.request

import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion
import eu.europa.ec.grow.espd.domain.enums.other.Country
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.PartyImpl
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture
/**
 *  Created by vigi on 11/11/15:3:31 PM:11:56 AM.
 */
class EspdRequestMarshallingTest extends AbstractCriteriaFixture {

    def "should make sure that we use the correct XML namespaces"() {
        when:
        def result = parseRequestXml()

        then:
        result.lookupNamespace('espd-req') == 'urn:grow:names:specification:ubl:schema:xsd:ESPDRequest-1'
        result.lookupNamespace('espd') == 'urn:grow:names:specification:ubl:schema:xsd:ESPDResponse-1'
        result.lookupNamespace('espd-cac') == 'urn:grow:names:specification:ubl:schema:xsd:ESPD-CommonAggregateComponents-1'
        result.lookupNamespace('espd-cbc') == 'urn:grow:names:specification:ubl:schema:xsd:ESPD-CommonBasicComponents-1'
        result.lookupNamespace('cbc') == 'urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2'
        result.lookupNamespace('cac') == 'urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2'
        result.lookupNamespace('ccv') == 'urn:isa:names:specification:ubl:schema:xsd:CCV-CommonAggregateComponents-1'
        result.lookupNamespace('ccv-cbc') == 'urn:isa:names:specification:ubl:schema:xsd:CCV-CommonBasicComponents-1'
        result.lookupNamespace('cev-cbc') == 'urn:isa:names:specification:ubl:schema:xsd:CEV-CommonBasicComponents-1'
    }

    def "should contain UBLVersionID element information"() {
        when:
        def result = parseRequestXml()

        then:
        result.UBLVersionID.text() == "2.1"
        result.UBLVersionID.@schemeAgencyID.text() == "OASIS-UBL-TC"
    }

    def "should contain CustomizationID element information"() {
        when:
        def result = parseRequestXml()

        then:
        result.CustomizationID.text() == "urn:www.cenbii.eu:transaction:biitrns070:ver3.0"
        result.CustomizationID.@schemeAgencyID.text() == "BII"
        result.CustomizationID.@schemeVersionID.text() == "3.0"
        result.CustomizationID.@schemeName.text() == "CustomizationID"
    }

    def "should contain ID element information"() {
        when:
        def result = parseRequestXml()

        then: "id is an UUID"
        result.ID.text().length() == 36

        then:
        result.ID.@schemeAgencyID.text() == "EU-COM-GROW"
        result.ID.@schemeAgencyName.text() == "DG GROW (European Commission)"
        result.ID.@schemeVersionID.text() == "1.1"
        result.ID.@schemeID.text() == "ISO/IEC 9834-8:2008 - 4UUID"
    }

    def "should contain CopyIndicator element information"() {
        when:
        def result = parseRequestXml()

        then:
        result.CopyIndicator.size() == 1
        result.CopyIndicator.toBoolean() == false
    }

    def "should contain VersionID element information"() {
        when:
        def result = parseRequestXml()

        then:
        result.VersionID.text() == "2016.4"
        result.VersionID.@schemeAgencyID.text() == "EU-COM-GROW"
    }

    def "should contain IssueDate element information"() {
        when:
        def result = parseRequestXml()

        then: "issue date must match the date format YYYY-MM-dd"
        result.IssueDate.text() ==~ "\\d{4}-\\d{2}-\\d{2}"
    }

    def "should contain IssueTime element information"() {
        when:
        def result = parseRequestXml()

        then: "issue time must match the time format HH:mm:ss"
        result.IssueTime.text() ==~ "\\d{2}:\\d{2}:\\d{2}"
    }

    def "should contain ContractFolderID element information"() {
        given:
        def espd = new EspdDocument(fileRefByCA: "HODOR refd by CA")

        when:
        def result = parseRequestXml(espd)

        then:
        result.ContractFolderID.text() == "HODOR refd by CA"
        result.ContractFolderID.@schemeAgencyID.text() == "TeD"
    }

    def "should contain AdditionalDocumentReference element information"() {
        given:
        def espd = new EspdDocument(ojsNumber: "S206|2015-10-23|PN33|2015/S 206-373035",
                procedureTitle: "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015",
                procedureShortDesc: "Service category No 11: Management consulting services [6] and related services.",
                tedUrl: "http://ted.europa.eu/udl?uri=TED:NOTICE:002226-2016:TEXT:ES:HTML",
                tedReceptionId: "16-000136-001"
        )

        when:
        def result = parseRequestXml(espd)

        then:
        result.AdditionalDocumentReference[0].ID.text() == "S206|2015-10-23|PN33|2015/S 206-373035"
        result.AdditionalDocumentReference[0].ID.@schemeID.text() == "ISO/IEC 9834-8:2008 - 4UUID"
        result.AdditionalDocumentReference[0].ID.@schemeAgencyID.text() == "EU-COM-GROW"
        result.AdditionalDocumentReference[0].ID.@schemeAgencyName.text() == "DG GROW (European Commission)"
        result.AdditionalDocumentReference[0].ID.@schemeVersionID.text() == "1.1"

        then:
        result.AdditionalDocumentReference[0].DocumentTypeCode.@listAgencyID.text() == "EU-COM-GROW"
        result.AdditionalDocumentReference[0].DocumentTypeCode.@listID.text() == "ReferencesTypeCodes"
        result.AdditionalDocumentReference[0].DocumentTypeCode.@listVersionID.text() == "1.0"
        result.AdditionalDocumentReference[0].DocumentTypeCode.text() == "TED_CN"

        then:
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.FileName.text() == "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015"
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.Description[0].text() == "Service category No 11: Management consulting services [6] and related services."
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.Description[1].text() == "16-000136-001"
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.URI.text() == "http://ted.europa.eu/udl?uri=TED:NOTICE:002226-2016:TEXT:ES:HTML"
    }

    def "should contain AdditionalDocumentReference with default ID if the TED OJS number is missing"() {
        given:
        def espd = new EspdDocument(ojsNumber: "     ", tedReceptionId: "     ",
                procedureTitle: "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015",
                procedureShortDesc: "Service category No 11: Management consulting services [6] and related services.",
                tedUrl: "http://ted.europa.eu/udl?uri=TED:NOTICE:002226-2016:TEXT:ES:HTML")

        when:
        def result = parseRequestXml(espd)

        then:
        result.AdditionalDocumentReference.size() == 1

        then:
        result.AdditionalDocumentReference[0].ID.text() == "0000/S 000-000000"
        result.AdditionalDocumentReference[0].ID.@schemeID.text() == "COM-GROW-TEMPORARY-ID"
        result.AdditionalDocumentReference[0].ID.@schemeAgencyID.text() == "EU-COM-GROW"
        result.AdditionalDocumentReference[0].ID.@schemeAgencyName.text() == "DG GROW (European Commission)"
        result.AdditionalDocumentReference[0].ID.@schemeVersionID.text() == "1.1"

        then:
        result.AdditionalDocumentReference[0].DocumentTypeCode.@listAgencyID.text() == "EU-COM-GROW"
        result.AdditionalDocumentReference[0].DocumentTypeCode.@listID.text() == "ReferencesTypeCodes"
        result.AdditionalDocumentReference[0].DocumentTypeCode.@listVersionID.text() == "1.0"
        result.AdditionalDocumentReference[0].DocumentTypeCode.text() == "TED_CN"

        then:
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.FileName.text() == "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015"
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.Description[0].text() == "Service category No 11: Management consulting services [6] and related services."
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.URI.text() == "http://ted.europa.eu/udl?uri=TED:NOTICE:002226-2016:TEXT:ES:HTML"
    }

    def "AdditionalDocumentReference element should contain a default description so that we can read tedReceptionId as a second description"() {
        given:
        def espd = new EspdDocument(ojsNumber: "433", procedureShortDesc: "    ",
                tedReceptionId: "16-000136-001"
        )

        when:
        def result = parseRequestXml(espd)

        then:
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.Description[0].text() == "-"
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.Description[1].text() == "16-000136-001"
    }

    def "should contain ContractingParty element information"() {
        given:
        def authority = new PartyImpl(name: "  Hodor authority  ", vatNumber: "  Hodor national reg number  ",
                street: "  Hodor street  ", postalCode: "  Hodor postcode  ", city: "  Hodor city  ", country: Country.RO,
                contactName: "  Hodor contact person  ", contactEmail: "  hodor@hodor.com  ", contactPhone: "  555-HODOR  ",
                website: "  www.hodor.com  ")
        def espd = new EspdDocument(authority: authority)

        when:
        def result = parseRequestXml(espd)

        then: "all values should be trimmed"
        result.ContractingParty.Party.PartyName.Name.text() == "Hodor authority"
        result.ContractingParty.Party.WebsiteURI.text() == "www.hodor.com"

        then: "party identification"
        result.ContractingParty.Party.PartyIdentification.ID.text() == "Hodor national reg number"

        then: "check address information"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.text() == "RO"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listAgencyID.text() == "EU-COM-GROW"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listName.text() == "CountryCodeIdentifier"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listVersionID.text() == "1.0.2"
        result.ContractingParty.Party.PostalAddress.CityName.text() == "Hodor city"
        result.ContractingParty.Party.PostalAddress.StreetName.text() == "Hodor street"
        result.ContractingParty.Party.PostalAddress.Postbox.text() == "Hodor postcode"

        then: "check contact information"
        result.ContractingParty.Party.Contact.Name.text() == "Hodor contact person"
        result.ContractingParty.Party.Contact.ElectronicMail.text() == "hodor@hodor.com"
        result.ContractingParty.Party.Contact.Telephone.text() == "555-HODOR"
    }

    def "should contain ProcurementProjectLot element information when there are no lots"() {
        when:
        def result = parseRequestXml()

        then: "In a Procurement Project with no Lots one ProcurementProjectLot element, and only one, MUST be included in the XML instance"
        result.ProcurementProjectLot.size() == 1

        then: "The identifier for this single ProcurementProjectLot MUST be the number 0"
        result.ProcurementProjectLot.ID.text() == "0"
    }

    def "should contain ProcurementProjectLot element information with lots"() {
        given:
        def espd = new EspdDocument(lotConcerned: "hodor lot")

        when:
        def result = parseRequestXml(espd)

        then: "In a Procurement Project with no Lots one ProcurementProjectLot element, and only one, MUST be included in the XML instance"
        result.ProcurementProjectLot.size() == 1

        then: "The identifier for this single ProcurementProjectLot MUST be the number 0"
        result.ProcurementProjectLot.ID.text() == "hodor lot"
    }

    def "should contain mandatory Criterion elements information when none of them is selected from ESPD"() {
        when:
        def result = parseRequestXml()

        then: "mandatory exclusion plus all selection plus the award criteria (eo criteria)"
        result.Criterion.size() == getMandatoryExclusionCriteriaSize() + SelectionCriterion.values().length + OtherCriterion.values().length
    }

}
