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
        final String reformattedIban = replaceCheckDigit(iban,
                Iban.DEFAULT_CHECK_DIGIT);
        final int modResult = calculateMod(reformattedIban);
        final int checkDigitIntValue = (98 - modResult);
        final String checkDigit = Integer.toString(checkDigitIntValue);
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

            final BbanStructure structure = getBbanStructure(iban);

            validateBbanLength(iban, structure);
            validateBbanEntries(iban, structure);

            validateCheckDigit(iban);
        } catch (Iban4jException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new IbanFormatException(UNKNOWN, e.getMessage());
        }
    }

    /**
     * Checks whether country is supporting iban.
     * @param countryCode {@link org.iban4j.CountryCode}
     *
     * @return boolean true if country supports iban, false otherwise.
     */
    public static boolean isSupportedCountry(final CountryCode countryCode) {
        return BbanStructure.forCountry(countryCode) != null;
    }

    /**
     * Returns iban length for the specified country.
     *
     * @param countryCode {@link org.iban4j.CountryCode}
     * @return the length of the iban for the specified country.
     */
    public static int getIbanLength(final CountryCode countryCode) {
        final BbanStructure structure = getBbanStructure(countryCode);
        return COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH + structure.getBbanLength();
    }

    /**
     * Returns iban's check digit.
     *
     * @param iban String
     * @return checkDigit String
     */
    public static String getCheckDigit(final String iban) {
        return iban.substring(CHECK_DIGIT_INDEX,
                CHECK_DIGIT_INDEX + CHECK_DIGIT_LENGTH);
    }

    /**
     * Returns iban's country code.
     *
     * @param iban String
     * @return countryCode String
     */
    public static String getCountryCode(final String iban) {
        return iban.substring(COUNTRY_CODE_INDEX,
                COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
    }

    /**
     * Returns iban's country code and check digit.
     *
     * @param iban String
     * @return countryCodeAndCheckDigit String
     */
    public static String getCountryCodeAndCheckDigit(final String iban) {
        return iban.substring(COUNTRY_CODE_INDEX,
                COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH);
    }

    /**
     * Returns iban's bban (Basic Bank Account Number).
     *
     * @param iban String
     * @return bban String
     */
    public static String getBban(final String iban) {
        return iban.substring(BBAN_INDEX);
    }

    /**
     * Returns iban's account number.
     *
     * @param iban String
     * @return accountNumber String
     */
    public static String getAccountNumber(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.account_number);
    }

    /**
     * Returns iban's bank code.
     *
     * @param iban String
     * @return bankCode String
     */
    public static String getBankCode(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.bank_code);
    }

    /**
     * Returns iban's branch code.
     *
     * @param iban String
     * @return branchCode String
     */
    static String getBranchCode(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.branch_code);
    }

    /**
     * Returns iban's national check digit.
     *
     * @param iban String
     * @return nationalCheckDigit String
     */
    static String getNationalCheckDigit(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.national_check_digit);
    }

    /**
     * Returns iban's account type.
     *
     * @param iban String
     * @return accountType String
     */
    static String getAccountType(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.account_type);
    }

    /**
     * Returns iban's owner account type.
     *
     * @param iban String
     * @return ownerAccountType String
     */
    static String getOwnerAccountType(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.owner_account_number);
    }

    /**
     * Returns iban's identification number.
     *
     * @param iban String
     * @return identificationNumber String
     */
    static String getIdentificationNumber(final String iban) {
        return extractBbanEntry(iban, BbanEntryType.identification_number);
    }

    static String calculateCheckDigit(final Iban iban) {
        return calculateCheckDigit(iban.toString());
    }

    /**
     * Returns an iban with replaced check digit.
     *
     * @param iban The iban
     * @return The iban without the check digit
     */
    static String replaceCheckDigit(final String iban, final String checkDigit) {
        return getCountryCode(iban) + checkDigit + getBban(iban);
    }



    private static void validateCheckDigit(final String iban) {
        String checkDigit = getCheckDigit(iban);
        String expectedCheckDigit = calculateCheckDigit(iban);
        if (!checkDigit.equals(expectedCheckDigit)) {
            throw new InvalidCheckDigitException(
                    checkDigit, expectedCheckDigit,
                    "[" + iban + "] has invalid check digit: " +
                    checkDigit + ", expected check digit is: " + expectedCheckDigit);
        }
    }

    private static void validateEmpty(final String iban) {
        if(iban == null) {
            throw new IbanFormatException(IBAN_NOT_NULL,
                    "Null can't be a valid Iban.");
        }

        if(iban.length() == 0) {
            throw new IbanFormatException(IBAN_NOT_EMPTY,
                    "Empty string can't be a valid Iban.");
        }
    }

    private static void validateCountryCode(final String iban) {
        // check if iban contains 2 char country code
        if(iban.length() < COUNTRY_CODE_LENGTH) {
            throw new IbanFormatException(COUNTRY_CODE_TWO_LETTERS, iban,
                    "Iban must contain 2 char country code.");
        }

        final String countryCode = getCountryCode(iban);

        // check case sensitivity
        if(!countryCode.equals(countryCode.toUpperCase()) ||
            !Character.isLetter(countryCode.charAt(0)) ||
            !Character.isLetter(countryCode.charAt(1))) {
            throw new IbanFormatException(COUNTRY_CODE_UPPER_CASE_LETTERS, countryCode,
                    "Iban country code must contain upper case letters.");
        }

        if(CountryCode.getByCode(countryCode) == null) {
            throw new IbanFormatException(COUNTRY_CODE_EXISTS, countryCode,
                    "Iban contains non existing country code.");
        }

        // check if country is supported
        final BbanStructure structure = BbanStructure.forCountry(
                CountryCode.getByCode(countryCode));
        if (structure == null) {
            throw new UnsupportedCountryException(countryCode,
                    "Country code is not supported.");
        }
    }

    private static void validateCheckDigitPresence(final String iban) {
        // check if iban contains 2 digit check digit
        if(iban.length() < COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH) {
            throw new IbanFormatException(CHECK_DIGIT_TWO_DIGITS,
                    iban.substring(COUNTRY_CODE_LENGTH),
                    "Iban must contain 2 digit check digit.");
        }

        final String checkDigit = getCheckDigit(iban);

        // check digits
        if(!Character.isDigit(checkDigit.charAt(0)) ||
           !Character.isDigit(checkDigit.charAt(1))) {
            throw new IbanFormatException(CHECK_DIGIT_ONLY_DIGITS, checkDigit,
                    "Iban's check digit should contain only digits.");
        }
    }

    private static void validateBbanLength(final String iban,
                                           final BbanStructure structure) {
        final int expectedBbanLength = structure.getBbanLength();
        final String bban = getBban(iban);
        final int bbanLength = bban.length();
        if (expectedBbanLength != bbanLength) {
            throw new IbanFormatException(BBAN_LENGTH,
                    bbanLength, expectedBbanLength,
                    "[" + bban + "] length is " + bbanLength +
                    ", expected BBAN length is: " + expectedBbanLength);
        }
    }

    private static void validateBbanEntries(final String iban,
                                            final BbanStructure structure) {
        final String bban = getBban(iban);
        // FIXME duplicate code
        int bbanEntryOffset = 0;
        for(final BbanStructureEntry entry : structure.getEntries()) {
            final int entryLength = entry.getLength();
            final String entryValue = bban.substring(bbanEntryOffset,
                    bbanEntryOffset + entryLength);

            bbanEntryOffset = bbanEntryOffset + entryLength;

            // validate character type
            validateBbanEntryCharacterType(entry, entryValue);
        }
    }

    private static void validateBbanEntryCharacterType(final BbanStructureEntry entry,
                                                       final String entryValue) {
        switch (entry.getCharacterType()) {
            case a:
                for(char ch: entryValue.toCharArray()) {
                    if(!Character.isUpperCase(ch)) {
                        throw new IbanFormatException(BBAN_ONLY_UPPER_CASE_LETTERS,
                                entry.getEntryType(), entryValue, ch,
                                String.format(ASSERT_UPPER_LETTERS, entryValue));
                    }
                }
                break;
            case c:
                for(char ch: entryValue.toCharArray()) {
                    if(!Character.isLetterOrDigit(ch)) {
                        throw new IbanFormatException(BBAN_ONLY_DIGITS_OR_LETTERS,
                                entry.getEntryType(), entryValue, ch,
                                String.format(ASSERT_DIGITS_AND_LETTERS, entryValue));
                    }
                }
                break;
            case n:
                for(char ch: entryValue.toCharArray()) {
                    if(!Character.isDigit(ch)) {
                        throw new IbanFormatException(BBAN_ONLY_DIGITS,
                                entry.getEntryType(), entryValue, ch,
                                String.format(ASSERT_DIGITS, entryValue));
                    }
                }
                break;
        }
    }

    /**
     * Calculates
     * <a href="http://en.wikipedia.org/wiki/ISO_13616#Modulo_operation_on_IBAN">Iban Modulo</a>.
     *
     * @param iban String value
     * @return modulo 97
     */
    private static int calculateMod(final String iban) {
        final String reformattedIban = getBban(iban) + getCountryCodeAndCheckDigit(iban);
        long total = 0;
        for (int i = 0; i < reformattedIban.length(); i++) {
            final int numericValue = Character.getNumericValue(reformattedIban.charAt(i));
            if (numericValue < 0 || numericValue > 35) {
                // FIXME IAE
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
        final String countryCode = getCountryCode(iban);
        return getBbanStructure(CountryCode.getByCode(countryCode));
    }

    private static BbanStructure getBbanStructure(final CountryCode countryCode) {
        return BbanStructure.forCountry(countryCode);
    }

    private static String extractBbanEntry(final String iban, final BbanEntryType entryType) {
        // FIXME duplicate code
        final String bban = getBban(iban);
        final BbanStructure structure = getBbanStructure(iban);
        int bbanEntryOffset = 0;
        for(final BbanStructureEntry entry : structure.getEntries()) {
            final int entryLength = entry.getLength();
            final String entryValue = bban.substring(bbanEntryOffset,
                    bbanEntryOffset + entryLength);

            bbanEntryOffset = bbanEntryOffset + entryLength;
            if(entry.getEntryType() == entryType) {
                return entryValue;
            }
        }
        return null;
    }

}
