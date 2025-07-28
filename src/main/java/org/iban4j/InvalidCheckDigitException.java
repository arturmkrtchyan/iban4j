/*
 * Copyright 2013 Artur Mkrtchyan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iban4j;

/**
 * Thrown to indicate that an IBAN's check digit is invalid.
 * This exception is typically thrown when the calculated check digit
 * does not match the check digit present in the IBAN string.
 */
public class InvalidCheckDigitException extends Iban4jException {

  private static final long serialVersionUID = -9222165415290480187L;

  /**
   * The actual (calculated) check digit.
   */
  private String actual;

  /**
   * The expected check digit, as present in the IBAN string.
   */
  private String expected;

  /**
   * Constructs a new {@code InvalidCheckDigitException} with no detail message.
   */
  public InvalidCheckDigitException() {
    super();
  }

  /**
   * Constructs a new {@code InvalidCheckDigitException} with the specified detail message.
   *
   * @param s The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
   */
  public InvalidCheckDigitException(final String s) {
    super(s);
  }

  /**
   * Constructs a new {@code InvalidCheckDigitException} with the specified actual check digit,
   * expected check digit, and detail message.
   *
   * @param actual The actual (calculated) check digit that was found.
   * @param expected The expected check digit that should have been found in the IBAN string.
   * @param s The detail message.
   */
  public InvalidCheckDigitException(final String actual, final String expected, final String s) {
    super(s);
    this.actual = actual;
    this.expected = expected;
  }

  /**
   * Constructs a new {@code InvalidCheckDigitException} with the specified detail message and cause.
   *
   * @param s The detail message.
   * @param t The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
   * (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
   */
  public InvalidCheckDigitException(final String s, final Throwable t) {
    super(s, t);
  }

  /**
   * Constructs a new {@code InvalidCheckDigitException} with the specified cause and a detail message
   * of {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
   *
   * @param t The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
   * (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
   */
  public InvalidCheckDigitException(final Throwable t) {
    super(t);
  }

  /**
   * Returns the actual (calculated) check digit that was found.
   *
   * @return A {@link String} representing the actual check digit.
   */
  public String getActual() {
    return actual;
  }

  /**
   * Returns the expected check digit that should have been found in the IBAN string.
   *
   * @return A {@link String} representing the expected check digit.
   */
  public String getExpected() {
    return expected;
  }
}
