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

import eu.europa.ec.grow.espd.domain.ubl.CacCountry;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vigi on 11/16/15:11:32 AM.
 */
@Getter
public enum Country implements CacCountry {

    /**
     * Austria
     */
    AUSTRIA("Austria", "country_austria", "AT", CountryType.ISO_3166_1),
    /**
     * Belgium
     */
    BELGIUM("Belgium", "country_belgium", "BE", CountryType.ISO_3166_1),
    /**
     * Bulgaria
     */
    BULGARIA("Bulgaria", "country_bulgaria", "BG", CountryType.ISO_3166_1),
    /**
     * Croatia
     */
    CROATIA("Croatia", "country_croatia", "HR", CountryType.ISO_3166_1),
    /**
     * Cyprus
     */
    CYPRUS("Cyprus", "country_cyprus", "CY", CountryType.ISO_3166_1),
    /**
     * Czech Republic
     */
    CZECH_REPUBLIC("Czech_Republic", "country_czech_republic", "CZ", CountryType.ISO_3166_1),
    /**
     * Denmark
     */
    DENMARK("Denmark", "country_denmark", "DK", CountryType.ISO_3166_1),
    /**
     * Estonia
     */
    ESTONIA("Estonia", "country_estonia", "EE", CountryType.ISO_3166_1),
    /**
     * Finland
     */
    FINLAND("Finland", "country_finland", "FI", CountryType.ISO_3166_1),
    /**
     * France
     */
    FRANCE("France", "country_france", "FR", CountryType.ISO_3166_1),
    /**
     * Germany
     */
    GERMANY("Germany", "country_germany", "DE", CountryType.ISO_3166_1),
    /**
     * Greece
     */
    GREECE("Greece", "country_greece", "GR", CountryType.ISO_3166_1),
    /**
     * Hungary
     */
    HUNGARY("Hungary", "country_hungary", "HU", CountryType.ISO_3166_1),
    /**
     * Ireland
     */
    IRELAND("Ireland", "country_ireland", "IE", CountryType.ISO_3166_1),
    /**
     * Italy
     */
    ITALY("Italy", "country_italy", "IT", CountryType.ISO_3166_1),
    /**
     * Latvia
     */
    LATVIA("Latvia", "country_latvia", "LV", CountryType.ISO_3166_1),
    /**
     * Lithuania
     */
    LITHUANIA("Lithuania", "country_lithuania", "LT", CountryType.ISO_3166_1),
    /**
     * Luxembourg
     */
    LUXEMBOURG("Luxembourg", "country_luxembourg", "LU", CountryType.ISO_3166_1),
    /**
     * Malta
     */
    MALTA("Malta", "country_malta", "MT", CountryType.ISO_3166_1),
    /**
     * Netherlands
     */
    NETHERLANDS("Netherlands", "country_netherlands", "NL", CountryType.ISO_3166_1),
    /**
     * Poland
     */
    POLAND("Poland", "country_poland", "PL", CountryType.ISO_3166_1),
    /**
     * Portugal
     */
    PORTUGAL("Portugal", "country_portugal", "PT", CountryType.ISO_3166_1),
    /**
     * Romania
     */
    ROMANIA("Romania", "country_romania", "RO", CountryType.ISO_3166_1),
    /**
     * Slovakia
     */
    SLOVAKIA("Slovakia", "country_slovakia", "SK", CountryType.ISO_3166_1),
    /**
     * Slovenia
     */
    SLOVENIA("Slovenia", "country_slovenia", "SI", CountryType.ISO_3166_1),
    /**
     * Spain
     */
    SPAIN("Spain", "country_spain", "ES", CountryType.ISO_3166_1),
    /**
     * Sweden
     */
    SWEDEN("Sweden", "country_sweden", "SE", CountryType.ISO_3166_1),
    /**
     * United Kingdom
     */
    UNITED_KINGDOM("United_Kingdom", "country_united_kingdom", "GB", CountryType.ISO_3166_1),

    /**
     * United Kingdom / England
     */
    UNITED_KINGDOM_ENGLAND("United_Kingdom_England", "country_united_kingdom_england", "GB-ENG",
            CountryType.ISO_3166_2),

    /**
     * United Kingdom / Northern Ireland
     */
    UNITED_KINGDOM_NORTHERN_IRELAND("United_Kingdom_Northern_Ireland", "country_united_kingdom_northern_ireland",
            "GB-NIR", CountryType.ISO_3166_2),

    /**
     * United Kingdom / Scotland
     */
    UNITED_KINGDOM_SCOTLAND("United_Kingdom_Scotland", "country_united_kingdom_scotland", "GB-SCT",
            CountryType.ISO_3166_2),

    /**
     * United Kingdom / Wales
     */
    UNITED_KINGDOM_WALES("United_Kingdom_Wales", "country_united_kingdom_wales", "GB-WLS", CountryType.ISO_3166_2),

    /**
     * Turkey
     */
    TURKEY("Turkey", "country_turkey", "TR", CountryType.ISO_3166_1),
    /**
     * Iceland
     */
    ICELAND("Iceland", "country_iceland", "IS", CountryType.ISO_3166_1),
    /**
     * Liechtenstein
     */
    LIECHTENSTEIN("Liechtenstein", "country_liechtenstein", "LI", CountryType.ISO_3166_1),
    /**
     * Switzerland
     */
    SWITZERLAND("Switzerland", "country_switzerland", "CH", CountryType.ISO_3166_1),
    /**
     * Norway
     */
    NORWAY("Norway", "country_norway", "NO", CountryType.ISO_3166_1);

    private final String countryName;

    private final String i18nCode;

    private final String isoCode;

    private final CountryType countryType;

    Country(String countryName, String i18nCode, String isoCode, CountryType countryType) {
        this.countryName = countryName;
        this.i18nCode = i18nCode;
        this.isoCode = isoCode;
        this.countryType = countryType;
    }

    @Override
    public String getName() {
        return countryName;
    }

	@Override
    public String getIsoType() {
        return countryType.getIsoType();
    }

    public static final List<Country> EU_COUNTRIES = Collections.unmodifiableList(
            Arrays.asList(AUSTRIA, BELGIUM, BULGARIA, CROATIA, CYPRUS, CZECH_REPUBLIC, DENMARK, ESTONIA,
                    FINLAND, FRANCE, GERMANY, GREECE, HUNGARY, IRELAND, ITALY, LATVIA, LITHUANIA, LUXEMBOURG, MALTA,
                    NETHERLANDS, POLAND, PORTUGAL, ROMANIA, SLOVAKIA, SLOVENIA, SPAIN, SWEDEN,
                    UNITED_KINGDOM, UNITED_KINGDOM_ENGLAND, UNITED_KINGDOM_NORTHERN_IRELAND, UNITED_KINGDOM_SCOTLAND,
                    UNITED_KINGDOM_WALES));

    public static final List<Country> EFTA_COUNTRIES = Collections.unmodifiableList(Arrays.asList(NORWAY, SWITZERLAND));

    public static Country findByIsoCode(String isoCode) {
        for (Country country : values()) {
            if (country.getIsoCode().equalsIgnoreCase(isoCode)) {
                return country;
            }
        }
        return null;
    }

}
