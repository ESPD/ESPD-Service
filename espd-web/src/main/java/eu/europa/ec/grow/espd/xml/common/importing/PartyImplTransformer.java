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

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import eu.europa.ec.grow.espd.domain.enums.other.Country;
import eu.europa.ec.grow.espd.domain.enums.other.CountryType;
import eu.europa.ec.grow.espd.xml.common.MarshallingConstants;
import lombok.extern.slf4j.Slf4j;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.AddressType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContactType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyIdentificationType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * Transforms a UBL {@link PartyType} into an internal ESPD Party implementation as {@link PartyImpl}.
 * <p/>
 * Created by ratoico on 11/25/15.
 */
@Component
@Slf4j
public class PartyImplTransformer {

	private final PartyIdentificationTypePredicate vatNumberPredicate = new PartyIdentificationTypePredicate(
			MarshallingConstants.VAT_NUMBER_SCHEME_ID);
	private final PartyIdentificationTypePredicate nationalNumberPredicate = new PartyIdentificationTypePredicate(
			MarshallingConstants.NATIONAL_NUMBER_SCHEME_ID);

	public PartyImpl apply(PartyType input) {
		PartyImpl authority = new PartyImpl();

		addName(input, authority);
		addWebsite(input, authority);
		addVatNumber(input, authority);
		addAddressInformation(input, authority);
		addContactInformation(input, authority);

		return authority;
	}

	private void addName(PartyType input, PartyImpl party) {
		if (isEmpty(input.getPartyName())) {
			return;
		}
		if (input.getPartyName().get(0).getName() == null) {
			return;
		}

		party.setName(trimToNull(input.getPartyName().get(0).getName().getValue()));
	}

	private void addWebsite(PartyType input, PartyImpl party) {
		if (input.getWebsiteURI() == null) {
			return;
		}

		party.setWebsite(trimToNull(input.getWebsiteURI().getValue()));
	}

	private void addVatNumber(PartyType input, PartyImpl party) {
		if (isEmpty(input.getPartyIdentification())) {
			return;
		}

		// this code is left here for compatibility with versions prior to 2016.08
		PartyIdentificationType vat1Type = input.getPartyIdentification().get(0);
		if (vat1Type.getID() != null) {
			party.setVatNumber(trimToNull(vat1Type.getID().getValue()));
		}
		if (input.getPartyIdentification().size() > 1) {
			PartyIdentificationType vat2Type = input.getPartyIdentification().get(1);
			if (vat2Type.getID() != null) {
				party.setAnotherNationalId(trimToNull(vat2Type.getID().getValue()));
			}
		}

		// this code uses 'schemeID' to distinguish between VAT number and national number
		String vatNumber = getIdentificationNumber(input, vatNumberPredicate);
		String nationalNumber = getIdentificationNumber(input, nationalNumberPredicate);

		// if either of these is not blank it means we are using the model according to version 2016.08
		if (isNotBlank(vatNumber) || isNotBlank(nationalNumber)) {
			party.setVatNumber(trimToNull(vatNumber));
			party.setAnotherNationalId(trimToNull(nationalNumber));
		}
	}

	private String getIdentificationNumber(PartyType input, PartyIdentificationTypePredicate typePredicate) {
		Collection<PartyIdentificationType> identificationTypes = Collections2
				.filter(input.getPartyIdentification(), typePredicate);
		if (isNotEmpty(identificationTypes)) {
			PartyIdentificationType identificationType = identificationTypes.iterator().next();
			if (identificationType.getID() != null) {
				return trimToNull(identificationType.getID().getValue());
			}
		}
		return null;
	}

	private void addAddressInformation(PartyType input, PartyImpl party) {
		if (input.getPostalAddress() == null) {
			return;
		}

		addStreetName(input.getPostalAddress(), party);
		addPostbox(input.getPostalAddress(), party);
		addCity(input.getPostalAddress(), party);
		addCountry(input.getPostalAddress(), party);
	}

	private void addStreetName(AddressType addressType, PartyImpl party) {
		if (addressType.getStreetName() == null) {
			return;
		}

		party.setStreet(trimToNull(addressType.getStreetName().getValue()));
	}

	private void addPostbox(AddressType addressType, PartyImpl party) {
		if (addressType.getPostbox() == null) {
			return;
		}

		party.setPostalCode(trimToNull(addressType.getPostbox().getValue()));
	}

	private void addCity(AddressType addressType, PartyImpl party) {
		if (addressType.getCityName() == null) {
			return;
		}

		party.setCity(trimToNull(addressType.getCityName().getValue()));
	}

	private void addCountry(AddressType addressType, PartyImpl party) {
		if (addressType.getCountry() == null || addressType.getCountry().getIdentificationCode() == null) {
			return;
		}

		party.setCountry(readCountry(addressType.getCountry()));
	}

	private void addContactInformation(PartyType input, PartyImpl party) {
		if (input.getContact() == null) {
			return;
		}

		addContactName(input.getContact(), party);
		addContactPhone(input.getContact(), party);
		addContactEmail(input.getContact(), party);
	}

	private void addContactName(ContactType contactType, PartyImpl party) {
		if (contactType.getName() == null) {
			return;
		}

		party.setContactName(trimToNull(contactType.getName().getValue()));
	}

	private void addContactPhone(ContactType contactType, PartyImpl party) {
		if (contactType.getTelephone() == null) {
			return;
		}

		party.setContactPhone(trimToNull(contactType.getTelephone().getValue()));
	}

	private void addContactEmail(ContactType contactType, PartyImpl party) {
		if (contactType.getElectronicMail() == null) {
			return;
		}

		party.setContactEmail(trimToNull(contactType.getElectronicMail().getValue()));
	}

	Country readCountry(oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.CountryType countryType) {
		if (countryType == null || countryType.getIdentificationCode() == null) {
			return null;
		}

		String countryCode = trimToNull(countryType.getIdentificationCode().getValue());
		if (usesIso3Code(countryType) && countryCode.length() >= 2) {
			return Country.findByIso2Code(countryCode.substring(0, 2));
		}
		return Country.findByIso2Code(countryCode);
	}

	private boolean usesIso3Code(
			oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.CountryType countryType) {
		return CountryType.ISO_3166_2.getIsoType().equalsIgnoreCase(countryType.getIdentificationCode().getListName());
	}

	/**
	 * Identify a {@link PartyIdentificationType} based on the 'schemeID' attribute of its 'ID' element.
	 */
	private static class PartyIdentificationTypePredicate implements Predicate<PartyIdentificationType> {

		private final String partyType;

		private PartyIdentificationTypePredicate(String partyType) {
			this.partyType = partyType;
		}

		@Override
		public boolean apply(PartyIdentificationType input) {
			if (input == null || input.getID() == null) {
				return false;
			}
			return partyType.equals(input.getID().getSchemeID());
		}
	}
}
