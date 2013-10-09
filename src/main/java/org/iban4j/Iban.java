package org.iban4j;

import java.io.Serializable;

public final class Iban implements Serializable {

    private static final long serialVersionUID = 3507561504372065317L;

    private CountryCode countryCode;
    private String checkDigit;
    private Bban bban;

    public Iban(CountryCode countryCode, Bban bban) throws IbanFormatException {
        this.countryCode = countryCode;
        this.bban = bban;
        this.checkDigit = IbanUtil.calculateCheckDigit(this);
        // TODO validation
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

    public static Iban valueOf() throws IbanFormatException {
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

    public static void main(String[] args) {
        Bban bban = new Bban.Builder()
                .bankCode("19043")
                .accountNumber("00234573201")
                .build();
        Iban iban = new Iban(CountryCode.AT, bban);
        System.out.println(iban);

        bban = new Bban.Builder()
                .bankCode("0002")
                .branchCode("0123")
                .accountNumber("12345678901")
                .nationalCheckDigit("54")
                .build();
        iban = new Iban(CountryCode.PT, bban);
        System.out.println(iban);
    }
}
