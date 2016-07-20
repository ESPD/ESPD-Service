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
	
	AD("AD", "Andorra", Currency.EUR),
	AE("AE", "United Arab Emirates", Currency.EUR),
	AF("AF", "Afghanistan", Currency.EUR),
	AG("AG", "Antigua and Barbuda", Currency.EUR),
	AI("AI", "Anguilla", Currency.EUR),
	AL("AL", "Albania", Currency.EUR),
	AM("AM", "Armenia", Currency.EUR),
	AO("AO", "Angola", Currency.EUR),
	AQ("AQ", "Antarctica", Currency.EUR),
	AR("AR", "Argentina", Currency.EUR),
	AS("AS", "American Samoa", Currency.EUR),
	AT("AT", "Austria", Currency.EUR),
	AU("AU", "Australia", Currency.EUR),
	AW("AW", "Aruba", Currency.EUR),
	AX("AX", "Åland Islands", Currency.EUR),
	AZ("AZ", "Azerbaijan", Currency.EUR),
	BA("BA", "Bosnia and Herzegovina", Currency.EUR),
	BB("BB", "Barbados", Currency.EUR),
	BD("BD", "Bangladesh", Currency.EUR),
	BE("BE", "Belgium", Currency.EUR),
	BF("BF", "Burkina Faso", Currency.EUR),
	BG("BG", "Bulgaria", Currency.EUR),
	BH("BH", "Bahrain", Currency.EUR),
	BI("BI", "Burundi", Currency.EUR),
	BJ("BJ", "Benin", Currency.EUR),
	BL("BL", "Saint Barthélemy", Currency.EUR),
	BM("BM", "Bermuda", Currency.EUR),
	BN("BN", "Brunei Darussalam", Currency.EUR),
	BO("BO", "Bolivia,  Plurinational State of", Currency.EUR),
	BQ("BQ", "Bonaire,  Sint Eustatius and Saba", Currency.EUR),
	BR("BR", "Brazil", Currency.EUR),
	BS("BS", "The	Bahamas", Currency.EUR),
	BT("BT", "Bhutan", Currency.EUR),
	BV("BV", "Bouvet Island", Currency.EUR),
	BW("BW", "Botswana", Currency.EUR),
	BY("BY", "Belarus", Currency.EUR),
	BZ("BZ", "Belize", Currency.EUR),
	CA("CA", "Canada", Currency.EUR),
	CC("CC", "Cocos (Keeling) Islands", Currency.EUR),
	CD("CD", "Congo,  the Democratic Republic of the", Currency.EUR),
	CF("CF", "Central African Republic", Currency.EUR),
	CG("CG", "Congo", Currency.EUR),
	CH("CH", "Switzerland", Currency.EUR),
	CI("CI", "Côte d'Ivoire", Currency.EUR),
	CK("CK", "Cook Islands", Currency.EUR),
	CL("CL", "Chile", Currency.EUR),
	CM("CM", "Cameroon", Currency.EUR),
	CN("CN", "China", Currency.EUR),
	CO("CO", "Colombia", Currency.EUR),
	CR("CR", "Costa Rica", Currency.EUR),
	CU("CU", "Cuba", Currency.EUR),
	CV("CV", "Cabo Verde", Currency.EUR),
	CW("CW", "Curaçao", Currency.EUR),
	CX("CX", "Christmas Island", Currency.EUR),
	CY("CY", "Cyprus", Currency.EUR),
	CZ("CZ", "Czech Republic", Currency.EUR),
	DE("DE", "Germany", Currency.EUR),
	DJ("DJ", "Djibouti", Currency.EUR),
	DK("DK", "Denmark", Currency.EUR),
	DM("DM", "Dominica", Currency.EUR),
	DO("DO", "Dominican Republic", Currency.EUR),
	DZ("DZ", "Algeria", Currency.EUR),
	EC("EC", "Ecuador", Currency.EUR),
	EE("EE", "Estonia", Currency.EUR),
	EG("EG", "Egypt", Currency.EUR),
	EH("EH", "Western Sahara", Currency.EUR),
	ER("ER", "Eritrea", Currency.EUR),
	ES("ES", "Spain", Currency.EUR),
	ET("ET", "Ethiopia", Currency.EUR),
	FI("FI", "Finland", Currency.EUR),
	FJ("FJ", "Fiji", Currency.EUR),
	FK("FK", "Falkland Islands (Malvinas)", Currency.EUR),
	FM("FM", "Micronesia,  Federated States of", Currency.EUR),
	FO("FO", "Faroe Islands", Currency.EUR),
	FR("FR", "France", Currency.EUR),
	GA("GA", "Gabon", Currency.EUR),
	GB("GB", "United Kingdom of Great Britain and Northern Ireland", Currency.EUR),
	GD("GD", "Grenada", Currency.EUR),
	GE("GE", "Georgia", Currency.EUR),
	GF("GF", "French Guiana", Currency.EUR),
	GG("GG", "Guernsey", Currency.EUR),
	GH("GH", "Ghana", Currency.EUR),
	GI("GI", "Gibraltar", Currency.EUR),
	GL("GL", "Greenland", Currency.EUR),
	GM("GM", "The Gambia", Currency.EUR),
	GN("GN", "Guinea", Currency.EUR),
	GP("GP", "Guadeloupe", Currency.EUR),
	GQ("GQ", "Equatorial Guinea", Currency.EUR),
	GR("GR", "Greece", Currency.EUR),
	GS("GS", "South Georgia and the South Sandwich Islands", Currency.EUR),
	GT("GT", "Guatemala", Currency.EUR),
	GU("GU", "Guam", Currency.EUR),
	GW("GW", "Guinea-Bissau", Currency.EUR),
	GY("GY", "Guyana", Currency.EUR),
	HK("HK", "Hong Kong", Currency.EUR),
	HM("HM", "Heard Island and McDonald Islands", Currency.EUR),
	HN("HN", "Honduras", Currency.EUR),
	HR("HR", "Croatia", Currency.EUR),
	HT("HT", "Haiti", Currency.EUR),
	HU("HU", "Hungary", Currency.EUR),
	ID("ID", "Indonesia", Currency.EUR),
	IE("IE", "Ireland", Currency.EUR),
	IL("IL", "Israel", Currency.EUR),
	IM("IM", "Isle of Man", Currency.EUR),
	IN("IN", "British	India", Currency.EUR),
	IO("IO", "British Indian Ocean Territory", Currency.EUR),
	IQ("IQ", "Iraq", Currency.EUR),
	IR("IR", "Iran,  Islamic Republic of", Currency.EUR),
	IS("IS", "Iceland", Currency.EUR),
	IT("IT", "Italy", Currency.EUR),
	JE("JE", "Jersey", Currency.EUR),
	JM("JM", "Jamaica", Currency.EUR),
	JO("JO", "Jordan", Currency.EUR),
	JP("JP", "Japan", Currency.EUR),
	KE("KE", "Kenya", Currency.EUR),
	KG("KG", "Kyrgyzstan", Currency.EUR),
	KH("KH", "Cambodia", Currency.EUR),
	KI("KI", "Kiribati", Currency.EUR),
	KM("KM", "The Comoros", Currency.EUR),
	KN("KN", "Saint Kitts and Nevis", Currency.EUR),
	KP("KP", "North Korea,  Democratic People's Republic of", Currency.EUR),
	KR("KR", "North Korea,  Republic of", Currency.EUR),
	KW("KW", "Kuwait", Currency.EUR),
	KY("KY", "Cayman Islands", Currency.EUR),
	KZ("KZ", "Kazakhstan", Currency.EUR),
	LA("LA", "Lao People's Democratic Republic", Currency.EUR),
	LB("LB", "Lebanon", Currency.EUR),
	LC("LC", "Saint Lucia", Currency.EUR),
	LI("LI", "Liechtenstein", Currency.EUR),
	LK("LK", "Sri Lanka", Currency.EUR),
	LR("LR", "Liberia", Currency.EUR),
	LS("LS", "Lesotho", Currency.EUR),
	LT("LT", "Lithuania", Currency.EUR),
	LU("LU", "Luxembourg", Currency.EUR),
	LV("LV", "Latvia", Currency.EUR),
	LY("LY", "Libya", Currency.EUR),
	MA("MA", "Morocco", Currency.EUR),
	MC("MC", "Monaco", Currency.EUR),
	MD("MD", "Moldova,  Republic of", Currency.EUR),
	ME("ME", "Montenegro", Currency.EUR),
	MF("MF", "Saint Martin (French part)", Currency.EUR),
	MG("MG", "Madagascar", Currency.EUR),
	MH("MH", "Marshall Islands", Currency.EUR),
	MK("MK", "The former Yugoslav Republic of Macedonia,  the former Yugoslav Republic of", Currency.EUR),
	ML("ML", "Mali", Currency.EUR),
	MM("MM", "Myanmar", Currency.EUR),
	MN("MN", "Mongolia", Currency.EUR),
	MO("MO", "Macao", Currency.EUR),
	MP("MP", "Northern Mariana Islands", Currency.EUR),
	MQ("MQ", "Martinique", Currency.EUR),
	MR("MR", "Mauritania", Currency.EUR),
	MS("MS", "Montserrat", Currency.EUR),
	MT("MT", "Malta", Currency.EUR),
	MU("MU", "Mauritius", Currency.EUR),
	MV("MV", "Maldives", Currency.EUR),
	MW("MW", "Malawi", Currency.EUR),
	MX("MX", "Mexico", Currency.EUR),
	MY("MY", "Malaysia", Currency.EUR),
	MZ("MZ", "Mozambique", Currency.EUR),
	NA("NA", "Namibia", Currency.EUR),
	NC("NC", "New Caledonia", Currency.EUR),
	NE("NE", "Niger", Currency.EUR),
	NF("NF", "Norfolk Island", Currency.EUR),
	NG("NG", "Nigeria", Currency.EUR),
	NI("NI", "Nicaragua", Currency.EUR),
	NL("NL", "Netherlands", Currency.EUR),
	NO("NO", "Norway", Currency.EUR),
	NP("NP", "Nepal", Currency.EUR),
	NR("NR", "Nauru", Currency.EUR),
	NU("NU", "Niue", Currency.EUR),
	NZ("NZ", "New Zealand", Currency.EUR),
	OM("OM", "Oman", Currency.EUR),
	PA("PA", "Panama", Currency.EUR),
	PE("PE", "Peru", Currency.EUR),
	PF("PF", "French Polynesia", Currency.EUR),
	PG("PG", "Papua New Guinea", Currency.EUR),
	PH("PH", "Philippines", Currency.EUR),
	PK("PK", "Pakistan", Currency.EUR),
	PL("PL", "Poland", Currency.EUR),
	PM("PM", "Saint Pierre and Miquelon", Currency.EUR),
	PN("PN", "Pitcairn", Currency.EUR),
	PR("PR", "Puerto Rico", Currency.EUR),
	PS("PS", "Palestine,  State of", Currency.EUR),
	PT("PT", "Portugal", Currency.EUR),
	PW("PW", "Palau", Currency.EUR),
	PY("PY", "Paraguay", Currency.EUR),
	QA("QA", "Qatar", Currency.EUR),
	RE("RE", "Réunion", Currency.EUR),
	RO("RO", "Romania", Currency.EUR),
	RS("RS", "Serbia", Currency.EUR),
	RU("RU", "Russian Federation", Currency.EUR),
	RW("RW", "Rwanda", Currency.EUR),
	SA("SA", "Saudi Arabia", Currency.EUR),
	SB("SB", "Solomon Islands", Currency.EUR),
	SC("SC", "Seychelles", Currency.EUR),
	SD("SD", "South Sudan", Currency.EUR),
	SE("SE", "Sweden", Currency.EUR),
	SG("SG", "Singapore", Currency.EUR),
	SH("SH", "Saint Helena,  Ascension and Tristan da Cunha", Currency.EUR),
	SI("SI", "Slovenia", Currency.EUR),
	SJ("SJ", "Svalbard and Jan Mayen", Currency.EUR),
	SK("SK", "Slovakia", Currency.EUR),
	SL("SL", "Sierra Leone", Currency.EUR),
	SM("SM", "San Marino", Currency.EUR),
	SN("SN", "Senegal", Currency.EUR),
	SO("SO", "Somalia", Currency.EUR),
	SR("SR", "Suriname", Currency.EUR),
	SS("SS", "South Sudan", Currency.EUR),
	ST("ST", "São Tomé and Principe", Currency.EUR),
	SV("SV", "El Salvador", Currency.EUR),
	SX("SX", "Sint Maarten (Dutch part)", Currency.EUR),
	SY("SY", "Syrian Arab Republic", Currency.EUR),
	SZ("SZ", "Swaziland", Currency.EUR),
	TC("TC", "Turks and Caicos Islands", Currency.EUR),
	TD("TD", "Chad", Currency.EUR),
	TF("TF", "French Southern Territories", Currency.EUR),
	TG("TG", "Togo", Currency.EUR),
	TH("TH", "Thailand", Currency.EUR),
	TJ("TJ", "Tajikistan", Currency.EUR),
	TK("TK", "Tokelau", Currency.EUR),
	TL("TL", "Timor-Leste", Currency.EUR),
	TM("TM", "Turkmenistan", Currency.EUR),
	TN("TN", "Tunisia", Currency.EUR),
	TO("TO", "Tonga", Currency.EUR),
	TR("TR", "Turkey", Currency.EUR),
	TT("TT", "Trinidad and Tobago", Currency.EUR),
	TV("TV", "Tuvalu", Currency.EUR),
	TW("TW", "Taiwan,  Province of China", Currency.EUR),
	TZ("TZ", "Tanzania,  United Republic of", Currency.EUR),
	UA("UA", "Ukraine", Currency.EUR),
	UG("UG", "Uganda", Currency.EUR),
	UM("UM", "United States Minor Outlying Islands", Currency.EUR),
	US("US", "United States of America", Currency.EUR),
	UY("UY", "Uruguay", Currency.EUR),
	UZ("UZ", "Uzbekistan", Currency.EUR),
	VA("VA", "Holy See", Currency.EUR),
	VC("VC", "Saint Vincent and the Grenadines", Currency.EUR),
	VE("VE", "Venezuela,  Bolivarian Republic of", Currency.EUR),
	VG("VG", "British Virgin Islands,  British", Currency.EUR),
	VI("VI", "British Virgin Islands,  U.S.", Currency.EUR),
	VN("VN", "Viet Nam", Currency.EUR),
	VU("VU", "Vanuatu", Currency.EUR),
	WF("WF", "Wallis and Futuna", Currency.EUR),
	WS("WS", "Samoa", Currency.EUR),
	XK("XK", "Kosovo", Currency.EUR),
	YE("YE", "Yemen", Currency.EUR),
	YT("YT", "Mayotte", Currency.EUR),
	ZA("ZA", "South Africa", Currency.EUR),
	ZM("ZM", "Zambia", Currency.EUR),
	ZW("ZW", "Zimbabwe", Currency.EUR);

    private final String countryName;

    private final String iso2Code;

    private final CountryType countryType;
    
    private final Currency currency;

	private static final Map<String, Country> BY_ISO2_CODE = new HashMap<>(values().length);

	static {
		for (Country c : values()) {
			BY_ISO2_CODE.put(c.getIso2Code().toLowerCase(), c);
		}
	}

	Country(String iso2Code, String countryName, Currency currency) {
		this.countryName = countryName;
		this.currency = currency;
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
