package eu.europa.ec.grow.espd.constants;

import com.google.common.collect.ImmutableList;
import lombok.Getter;

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
    AUSTRIA("Austria", "country_austria", "at", EU),
    /**
     * Belgium
     */
    BELGIUM("Belgium", "country_belgium", "be", EU),
    /**
     * Bulgaria
     */
    BULGARIA("Bulgaria", "country_bulgaria", "bg", EU),
    /**
     * Croatia
     */
    CROATIA("Croatia", "country_croatia", "hr", EU),
    /**
     * Cyprus
     */
    CYPRUS("Cyprus", "country_cyprus", "cy", EU),
    /**
     * Czech Republic
     */
    CZECH_REPUBLIC("Czech_Republic", "country_czech_republic", "cz", EU),
    /**
     * Denmark
     */
    DENMARK("Denmark", "country_denmark", "dk", EU),
    /**
     * Estonia
     */
    ESTONIA("Estonia", "country_estonia", "ee", EU),
    /**
     * Finland
     */
    FINLAND("Finland", "country_finland", "fi", EU),
    /**
     * France
     */
    FRANCE("France", "country_france", "fr", EU),
    /**
     * Germany
     */
    GERMANY("Germany", "country_germany", "de", EU),
    /**
     * Greece
     */
    GREECE("Greece", "country_greece", "gr", EU),
    /**
     * Hungary
     */
    HUNGARY("Hungary", "country_hungary", "hu", EU),
    /**
     * Ireland
     */
    IRELAND("Ireland", "country_ireland", "ie", EU),
    /**
     * Italy
     */
    ITALY("Italy", "country_italy", "it", EU),
    /**
     * Latvia
     */
    LATVIA("Latvia", "country_latvia", "lv", EU),
    /**
     * Lithuania
     */
    LITHUANIA("Lithuania", "country_lithuania", "lt", EU),
    /**
     * Luxembourg
     */
    LUXEMBOURG("Luxembourg", "country_luxembourg", "lu", EU),
    /**
     * Malta
     */
    MALTA("Malta", "country_malta", "mt", EU),
    /**
     * Netherlands
     */
    NETHERLANDS("Netherlands", "country_netherlands", "nl", EU),
    /**
     * Poland
     */
    POLAND("Poland", "country_poland", "pl", EU),
    /**
     * Portugal
     */
    PORTUGAL("Portugal", "country_portugal", "pt", EU),
    /**
     * Romania
     */
    ROMANIA("Romania", "country_romania", "ro", EU),
    /**
     * Slovakia
     */
    SLOVAKIA("Slovakia", "country_slovakia", "sk", EU),
    /**
     * Slovenia
     */
    SLOVENIA("Slovenia", "country_slovenia", "si", EU),
    /**
     * Spain
     */
    SPAIN("Spain", "country_spain", "es", EU),
    /**
     * Sweden
     */
    SWEDEN("Sweden", "country_sweden", "se", EU),
    /**
     * United Kingdom
     */
    UNITED_KINGDOM("United_Kingdom", "country_united_kingdom", "gb", EU),
    /**
     * Turkey
     */
    TURKEY("Turkey", "country_turkey", "tr", EU_PLUS),
    /**
     * Iceland
     */
    ICELAND("Iceland", "country_iceland", "is", EFTA),
    /**
     * Liechtenstein
     */
    LIECHTENSTEIN("Liechtenstein", "country_liechtenstein", "li", EFTA),
    /**
     * Switzerland
     */
    SWITZERLAND("Switzerland", "country_switzerland", "ch", EFTA),
    /**
     * Norway
     */
    NORWAY("Norway", "country_norway", "no", EFTA);

    private final String countryName;

    private final String i18nCode;

    private final String iso2Code;

    private final CountryType countryType;

    Country(final String countryName, final String i18nCode, final String iso2Code, final CountryType countryType) {
        this.countryName = countryName;
        this.i18nCode = i18nCode;
        this.iso2Code = iso2Code;
        this.countryType = countryType;
    }

    public static List<Country> getEuCountries() {
        return ImmutableList.of(AUSTRIA, BELGIUM, BULGARIA, CROATIA, CYPRUS, CZECH_REPUBLIC, DENMARK, ESTONIA,
                FINLAND, FRANCE, GERMANY, GREECE, HUNGARY, IRELAND, ITALY, LATVIA, LITHUANIA, LUXEMBOURG, MALTA,
                NETHERLANDS, POLAND, PORTUGAL, ROMANIA, SLOVAKIA, SLOVENIA, SPAIN, SWEDEN, UNITED_KINGDOM);
    }

    public static List<Country> getEftaCountries() {
        return ImmutableList.of(ICELAND, LIECHTENSTEIN, NORWAY, SWITZERLAND);
    }

    public static List<Country> getEuPlusCountries() {
        return ImmutableList.of(TURKEY);
    }

}
