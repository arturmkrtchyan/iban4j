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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/** Test class for IbanValidationUtil with JUnit Jupiter - Java 11 compatible. */
@DisplayName("IbanValidationUtil Tests")
class IbanValidationUtilTest {

  @Nested
  @DisplayName("National Check Digit Validation")
  class NationalCheckDigitValidation {

    @Test
    @DisplayName("Should validate Spanish IBAN with correct national check digit")
    void testValidateWithNationalCheckDigitSpain() {
      // Valid Spanish IBAN with correct national check digit
      String validSpanishIban = "ES9121000418450200051332";

      assertDoesNotThrow(
          () -> IbanValidationUtil.validateWithNationalCheckDigit(validSpanishIban),
          "Valid Spanish IBAN should pass validation");
    }

    @Test
    @DisplayName(
        "Should throw InvalidNationalCheckDigitException for Spanish IBAN with invalid national check digit")
    void testValidateWithInvalidNationalCheckDigitSpain() {
      // Spanish IBAN with incorrect national check digit (changed 45 to 46)
      // IBAN check digit recalculated to 29 for BBAN with national check 46
      String invalidSpanishIban = "ES2921000418460200051332";

      InvalidNationalCheckDigitException exception =
          assertThrows(
              InvalidNationalCheckDigitException.class,
              () -> IbanValidationUtil.validateWithNationalCheckDigit(invalidSpanishIban),
              "Invalid Spanish IBAN should throw InvalidNationalCheckDigitException");

      assertEquals(CountryCode.ES, exception.getCountryCode(), "Should be Spanish country code");
      assertNotNull(exception.getActualCheckDigit(), "Should have actual check digit");
      assertNotNull(exception.getExpectedCheckDigit(), "Should have expected check digit");
      assertEquals("46", exception.getActualCheckDigit(), "Actual check digit should be 46");
      assertEquals("45", exception.getExpectedCheckDigit(), "Expected check digit should be 45");
    }

    @Test
    @DisplayName("Should validate French IBAN with national check digit")
    void testValidateWithNationalCheckDigitFrance() {
      // Valid French IBAN
      String validFrenchIban = "FR1420041010050500013M02606";

      assertDoesNotThrow(
          () -> IbanValidationUtil.validateWithNationalCheckDigit(validFrenchIban),
          "Valid French IBAN should pass validation");
    }

    @Test
    @DisplayName("Should validate Italian IBAN with national check digit")
    void testValidateWithNationalCheckDigitItaly() {
      // Valid Italian IBAN
      String validItalianIban = "IT60X0542811101000000123456";

      assertDoesNotThrow(
          () -> IbanValidationUtil.validateWithNationalCheckDigit(validItalianIban),
          "Valid Italian IBAN should pass validation");
    }

    @ParameterizedTest
    @DisplayName("Should validate multiple valid IBANs with national check digits")
    @ValueSource(
        strings = {
          "ES9121000418450200051332", // Spain
          "FR1420041010050500013M02606", // France
          "IT60X0542811101000000123456", // Italy
          "BE68539007547034", // Belgium
          "PT50000201231234567890154" // Portugal
        })
    void testValidateMultipleValidIbansWithNationalCheckDigits(String iban) {
      assertDoesNotThrow(
          () -> IbanValidationUtil.validateWithNationalCheckDigit(iban),
          () -> "Valid IBAN " + iban + " should pass validation");
    }

    @Test
    @DisplayName("Should pass validation for countries without national check digits")
    void testValidateCountryWithoutNationalCheckDigit() {
      // German IBAN (Germany doesn't have national check digits in this implementation)
      String germanIban = "DE89370400440532013000";

      assertDoesNotThrow(
          () -> IbanValidationUtil.validateWithNationalCheckDigit(germanIban),
          "IBAN from country without national check digit should pass");
    }

    @ParameterizedTest
    @DisplayName("Should handle countries without national check digits")
    @ValueSource(
        strings = {
          "DE89370400440532013000", // Germany
          "GB82WEST12345698765432", // United Kingdom
          "NL91ABNA0417164300" // Netherlands
        })
    void testValidateCountriesWithoutNationalCheckDigits(String iban) {
      assertDoesNotThrow(
          () -> IbanValidationUtil.validateWithNationalCheckDigit(iban),
          () -> "IBAN " + iban + " from country without national check digit should pass");
    }
  }

  @Nested
  @DisplayName("Boolean Validation Methods")
  class BooleanValidationMethods {

    @ParameterizedTest
    @DisplayName("Should return true for valid IBANs with national check digits")
    @CsvSource({
      "ES9121000418450200051332, true", // Valid Spanish
      "ES2921000418460200051332, false", // Invalid Spanish national check digit
      "FR1420041010050500013M02606, true", // Valid French
      "DE89370400440532013000, true" // Valid German (no national check digit)
    })
    void testIsValidWithNationalCheckDigit(String iban, boolean expectedResult) {
      boolean actualResult = IbanValidationUtil.isValidWithNationalCheckDigit(iban);
      assertEquals(
          expectedResult,
          actualResult,
          () -> String.format("IBAN %s should return %s", iban, expectedResult));
    }

    @Test
    @DisplayName("Should handle formatted IBANs correctly")
    void testIsValidWithNationalCheckDigitFormatted() {
      String validFormattedSpanishIban = "ES91 2100 0418 4502 0005 1332";
      String invalidFormattedSpanishIban = "ES91 2100 0418 4602 0005 1332";

      assertTrue(
          IbanValidationUtil.isValidWithNationalCheckDigit(
              validFormattedSpanishIban, IbanFormat.Default),
          "Valid formatted Spanish IBAN should return true");

      assertFalse(
          IbanValidationUtil.isValidWithNationalCheckDigit(
              invalidFormattedSpanishIban, IbanFormat.Default),
          "Invalid formatted Spanish IBAN should return false");
    }
  }

