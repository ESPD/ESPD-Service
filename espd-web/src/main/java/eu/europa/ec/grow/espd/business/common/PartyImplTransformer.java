package eu.europa.ec.grow.espd.business.common;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.domain.PartyImpl;
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
public class PartyImplTransformer implements Function<PartyType, PartyImpl> {

    @Override
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

        PartyIdentificationType partyIdentificationType = input.getPartyIdentification().get(0);
        if (partyIdentificationType.getID() == null) {
            return;
        }
        authority.setVatNumber(trimToEmpty(partyIdentificationType.getID().getValue()));
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

        Country country = null;
        for (Country c : Country.values()) {
            if (c.getIso2Code().equalsIgnoreCase(addressType.getCountry().getIdentificationCode().getValue())) {
                country = c;
            }
        }
        authority.setCountry(country);
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
}
