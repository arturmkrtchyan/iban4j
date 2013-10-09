package org.iban4j;

import org.iban4j.Bban;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

public class IbanUtil {

    private static final String DEFAULT_CHECK_DIGIT = "00";
    private static final long MOD = 97;
    private static final long MAX = 999999999;

    public static String calculateCheckDigit(Iban iban) {
        return calculateCheckDigit(iban.getCountryCode(), iban.getBban());
    }

    public static String calculateCheckDigit(CountryCode countryCode, Bban bban) {
        StringBuilder sb = new StringBuilder()
                .append(countryCode.name())
                .append(DEFAULT_CHECK_DIGIT)
                .append(bban.format(countryCode));
        return calculateCheckDigit(sb.toString());
    }

    private static String calculateCheckDigit(String iban) {
        int modResult = calculateMod(iban);
        int checkDigitIntValue = (98 - modResult);
        String checkDigit = Integer.toString(checkDigitIntValue);
        return checkDigitIntValue > 9 ? checkDigit : "0" + checkDigit;
    }

    private static int calculateMod(String iban) {
        String reformattedIban = iban.substring(4) + iban.substring(0, 4);
        long total = 0;
        for (int i = 0; i < reformattedIban.length(); i++) {
            int numericValue = Character.getNumericValue(reformattedIban.charAt(i));
            if (numericValue < 0 || numericValue > 35) {
                throw new IllegalArgumentException("Invalid Character[" + i + "] = '" + numericValue + "'");
            }
            total = (numericValue > 9 ? total * 100 : total * 10) + numericValue;

            if (total > MAX) {
                total = (total % MOD);
            }

        }
        return (int)(total % MOD);
    }


}
