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

import org.iban4j.bban.BbanEntryType;
import org.iban4j.bban.BbanStructure;
import org.iban4j.bban.BbanStructureEntry;
import org.iban4j.support.Assert;
import static org.iban4j.IbanFormatException.IbanFormatViolation.*;
/**
 * Iban Utility Class
 */
public final class IbanUtil {

    private static final int MOD = 97;
    private static final long MAX = 999999999;

    private static final int COUNTRY_CODE_INDEX = 0;
    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int CHECK_DIGIT_INDEX = COUNTRY_CODE_LENGTH;
    private static final int CHECK_DIGIT_LENGTH = 2;
    private static final int BBAN_INDEX = CHECK_DIGIT_INDEX + CHECK_DIGIT_LENGTH;

    private static final String ASSERT_UPPER_LETTERS = "[%s] must contain only upper case letters.";
    private static final String ASSERT_DIGITS_AND_LETTERS = "[%s] must contain only digits or letters.";
    private static final String ASSERT_DIGITS = "[%s] must contain only digits.";

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
        String reformattedIban = replaceCheckDigit(iban, Iban.DEFAULT_CHECK_DIGIT);
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
            validateEmpty(iban);
            validateCountryCode(iban);
            validateCheckDigitPresence(iban);

            BbanStructure structure = getBbanStructure(iban);

            validateBbanLength(iban, structure);
            validateBbanEntries(iban, structure);

            validateCheckDigit(iban);
        } catch (UnsupportedCountryException e) {
            throw e;
        } catch (InvalidCheckDigitException e) {
            throw e;
        } catch (IbanFormatException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new IbanFormatException(e.getMessage(), e);
        }
    }

    private static void validateCheckDigit(final String iban) {
        String checkDigit = getCheckDigit(iban);
        String expectedCheckDigit = calculateCheckDigit(iban);
        if (!checkDigit.equals(expectedCheckDigit)) {
            throw new InvalidCheckDigitException("[" + iban + "] has invalid check digit: " +
                    checkDigit + ", expected check digit is: " + expectedCheckDigit);
        }
    }

    private static void validateEmpty(final String iban) {
        if(iban == null) {
            throw new IbanFormatException(NOT_NULL_IBAN, "Null can't be a valid Iban.");
        }

        if(iban.trim().length() == 0) {
            throw new IbanFormatException(NOT_EMPTY_IBAN, "Empty string can't be a valid Iban.");
        }
    }

    private static void validateCountryCode(final String iban) {
        // check if iban contains 2 char country code
        if(iban.trim().length() < COUNTRY_CODE_LENGTH) {
            throw new IbanFormatException(TWO_CHAR_COUNTRY_CODE,
                    "Iban must contain 2 char country code.");
        }

        String countryCode = getCountryCode(iban);

        // check case sensitivity
        if(!countryCode.equals(countryCode.toUpperCase()) ||
            !Character.isLetter(countryCode.charAt(0)) ||
            !Character.isLetter(countryCode.charAt(1))) {
            throw new IbanFormatException(UPPER_CASE_CHAR_COUNTRY_CODE,
                    "Iban country code must contain upper case letters.");
        }

        if(CountryCode.getByCode(countryCode) == null) {
            throw new IbanFormatException(EXISTING_COUNTRY_CODE,
                    "Iban contains non existing country code.");
        }

        // check if country is supported
        BbanStructure structure = BbanStructure.forCountry(CountryCode.getByCode(countryCode));
        if (structure == null) {
            throw new UnsupportedCountryException("Country code: " + countryCode + " is not supported.");
        }
    }

    private static void validateCheckDigitPresence(final String iban) {
        // check if iban contains 2 digit check digit
        if(iban.trim().length() < COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH) {
            throw new IbanFormatException(TWO_DIGIT_CHECK_DIGIT,
                    "Iban must contain 2 digit check digit.");
        }

        String checkDigit = getCheckDigit(iban);

        // check digits
        if(!Character.isDigit(checkDigit.charAt(0)) ||
           !Character.isDigit(checkDigit.charAt(1))) {
            throw new IbanFormatException(ONLY_DIGIT_CHECK_DIGIT,
                    "Iban's check digit should contain only digits.");
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
                    Assert.isTrue(Character.isUpperCase(ch), ASSERT_UPPER_LETTERS, entryValue);
                }
                break;
            case c:
                for(char ch: entryValue.toCharArray()) {
                    Assert.isTrue(Character.isLetterOrDigit(ch), ASSERT_DIGITS_AND_LETTERS, entryValue);
                }
                break;
            case n:
                for(char ch: entryValue.toCharArray()) {
                    Assert.isTrue(Character.isDigit(ch), ASSERT_DIGITS, entryValue);
                }
                break;
        }
    }

    protected static String calculateCheckDigit(final Iban iban) {
        return calculateCheckDigit(iban.toString());
    }

    /**
     * Returns an iban with replaced check digit.
     *
     * @param iban The iban
     * @return The iban without the check digit
     */
    protected static String replaceCheckDigit(final String iban, final String checkDigit) {
        return getCountryCode(iban) + checkDigit + getBban(iban);
    }


    /**
     * Calculates
     * <a href="http://en.wikipedia.org/wiki/ISO_13616#Modulo_operation_on_IBAN">Iban Modulo</a>.
     *
     * @param iban String value
     * @return modulo 97
     */
    private static int calculateMod(final String iban) {
        String reformattedIban = getBban(iban) + getCountryCodeAndCheckDigit(iban);
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

    static String getCheckDigit(final String iban) {
        return iban.substring(CHECK_DIGIT_INDEX, CHECK_DIGIT_INDEX + CHECK_DIGIT_LENGTH);
    }

    static String getCountryCode(final String iban) {
        return iban.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
    }

    static String getCountryCodeAndCheckDigit(final String iban) {
        return iban.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH);
    }

    static String getBban(final String iban) {
        return iban.substring(BBAN_INDEX);
    }

    static String getAccountNumber(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.account_number);
    }

    static String getBankCode(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.bank_code);
    }

    static String getBranchCode(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.branch_code);
    }

    static String getNationalCheckDigit(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.national_check_digit);
    }

    static String getAccountType(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.account_type);
    }

    static String getOwnerAccountType(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.owner_account_number);
    }

    static String getIdentificationNumber(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.identification_number);
    }

    private static String extractBbanEntry(final String iban, final BbanEntryType entryType) {
        final String bban = getBban(iban);
        final BbanStructure structure = getBbanStructure(iban);
        int bbanEntryOffset = 0;
        for(BbanStructureEntry entry : structure.getEntries()) {
            final int entryLength = entry.getLength();
            final String entryValue = bban.substring(bbanEntryOffset, bbanEntryOffset + entryLength);

            bbanEntryOffset = bbanEntryOffset + entryLength;
            if(entry.getEntryType() == entryType) {
                return entryValue;
            }
        }
        return null;
    }

}
