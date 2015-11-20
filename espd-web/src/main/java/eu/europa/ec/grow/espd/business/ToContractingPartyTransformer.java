package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.entities.CacParty;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Transforms the information coming from a {@link CacParty} into a {@link ContractingPartyType} object.
 * <p/>
 * Created by vigi on 11/16/15:3:29 PM.
 */
@Component
class ToContractingPartyTransformer implements Function<CacParty, ContractingPartyType> {

    @Override
    public ContractingPartyType apply(CacParty party) {
        // TODO create party interface

        ContractingPartyType contractingPartyType = new ContractingPartyType();
        if (party == null) {
            return contractingPartyType;
        }

        // TODO what is buyer profile uri
        PartyType partyType = new PartyType();

        addPartyNameInformation(party, partyType);
        addPartyIdInformation(party, partyType);
        addPartyWebsiteInformation(party, partyType);
        addAddressInformation(party, partyType);
        addContactInformation(party, partyType);

        contractingPartyType.setParty(partyType);
        return contractingPartyType;
    }

    private void addPartyNameInformation(CacParty party, PartyType partyType) {
        PartyNameType partyNameType = new PartyNameType();
        NameType nameType = new NameType();
        nameType.setValue(trimToEmpty(party.getName()));
        partyNameType.setName(nameType);
        partyType.getPartyName().add(partyNameType);
    }

    private void addPartyIdInformation(CacParty party, PartyType partyType) {
        if (isBlank(party.getNationalRegistrationNumber())) {
            return;
        }
        // TODO where do we get the schemeAgencyId and schemeName

        PartyIdentificationType partyIdentificationType = new PartyIdentificationType();
        IDType id = new IDType();
        id.setValue(trimToEmpty(party.getNationalRegistrationNumber()));
        partyIdentificationType.setID(id);
        partyType.getPartyIdentification().add(partyIdentificationType);
    }

    private void addPartyWebsiteInformation(CacParty party, PartyType partyType) {
        if (isBlank(party.getWebsite())) {
            return;
        }

        WebsiteURIType websiteURI = new WebsiteURIType();
        websiteURI.setValue(trimToEmpty(party.getWebsite()));
        partyType.setWebsiteURI(websiteURI);
    }

    private void addAddressInformation(CacParty party, PartyType partyType) {
        AddressType addressType = new AddressType();
        addCountryInformation(party, addressType);
        addCityInformation(party, addressType);
        addStreetInformation(party, addressType);
        addPostalZoneInformation(party, addressType);

        partyType.setPostalAddress(addressType);
    }

    private void addCountryInformation(CacParty party, AddressType addressType) {
        if (party.getCountry() == null) {
            return;
        }

        CountryType countryType = new CountryType();
        IdentificationCodeType identificationCodeType = new IdentificationCodeType();
        identificationCodeType.setValue(party.getCountry().getIso2Code());
        identificationCodeType.setListAgencyID("ISO");
        identificationCodeType.setListName("ISO 3166-1");
        identificationCodeType.setListVersionID("1.0");
        countryType.setIdentificationCode(identificationCodeType);
        addressType.setCountry(countryType);
    }

    private void addCityInformation(CacParty party, AddressType addressType) {
        if (isBlank(party.getCity())) {
            return;
        }

        CityNameType cityName = new CityNameType();
        cityName.setValue(trimToEmpty(party.getCity()));
        addressType.setCityName(cityName);
    }

    private void addStreetInformation(CacParty party, AddressType addressType) {
        if (isBlank(party.getStreet())) {
            return;
        }

        StreetNameType streetName = new StreetNameType();
        streetName.setValue(trimToEmpty(party.getStreet()));
        addressType.setStreetName(streetName);
    }

    private void addPostalZoneInformation(CacParty party, AddressType addressType) {
        if (isBlank(party.getPostalCode())) {
            return;
        }

        PostalZoneType postalZone = new PostalZoneType();
        postalZone.setValue(trimToEmpty(party.getPostalCode()));
        addressType.setPostalZone(postalZone);
    }

    private void addContactInformation(CacParty party, PartyType partyType) {
        ContactType contactType = new ContactType();

        addContactNameInformation(party, contactType);
        addContactEmailInformation(party, contactType);
        addContactTelephoneInformation(party, contactType);

        partyType.setContact(contactType);
    }

    private void addContactNameInformation(CacParty party, ContactType contactType) {
        if (isBlank(party.getContactName())) {
            return;
        }

        NameType name = new NameType();
        name.setValue(trimToEmpty(party.getContactName()));
        contactType.setName(name);
    }

    private void addContactEmailInformation(CacParty party, ContactType contactType) {
        if (isBlank(party.getContactEmail())) {
            return;
        }

        ElectronicMailType electronicMail = new ElectronicMailType();
        electronicMail.setValue(trimToEmpty(party.getContactEmail()));
        contactType.setElectronicMail(electronicMail);
    }

    private void addContactTelephoneInformation(CacParty party, ContactType contactType) {
        if (isBlank(party.getContactPhone())) {
            return;
        }

        TelephoneType telephone = new TelephoneType();
        telephone.setValue(trimToEmpty(party.getContactPhone()));
        contactType.setTelephone(telephone);
    }
}
