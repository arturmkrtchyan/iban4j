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

/**
 * Represents an <a href="http://en.wikipedia.org/wiki/ISO_3166-1">ISO 3166-1</a> country code.
 * <p>
 * This enum provides a comprehensive list of officially assigned two-letter (alpha-2)
 * and three-letter (alpha-3) country codes, along with their full country names.
 */
public enum CountryCode {

    /** Andorra */
    AD("Andorra", "AND"),
    /** United Arab Emirates */
    AE("United Arab Emirates", "ARE"),
    /** Afghanistan */
    AF("Afghanistan", "AFG"),
    /** Antigua and Barbuda */
    AG("Antigua and Barbuda", "ATG"),
    /** Anguilla */
    AI("Anguilla", "AIA"),
    /** Albania */
    AL("Albania", "ALB"),
    /** Armenia */
    AM("Armenia", "ARM"),
    /** Angola */
    AO("Angola", "AGO"),
    /** Antarctica */
    AQ("Antarctica", "ATA"),
    /** Argentina */
    AR("Argentina", "ARG"),
    /** American Samoa */
    AS("American Samoa", "ASM"),
    /** Austria */
    AT("Austria", "AUT"),
    /** Australia */
    AU("Australia", "AUS"),
    /** Aruba */
    AW("Aruba", "ABW"),
    /** Åland Islands */
    AX("\u212Bland Islands", "ALA"),
    /** Azerbaijan */
    AZ("Azerbaijan", "AZE"),
    /** Bosnia and Herzegovina */
    BA("Bosnia and Herzegovina", "BIH"),
    /** Barbados */
    BB("Barbados", "BRB"),
    /** Bangladesh */
    BD("Bangladesh", "BGD"),
    /** Belgium */
    BE("Belgium", "BEL"),
    /** Burkina Faso */
    BF("Burkina Faso", "BFA"),
    /** Bulgaria */
    BG("Bulgaria", "BGR"),
    /** Bahrain */
    BH("Bahrain", "BHR"),
    /** Burundi */
    BI("Burundi", "BDI"),
    /** Benin */
    BJ("Benin", "BEN"),
    /** Saint Barthélemy */
    BL("Saint Barth\u00E9lemy", "BLM"),
    /** Bermuda */
    BM("Bermuda", "BMU"),
    /** Brunei Darussalam */
    BN("Brunei Darussalam", "BRN"),
    /** Plurinational State of Bolivia */
    BO("Plurinational State of Bolivia", "BOL"),
    /** Bonaire, Sint Eustatius and Saba */
    BQ("Bonaire, Sint Eustatius and Saba", "BES"),
    /** Brazil */
    BR("Brazil", "BRA"),
    /** Bahamas */
    BS("Bahamas", "BHS"),
    /** Bhutan */
    BT("Bhutan", "BTN"),
    /** Bouvet Island */
    BV("Bouvet Island", "BVT"),
    /** Botswana */
    BW("Botswana", "BWA"),
    /** Belarus */
    BY("Belarus", "BLR"),
    /** Belize */
    BZ("Belize", "BLZ"),
    /** Canada */
    CA("Canada", "CAN"),
    /** Cocos Islands */
    CC("Cocos Islands", "CCK"),
    /** The Democratic Republic of the Congo */
    CD("The Democratic Republic of the Congo", "COD"),
    /** Central African Republic */
    CF("Central African Republic", "CAF"),
    /** Congo */
    CG("Congo", "COG"),
    /** Switzerland */
    CH("Switzerland", "CHE"),
    /** Côte d'Ivoire */
    CI("C\u00F4te d'Ivoire", "CIV"),
    /** Cook Islands */
    CK("Cook Islands", "COK"),
    /** Chile */
    CL("Chile", "CHL"),
    /** Cameroon */
    CM("Cameroon", "CMR"),
    /** China */
    CN("China", "CHN"),
    /** Colombia */
    CO("Colombia", "COL"),
    /** Costa Rica */
    CR("Costa Rica", "CRI"),
    /** Cuba */
    CU("Cuba", "CUB"),
    /** Cape Verde */
    CV("Cape Verde", "CPV"),
    /** Curaçao */
    CW("Cura\u00E7ao", "CUW"),
    /** Christmas Island */
    CX("Christmas Island", "CXR"),
    /** Cyprus */
    CY("Cyprus", "CYP"),
    /** Czech Republic */
    CZ("Czech Republic", "CZE"),
    /** Germany */
    DE("Germany", "DEU"),
    /** Djibouti */
    DJ("Djibouti", "DJI"),
    /** Denmark */
    DK("Denmark", "DNK"),
    /** Dominica */
    DM("Dominica", "DMA"),
    /** Dominican Republic */
    DO("Dominican Republic", "DOM"),
    /** Algeria */
    DZ("Algeria", "DZA"),
    /** Ecuador */
    EC("Ecuador", "ECU"),
    /** Estonia */
    EE("Estonia", "EST"),
    /** Egypt */
    EG("Egypt", "EGY"),
    /** Western Sahara */
    EH("Western Sahara", "ESH"),
    /** Eritrea */
    ER("Eritrea", "ERI"),
    /** Spain */
    ES("Spain", "ESP"),
    /** Ethiopia */
    ET("Ethiopia", "ETH"),
    /** Finland */
    FI("Finland", "FIN"),
    /** Fiji */
    FJ("Fiji", "FJI"),
    /** Falkland Islands */
    FK("Falkland Islands", "FLK"),
    /** Federated States of Micronesia */
    FM("Federated States of Micronesia", "FSM"),
    /** Faroe Islands */
    FO("Faroe Islands", "FRO"),
    /** France */
    FR("France", "FRA"),
    /** Gabon */
    GA("Gabon", "GAB"),
    /** United Kingdom */
    GB("United Kingdom", "GBR"),
    /** Grenada */
    GD("Grenada", "GRD"),
    /** Georgia */
    GE("Georgia", "GEO"),
    /** French Guiana */
    GF("French Guiana", "GUF"),
    /** Guernsey */
    GG("Guernsey", "GGY"),
    /** Ghana */
    GH("Ghana", "GHA"),
    /** Gibraltar */
    GI("Gibraltar", "GIB"),
    /** Greenland */
    GL("Greenland", "GRL"),
    /** Gambia */
    GM("Gambia", "GMB"),
    /** Guinea */
    GN("Guinea", "GIN"),
    /** Guadeloupe */
    GP("Guadeloupe", "GLP"),
    /** Equatorial Guinea */
    GQ("Equatorial Guinea", "GNQ"),
    /** Greece */
    GR("Greece", "GRC"),
    /** South Georgia and the South Sandwich Islands */
    GS("South Georgia and the South Sandwich Islands", "SGS"),
    /** Guatemala */
    GT("Guatemala", "GTM"),
    /** Guam */
    GU("Guam", "GUM"),
    /** Guinea-Bissau */
    GW("Guinea-Bissau", "GNB"),
    /** Guyana */
    GY("Guyana", "GUY"),
    /** Hong Kong */
    HK("Hong Kong", "HKG"),
    /** Heard Island and McDonald Islands */
    HM("Heard Island and McDonald Islands", "HMD"),
    /** Honduras */
    HN("Honduras", "HND"),
    /** Croatia */
    HR("Croatia", "HRV"),
    /** Haiti */
    HT("Haiti", "HTI"),
    /** Hungary */
    HU("Hungary", "HUN"),
    /** Indonesia */
    ID("Indonesia", "IDN"),
    /** Ireland */
    IE("Ireland", "IRL"),
    /** Israel */
    IL("Israel", "ISR"),
    /** Isle of Man */
    IM("Isle of Man", "IMN"),
    /** India */
    IN("India", "IND"),
    /** British Indian Ocean Territory */
    IO("British Indian Ocean Territory", "IOT"),
    /** Iraq */
    IQ("Iraq", "IRQ"),
    /** Islamic Republic of Iran */
    IR("Islamic Republic of Iran", "IRN"),
    /** Iceland */
    IS("Iceland", "ISL"),
    /** Italy */
    IT("Italy", "ITA"),
    /** Jersey */
    JE("Jersey", "JEY"),
    /** Jamaica */
    JM("Jamaica", "JAM"),
    /** Jordan */
    JO("Jordan", "JOR"),
    /** Japan */
    JP("Japan", "JPN"),
    /** Kenya */
    KE("Kenya", "KEN"),
    /** Kyrgyzstan */
    KG("Kyrgyzstan", "KGZ"),
    /** Cambodia */
    KH("Cambodia", "KHM"),
    /** Kiribati */
    KI("Kiribati", "KIR"),
    /** Comoros */
    KM("Comoros", "COM"),
    /** Saint Kitts and Nevis */
    KN("Saint Kitts and Nevis", "KNA"),
    /** Democratic People's Republic of Korea */
    KP("Democratic People's Republic of Korea", "PRK"),
    /** Republic of Korea */
    KR("Republic of Korea", "KOR"),
    /** Kuwait */
    KW("Kuwait", "KWT"),
    /** Cayman Islands */
    KY("Cayman Islands", "CYM"),
    /** Kazakhstan */
    KZ("Kazakhstan", "KAZ"),
    /** Lao People's Democratic Republic */
    LA("Lao People's Democratic Republic", "LAO"),
    /** Lebanon */
    LB("Lebanon", "LBN"),
    /** Saint Lucia */
    LC("Saint Lucia", "LCA"),
    /** Liechtenstein */
    LI("Liechtenstein", "LIE"),
    /** Sri Lanka */
    LK("Sri Lanka", "LKA"),
    /** Liberia */
    LR("Liberia", "LBR"),
    /** Lesotho */
    LS("Lesotho", "LSO"),
    /** Lithuania */
    LT("Lithuania", "LTU"),
    /** Luxembourg */
    LU("Luxembourg", "LUX"),
    /** Latvia */
    LV("Latvia", "LVA"),
    /** Libya */
    LY("Libya", "LBY"),
    /** Morocco */
    MA("Morocco", "MAR"),
    /** Monaco */
    MC("Monaco", "MCO"),
    /** Republic of Moldova */
    MD("Republic of Moldova", "MDA"),
    /** Montenegro */
    ME("Montenegro", "MNE"),
    /** Saint Martin */
    MF("Saint Martin", "MAF"),
    /** Madagascar */
    MG("Madagascar", "MDG"),
    /** Marshall Islands */
    MH("Marshall Islands", "MHL"),
    /** The former Yugoslav Republic of Macedonia */
    MK("The former Yugoslav Republic of Macedonia", "MKD"),
    /** Mali */
    ML("Mali", "MLI"),
    /** Myanmar */
    MM("Myanmar", "MMR"),
    /** Mongolia */
    MN("Mongolia", "MNG"),
    /** Macao */
    MO("Macao", "MAC"),
    /** Northern Mariana Islands */
    MP("Northern Mariana Islands", "MNP"),
    /** Martinique */
    MQ("Martinique", "MTQ"),
    /** Mauritania */
    MR("Mauritania", "MRT"),
    /** Montserrat */
    MS("Montserrat", "MSR"),
    /** Malta */
    MT("Malta", "MLT"),
    /** Mauritius */
    MU("Mauritius", "MUS"),
    /** Maldives */
    MV("Maldives", "MDV"),
    /** Malawi */
    MW("Malawi", "MWI"),
    /** Mexico */
    MX("Mexico", "MEX"),
    /** Malaysia */
    MY("Malaysia", "MYS"),
    /** Mozambique */
    MZ("Mozambique", "MOZ"),
    /** Namibia */
    NA("Namibia", "NAM"),
    /** New Caledonia */
    NC("New Caledonia", "NCL"),
    /** Niger */
    NE("Niger", "NER"),
    /** Norfolk Island */
    NF("Norfolk Island", "NFK"),
    /** Nigeria */
    NG("Nigeria", "NGA"),
    /** Nicaragua */
    NI("Nicaragua", "NIC"),
    /** Netherlands */
    NL("Netherlands", "NLD"),
    /** Norway */
    NO("Norway", "NOR"),
    /** Nepal */
    NP("Nepal", "NPL"),
    /** Nauru */
    NR("Nauru", "NRU"),
    /** Niue */
    NU("Niue", "NIU"),
    /** New Zealand */
    NZ("New Zealand", "NZL"),
    /** Oman */
    OM("Oman", "OMN"),
    /** Panama */
    PA("Panama", "PAN"),
    /** Peru */
    PE("Peru", "PER"),
    /** French Polynesia */
    PF("French Polynesia", "PYF"),
    /** Papua New Guinea */
    PG("Papua New Guinea", "PNG"),
    /** Philippines */
    PH("Philippines", "PHL"),
    /** Pakistan */
    PK("Pakistan", "PAK"),
    /** Poland */
    PL("Poland", "POL"),
    /** Saint Pierre and Miquelon */
    PM("Saint Pierre and Miquelon", "SPM"),
    /** Pitcairn */
    PN("Pitcairn", "PCN"),
    /** Puerto Rico */
    PR("Puerto Rico", "PRI"),
    /** Occupied Palestinian Territory */
    PS("Occupied Palestinian Territory", "PSE"),
    /** Portugal */
    PT("Portugal", "PRT"),
    /** Palau */
    PW("Palau", "PLW"),
    /** Paraguay */
    PY("Paraguay", "PRY"),
    /** Qatar */
    QA("Qatar", "QAT"),
    /** Réunion */
    RE("R\u00E9union", "REU"),
    /** Romania */
    RO("Romania", "ROU"),
    /** Serbia */
    RS("Serbia", "SRB"),
    /** Russian Federation */
    RU("Russian Federation", "RUS"),
    /** Rwanda */
    RW("Rwanda", "RWA"),
    /** Saudi Arabia */
    SA("Saudi Arabia", "SAU"),
    /** Solomon Islands */
    SB("Solomon Islands", "SLB"),
    /** Seychelles */
    SC("Seychelles", "SYC"),
    /** Sudan */
    SD("Sudan", "SDN"),
    /** Sweden */
    SE("Sweden", "SWE"),
    /** Singapore */
    SG("Singapore", "SGP"),
    /** Saint Helena, Ascension and Tristan da Cunha */
    SH("Saint Helena, Ascension and Tristan da Cunha", "SHN"),
    /** Slovenia */
    SI("Slovenia", "SVN"),
    /** Svalbard and Jan Mayen */
    SJ("Svalbard and Jan Mayen", "SJM"),
    /** Slovakia */
    SK("Slovakia", "SVK"),
    /** Sierra Leone */
    SL("Sierra Leone", "SLE"),
    /** San Marino */
    SM("San Marino", "SMR"),
    /** Senegal */
    SN("Senegal", "SEN"),
    /** Somalia */
    SO("Somalia", "SOM"),
    /** Suriname */
    SR("Suriname", "SUR"),
    /** South Sudan */
    SS("South Sudan", "SSD"),
    /** Sao Tome and Principe */
    ST("Sao Tome and Principe", "STP"),
    /** El Salvador */
    SV("El Salvador", "SLV"),
    /** Sint Maarten */
    SX("Sint Maarten", "SXM"),
    /** Syrian Arab Republic */
    SY("Syrian Arab Republic", "SYR"),
    /** Swaziland */
    SZ("Swaziland", "SWZ"),
    /** Turks and Caicos Islands */
    TC("Turks and Caicos Islands", "TCA"),
    /** Chad */
    TD("Chad", "TCD"),
    /** French Southern Territories */
    TF("French Southern Territories", "ATF"),
    /** Togo */
    TG("Togo", "TGO"),
    /** Thailand */
    TH("Thailand", "THA"),
    /** Tajikistan */
    TJ("Tajikistan", "TJK"),
    /** Tokelau */
    TK("Tokelau", "TKL"),
    /** Timor-Leste */
    TL("Timor-Leste", "TLS"),
    /** Turkmenistan */
    TM("Turkmenistan", "TKM"),
    /** Tunisia */
    TN("Tunisia", "TUN"),
    /** Tonga */
    TO("Tonga", "TON"),
    /** Turkey */
    TR("Turkey", "TUR"),
    /** Trinidad and Tobago */
    TT("Trinidad and Tobago", "TTO"),
    /** Tuvalu */
    TV("Tuvalu", "TUV"),
    /** Taiwan, Province of China */
    TW("Taiwan, Province of China", "TWN"),
    /** United Republic of Tanzania */
    TZ("United Republic of Tanzania", "TZA"),
    /** Ukraine */
    UA("Ukraine", "UKR"),
    /** Uganda */
    UG("Uganda", "UGA"),
    /** United States Minor Outlying Islands */
    UM("United States Minor Outlying Islands", "UMI"),
    /** United States */
    US("United States", "USA"),
    /** Uruguay */
    UY("Uruguay", "URY"),
    /** Uzbekistan */
    UZ("Uzbekistan", "UZB"),
    /** Holy See */
    VA("Holy See", "VAT"),
    /** Saint Vincent and the Grenadines */
    VC("Saint Vincent and the Grenadines", "VCT"),
    /** Bolivarian Republic of Venezuela */
    VE("Bolivarian Republic of Venezuela", "VEN"),
    /** British Virgin Islands */
    VG("British Virgin Islands", "VGB"),
    /** Virgin Islands, U.S. */
    VI("Virgin Islands, U.S.", "VIR"),
    /** Viet Nam */
    VN("Viet Nam", "VNM"),
    /** Vanuatu */
    VU("Vanuatu", "VUT"),
    /** Wallis and Futuna */
    WF("Wallis and Futuna", "WLF"),
    /** Samoa */
    WS("Samoa", "WSM"),
    /** Kosovo (User-assigned code element) */
    XK("Kosovo", "UNK"), // Note: XK is a user-assigned code, UNK is the assigned alpha-3
    /** Yemen */
    YE("Yemen", "YEM"),
    /** Mayotte */
    YT("Mayotte", "MYT"),
    /** South Africa */
    ZA("South Africa", "ZAF"),
    /** Zambia */
    ZM("Zambia", "ZMB"),
    /** Zimbabwe */
    ZW("Zimbabwe", "ZWE");

