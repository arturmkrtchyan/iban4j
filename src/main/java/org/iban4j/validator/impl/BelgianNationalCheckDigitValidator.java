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
package org.iban4j.validator.impl;

import org.iban4j.CountryCode;
import org.iban4j.validator.NationalCheckDigitValidator;

/**
 * Belgian national check digit validator using MOD 97 algorithm.
 *
 * <p>The Belgian BBAN format is: <strong>BBBCCCCCCCDD</strong> where:
 *
 * <ul>
 *   <li><strong>B</strong> = Bank code (3 digits)
 *   <li><strong>C</strong> = Account number (7 digits)
 *   <li><strong>D</strong> = Check digits (2 digits) - validated by this class
 * </ul>
 *
 * <p>The algorithm calculates MOD 97 of the bank code + account number. If the remainder is 0, the
 * check digit is 97, otherwise it's the remainder itself.
 *
 * <h3>Algorithm:</h3>
 *
 * <ol>
 *   <li>Concatenate bank code (3 digits) + account number (7 digits)
 *   <li>Calculate the number modulo 97
 *   <li>If remainder is 0, check digit is 97; otherwise check digit is the remainder
 * </ol>
 *
 * <h3>Example:</h3>
 *
 * <pre>{@code
 * // IBAN: BE68539007547034
 * // BBAN: 539007547034
 * // Bank: 539, Account: 0075470, Check: 34
 *
 * BelgianNationalCheckDigitValidator validator = new BelgianNationalCheckDigitValidator();
 * boolean isValid = validator.validate("539007547034", "34");
 * }</pre>
 *
 * <h3>References:</h3>
 *
 * <ul>
 *   <li><a href="https://www.nbb.be/">National Bank of Belgium</a>
 *   <li>Belgian Banking Association standards
 * </ul>
 *
 * @author iban4j contributors
 * @since 3.3.0
 * @see NationalCheckDigitValidator
 * @see CountryCode#BE
 */
public class BelgianNationalCheckDigitValidator implements NationalCheckDigitValidator {

  private static final int EXPECTED_BBAN_LENGTH = 12;
  private static final int CHECK_DIGIT_LENGTH = 2;
  private static final int MODULUS = 97;

  @Override
  public boolean validate(String bban, String nationalCheckDigit) {
    if (nationalCheckDigit == null || nationalCheckDigit.length() != CHECK_DIGIT_LENGTH) {
      return false;
    }

    if (!nationalCheckDigit.matches("\\d{2}")) {
      return false;
    }

    try {
      String calculatedCheckDigit = calculate(bban);
      return nationalCheckDigit.equals(calculatedCheckDigit);
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public String calculate(String bban) {
    validateBbanFormat(bban);

    // Belgian BBAN format: BBBCCCCCCCDD (3 bank + 7 account + 2 check = 12)
    String bankCode = bban.substring(0, 3);
    String accountNumber = bban.substring(3, 10);

    String numberToCheck = bankCode + accountNumber;

    try {
      long number = Long.parseLong(numberToCheck);
      int remainder = (int) (number % MODULUS);

      // Belgian rule: if remainder is 0, use 97
      int checkDigit = remainder == 0 ? MODULUS : remainder;

      return String.format("%02d", checkDigit);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "Invalid number format in Belgian BBAN: " + numberToCheck, e);
    }
  }

  @Override
  public CountryCode getSupportedCountry() {
    return CountryCode.BE;
  }

  /**
   * Validates the BBAN format for Belgium.
   *
   * @param bban the BBAN to validate
   * @throws IllegalArgumentException if the BBAN format is invalid
   */
  private void validateBbanFormat(String bban) {
    if (bban == null) {
      throw new IllegalArgumentException("Belgian BBAN cannot be null");
    }

    if (bban.length() < EXPECTED_BBAN_LENGTH) {
      throw new IllegalArgumentException(
          String.format(
              "Belgian BBAN must be at least %d characters, got %d",
              EXPECTED_BBAN_LENGTH, bban.length()));
    }

    // First 12 characters must be digits
    String numberPart = bban.substring(0, 12);
    if (!numberPart.matches("\\d{12}")) {
      throw new IllegalArgumentException("Belgian BBAN must contain 12 digits");
    }
  }
}
