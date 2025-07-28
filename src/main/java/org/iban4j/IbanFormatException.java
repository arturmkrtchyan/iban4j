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

import org.iban4j.bban.BbanEntryType;

/**
 * Thrown to indicate that the application has attempted to convert a string to an IBAN,
 * but that the string does not have the appropriate format.
 * <p>
 * This exception provides details about the specific format violation,
 * including expected and actual values, the BBAN entry type, and any invalid characters, where applicable.
 */
public class IbanFormatException extends Iban4jException {

  private static final long serialVersionUID = -2715142907876721085L;

  /**
   * The specific type of IBAN format violation that occurred.
   */
  private IbanFormatViolation formatViolation;

  /**
   * The expected format or value, if applicable, that the IBAN part should have conformed to.
   */
  private Object expected;

  /**
   * The actual value or part of the IBAN string that caused the format violation.
   */
  private Object actual;

  /**
   * The BBAN entry type that was involved in the format violation, if applicable.
   */
  private BbanEntryType bbanEntryType;

  /**
   * The specific character in the IBAN string that was found to be invalid, if applicable.
   */
  private char invalidCharacter;

  /**
   * Constructs a <code>IbanFormatException</code> without detail message.
   */
  public IbanFormatException() {
    super();
  }

  /**
   * Constructs a <code>IbanFormatException</code> with the specified detail message.
   *
   * @param s the detail message.
   */
  public IbanFormatException(final String s) {
    super(s);
  }

  /**
   * Constructs a <code>IbanFormatException</code> with the specified detail message and cause.
   *
   * @param s the detail message.
   * @param t the cause.
   */
  public IbanFormatException(final String s, final Throwable t) {
    super(s, t);
  }

  /**
   * Constructs a <code>IbanFormatException</code> with the specified cause.
   *
   * @param t the cause.
   */
  public IbanFormatException(final Throwable t) {
    super(t);
  }

  /**
   * Constructs a <code>IbanFormatException</code> with the specified violation, actual value,
   * expected value and detail message.
   *
   * @param violation the violation.
   * @param actual the actual value.
   * @param expected the expected value.
   * @param s the detail message.
   */
  public IbanFormatException(
      final IbanFormatViolation violation,
      final Object actual,
      final Object expected,
      final String s) {
    super(s);
    this.expected = expected;
    this.actual = actual;
    this.formatViolation = violation;
  }

  /**
   * Constructs a <code>IbanFormatException</code> with the specified violation, actual value and
   * detail message.
   *
   * @param violation the violation.
   * @param actual the actual value.
   * @param s the detail message.
   */
  public IbanFormatException(
      final IbanFormatViolation violation, final Object actual, final String s) {
    super(s);
    this.actual = actual;
    this.formatViolation = violation;
  }

  /**
   * Constructs a <code>IbanFormatException</code> with the specified violation, entryType, actual
   * value, invalidCharacter and detail message.
   *
   * @param violation the violation.
   * @param entryType the BBAN entry type related to the violation.
   * @param actual the actual value that caused the violation.
   * @param invalidCharacter the specific character that was found to be invalid.
   * @param s the detail message.
   */
  public IbanFormatException(
      final IbanFormatViolation violation,
      final BbanEntryType entryType,
      final Object actual,
      final char invalidCharacter,
      final String s) {
    super(s);
    this.actual = actual;
    this.formatViolation = violation;
    this.bbanEntryType = entryType;
    this.invalidCharacter = invalidCharacter;
  }

  /**
   * Constructs a <code>IbanFormatException</code> with the specified violation and detail message.
   *
   * @param violation the violation.
   * @param s the detail message.
   */
  public IbanFormatException(final IbanFormatViolation violation, final String s) {
    super(s);
    this.formatViolation = violation;
  }

  /**
   * Returns the specific IBAN format violation that caused this exception.
   *
   * @return the {@link IbanFormatViolation} enum constant.
   */
  public IbanFormatViolation getFormatViolation() {
    return formatViolation;
  }

  /**
   * Returns the expected format or value for the IBAN part that caused the violation.
   * This might be a regular expression, a specific length, or a set of allowed characters.
   *
   * @return an {@code Object} representing the expected value or format.
   */
  public Object getExpected() {
    return expected;
  }

  /**
   * Returns the actual value of the IBAN part that violated the format rules.
   * This provides insight into what was actually present in the invalid IBAN string.
   *
   * @return an {@code Object} representing the actual value.
   */
  public Object getActual() {
    return actual;
  }

  /**
   * Returns the specific character in the IBAN string that was found to be invalid.
   *
   * @return the invalid character.
   */
  public char getInvalidCharacter() {
    return invalidCharacter;
  }

  /**
   * Returns the BBAN entry type that was involved in the format violation.
   *
   * @return the {@link BbanEntryType} enum constant.
   */
  public BbanEntryType getBbanEntryType() {
    return bbanEntryType;
  }

  /**
   * Enum representing various types of violations that can occur
   * when validating an International Bank Account Number (IBAN) against its format rules.
   * Each constant indicates a specific reason why an IBAN might be considered invalid.
   */
  public enum IbanFormatViolation {
    /** Indicates that the specific cause of the IBAN format violation is unknown or could not be determined. */
    UNKNOWN,

    /** The overall IBAN string does not conform to the expected general formatting rules. */
    IBAN_FORMATTING,
    /** The IBAN string provided was {@code null}. */
    IBAN_NOT_NULL,
    /** The IBAN string provided was empty. */
    IBAN_NOT_EMPTY,
    /** The IBAN contains characters that are not alphanumeric (A-Z, 0-9) or space. */
    IBAN_VALID_CHARACTERS,

    /** The check digit portion of the IBAN contains non-digit characters. */
    CHECK_DIGIT_ONLY_DIGITS,
    /** The check digit portion of the IBAN does not consist of exactly two digits. */
    CHECK_DIGIT_TWO_DIGITS,

    /** The country code portion of the IBAN is not two letters. */
    COUNTRY_CODE_TWO_LETTERS,
    /** The country code portion of the IBAN does not contain only upper-case letters (A-Z). */
    COUNTRY_CODE_UPPER_CASE_LETTERS,
    /** The country code embedded in the IBAN is not a known or supported country. */
    COUNTRY_CODE_EXISTS,
    /** The country code portion of the IBAN was {@code null}. */
    COUNTRY_CODE_NOT_NULL,

    /** The BBAN (Basic Bank Account Number) portion of the IBAN has an incorrect length for the given country. */
    BBAN_LENGTH,
    /** An entry type within the BBAN structure is invalid or not recognized for the specified country. */
    BBAN_INVALID_ENTRY_TYPE,
    /** A part of the BBAN (e.g., account number) must contain only digits but was found to contain other characters. */
    BBAN_ONLY_DIGITS,
    /** A part of the BBAN (e.g., bank code) must contain only upper-case letters but was found to contain other characters. */
    BBAN_ONLY_UPPER_CASE_LETTERS,
    /** A part of the BBAN (e.g., branch code) must contain only digits or letters but was found to contain other characters. */
    BBAN_ONLY_DIGITS_OR_LETTERS,

    /** The bank code portion of the BBAN was {@code null} or empty. */
    BANK_CODE_NOT_NULL,
    /** The account number portion of the BBAN was {@code null} or empty. */
    ACCOUNT_NUMBER_NOT_NULL,
    /** The national check digit portion of the BBAN was {@code null} or empty. */
    NATIONAL_CHECK_DIGIT_NOT_NULL
  }
}