  @Nested
  @DisplayName("Country Support Methods")
  class CountrySupportMethods {

    @ParameterizedTest
    @DisplayName("Should correctly identify supported countries")
    @CsvSource({
      "ES, true", // Spain
      "FR, true", // France
      "IT, true", // Italy
      "BE, true", // Belgium
      "PT, true", // Portugal
      "DE, false", // Germany
      "GB, false", // United Kingdom
      "US, false" // United States
    })
    void testSupportsNationalCheckDigit(String countryCode, boolean expectedSupport) {
      CountryCode country = CountryCode.valueOf(countryCode);
      boolean actualSupport = IbanValidationUtil.supportsNationalCheckDigit(country);

      assertEquals(
          expectedSupport,
          actualSupport,
          () -> String.format("Country %s support should be %s", countryCode, expectedSupport));
    }
  }

  @Nested
  @DisplayName("Backward Compatibility")
  class BackwardCompatibility {

    @ParameterizedTest
    @DisplayName("Should maintain backward compatibility with existing IbanUtil")
    @ValueSource(
        strings = {
          "DE89370400440532013000",
          "GB82WEST12345698765432",
          "ES9121000418450200051332",
          "FR1420041010050500013M02606"
        })
    void testBackwardCompatibility(String iban) {
      // Standard validation should work exactly as before
      boolean oldResult = IbanUtil.isValid(iban);
      assertTrue(oldResult, () -> "Standard validation should still work for " + iban);

      // Enhanced validation should also pass for valid IBANs
      boolean newResult = IbanValidationUtil.isValidWithNationalCheckDigit(iban);
      assertTrue(newResult, () -> "Enhanced validation should pass for valid IBAN " + iban);
    }

    @Test
    @DisplayName(
        "Should demonstrate that standard validation passes but enhanced fails for invalid national check digit")
    void testStandardPassesEnhancedFails() {
      String invalidNationalCheckDigit = "ES2921000418460200051332"; // Wrong national check digit

      // Standard validation should pass (only checks IBAN check digit)
      assertTrue(
          IbanUtil.isValid(invalidNationalCheckDigit),
          "Standard validation should pass for IBAN with invalid national check digit");

      // Enhanced validation should fail
      assertFalse(
          IbanValidationUtil.isValidWithNationalCheckDigit(invalidNationalCheckDigit),
          "Enhanced validation should fail for IBAN with invalid national check digit");
    }
  }

  @Nested
  @DisplayName("Delegation Methods")
  class DelegationMethods {

    @Test
    @DisplayName("Should correctly delegate to IbanUtil methods")
    void testDelegationMethods() {
      String testIban = "ES9121000418450200051332";

      // Test that delegation methods work correctly
      assertEquals(
          IbanUtil.getCountryCode(testIban),
          IbanValidationUtil.getCountryCode(testIban),
          "Country code should match between IbanUtil and IbanValidationUtil");

      assertEquals(
          IbanUtil.getBban(testIban),
          IbanValidationUtil.getBban(testIban),
          "BBAN should match between IbanUtil and IbanValidationUtil");
    }
  }

  @Nested
  @DisplayName("Error Handling")
  class ErrorHandling {

    @Test
    @DisplayName("Should provide detailed error information in exceptions")
    void testDetailedErrorInformation() {
      String invalidSpanishIban = "ES2921000418460200051332";

      InvalidNationalCheckDigitException exception =
          assertThrows(
              InvalidNationalCheckDigitException.class,
              () -> IbanValidationUtil.validateWithNationalCheckDigit(invalidSpanishIban));

      // Verify exception contains useful information
      assertNotNull(exception.getMessage(), "Exception message should not be null");
      assertTrue(exception.getMessage().contains("ES"), "Exception message should mention country");
      assertEquals(CountryCode.ES, exception.getCountryCode(), "Country code should be Spain");
      assertNotNull(exception.getActualCheckDigit(), "Actual check digit should be provided");
      assertNotNull(exception.getExpectedCheckDigit(), "Expected check digit should be provided");
      assertNotEquals(
          exception.getActualCheckDigit(),
          exception.getExpectedCheckDigit(),
          "Actual and expected should be different");
    }

    @Test
    @DisplayName("Should handle null IBAN gracefully")
    void testNullIbanHandling() {
      assertThrows(
          Exception.class,
          () -> IbanValidationUtil.validateWithNationalCheckDigit(null),
          "Null IBAN should throw an exception");

      assertFalse(
          IbanValidationUtil.isValidWithNationalCheckDigit(null),
          "Null IBAN should return false for boolean validation");
    }

    @Test
    @DisplayName("Should handle empty IBAN gracefully")
    void testEmptyIbanHandling() {
      assertThrows(
          Exception.class,
          () -> IbanValidationUtil.validateWithNationalCheckDigit(""),
          "Empty IBAN should throw an exception");

      assertFalse(
          IbanValidationUtil.isValidWithNationalCheckDigit(""),
          "Empty IBAN should return false for boolean validation");
    }

    @Test
    @DisplayName("Should handle malformed IBAN gracefully")
    void testMalformedIbanHandling() {
      String malformedIban = "INVALID_IBAN";

      assertThrows(
          Exception.class,
          () -> IbanValidationUtil.validateWithNationalCheckDigit(malformedIban),
          "Malformed IBAN should throw an exception");

      assertFalse(
          IbanValidationUtil.isValidWithNationalCheckDigit(malformedIban),
          "Malformed IBAN should return false for boolean validation");
    }
  }
}
