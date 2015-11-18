package eu.europa.ec.grow.espd.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.constants.CountryType.*;

/**
 * Created by vigi on 11/16/15:11:32 AM.
 */
@Getter
public enum Country {

    /**
     * Austria
     */
    AUSTRIA("Austria", "country_austria", "AT", EU),
    /**
     * Belgium
     */
    BELGIUM("Belgium", "country_belgium", "BE", EU),
    /**
     * Bulgaria
     */
    BULGARIA("Bulgaria", "country_bulgaria", "BG", EU),
    /**
     * Croatia
     */
    CROATIA("Croatia", "country_croatia", "HR", EU),
    /**
     * Cyprus
     */
    CYPRUS("Cyprus", "country_cyprus", "CY", EU),
    /**
     * Czech Republic
     */
    CZECH_REPUBLIC("Czech_Republic", "country_czech_republic", "CZ", EU),
    /**
     * Denmark
     */
    DENMARK("Denmark", "country_denmark", "DK", EU),
    /**
     * Estonia
     */
    ESTONIA("Estonia", "country_estonia", "EE", EU),
    /**
     * Finland
     */
    FINLAND("Finland", "country_finland", "FI", EU),
    /**
     * France
     */
    FRANCE("France", "country_france", "FR", EU),
    /**
     * Germany
     */
    GERMANY("Germany", "country_germany", "DE", EU),
    /**
     * Greece
     */
    GREECE("Greece", "country_greece", "GR", EU),
    /**
     * Hungary
     */
    HUNGARY("Hungary", "country_hungary", "HU", EU),
    /**
     * Ireland
     */
    IRELAND("Ireland", "country_ireland", "IE", EU),
    /**
     * Italy
     */
    ITALY("Italy", "country_italy", "IT", EU),
    /**
     * Latvia
     */
    LATVIA("Latvia", "country_latvia", "LV", EU),
    /**
     * Lithuania
     */
    LITHUANIA("Lithuania", "country_lithuania", "LT", EU),
    /**
     * Luxembourg
     */
    LUXEMBOURG("Luxembourg", "country_luxembourg", "LU", EU),
    /**
     * Malta
     */
    MALTA("Malta", "country_malta", "MT", EU),
    /**
     * Netherlands
     */
    NETHERLANDS("Netherlands", "country_netherlands", "NL", EU),
    /**
     * Poland
     */
    POLAND("Poland", "country_poland", "PL", EU),
    /**
     * Portugal
     */
    PORTUGAL("Portugal", "country_portugal", "PT", EU),
    /**
     * Romania
     */
    ROMANIA("Romania", "country_romania", "RO", EU),
    /**
     * Slovakia
     */
    SLOVAKIA("Slovakia", "country_slovakia", "SK", EU),
    /**
     * Slovenia
     */
    SLOVENIA("Slovenia", "country_slovenia", "SI", EU),
    /**
     * Spain
     */
    SPAIN("Spain", "country_spain", "ES", EU),
    /**
     * Sweden
     */
    SWEDEN("Sweden", "country_sweden", "SE", EU),
    /**
     * United Kingdom
     */
    UNITED_KINGDOM("United_Kingdom", "country_united_kingdom", "GB", EU),
    /**
     * Turkey
     */
    TURKEY("Turkey", "country_turkey", "TR", EU_PLUS),
    /**
     * Iceland
     */
    ICELAND("Iceland", "country_iceland", "IS", EFTA),
    /**
     * Liechtenstein
     */
    LIECHTENSTEIN("Liechtenstein", "country_liechtenstein", "LI", EFTA),
    /**
     * Switzerland
     */
    SWITZERLAND("Switzerland", "country_switzerland", "CH", EFTA),
    /**
     * Norway
     */
    NORWAY("Norway", "country_norway", "NO", EFTA);

    private final String countryName;

    private final String i18nCode;

    private final String iso2Code;

    private final CountryType countryType;

    Country(String countryName, String i18nCode, String iso2Code, CountryType countryType) {
        this.countryName = countryName;
        this.i18nCode = i18nCode;
        this.iso2Code = iso2Code;
        this.countryType = countryType;
    }

    public static final List<Country> EU_COUNTRIES = Collections.unmodifiableList(
            Arrays.asList(AUSTRIA, BELGIUM, BULGARIA, CROATIA, CYPRUS, CZECH_REPUBLIC, DENMARK, ESTONIA,
                    FINLAND, FRANCE, GERMANY, GREECE, HUNGARY, IRELAND, ITALY, LATVIA, LITHUANIA, LUXEMBOURG, MALTA,
                    NETHERLANDS, POLAND, PORTUGAL, ROMANIA, SLOVAKIA, SLOVENIA, SPAIN, SWEDEN, UNITED_KINGDOM));

    public static final List<Country> EU_PLUS_COUNTRIES = Collections.singletonList(TURKEY);

    public static final List<Country> EFTA_COUNTRIES = Collections
            .unmodifiableList(Arrays.asList(ICELAND, LIECHTENSTEIN, NORWAY, SWITZERLAND));

}
