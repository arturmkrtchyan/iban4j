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

/**
 * Iban Utility Class
 */
public final class IbanUtil {

    private static final String DEFAULT_CHECK_DIGIT = "00";
    private static final long MOD = 97;
    private static final long MAX = 999999999;

    private IbanUtil() {
    }

    /**
     * Calculates Iban
     * <a href="http://en.wikipedia.org/wiki/ISO_13616#Generating_IBAN_check_digits">Check Digit</a>.
     *
     * @param iban string value
     * @return  check digit as String
     */
    public static String calculateCheckDigit(final String iban) {
        String reformattedIban = removeCheckDigit(iban);
        int modResult = calculateMod(reformattedIban);
        int checkDigitIntValue = (98 - modResult);
        String checkDigit = Integer.toString(checkDigitIntValue);
        return checkDigitIntValue > 9 ? checkDigit : "0" + checkDigit;
    }

    protected static String calculateCheckDigit(final CountryCode countryCode, final Bban bban) {
        return calculateCheckDigit(countryCode, bban.format(countryCode));
    }

    protected static String calculateCheckDigit(final CountryCode countryCode, final String bban) {
        StringBuilder sb = new StringBuilder()
                .append(countryCode.name())
                .append(DEFAULT_CHECK_DIGIT)
                .append(bban);
        return calculateCheckDigit(sb.toString());
    }

    /**
     * Returns an iban with default check digit.
     *
     * @param iban The iban
     * @return The iban without the check digit
     */
    private static String removeCheckDigit(final String iban) {
        return iban.substring(0, 2) + DEFAULT_CHECK_DIGIT + iban.substring(4);
    }


    /**
     * Calculates
     * <a href="http://en.wikipedia.org/wiki/ISO_13616#Modulo_operation_on_IBAN">Iban Modulo</a>.
     *
     * @param iban String value
     * @return modulo 97
     */
    private static int calculateMod(final String iban) {
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