    /**
     * Map that stores CountryCode instances by their <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-1 alpha-3</a> codes.
     */
    private static final Map<String, CountryCode> alpha3Map = new HashMap<String, CountryCode>();

    static {
        for (final CountryCode cc : values()) {
            alpha3Map.put(cc.getAlpha3(), cc);
        }
    }

    /**
     * The full name of the country.
     */
    private final String name;

    /**
     * The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-1 alpha-3</a> code for the country.
     */
    private final String alpha3;

    /**
     * Constructs a CountryCode enum constant with the specified country name and alpha-3 code.
     *
     * @param name The full name of the country.
     * @param alpha3 The ISO 3166-1 alpha-3 code for the country.
     */
    CountryCode(final String name, final String alpha3) {
        this.name = name;
        this.alpha3 = alpha3;
    }

    /**
     * Retrieves a {@code CountryCode} instance that corresponds to the given
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a>
     * or <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
     * The lookup is case-insensitive.
     *
     * @param code The ISO 3166-1 alpha-2 or alpha-3 code (e.g., "DE", "USA", "JPN").
     * @return A {@code CountryCode} instance, or {@code null} if no matching country code is found
     * or the input code is {@code null} or has an invalid length.
     */
    public static CountryCode getByCode(final String code) {
        if (code == null) {
            return null;
        }

        switch (code.length()) {
            case 2:
                return getByAlpha2Code(code.toUpperCase());

            case 3:
                return getByAlpha3Code(code.toUpperCase());

            default:
                return null;
        }
    }

