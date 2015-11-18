package eu.europa.ec.grow.espd.constants;

import lombok.Getter;

/**
 * Created by vigi on 11/16/15:11:12 AM.
 */
@Getter
public enum Language {

    /**
     * Bulgarian
     */
    BULGARIAN(1, "български", "Bulgarian", "bg"),
    /**
     * Spanish
     */
    SPANISH(2, "Español", "Spanish", "es"),
    /**
     * Czech
     */
    CZECH(3, "čeština", "Czech", "cs"),
    /**
     * Danish
     */
    DANISH(4, "Dansk", "Danish", "da"),
    /**
     * German
     */
    GERMAN(5, "Deutsch", "German", "de"),
    /**
     * Estonian
     */
    ESTONIAN(6, "Eesti", "Estonian", "et"),
    /**
     * Greek
     */
    GREEK(7, "Eλληνικά", "Greek", "el"),
    /**
     * English
     */
    ENGLISH(8, "English", "English", "en"),
    /**
     * French
     */
    FRENCH(9, "Français", "French", "fr"),
    /**
     * Irish
     */
    IRISH(10, "Gaeilge", "Irish", "ga"),
    /**
     * Croatian
     */
    CROATIAN(11, "Hrvatski", "Croatian", "hr"),
    /**
     * Italian
     */
    ITALIAN(12, "Italiano", "Italian", "it"),
    /**
     * Latvian
     */
    LATVIAN(13, "Latviešu", "Latvian", "lv"),
    /**
     * Lithuanian
     */
    LITHUANIAN(14, "Lietuvių", "Lithuanian", "lt"),
    /**
     * Hungarian
     */
    HUNGARIAN(15, "Magyar", "Hungarian", "hu"),
    /**
     * Maltese
     */
    MALTESE(16, "Malti", "Maltese", "mt"),
    /**
     * Dutch
     */
    DUTCH(17, "Nederlands", "Dutch", "nl"),
    /**
     * Polish
     */
    POLISH(18, "Polski", "Polish", "pl"),
    /**
     * Portuguese
     */
    PORTUGUESE(19, "Português", "Portuguese", "pt"),
    /**
     * Romanian
     */
    ROMANIAN(20, "Română", "Romanian", "ro"),
    /**
     * Slovak
     */
    SLOVAK(21, "Slovenčina", "Slovak", "sk"),
    /**
     * Slovenian
     */
    SLOVENIAN(22, "Slovenščina", "Slovenian", "sl"),
    /**
     * Finnish
     */
    FINNISH(23, "Suomi", "Finnish", "fi"),
    /**
     * Swedish
     */
    SWEDISH(24, "Svenska", "Swedish", "sv");

    private int order;

    private String sourceLanguage;

    private String englishName;

    private String code;

    Language(final int order, final String sourceLanguage, final String englishName, final String code) {
        this.order = order;
        this.sourceLanguage = sourceLanguage;
        this.englishName = englishName;
        this.code = code;
    }

    /**
     * Load an official language by the ISO-2 code. Returns null if the language
     * cannot be found in the list.
     *
     * @param code The language code
     *
     * @return The language if the code was correct, null otherwise
     *
     */
    public static Language getByLanguageCode(final String code) {
        for (Language lang : values()) {
            if (lang.getCode().equalsIgnoreCase(code)) {
                return lang;
            }
        }
        return null;
    }

}
