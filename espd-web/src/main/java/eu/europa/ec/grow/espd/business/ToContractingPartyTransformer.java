package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Transforms the contracting party (authority) information coming from ESPD into a {@link ContractingPartyType} object.
 * <p/>
 * Created by vigi on 11/16/15:3:29 PM.
 */
@Component
class ToContractingPartyTransformer implements Function<EspdDocument, ContractingPartyType> {

    @Override
    public ContractingPartyType apply(EspdDocument espdDocument) {
        // TODO create party interface

        ContractingPartyType contractingPartyType = new ContractingPartyType();
        // TODO what is buyer profile uri
        PartyType partyType = new PartyType();

        addPartyNameInformation(espdDocument, partyType);
        addPartyIdInformation(espdDocument, partyType);
        addPartyWebsiteInformation(espdDocument, partyType);
        addAddressInformation(espdDocument, partyType);
        addContactInformation(espdDocument, partyType);

        contractingPartyType.setParty(partyType);
        return contractingPartyType;
    }

    private void addPartyNameInformation(EspdDocument espdDocument, PartyType partyType) {
        PartyNameType partyNameType = new PartyNameType();
        NameType nameType = new NameType();
        nameType.setValue(trimToEmpty(espdDocument.getAuthorityName()));
        partyNameType.setName(nameType);
        partyType.getPartyName().add(partyNameType);
    }

    private void addPartyIdInformation(EspdDocument espdDocument, PartyType partyType) {
        if (isBlank(espdDocument.getNatRegNumber())) {
            return;
        }

        PartyIdentificationType partyIdentificationType = new PartyIdentificationType();
        IDType id = new IDType();
        id.setValue(trimToEmpty(espdDocument.getNatRegNumber()));
        partyIdentificationType.setID(id);
        partyType.getPartyIdentification().add(partyIdentificationType);
    }

    private void addPartyWebsiteInformation(EspdDocument espdDocument, PartyType partyType) {
        if (isBlank(espdDocument.getWebsite())) {
            return;
        }

        WebsiteURIType websiteURI = new WebsiteURIType();
        websiteURI.setValue(trimToEmpty(espdDocument.getWebsite()));
        partyType.setWebsiteURI(websiteURI);
    }

    private void addAddressInformation(EspdDocument espdDocument, PartyType partyType) {
        AddressType addressType = new AddressType();
        addCountryInformation(espdDocument, addressType);
        addCityInformation(espdDocument, addressType);
        addStreetInformation(espdDocument, addressType);
        addPostalZoneInformation(espdDocument, addressType);

        partyType.setPostalAddress(addressType);
    }

    private void addCountryInformation(EspdDocument espdDocument, AddressType addressType) {
        if (espdDocument.getCountry() == null) {
            return;
        }

        CountryType countryType = new CountryType();
        IdentificationCodeType identificationCodeType = new IdentificationCodeType();
        identificationCodeType.setValue(espdDocument.getCountry().getIso2Code());
        identificationCodeType.setListAgencyID("ISO");
        identificationCodeType.setListName("ISO 3166-1");
        identificationCodeType.setListVersionID("1.0");
        countryType.setIdentificationCode(identificationCodeType);
        addressType.setCountry(countryType);
    }

    private void addCityInformation(EspdDocument espdDocument, AddressType addressType) {
        if (isBlank(espdDocument.getCity())) {
            return;
        }

        CityNameType cityName = new CityNameType();
        cityName.setValue(trimToEmpty(espdDocument.getCity()));
        addressType.setCityName(cityName);
    }

    private void addStreetInformation(EspdDocument espdDocument, AddressType addressType) {
        if (isBlank(espdDocument.getStreetAndNumber())) {
            return;
        }

        StreetNameType streetName = new StreetNameType();
        streetName.setValue(trimToEmpty(espdDocument.getStreetAndNumber()));
        addressType.setStreetName(streetName);
    }

    private void addPostalZoneInformation(EspdDocument espdDocument, AddressType addressType) {
        if (isBlank(espdDocument.getPostcode())) {
            return;
        }

        PostalZoneType postalZone = new PostalZoneType();
        postalZone.setValue(trimToEmpty(espdDocument.getPostcode()));
        addressType.setPostalZone(postalZone);
    }

    private void addContactInformation(EspdDocument espdDocument, PartyType partyType) {
        ContactType contactType = new ContactType();

        addContactNameInformation(espdDocument, contactType);
        addContactEmailInformation(espdDocument, contactType);
        addContactTelephoneInformation(espdDocument, contactType);

        partyType.setContact(contactType);
    }

    private void addContactNameInformation(EspdDocument espdDocument, ContactType contactType) {
        if (isBlank(espdDocument.getContactPerson())) {
            return;
        }

        NameType name = new NameType();
        name.setValue(trimToEmpty(espdDocument.getContactPerson()));
        contactType.setName(name);
    }

    private void addContactEmailInformation(EspdDocument espdDocument, ContactType contactType) {
        if (isBlank(espdDocument.getEmail())) {
            return;
        }

        ElectronicMailType electronicMail = new ElectronicMailType();
        electronicMail.setValue(trimToEmpty(espdDocument.getEmail()));
        contactType.setElectronicMail(electronicMail);
    }

    private void addContactTelephoneInformation(EspdDocument espdDocument, ContactType contactType) {
        if (isBlank(espdDocument.getTelephone())) {
            return;
        }

        TelephoneType telephone = new TelephoneType();
        telephone.setValue(trimToEmpty(espdDocument.getTelephone()));
        contactType.setTelephone(telephone);
    }
}