    /**
     * Retrieves a {@code CountryCode} instance that corresponds to the given
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code.
     * The lookup is case-insensitive.
     *
     * @param code The ISO 3166-1 alpha-2 code (e.g., "DE", "US").
     * @return A {@code CountryCode} instance, or {@code null} if no matching country code is found.
     */
    private static CountryCode getByAlpha2Code(final String code) {
        try {
            return Enum.valueOf(CountryCode.class, code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Retrieves a {@code CountryCode} instance that corresponds to the given
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-1 alpha-3</a> code.
     * The lookup is case-insensitive.
     *
     * @param code The ISO 3166-1 alpha-3 code (e.g., "DEU", "USA").
     * @return A {@code CountryCode} instance, or {@code null} if no matching country code is found.
     */
    private static CountryCode getByAlpha3Code(final String code) {
        return alpha3Map.get(code);
    }

    /**
     * Returns the full name of the country.
     *
     * @return The country's full name (e.g., "Germany", "United States").
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code for the country.
     * This is the two-letter code that represents the enum constant itself (e.g., "DE", "US").
     *
     * @return The two-letter ISO 3166-1 alpha-2 code.
     */
    public String getAlpha2() {
        return name(); // Enum.name() returns the constant's name, which is the alpha-2 code.
    }

    /**
     * Returns the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-1 alpha-3</a> code for the country.
     * This is the three-letter code associated with the country.
     *
     * @return The three-letter ISO 3166-1 alpha-3 code.
     */
    public String getAlpha3() {
        return alpha3;
    }
}
