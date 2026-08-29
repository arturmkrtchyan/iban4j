/*
 * Copyright 2016 Artur Mkrtchyan
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


import static org.iban4j.CreditorIdentifierFormatException.CreditorIdentifierViolation.*;

/**
 * Creditor Identifier Util.
 *
 */
public class CreditorIdentifierUtil {

    private static final int MOD = 97;
    private static final long MAX = 999999999;

    private static final int COUNTRY_CODE_INDEX = 0;
    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int CHECK_DIGIT_INDEX = COUNTRY_CODE_LENGTH;
    private static final int CHECK_DIGIT_LENGTH = 2;
    private static final int BUSINESS_CODE_INDEX = CHECK_DIGIT_INDEX + CHECK_DIGIT_LENGTH;
    private static final int BUSINESS_CODE_LENGTH = 3;
    private static final int NATIONAL_IDENTIFIER_INDEX = BUSINESS_CODE_INDEX + BUSINESS_CODE_LENGTH;

    /**
     * Validates CreditorIdentifier.
     *
     * @param creditorIdentifier to be validated.
     * @throws CreditorIdentifierFormatException if creditorIdentifier is invalid.
     *         UnsupportedCountryException if creditorIdentifier's country is not supported.
     *         InvalidCheckDigitException if creditorIdentifier has invalid check digit.
     */
    public static void validate(final String creditorIdentifier) throws CreditorIdentifierFormatException,
            InvalidCheckDigitException, UnsupportedCountryException {
        try {
            //validateEmpty(creditorIdentifier);
            //validateCountryCode(creditorIdentifier);
            //validateCheckDigitPresence(creditorIdentifier);
            //validateCheckDigit(creditorIdentifier);
        } catch (Iban4jException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new CreditorIdentifierFormatException(UNKNOWN, e.getMessage());
        }
    }

    /**
     * Calculates CreditorIdentifier Check Digit.
     *
     * @param creditorIdentifier string value
     * @throws CreditorIdentifierFormatException if creditorIdentifier contains invalid character.
     *
     * @return check digit as String
     */
    public static String calculateCheckDigit(final String creditorIdentifier) throws CreditorIdentifierFormatException {
        final String reformattedCreditorIdentifier = replaceCheckDigit(creditorIdentifier,
                CreditorIdentifier.DEFAULT_CHECK_DIGIT);
        final String creditorIdentifierWithoutBusinessCode = removeBusinessCode(reformattedCreditorIdentifier);
        final int modResult = calculateMod(creditorIdentifierWithoutBusinessCode);
        final int checkDigitIntValue = (98 - modResult);
        final String checkDigit = Integer.toString(checkDigitIntValue);
        return checkDigitIntValue > 9 ? checkDigit : "0" + checkDigit;
    }

    /**
     * Returns creditorIdentifier's check digit.
     *
     * @param creditorIdentifier String
     * @return checkDigit String
     */
    public static String getCheckDigit(final String creditorIdentifier) {
        return creditorIdentifier.substring(CHECK_DIGIT_INDEX,
                CHECK_DIGIT_INDEX + CHECK_DIGIT_LENGTH);
    }

    /**
     * Returns creditorIdentifier's country code.
     *
     * @param creditorIdentifier String
     * @return countryCode String
     */
    public static String getCountryCode(final String creditorIdentifier) {
        return creditorIdentifier.substring(COUNTRY_CODE_INDEX,
                COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
    }

    /**
     * Returns creditorIdentifier's country code and check digit.
     *
     * @param creditorIdentifier String
     * @return countryCodeAndCheckDigit String
     */
    public static String getCountryCodeAndCheckDigit(final String creditorIdentifier) {
        return creditorIdentifier.substring(COUNTRY_CODE_INDEX,
                COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH);
    }

    /**
     * Returns creditorIdentifier's country code and check digit.
     *
     * @param creditorIdentifier String
     * @return countryCodeAndCheckDigit String
     */
    public static String getCreditorBusinessCode(final String creditorIdentifier) {
        return creditorIdentifier.substring(BUSINESS_CODE_INDEX,
                BUSINESS_CODE_INDEX + BUSINESS_CODE_LENGTH);
    }

    /**
     * Returns creditorIdentifier's country code and check digit.
     *
     * @param creditorIdentifier String
     * @return countryCodeAndCheckDigit String
     */
    public static String getNationalIdentifier(final String creditorIdentifier) {
        return creditorIdentifier.substring(NATIONAL_IDENTIFIER_INDEX);
    }

    /**
     * Returns creditorIdentifier's country code and check digit.
     *
     * @param creditorIdentifier String
     * @return countryCodeAndCheckDigit String
     */
    public static String getCreditorBusinessCodeAndNationalIdentifier(final String creditorIdentifier) {
        return creditorIdentifier.substring(BUSINESS_CODE_INDEX);
    }

    /**
     * Returns an creditorIdentifier with replaced check digit.
     *
     * @param creditorIdentifier The creditorIdentifier
     * @return The creditorIdentifier without the check digit
     */
    static String replaceCheckDigit(final String creditorIdentifier, final String checkDigit) {
        return getCountryCode(creditorIdentifier) + checkDigit +
                getCreditorBusinessCodeAndNationalIdentifier(creditorIdentifier);
    }

    /**
     * Returns an creditorIdentifier with replaced check digit.
     *
     * @param creditorIdentifier The creditorIdentifier
     * @return The creditorIdentifier without the check digit
     */
    static String removeBusinessCode(final String creditorIdentifier) {
        return getCountryCode(creditorIdentifier) + getCheckDigit(creditorIdentifier) +
                getNationalIdentifier(creditorIdentifier);
    }


    /**
     * Calculates Modulo 97.
     *
     * @param creditorIdentifier String value
     * @return modulo 97
     */
    private static int calculateMod(final String creditorIdentifier) {
        final String reformattedCreditorIdentifier = getCreditorBusinessCodeAndNationalIdentifier(creditorIdentifier) +
                getCountryCodeAndCheckDigit(creditorIdentifier);
        System.out.println(reformattedCreditorIdentifier);
        long total = 0;
        for (int i = 0; i < reformattedCreditorIdentifier.length(); i++) {
            final int numericValue = Character.getNumericValue(reformattedCreditorIdentifier.charAt(i));
            if (numericValue < 0 || numericValue > 35) {
                throw new CreditorIdentifierFormatException(CREDITOR_IDENTIFIER_VALID_CHARACTERS,
                        reformattedCreditorIdentifier.charAt(i),
                        String.format("Invalid Character[%d] = '%d'", i, numericValue));
            }
            total = (numericValue > 9 ? total * 100 : total * 10) + numericValue;

            if (total > MAX) {
                total = (total % MOD);
            }

        }
        return (int) (total % MOD);
    }

    public static void main(String[] args) {
        String creditorIdentifier = "DE98ZZZ09999999999";

        System.out.println(getCountryCode(creditorIdentifier));
        System.out.println(getCreditorBusinessCode(creditorIdentifier));
        System.out.println(getNationalIdentifier(creditorIdentifier));
        System.out.println(getCheckDigit(creditorIdentifier));

        System.out.println(calculateCheckDigit("DE00ZZZ09999999999"));
    }


}
