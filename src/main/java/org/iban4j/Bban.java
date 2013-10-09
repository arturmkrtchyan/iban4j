package org.iban4j;

import org.iban4j.support.IbanStructure;
import org.iban4j.support.IbanStructureCache;
import org.iban4j.support.IbanStructureEntry;

import java.io.Serializable;

public final class Bban implements Serializable {

    private static final long serialVersionUID = 8748285872603350092L;

    private String bankCode;
    private String branchCode;
    private String nationalCheckDigit;
    private String accountType;
    private String ownerAccountType;
    private String accountNumber;

    private Bban(Builder builder) {
        this.bankCode = builder.bankCode;
        this.branchCode = builder.branchCode;
        this.nationalCheckDigit = builder.nationalCheckDigit;
        this.accountType = builder.accountType;
        this.ownerAccountType = builder.ownerAccountType;
        this.accountNumber = builder.accountNumber;
    }

    public String format(CountryCode countryCode) {
        StringBuilder sb = new StringBuilder();
        IbanStructure structure = IbanStructureCache.getStructure(countryCode);
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
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "TODO";
    }

    public static class Builder {
        private String bankCode;
        private String branchCode;
        private String nationalCheckDigit;
        private String accountType;
        private String ownerAccountType;
        private String accountNumber;

        public Builder bankCode(String bankCode) {
            this.bankCode = bankCode;
            return this;
        }

        public Builder branchCode(String branchCode) {
            this.branchCode = branchCode;
            return this;
        }

        public Builder nationalCheckDigit(String nationalCheckDigit) {
            this.nationalCheckDigit = nationalCheckDigit;
            return this;
        }

        public Builder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder ownerAccountType(String ownerAccountType) {
            this.ownerAccountType = ownerAccountType;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Bban build() {
            return new Bban(this);
        }
    }
}
