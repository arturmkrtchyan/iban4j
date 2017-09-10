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

import static org.iban4j.BicFormatException.BicFormatViolation.*;

/**
 * Bic Utility Class
 */
public class BicUtil {

    private static final int BIC8_LENGTH = 8;
    private static final int BIC11_LENGTH = 11;

    private static final int BANK_CODE_INDEX = 0;
    private static final int BANK_CODE_LENGTH = 4;
    private static final int COUNTRY_CODE_INDEX = BANK_CODE_INDEX + BANK_CODE_LENGTH;
    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int LOCATION_CODE_INDEX = COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH;
    private static final int LOCATION_CODE_LENGTH = 2;
    private static final int BRANCH_CODE_INDEX = LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH;
    private static final int BRANCH_CODE_LENGTH = 3;

    /**
     * Validates bic.
     *
     * @param bic to be validated.
     * @throws BicFormatException if bic is invalid.
     *         UnsupportedCountryException if bic's country is not supported.
     */
    public static void validate(final String bic) throws BicFormatException,
            UnsupportedCountryException {
        try {
            validateEmpty(bic);
            validateLength(bic);
            validateCase(bic);

            validateBankCode(bic);
            validateCountryCode(bic);
            validateLocationCode(bic);

            if(hasBranchCode(bic)) {
                validateBranchCode(bic);
            }
        } catch (UnsupportedCountryException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new BicFormatException(UNKNOWN, e.getMessage());
        }
    }

    private static void validateEmpty(final String bic) {
        if(bic == null) {
            throw new BicFormatException(BIC_NOT_NULL,
                    "Null can't be a valid BIC.");
        }
        if(bic.isEmpty()) {
            throw new BicFormatException(BIC_NOT_EMPTY,
                    "Empty string can't be a valid BIC.");
        }
    }

    private static void validateLength(final String bic) {
        if(bic.length() != BIC8_LENGTH && bic.length() != BIC11_LENGTH) {
            throw new BicFormatException(BIC_LENGTH_8_OR_11,
                    String.format("BIC length must be %d or %d, but is %d in BIC '%s'.",
                            BIC8_LENGTH, BIC11_LENGTH, bic.length(), bic));
        }
    }

    private static void validateCase(final String bic) {
        if(!bic.equals(bic.toUpperCase())) {
            throw new BicFormatException(BIC_ONLY_UPPER_CASE_LETTERS,
                    "BIC must contain only upper case letters but is '" + bic + "'.");
        }
    }

    private static void validateBankCode(final String bic) {
        String bankCode = getBankCode(bic);
        for(final char ch : bankCode.toCharArray()) {
            if(!Character.isLetter(ch)) {
                throw new BicFormatException(BANK_CODE_ONLY_LETTERS, "" + ch,
                        "Bank code must contain only letters but is '" + bankCode + "' in BIC '" + bic + "'.");
            }
        }
    }

    private static void validateCountryCode(final String bic) {
        final String countryCode = getCountryCode(bic);
        if(CountryCode.getByCode(countryCode) == null) {
            throw new UnsupportedCountryException(countryCode,
                    "Country code '" + countryCode + "' is not supported in BIC '" + bic + "'.");
        }
    }

    private static void validateLocationCode(final String bic) {
        final String locationCode = getLocationCode(bic);
        for (int i=0;i<LOCATION_CODE_LENGTH;i++) {
            char ch = locationCode.charAt(i);
            if(!Character.isLetterOrDigit(ch)) {
                throw new BicFormatException(LOCATION_CODE_ONLY_LETTERS_OR_DIGITS,
                        "" + ch, "Location code must contain only letters or digits but is '" + locationCode + "' in BIC '" + bic + "'.");
            }
            if (i == 0 && (ch == '0' || ch == '1')) {
                throw new BicFormatException(LOCATION_CODE_RESTRICTION_FIRST_CHAR,
                        "" + ch, "Location code's first letter must not be digits '0' or '1' in '" + locationCode + "' in BIC '" + bic + "'.");
            }
            if (i == 1 && (ch == 'O')) {
                throw new BicFormatException(LOCATION_CODE_RESTRICTION_SECOND_CHAR,
                        "" + ch, "Location code's second letter must not be 'O' (the letter 'oh') in '" + locationCode + "' in BIC '" + bic + "'.");
            }
        }
    }

    private static void validateBranchCode(final String bic) {
        final String branchCode = getBranchCode(bic);
        for (int i=0;i<BRANCH_CODE_LENGTH;i++) {
            char ch = branchCode.charAt(i);
            if(!Character.isLetterOrDigit(ch)) {
                throw new BicFormatException(BRANCH_CODE_ONLY_LETTERS_OR_DIGITS,
                        "" + ch, "Branch code must contain only letters or digits but is '" + branchCode + "' in BIC '" + bic + "'.");
            }
        }
    }

    static String getBankCode(final String bic) {
        // may throw if "bic" string does not have enough characters
        return bic.substring(BANK_CODE_INDEX, BANK_CODE_INDEX + BANK_CODE_LENGTH);
    }

    static String getCountryCode(final String bic) {
        // may throw if "bic" string does not have enough characters
        return bic.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
    }

    static String getLocationCode(final String bic) {
        // may throw if "bic" string does not have enough characters
        return bic.substring(LOCATION_CODE_INDEX, LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH);
    }

    static String getBranchCode(final String bic) {
        // may throw if "bic" string does not have enough characters
        return bic.substring(BRANCH_CODE_INDEX, BRANCH_CODE_INDEX + BRANCH_CODE_LENGTH);
    }

    static boolean hasBranchCode(final String bic) {
        return bic.length() == BIC11_LENGTH;
    }
}
