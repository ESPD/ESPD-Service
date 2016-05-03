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

package eu.europa.ec.grow.espd.domain.enums.other;

import lombok.Getter;

/**
 * Created by ratoico on 1/14/16 at 4:31 PM.
 */
@Getter
public enum Currency {

    EUR("Euro"),
    ALL("Albanian lek"),
    AMD("Armenian dram"),
    AZN("Azerbaijani manat"),
    BAM("Bosnian convertible mark"),
    BGN("Bulgarian lev"),
    BYR("Belarusian ruble"),
    CHF("Swiss franc"),
    CZK("Czech koruna"),
    DKK("Danish krone"),
    GBP("pound sterling"),
    GEL("Georgian lari"),
    HRK("Croatian kuna"),
    HUF("Hungarian forint"),
    ISK("Icelandic króna"),
    MDL("Moldovan krone"),
    PLN("Polish zloty"),
    RON("New Romanian leu"),
    RSD("Serbian dinar"),
    RUB("Russian ruble"),
    SEK("Swedish krona"),
    TRY("Turkish lira"),
    UAH("Ukrainian hryvnia"),
    USD("US dollar");

    private final String description;

    Currency(String description) {
        this.description = description;
    }
}
