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


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Business Identifier Codes (also known as SWIFT-BIC, BIC code, SWIFT ID or SWIFT code).
 *
 * <a href="http://en.wikipedia.org/wiki/ISO_9362">ISO_9362</a>.
 *
 * The european banking system is of the opinion that BIC codes have to pass the following regex:
 *
 * <code>[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}</code>
 *
 * which can be rewritten as
 *
 * <code>^([A-Z]{4})([A-Z]{2})([A-Z2-9][A-NP-Z0-9])([A-Z0-9]{3})?$</code>
 *
 * Which matches the following structure:
 *
 * <ul>
 * <li>4 characters A-Z: Institution code op Bank code</li>
 * <li>2 characters A-Z: ISO 3166-1 alpha-2 country code</li>
 * <li>2 characters A-Z,0-9 with certain restrictions: Location Code</li>
 * <li>3 characters A-Z,0-9, possibly missing: Branch code ('XXX' for primary office)</li>
 * </ul>
 *
 */

public final class Bic {

    private final String value;

    private final static String bicPatternAsString = "([A-Z]{4})([A-Z]{2})([A-Z2-9][A-NP-Z0-9])([A-Z0-9]{3})?";
    private final static Pattern bicPattern = Pattern.compile(bicPatternAsString);

    /**
     * Returns a BIC object holding the value of the specified String.
     *
     * @param bic the String to be parsed.
     * @throws BicFormatException if the String doesn't contain parsable Bic.
     *         UnsupportedCountryException if bic's country is not supported.
     */

    public Bic(String bic) {
        BicUtil.validate(bic);
        this.value = bic;
    }

    /**
     * Returns a BIC object holding the value of the specified String.
     *
     * @deprecated Just use the constructor instead.
     * @param bic the String to be parsed.
     * @return a Bic object holding the value represented by the string argument.
     * @throws BicFormatException if the String doesn't contain parsable Bic.
     *         UnsupportedCountryException if bic's country is not supported.
     */
    @Deprecated
    public static Bic valueOf(final String bic) throws BicFormatException,
            UnsupportedCountryException {
        return new Bic(bic);
    }

	/**
	 * Check against regex
	 */

	public static boolean passesRegex(String bic) {
		if (bic == null) {
			throw new IllegalArgumentException("The passed 'bic' is (null)");
		}
		Matcher m = bicPattern.matcher(bic);
		return m.matches();
	}

	/**
     * Returns the institution code from the BIC.
     *
     * @deprecated Use getInstitutionCode() instead
     * @return string representation of Bic's institution code.
     */
    @Deprecated
    public String getBankCode() {
        return BicUtil.getBankCode(value);
    }

    /**
     * Returns the institution code (formerly: bank code) from the BIC.
     *
     * @return string representation of BIC's institution code.
     */
    public String getInstitutionCode() {
        return BicUtil.getBankCode(value);
    }

    /**
     * Returns the country code from the BIC.
     *
     * @return CountryCode representation of BIC's country code.
     */
    public CountryCode getCountryCode() {
        return CountryCode.getByCode(BicUtil.getCountryCode(value));
    }

    /**
     * Returns the location code from the BIC.
     *
     * @return string representation of Bic's location code.
     */
    public String getLocationCode() {
        return BicUtil.getLocationCode(value);
    }

    /**
     * Returns the branch code from the BIC.
     *
     * (This function may return null instead of the empty string to indicate missing branch code; this is not nice)
     *
     * @return string representation of Bic's branch code, null if Bic has no branch code.
     */
    public String getBranchCode() {
        if(BicUtil.hasBranchCode(value)) {
            return BicUtil.getBranchCode(value);
        }
        else {
            return null;
        }
    }

    /**
     * Equality based on underlying BIC string
     * @param obj Any other object, possibly null
     * @return true if the underlying BIC strings are equal (no special case handling for "XXX" and missing branch code)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Bic)) {
            return false;
        }
        return value.equals(((Bic)obj).value);
    }

    /**
     * Hash the underlying BIC code in its String form
     * @return integer has code based on BIC code
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Return the BIC code as a String
     * @return bic code as a String
     */
    @Override
    public String toString() {
        return value;
    }
}
