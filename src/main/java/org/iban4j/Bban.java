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

import org.iban4j.support.IbanStructure;
import org.iban4j.support.IbanStructureCache;
import org.iban4j.support.IbanStructureEntry;

import java.io.Serializable;

/**
 * Basic Bank Account Number Class
 */
final class Bban implements Serializable {

    private static final long serialVersionUID = 8748285872603350092L;

    private String bankCode;
    private String branchCode;
    private String nationalCheckDigit;
    private String accountType;
    private String accountNumber;
    private String ownerAccountType;
    private String identificationNumber;


    private Bban(final Builder builder) {
        this.bankCode = builder.bankCode;
        this.branchCode = builder.branchCode;
        this.nationalCheckDigit = builder.nationalCheckDigit;
        this.accountType = builder.accountType;
        this.ownerAccountType = builder.ownerAccountType;
        this.accountNumber = builder.accountNumber;
        this.identificationNumber = builder.identificationNumber;
    }

    protected String format(final CountryCode countryCode) {
        IbanStructure structure = IbanStructureCache.getStructure(countryCode.getAlpha2());
        return format(structure);
    }

    private String format(final IbanStructure structure) {
        StringBuilder sb = new StringBuilder();
        for(IbanStructureEntry entry : structure.getBbanEntries()) {
            switch (entry.getEntryType()) {

                case k:
                    break;
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

    protected String getBankCode() {
        return bankCode;
    }

    protected String getBranchCode() {
        return branchCode;
    }

    protected String getNationalCheckDigit() {
        return nationalCheckDigit;
    }

    protected String getAccountType() {
        return accountType;
    }

    protected String getAccountNumber() {
        return accountNumber;
    }

    protected String getOwnerAccountType() {
        return ownerAccountType;
    }

    protected String getIdentificationNumber() {
        return identificationNumber;
    }

    @Override
    public String toString() {
        return "Bban{" +
                "bankCode='" + bankCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", nationalCheckDigit='" + nationalCheckDigit + '\'' +
                ", accountType='" + accountType + '\'' +
                ", ownerAccountType='" + ownerAccountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

    static class Builder {
        private String bankCode;
        private String branchCode;
        private String nationalCheckDigit;
        private String accountType;
        private String ownerAccountType;
        private String accountNumber;
        private String identificationNumber;

        public Builder bankCode(final String bankCode) {
            this.bankCode = bankCode;
            return this;
        }

        public Builder branchCode(final String branchCode) {
            this.branchCode = branchCode;
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

        public Builder accountNumber(final String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder identificationNumber(final String identificationNumber) {
            this.identificationNumber = identificationNumber;
            return this;
        }

        public Bban build() {
            return new Bban(this);
        }
    }
}
