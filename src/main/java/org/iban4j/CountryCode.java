/*
 * Copyright 2013 Artur Mkrtchyan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iban4j;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Country Code Enum
 * <p>
 * <ul>
 * <li><a href="https://en.wikipedia.org/wiki/ISO_3166">ISO 3166</a> at Wikipedia: Codes for the representation of names of countries and their subdivisions.</li>
 * <li><a href="http://en.wikipedia.org/wiki/ISO_3166-1">ISO 3166-1</a> at Wikipedia: Codes for the names of countries, dependent territories, and special areas of geographical interest</li>
 * <li><a href="http://en.wikipedia.org/wiki/ISO_3166-1">ISO 3166-2</a> at Wikipedia: Codes for identifying the principal subdivisions (e.g., provinces or states) of all countries coded in ISO 3166-1</li>
 * <li><a href="http://en.wikipedia.org/wiki/ISO_3166-3">ISO 3166-3</a> at Wikipedia: Codes for country names which have been deleted from ISO 3166-1</li>
 * <li><a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> 2-letter country codes at Wikipedia</li>
 * <li><a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-1 alpha-3</a> 3-letter country codes at Wikipedia</li>
 * <li><a href="https://docs.oracle.com/javase/7/docs/api/java/util/Locale.html">java.util.Locale</a> information</li>
 * </ul>
 * <p>
 * Note DATO 2017-09-09
 * <p>
 * Added "transitionally reserved" (i.e. scheduled for deletion) codes with their deletion date, which is
 * held in a field generally (null)
 */

public enum CountryCode {

	// transitionally reserved codes (from Wikipedia: "Transitional reserved code elements are codes reserved
	// after their deletion from ISO 3166-1. These codes may be used only during a transitional period of at
	// least five years while new code elements that may have replaced them are taken into use. These codes
	// may be reassigned by the ISO 3166/MA after the expiration of the transitional period.

	AN("Netherlands Antilles", "ANT", "2010-12"),
	BU("Burma", "BUR", "1989-12"),
	CS("Czechoslovakia (CSK) or Serbia and Montenegro (SCG)", "SCG", "2006-09"), // this was transferred back then
	NT("Neutral Zone", "NTZ", "1993-07"),
	TP("East Timor", "TMP", "2002-05"),
	YU("Yugoslavia", "YUG", "2003-07"),
	ZR("Zaire", "ZAR", "1997-07"),

	// live codes

