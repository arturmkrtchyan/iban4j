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
 * Country Code Enum
 * <p>
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1">ISO 3166-1</a> country code.
 */
public enum CountryCode {

    AD("Andorra", "AND", ""),
    AE("United Arab Emirates", "ARE", ""),
    AF("Afghanistan", "AFG", ""),
    AG("Antigua and Barbuda", "ATG", ""),
    AI("Anguilla", "AIA", ""),
    AL("Albania", "ALB", ""),
    AM("Armenia", "ARM", ""),
    AO("Angola", "AGO", ""),
    AQ("Antarctica", "ATA", ""),
    AR("Argentina", "ARG", ""),
    AS("American Samoa", "ASM", ""),
    AT("Austria", "AUT", "SEPA"),
    AU("Australia", "AUS", ""),
    AW("Aruba", "ABW", ""),
    AX("\u212Bland Islands", "ALA", ""),
    AZ("Azerbaijan", "AZE", ""),
    BA("Bosnia and Herzegovina", "BIH", ""),
    BB("Barbados", "BRB", ""),
    BD("Bangladesh", "BGD", ""),
    BE("Belgium", "BEL", "SEPA"),
    BF("Burkina Faso", "BFA", ""),
    BG("Bulgaria", "BGR", "SEPA"),
    BH("Bahrain", "BHR", ""),
    BI("Burundi", "BDI", ""),
    BJ("Benin", "BEN", ""),
    BL("Saint Barth\u00E9lemy", "BLM", ""),
    BM("Bermuda", "BMU", ""),
    BN("Brunei Darussalam", "BRN", ""),
    BO("Plurinational State of Bolivia", "BOL", ""),
    BQ("Bonaire, Sint Eustatius and Saba", "BES", ""),
    BR("Brazil", "BRA", ""),
    BS("Bahamas", "BHS", ""),
    BT("Bhutan", "BTN", ""),
    BV("Bouvet Island", "BVT", ""),
    BW("Botswana", "BWA", ""),
    BY("Belarus", "BLR", ""),
    BZ("Belize", "BLZ", ""),
    CA("Canada", "CAN", ""),
    CC("Cocos Islands", "CCK", ""),
    CD("The Democratic Republic of the Congo", "COD", ""),
    CF("Central African Republic", "CAF", ""),
    CG("Congo", "COG", ""),
    CH("Switzerland", "CHE", "SEPA"),
    CI("C\u00F4te d'Ivoire", "CIV", ""),
    CK("Cook Islands", "COK", ""),
    CL("Chile", "CHL", ""),
    CM("Cameroon", "CMR", ""),
    CN("China", "CHN", ""),
    CO("Colombia", "COL", ""),
    CR("Costa Rica", "CRI", ""),
    CU("Cuba", "CUB", ""),
    CV("Cape Verde", "CPV", ""),
    CW("Cura\u00E7ao", "CUW", ""),
    CX("Christmas Island", "CXR", ""),
    CY("Cyprus", "CYP", "SEPA"),
    CZ("Czech Republic", "CZE", "SEPA"),
    DE("Germany", "DEU", "SEPA"),
    DJ("Djibouti", "DJI", ""),
    DK("Denmark", "DNK", "SEPA"),
    DM("Dominica", "DMA", ""),
    DO("Dominican Republic", "DOM", ""),
    DZ("Algeria", "DZA", ""),
    EC("Ecuador", "ECU", ""),
    EE("Estonia", "EST", "SEPA"),
    EG("Egypt", "EGY", ""),
    EH("Western Sahara", "ESH", ""),
    ER("Eritrea", "ERI", ""),
    ES("Spain", "ESP", "SEPA"),
    ET("Ethiopia", "ETH", ""),
    FI("Finland", "FIN", "SEPA"),
    FJ("Fiji", "FJI", ""),
    FK("Falkland Islands", "FLK", ""),
    FM("Federated States of Micronesia", "FSM", ""),
    FO("Faroe Islands", "FRO", ""),
    FR("France", "FRA", "SEPA"),
    GA("Gabon", "GAB", ""),
    GB("United Kingdom", "GBR", "SEPA"),
    GD("Grenada", "GRD", ""),
    GE("Georgia", "GEO", ""),
    GF("French Guiana", "GUF", ""),
    GG("Guemsey", "GGY", ""),
    GH("Ghana", "GHA", ""),
    GI("Gibraltar", "GIB", "SEPA"),
    GL("Greenland", "GRL", ""),
    GM("Gambia", "GMB", ""),
    GN("Guinea", "GIN", ""),
    GP("Guadeloupe", "GLP", ""),
    GQ("Equatorial Guinea", "GNQ", ""),
    GR("Greece", "GRC", "SEPA"),
    GS("South Georgia and the South Sandwich Islands", "SGS", ""),
    GT("Guatemala", "GTM", ""),
    GU("Guam", "GUM", ""),
    GW("Guinea-Bissau", "GNB", ""),
    GY("Guyana", "GUY", ""),
    HK("Hong Kong", "HKG", ""),
    HM("Heard Island and McDonald Islands", "HMD", ""),
    HN("Honduras", "HND", ""),
    HR("Croatia", "HRV", "SEPA"),
    HT("Haiti", "HTI", ""),
    HU("Hungary", "HUN", "SEPA"),
    ID("Indonesia", "IDN", ""),
    IE("Ireland", "IRL", "SEPA"),
    IL("Israel", "ISR", ""),
    IM("Isle of Man", "IMN", ""),
    IN("India", "IND", ""),
    IO("British Indian Ocean Territory", "IOT", ""),
    IQ("Iraq", "IRQ", ""),
    IR("Islamic Republic of Iran", "IRN", ""),
    IS("Iceland", "ISL", "SEPA"),
    IT("Italy", "ITA", "SEPA"),
    JE("Jersey", "JEY", ""),
    JM("Jamaica", "JAM", ""),
    JO("Jordan", "JOR", ""),
    JP("Japan", "JPN", ""),
    KE("Kenya", "KEN", ""),
    KG("Kyrgyzstan", "KGZ", ""),
    KH("Cambodia", "KHM", ""),
    KI("Kiribati", "KIR", ""),
    KM("Comoros", "COM", ""),
    KN("Saint Kitts and Nevis", "KNA", ""),
    KP("Democratic People's Republic of Korea", "PRK", ""),
    KR("Republic of Korea", "KOR", ""),
    KW("Kuwait", "KWT", ""),
    KY("Cayman Islands", "CYM", ""),
    KZ("Kazakhstan", "KAZ", ""),
    LA("Lao People's Democratic Republic", "LAO", ""),
    LB("Lebanon", "LBN", ""),
    LC("Saint Lucia", "LCA", ""),
    LI("Liechtenstein", "LIE", "SEPA"),
    LK("Sri Lanka", "LKA", ""),
    LR("Liberia", "LBR", ""),
    LS("Lesotho", "LSO", ""),
    LT("Lithuania", "LTU", "SEPA"),
    LU("Luxembourg", "LUX", "SEPA"),
    LV("Latvia", "LVA", "SEPA"),
    LY("Libya", "LBY", ""),
    MA("Morocco", "MAR", ""),
    MC("Monaco", "MCO", "SEPA"),
    MD("Republic of Moldova", "MDA", ""),
    ME("Montenegro", "MNE", ""),
    MF("Saint Martin", "MAF", ""),
    MG("Madagascar", "MDG", ""),
    MH("Marshall Islands", "MHL", ""),
    MK("The former Yugoslav Republic of Macedonia", "MKD", ""),
    ML("Mali", "MLI", ""),
    MM("Myanmar", "MMR", ""),
    MN("Mongolia", "MNG", ""),
    MO("Macao", "MAC", ""),
    MP("Northern Mariana Islands", "MNP", ""),
    MQ("Martinique", "MTQ", ""),
    MR("Mauritania", "MRT", ""),
    MS("Montserrat", "MSR", ""),
    MT("Malta", "MLT", "SEPA"),
    MU("Mauritius", "MUS", ""),
    MV("Maldives", "MDV", ""),
    MW("Malawi", "MWI", ""),
    MX("Mexico", "MEX", ""),
    MY("Malaysia", "MYS", ""),
    MZ("Mozambique", "MOZ", ""),
    NA("Namibia", "NAM", ""),
    NC("New Caledonia", "NCL", ""),
    NE("Niger", "NER", ""),
    NF("Norfolk Island", "NFK", ""),
    NG("Nigeria", "NGA", ""),
    NI("Nicaragua", "NIC", ""),
    NL("Netherlands", "NLD", "SEPA"),
    NO("Norway", "NOR", "SEPA"),
    NP("Nepal", "NPL", ""),
    NR("Nauru", "NRU", ""),
    NU("Niue", "NIU", ""),
    NZ("New Zealand", "NZL", ""),
    OM("Oman", "OMN", ""),
    PA("Panama", "PAN", ""),
    PE("Peru", "PER", ""),
    PF("French Polynesia", "PYF", ""),
    PG("Papua New Guinea", "PNG", ""),
    PH("Philippines", "PHL", ""),
    PK("Pakistan", "PAK", ""),
    PL("Poland", "POL", "SEPA"),
    PM("Saint Pierre and Miquelon", "SPM", ""),
    PN("Pitcairn", "PCN", ""),
    PR("Puerto Rico", "PRI", ""),
    PS("Occupied Palestinian Territory", "PSE", ""),
    PT("Portugal", "PRT", "SEPA"),
    PW("Palau", "PLW", ""),
    PY("Paraguay", "PRY", ""),
    QA("Qatar", "QAT", ""),
    RE("R\u00E9union", "REU", ""),
    RO("Romania", "ROU", "SEPA"),
    RS("Serbia", "SRB", ""),
    RU("Russian Federation", "RUS", ""),
    RW("Rwanda", "RWA", ""),
    SA("Saudi Arabia", "SAU", ""),
    SB("Solomon Islands", "SLB", ""),
    SC("Seychelles", "SYC", ""),
    SD("Sudan", "SDN", ""),
    SE("Sweden", "SWE", "SEPA"),
    SG("Singapore", "SGP", ""),
    SH("Saint Helena, Ascension and Tristan da Cunha", "SHN", ""),
    SI("Slovenia", "SVN", "SEPA"),
    SJ("Svalbard and Jan Mayen", "SJM", ""),
    SK("Slovakia", "SVK", "SEPA"),
    SL("Sierra Leone", "SLE", ""),
    SM("San Marino", "SMR", "SEPA"),
    SN("Senegal", "SEN", ""),
    SO("Somalia", "SOM", ""),
    SR("Suriname", "SUR", ""),
    SS("South Sudan", "SSD", ""),
    ST("Sao Tome and Principe", "STP", ""),
    SV("El Salvador", "SLV", ""),
    SX("Sint Maarten", "SXM", ""),
    SY("Syrian Arab Republic", "SYR", ""),
    SZ("Swaziland", "SWZ", ""),
    TC("Turks and Caicos Islands", "TCA", ""),
    TD("Chad", "TCD", ""),
    TF("French Southern Territories", "ATF", ""),
    TG("Togo", "TGO", ""),
    TH("Thailand", "THA", ""),
    TJ("Tajikistan", "TJK", ""),
    TK("Tokelau", "TKL", ""),
    TL("Timor-Leste", "TLS", ""),
    TM("Turkmenistan", "TKM", ""),
    TN("Tunisia", "TUN", ""),
    TO("Tonga", "TON", ""),
    TR("Turkey", "TUR", ""),
    TT("Trinidad and Tobago", "TTO", ""),
    TV("Tuvalu", "TUV", ""),
    TW("Taiwan, Province of China", "TWN", ""),
    TZ("United Republic of Tanzania", "TZA", ""),
    UA("Ukraine", "UKR", ""),
    UG("Uganda", "UGA", ""),
    UM("United States Minor Outlying Islands", "UMI", ""),
    US("United States", "USA", ""),
    UY("Uruguay", "URY", ""),
    UZ("Uzbekistan", "UZB", ""),
    VA("Holy See", "VAT", ""),
    VC("Saint Vincent and the Grenadines", "VCT", ""),
    VE("Bolivarian Republic of Venezuela", "VEN", ""),
    VG("British Virgin Islands", "VGB", ""),
    VI("Virgin Islands, U.S.", "VIR", ""),
    VN("Viet Nam", "VNM", ""),
    VU("Vanuatu", "VUT", ""),
    WF("Wallis and Futuna", "WLF", ""),
    WS("Samoa", "WSM", ""),
    XK("Kosovo", "UNK", ""),
    YE("Yemen", "YEM", ""),
    YT("Mayotte", "MYT", ""),
    ZA("South Africa", "ZAF", ""),
    ZM("Zambia", "ZMB", ""),
    ZW("Zimbabwe", "ZWE");


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
     * Country name
     */
    private final String name;

