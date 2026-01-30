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
 * Spanish national check digit validator implementing the Código de Cuenta Cliente (CCC) algorithm.
 *
 * <p>The Spanish BBAN format is: <strong>BBBBSSSSDDAAAAAAAAAA</strong> where:
 *
 * <ul>
 *   <li><strong>B</strong> = Bank code (4 digits)
 *   <li><strong>S</strong> = Branch code (4 digits)
 *   <li><strong>D</strong> = Check digits (2 digits) - validated by this class
 *   <li><strong>A</strong> = Account number (10 digits)
 * </ul>
 *
 * <p>The CCC algorithm calculates two check digits using MOD-11:
 *
 * <ol>
 *   <li><strong>First digit</strong>: MOD-11 of "00" + bank code + branch code (10 digits)
 *   <li><strong>Second digit</strong>: MOD-11 of account number (10 digits)
 * </ol>
 *
 * <p>Both calculations use the same weights [1,2,4,8,5,10,9,7,3,6] applied left-to-right.
 *
 * @author iban4j contributors
 * @since 3.3.0
 */
public class SpanishNationalCheckDigitValidator implements NationalCheckDigitValidator {

  /**
   * Weight array for MOD-11 calculation (10 digits). Applied left-to-right to both "00" + bank +
   * branch (for first digit) and account number (for second digit).
   */
  private static final int[] WEIGHTS = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};

  private static final int SPANISH_BBAN_LENGTH = 20;
  private static final int CHECK_DIGIT_LENGTH = 2;
  private static final int MODULUS = 11;

  @Override
  public boolean validate(String bban, String nationalCheckDigit) {
    if (nationalCheckDigit == null || nationalCheckDigit.length() != CHECK_DIGIT_LENGTH) {
      return false;
    }

    if (!nationalCheckDigit.matches("\\d{2}")) {
      return false;
    }

    try {
      String calculated = calculate(bban);
      return nationalCheckDigit.equals(calculated);
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public String calculate(String bban) {
    validateBbanFormat(bban);

    // Spanish BBAN format: BBBBSSSSDDAAAAAAAAAA
    // Extract components, skipping check digits at positions 8-9
    String bankCode = bban.substring(0, 4); // Bank: positions 0-3
    String branchCode = bban.substring(4, 8); // Branch: positions 4-7
    String accountNumber = bban.substring(10, 20); // Account: positions 10-19

    // First check digit: MOD-11 of "00" + bank + branch (10 digits total)
    String bankBranchInput = "00" + bankCode + branchCode; // "00" + "2100" + "0418" = "0021000418"
    int firstDigit = calculateCheckDigit(bankBranchInput, WEIGHTS);

    // Second check digit: MOD-11 of account number (10 digits)
    int secondDigit = calculateCheckDigit(accountNumber, WEIGHTS);

    return String.format("%d%d", firstDigit, secondDigit);
  }

  @Override
  public CountryCode getSupportedCountry() {
    return CountryCode.ES;
  }

  private void validateBbanFormat(String bban) {
    if (bban == null) {
      throw new IllegalArgumentException("Spanish BBAN cannot be null");
    }

    if (bban.length() != SPANISH_BBAN_LENGTH) {
      throw new IllegalArgumentException(
          String.format(
              "Spanish BBAN must be exactly %d characters, got %d",
              SPANISH_BBAN_LENGTH, bban.length()));
    }

    if (!bban.matches("\\d{20}")) {
      throw new IllegalArgumentException("Spanish BBAN must contain only digits");
    }
  }

  private int calculateCheckDigit(String number, int[] weights) {
    if (number.length() != weights.length) {
      throw new IllegalArgumentException(
          String.format(
              "Number length (%d) must match weights length (%d). Number: %s",
              number.length(), weights.length, number));
    }

    int sum = 0;
    for (int i = 0; i < number.length(); i++) {
      int digit = Character.getNumericValue(number.charAt(i));
      sum += digit * weights[i];
    }

    int remainder = sum % MODULUS;
    int result = MODULUS - remainder;

    // Spanish MOD-11 special rules:
    // If result is 10, use 1 instead
    // If result is 11, use 0 instead
    if (result == 10) {
      return 1;
    } else if (result == 11) {
      return 0;
    }

    return result;
  }
}
