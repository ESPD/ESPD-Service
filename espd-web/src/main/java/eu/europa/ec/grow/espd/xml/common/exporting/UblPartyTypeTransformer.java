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

package eu.europa.ec.grow.espd.xml.common.exporting;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.ubl.CacParty;
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
        IDType id = CommonUblFactory.buildIdType();
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

        addressType.setCountry(CommonUblFactory.buildCountryType(party.getCountry()));
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

        PostalZoneType postalZoneType = new PostalZoneType();
        postalZoneType.setValue(trimToEmpty(party.getPostalCode()));
        addressType.setPostalZone(postalZoneType);


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
