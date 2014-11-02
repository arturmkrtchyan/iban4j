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


/**
 * Business Identifier Codes (also known as SWIFT-BIC, BIC code, SWIFT ID or SWIFT code).
 *
 * <a href="http://en.wikipedia.org/wiki/ISO_9362">ISO_9362</a>.
 */
public final class Bic {

    private final String value;

    private Bic(final String value) {
        this.value = value;
    }

    /**
     * Returns a Bic object holding the value of the specified String.
     *
     * @param bic the String to be parsed.
     * @return a Bic object holding the value represented by the string argument.
     * @throws BicFormatException if the String doesn't contain parsable Bic.
     */
    public static Bic valueOf(final String bic) throws BicFormatException {
        BicUtil.validate(bic);
        return new Bic(bic);
    }

    /**
     * Returns the bank code from the Bic.
     *
     * @return string representation of Bic's institution code.
     */
    public String getBankCode() {
        return BicUtil.getBankCode(value);
    }

    /**
     * Returns the country code from the Bic.
     *
     * @return CountryCode representation of Bic's country code.
     */
    public CountryCode getCountryCode() {
        return CountryCode.getByCode(BicUtil.getCountryCode(value));
    }

    /**
     * Returns the location code from the Bic.
     *
     * @return string representation of Bic's location code.
     */
    public String getLocationCode() {
        return BicUtil.getLocationCode(value);
    }

    /**
     * Returns the branch code from the Bic.
     *
     * @return string representation of Bic's branch code, null if Bic has no branch code.
     */
    public String getBranchCode() {
        if(BicUtil.hasBranchCode(value)) {
            return BicUtil.getBranchCode(value);
        }
        return null;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Bic) {
            return value.equals(((Bic)obj).value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
