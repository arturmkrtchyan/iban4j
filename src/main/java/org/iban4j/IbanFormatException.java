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
 * a string to Iban, but that the string does not
 * have the appropriate format.
 */
public class IbanFormatException extends RuntimeException {

    private IbanFormatViolation formatViolation;
    private Object expected;
    private Object actual;

    private static final long serialVersionUID = 4385766780446382504L;

    /**
     * Constructs a <code>IbanFormatException</code> with no detail message.
     */
    public IbanFormatException() {
        super();
    }

    /**
     * Constructs a <code>IbanFormatException</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public IbanFormatException(final String s) {
        super(s);
    }

    /**
     * Constructs a <code>IbanFormatException</code> with the
     * specified detail message and cause.
     *
     * @param s the detail message.
     * @param t the cause.
     */
    public IbanFormatException(final String s, final Throwable t) {
        super(s, t);
    }

    /**
     * Constructs a <code>IbanFormatException</code> with the
     * specified cause.
     *
     * @param t the cause.
     */
    public IbanFormatException(final Throwable t) {
        super(t);
    }

    /**
     * Constructs a <code>IbanFormatException</code> with the
     * specified violation, actual value, expected value and detail message.
     *
     * @param s the detail message.
     */
    public IbanFormatException(IbanFormatViolation violation, Object actual,
                               Object expected, final String s) {
        super(s);
        this.expected = expected;
        this.actual = actual;
        this.formatViolation = violation;
    }

    /**
     * Constructs a <code>IbanFormatException</code> with the
     * specified violation and detail message.
     *
     * @param s the detail message.
     */
    public IbanFormatException(IbanFormatViolation violation, final String s) {
        super(s);
        this.formatViolation = violation;
    }

    public IbanFormatViolation getFormatViolation() {
        return formatViolation;
    }

    public Object getExpected() {
        return expected;
    }

    public Object getActual() {
        return actual;
    }

    public static enum IbanFormatViolation {
        NULL,
        EMPTY,
        LENGTH,
        TWO_CHAR_COUNTRY_CODE,
        UPPER_CASE_LETTERS_ONLY;

    }
}