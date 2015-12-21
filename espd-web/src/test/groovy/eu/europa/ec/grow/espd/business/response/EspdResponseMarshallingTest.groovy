package eu.europa.ec.grow.espd.business.response

import eu.europa.ec.grow.espd.business.base.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.PartyImpl

/**
 * Created by ratoico on 11/26/15.
 */
class EspdResponseMarshallingTest extends AbstractEspdXmlMarshalling {

    def "should make sure that we use the correct XML namespaces"() {
        when:
        def result = parseResponseXml()

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
//        result.lookupNamespace('cev') == 'urn:isa:names:specification:ubl:schema:xsd:CEV-CommonAggregateComponents-1'
//        result.lookupNamespace('ext') == 'urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2'
    }

    def "should contain UBLVersionID element information"() {
        when:
        def result = parseResponseXml()

        then:
        result.UBLVersionID.text() == "2.1"
        result.UBLVersionID.@schemeAgencyID.text() == "OASIS-UBL-TC"
    }

    def "should contain CustomizationID element information"() {
        when:
        def result = parseResponseXml()

        then:
        result.CustomizationID.text() == "urn:www.cenbii.eu:transaction:biitrns092:ver3.0"
        result.CustomizationID.@schemeAgencyID.text() == "BII"
        result.CustomizationID.@schemeVersionID.text() == "3.0"
        result.CustomizationID.@schemeName.text() == "CustomizationID"
    }

    def "should contain ID element information"() {
        when:
        def result = parseResponseXml()

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
        def result = parseResponseXml()

        then:
        result.CopyIndicator.size() == 1
        result.CopyIndicator.toBoolean() == false
    }

    def "should contain VersionID element information"() {
        when:
        def result = parseResponseXml()

        then:
        result.VersionID.text() == "1"
        result.VersionID.@schemeAgencyID.text() == "EU-COM-GROW"
    }

    def "should contain IssueDate element information"() {
        when:
        def result = parseResponseXml()

        then: "issue date must match the date format YYYY-MM-dd"
        result.IssueDate.text() ==~ "\\d{4}-\\d{2}-\\d{2}"
    }

    def "should contain IssueTime element information"() {
        when:
        def result = parseResponseXml()

        then: "issue time must match the time format HH:mm:ss"
        result.IssueTime.text() ==~ "\\d{2}:\\d{2}:\\d{2}"
    }

    def "should transform ContractingParty element information"() {
        given:
        def authority = new PartyImpl(name: "  Hodor authority  ", nationalRegistrationNumber: "  Hodor national reg number  ",
                street: "  Hodor street  ", postalCode: "  Hodor postcode  ", city: "  Hodor city  ", country: Country.ROMANIA,
                contactName: "  Hodor contact person  ", contactEmail: "  hodor@hodor.com  ", contactPhone: "  555-HODOR  ",
                website: "  www.hodor.com  ")
        def espd = new EspdDocument(authority: authority)

        when:
        def result = parseResponseXml(espd)

        then: "all values should be trimmed"
        result.ContractingParty.Party.PartyName.Name.text() == "Hodor authority"
        result.ContractingParty.Party.WebsiteURI.text() == "www.hodor.com"

        then: "party identification"
        result.ContractingParty.Party.PartyIdentification.ID.text() == "Hodor national reg number"

        then: "check address information"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.text() == "RO"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listAgencyID.text() == "ISO"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listName.text() == "ISO 3166-1"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listVersionID.text() == "1.0"
        result.ContractingParty.Party.PostalAddress.CityName.text() == "Hodor city"
        result.ContractingParty.Party.PostalAddress.StreetName.text() == "Hodor street"
        result.ContractingParty.Party.PostalAddress.PostalZone.text() == "Hodor postcode"

        then: "check contact information"
        result.ContractingParty.Party.Contact.Name.text() == "Hodor contact person"
        result.ContractingParty.Party.Contact.ElectronicMail.text() == "hodor@hodor.com"
        result.ContractingParty.Party.Contact.Telephone.text() == "555-HODOR"
    }

    def "should contain ProcurementProjectLot element information when there are no lots"() {
        when:
        def result = parseResponseXml()

        then: "In a Procurement Project with no Lots one ProcurementProjectLot element, and only one, MUST be included in the XML instance"
        result.ProcurementProjectLot.size() == 1

        then: "The identifier for this single ProcurementProjectLot MUST be the number 0"
        result.ProcurementProjectLot.ID.text() == "0"
    }

}