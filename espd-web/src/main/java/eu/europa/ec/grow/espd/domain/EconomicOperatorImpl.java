package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.entities.CacParty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class EconomicOperatorImpl extends PartyImpl {
	
	private Boolean isSmallSizedEnterprise; //Is the economic operator a Micro, a Small or a Medium-Sized Enterprise ?

    private EconomicOperatorRepresentative representative;

    public void copyProperties(CacParty fromParty) {
        setName(fromParty.getName());
        setWebsite(fromParty.getWebsite());
        setVatNumber(fromParty.getVatNumber());
        setAnotherNationalId(fromParty.getAnotherNationalId());
        setStreet(fromParty.getStreet());
        setPostalCode(fromParty.getPostalCode());
        setCity(fromParty.getCity());
        setCountry((Country) fromParty.getCountry());
        setContactName(fromParty.getContactName());
        setContactPhone(fromParty.getContactPhone());
        setContactEmail(fromParty.getContactEmail());
    }

}
