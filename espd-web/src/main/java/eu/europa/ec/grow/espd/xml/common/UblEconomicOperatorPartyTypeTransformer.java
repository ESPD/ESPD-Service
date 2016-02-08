package eu.europa.ec.grow.espd.xml.common;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EconomicOperatorImpl;
import eu.europa.ec.grow.espd.domain.EconomicOperatorRepresentative;
import grow.names.specification.ubl.schema.xsd.espd_commonaggregatecomponents_1.EconomicOperatorPartyType;
import grow.names.specification.ubl.schema.xsd.espd_commonaggregatecomponents_1.NaturalPersonType;
import grow.names.specification.ubl.schema.xsd.espd_commonbasiccomponents_1.IndicatorType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Builds an UBL {@link EconomicOperatorPartyType} that contains the information regarding the economic operator,
 * its representative and additional information.
 * <p/>
 * <p/>
 * Created by ratoico on 1/18/16 at 5:34 PM.
 */
@Component
public class UblEconomicOperatorPartyTypeTransformer
        implements Function<EconomicOperatorImpl, EconomicOperatorPartyType> {

    private final UblPartyTypeTransformer partyTypeTransformer = new UblPartyTypeTransformer();

    @Override
    public EconomicOperatorPartyType apply(EconomicOperatorImpl input) {
        if (input == null) {
            return null;
        }

        EconomicOperatorPartyType eoPartyType = new EconomicOperatorPartyType();

        eoPartyType.setParty(partyTypeTransformer.apply(input));
        eoPartyType.getRepresentativeNaturalPerson().add(buildRepresentative(input));
        eoPartyType.setSMEIndicator(buildSmeIndicator(input.getIsSmallSizedEnterprise()));

        return eoPartyType;
    }

    private NaturalPersonType buildRepresentative(EconomicOperatorImpl input) {
        EconomicOperatorRepresentative representative = input.getRepresentative();
        if (representative == null) {
            return null;
        }

        NaturalPersonType naturalPersonType = new NaturalPersonType();

        naturalPersonType.setNaturalPersonRoleDescription(buildRepresentativeRole(representative));
        naturalPersonType.setPowerOfAttorney(buildPowerOfAttorney(representative));

        return naturalPersonType;
    }

    private DescriptionType buildRepresentativeRole(EconomicOperatorRepresentative representative) {
        if (StringUtils.isBlank(representative.getPosition())) {
            return null;
        }

        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(representative.getPosition());
        return descriptionType;
    }

    private PowerOfAttorneyType buildPowerOfAttorney(EconomicOperatorRepresentative representative) {
        PowerOfAttorneyType attorneyType = new PowerOfAttorneyType();

        if (isNotBlank(representative.getAdditionalInfo())) {
            DescriptionType descriptionType = new DescriptionType();
            descriptionType.setValue(representative.getAdditionalInfo());
            attorneyType.getDescription().add(descriptionType);
        }

        attorneyType.setAgentParty(buildAgentPartyType(representative));

        return attorneyType;
    }

    private PartyType buildAgentPartyType(EconomicOperatorRepresentative representative) {
        PartyType agentParty = new PartyType();

        agentParty.getPerson().add(buildPersonType(representative));

        return agentParty;
    }

    private PersonType buildPersonType(EconomicOperatorRepresentative representative) {
        PersonType personType = new PersonType();

        if (isNotBlank(representative.getFirstName())) {
            FirstNameType firstName = new FirstNameType();
            firstName.setValue(trimToEmpty(representative.getFirstName()));
            personType.setFirstName(firstName);
        }
        if (isNotBlank(representative.getLastName())) {
            FamilyNameType familyName = new FamilyNameType();
            familyName.setValue(trimToEmpty(representative.getLastName()));
            personType.setFamilyName(familyName);
        }
        if (representative.getDateOfBirth() != null) {
            BirthDateType birthDate = new BirthDateType();
            birthDate.setValue(new LocalDate(representative.getDateOfBirth()));
            personType.setBirthDate(birthDate);
        }
        if (isNotBlank(representative.getPlaceOfBirth())) {
            BirthplaceNameType birthplaceName = new BirthplaceNameType();
            birthplaceName.setValue(trimToEmpty(representative.getPlaceOfBirth()));
            personType.setBirthplaceName(birthplaceName);
        }

        personType.setResidenceAddress(buildPersonAddress(representative));
        personType.setContact(buildContact(representative));

        return personType;
    }

    private AddressType buildPersonAddress(EconomicOperatorRepresentative representative) {
        // TODO this code is the same as UblPartyTypeTransformer (we need an abstraction)
        AddressType addressType = new AddressType();
        addCountryInformation(representative, addressType);
        addCityInformation(representative, addressType);
        addStreetInformation(representative, addressType);
        addPostboxInformation(representative, addressType);
        return addressType;
    }

    private void addCountryInformation(EconomicOperatorRepresentative representative, AddressType addressType) {
        if (representative.getCountry() == null) {
            return;
        }

        CountryType countryType = new CountryType();
        IdentificationCodeType identificationCodeType = new IdentificationCodeType();
        identificationCodeType.setValue(representative.getCountry().getIso2Code());
        identificationCodeType.setListAgencyID("ISO");
        identificationCodeType.setListName("ISO 3166-1");
        identificationCodeType.setListVersionID("1.0");
        countryType.setIdentificationCode(identificationCodeType);
        addressType.setCountry(countryType);
    }

    private void addCityInformation(EconomicOperatorRepresentative representative, AddressType addressType) {
        if (isBlank(representative.getCity())) {
            return;
        }

        CityNameType cityName = new CityNameType();
        cityName.setValue(trimToEmpty(representative.getCity()));
        addressType.setCityName(cityName);
    }

    private void addStreetInformation(EconomicOperatorRepresentative representative, AddressType addressType) {
        if (isBlank(representative.getStreet())) {
            return;
        }

        StreetNameType streetName = new StreetNameType();
        streetName.setValue(trimToEmpty(representative.getStreet()));
        addressType.setStreetName(streetName);
    }

    private void addPostboxInformation(EconomicOperatorRepresentative representative, AddressType addressType) {
        if (isBlank(representative.getPostalCode())) {
            return;
        }

        PostboxType postboxType = new PostboxType();
        postboxType.setValue(trimToEmpty(representative.getPostalCode()));
        addressType.setPostbox(postboxType);
    }

    private ContactType buildContact(EconomicOperatorRepresentative representative) {
        // TODO this code is the same as UblPartyTypeTransformer (we need an abstraction)
        ContactType contactType = new ContactType();

        addContactEmailInformation(representative, contactType);
        addContactTelephoneInformation(representative, contactType);

        return contactType;
    }

    private void addContactEmailInformation(EconomicOperatorRepresentative representative, ContactType contactType) {
        if (isBlank(representative.getEmail())) {
            return;
        }

        ElectronicMailType electronicMail = new ElectronicMailType();
        electronicMail.setValue(trimToEmpty(representative.getEmail()));
        contactType.setElectronicMail(electronicMail);
    }

    private void addContactTelephoneInformation(EconomicOperatorRepresentative representative, ContactType contactType) {
        if (isBlank(representative.getPhone())) {
            return;
        }

        TelephoneType telephone = new TelephoneType();
        telephone.setValue(trimToEmpty(representative.getPhone()));
        contactType.setTelephone(telephone);
    }

    private IndicatorType buildSmeIndicator(Boolean isSme) {
        IndicatorType smeIndicator = new IndicatorType();
        smeIndicator.setValue(Boolean.TRUE.equals(isSme));
        return smeIndicator;
    }
}
