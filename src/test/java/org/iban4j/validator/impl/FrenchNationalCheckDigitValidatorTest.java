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

import static org.junit.jupiter.api.Assertions.*;

import org.iban4j.CountryCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("French National Check Digit Validator Tests")
class FrenchNationalCheckDigitValidatorTest {

  private FrenchNationalCheckDigitValidator validator;

  @BeforeEach
  void setUp() {
    validator = new FrenchNationalCheckDigitValidator();
  }

  @Nested
  @DisplayName("Valid Check Digit Validation")
  class ValidCheckDigitValidation {

    @Test
    @DisplayName("Should validate correct French check digit")
    void testValidFrenchCheckDigit() {
      // Valid French IBAN: FR1420041010050500013M02606
      // BBAN: 20041010050500013M02606
      // Bank: 20041, Branch: 01005, Account: 0500013M026, Check: 06
      String bban = "20041010050500013M02606";
      String checkDigit = "06";

      assertTrue(validator.validate(bban, checkDigit), "French check digit should be valid");
    }

    @ParameterizedTest
    @DisplayName("Should validate multiple valid French check digits")
    @CsvSource({
      "20041010050500013M02606, 06", // Original test case
      "30002005009999999A01287, 87", // Different bank/branch/account
      "10203040506070809B09848, 48" // Edge case with different pattern
    })
    void testMultipleValidFrenchCheckDigits(String bban, String expectedCheckDigit) {
      assertTrue(
          validator.validate(bban, expectedCheckDigit),
          () ->
              String.format("BBAN %s should have valid check digit %s", bban, expectedCheckDigit));
    }
  }

  @Nested
  @DisplayName("Invalid Check Digit Validation")
  class InvalidCheckDigitValidation {

    @Test
    @DisplayName("Should reject incorrect French check digit")
    void testInvalidFrenchCheckDigit() {
      String bban = "20041010050500013M02606";
      String invalidCheckDigit = "07"; // Wrong check digit

      assertFalse(
          validator.validate(bban, invalidCheckDigit), "French check digit should be invalid");
    }

    @ParameterizedTest
    @DisplayName("Should reject invalid check digit formats")
    @ValueSource(strings = {"6", "067", "AB", "6A", ""})
    void testInvalidCheckDigitFormats(String invalidCheckDigit) {
      String bban = "20041010050500013M02606";

      assertFalse(
          validator.validate(bban, invalidCheckDigit),
          () -> String.format("Check digit '%s' should be invalid", invalidCheckDigit));
    }

    @Test
    @DisplayName("Should reject null check digit")
    void testNullCheckDigit() {
      String bban = "20041010050500013M02606";

      assertFalse(validator.validate(bban, null), "Null check digit should be invalid");
    }
  }

  @Nested
  @DisplayName("Check Digit Calculation")
  class CheckDigitCalculation {

    @Test
    @DisplayName("Should calculate correct French check digit")
    void testCalculateFrenchCheckDigit() {
      String bban = "20041010050500013M02606";

      String calculated = validator.calculate(bban);
      assertNotNull(calculated, "Calculated check digit should not be null");
      assertEquals(2, calculated.length(), "Check digit should be exactly 2 characters");
      assertTrue(calculated.matches("\\d{2}"), "Check digit should contain only digits");
    }

    @ParameterizedTest
    @DisplayName("Should calculate check digits for multiple test cases")
    @CsvSource({
      "20041010050500013M02606, 06", // Standard case
      "30002005009999999A01287, 87", // Different pattern
      "10203040506070809B09848, 48" // Edge case
    })
    void testCalculateMultipleCheckDigits(String bban, String expectedCheckDigit) {
      String calculated = validator.calculate(bban);
      assertEquals(
          expectedCheckDigit,
          calculated,
          () ->
              String.format(
                  "BBAN %s should calculate to check digit %s", bban, expectedCheckDigit));

      // Verify that calculated check digit validates correctly
      assertTrue(
          validator.validate(bban, calculated),
          () ->
              String.format(
                  "Calculated check digit %s should validate for BBAN %s", calculated, bban));
    }
  }

  @Nested
  @DisplayName("Input Validation")
  class InputValidation {

    @ParameterizedTest
    @DisplayName("Should reject invalid BBAN lengths")
    @ValueSource(
        strings = {
          "1234567890123456789012",
          "123456789012345678901234567890"
        }) // Too short and too long
    void testInvalidBbanLength(String invalidBban) {
      assertThrows(
          IllegalArgumentException.class,
          () -> validator.calculate(invalidBban),
          () ->
              String.format(
                  "BBAN '%s' with invalid length should throw IllegalArgumentException",
                  invalidBban));
    }

    @Test
    @DisplayName("Should reject null BBAN")
    void testNullBban() {
      assertThrows(
          IllegalArgumentException.class,
          () -> validator.calculate(null),
          "Null BBAN should throw IllegalArgumentException");
    }

    @ParameterizedTest
    @DisplayName("Should reject invalid BBAN formats")
    @ValueSource(
        strings = {"ABCDE01005050013M02606", "2004101005050013@02606", "200410100505001#M02606"})
    void testInvalidBbanFormat(String invalidBban) {
      assertThrows(
          IllegalArgumentException.class,
          () -> validator.calculate(invalidBban),
          () ->
              String.format(
                  "Invalid BBAN format '%s' should throw IllegalArgumentException", invalidBban));
    }
  }

  @Nested
  @DisplayName("Character Conversion")
  class CharacterConversion {

    @Test
    @DisplayName("Should handle alphanumeric account numbers correctly")
    void testAlphanumericAccountNumbers() {
      // Test account numbers with letters
      String bbanWithA = "20041010050500013A02606";
      String bbanWithZ = "20041010050500013Z02606";

      assertDoesNotThrow(
          () -> validator.calculate(bbanWithA), "BBAN with letter A should be processed correctly");
      assertDoesNotThrow(
          () -> validator.calculate(bbanWithZ), "BBAN with letter Z should be processed correctly");
    }

    @Test
    @DisplayName("Should handle case insensitive letters")
    void testCaseInsensitiveLetters() {
      String bbanUpperCase = "20041010050500013M02606";
      String bbanLowerCase = "20041010050500013m02606";

      String upperResult = validator.calculate(bbanUpperCase);
      String lowerResult = validator.calculate(bbanLowerCase);

      assertEquals(
          upperResult, lowerResult, "Upper and lower case letters should produce same result");
    }
  }

  @Nested
  @DisplayName("Validator Metadata")
  class ValidatorMetadata {

    @Test
    @DisplayName("Should return correct supported country")
    void testGetSupportedCountry() {
      assertEquals(CountryCode.FR, validator.getSupportedCountry(), "Should support France");
    }
  }
}