	AD("Andorra", "AND"),
	AE("United Arab Emirates", "ARE"),
	AF("Afghanistan", "AFG"),
	AG("Antigua and Barbuda", "ATG"),
	AI("Anguilla", "AIA"),
	AL("Albania", "ALB"),
	AM("Armenia", "ARM"),
	AO("Angola", "AGO"),
	AQ("Antarctica", "ATA"),
	AR("Argentina", "ARG"),
	AS("American Samoa", "ASM"),
	AT("Austria", "AUT"),
	AU("Australia", "AUS"),
	AW("Aruba", "ABW"),
	AX("\u212Bland Islands", "ALA"),
	AZ("Azerbaijan", "AZE"),
	BA("Bosnia and Herzegovina", "BIH"),
	BB("Barbados", "BRB"),
	BD("Bangladesh", "BGD"),
	BE("Belgium", "BEL"),
	BF("Burkina Faso", "BFA"),
	BG("Bulgaria", "BGR"),
	BH("Bahrain", "BHR"),
	BI("Burundi", "BDI"),
	BJ("Benin", "BEN"),
	BL("Saint Barth\u00E9lemy", "BLM"),
	BM("Bermuda", "BMU"),
	BN("Brunei Darussalam", "BRN"),
	BO("Plurinational State of Bolivia", "BOL"),
	BQ("Bonaire, Sint Eustatius and Saba", "BES"),
	BR("Brazil", "BRA"),
	BS("Bahamas", "BHS"),
	BT("Bhutan", "BTN"),
	BV("Bouvet Island", "BVT"),
	BW("Botswana", "BWA"),
	BY("Belarus", "BLR"), // the 2-letter code was BYS before 1992-06
	BZ("Belize", "BLZ"),
	CA("Canada", "CAN"),
	CC("Cocos Islands", "CCK"),
	CD("The Democratic Republic of the Congo", "COD"),
	CF("Central African Republic", "CAF"),
	CG("Congo", "COG"),
	CH("Switzerland", "CHE"),
	CI("C\u00F4te d'Ivoire", "CIV"),
	CK("Cook Islands", "COK"),
	CL("Chile", "CHL"),
	CM("Cameroon", "CMR"),
	CN("China", "CHN"),
	CO("Colombia", "COL"),
	CR("Costa Rica", "CRI"),
	CU("Cuba", "CUB"),
	CV("Cape Verde", "CPV"),
	CW("Cura\u00E7ao", "CUW"),
	CX("Christmas Island", "CXR"),
	CY("Cyprus", "CYP"),
	CZ("Czech Republic", "CZE"),
	DE("Germany", "DEU"),
	DJ("Djibouti", "DJI"),
	DK("Denmark", "DNK"),
	DM("Dominica", "DMA"),
	DO("Dominican Republic", "DOM"),
	DZ("Algeria", "DZA"),
	EC("Ecuador", "ECU"),
	EE("Estonia", "EST"),
	EG("Egypt", "EGY"),
	EH("Western Sahara", "ESH"),
	ER("Eritrea", "ERI"),
	ES("Spain", "ESP"),
	ET("Ethiopia", "ETH"),
	FI("Finland", "FIN"),
	FJ("Fiji", "FJI"),
	FK("Falkland Islands", "FLK"),
	FM("Federated States of Micronesia", "FSM"),
	FO("Faroe Islands", "FRO"),
	FR("France", "FRA"),
	GA("Gabon", "GAB"),
	GB("United Kingdom", "GBR"),
	GD("Grenada", "GRD"),
	GE("Georgia", "GEO"),
	GF("French Guiana", "GUF"),
	GG("Guemsey", "GGY"),
	GH("Ghana", "GHA"),
	GI("Gibraltar", "GIB"),
	GL("Greenland", "GRL"),
	GM("Gambia", "GMB"),
	GN("Guinea", "GIN"),
	GP("Guadeloupe", "GLP"),
	GQ("Equatorial Guinea", "GNQ"),
	GR("Greece", "GRC"),
	GS("South Georgia and the South Sandwich Islands", "SGS"),
	GT("Guatemala", "GTM"),
	GU("Guam", "GUM"),
	GW("Guinea-Bissau", "GNB"),
	GY("Guyana", "GUY"),
	HK("Hong Kong", "HKG"),
	HM("Heard Island and McDonald Islands", "HMD"),
	HN("Honduras", "HND"),
	HR("Croatia", "HRV"),
	HT("Haiti", "HTI"),
	HU("Hungary", "HUN"),
	ID("Indonesia", "IDN"),
	IE("Ireland", "IRL"),
	IL("Israel", "ISR"),
	IM("Isle of Man", "IMN"),
	IN("India", "IND"),
	IO("British Indian Ocean Territory", "IOT"),
	IQ("Iraq", "IRQ"),
	IR("Islamic Republic of Iran", "IRN"),
	IS("Iceland", "ISL"),
	IT("Italy", "ITA"),
	JE("Jersey", "JEY"),
	JM("Jamaica", "JAM"),
	JO("Jordan", "JOR"),
	JP("Japan", "JPN"),
	KE("Kenya", "KEN"),
	KG("Kyrgyzstan", "KGZ"),
	KH("Cambodia", "KHM"),
	KI("Kiribati", "KIR"),
	KM("Comoros", "COM"),
	KN("Saint Kitts and Nevis", "KNA"),
	KP("Democratic People's Republic of Korea", "PRK"),
	KR("Republic of Korea", "KOR"),
	KW("Kuwait", "KWT"),
	KY("Cayman Islands", "CYM"),
	KZ("Kazakhstan", "KAZ"),
	LA("Lao People's Democratic Republic", "LAO"),
	LB("Lebanon", "LBN"),
	LC("Saint Lucia", "LCA"),
	LI("Liechtenstein", "LIE"),
	LK("Sri Lanka", "LKA"),
	LR("Liberia", "LBR"),
	LS("Lesotho", "LSO"),
	LT("Lithuania", "LTU"),
	LU("Luxembourg", "LUX"),
	LV("Latvia", "LVA"),
	LY("Libya", "LBY"),
	MA("Morocco", "MAR"),
	MC("Monaco", "MCO"),
	MD("Republic of Moldova", "MDA"),
	ME("Montenegro", "MNE"),
	MF("Saint Martin", "MAF"),
	MG("Madagascar", "MDG"),
	MH("Marshall Islands", "MHL"),
	MK("The former Yugoslav Republic of Macedonia", "MKD"),
	ML("Mali", "MLI"),
	MM("Myanmar", "MMR"),
	MN("Mongolia", "MNG"),
	MO("Macao", "MAC"),
	MP("Northern Mariana Islands", "MNP"),
	MQ("Martinique", "MTQ"),
	MR("Mauritania", "MRT"),
	MS("Montserrat", "MSR"),
	MT("Malta", "MLT"),
	MU("Mauritius", "MUS"),
	MV("Maldives", "MDV"),
	MW("Malawi", "MWI"),
	MX("Mexico", "MEX"),
	MY("Malaysia", "MYS"),
	MZ("Mozambique", "MOZ"),
	NA("Namibia", "NAM"),
	NC("New Caledonia", "NCL"),
	NE("Niger", "NER"),
	NF("Norfolk Island", "NFK"),
	NG("Nigeria", "NGA"),
	NI("Nicaragua", "NIC"),
	NL("Netherlands", "NLD"),
	NO("Norway", "NOR"),
	NP("Nepal", "NPL"),
	NR("Nauru", "NRU"),
	NU("Niue", "NIU"),
	NZ("New Zealand", "NZL"),
	OM("Oman", "OMN"),
	PA("Panama", "PAN"),
	PE("Peru", "PER"),
	PF("French Polynesia", "PYF"),
	PG("Papua New Guinea", "PNG"),
	PH("Philippines", "PHL"),
	PK("Pakistan", "PAK"),
	PL("Poland", "POL"),
	PM("Saint Pierre and Miquelon", "SPM"),
	PN("Pitcairn", "PCN"),
	PR("Puerto Rico", "PRI"),
	PS("Occupied Palestinian Territory", "PSE"),
	PT("Portugal", "PRT"),
	PW("Palau", "PLW"),
	PY("Paraguay", "PRY"),
	QA("Qatar", "QAT"),
	RE("R\u00E9union", "REU"),
	RO("Romania", "ROU"), // the three-letter code was "ROM" before 2002-02
	RS("Serbia", "SRB"),
	RU("Russian Federation", "RUS"),
	RW("Rwanda", "RWA"),
	SA("Saudi Arabia", "SAU"),
	SB("Solomon Islands", "SLB"),
	SC("Seychelles", "SYC"),
	SD("Sudan", "SDN"),
	SE("Sweden", "SWE"),
	SG("Singapore", "SGP"),
	SH("Saint Helena, Ascension and Tristan da Cunha", "SHN"),
	SI("Slovenia", "SVN"),
	SJ("Svalbard and Jan Mayen", "SJM"),
	SK("Slovakia", "SVK"),
	SL("Sierra Leone", "SLE"),
	SM("San Marino", "SMR"),
	SN("Senegal", "SEN"),
	SO("Somalia", "SOM"),
	SR("Suriname", "SUR"),
	SS("South Sudan", "SSD"),
	ST("Sao Tome and Principe", "STP"),
	SV("El Salvador", "SLV"),
	SX("Sint Maarten", "SXM"),
	SY("Syrian Arab Republic", "SYR"),
	SZ("Swaziland", "SWZ"),
	TC("Turks and Caicos Islands", "TCA"),
	TD("Chad", "TCD"),
	TF("French Southern Territories", "ATF"),
	TG("Togo", "TGO"),
	TH("Thailand", "THA"),
	TJ("Tajikistan", "TJK"),
	TK("Tokelau", "TKL"),
	TL("Timor-Leste", "TLS"),
	TM("Turkmenistan", "TKM"),
	TN("Tunisia", "TUN"),
	TO("Tonga", "TON"),
	TR("Turkey", "TUR"),
	TT("Trinidad and Tobago", "TTO"),
	TV("Tuvalu", "TUV"),
	TW("Taiwan, Province of China", "TWN"),
	TZ("United Republic of Tanzania", "TZA"),
	UA("Ukraine", "UKR"),
	UG("Uganda", "UGA"),
	UM("United States Minor Outlying Islands", "UMI"),
	US("United States", "USA"),
	UY("Uruguay", "URY"),
	UZ("Uzbekistan", "UZB"),
	VA("Holy See", "VAT"),
	VC("Saint Vincent and the Grenadines", "VCT"),
	VE("Bolivarian Republic of Venezuela", "VEN"),
	VG("British Virgin Islands", "VGB"),
	VI("Virgin Islands, U.S.", "VIR"),
	VN("Viet Nam", "VNM"),
	VU("Vanuatu", "VUT"),
	WF("Wallis and Futuna", "WLF"),
	WS("Samoa", "WSM"),
	XK("Kosovo", "UNK"),
	YE("Yemen", "YEM"),
	YT("Mayotte", "MYT"),
	ZA("South Africa", "ZAF"),
	ZM("Zambia", "ZMB"),
	ZW("Zimbabwe", "ZWE");

