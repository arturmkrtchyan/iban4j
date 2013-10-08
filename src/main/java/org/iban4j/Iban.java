package org.iban4j;

import java.io.Serializable;

public final class Iban implements Serializable {

    private static final long serialVersionUID = 3507561504372065317L;

    private CountryCode countryCode;
    private String checkDigit;
    private Bban bban;

    public Iban(CountryCode countryCode, String checkDigit, Bban bban) {
        this.countryCode = countryCode;
        this.checkDigit = checkDigit;
        this.bban = bban;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public Bban getBban() {
        return bban;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(countryCode.name())
                .append(checkDigit)
                .append(bban.toString())
                .toString();
    }
}
