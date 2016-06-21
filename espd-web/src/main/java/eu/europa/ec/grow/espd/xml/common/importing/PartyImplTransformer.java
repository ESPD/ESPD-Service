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

package eu.europa.ec.grow.espd.xml.common.importing;

import eu.europa.ec.grow.espd.domain.PartyImpl;
import eu.europa.ec.grow.espd.domain.enums.other.Country;
import eu.europa.ec.grow.espd.domain.enums.other.CountryType;
import lombok.extern.slf4j.Slf4j;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.AddressType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContactType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyIdentificationType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import org.springframework.stereotype.Component;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Transforms a UBL {@link PartyType} into an internal ESPD Party implementation as {@link PartyImpl}.
 * <p/>
 * Created by ratoico on 11/25/15.
 */
@Component
@Slf4j
public class PartyImplTransformer {

	public PartyImpl apply(PartyType input) {
		PartyImpl authority = new PartyImpl();

		addName(input, authority);
		addWebsite(input, authority);
		addVatNumber(input, authority);
		addAddressInformation(input, authority);
		addContactInformation(input, authority);

		return authority;
	}

	private void addName(PartyType input, PartyImpl authority) {
		if (isEmpty(input.getPartyName())) {
			return;
		}
		if (input.getPartyName().get(0).getName() == null) {
			return;
		}

		authority.setName(trimToEmpty(input.getPartyName().get(0).getName().getValue()));
	}

	private void addWebsite(PartyType input, PartyImpl authority) {
		if (input.getWebsiteURI() == null) {
			return;
		}

		authority.setWebsite(trimToEmpty(input.getWebsiteURI().getValue()));
	}

	private void addVatNumber(PartyType input, PartyImpl authority) {
		if (isEmpty(input.getPartyIdentification())) {
			return;
		}

		PartyIdentificationType vat1Type = input.getPartyIdentification().get(0);
		if (vat1Type.getID() != null) {
			authority.setVatNumber(trimToEmpty(vat1Type.getID().getValue()));
		}
		if (input.getPartyIdentification().size() > 1) {
			PartyIdentificationType vat2Type = input.getPartyIdentification().get(1);
			if (vat2Type.getID() != null) {
				authority.setAnotherNationalId(trimToEmpty(vat2Type.getID().getValue()));
			}
		}
	}

	private void addAddressInformation(PartyType input, PartyImpl authority) {
		if (input.getPostalAddress() == null) {
			return;
		}

		addStreetName(input.getPostalAddress(), authority);
		addPostbox(input.getPostalAddress(), authority);
		addCity(input.getPostalAddress(), authority);
		addCountry(input.getPostalAddress(), authority);
	}

	private void addStreetName(AddressType addressType, PartyImpl authority) {
		if (addressType.getStreetName() == null) {
			return;
		}

		authority.setStreet(trimToEmpty(addressType.getStreetName().getValue()));
	}

	private void addPostbox(AddressType addressType, PartyImpl authority) {
		if (addressType.getPostbox() == null) {
			return;
		}

		authority.setPostalCode(trimToEmpty(addressType.getPostbox().getValue()));
	}

	private void addCity(AddressType addressType, PartyImpl authority) {
		if (addressType.getCityName() == null) {
			return;
		}

		authority.setCity(trimToEmpty(addressType.getCityName().getValue()));
	}

	private void addCountry(AddressType addressType, PartyImpl authority) {
		if (addressType.getCountry() == null || addressType.getCountry().getIdentificationCode() == null) {
			return;
		}

		authority.setCountry(readCountry(addressType.getCountry()));
	}

	private void addContactInformation(PartyType input, PartyImpl authority) {
		if (input.getContact() == null) {
			return;
		}

		addContactName(input.getContact(), authority);
		addContactPhone(input.getContact(), authority);
		addContactEmail(input.getContact(), authority);
	}

	private void addContactName(ContactType contactType, PartyImpl authority) {
		if (contactType.getName() == null) {
			return;
		}

		authority.setContactName(trimToEmpty(contactType.getName().getValue()));
	}

	private void addContactPhone(ContactType contactType, PartyImpl authority) {
		if (contactType.getTelephone() == null) {
			return;
		}

		authority.setContactPhone(trimToEmpty(contactType.getTelephone().getValue()));
	}

	private void addContactEmail(ContactType contactType, PartyImpl authority) {
		if (contactType.getElectronicMail() == null) {
			return;
		}

		authority.setContactEmail(trimToEmpty(contactType.getElectronicMail().getValue()));
	}

	Country readCountry(oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.CountryType countryType) {
		if (countryType == null || countryType.getIdentificationCode() == null) {
			return null;
		}

		String countryCode = trimToEmpty(countryType.getIdentificationCode().getValue());
		if (usesIso3Code(countryType) && countryCode.length() >= 2) {
			return Country.findByIso2Code(countryCode.substring(0, 2));
		}
		return Country.findByIso2Code(countryCode);
	}

	private boolean usesIso3Code(
			oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.CountryType countryType) {
		return CountryType.ISO_3166_2.getIsoType().equalsIgnoreCase(countryType.getIdentificationCode().getListName());
	}
}
