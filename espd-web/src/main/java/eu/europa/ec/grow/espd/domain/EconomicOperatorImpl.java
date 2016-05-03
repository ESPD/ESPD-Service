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

package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.domain.enums.other.Country;
import eu.europa.ec.grow.espd.domain.ubl.CacParty;
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
