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
	AE("AE", "United Arab Emirates", Currency.AED),
	AF("AF", "Afghanistan", Currency.AFN),
	AG("AG", "Antigua and Barbuda", Currency.XCD),
	AI("AI", "Anguilla", Currency.XCD),
	AL("AL", "Albania", Currency.ALL),
	AM("AM", "Armenia", Currency.AMD),
	AO("AO", "Angola", Currency.AOA),
	AQ("AQ", "Antarctica", Currency.USD),
	AR("AR", "Argentina", Currency.ARS),
	AS("AS", "American Samoa", Currency.WST),
	AT("AT", "Austria", Currency.EUR),
	AU("AU", "Australia", Currency.AUD),
	AW("AW", "Aruba", Currency.AWG),
	AX("AX", "Åland Islands", Currency.EUR),
	AZ("AZ", "Azerbaijan", Currency.AZN),
	BA("BA", "Bosnia and Herzegovina", Currency.BAM),
	BB("BB", "Barbados", Currency.BBD),
	BD("BD", "Bangladesh", Currency.BDT),
	BE("BE", "Belgium", Currency.EUR),
	BF("BF", "Burkina Faso", Currency.XOF),
	BG("BG", "Bulgaria", Currency.BGN),
	BH("BH", "Bahrain", Currency.BHD),
	BI("BI", "Burundi", Currency.BIF),
	BJ("BJ", "Benin", Currency.XOF),
	BL("BL", "Saint Barthélemy", Currency.EUR),
	BM("BM", "Bermuda", Currency.BMD),
	BN("BN", "Brunei", Currency.BND),
	BO("BO", "Bolivia", Currency.BOB),
	BQ("BQ", "Bonaire, Saint Eustatius and Saba", Currency.USD),
	BR("BR", "Brazil", Currency.BRL),
	BS("BS", "The Bahamas", Currency.BSD),
	BT("BT", "Bhoutan", Currency.BTN),
	BV("BV", "Bouvet Island", Currency.NOK),
	BW("BW", "Botswana", Currency.BWP),
	BY("BY", "Belarus", Currency.BYR),
	BZ("BZ", "Belize", Currency.BZD),
	CA("CA", "Canada", Currency.CAD),
	CC("CC", "Cocos (Keeling) Islands", Currency.AUD),
	CD("CD", "Democratic Republic of the Congo", Currency.CDF),
	CF("CF", "Central African Republic", Currency.XAF),
	CG("CG", "Congo", Currency.CDF),
	CH("CH", "Switzerland", Currency.CHF),
	CI("CI", "Côte d'Ivoire", Currency.XOF),
	CK("CK", "Cook Islands", Currency.NZD),
	CL("CL", "Chile", Currency.CLP),
	CM("CM", "Cameroon", Currency.XAF),
	CN("CN", "China", Currency.CNY),
	CO("CO", "Colombia", Currency.COP),
	CR("CR", "Costa Rica", Currency.CRC),
	CU("CU", "Cuba", Currency.CUP),
	CV("CV", "Cape Verde", Currency.CVE),
	CW("CW", "Curaçao", Currency.ANG),
	CX("CX", "Christmas Island", Currency.AUD),
	CY("CY", "Cyprus", Currency.CYP),
	CZ("CZ", "Czech Republic", Currency.CZK),
	DE("DE", "Germany", Currency.EUR),
	DJ("DJ", "Djibouti", Currency.DJF),
	DK("DK", "Denmark", Currency.DKK),
	DM("DM", "Dominica", Currency.XCD),
	DO("DO", "Dominican Republic", Currency.DOP),
	DZ("DZ", "Algeria", Currency.DZD),
	EC("EC", "Ecuador", Currency.USD),
	EE("EE", "Estonia", Currency.EUR),
	EG("EG", "Egypt", Currency.EGP),
	EH("EH", "Western Sahara", Currency.MAD),
	ER("ER", "Eritrea", Currency.ERN),
	ES("ES", "Spain", Currency.EUR),
	ET("ET", "Ethiopia", Currency.ETB),
	FI("FI", "Finland", Currency.EUR),
	FJ("FJ", "Fiji", Currency.FJD),
	FK("FK", "Falkland Islands", Currency.FKP),
	FM("FM", "Micronesia", Currency.USD),
	FO("FO", "Faeroe Islands", Currency.DKK),
	FR("FR", "France", Currency.EUR),
	GA("GA", "Gabon", Currency.XAF),
	GB("GB", "United Kingdom", Currency.GBP),
	GD("GD", "Grenada", Currency.XCD),
	GE("GE", "Georgia", Currency.GEL),
	GF("GF", "French Guiana", Currency.EUR),
	GG("GG", "Guernsey", Currency.GBP),
	GH("GH", "Ghana", Currency.GHS),
	GI("GI", "Gibraltar", Currency.GIP),
	GL("GL", "Greenland", Currency.DKK),
	GM("GM", "The Gambia", Currency.GMD),
	GN("GN", "Guinea", Currency.GNF),
	GP("GP", "Guadeloupe", Currency.EUR),
	GQ("GQ", "Equatorial Guinea", Currency.XAF),
	GR("GR", "Greece", Currency.EUR),
	GS("GS", "South Georgia and the South Sandwich Islands", Currency.GBP),
	GT("GT", "Guatemala", Currency.GTQ),
	GU("GU", "Guam", Currency.USD),
	GW("GW", "Guinea-Bissau", Currency.XOF),
	GY("GY", "Guyana", Currency.GYD),
	HK("HK", "Hong Kong", Currency.HKD),
	HM("HM", "Heard Island and McDonald Islands", Currency.AUD),
	HN("HN", "Honduras", Currency.HNL),
	HR("HR", "Croatia", Currency.HRK),
	HT("HT", "Haiti", Currency.HTG),
	HU("HU", "Hungary", Currency.HUF),
	ID("ID", "Indonesia", Currency.IDR),
	IE("IE", "Ireland", Currency.EUR),
	IL("IL", "Israel", Currency.ILS),
	IM("IM", "Isle of Man", Currency.GBP),
	IN("IN", "India", Currency.INR),
	IO("IO", "British Indian Ocean Territory", Currency.USD),
	IQ("IQ", "Iraq", Currency.IQD),
	IR("IR", "Iran", Currency.IRR),
	IS("IS", "Iceland", Currency.ISK),
	IT("IT", "Italy", Currency.EUR),
	JE("JE", "Jersey", Currency.GBP),
	JM("JM", "Jamaica", Currency.JMD),
	JO("JO", "Jordan", Currency.JOD),
	JP("JP", "Japan", Currency.JPY),
	KE("KE", "Kenya", Currency.KES),
	KG("KG", "Kyrgyzstan", Currency.KGS),
	KH("KH", "Cambodia", Currency.KHR),
	KI("KI", "Kiribati", Currency.AUD),
	KM("KM", "The Comoros", Currency.KMF),
	KN("KN", "Saint Kitts and Nevis", Currency.XCD),
	KP("KP", "North Korea", Currency.KPW),
	KR("KR", "South Korea", Currency.KRW),
	KW("KW", "Kuwait", Currency.KWD),
	KY("KY", "Cayman Islands", Currency.KYD),
	KZ("KZ", "Kazakhstan", Currency.KZT),
	LA("LA", "Laos", Currency.LAK),
	LB("LB", "Lebanon", Currency.LBP),
	LC("LC", "Saint Lucia", Currency.XCD),
	LI("LI", "Liechtenstein", Currency.CHF),
	LK("LK", "Sri Lanka", Currency.LKR),
	LR("LR", "Liberia", Currency.LRD),
	LS("LS", "Lesotho", Currency.LSL),
	LT("LT", "Lithuania", Currency.EUR),
	LU("LU", "Luxembourg", Currency.EUR),
	LV("LV", "Latvia", Currency.EUR),
	LY("LY", "Libya", Currency.LYD),
	MA("MA", "Morocco", Currency.MAD),
	MC("MC", "Monaco", Currency.EUR),
	MD("MD", "Moldova", Currency.MDL),
	ME("ME", "Montenegro", Currency.EUR),
	MF("MF", "Saint Martin", Currency.ANG),
	MG("MG", "Madagascar", Currency.MGA),
	MH("MH", "Marshall Islands", Currency.USD),
	MK("MK", "The former Yugoslav Republic of Macedonia", Currency.MKD),
	ML("ML", "Mali", Currency.XOF),
	MM("MM", "Myanmar", Currency.MMK),
	MN("MN", "Mongolia", Currency.MNT),
	MO("MO", "Macau", Currency.HKD),
	MP("MP", "Northern Marianas", Currency.USD),
	MQ("MQ", "Martinique", Currency.EUR),
	MR("MR", "Mauritania", Currency.MRO),
	MS("MS", "Montserrat", Currency.XCD),
	MT("MT", "Malta", Currency.EUR),
	MU("MU", "Mauritius", Currency.MUR),
	MV("MV", "Maldives", Currency.MVR),
	MW("MW", "Malawi", Currency.MWK),
	MX("MX", "Mexico", Currency.MXN),
	MY("MY", "Malaysia", Currency.MYR),
	MZ("MZ", "Mozambique", Currency.MZN),
	NA("NA", "Namibia", Currency.NAD),
	NC("NC", "New Caledonia", Currency.XPF),
	NE("NE", "Niger", Currency.NGN),
	NF("NF", "Norfolk Island", Currency.AUD),
	NG("NG", "Nigeria", Currency.NGN),
	NI("NI", "Nicaragua", Currency.NIO),
	NL("NL", "Netherlands", Currency.EUR),
	NO("NO", "Norway", Currency.NOK),
	NP("NP", "Nepal", Currency.INR),
	NR("NR", "Nauru", Currency.AUD),
	NU("NU", "Niue", Currency.NZD),
	NZ("NZ", "New Zealand", Currency.NZD),
	OM("OM", "Oman", Currency.OMR),
	PA("PA", "Panama", Currency.PAB),
	PE("PE", "Peru", Currency.PEN),
	PF("PF", "French Polynesia", Currency.XPF),
	PG("PG", "Papua New Guinea", Currency.PGK),
	PH("PH", "Philippines", Currency.PHP),
	PK("PK", "Pakistan", Currency.PKR),
	PL("PL", "Poland", Currency.PLN),
	PM("PM", "Saint Pierre and Miquelon", Currency.EUR),
	PN("PN", "Pitcairn Islands", Currency.NZD),
	PR("PR", "Puerto Rico", Currency.USD),
	PS("PS", "Palestine", Currency.ILS),
	PT("PT", "Portugal", Currency.EUR),
	PW("PW", "Palau", Currency.USD),
	PY("PY", "Paraguay", Currency.PYG),
	QA("QA", "Qatar", Currency.QAR),
	RE("RE", "Réunion", Currency.EUR),
	RO("RO", "Romania", Currency.RON),
	RS("RS", "Serbia", Currency.RSD),
	RU("RU", "Russia", Currency.RUB),
	RW("RW", "Rwanda", Currency.RWF),
	SA("SA", "Saudi Arabia", Currency.SAR),
	SB("SB", "Solomon Islands", Currency.SBD),
	SC("SC", "Seychelles", Currency.SCR),
	SD("SD", "South Sudan", Currency.SSP),
	SE("SE", "Sweden", Currency.SEK),
	SG("SG", "Singapore", Currency.BND),
	SH("SH", "Saint Helena", Currency.SHP),
	SI("SI", "Slovenia", Currency.EUR),
	SJ("SJ", "Svalbard and Jan Mayen", Currency.NOK),
	SK("SK", "Slovakia", Currency.EUR),
	SL("SL", "Sierra Leone", Currency.SLL),
	SM("SM", "San Marino", Currency.EUR),
	SN("SN", "Senegal", Currency.XOF),
	SO("SO", "Somalia", Currency.SOS),
	SR("SR", "Suriname", Currency.SRD),
	SS("SS", "Sudan", Currency.SDG),
	ST("ST", "São Tomé and Principe", Currency.STD),
	SV("SV", "El Salvador", Currency.USD),
	SX("SX", "Sint Maarten", Currency.ANG),
	SY("SY", "Syria", Currency.SYP),
	SZ("SZ", "Swaziland", Currency.SZL),
	TC("TC", "Turks and Caicos Islands", Currency.USD),
	TD("TD", "Chad", Currency.XAF),
	TF("TF", "French Southern Territories", Currency.EUR),
	TG("TG", "Togo", Currency.XOF),
	TH("TH", "Thailand", Currency.THB),
	TJ("TJ", "Tajikistan", Currency.TJS),
	TK("TK", "Tokelau", Currency.NZD),
	TL("TL", "East Timor", Currency.USD),
	TM("TM", "Turkmenistan", Currency.TMM),
	TN("TN", "Tunisia", Currency.TND),
	TO("TO", "Tonga", Currency.TOP),
	TR("TR", "Turkey", Currency.TRY),
	TT("TT", "Trinidad and Tobago", Currency.TTD),
	TV("TV", "Tuvalu", Currency.AUD),
	TW("TW", "Taiwan", Currency.TWD),
	TZ("TZ", "Tanzania", Currency.TZS),
	UA("UA", "Ukraine", Currency.UAH),
	UG("UG", "Uganda", Currency.UGX),
	UM("UM", "United States Minor Outlying Islands", Currency.USD),
	US("US", "United States", Currency.USD),
	UY("UY", "Uruguay", Currency.UYU),
	UZ("UZ", "Uzbekistan", Currency.UZS),
	VA("VA", "Vatican City", Currency.EUR),
	VC("VC", "Saint Vincent", Currency.XCD),
	VE("VE", "Venezuela", Currency.VEB),
	VG("VG", "British Virgin Islands", Currency.USD),
	VI("VI", "US Virgin Islands", Currency.USD),
	VN("VN", "Vietnam", Currency.VND),
	VU("VU", "Vanuatu", Currency.VUV),
	WF("WF", "Wallis and Futuna", Currency.XPF),
	WS("WS", "Samoa", Currency.USD),
	XK("XK", "Kosovo", Currency.EUR),
	YE("YE", "Yemen", Currency.YER),
	YT("YT", "Mayotte", Currency.EUR),
	ZA("ZA", "South Africa", Currency.ZAR),
	ZM("ZM", "Zambia", Currency.ZMK),
	ZW("ZW", "Zimbabwe", Currency.INR);

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
