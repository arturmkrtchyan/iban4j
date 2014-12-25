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

    private BicFormatViolation formatViolation;

    private Object expected;
    private Object actual;

    /**
     * Constructs a <code>BicFormatException</code> with no detail message.
     */
    public BicFormatException() {
        super();
    }

    /**
     * Constructs a <code>BicFormatException</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public BicFormatException(final String s) {
        super(s);
    }

    /**
     * Constructs a <code>BicFormatException</code> with the
     * specified detail message and cause.
     *
     * @param s the detail message.
     * @param t the cause.
     */
    public BicFormatException(final String s, final Throwable t) {
        super(s, t);
    }

    /**
     * Constructs a <code>BicFormatException</code> with the
     * specified violation, actual value, expected value and detail message.
     *
     * @param violation the violation.
     * @param actual the actual value.
     * @param expected the expected value.
     * @param s the detail message.
     */
    public BicFormatException(BicFormatViolation violation,
                              Object actual, Object expected, final String s) {
        super(s);
        this.actual = actual;
        this.expected = expected;
        this.formatViolation = violation;
    }

    /**
     * Constructs a <code>BicFormatException</code> with the
     * specified violation and detail message.
     *
     * @param violation the violation.
     * @param s the detail message.
     */
    public BicFormatException(BicFormatViolation violation, final String s) {
        super(s);
        this.formatViolation = violation;
    }

    /**
     * Constructs a <code>BicFormatException</code> with the
     * specified violation, actual value and detail message.
     *
     * @param violation the violation.
     * @param actual the actual value.
     * @param s the detail message.
     */
    public BicFormatException(BicFormatViolation violation, Object actual,
                              final String s) {
        super(s);
        this.actual = actual;
        this.formatViolation = violation;
    }

    /**
     * Constructs a <code>BicFormatException</code> with the
     * specified cause.
     *
     * @param t the cause.
     */
    public BicFormatException(final Throwable t) {
        super(t);
    }

    public BicFormatViolation getFormatViolation() {
        return formatViolation;
    }

    public Object getExpected() {
        return expected;
    }

    public Object getActual() {
        return actual;
    }

    public static enum BicFormatViolation {
        UNKNOWN,

        BIC_NOT_NULL,
        BIC_NOT_EMPTY,
        BIC_LENGTH_8_OR_11,
        BIC_ONLY_UPPER_CASE_LETTERS,

        BRANCH_CODE_ONLY_LETTERS_OR_DIGITS,
        LOCATION_CODE_ONLY_LETTERS_OR_DIGITS,
        BANK_CODE_ONLY_LETTERS,
        COUNTRY_CODE_ONLY_UPPER_CASE_LETTERS
    }
}