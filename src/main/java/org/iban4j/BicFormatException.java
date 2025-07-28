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
 * Thrown to indicate that the application has attempted to convert a string to Bic or to validate
 * Bic's string representation, but the string does not have the appropriate format.
 * <p>
 * This exception provides details about the specific format violation,
 * including expected and actual values where applicable.
 */
public class BicFormatException extends Iban4jException {

  private static final long serialVersionUID = 1764207967955765664L;

  /**
   * The specific type of BIC format violation that occurred.
   */
  private BicFormatViolation formatViolation;

  /**
   * The expected value or format, if applicable, that the BIC part should have conformed to.
   */
  private Object expected;

  /**
   * The actual value or part of the BIC string that caused the format violation.
   */
  private Object actual;

  /**
   * Constructs a <code>BicFormatException</code> with no detail message.
   */
  public BicFormatException() {
    super();
  }

  /**
   * Constructs a <code>BicFormatException</code> with the specified detail message.
   *
   * @param s the detail message.
   */
  public BicFormatException(final String s) {
    super(s);
  }

  /**
   * Constructs a <code>BicFormatException</code> with the specified detail message and cause.
   *
   * @param s the detail message.
   * @param t the cause.
   */
  public BicFormatException(final String s, final Throwable t) {
    super(s, t);
  }

  /**
   * Constructs a <code>BicFormatException</code> with the specified violation, actual value,
   * expected value and detail message.
   *
   * @param violation the violation.
   * @param actual the actual value.
   * @param expected the expected value.
   * @param s the detail message.
   */
  public BicFormatException(
      BicFormatViolation violation, Object actual, Object expected, final String s) {
    super(s);
    this.actual = actual;
    this.expected = expected;
    this.formatViolation = violation;
  }

  /**
   * Constructs a <code>BicFormatException</code> with the specified violation and detail message.
   *
   * @param violation the violation.
   * @param s the detail message.
   */
  public BicFormatException(BicFormatViolation violation, final String s) {
    super(s);
    this.formatViolation = violation;
  }

  /**
   * Constructs a <code>BicFormatException</code> with the specified violation, actual value and
   * detail message.
   *
   * @param violation the violation.
   * @param actual the actual value.
   * @param s the detail message.
   */
  public BicFormatException(BicFormatViolation violation, Object actual, final String s) {
    super(s);
    this.actual = actual;
    this.formatViolation = violation;
  }

  /**
   * Constructs a <code>BicFormatException</code> with the specified cause.
   *
   * @param t the cause.
   */
  public BicFormatException(final Throwable t) {
    super(t);
  }

  /**
   * Returns the specific BIC format violation that caused this exception.
   *
   * @return the {@link BicFormatViolation} enum constant.
   */
  public BicFormatViolation getFormatViolation() {
    return formatViolation;
  }

  /**
   * Returns the expected format or value for the BIC part that caused the violation.
   * This might be a regular expression, a specific length, or a set of allowed characters.
   *
   * @return an {@code Object} representing the expected value or format.
   */
  public Object getExpected() {
    return expected;
  }

  /**
   * Returns the actual value of the BIC part that violated the format rules.
   * This provides insight into what was actually present in the invalid BIC string.
   *
   * @return an {@code Object} representing the actual value.
   */
  public Object getActual() {
    return actual;
  }

  /**
   * Enum representing various types of violations that can occur
   * when validating a Business Identifier Code (BIC) against its format rules.
   * <p>
   * Each constant indicates a specific reason why a BIC might be considered invalid.
   */
  public enum BicFormatViolation {
    /**
     * Indicates that the specific cause of the BIC format violation is unknown
     * or could not be determined. This is a fallback for unhandled cases.
     */
    UNKNOWN,

    /**
     * The BIC string provided was {@code null}.
     */
    BIC_NOT_NULL,
    /**
     * The BIC string provided was empty.
     */
    BIC_NOT_EMPTY,
    /**
     * The BIC's length is not 8 or 11 characters, which are the only valid lengths for a BIC.
     */
    BIC_LENGTH_8_OR_11,
    /**
     * The entire BIC string contains characters that are not upper-case letters (A-Z).
     * This applies to the overall structure before specific parts are checked.
     */
    BIC_ONLY_UPPER_CASE_LETTERS,
    /**
     * The branch code portion of the BIC contains characters that are not letters (A-Z) or digits (0-9).
     */
    BRANCH_CODE_ONLY_LETTERS_OR_DIGITS,
    /**
     * The location code portion of the BIC contains characters that are not
     * letters (A-Z) or digits (0-9).
     */
    LOCATION_CODE_ONLY_LETTERS_OR_DIGITS,
    /**
     * The bank code portion of the BIC contains characters that are not
     * alphanumeric (A-Z, 0-9).
     */
    BANK_CODE_ONLY_ALPHANUMERIC,
    /**
     * The country code portion of the BIC contains characters that are not
     * upper-case letters (A-Z).
     */
    COUNTRY_CODE_ONLY_UPPER_CASE_LETTERS
  }

}
