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
 * Italian national check digit validator using CIN (Codice Identificativo Nazionale) algorithm.
 *
 * <p>The Italian BBAN format is: <strong>XBBBBBSSSSSCCCCCCCCCCC</strong> where:
 *
 * <ul>
 *   <li><strong>X</strong> = CIN check character (1 letter A-Z) - validated by this class
 *   <li><strong>B</strong> = ABI bank code (5 digits)
 *   <li><strong>S</strong> = CAB branch code (5 digits)
 *   <li><strong>C</strong> = Account number (12 alphanumeric characters)
 * </ul>
 *
 * <p>The CIN algorithm uses position-based lookup tables:
 *
 * <ol>
 *   <li>Extract ABI + CAB + account number (22 characters)
 *   <li>For even positions (0-based): use simple conversion (A/0=0, B/1=1, ..., Z=25)
 *   <li>For odd positions (0-based): use special lookup table
 *   <li>Sum all values
 *   <li>Take modulo 26 to get the CIN letter (0=A, 1=B, ..., 25=Z)
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
 * </ul>
 *
 * @author iban4j contributors
 * @since 3.3.0
 * @see NationalCheckDigitValidator
 * @see CountryCode#IT
 */
public class ItalianNationalCheckDigitValidator implements NationalCheckDigitValidator {

  /** CIN result characters (0=A, 1=B, ..., 25=Z). */
  private static final char[] CIN_CHARS = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
  };

  /**
   * Lookup table for ODD positions (0-based indexing). Index 0-9 for digits '0'-'9', index 10-35
   * for letters 'A'-'Z'. Values: 0=1, 1=0, 2=5, 3=7, 4=9, 5=13, 6=15, 7=17, 8=19, 9=21, A=1, B=0,
   * C=5, D=7, E=9, F=13, G=15, H=17, I=19, J=21, K=2, L=4, M=18, N=20, O=11, P=3, Q=6, R=8, S=12,
   * T=14, U=16, V=10, W=22, X=25, Y=24, Z=23
   */
  private static final int[] ODD_POSITION_VALUES = {
    // Digits 0-9
    1,
    0,
    5,
    7,
    9,
    13,
    15,
    17,
    19,
    21,
    // Letters A-Z (A=index 10, B=index 11, ..., Z=index 35)
    1,
    0,
    5,
    7,
    9,
    13,
    15,
    17,
    19,
    21, // A-J (same as 0-9)
    2,
    4,
    18,
    20,
    11,
    3,
    6,
    8,
    12,
    14,
    16,
    10,
    22,
    25,
    24,
    23 // K-Z
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
    // Extract ABI + CAB + account number (skip the check digit at position 0)
    String controlString = bban.substring(1, 23);

    int sum = 0;
    for (int i = 0; i < controlString.length(); i++) {
      char c = Character.toUpperCase(controlString.charAt(i));
      int charIndex = getCharacterIndex(c);

      // Algorithm uses 1-based positions: odd positions (1,3,5...) use special table
      // In 0-based indexing: position 0,2,4... (even) = 1-based odd = special table
      if (i % 2 == 0) {
        // 0-based even = 1-based odd: use special lookup table
        sum += ODD_POSITION_VALUES[charIndex];
      } else {
        // 0-based odd = 1-based even: use simple conversion (0-25)
        sum += charIndex;
      }
    }

    int cinIndex = sum % MODULUS;
    return String.valueOf(CIN_CHARS[cinIndex]);
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
   * Gets the index for a character in the lookup tables.
   *
   * <p>Converts characters to indices:
   *
   * <ul>
   *   <li>Digits (0-9): index 0-9
   *   <li>Letters (A-Z): index 10-35
   * </ul>
   *
   * @param c the character to convert (must be uppercase)
   * @return index for lookup tables (0-35)
   * @throws IllegalArgumentException if character is invalid
   */
  private int getCharacterIndex(char c) {
    if (c >= '0' && c <= '9') {
      return c - '0'; // 0-9
    } else if (c >= 'A' && c <= 'Z') {
      return c - 'A' + 10; // 10-35
    } else {
      throw new IllegalArgumentException("Invalid character in Italian BBAN: " + c);
    }
  }
}
