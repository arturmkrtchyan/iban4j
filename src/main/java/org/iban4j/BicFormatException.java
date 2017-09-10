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

/**
 * Thrown to indicate that the application has attempted to convert
 * a string to Bic or to validate Bic's string representation, but the string does not
 * have the appropriate format.
 */
public class BicFormatException extends Iban4jException {

    private static final long serialVersionUID = 1764207967955765664L;

    public final BicFormatViolation formatViolation; // may be null
    public final String moreInfo; // may be null

    public static enum BicFormatViolation {
        UNKNOWN,

        BIC_NOT_NULL,
        BIC_NOT_EMPTY,
        BIC_LENGTH_8_OR_11,
        BIC_ONLY_UPPER_CASE_LETTERS,

        BRANCH_CODE_ONLY_LETTERS_OR_DIGITS,
        LOCATION_CODE_ONLY_LETTERS_OR_DIGITS,
        BANK_CODE_ONLY_LETTERS,

        LOCATION_CODE_RESTRICTION_FIRST_CHAR,
        LOCATION_CODE_RESTRICTION_SECOND_CHAR

    }

    /**
     * Constructs a <code>BicFormatException</code> with the
     * specified violation and detail message. The "moreInfo" String stays null.
     *
     * @param violation the violation (possibly null)
     * @param message the detail message  (possibly null)
     */

    public BicFormatException(BicFormatViolation violation, String message) {
        super(message);
        this.formatViolation = violation;
        this.moreInfo = null;
    }

    /**
     * Constructs a <code>BicFormatException</code> with the
     * specified violation and detail message and "moreInfo" string (may stay null)
     *
     * @param violation the violation (possibly null)
     * @param moreInfo the problematic character  (possibly null)
     * @param message the detail message  (possibly null)
     */


    public BicFormatException(BicFormatViolation violation, String moreInfo, String message) {
        super(message);
        this.formatViolation = violation;
        this.moreInfo = moreInfo;
    }

}