package org.iban4j;

public class IbanUtil {

    private static final long MOD = 97;
    private static final long MAX = 999999999;

    static CheckDigit calculateCheckDigit(String iban) {
        int modResult = calculateMod(iban);
        int checkDigitIntValue = (98 - modResult);
        String checkDigit = Integer.toString(checkDigitIntValue);
        return new CheckDigit(checkDigitIntValue > 9 ? checkDigit : "0" + checkDigit);
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