	/**
	 * Helper class to express a (year,month) tuple
	 */

	public static class YM {

		public final int year;
		public final int month; // ranges from 1 to 12
		public final String txt;

		private final Pattern pat = Pattern.compile("^(\\d{4})-(\\d{2})$");

		public YM(int year, int month) {
			this.year = year;
			this.month = month;
			this.txt = String.format("%04d-%02d", year, month);
			assert this.year > 1900;
			assert 1 <= this.month && this.month <= 12;
		}

		public YM(String expr) {
			Matcher m = pat.matcher(expr);
			boolean ok = m.lookingAt();
			assert ok;
			this.year = Integer.valueOf(m.group(1));
			this.month = Integer.valueOf(m.group(2));
			this.txt = expr;
			assert this.year > 1900;
			assert 1 <= this.month && this.month <= 12;
		}

		public String toString() {
			return txt;
		}
	}

	/**
	 * Country alpha3 code map, maps alpha3 codes to country codes
	 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-3 alpha-3</a> code.
	 */
	private static final Map<String, CountryCode> alpha3Map = new HashMap<String, CountryCode>();

	static {
		for (final CountryCode cc : values()) {
			alpha3Map.put(cc.getAlpha3(), cc);
		}
	}

	/**
	 * The string that is displayed (in english), do not confuse with the Enum "name" as can
	 * be obtained by {@code java.lang.enum.name()} and which corresponds to the enum constant.
	 */
	private final String displayName;

