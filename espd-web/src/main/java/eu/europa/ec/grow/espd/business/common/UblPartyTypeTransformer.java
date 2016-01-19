package eu.europa.ec.grow.espd.business.common;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.entities.CacParty;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by ratoico on 1/18/16 at 5:29 PM.
 */
final class UblPartyTypeTransformer implements Function<CacParty, PartyType> {

    @Override
    public PartyType apply(CacParty input) {
        PartyType partyType = new PartyType();

        addPartyNameInformation(input, partyType);
        addPartyIdInformation(input, partyType);
        addPartyWebsiteInformation(input, partyType);
        addAddressInformation(input, partyType);
        addContactInformation(input, partyType);

        return partyType;
    }

    private void addPartyNameInformation(CacParty party, PartyType partyType) {
        PartyNameType partyNameType = new PartyNameType();
        NameType nameType = new NameType();
        nameType.setValue(trimToEmpty(party.getName()));
        partyNameType.setName(nameType);
        partyType.getPartyName().add(partyNameType);
    }

    private void addPartyIdInformation(CacParty party, PartyType partyType) {
        if (isNotBlank(party.getVatNumber())) {
            partyType.getPartyIdentification().add(buildPartyIdentificationType(party.getVatNumber()));
        }

        if (isNotBlank(party.getAnotherNationalId())) {
            partyType.getPartyIdentification().add(buildPartyIdentificationType(party.getAnotherNationalId()));
        }

    }

    private PartyIdentificationType buildPartyIdentificationType(String regNumber) {
        PartyIdentificationType partyIdentificationType = new PartyIdentificationType();
        IDType id = new IDType();
        id.setValue(trimToEmpty(regNumber));
        partyIdentificationType.setID(id);
        return partyIdentificationType;
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
        addPostboxInformation(party, addressType);

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

    private void addPostboxInformation(CacParty party, AddressType addressType) {
        if (isBlank(party.getPostalCode())) {
            return;
        }

        PostboxType postboxType = new PostboxType();
        postboxType.setValue(trimToEmpty(party.getPostalCode()));
        addressType.setPostbox(postboxType);
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
