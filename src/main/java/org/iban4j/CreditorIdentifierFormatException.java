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
 * a string to CreditorIdentifier or to validate CreditorIdentifier's string representation, but the string does not
 * have the appropriate format.
 */
public class CreditorIdentifierFormatException extends Iban4jException {

    private CreditorIdentifierViolation formatViolation;
    private Object expected;
    private Object actual;

    /**
     * Constructs a <code>CreditorIdentifierFormatException</code> with no detail message.
     */
    public CreditorIdentifierFormatException() {
        super();
    }

    /**
     * Constructs a <code>CreditorIdentifierFormatException</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public CreditorIdentifierFormatException(final String s) {
        super(s);
    }

    /**
     * Constructs a <code>CreditorIdentifierFormatException</code> with the
     * specified detail message and cause.
     *
     * @param s the detail message.
     * @param t the cause.
     */
    public CreditorIdentifierFormatException(final String s, final Throwable t) {
        super(s, t);
    }

    /**
     * Constructs a <code>CreditorIdentifierFormatException</code> with the
     * specified cause.
     *
     * @param t the cause.
     */
    public CreditorIdentifierFormatException(final Throwable t) {
        super(t);
    }

    /**
     * Constructs a <code>CreditorIdentifierFormatException</code> with the
     * specified violation, actual value, expected value and detail message.
     *
     * @param violation the violation.
     * @param actual the actual value.
     * @param expected the expected value.
     * @param s the detail message.
     */
    public CreditorIdentifierFormatException(final CreditorIdentifierViolation violation,
                               final Object actual,
                               final Object expected,
                               final String s) {
        super(s);
        this.expected = expected;
        this.actual = actual;
        this.formatViolation = violation;
    }

    /**
     * Constructs a <code>CreditorIdentifierFormatException</code> with the
     * specified violation, actual value and detail message.
     *
     * @param violation the violation.
     * @param actual the actual value.
     * @param s the detail message.
     */
    public CreditorIdentifierFormatException(final CreditorIdentifierViolation violation,
                               final Object actual,
                               final String s) {
        super(s);
        this.actual = actual;
        this.formatViolation = violation;
    }

    /**
     * Constructs a <code>CreditorIdentifierFormatException</code> with the
     * specified violation and detail message.
     *
     * @param violation the violation.
     * @param s the detail message.
     */
    public CreditorIdentifierFormatException(final CreditorIdentifierViolation violation,
                               final String s) {
        super(s);
        this.formatViolation = violation;
    }

    public CreditorIdentifierViolation getFormatViolation() {
        return formatViolation;
    }

    public Object getExpected() {
        return expected;
    }

    public Object getActual() {
        return actual;
    }

    public static enum CreditorIdentifierViolation {
        UNKNOWN,

        IBAN_FORMATTING,
        IBAN_NOT_NULL,
        IBAN_NOT_EMPTY,
        CREDITOR_IDENTIFIER_VALID_CHARACTERS,

        CHECK_DIGIT_ONLY_DIGITS,
        CHECK_DIGIT_TWO_DIGITS,

        COUNTRY_CODE_TWO_LETTERS,
        COUNTRY_CODE_UPPER_CASE_LETTERS,
        COUNTRY_CODE_EXISTS,
        COUNTRY_CODE_NOT_NULL,

        BANK_CODE_NOT_NULL,
        ACCOUNT_NUMBER_NOT_NULL

    }

}