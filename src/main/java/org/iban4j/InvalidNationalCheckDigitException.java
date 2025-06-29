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
 * Exception thrown when national check digit validation fails.
 *
 * <p>This exception provides detailed information about the validation failure, including the
 * actual and expected check digits, and the country code.
 *
 * <h3>Usage Example:</h3>
 *
 * <pre>{@code
 * try {
 *     IbanValidationUtil.validateWithNationalCheckDigit("ES9121000418460200051332");
 * } catch (InvalidNationalCheckDigitException e) {
 *     System.out.printf("Validation failed for %s: Expected %s, got %s%n",
 *         e.getCountryCode(), e.getExpectedCheckDigit(), e.getActualCheckDigit());
 * }
 * }</pre>
 *
 * @author iban4j contributors
 * @since 3.3.0
 * @see IbanValidationUtil
 */
public class InvalidNationalCheckDigitException extends Iban4jException {

  private static final long serialVersionUID = 1L;

  private final String actualCheckDigit;
  private final String expectedCheckDigit;
  private final CountryCode countryCode;

  /**
   * Constructs an InvalidNationalCheckDigitException with the specified detail message.
   *
   * @param message the detail message explaining the validation failure
   * @param actualCheckDigit the actual check digit found in the IBAN
   * @param expectedCheckDigit the expected check digit based on calculation
   * @param countryCode the country code for which validation failed
   */
  public InvalidNationalCheckDigitException(
      String message, String actualCheckDigit, String expectedCheckDigit, CountryCode countryCode) {
    super(message);
    this.actualCheckDigit = actualCheckDigit;
    this.expectedCheckDigit = expectedCheckDigit;
    this.countryCode = countryCode;
  }

  /**
   * Returns the actual check digit that was found in the IBAN.
   *
   * @return the actual check digit
   */
  public String getActualCheckDigit() {
    return actualCheckDigit;
  }

  /**
   * Returns the expected check digit based on the algorithm calculation.
   *
   * @return the expected check digit
   */
  public String getExpectedCheckDigit() {
    return expectedCheckDigit;
  }

  /**
   * Returns the country code for which the validation failed.
   *
   * @return the country code
   */
  public CountryCode getCountryCode() {
    return countryCode;
  }
}
