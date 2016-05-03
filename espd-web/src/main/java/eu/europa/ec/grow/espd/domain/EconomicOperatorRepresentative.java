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
import lombok.Data;

import java.util.Date;

/**
 * Created by ratoico on 1/19/16 at 11:56 AM.
 */
@Data
public class EconomicOperatorRepresentative {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String placeOfBirth;

    private String street;

    private String postalCode;

    private String city;

    private Country country;

    private String email;

    private String phone;

    /**
     * Position/Acting in the capacity of
     */
    private String position;

    /**
     * If needed, please provide detailed information on the representation (its forms, extent, purpose ...)
     */
    private String additionalInfo;
}
