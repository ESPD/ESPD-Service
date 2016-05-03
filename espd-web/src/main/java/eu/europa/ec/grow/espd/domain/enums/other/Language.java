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
 * Created by vigi on 11/16/15:11:12 AM.
 */
@Getter
public enum Language {

    /**
     * Bulgarian
     */
    BULGARIAN( "български", "Bulgarian", "bg"),
    /**
     * Czech
     */
    CZECH("čeština", "Czech", "cs"),
    /**
     * Danish
     */
    DANISH( "Dansk", "Danish", "da"),
    /**
     * German
     */
    GERMAN("Deutsch", "German", "de"),
    /**
     * Estonian
     */
    ESTONIAN( "Eesti keel", "Estonian", "et"),
    /**
     * Greek
     */
    GREEK("Eλληνικά", "Greek", "el"),
    /**
     * English
     */
    ENGLISH( "English", "English", "en"),
    /**
     * Spanish
     */
    SPANISH( "Español", "Spanish", "es"),
    /**
     * French
     */
    FRENCH( "Français", "French", "fr"),
    /**
     * Croatian
     */
    CROATIAN( "Hrvatski", "Croatian", "hr"),
    /**
     * Italian
     */
    ITALIAN( "Italiano", "Italian", "it"),
    /**
     * Latvian
     */
    LATVIAN("Latviešu valoda", "Latvian", "lv"),
    /**
     * Lithuanian
     */
    LITHUANIAN( "Lietuvių kalba", "Lithuanian", "lt"),
    /**
     * Hungarian
     */
    HUNGARIAN( "Magyar", "Hungarian", "hu"),
    /**
     * Maltese
     */
    MALTESE( "Malti", "Maltese", "mt"),
    /**
     * Dutch
     */
    DUTCH( "Nederlands", "Dutch", "nl"),
    /**
     * Polish
     */
    POLISH( "Polski", "Polish", "pl"),
    /**
     * Portuguese
     */
    PORTUGUESE( "Português", "Portuguese", "pt"),
    /**
     * Romanian
     */
    ROMANIAN( "Română", "Romanian", "ro"),
    /**
     * Slovak
     */
    SLOVAK( "Slovenčina", "Slovak", "sk"),
    /**
     * Slovenian
     */
    SLOVENIAN( "Slovenščina", "Slovenian", "sl"),
    /**
     * Finnish
     */
    FINNISH( "Suomi", "Finnish", "fi"),
    /**
     * Swedish
     */
    SWEDISH( "Svenska", "Swedish", "sv");

    private int order;

    private String sourceLanguage;

    private String englishName;

    private String code;

    Language(final String sourceLanguage, final String englishName, final String code) {
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
