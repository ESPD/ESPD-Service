package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IdentificationCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.NameType;
import org.springframework.stereotype.Component;

/**
 * Transforms the contracting party (authority) information coming from ESPD into a {@link ContractingPartyType} object.
 * <p/>
 * Created by vigi on 11/16/15:3:29 PM.
 */
@Component
class ToContractingPartyTransformer implements Function<EspdDocument, ContractingPartyType> {

    @Override
    public ContractingPartyType apply(EspdDocument espdDocument) {
        ContractingPartyType contractingPartyType = new ContractingPartyType();
        // TODO what is buyer profile uri
        PartyType partyType = new PartyType();

        addPartyNameInformation(espdDocument, partyType);
        addAddressInformation(espdDocument, partyType);

        contractingPartyType.setParty(partyType);
        return contractingPartyType;
    }

    private void addPartyNameInformation(final EspdDocument espdDocument, final PartyType partyType) {
        PartyNameType partyNameType = new PartyNameType();
        NameType nameType = new NameType();
        nameType.setValue(espdDocument.getAuthorityName());
        partyNameType.setName(nameType);
        partyType.getPartyName().add(partyNameType);
    }

    private void addAddressInformation(final EspdDocument espdDocument, final PartyType partyType) {
        AddressType addressType = new AddressType();
        addCountryInformation(espdDocument, addressType);

        partyType.setPostalAddress(addressType);
    }

    private void addCountryInformation(final EspdDocument espdDocument, final AddressType addressType) {
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
}