	/**
	 * Country alpha3 code
	 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-3 alpha-3</a> code.
	 */
	private final String alpha3;

	/**
	 * IF this is a "transitional" code, this holds the date from which the trasnitional period starts
	 */
	private final YM tpstart;

	/**
	 * Constructor
	 *
	 * @param displayName - Display text (english)
	 * @param alpha3      - ISO 3166-3 letter code
	 */
	private CountryCode(final String displayName, final String alpha3) {
		this(displayName, alpha3, null);
	}

	/**
	 * Constructor
	 *
	 * @param displayName             - Cleartext (english) displayName
	 * @param alpha3                  - ISO 3166-3 letter code
	 * @param transitionalPeriodStart - "YYYY-MM" when the transitional period starts, or null
	 */
	private CountryCode(final String displayName, final String alpha3, String transitionalPeriodStart) {
		assert displayName.trim().equals(displayName);
		assert alpha3.trim().equals(alpha3);
		assert alpha3.length() == 3;
		assert alpha3.toUpperCase().equals(alpha3);
		this.displayName = displayName;
		this.alpha3 = alpha3;
		if (transitionalPeriodStart == null) {
			this.tpstart = null;
		} else {
			this.tpstart = new YM(transitionalPeriodStart);
		}
	}

	/**
	 * Get the "display displayName" (country displayName with english spelling)
	 * Do not confuse with enum "displayName" as can be obtained by {@code java.lang.enum.displayName()}, which is
	 * the displayName of the enum constant, alias the 2-letter code.
	 *
	 * @return The "display displayName" (country displayName with english spelling)
	 * @deprecated To avoid confusion between cc.displayName() and cc.getName(), the more explicit cc.getDisplayName()
	 * should be used
	 */

