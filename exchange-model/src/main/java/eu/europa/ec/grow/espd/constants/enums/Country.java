package eu.europa.ec.grow.espd.constants.enums;

import eu.europa.ec.grow.espd.entities.CacCountry;
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
    AUSTRIA("Austria", "country_austria", "AT", CountryType.EU),
    /**
     * Belgium
     */
    BELGIUM("Belgium", "country_belgium", "BE", CountryType.EU),
    /**
     * Bulgaria
     */
    BULGARIA("Bulgaria", "country_bulgaria", "BG", CountryType.EU),
    /**
     * Croatia
     */
    CROATIA("Croatia", "country_croatia", "HR", CountryType.EU),
    /**
     * Cyprus
     */
    CYPRUS("Cyprus", "country_cyprus", "CY", CountryType.EU),
    /**
     * Czech Republic
     */
    CZECH_REPUBLIC("Czech_Republic", "country_czech_republic", "CZ", CountryType.EU),
    /**
     * Denmark
     */
    DENMARK("Denmark", "country_denmark", "DK", CountryType.EU),
    /**
     * Estonia
     */
    ESTONIA("Estonia", "country_estonia", "EE", CountryType.EU),
    /**
     * Finland
     */
    FINLAND("Finland", "country_finland", "FI", CountryType.EU),
    /**
     * France
     */
    FRANCE("France", "country_france", "FR", CountryType.EU),
    /**
     * Germany
     */
    GERMANY("Germany", "country_germany", "DE", CountryType.EU),
    /**
     * Greece
     */
    GREECE("Greece", "country_greece", "GR", CountryType.EU),
    /**
     * Hungary
     */
    HUNGARY("Hungary", "country_hungary", "HU", CountryType.EU),
    /**
     * Ireland
     */
    IRELAND("Ireland", "country_ireland", "IE", CountryType.EU),
    /**
     * Italy
     */
    ITALY("Italy", "country_italy", "IT", CountryType.EU),
    /**
     * Latvia
     */
    LATVIA("Latvia", "country_latvia", "LV", CountryType.EU),
    /**
     * Lithuania
     */
    LITHUANIA("Lithuania", "country_lithuania", "LT", CountryType.EU),
    /**
     * Luxembourg
     */
    LUXEMBOURG("Luxembourg", "country_luxembourg", "LU", CountryType.EU),
    /**
     * Malta
     */
    MALTA("Malta", "country_malta", "MT", CountryType.EU),
    /**
     * Netherlands
     */
    NETHERLANDS("Netherlands", "country_netherlands", "NL", CountryType.EU),
    /**
     * Poland
     */
    POLAND("Poland", "country_poland", "PL", CountryType.EU),
    /**
     * Portugal
     */
    PORTUGAL("Portugal", "country_portugal", "PT", CountryType.EU),
    /**
     * Romania
     */
    ROMANIA("Romania", "country_romania", "RO", CountryType.EU),
    /**
     * Slovakia
     */
    SLOVAKIA("Slovakia", "country_slovakia", "SK", CountryType.EU),
    /**
     * Slovenia
     */
    SLOVENIA("Slovenia", "country_slovenia", "SI", CountryType.EU),
    /**
     * Spain
     */
    SPAIN("Spain", "country_spain", "ES", CountryType.EU),
    /**
     * Sweden
     */
    SWEDEN("Sweden", "country_sweden", "SE", CountryType.EU),
    /**
     * United Kingdom
     */
    UNITED_KINGDOM("United_Kingdom", "country_united_kingdom", "GB", CountryType.EU),
    /**
     * Turkey
     */
    TURKEY("Turkey", "country_turkey", "TR", CountryType.EU_PLUS),
    /**
     * Iceland
     */
    ICELAND("Iceland", "country_iceland", "IS", CountryType.EFTA),
    /**
     * Liechtenstein
     */
    LIECHTENSTEIN("Liechtenstein", "country_liechtenstein", "LI", CountryType.EFTA),
    /**
     * Switzerland
     */
    SWITZERLAND("Switzerland", "country_switzerland", "CH", CountryType.EFTA),
    /**
     * Norway
     */
    NORWAY("Norway", "country_norway", "NO", CountryType.EFTA);

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

    @Override
    public String getName() {
        return countryName;
    }

    public static final List<Country> EU_COUNTRIES = Collections.unmodifiableList(
            Arrays.asList(AUSTRIA, BELGIUM, BULGARIA, CROATIA, CYPRUS, CZECH_REPUBLIC, DENMARK, ESTONIA,
                    FINLAND, FRANCE, GERMANY, GREECE, HUNGARY, IRELAND, ITALY, LATVIA, LITHUANIA, LUXEMBOURG, MALTA,
                    NETHERLANDS, POLAND, PORTUGAL, ROMANIA, SLOVAKIA, SLOVENIA, SPAIN, SWEDEN, UNITED_KINGDOM));

    public static final List<Country> EU_PLUS_COUNTRIES = Collections.singletonList(TURKEY);

    public static final List<Country> EFTA_COUNTRIES = Collections
            .unmodifiableList(Arrays.asList(ICELAND, LIECHTENSTEIN, NORWAY, SWITZERLAND));

    public static Country findByIso2Code(String iso2Code) {
        for (Country country : values()) {
            if (country.getIso2Code().equalsIgnoreCase(iso2Code)) {
                return country;
            }
        }
        return null;
    }

}
