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
 * Thrown to indicate that Iban's check digit is invalid
 */
public class InvalidCheckDigitException extends Iban4jException {

    private static final long serialVersionUID = -9222165415290480187L;

    private String actual;
    private String expected;

    /**
     * Constructs a <code>InvalidCheckDigitException</code> with no detail message.
     */
    public InvalidCheckDigitException() {
        super();
    }

    /**
     * Constructs a <code>InvalidCheckDigitException</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public InvalidCheckDigitException(final String s) {
        super(s);
    }

    /**
     * Constructs a <code>InvalidCheckDigitException</code> with the
     * specified actual, expected and detail message.
     *
     * @param actual the actual check digit.
     * @param expected the expected check digit.
     * @param s the detail message.
     */
    public InvalidCheckDigitException(final String actual, final String expected, final String s) {
        super(s);
        this.actual = actual;
        this.expected = expected;
    }

    /**
     * Constructs a <code>InvalidCheckDigitException</code> with the
     * specified detail message and cause.
     *
     * @param s the detail message.
     * @param t the cause.
     */
    public InvalidCheckDigitException(final String s, final Throwable t) {
        super(s, t);
    }

    /**
     * Constructs a <code>InvalidCheckDigitException</code> with the
     * specified cause.
     *
     * @param t the cause.
     */
    public InvalidCheckDigitException(final Throwable t) {
        super(t);
    }

    public String getActual() {
        return actual;
    }

    public String getExpected() {
        return expected;
    }
}