	@Deprecated
	public String getName() {
		return displayName;
	}

	/**
	 * Get the "display displayName" (country displayName with english spelling)
	 * Do not confuse with enum "displayName" as can be obtained by {@code java.lang.enum.displayName()}, which is
	 * the displayName of the enum constant, alias the 2-letter code.
	 *
	 * @return The "display displayName" (country displayName with english spelling)
	 */

	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code, which is the same
	 * as {@code displayName()} and as {@code toString()}
	 *
	 * @return The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code.
	 */
	public String getAlpha2() {
		return name();
	}

	/**
	 * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-1 alpha-3</a> code.
	 *
	 * @return The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-1 alpha-3</a> code.
	 */
	public String getAlpha3() {
		return alpha3;
	}

	/**
	 * Get a CountryCode that corresponds to the given ISO 3166-1
	 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> or
	 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
	 *
	 * @param code An ISO 3166-1 <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> or
	 *             <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code. The passed
	 *             code will be uppercased AND trimmed before lookup. Passing null will yield null.
	 * @return A CountryCode instance, or null if not found. Passing null as 'code' will yield null.
	 * Always returns a "live" (not transitional) CountryCode.
	 */
	public static CountryCode getByCode(final String code) {
		return getByCode(code, true);
	}

	/**
	 * Get a CountryCode that corresponds to the given ISO 3166-1
	 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> or
	 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
	 *
	 * @param code     An ISO 3166-1 <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> or
	 *                 <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code. The passed
	 *                 code will be uppercased AND trimmed before lookup. Passing null will yield null.
	 * @param liveOnly Make sure the returned code is a "live" only. Return null if none found.
	 * @return A CountryCode instance, or null if not found. Passing null as 'code' will yield null.
	 */

	public static CountryCode getByCode(final String code, final boolean liveOnly) {
		if (code == null) {
			return null;
		}
		String canonCode = code.trim().toUpperCase();
		CountryCode res;
		switch (canonCode.length()) {
			case 2:
				try {
					res = CountryCode.valueOf(canonCode);
				} catch (IllegalArgumentException e) {
					// enum lookup fails with exception on bad code, so catch that
					res = null;
				}
				break;
			case 3:
				res = alpha3Map.get(canonCode);
				break;
			default:
				// note that codes with length different from 2,3 will yield null silently; is that really ok?
				res = null;
		}
		if (res == null || (liveOnly && res.isTransitional())) {
			return null;
		} else {
			return res;
		}
	}

	/**
	 * If this is a "transitional" code (i.e. no longer live and just reserved as it drops out of use)
	 * then return true.
	 *
	 * @return true if this is a "transitional" code
	 */

	public boolean isTransitional() {
		return tpstart != null;
	}

	/**
	 * Return the time when the transitional period started, or null if this is not a transitional code.
	 *
	 * @return A container class holding year and month of the start of the transitional period if this is
	 * a "transitional" code, and null otherwise.
	 */

	public YM getStartOfTransitionalPeriod() {
		return tpstart;
	}

	/**
	 * For printouts. "toString()" will still print out the 2-character uppercased ISO 3166-1 code.
	 * Note that this uses the english spelling (i.e. not resolved by Locale)
	 */

	public String toStringExtended() {
		// TODO: Buffer the string
		StringBuilder buf = new StringBuilder();
		buf.append("(");
		buf.append(this.getAlpha2());
		buf.append(",");
		buf.append(this.getAlpha3());
		buf.append(",");
		buf.append(this.getName());
		if (isTransitional()) {
			buf.append(",Transitional from ");
			buf.append(tpstart);
		}
		buf.append(")");
		return buf.toString();
	}
}