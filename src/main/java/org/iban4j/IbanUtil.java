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

public final class IbanUtil {

    private static final String DEFAULT_CHECK_DIGIT = "00";
    private static final long MOD = 97;
    private static final long MAX = 999999999;

    private IbanUtil() {
    }

    public static String calculateCheckDigit(CountryCode countryCode, Bban bban) {
        return calculateCheckDigit(countryCode, bban.format(countryCode));
    }

    public static String calculateCheckDigit(CountryCode countryCode, String bban) {
        StringBuilder sb = new StringBuilder()
                .append(countryCode.name())
                .append(DEFAULT_CHECK_DIGIT)
                .append(bban);
        return calculateCheckDigit(sb.toString());
    }

    public static String calculateCheckDigit(String iban) {
        String reformattedIban = removeCheckDigit(iban);
        int modResult = calculateMod(reformattedIban);
        int checkDigitIntValue = (98 - modResult);
        String checkDigit = Integer.toString(checkDigitIntValue);
        return checkDigitIntValue > 9 ? checkDigit : "0" + checkDigit;
    }

    /**
     * Returns an iban with default check digit.
     *
     * @param iban The iban
     * @return The iban without the check digit
     */
    protected static String removeCheckDigit(String iban) {
        return iban.substring(0, 2) + DEFAULT_CHECK_DIGIT + iban.substring(4);
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
