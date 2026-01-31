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

import java.math.BigInteger;
import org.iban4j.CountryCode;
import org.iban4j.validator.NationalCheckDigitValidator;

/**
 * French national check digit validator using the RIB (Relevé d'Identité Bancaire) algorithm.
 *
 * <p>The French BBAN format is: <strong>BBBBBGGGGGCCCCCCCCCCCDD</strong> where:
 *
 * <ul>
 *   <li><strong>B</strong> = Bank code (5 digits)
 *   <li><strong>G</strong> = Branch code (Guichet - 5 digits)
 *   <li><strong>C</strong> = Account number (11 alphanumeric characters)
 *   <li><strong>D</strong> = Check digits (Clé RIB - 2 digits) - validated by this class
 * </ul>
 *
 * <p>The algorithm uses the formula: Clé RIB = 97 - ((89 × bank + 15 × branch + 3 × account) mod
 * 97)
 *
 * <h3>Example:</h3>
 *
 * <pre>{@code
 * // IBAN: FR1420041010050500013M02606
 * // BBAN: 20041010050500013M02606
 * // Bank: 20041, Branch: 01005, Account: 0500013M026, Check: 06
 *
 * FrenchNationalCheckDigitValidator validator = new FrenchNationalCheckDigitValidator();
 * boolean isValid = validator.validate("20041010050500013M02606", "06");
 * }</pre>
 *
 * <h3>Character Conversion (French Banking Standard):</h3>
 *
 * <ul>
 *   <li>Digits (0-9): remain unchanged
 *   <li>A, J = 1
 *   <li>B, K, S = 2
 *   <li>C, L, T = 3
 *   <li>D, M, U = 4
 *   <li>E, N, V = 5
 *   <li>F, O, W = 6
 *   <li>G, P, X = 7
 *   <li>H, Q, Y = 8
 *   <li>I, R, Z = 9
 * </ul>
 *
 * <h3>References:</h3>
 *
 * <ul>
 *   <li><a href="https://fr.wikipedia.org/wiki/Cl%C3%A9_RIB">Wikipedia - Clé RIB</a>
 *   <li>CFONB (Comité Français d'Organisation et de Normalisation Bancaires) standards
 * </ul>
 *
 * @author iban4j contributors
 * @since 3.3.0
 * @see NationalCheckDigitValidator
 * @see CountryCode#FR
 */
public class FrenchNationalCheckDigitValidator implements NationalCheckDigitValidator {

  private static final int EXPECTED_BBAN_LENGTH = 23;
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

    // French BBAN format: BBBBBGGGGGCCCCCCCCCCCDD
    String bankCode = bban.substring(0, 5);
    String branchCode = bban.substring(5, 10);
    String accountNumber = bban.substring(10, 21);

    // Convert account number letters to numbers using French banking standard
    String numericAccount = convertAlphaToNumeric(accountNumber);

    // Concatenate bank + branch + account + "00" and calculate mod 97
    // The clé RIB formula: key = 97 - ((bank || branch || account || "00") mod 97)
    String fullNumber = bankCode + branchCode + numericAccount + "00";
    BigInteger bigNumber = new BigInteger(fullNumber);
    int remainder = bigNumber.mod(BigInteger.valueOf(MODULUS)).intValue();

    int checkDigit = MODULUS - remainder;
    if (checkDigit == MODULUS) {
      checkDigit = 0;
    }

    return String.format("%02d", checkDigit);
  }

  @Override
  public CountryCode getSupportedCountry() {
    return CountryCode.FR;
  }

  /**
   * Validates the BBAN format for France.
   *
   * @param bban the BBAN to validate
   * @throws IllegalArgumentException if the BBAN format is invalid
   */
  private void validateBbanFormat(String bban) {
    if (bban == null) {
      throw new IllegalArgumentException("French BBAN cannot be null");
    }

    if (bban.length() != EXPECTED_BBAN_LENGTH) {
      throw new IllegalArgumentException(
          String.format(
              "French BBAN must be exactly %d characters, got %d",
              EXPECTED_BBAN_LENGTH, bban.length()));
    }

    // First 10 characters must be digits (bank + branch)
    String bankAndBranch = bban.substring(0, 10);
    if (!bankAndBranch.matches("\\d{10}")) {
      throw new IllegalArgumentException("French bank and branch codes must be 10 digits");
    }

    // Account number (positions 10-20) can be alphanumeric
    String accountPart = bban.substring(10, 21);
    if (!accountPart.matches("[A-Za-z0-9]{11}")) {
      throw new IllegalArgumentException(
          "French account number must be 11 alphanumeric characters");
    }

    // Check digits (positions 21-22) must be digits
    if (bban.length() >= 23) {
      String checkPart = bban.substring(21, 23);
      if (!checkPart.matches("\\d{2}")) {
        throw new IllegalArgumentException("French check digits must be 2 digits");
      }
    }
  }

  /**
   * Converts alphanumeric string to numeric using French banking standard.
   *
   * <p>French banking letter conversion table:
   *
   * <ul>
   *   <li>A, J = 1
   *   <li>B, K, S = 2
   *   <li>C, L, T = 3
   *   <li>D, M, U = 4
   *   <li>E, N, V = 5
   *   <li>F, O, W = 6
   *   <li>G, P, X = 7
   *   <li>H, Q, Y = 8
   *   <li>I, R, Z = 9
   * </ul>
   *
   * @param alphanumeric the alphanumeric string to convert
   * @return numeric string representation
   * @throws IllegalArgumentException if invalid characters are found
   */
  private String convertAlphaToNumeric(String alphanumeric) {
    if (alphanumeric == null) {
      return "";
    }

    StringBuilder numeric = new StringBuilder();

    for (int i = 0; i < alphanumeric.length(); i++) {
      char c = alphanumeric.charAt(i);

      if (Character.isDigit(c)) {
        numeric.append(c);
      } else if (Character.isLetter(c)) {
        char upperChar = Character.toUpperCase(c);
        int numericValue = convertLetterToDigit(upperChar);
        numeric.append(numericValue);
      } else {
        throw new IllegalArgumentException(
            String.format(
                "Invalid character in French account number: %c (only letters and digits allowed)",
                c));
      }
    }

    return numeric.toString();
  }

  /**
   * Converts a letter to its French banking digit equivalent.
   *
   * @param c the uppercase letter to convert
   * @return the digit equivalent (1-9)
   */
  private int convertLetterToDigit(char c) {
    switch (c) {
      case 'A':
      case 'J':
        return 1;
      case 'B':
      case 'K':
      case 'S':
        return 2;
      case 'C':
      case 'L':
      case 'T':
        return 3;
      case 'D':
      case 'M':
      case 'U':
        return 4;
      case 'E':
      case 'N':
      case 'V':
        return 5;
      case 'F':
      case 'O':
      case 'W':
        return 6;
      case 'G':
      case 'P':
      case 'X':
        return 7;
      case 'H':
      case 'Q':
      case 'Y':
        return 8;
      case 'I':
      case 'R':
      case 'Z':
        return 9;
      default:
        throw new IllegalArgumentException(
            String.format("Invalid character in French account number: %c", c));
    }
  }
}
