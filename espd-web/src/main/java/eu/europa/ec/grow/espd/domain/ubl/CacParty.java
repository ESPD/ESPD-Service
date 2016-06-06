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

package eu.europa.ec.grow.espd.domain.ubl;

import java.io.Serializable;

/**
 * The contracting authority or contracting entity who is buying supplies,
 * services or public works using a tendering procedure as described in the
 * applicable directive (Directives 2014/24/EU, 2014/25/EU).
 * <p/>
 * Created by vigi on 11/19/15:2:59 PM.
 */
public interface CacParty extends Serializable {

    String getName();
    
    String getVatNumber();

    String getAnotherNationalId();

    String getWebsite();

    String getStreet();

    String getPostalCode();

    String getCity();

    CacCountry getCountry();

    String getContactName();

    String getContactPhone();

    String getContactEmail();
}
