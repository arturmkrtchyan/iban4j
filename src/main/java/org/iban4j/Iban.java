/* Licensed under the Apache License, Version 2.0 (the "License");
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

import java.io.Serializable;

/**
 * International Bank Account Number
 *
 * <a href="http://en.wikipedia.org/wiki/ISO_13616">ISO_13616</a>.
 */
public final class Iban implements Serializable {

    private static final long serialVersionUID = 3507561504372065317L;

    private CountryCode countryCode;
    private String checkDigit;
    private Bban bban;

    private Iban(Builder builder) throws IbanFormatException {
        countryCode = builder.countryCode;
        bban = builder.buildBban();
        checkDigit = IbanUtil.calculateCheckDigit(countryCode, bban);
        // TODO validation, add to next version
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public String getAccountNumber() {
        return bban.getAccountNumber();
    }

    public String getBankCode() {
        return bban.getBankCode();
    }

    public String getBranchCode() {
        return bban.getBranchCode();
    }

    public String getNationalCheckDigit() {
        return bban.getNationalCheckDigit();
    }

    public String getAccountType() {
        return bban.getAccountType();
    }

    public String getOwnerAccountType() {
        return bban.getOwnerAccountType();
    }

    public String getIdentificationNumber() {
        return bban.getIdentificationNumber();
    }

    // TODO for future releases
    protected Bban getBban() {
        return bban;
    }

    // TODO for future releases
    private static Iban valueOf() throws IbanFormatException {
        // TODO implement
        return null;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(countryCode.name())
                .append(checkDigit)
                .append(bban.format(countryCode))
                .toString();
    }

    /**
     * Iban Builder Class
     */
    public static class Builder {

        private Bban.Builder bbanBuilder;
        private CountryCode countryCode;

        public Builder() {
            bbanBuilder = new Bban.Builder();
        }

        public Builder countryCode(final CountryCode countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder bankCode(final String bankCode) {
            bbanBuilder.bankCode(bankCode);
            return this;
        }

        public Builder branchCode(final String branchCode) {
            bbanBuilder.branchCode(branchCode);
            return this;
        }

        public Builder accountNumber(final String accountNumber) {
            bbanBuilder.accountNumber(accountNumber);
            return this;
        }

        public Builder nationalCheckDigit(final String nationalCheckDigit) {
            bbanBuilder.nationalCheckDigit(nationalCheckDigit);
            return this;
        }

        public Builder accountType(final String accountType) {
            bbanBuilder.accountType(accountType);
            return this;
        }

        public Builder ownerAccountType(final String ownerAccountType) {
            bbanBuilder.ownerAccountType(ownerAccountType);
            return this;
        }

        public Builder identificationNumber(final String identificationNumber) {
            bbanBuilder.identificationNumber(identificationNumber);
            return this;
        }

        /**
         * Builds new iban instance
         *
         * @return new iban instance
         * @throws IbanFormatException if values are not parsable by Iban Specification
         * <a href="http://en.wikipedia.org/wiki/ISO_13616">ISO_13616</a>
         */
        public Iban build() throws IbanFormatException {
            return new Iban(this);
        }

        private Bban buildBban() {
            return bbanBuilder.build();
        }
    }

}
