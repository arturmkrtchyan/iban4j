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

import org.iban4j.bban.BbanStructure;
import org.iban4j.bban.BbanStructureEntry;
import org.iban4j.support.Assert;

import java.io.Serializable;

/**
 * International Bank Account Number
 *
 * <a href="http://en.wikipedia.org/wiki/ISO_13616">ISO_13616</a>.
 */
public final class Iban implements Serializable {

    private static final long serialVersionUID = 3507561504372065317L;

    protected static final String DEFAULT_CHECK_DIGIT = "00";

    // Cache string value of the iban
    private final String value;

    /**
     * Creates iban with default check digit.
     *
     * @param value String
     */
    private Iban(final String value) {
        this.value = value;
    }

    public CountryCode getCountryCode() {
        return CountryCode.getByCode(IbanUtil.getCountryCode(value));
    }

    public String getCheckDigit() {
        return IbanUtil.getCheckDigit(value);
    }

    public String getAccountNumber() {
        return IbanUtil.getAccountNumber(value);
    }

    public String getBankCode() {
        return IbanUtil.getBankCode(value);
    }

    public String getBranchCode() {
        return IbanUtil.getBranchCode(value);
    }

    public String getNationalCheckDigit() {
        return IbanUtil.getNationalCheckDigit(value);
    }

    public String getAccountType() {
        return IbanUtil.getAccountType(value);
    }

    public String getOwnerAccountType() {
        return IbanUtil.getOwnerAccountType(value);
    }

    public String getIdentificationNumber() {
        return IbanUtil.getIdentificationNumber(value);
    }

    public String getBban() {
        return IbanUtil.getBban(value);
    }

    /**
     * Returns an Iban object holding the value of the specified String.
     *
     * @param iban the String to be parsed.
     * @return an Iban object holding the value represented by the string argument.
     * @throws IbanFormatException if the String doesn't contain parsable Iban
     *         InvalidCheckDigitException if Iban has invalid check digit
     *         UnsupportedCountryException if Iban's Country is not supported.
     *
     */
    public static Iban valueOf(final String iban) throws IbanFormatException,
            InvalidCheckDigitException, UnsupportedCountryException {
        IbanUtil.validate(iban);
        return new Iban(iban);
    }

    @Override
    public String toString() {
        return value;
    }

    private String toFormattedString() {
        // TODO for next release
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Iban) {
            return value.equals(((Iban)obj).value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Iban Builder Class
     */
    public final static class Builder {

        private CountryCode countryCode;
        private String bankCode;
        private String branchCode;
        private String nationalCheckDigit;
        private String accountType;
        private String accountNumber;
        private String ownerAccountType;
        private String identificationNumber;

        public Builder() {
        }

        public Builder countryCode(final CountryCode countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder bankCode(final String bankCode) {
            this.bankCode = bankCode;
            return this;
        }

        public Builder branchCode(final String branchCode) {
            this.branchCode = branchCode;
            return this;
        }

        public Builder accountNumber(final String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder nationalCheckDigit(final String nationalCheckDigit) {
            this.nationalCheckDigit = nationalCheckDigit;
            return this;
        }

        public Builder accountType(final String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder ownerAccountType(final String ownerAccountType) {
            this.ownerAccountType = ownerAccountType;
            return this;
        }

        public Builder identificationNumber(final String identificationNumber) {
            this.identificationNumber = identificationNumber;
            return this;
        }

        private String formatBban() {
            StringBuilder sb = new StringBuilder();
            BbanStructure structure = BbanStructure.forCountry(countryCode);
            for(BbanStructureEntry entry : structure.getEntries()) {
                switch (entry.getEntryType()) {
                    case b:
                        sb.append(bankCode);
                        break;
                    case s:
                        sb.append(branchCode);
                        break;
                    case c:
                        sb.append(accountNumber);
                        break;
                    case x:
                        sb.append(nationalCheckDigit);
                        break;
                    case t:
                        sb.append(accountType);
                        break;
                    case n:
                        sb.append(ownerAccountType);
                        break;
                    case i:
                        sb.append(identificationNumber);
                        break;
                }
            }
            return sb.toString();
        }

        private String formatIban() {
            StringBuilder sb = new StringBuilder(countryCode.getAlpha2());
            sb.append(DEFAULT_CHECK_DIGIT);
            sb.append(formatBban());
            return sb.toString();
        }

        /**
         * Builds new iban instance.
         *
         * @return new iban instance.
         * @throws IbanFormatException, IllegalArgumentException, UnsupportedCountryException
         *  if values are not parsable by Iban Specification
         *  <a href="http://en.wikipedia.org/wiki/ISO_13616">ISO_13616</a>
         */
        public Iban build() throws IbanFormatException,
                IllegalArgumentException, UnsupportedCountryException {

            // null checks
            Assert.notNull(countryCode, "countryCode is required; it cannot be null");
            Assert.notNull(bankCode, "bankCode is required; it cannot be null");
            Assert.notNull(accountNumber, "accountNumber is required; it cannot be null");

            // iban is formatted with default check digit.
            String ibanValue = formatIban();

            final String checkDigit = IbanUtil.calculateCheckDigit(ibanValue);

            // replace default check digit with calculated check digit
            ibanValue = IbanUtil.replaceCheckDigit(ibanValue, checkDigit);


            IbanUtil.validate(ibanValue);
            return new Iban(ibanValue);
        }
    }

}
