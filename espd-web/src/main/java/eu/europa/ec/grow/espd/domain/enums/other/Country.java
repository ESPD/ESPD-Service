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

	AD("AD", "Andorra", "country_AD"), 
	AE("AE", "United Arab Emirates", "country_AE"), 
	AF("AF", "Afghanistan", "country_AF"), 
	AG("AG", "Antigua and Barbuda", "country_AG"), 
	AI("AI", "Anguilla", "country_AI"), 
	AL("AL", "Albania", "country_AL"), 
	AM("AM", "Armenia", "country_AM"), 
	AO("AO", "Angola", "country_AO"), 
	AQ("AQ", "Antarctica", "country_AQ"), 
	AR("AR", "Argentina", "country_AR"), 
	AS("AS", "American Samoa", "country_AS"), 
	AT("AT", "Austria", "country_AT"), 
	AU("AU", "Australia", "country_AU"), 
	AW("AW", "Aruba", "country_AW"), 
	AX("AX", "Åland Islands", "country_AX"), 
	AZ("AZ", "Azerbaijan", "country_AZ"), 
	BA("BA", "Bosnia and Herzegovina", "country_BA"), 
	BB("BB", "Barbados", "country_BB"), 
	BD("BD", "Bangladesh", "country_BD"), 
	BE("BE", "Belgium", "country_BE"), 
	BF("BF", "Burkina Faso", "country_BF"), 
	BG("BG", "Bulgaria", "country_BG"), 
	BH("BH", "Bahrain", "country_BH"), 
	BI("BI", "Burundi", "country_BI"), 
	BJ("BJ", "Benin", "country_BJ"), 
	BL("BL", "Saint Barthélemy", "country_BL"), 
	BM("BM", "Bermuda", "country_BM"), 
	BN("BN", "Brunei Darussalam", "country_BN"), 
	BO("BO", "Bolivia,  Plurinational State of", "country_BO"), 
	BQ("BQ", "Bonaire,  Sint Eustatius and Saba", "country_BQ"), 
	BR("BR", "Brazil", "country_BR"), 
	BS("BS", "The	Bahamas", "country_BS"), 
	BT("BT", "Bhutan", "country_BT"), 
	BV("BV", "Bouvet Island", "country_BV"), 
	BW("BW", "Botswana", "country_BW"), 
	BY("BY", "Belarus", "country_BY"), 
	BZ("BZ", "Belize", "country_BZ"), 
	CA("CA", "Canada", "country_CA"), 
	CC("CC", "Cocos (Keeling) Islands", "country_CC"), 
	CD("CD", "Congo,  the Democratic Republic of the", "country_CD"), 
	CF("CF", "Central African Republic", "country_CF"), 
	CG("CG", "Congo", "country_CG"), 
	CH("CH", "Switzerland", "country_CH"), 
	CI("CI", "Côte d'Ivoire", "country_CI"), 
	CK("CK", "Cook Islands", "country_CK"), 
	CL("CL", "Chile", "country_CL"), 
	CM("CM", "Cameroon", "country_CM"), 
	CN("CN", "China", "country_CN"), 
	CO("CO", "Colombia", "country_CO"), 
	CR("CR", "Costa Rica", "country_CR"), 
	CU("CU", "Cuba", "country_CU"), 
	CV("CV", "Cabo Verde", "country_CV"), 
	CW("CW", "Curaçao", "country_CW"), 
	CX("CX", "Christmas Island", "country_CX"), 
	CY("CY", "Cyprus", "country_CY"), 
	CZ("CZ", "Czech Republic", "country_CZ"), 
	DE("DE", "Germany", "country_DE"), 
	DJ("DJ", "Djibouti", "country_DJ"), 
	DK("DK", "Denmark", "country_DK"), 
	DM("DM", "Dominica", "country_DM"), 
	DO("DO", "Dominican Republic", "country_DO"), 
	DZ("DZ", "Algeria", "country_DZ"), 
	EC("EC", "Ecuador", "country_EC"), 
	EE("EE", "Estonia", "country_EE"), 
	EG("EG", "Egypt", "country_EG"), 
	EH("EH", "Western Sahara", "country_EH"), 
	ER("ER", "Eritrea", "country_ER"), 
	ES("ES", "Spain", "country_ES"), 
	ET("ET", "Ethiopia", "country_ET"), 
	FI("FI", "Finland", "country_FI"), 
	FJ("FJ", "Fiji", "country_FJ"), 
	FK("FK", "Falkland Islands (Malvinas)", "country_FK"), 
	FM("FM", "Micronesia,  Federated States of", "country_FM"), 
	FO("FO", "Faroe Islands", "country_FO"), 
	FR("FR", "France", "country_FR"), 
	GA("GA", "Gabon", "country_GA"), 
	GB("GB", "United Kingdom of Great Britain and Northern Ireland", "country_GB"), 
	GD("GD", "Grenada", "country_GD"), 
	GE("GE", "Georgia", "country_GE"), 
	GF("GF", "French Guiana", "country_GF"), 
	GG("GG", "Guernsey", "country_GG"), 
	GH("GH", "Ghana", "country_GH"), 
	GI("GI", "Gibraltar", "country_GI"), 
	GL("GL", "Greenland", "country_GL"), 
	GM("GM", "The Gambia", "country_GM"), 
	GN("GN", "Guinea", "country_GN"), 
	GP("GP", "Guadeloupe", "country_GP"), 
	GQ("GQ", "Equatorial Guinea", "country_GQ"), 
	GR("GR", "Greece", "country_GR"), 
	GS("GS", "South Georgia and the South Sandwich Islands", "country_GS"), 
	GT("GT", "Guatemala", "country_GT"), 
	GU("GU", "Guam", "country_GU"), 
	GW("GW", "Guinea-Bissau", "country_GW"), 
	GY("GY", "Guyana", "country_GY"), 
	HK("HK", "Hong Kong", "country_HK"), 
	HM("HM", "Heard Island and McDonald Islands", "country_HM"), 
	HN("HN", "Honduras", "country_HN"), 
	HR("HR", "Croatia", "country_HR"), 
	HT("HT", "Haiti", "country_HT"), 
	HU("HU", "Hungary", "country_HU"), 
	ID("ID", "Indonesia", "country_ID"), 
	IE("IE", "Ireland", "country_IE"), 
	IL("IL", "Israel", "country_IL"), 
	IM("IM", "Isle of Man", "country_IM"), 
	IN("IN", "British	India", "country_IN"), 
	IO("IO", "British Indian Ocean Territory", "country_IO"), 
	IQ("IQ", "Iraq", "country_IQ"), 
	IR("IR", "Iran,  Islamic Republic of", "country_IR"), 
	IS("IS", "Iceland", "country_IS"), 
	IT("IT", "Italy", "country_IT"), 
	JE("JE", "Jersey", "country_JE"), 
	JM("JM", "Jamaica", "country_JM"), 
	JO("JO", "Jordan", "country_JO"), 
	JP("JP", "Japan", "country_JP"), 
	KE("KE", "Kenya", "country_KE"), 
	KG("KG", "Kyrgyzstan", "country_KG"), 
	KH("KH", "Cambodia", "country_KH"), 
	KI("KI", "Kiribati", "country_KI"), 
	KM("KM", "The Comoros", "country_KM"), 
	KN("KN", "Saint Kitts and Nevis", "country_KN"), 
	KP("KP", "North Korea,  Democratic People's Republic of", "country_KP"), 
	KR("KR", "North Korea,  Republic of", "country_KR"), 
	KW("KW", "Kuwait", "country_KW"), 
	KY("KY", "Cayman Islands", "country_KY"), 
	KZ("KZ", "Kazakhstan", "country_KZ"), 
	LA("LA", "Lao People's Democratic Republic", "country_LA"), 
	LB("LB", "Lebanon", "country_LB"), 
	LC("LC", "Saint Lucia", "country_LC"), 
	LI("LI", "Liechtenstein", "country_LI"), 
	LK("LK", "Sri Lanka", "country_LK"), 
	LR("LR", "Liberia", "country_LR"), 
	LS("LS", "Lesotho", "country_LS"), 
	LT("LT", "Lithuania", "country_LT"), 
	LU("LU", "Luxembourg", "country_LU"), 
	LV("LV", "Latvia", "country_LV"), 
	LY("LY", "Libya", "country_LY"), 
	MA("MA", "Morocco", "country_MA"), 
	MC("MC", "Monaco", "country_MC"), 
	MD("MD", "Moldova,  Republic of", "country_MD"), 
	ME("ME", "Montenegro", "country_ME"), 
	MF("MF", "Saint Martin (French part)", "country_MF"), 
	MG("MG", "Madagascar", "country_MG"), 
	MH("MH", "Marshall Islands", "country_MH"), 
	MK("MK", "The former Yugoslav Republic of Macedonia,  the former Yugoslav Republic of", "country_MK"), 
	ML("ML", "Mali", "country_ML"), 
	MM("MM", "Myanmar", "country_MM"), 
	MN("MN", "Mongolia", "country_MN"), 
	MO("MO", "Macao", "country_MO"), 
	MP("MP", "Northern Mariana Islands", "country_MP"), 
	MQ("MQ", "Martinique", "country_MQ"), 
	MR("MR", "Mauritania", "country_MR"), 
	MS("MS", "Montserrat", "country_MS"), 
	MT("MT", "Malta", "country_MT"), 
	MU("MU", "Mauritius", "country_MU"), 
	MV("MV", "Maldives", "country_MV"), 
	MW("MW", "Malawi", "country_MW"), 
	MX("MX", "Mexico", "country_MX"), 
	MY("MY", "Malaysia", "country_MY"), 
	MZ("MZ", "Mozambique", "country_MZ"), 
	NA("NA", "Namibia", "country_NA"), 
	NC("NC", "New Caledonia", "country_NC"), 
	NE("NE", "Niger", "country_NE"), 
	NF("NF", "Norfolk Island", "country_NF"), 
	NG("NG", "Nigeria", "country_NG"), 
	NI("NI", "Nicaragua", "country_NI"), 
	NL("NL", "Netherlands", "country_NL"), 
	NO("NO", "Norway", "country_NO"), 
	NP("NP", "Nepal", "country_NP"), 
	NR("NR", "Nauru", "country_NR"), 
	NU("NU", "Niue", "country_NU"), 
	NZ("NZ", "New Zealand", "country_NZ"), 
	OM("OM", "Oman", "country_OM"), 
	PA("PA", "Panama", "country_PA"), 
	PE("PE", "Peru", "country_PE"), 
	PF("PF", "French Polynesia", "country_PF"), 
	PG("PG", "Papua New Guinea", "country_PG"), 
	PH("PH", "Philippines", "country_PH"), 
	PK("PK", "Pakistan", "country_PK"), 
	PL("PL", "Poland", "country_PL"), 
	PM("PM", "Saint Pierre and Miquelon", "country_PM"), 
	PN("PN", "Pitcairn", "country_PN"), 
	PR("PR", "Puerto Rico", "country_PR"), 
	PS("PS", "Palestine,  State of", "country_PS"), 
	PT("PT", "Portugal", "country_PT"), 
	PW("PW", "Palau", "country_PW"), 
	PY("PY", "Paraguay", "country_PY"), 
	QA("QA", "Qatar", "country_QA"), 
	RE("RE", "Réunion", "country_RE"), 
	RO("RO", "Romania", "country_RO"), 
	RS("RS", "Serbia", "country_RS"), 
	RU("RU", "Russian Federation", "country_RU"), 
	RW("RW", "Rwanda", "country_RW"), 
	SA("SA", "Saudi Arabia", "country_SA"), 
	SB("SB", "Solomon Islands", "country_SB"), 
	SC("SC", "Seychelles", "country_SC"), 
	SD("SD", "South Sudan", "country_SD"), 
	SE("SE", "Sweden", "country_SE"), 
	SG("SG", "Singapore", "country_SG"), 
	SH("SH", "Saint Helena,  Ascension and Tristan da Cunha", "country_SH"), 
	SI("SI", "Slovenia", "country_SI"), 
	SJ("SJ", "Svalbard and Jan Mayen", "country_SJ"), 
	SK("SK", "Slovakia", "country_SK"), 
	SL("SL", "Sierra Leone", "country_SL"), 
	SM("SM", "San Marino", "country_SM"), 
	SN("SN", "Senegal", "country_SN"), 
	SO("SO", "Somalia", "country_SO"), 
	SR("SR", "Suriname", "country_SR"), 
	SS("SS", "South Sudan", "country_SS"), 
	ST("ST", "São Tomé and Principe", "country_ST"), 
	SV("SV", "El Salvador", "country_SV"), 
	SX("SX", "Sint Maarten (Dutch part)", "country_SX"), 
	SY("SY", "Syrian Arab Republic", "country_SY"), 
	SZ("SZ", "Swaziland", "country_SZ"), 
	TC("TC", "Turks and Caicos Islands", "country_TC"), 
	TD("TD", "Chad", "country_TD"), 
	TF("TF", "French Southern Territories", "country_TF"), 
	TG("TG", "Togo", "country_TG"), 
	TH("TH", "Thailand", "country_TH"), 
	TJ("TJ", "Tajikistan", "country_TJ"), 
	TK("TK", "Tokelau", "country_TK"), 
	TL("TL", "Timor-Leste", "country_TL"), 
	TM("TM", "Turkmenistan", "country_TM"), 
	TN("TN", "Tunisia", "country_TN"), 
	TO("TO", "Tonga", "country_TO"), 
	TR("TR", "Turkey", "country_TR"), 
	TT("TT", "Trinidad and Tobago", "country_TT"), 
	TV("TV", "Tuvalu", "country_TV"), 
	TW("TW", "Taiwan,  Province of China", "country_TW"), 
	TZ("TZ", "Tanzania,  United Republic of", "country_TZ"), 
	UA("UA", "Ukraine", "country_UA"), 
	UG("UG", "Uganda", "country_UG"), 
	UM("UM", "United States Minor Outlying Islands", "country_UM"), 
	US("US", "United States of America", "country_US"), 
	UY("UY", "Uruguay", "country_UY"), 
	UZ("UZ", "Uzbekistan", "country_UZ"), 
	VA("VA", "Holy See", "country_VA"), 
	VC("VC", "Saint Vincent and the Grenadines", "country_VC"), 
	VE("VE", "Venezuela,  Bolivarian Republic of", "country_VE"), 
	VG("VG", "British Virgin Islands,  British", "country_VG"), 
	VI("VI", "British Virgin Islands,  U.S.", "country_VI"), 
	VN("VN", "Viet Nam", "country_VN"), 
	VU("VU", "Vanuatu", "country_VU"), 
	WF("WF", "Wallis and Futuna", "country_WF"), 
	WS("WS", "Samoa", "country_WS"), 
	XK("XK", "Kosovo", "country_XK"), 
	YE("YE", "Yemen", "country_YE"), 
	YT("YT", "Mayotte", "country_YT"), 
	ZA("ZA", "South Africa", "country_ZA"), 
	ZM("ZM", "Zambia", "country_ZM"), 
	ZW("ZW", "Zimbabwe", "country_ZW");

    private final String countryName;

    private final String iso2Code;
    
    private final String countryCode;

    private final CountryType countryType;

	private static final Map<String, Country> BY_ISO2_CODE = new HashMap<>(values().length);

	static {
		for (Country c : values()) {
			BY_ISO2_CODE.put(c.getIso2Code().toLowerCase(), c);
		}
	}

	Country(String iso2Code, String countryName, String countryCode) {
		this.countryName = countryName;
		this.iso2Code = iso2Code;
		this.countryCode = countryCode;
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
