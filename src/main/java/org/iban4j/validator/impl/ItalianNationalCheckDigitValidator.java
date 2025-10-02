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
 * Italian national check digit validator using CIN (Codice di Controllo Interno Nazionale)
 * algorithm.
 *
 * <p>The Italian BBAN format is: <strong>XBBBBBSSSSSCCCCCCCCCCC</strong> where:
 *
 * <ul>
 *   <li><strong>X</strong> = Check digit (1 letter A-Z) - validated by this class
 *   <li><strong>B</strong> = Bank code (5 digits)
 *   <li><strong>S</strong> = Branch code (5 digits)
 *   <li><strong>C</strong> = Account number (12 alphanumeric characters)
 * </ul>
 *
 * <p>The CIN algorithm uses a weighted sum calculation where each character is converted to a
 * numeric value and different weights are applied based on the character's position (even vs odd).
 *
 * <h3>Algorithm Steps:</h3>
 *
 * <ol>
 *   <li>Extract bank code, branch code, and account number
 *   <li>For each character, convert to numeric value (digits stay as-is, letters: A=10, B=11, etc.)
 *   <li>Apply position-based weights (even positions: value, odd positions: value * 2, subtract 9
 *       if > 9)
 *   <li>Sum all weighted values
 *   <li>Take modulo 26 to get index into letter array A-Z
 * </ol>
 *
 * <h3>Example:</h3>
 *
 * <pre>{@code
 * // IBAN: IT60X0542811101000000123456
 * // BBAN: X0542811101000000123456
 * // Check: X, Bank: 05428, Branch: 11101, Account: 000000123456
 *
 * ItalianNationalCheckDigitValidator validator = new ItalianNationalCheckDigitValidator();
 * boolean isValid = validator.validate("X0542811101000000123456", "X");
 * }</pre>
 *
 * <h3>References:</h3>
 *
 * <ul>
 *   <li><a href="https://www.bancaditalia.it/">Banca d'Italia</a>
 *   <li>ABI (Associazione Bancaria Italiana) CIN documentation
 *   <li>Circolare della Banca d'Italia n. 166 del 18 novembre 1987
 * </ul>
 *
 * @author iban4j contributors
 * @since 3.3.0
 * @see NationalCheckDigitValidator
 * @see CountryCode#IT
 */
public class ItalianNationalCheckDigitValidator implements NationalCheckDigitValidator {

  /** Available check digit characters (A-Z). */
  private static final char[] CHECK_DIGIT_CHARS = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
  };

  private static final int EXPECTED_BBAN_LENGTH = 23;
  private static final int CHECK_DIGIT_LENGTH = 1;
  private static final int MODULUS = 26;

  @Override
  public boolean validate(String bban, String nationalCheckDigit) {
    if (nationalCheckDigit == null || nationalCheckDigit.length() != CHECK_DIGIT_LENGTH) {
      return false;
    }

    if (!nationalCheckDigit.matches("[A-Za-z]")) {
      return false;
    }

    try {
      String calculatedCheckDigit = calculate(bban);
      return nationalCheckDigit.toUpperCase().equals(calculatedCheckDigit.toUpperCase());
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public String calculate(String bban) {
    validateBbanFormat(bban);

    // Italian BBAN format: XBBBBBSSSSSCCCCCCCCCCC
    // Extract components (skip the check digit at position 0)
    String bankCode = bban.substring(1, 6);
    String branchCode = bban.substring(6, 11);
    String accountNumber = bban.substring(11, 23);

    String controlString = bankCode + branchCode + accountNumber;

    int sum = 0;
    for (int i = 0; i < controlString.length(); i++) {
      char c = controlString.charAt(i);
      int value = getCharacterValue(c);

      // Apply different weights based on position (0-based indexing)
      if (i % 2 == 0) { // Even position
        sum += value;
      } else { // Odd position
        int weightedValue = value * 2;
        if (weightedValue > 9) {
          weightedValue -= 9; // Subtract 9 if result is two digits
        }
        sum += weightedValue;
      }
    }

    int checkIndex = sum % MODULUS;
    return String.valueOf(CHECK_DIGIT_CHARS[checkIndex]);
  }

  @Override
  public CountryCode getSupportedCountry() {
    return CountryCode.IT;
  }

  /**
   * Validates the BBAN format for Italy.
   *
   * @param bban the BBAN to validate
   * @throws IllegalArgumentException if the BBAN format is invalid
   */
  private void validateBbanFormat(String bban) {
    if (bban == null) {
      throw new IllegalArgumentException("Italian BBAN cannot be null");
    }

    if (bban.length() < EXPECTED_BBAN_LENGTH) {
      throw new IllegalArgumentException(
          String.format(
              "Italian BBAN must be at least %d characters, got %d",
              EXPECTED_BBAN_LENGTH, bban.length()));
    }

    // Check digit (position 0) must be a letter
    char checkChar = bban.charAt(0);
    if (!Character.isLetter(checkChar)) {
      throw new IllegalArgumentException("Italian BBAN check digit must be a letter");
    }

    // Bank code (positions 1-5) must be digits
    String bankCode = bban.substring(1, 6);
    if (!bankCode.matches("\\d{5}")) {
      throw new IllegalArgumentException("Italian bank code must be 5 digits");
    }

    // Branch code (positions 6-10) must be digits
    String branchCode = bban.substring(6, 11);
    if (!branchCode.matches("\\d{5}")) {
      throw new IllegalArgumentException("Italian branch code must be 5 digits");
    }

    // Account number (positions 11-22) can be alphanumeric
    String accountNumber = bban.substring(11, 23);
    if (!accountNumber.matches("[A-Za-z0-9]{12}")) {
      throw new IllegalArgumentException(
          "Italian account number must be 12 alphanumeric characters");
    }
  }

  /**
   * Java 11 compatible method to get character value.
   *
   * <p>Converts characters to numeric values for CIN calculation:
   *
   * <ul>
   *   <li>Digits (0-9): numeric value (0-9)
   *   <li>Letters (A-Z): A=10, B=11, ..., Z=35
   * </ul>
   *
   * @param c the character to convert
   * @return numeric value of the character
   * @throws IllegalArgumentException if character is invalid
   */
  private int getCharacterValue(char c) {
    if (Character.isDigit(c)) {
      return Character.getNumericValue(c);
    } else if (Character.isLetter(c)) {
      // Java 11 compatible uppercase conversion
      char upperChar = Character.toUpperCase(c);
      return upperChar - 'A' + 10; // A=10, B=11, ..., Z=35
    } else {
      throw new IllegalArgumentException("Invalid character in Italian BBAN: " + c);
    }
  }
}
