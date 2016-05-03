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

/**
 * Created by vigi on 11/19/15:2:27 PM.
 */
@Data
public class PartyImpl implements CacParty {

    private String name;
    
    private String vatNumber;

    private String anotherNationalId;//If no VAT-number is applicable, please indicate another national identification number, if required and applicable

    private String website;

    private String street;

    private String postalCode;

    private String city;

    private Country country;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

}
