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
import org.iban4j.support.*;

/**
 * Iban Utility Class
 */
public final class IbanUtil {


    private static final long MOD = 97;
    private static final long MAX = 999999999;

    private IbanUtil() {
    }

    /**
     * Calculates Iban
     * <a href="http://en.wikipedia.org/wiki/ISO_13616#Generating_IBAN_check_digits">Check Digit</a>.
     *
     * @param iban string value
     * @return check digit as String
     */
    public static String calculateCheckDigit(final String iban) {
        String reformattedIban = removeCheckDigit(iban);
        int modResult = calculateMod(reformattedIban);
        int checkDigitIntValue = (98 - modResult);
        String checkDigit = Integer.toString(checkDigitIntValue);
        return checkDigitIntValue > 9 ? checkDigit : "0" + checkDigit;
    }

    /**
     * Validates iban.
     *
     * @param iban to be validated.
     * @throws IbanFormatException if iban is invalid.
     *
     * @deprecated use {@link #validate(String)} instead.
     */
    @Deprecated
    public static void validate(final Iban iban) throws IbanFormatException {
        validate(iban.toString());
    }

    /**
     * Validates iban.
     *
     * @param iban to be validated.
     * @throws IbanFormatException if iban is invalid.
     *         UnsupportedCountryException if iban's country is not supported.
     */
    public static void validate(String iban) throws IbanFormatException {

        BbanStructure structure = getBbanStructure(iban);

        if(structure == null) {
            throw new UnsupportedCountryException();
        }

        try {
            validateIbanLength(iban, structure);
            validateIbanEntries(iban, structure);
        } catch (Exception e) {
            throw new IbanFormatException(e);
        }
    }

    private static BbanStructure getBbanStructure(String iban) {
        String countryCode = iban.substring(0, 2);
        return BbanStructure.forCountry(CountryCode.valueOf(countryCode));
    }

    private static void validateIbanLength(String iban, BbanStructure structure) {
        int bbanLength = structure.getBbanLength();
        int ibanLength = iban.length();
        if (bbanLength != ibanLength - 4) {
            throw new IbanFormatException("[" + iban + "] length is " +
                    ibanLength + ", expected IBAN length is: " + (bbanLength + 4));
        }
    }

    private static void validateIbanEntries(String iban, BbanStructure structure) {
        int ibanEntryOffset = 4;
        for(BbanStructureEntry entry : structure.getEntries()) {
            int entryLength = entry.getLength();
            String entryValue = iban.substring(ibanEntryOffset, ibanEntryOffset + entryLength);

            // validate length
            Assert.hasLength(entryValue, entryLength, "Invalid bank code length.");
            ibanEntryOffset = ibanEntryOffset + entryLength;

            // validate character type
            switch (entry.getCharacterType()) {
                case a:
                    for(char ch: entryValue.toCharArray()) {
                        Assert.isTrue(Character.isUpperCase(ch), "[" + entryValue + "] must contain only digits");
                    }
                    break;
                case c:
                    for(char ch: entryValue.toCharArray()) {
                        Assert.isTrue(Character.isLetterOrDigit(ch), "[" + entryValue + "] must contain only digits");
                    }
                    break;
                case n:
                    for(char ch: entryValue.toCharArray()) {
                        Assert.isTrue(Character.isDigit(ch), "[" + entryValue + "] must contain only digits");
                    }
                    break;

            }
        }
    }

    protected static String calculateCheckDigit(final Iban iban) {
        return calculateCheckDigit(iban.toString());
    }

    /**
     * Returns an iban with default check digit.
     *
     * @param iban The iban
     * @return The iban without the check digit
     */
    private static String removeCheckDigit(final String iban) {
        return iban.substring(0, 2) + Iban.DEFAULT_CHECK_DIGIT + iban.substring(4);
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
        return (int) (total % MOD);
    }

}
