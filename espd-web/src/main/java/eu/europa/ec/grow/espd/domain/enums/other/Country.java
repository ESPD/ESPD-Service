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

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Created by vigi on 11/16/15:11:32 AM.
 */
@Getter
public enum Country implements CacCountry {

//    /**
//     * Austria
//     */
//    AUSTRIA("Austria", "country_austria", "AT", CountryType.ISO_3166_1),
//    /**
//     * Belgium
//     */
//    BELGIUM("Belgium", "country_belgium", "BE", CountryType.ISO_3166_1),
//    /**
//     * Bulgaria
//     */
//    BULGARIA("Bulgaria", "country_bulgaria", "BG", CountryType.ISO_3166_1),
//    /**
//     * Croatia
//     */
//    CROATIA("Croatia", "country_croatia", "HR", CountryType.ISO_3166_1),
//    /**
//     * Cyprus
//     */
//    CYPRUS("Cyprus", "country_cyprus", "CY", CountryType.ISO_3166_1),
//    /**
//     * Czech Republic
//     */
//    CZECH_REPUBLIC("Czech_Republic", "country_czech_republic", "CZ", CountryType.ISO_3166_1),
//    /**
//     * Denmark
//     */
//    DENMARK("Denmark", "country_denmark", "DK", CountryType.ISO_3166_1),
//    /**
//     * Estonia
//     */
//    ESTONIA("Estonia", "country_estonia", "EE", CountryType.ISO_3166_1),
//    /**
//     * Finland
//     */
//    FINLAND("Finland", "country_finland", "FI", CountryType.ISO_3166_1),
//    /**
//     * France
//     */
//    FRANCE("France", "country_france", "FR", CountryType.ISO_3166_1),
//    /**
//     * Germany
//     */
//    GERMANY("Germany", "country_germany", "DE", CountryType.ISO_3166_1),
//    /**
//     * Greece
//     */
//    GREECE("Greece", "country_greece", "GR", CountryType.ISO_3166_1),
//    /**
//     * Hungary
//     */
//    HUNGARY("Hungary", "country_hungary", "HU", CountryType.ISO_3166_1),
//    /**
//     * Ireland
//     */
//    IRELAND("Ireland", "country_ireland", "IE", CountryType.ISO_3166_1),
//    /**
//     * Italy
//     */
//    ITALY("Italy", "country_italy", "IT", CountryType.ISO_3166_1),
//    /**
//     * Latvia
//     */
//    LATVIA("Latvia", "country_latvia", "LV", CountryType.ISO_3166_1),
//    /**
//     * Lithuania
//     */
//    LITHUANIA("Lithuania", "country_lithuania", "LT", CountryType.ISO_3166_1),
//    /**
//     * Luxembourg
//     */
//    LUXEMBOURG("Luxembourg", "country_luxembourg", "LU", CountryType.ISO_3166_1),
//    /**
//     * Malta
//     */
//    MALTA("Malta", "country_malta", "MT", CountryType.ISO_3166_1),
//    /**
//     * Netherlands
//     */
//    NETHERLANDS("Netherlands", "country_netherlands", "NL", CountryType.ISO_3166_1),
//    /**
//     * Poland
//     */
//    POLAND("Poland", "country_poland", "PL", CountryType.ISO_3166_1),
//    /**
//     * Portugal
//     */
//    PORTUGAL("Portugal", "country_portugal", "PT", CountryType.ISO_3166_1),
//    /**
//     * Romania
//     */
//    ROMANIA("Romania", "country_romania", "RO", CountryType.ISO_3166_1),
//    /**
//     * Slovakia
//     */
//    SLOVAKIA("Slovakia", "country_slovakia", "SK", CountryType.ISO_3166_1),
//    /**
//     * Slovenia
//     */
//    SLOVENIA("Slovenia", "country_slovenia", "SI", CountryType.ISO_3166_1),
//    /**
//     * Spain
//     */
//    SPAIN("Spain", "country_spain", "ES", CountryType.ISO_3166_1),
//    /**
//     * Sweden
//     */
//    SWEDEN("Sweden", "country_sweden", "SE", CountryType.ISO_3166_1),
//    /**
//     * United Kingdom
//     */
//    UNITED_KINGDOM("United_Kingdom", "country_united_kingdom", "GB", CountryType.ISO_3166_1),
//
//    /**
//     * United Kingdom / England
//     */
//    UNITED_KINGDOM_ENGLAND("United_Kingdom_England", "country_united_kingdom_england", "GB-ENG",
//            CountryType.ISO_3166_2),
//
//    /**
//     * United Kingdom / Northern Ireland
//     */
//    UNITED_KINGDOM_NORTHERN_IRELAND("United_Kingdom_Northern_Ireland", "country_united_kingdom_northern_ireland",
//            "GB-NIR", CountryType.ISO_3166_2),
//
//    /**
//     * United Kingdom / Scotland
//     */
//    UNITED_KINGDOM_SCOTLAND("United_Kingdom_Scotland", "country_united_kingdom_scotland", "GB-SCT",
//            CountryType.ISO_3166_2),
//
//    /**
//     * United Kingdom / Wales
//     */
//    UNITED_KINGDOM_WALES("United_Kingdom_Wales", "country_united_kingdom_wales", "GB-WLS", CountryType.ISO_3166_2),
//
//    /**
//     * Turkey
//     */
//    TURKEY("Turkey", "country_turkey", "TR", CountryType.ISO_3166_1),
//    /**
//     * Iceland
//     */
//    ICELAND("Iceland", "country_iceland", "IS", CountryType.ISO_3166_1),
//    /**
//     * Liechtenstein
//     */
//    LIECHTENSTEIN("Liechtenstein", "country_liechtenstein", "LI", CountryType.ISO_3166_1),
//    /**
//     * Switzerland
//     */
//    SWITZERLAND("Switzerland", "country_switzerland", "CH", CountryType.ISO_3166_1),
//    /**
//     * Norway
//     */
//    NORWAY("Norway", "country_norway", "NO", CountryType.ISO_3166_1);

