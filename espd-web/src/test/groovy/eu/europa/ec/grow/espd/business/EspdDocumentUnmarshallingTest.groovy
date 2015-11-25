package eu.europa.ec.grow.espd.business

import eu.europa.ec.grow.espd.constants.enums.Country
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 11/25/15.
 */
class EspdDocumentUnmarshallingTest extends AbstractEspdXmlMarshalling {

    def "should parse authority information"() {
        given:
        def espdRequestXml = """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<espd-req:ESPDRequest xmlns:espd-resp="urn:grow:names:specification:ubl:schema:xsd:ESPDResponse-1" xmlns:ccv-cbc="urn:isa:names:specification:ubl:schema:xsd:CCV-CommonBasicComponents-1" xmlns:espd-req="urn:grow:names:specification:ubl:schema:xsd:ESPDRequest-1" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ccv="urn:isa:names:specification:ubl:schema:xsd:CCV-CommonAggregateComponents-1" xmlns:cev="urn:isa:names:specification:ubl:schema:xsd:CEV-CommonAggregateComponents-1" xmlns:cev-cbc="urn:isa:names:specification:ubl:schema:xsd:CEV-CommonBasicComponents-1" xmlns:espd-cbc="urn:grow:names:specification:ubl:schema:xsd:ESPD-CommonBasicComponents-1" xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2" xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2" xmlns:espd="urn:grow:names:specification:ubl:schema:xsd:ESPD-CommonAggregateComponents-1">
    <cac:ContractingParty>
        <cac:Party>
            <cbc:WebsiteURI>www.hodor.com</cbc:WebsiteURI>
            <cac:PartyIdentification>
                <cbc:ID>43354d43323</cbc:ID>
            </cac:PartyIdentification>
            <cac:PartyName>
                <cbc:Name>hodor</cbc:Name>
            </cac:PartyName>
            <cac:PostalAddress>
                <cbc:StreetName>elm street</cbc:StreetName>
                <cbc:CityName>drubetis</cbc:CityName>
                <cbc:PostalZone>1500</cbc:PostalZone>
                <cac:Country>
                    <cbc:IdentificationCode listAgencyID="ISO" listName="ISO 3166-1" listVersionID="1.0">RO</cbc:IdentificationCode>
                </cac:Country>
            </cac:PostalAddress>
            <cac:Contact>
                <cbc:Name>gogu</cbc:Name>
                <cbc:Telephone>+43435543</cbc:Telephone>
                <cbc:ElectronicMail>gogu@gogu.com</cbc:ElectronicMail>
            </cac:Contact>
        </cac:Party>
    </cac:ContractingParty>
</espd-req:ESPDRequest>"""
        when:
        def espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml))

        then:
        espd.authority.name == "hodor"
        espd.authority.website == "www.hodor.com"
        espd.authority.nationalRegistrationNumber == "43354d43323"
        espd.authority.street == "elm street"
        espd.authority.postalCode == "1500"
        espd.authority.city == "drubetis"
        espd.authority.country == Country.ROMANIA
        espd.authority.contactName == "gogu"
        espd.authority.contactPhone == "+43435543"
        espd.authority.contactEmail == "gogu@gogu.com"
    }

}