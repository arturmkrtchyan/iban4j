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
 * Portuguese national check digit validator using MOD 97 algorithm.
 *
 * <p>The Portuguese BBAN format is: <strong>BBBBSSSSCCCCCCCCCCDD</strong> where:
 *
 * <ul>
 *   <li><strong>B</strong> = Bank code (4 digits)
 *   <li><strong>S</strong> = Branch code (4 digits)
 *   <li><strong>C</strong> = Account number (11 digits)
 *   <li><strong>D</strong> = Check digits (2 digits) - validated by this class
 * </ul>
 *
 * <p>The algorithm uses a variant of MOD 97 similar to IBAN check digits, calculating 98 minus the
 * remainder of the division by 97.
 *
 * <h3>Algorithm:</h3>
 *
 * <ol>
 *   <li>Concatenate bank code + branch code + account number
 *   <li>Calculate the number modulo 97
 *   <li>Check digit = 98 - remainder
 * </ol>
 *
 * <h3>Example:</h3>
 *
 * <pre>{@code
 * // IBAN: PT50000201231234567890154
 * // BBAN: 000201231234567890154
 * // Bank: 0002, Branch: 0123, Account: 12345678901, Check: 54
 *
 * PortugueseNationalCheckDigitValidator validator = new PortugueseNationalCheckDigitValidator();
 * boolean isValid = validator.validate("000201231234567890154", "54");
 * }</pre>
 *
 * <h3>References:</h3>
 *
 * <ul>
 *   <li><a href="https://www.bportugal.pt/">Banco de Portugal</a>
 *   <li>Portuguese Banking Association (APB) standards
 * </ul>
 *
 * @author iban4j contributors
 * @since 3.3.0
 * @see NationalCheckDigitValidator
 * @see CountryCode#PT
 */
public class PortugueseNationalCheckDigitValidator implements NationalCheckDigitValidator {

  private static final int EXPECTED_BBAN_LENGTH = 21;
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

    // Portuguese BBAN format: BBBBSSSSCCCCCCCCCCDD
    String bankCode = bban.substring(0, 4);
    String branchCode = bban.substring(4, 8);
    String accountNumber = bban.substring(8, 19);

    String numberToCheck = bankCode + branchCode + accountNumber;

    try {
      long number = Long.parseLong(numberToCheck);
      int remainder = (int) (number % MODULUS);

      // Portuguese algorithm: 98 - remainder
      int checkDigit = 98 - remainder;

      return String.format("%02d", checkDigit);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "Invalid number format in Portuguese BBAN: " + numberToCheck, e);
    }
  }

  @Override
  public CountryCode getSupportedCountry() {
    return CountryCode.PT;
  }

  /**
   * Validates the BBAN format for Portugal.
   *
   * @param bban the BBAN to validate
   * @throws IllegalArgumentException if the BBAN format is invalid
   */
  private void validateBbanFormat(String bban) {
    if (bban == null) {
      throw new IllegalArgumentException("Portuguese BBAN cannot be null");
    }

    if (bban.length() < EXPECTED_BBAN_LENGTH) {
      throw new IllegalArgumentException(
          String.format(
              "Portuguese BBAN must be at least %d characters, got %d",
              EXPECTED_BBAN_LENGTH, bban.length()));
    }

    // First 19 characters must be digits (bank + branch + account)
    String numberPart = bban.substring(0, 19);
    if (!numberPart.matches("\\d{19}")) {
      throw new IllegalArgumentException(
          "Portuguese bank code, branch code, and account number must be digits");
    }

    // Check digits (positions 19-20) must be digits if present
    if (bban.length() >= 21) {
      String checkPart = bban.substring(19, 21);
      if (!checkPart.matches("\\d{2}")) {
        throw new IllegalArgumentException("Portuguese check digits must be 2 digits");
      }
    }
  }
}
