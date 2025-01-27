/*
 * Copyright 2016 Artur Mkrtchyan
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

import static org.iban4j.CreditorIdentifierFormatException.CreditorIdentifierViolation.*;

import java.util.Random;

/**
 * Creditor Identifier.
 *
 * A Creditor Identifier is a unique reference which identifies each SEPA Direct Debit originator.
 *
 * <a href="http://www.europeanpaymentscouncil.eu/index.cfm/knowledge-bank/epc-documents/creditor-identifier-overview/epc262-08-creditor-identifier-overview-v40/">Creditor Identifier</a>.
 */
public class CreditorIdentifier {

    static final String DEFAULT_CHECK_DIGIT = "00";
    static final String DEFAULT_BUSINESS_CODE = "ZZZ";

    // Cache string value of the CreditorIdentifier
    private final String value;

    /**
     * Creates CreditorIdentifier instance.
     *
     * @param value String
     */
    private CreditorIdentifier(final String value) {
        this.value = value;
    }

    /**
     * Returns CreditorIdentifier's country code.
     *
     * @return countryCode CountryCode
     */
    public CountryCode getCountryCode() {
        return CountryCode.getByCode(CreditorIdentifierUtil.getCountryCode(value));
    }

    /**
     * Returns CreditorIdentifier's check digit.
     *
     * @return checkDigit String
     */
    public String getCheckDigit() {
        return CreditorIdentifierUtil.getCheckDigit(value);
    }

    /**
     * Returns CreditorIdentifier's creditor business code.
     *
     * @return creditorBusinessCode String
     */
    public String getCreditorBusinessCode() {
        return CreditorIdentifierUtil.getCreditorBusinessCode(value);
    }

    /**
     * Returns CreditorIdentifier's national identifier.
     *
     * @return nationalIdentifier String
     */
    public String getNationalIdentifier() {
        return CreditorIdentifierUtil.getNationalIdentifier(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof CreditorIdentifier) {
            return value.equals(((CreditorIdentifier)obj).value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    /**
     * CreditorIdentifier Builder Class
     */
    public final static class Builder {

        private CountryCode countryCode;
        private String creditorBusinessCode;
        private String nationalIdentifier;

        private final Random random = new Random();

        /**
         * Creates an CreditorIdentifier Builder instance.
         */
        public Builder() {
        }

        /**
         * Sets CreditorIdentifier's country code.
         *
         * @param countryCode CountryCode
         * @return builder Builder
         */
        public Builder countryCode(final CountryCode countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        /**
         * Sets CreditorIdentifier's creditor business code.
         *
         * @param creditorBusinessCode String
         * @return builder Builder
         */
        public Builder creditorBusinessCode(final String creditorBusinessCode) {
            this.creditorBusinessCode = creditorBusinessCode;
            return this;
        }

        /**
         * Sets CreditorIdentifier's national identifier.
         *
         * @param nationalIdentifier String
         * @return builder Builder
         */
        public Builder nationalIdentifier(final String nationalIdentifier) {
            this.nationalIdentifier = nationalIdentifier;
            return this;
        }

        /**
         * Builds new CreditorIdentifier instance. This methods validates the generated CreditorIdentifier.
         *
         * @return new CreditorIdentifier instance.
         * @exception CreditorIdentifierFormatException if values are not parsable by CreditorIdentifier Specification
         *  <a href="http://www.europeanpaymentscouncil.eu/index.cfm/knowledge-bank/epc-documents/creditor-identifier-overview/epc262-08-creditor-identifier-overview-v40/">Creditor Identifier</a>.
         * @exception UnsupportedCountryException if country is not supported
         */
        public CreditorIdentifier build() throws CreditorIdentifierFormatException,
                IllegalArgumentException, UnsupportedCountryException {
            return build(true);
        }

        /**
         * Builds new CreditorIdentifier instance.
         *
         * @param validate boolean indicates if the generated CreditorIdentifier needs to be
         *  validated after generation
         * @return new CreditorIdentifier instance.
         * @exception CreditorIdentifierFormatException if values are not parsable by CreditorIdentifier Specification
         *  <a href="http://www.europeanpaymentscouncil.eu/index.cfm/knowledge-bank/epc-documents/creditor-identifier-overview/epc262-08-creditor-identifier-overview-v40/">Creditor Identifier</a>.
         * @exception UnsupportedCountryException if country is not supported
         */
        public CreditorIdentifier build(boolean validate) throws CreditorIdentifierFormatException,
                IllegalArgumentException, UnsupportedCountryException {

            // null checks
            require(countryCode, creditorBusinessCode, nationalIdentifier);

            // creditorIdentifier is formatted with default check digit.
            final String formattedCreditorIdentifier = formatCreditorIdentifier();

            final String checkDigit = CreditorIdentifierUtil.calculateCheckDigit(formattedCreditorIdentifier);

            // replace default check digit with calculated check digit
            final String creditorIdentifierValue = CreditorIdentifierUtil.replaceCheckDigit(formattedCreditorIdentifier, checkDigit);

            if (validate) {
                CreditorIdentifierUtil.validate(creditorIdentifierValue);
            }
            return new CreditorIdentifier(creditorIdentifierValue);
        }

        /**
         * Returns formatted CreditorIdentifier string with default check digit.
         */
        private String formatCreditorIdentifier() {
            final StringBuilder sb = new StringBuilder();
            sb.append(countryCode.getAlpha2());
            sb.append(DEFAULT_CHECK_DIGIT);
            sb.append(creditorBusinessCode);
            sb.append(nationalIdentifier);
            return sb.toString();
        }

        private void require(final CountryCode countryCode,
                             final String bankCode,
                             final String accountNumber)
                throws CreditorIdentifierFormatException {
            if(countryCode == null) {
                throw new CreditorIdentifierFormatException(COUNTRY_CODE_NOT_NULL,
                        "countryCode is required; it cannot be null");
            }

            if(bankCode == null) {
                throw new CreditorIdentifierFormatException(BANK_CODE_NOT_NULL,
                        "bankCode is required; it cannot be null");
            }

            if(accountNumber == null) {
                throw new CreditorIdentifierFormatException(ACCOUNT_NUMBER_NOT_NULL,
                        "accountNumber is required; it cannot be null");
            }
        }
    }

}