    /**
     * Country alpha3 code
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">ISO 3166-3 alpha-3</a> code.
     */
    private final String alpha3;

    /**
     * SEPA identifier
     */
    private final String sepaIdentifier;

    CountryCode(final String name, final String alpha3) {
        this.name = name;
        this.alpha3 = alpha3;
        this.sepaIdentifier = "";
    }

    CountryCode(final String name, final String alpha3, final String sepaIdentifier) {
        this.name = name;
        this.alpha3 = alpha3;
        this.sepaIdentifier = sepaIdentifier;
    }


    /**
     * Get a CountryCode that corresponds to the given ISO 3166-1
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> or
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
     *
     * @param code An ISO 3166-1 <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> or
     *             <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
     * @return A CountryCode instance, or null if not found.
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
     * Get a CountryCode that corresponds to the given ISO 3166-1
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> code.
     *
     * @param code An ISO 3166-1 <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> code.
     * @return A CountryCode instance, or null if not found.
     */
    private static CountryCode getByAlpha2Code(final String code) {
        try {
            return Enum.valueOf(CountryCode.class, code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Get a CountryCode that corresponds to the given ISO 3166-1
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
     *
     * @param code An ISO 3166-1 <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
     * @return A CountryCode instance, or null if not found.
     */
    private static CountryCode getByAlpha3Code(final String code) {
        return alpha3Map.get(code);
    }

    /**
     * Get the country name.
     *
     * @return The country name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> code.
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
     * check SEPA membership
     *
     * @return true if countycode represents a SEPA member country
     */
    public boolean isSepaCountry() {
        return sepaIdentifier.equals("SEPA");
    }

}