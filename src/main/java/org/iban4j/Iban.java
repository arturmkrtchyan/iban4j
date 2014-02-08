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
    protected static final int COUNTRY_CODE_INDEX = 0;
    protected static final int COUNTRY_CODE_LENGTH = 2;
    protected static final int CHECK_DIGIT_INDEX = COUNTRY_CODE_LENGTH;
    protected static final int CHECK_DIGIT_LENGTH = 2;
    protected static final int BBAN_INDEX = CHECK_DIGIT_INDEX + CHECK_DIGIT_LENGTH;

    private final CountryCode countryCode;
    private String checkDigit;
    private final String bankCode;
    private final String branchCode;
    private final String nationalCheckDigit;
    private final String accountType;
    private final String accountNumber;
    private final String ownerAccountType;
    private final String identificationNumber;

    private final BbanStructure bbanStructure;

    // Cache string value of the iban
    private String value;

    /**
     * Creates iban with default check digit.
     *
     * @param builder Builder
     */
    private Iban(final Builder builder) {
        this.countryCode = builder.countryCode;
        this.bankCode = builder.bankCode;
        this.branchCode = builder.branchCode;
        this.nationalCheckDigit = builder.nationalCheckDigit;
        this.accountType = builder.accountType;
        this.ownerAccountType = builder.ownerAccountType;
        this.accountNumber = builder.accountNumber;
        this.identificationNumber = builder.identificationNumber;

        // initialize with default check digit
        this.checkDigit = DEFAULT_CHECK_DIGIT;

        this.bbanStructure = builder.bbanStructure;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getNationalCheckDigit() {
        return nationalCheckDigit;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getOwnerAccountType() {
        return ownerAccountType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getBban() {
        return formatBban();
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
    public static Iban valueOf(final String iban) throws IbanFormatException {

        if (iban == null) {
            throw new IbanFormatException("null can't be parsed to Iban.");
        }

        CountryCode countryCode = CountryCode.getByCode(iban.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_LENGTH));

        if (countryCode == null) {
            throw new IbanFormatException("Iban has invalid country code: " + iban);
        }

        IbanUtil.validateCheckDigit(iban);

        BbanStructure bbanStructure = BbanStructure.forCountry(countryCode);

        Builder builder = new Builder().countryCode(countryCode).bbanStructure(bbanStructure);

        // FIXME move to helper method to retrieve all entries
        int bbanEntryOffset = BBAN_INDEX;
        for(BbanStructureEntry entry : bbanStructure.getEntries()) {
            int entryLength = entry.getLength();
            String entryValue = iban.substring(bbanEntryOffset, bbanEntryOffset + entryLength);

            switch (entry.getEntryType()) {
                case b:
                    builder.bankCode(entryValue);
                    break;
                case s:
                    builder.branchCode(entryValue);
                    break;
                case c:
                    builder.accountNumber(entryValue);
                    break;
                case x:
                    builder.nationalCheckDigit(entryValue);
                    break;
                case t:
                    builder.accountType(entryValue);
                    break;
                case n:
                    builder.ownerAccountType(entryValue);
                    break;
                case i:
                    builder.identificationNumber(entryValue);
                    break;
            }

            bbanEntryOffset = bbanEntryOffset + entryLength;
        }

        return builder.build();
    }



    private String format() {
        StringBuilder sb = new StringBuilder(countryCode.getAlpha2());
        sb.append(checkDigit);
        sb.append(formatBban());
        return sb.toString();
    }

    private String formatBban() {
        StringBuilder sb = new StringBuilder();
        for(BbanStructureEntry entry : bbanStructure.getEntries()) {
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

    @Override
    public String toString() {
        return format();
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
        private String checkDigit;
        private String bankCode;
        private String branchCode;
        private String nationalCheckDigit;
        private String accountType;
        private String accountNumber;
        private String ownerAccountType;
        private String identificationNumber;
        private BbanStructure bbanStructure;


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

        protected Builder bbanStructure(final BbanStructure bbanStructure) {
            this.bbanStructure = bbanStructure;
            return this;
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

            if(bbanStructure == null) {
                bbanStructure = BbanStructure.forCountry(countryCode);
            }

            // iban instance with default check digit
            Iban iban = new Iban(this);

            // replace default check digit with calculated check digit
            iban.checkDigit = IbanUtil.calculateCheckDigit(iban);

            // pre-genrate and cache iban string value
            iban.value = iban.format();

            // validate iban
            IbanUtil.validate(iban.value);

            return iban;
        }
    }

}
