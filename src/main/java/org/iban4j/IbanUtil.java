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
import org.iban4j.support.Assert;

import static org.iban4j.Iban.*;

/**
 * Iban Utility Class
 */
public final class IbanUtil {

    private static final int MOD = 97;
    private static final long MAX = 999999999;

    private static final int MIN_IBAN_SIZE = 15;
    private static final int COUNTRY_CODE_LENGTH = 2;

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
     *         UnsupportedCountryException if iban's country is not supported.
     *         InvalidCheckDigitException if iban has invalid check digit.
     */
    public static void validate(final String iban) throws IbanFormatException,
            InvalidCheckDigitException, UnsupportedCountryException {

        try {
            Assert.notNull(iban, "Null can't be a valid Iban.");
            validateCountryCode(iban);
            validateMinLength(iban);

            BbanStructure structure = getBbanStructure(iban);

            validateBbanLength(iban, structure);
            validateBbanEntries(iban, structure);

            validateCheckDigit(iban);
        } catch (UnsupportedCountryException e) {
            throw e;
        } catch (InvalidCheckDigitException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new IbanFormatException(e.getMessage());
        }
    }

    protected static void validateCheckDigit(final String iban) {
        String checkDigit = getCheckDigit(iban);
        String expectedCheckDigit = calculateCheckDigit(iban);
        if (!checkDigit.equals(expectedCheckDigit)) {
            throw new InvalidCheckDigitException("[" + iban + "] has invalid check digit: " +
                    checkDigit + ", expected check digit is: " + expectedCheckDigit);
        }
    }

    private static void validateCountryCode(final String iban) {
        String countryCode = getCountryCode(iban);
        if( countryCode.trim().length() < COUNTRY_CODE_LENGTH ||
            !countryCode.equals(countryCode.toUpperCase()) ||
            !Character.isLetter(countryCode.charAt(0)) ||
            !Character.isLetter(countryCode.charAt(1))) {
            throw new IbanFormatException("Iban country code must contain upper case letters");
        }

        Assert.notNull(CountryCode.getByCode(countryCode), "Iban contains non existing country code.");
    }

    private static void validateMinLength(final String iban) {
        if(iban.length() < MIN_IBAN_SIZE) {
            throw new IbanFormatException("Iban length can't be less than " + MIN_IBAN_SIZE);
        }
    }

    private static void validateBbanLength(final String iban, final BbanStructure structure) {
        int expectedBbanLength = structure.getBbanLength();
        String bban = getBban(iban);
        int bbanLength = bban.length();
        if (expectedBbanLength != bbanLength) {
            throw new IbanFormatException("[" + bban + "] length is " +
                    bbanLength + ", expected BBAN length is: " + expectedBbanLength);
        }
    }

    private static void validateBbanEntries(final String iban, final BbanStructure structure) {
        final String bban = getBban(iban);
        int bbanEntryOffset = 0;
        for(BbanStructureEntry entry : structure.getEntries()) {
            final int entryLength = entry.getLength();
            final String entryValue = bban.substring(bbanEntryOffset, bbanEntryOffset + entryLength);

            bbanEntryOffset = bbanEntryOffset + entryLength;

            // validate character type
            validateBbanEntryCharacterType(entry, entryValue);
        }
    }

    private static void validateBbanEntryCharacterType(final BbanStructureEntry entry, final String entryValue) {
        switch (entry.getCharacterType()) {
            case a:
                for(char ch: entryValue.toCharArray()) {
                    Assert.isTrue(Character.isUpperCase(ch), "[" + entryValue + "] must contain only upper case letters.");
                }
                break;
            case c:
                for(char ch: entryValue.toCharArray()) {
                    Assert.isTrue(Character.isLetterOrDigit(ch), "[" + entryValue + "] must contain only digits or letters.");
                }
                break;
            case n:
                for(char ch: entryValue.toCharArray()) {
                    Assert.isTrue(Character.isDigit(ch), "[" + entryValue + "] must contain only digits.");
                }
                break;
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
        return getCountryCode(iban) + DEFAULT_CHECK_DIGIT + getBban(iban);
    }


    /**
     * Calculates
     * <a href="http://en.wikipedia.org/wiki/ISO_13616#Modulo_operation_on_IBAN">Iban Modulo</a>.
     *
     * @param iban String value
     * @return modulo 97
     */
    private static int calculateMod(final String iban) {
        String reformattedIban = getBban(iban) + getCountryCode(iban) + getCheckDigit(iban);
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

    private static BbanStructure getBbanStructure(final String iban) {
        String countryCode = getCountryCode(iban);
        return BbanStructure.forCountry(CountryCode.getByCode(countryCode));
    }

    private static String getCheckDigit(final String iban) {
        return iban.substring(CHECK_DIGIT_INDEX, CHECK_DIGIT_INDEX + CHECK_DIGIT_LENGTH);
    }

    private static String getCountryCode(final String iban) {
        return iban.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
    }

    private static String getBban(final String iban) {
        return iban.substring(BBAN_INDEX);
    }

}
