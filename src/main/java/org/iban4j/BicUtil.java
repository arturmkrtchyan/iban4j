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

import org.iban4j.support.Assert;

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
     */
    public static void validate(final String bic) throws BicFormatException {

        try {
            Assert.notNull(bic, "Null can't be a valid Bic.");
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
            throw new BicFormatException(e.getMessage());
        }
    }



    private static void validateLength(final String bic) {
        if(bic.length() != BIC8_LENGTH && bic.length() != BIC11_LENGTH) {
            throw new BicFormatException(String.format("Bic length must be %d or %d", BIC8_LENGTH, BIC11_LENGTH));
        }
    }

    private static void validateCase(final String bic) {
        if(!bic.equals(bic.toUpperCase())) {
            throw new BicFormatException("Bic must contain only upper case letters.");
        }
    }

    private static void validateBankCode(final String bic) {
        String bankCode = getBankCode(bic);
        for(final char ch : bankCode.toCharArray()) {
            if(!Character.isLetter(ch)) {
                throw new BicFormatException("Bank code must contain only letters.");
            }
        }
    }

    private static void validateCountryCode(final String bic) {
        final String countryCode = getCountryCode(bic);
        if(countryCode.trim().length() < COUNTRY_CODE_LENGTH ||
                !countryCode.equals(countryCode.toUpperCase()) ||
                !Character.isLetter(countryCode.charAt(0)) ||
                !Character.isLetter(countryCode.charAt(1))) {
            throw new BicFormatException("Bic country code must contain upper case letters");
        }
        Assert.notNull(CountryCode.getByCode(countryCode),
                String.format("Bic contains non existing country code: %s", countryCode));
    }

    private static void validateLocationCode(final String bic) {
        final String locationCode = getLocationCode(bic);
        for(char ch : locationCode.toCharArray()) {
            if(!Character.isLetterOrDigit(ch)) {
                throw new BicFormatException("Location code must contain only letters or digits.");
            }
        }
    }

    private static void validateBranchCode(final String bic) {
        final String branchCode = getBranchCode(bic);
        for(final char ch : branchCode.toCharArray()) {
            if(!Character.isLetterOrDigit(ch)) {
                throw new BicFormatException("Branch code must contain only letters or digits.");
            }
        }
    }

    static String getBankCode(final String bic) {
        return bic.substring(BANK_CODE_INDEX, BANK_CODE_INDEX + BANK_CODE_LENGTH);
    }

    static String getCountryCode(final String bic) {
        return bic.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
    }

    static String getLocationCode(final String bic) {
        return bic.substring(LOCATION_CODE_INDEX, LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH);
    }

    static String getBranchCode(final String bic) {
        return bic.substring(BRANCH_CODE_INDEX, BRANCH_CODE_INDEX + BRANCH_CODE_LENGTH);
    }

    static boolean hasBranchCode(final String bic) {
        return bic.length() == BIC11_LENGTH;
    }
}
