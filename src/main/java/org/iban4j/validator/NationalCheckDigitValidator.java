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
package org.iban4j.validator;

import org.iban4j.CountryCode;

/**
 * Interface for national check digit validation algorithms.
 *
 * <p>Each country that includes national check digits in their IBAN structure can implement this
 * interface to provide country-specific validation logic.
 *
 * <p>National check digits are additional validation digits included within the BBAN (Basic Bank
 * Account Number) portion of an IBAN, beyond the standard IBAN check digits that appear after the
 * country code.
 *
 * <h3>Implementation Guidelines:</h3>
 *
 * <ul>
 *   <li>Implementations should be stateless and thread-safe
 *   <li>Validation should be fast (typically sub-millisecond)
 *   <li>Should handle edge cases gracefully
 *   <li>Must include comprehensive JavaDoc with algorithm references
 * </ul>
 *
 * <h3>Example Implementation:</h3>
 *
 * <pre>{@code
 * public class ExampleValidator implements NationalCheckDigitValidator {
 *
 *     @Override
 *     public boolean validate(String bban, String nationalCheckDigit) {
 *         String calculated = calculate(bban);
 *         return nationalCheckDigit.equals(calculated);
 *     }
 *
 *     @Override
 *     public String calculate(String bban) {
 *         // Country-specific algorithm implementation
 *         return "42"; // Example result
 *     }
 *
 *     @Override
 *     public CountryCode getSupportedCountry() {
 *         return CountryCode.XX;
 *     }
 * }
 * }</pre>
 *
 * @author iban4j contributors
 * @since 3.3.0
 * @see org.iban4j.validator.NationalCheckDigitValidatorFactory
 */
public interface NationalCheckDigitValidator {

  /**
   * Validates the national check digit for a given BBAN.
   *
   * <p>This method should verify that the provided national check digit is correct for the given
   * BBAN according to the country's specific algorithm.
   *
   * @param bban the Basic Bank Account Number (without IBAN country code and check digits)
   * @param nationalCheckDigit the national check digit(s) to validate
   * @return {@code true} if the check digit is valid, {@code false} otherwise
   * @throws IllegalArgumentException if the BBAN format is invalid for this country
   */
  boolean validate(String bban, String nationalCheckDigit);

  /**
   * Calculates the expected national check digit for a given BBAN.
   *
   * <p>This method should implement the country-specific algorithm to calculate what the national
   * check digit should be for the provided BBAN.
   *
   * @param bban the Basic Bank Account Number (may include or exclude existing check digits
   *     depending on algorithm)
   * @return the calculated national check digit as a string
   * @throws IllegalArgumentException if the BBAN format is invalid for this country
   */
  String calculate(String bban);

  /**
   * Returns the country code this validator supports.
   *
   * <p>Each validator implementation should support exactly one country.
   *
   * @return the supported country code
   */
  CountryCode getSupportedCountry();
}
