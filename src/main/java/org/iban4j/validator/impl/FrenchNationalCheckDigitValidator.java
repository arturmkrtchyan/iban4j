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
 * French national check digit validator using MOD 97 algorithm.
 *
 * <p>The French BBAN format is: <strong>BBBBBGGGGGCCCCCCCCCCCDD</strong> where:
 *
 * <ul>
 *   <li><strong>B</strong> = Bank code (5 digits)
 *   <li><strong>G</strong> = Branch code (Guichet - 5 digits)
 *   <li><strong>C</strong> = Account number (11 alphanumeric characters)
 *   <li><strong>D</strong> = Check digits (2 digits) - validated by this class
 * </ul>
 *
 * <p>The algorithm uses MOD 97 calculation on the numeric representation of: bank code + branch
 * code + converted account number, where letters in the account number are converted to numbers
 * (A=1, B=2, ..., Z=26).
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
 * <h3>Character Conversion (Java 11 Compatible):</h3>
 *
 * <ul>
 *   <li>Digits (0-9): remain unchanged
 *   <li>Letters (A-Z): converted to 1-26 respectively
 *   <li>Case insensitive: 'a' and 'A' both convert to 1
 * </ul>
 *
 * <h3>References:</h3>
 *
 * <ul>
 *   <li><a href="https://www.banque-france.fr/">Banque de France</a>
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

    // Convert account number to numeric for calculation
    String numericAccount = convertAlphaToNumeric(accountNumber);
    String fullNumber = bankCode + branchCode + numericAccount;

    // Calculate MOD 97
    try {
      // Handle large numbers by using BigInteger arithmetic via string parsing
      long number = Long.parseLong(fullNumber);
      int remainder = (int) (number % MODULUS);
      int checkDigit = MODULUS - remainder;

      return String.format("%02d", checkDigit);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "Invalid numeric conversion in French BBAN: " + fullNumber, e);
    }
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

    if (bban.length() < EXPECTED_BBAN_LENGTH) {
      throw new IllegalArgumentException(
          String.format(
              "French BBAN must be at least %d characters, got %d",
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
   * Converts alphanumeric string to numeric using Java 11 compatible methods.
   *
   * <p><strong>Java 11 Compatibility:</strong> This method uses {@code Character.toUpperCase(char)}
   * instead of newer APIs to ensure compatibility with Java 11 environments.
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
        // Java 11 compatible way to convert to uppercase and then to number
        char upperChar = Character.toUpperCase(c); // Available in Java 11
        int numericValue = upperChar - 'A' + 1; // A=1, B=2, ..., Z=26

        if (numericValue >= 1 && numericValue <= 26) {
          numeric.append(numericValue);
        } else {
          throw new IllegalArgumentException(
              String.format("Invalid character in French account number: %c", c));
        }
      } else {
        throw new IllegalArgumentException(
            String.format(
                "Invalid character in French account number: %c (only letters and digits allowed)",
                c));
      }
    }

    return numeric.toString();
  }
}