	AD("AD","Andorra"),
	AE("AE","United Arab Emirates"),
	AF("AF","Afghanistan"),
	AG("AG","Antigua and Barbuda"),
	AI("AI","Anguilla"),
	AL("AL","Albania"),
	AM("AM","Armenia"),
	AO("AO","Angola"),
	AQ("AQ","Antarctica"),
	AR("AR","Argentina"),
	AS("AS","American Samoa"),
	AT("AT","Austria"),
	AU("AU","Australia"),
	AW("AW","Aruba"),
	AX("AX","Åland Islands"),
	AZ("AZ","Azerbaijan"),
	BA("BA","Bosnia and Herzegovina"),
	BB("BB","Barbados"),
	BD("BD","Bangladesh"),
	BE("BE","Belgium"),
	BF("BF","Burkina Faso"),
	BG("BG","Bulgaria"),
	BH("BH","Bahrain"),
	BI("BI","Burundi"),
	BJ("BJ","Benin"),
	BL("BL","Saint Barthélemy"),
	BM("BM","Bermuda"),
	BN("BN","Brunei Darussalam"),
	BO("BO","Bolivia, Plurinational State of"),
	BQ("BQ","Bonaire, Sint Eustatius and Saba"),
	BR("BR","Brazil"),
	BS("BS","Bahamas"),
	BT("BT","Bhutan"),
	BV("BV","Bouvet Island"),
	BW("BW","Botswana"),
	BY("BY","Belarus"),
	BZ("BZ","Belize"),
	CA("CA","Canada"),
	CC("CC","Cocos (Keeling) Islands"),
	CD("CD","Congo, the Democratic Republic of the"),
	CF("CF","Central African Republic"),
	CG("CG","Congo"),
	CH("CH","Switzerland"),
	CI("CI","Côte d'Ivoire"),
	CK("CK","Cook Islands"),
	CL("CL","Chile"),
	CM("CM","Cameroon"),
	CN("CN","China"),
	CO("CO","Colombia"),
	CR("CR","Costa Rica"),
	CU("CU","Cuba"),
	CV("CV","Cabo Verde"),
	CW("CW","Curaçao"),
	CX("CX","Christmas Island"),
	CY("CY","Cyprus"),
	CZ("CZ","Czech Republic"),
	DE("DE","Germany"),
	DJ("DJ","Djibouti"),
	DK("DK","Denmark"),
	DM("DM","Dominica"),
	DO("DO","Dominican Republic"),
	DZ("DZ","Algeria"),
	EC("EC","Ecuador"),
	EE("EE","Estonia"),
	EG("EG","Egypt"),
	EH("EH","Western Sahara"),
	ER("ER","Eritrea"),
	ES("ES","Spain"),
	ET("ET","Ethiopia"),
	FI("FI","Finland"),
	FJ("FJ","Fiji"),
	FK("FK","Falkland Islands (Malvinas)"),
	FM("FM","Micronesia, Federated States of"),
	FO("FO","Faroe Islands"),
	FR("FR","France"),
	GA("GA","Gabon"),
	GB("GB","United Kingdom of Great Britain and Northern Ireland"),
	GD("GD","Grenada"),
	GE("GE","Georgia"),
	GF("GF","French Guiana"),
	GG("GG","Guernsey"),
	GH("GH","Ghana"),
	GI("GI","Gibraltar"),
	GL("GL","Greenland"),
	GM("GM","Gambia"),
	GN("GN","Guinea"),
	GP("GP","Guadeloupe"),
	GQ("GQ","Equatorial Guinea"),
	GR("GR","Greece"),
	GS("GS","South Georgia and the South Sandwich Islands"),
	GT("GT","Guatemala"),
	GU("GU","Guam"),
	GW("GW","Guinea-Bissau"),
	GY("GY","Guyana"),
	HK("HK","Hong Kong"),
	HM("HM","Heard Island and McDonald Islands"),
	HN("HN","Honduras"),
	HR("HR","Croatia"),
	HT("HT","Haiti"),
	HU("HU","Hungary"),
	ID("ID","Indonesia"),
	IE("IE","Ireland"),
	IL("IL","Israel"),
	IM("IM","Isle of Man"),
	IN("IN","India"),
	IO("IO","British Indian Ocean Territory"),
	IQ("IQ","Iraq"),
	IR("IR","Iran, Islamic Republic of"),
	IS("IS","Iceland"),
	IT("IT","Italy"),
	JE("JE","Jersey"),
	JM("JM","Jamaica"),
	JO("JO","Jordan"),
	JP("JP","Japan"),
	KE("KE","Kenya"),
	KG("KG","Kyrgyzstan"),
	KH("KH","Cambodia"),
	KI("KI","Kiribati"),
	KM("KM","Comoros"),
	KN("KN","Saint Kitts and Nevis"),
	KP("KP","Korea, Democratic People's Republic of"),
	KR("KR","Korea, Republic of"),
	KW("KW","Kuwait"),
	KY("KY","Cayman Islands"),
	KZ("KZ","Kazakhstan"),
	LA("LA","Lao People's Democratic Republic"),
	LB("LB","Lebanon"),
	LC("LC","Saint Lucia"),
	LI("LI","Liechtenstein"),
	LK("LK","Sri Lanka"),
	LR("LR","Liberia"),
	LS("LS","Lesotho"),
	LT("LT","Lithuania"),
	LU("LU","Luxembourg"),
	LV("LV","Latvia"),
	LY("LY","Libya"),
	MA("MA","Morocco"),
	MC("MC","Monaco"),
	MD("MD","Moldova, Republic of"),
	ME("ME","Montenegro"),
	MF("MF","Saint Martin (French part)"),
	MG("MG","Madagascar"),
	MH("MH","Marshall Islands"),
	MK("MK","Macedonia, the former Yugoslav Republic of"),
	ML("ML","Mali"),
	MM("MM","Myanmar"),
	MN("MN","Mongolia"),
	MO("MO","Macao"),
	MP("MP","Northern Mariana Islands"),
	MQ("MQ","Martinique"),
	MR("MR","Mauritania"),
	MS("MS","Montserrat"),
	MT("MT","Malta"),
	MU("MU","Mauritius"),
	MV("MV","Maldives"),
	MW("MW","Malawi"),
	MX("MX","Mexico"),
	MY("MY","Malaysia"),
	MZ("MZ","Mozambique"),
	NA("NA","Namibia"),
	NC("NC","New Caledonia"),
	NE("NE","Niger"),
	NF("NF","Norfolk Island"),
	NG("NG","Nigeria"),
	NI("NI","Nicaragua"),
	NL("NL","Netherlands"),
	NO("NO","Norway"),
	NP("NP","Nepal"),
	NR("NR","Nauru"),
	NU("NU","Niue"),
	NZ("NZ","New Zealand"),
	OM("OM","Oman"),
	PA("PA","Panama"),
	PE("PE","Peru"),
	PF("PF","French Polynesia"),
	PG("PG","Papua New Guinea"),
	PH("PH","Philippines"),
	PK("PK","Pakistan"),
	PL("PL","Poland"),
	PM("PM","Saint Pierre and Miquelon"),
	PN("PN","Pitcairn"),
	PR("PR","Puerto Rico"),
	PS("PS","Palestine, State of"),
	PT("PT","Portugal"),
	PW("PW","Palau"),
	PY("PY","Paraguay"),
	QA("QA","Qatar"),
	RE("RE","Réunion"),
	RO("RO","Romania"),
	RS("RS","Serbia"),
	RU("RU","Russian Federation"),
	RW("RW","Rwanda"),
	SA("SA","Saudi Arabia"),
	SB("SB","Solomon Islands"),
	SC("SC","Seychelles"),
	SD("SD","Sudan"),
	SE("SE","Sweden"),
	SG("SG","Singapore"),
	SH("SH","Saint Helena, Ascension and Tristan da Cunha"),
	SI("SI","Slovenia"),
	SJ("SJ","Svalbard and Jan Mayen"),
	SK("SK","Slovakia"),
	SL("SL","Sierra Leone"),
	SM("SM","San Marino"),
	SN("SN","Senegal"),
	SO("SO","Somalia"),
	SR("SR","Suriname"),
	SS("SS","South Sudan"),
	ST("ST","Sao Tome and Principe"),
	SV("SV","El Salvador"),
	SX("SX","Sint Maarten (Dutch part)"),
	SY("SY","Syrian Arab Republic"),
	SZ("SZ","Swaziland"),
	TC("TC","Turks and Caicos Islands"),
	TD("TD","Chad"),
	TF("TF","French Southern Territories"),
	TG("TG","Togo"),
	TH("TH","Thailand"),
	TJ("TJ","Tajikistan"),
	TK("TK","Tokelau"),
	TL("TL","Timor-Leste"),
	TM("TM","Turkmenistan"),
	TN("TN","Tunisia"),
	TO("TO","Tonga"),
	TR("TR","Turkey"),
	TT("TT","Trinidad and Tobago"),
	TV("TV","Tuvalu"),
	TW("TW","Taiwan, Province of China"),
	TZ("TZ","Tanzania, United Republic of"),
	UA("UA","Ukraine"),
	UG("UG","Uganda"),
	UM("UM","United States Minor Outlying Islands"),
	US("US","United States of America"),
	UY("UY","Uruguay"),
	UZ("UZ","Uzbekistan"),
	VA("VA","Holy See"),
	VC("VC","Saint Vincent and the Grenadines"),
	VE("VE","Venezuela, Bolivarian Republic of"),
	VG("VG","Virgin Islands, British"),
	VI("VI","Virgin Islands, U.S."),
	VN("VN","Viet Nam"),
	VU("VU","Vanuatu"),
	WF("WF","Wallis and Futuna"),
	WS("WS","Samoa"),
	YE("YE","Yemen"),
	YT("YT","Mayotte"),
	ZA("ZA","South Africa"),
	ZM("ZM","Zambia"),
	ZW("ZW","Zimbabwe");

    private final String countryName;

    private final String iso2Code;

    private final CountryType countryType;

	private static final Map<String, Country> BY_ISO2_CODE = new HashMap<>(values().length);

	static {
		for (Country c : values()) {
			BY_ISO2_CODE.put(c.getIso2Code().toLowerCase(), c);
		}
	}

	Country(String iso2Code, String countryName) {
		this.countryName = countryName;
		this.iso2Code = iso2Code;
		this.countryType = CountryType.ISO_3166_1;
	}

    @Override
    public String getName() {
        return countryName;
    }

	@Override
    public String getIsoType() {
        return countryType.getIsoType();
    }

    public static Country findByIso2Code(String iso2Code) {
	    return BY_ISO2_CODE.get(trimToEmpty(iso2Code).toLowerCase());
    }

}
