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

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.iban4j.CountryCode;
import org.iban4j.validator.impl.BelgianNationalCheckDigitValidator;
import org.iban4j.validator.impl.FrenchNationalCheckDigitValidator;
import org.iban4j.validator.impl.ItalianNationalCheckDigitValidator;
import org.iban4j.validator.impl.PortugueseNationalCheckDigitValidator;
import org.iban4j.validator.impl.SpanishNationalCheckDigitValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

@DisplayName("National Check Digit Validator Factory Tests")
class NationalCheckDigitValidatorFactoryTest {

  @Nested
  @DisplayName("Supported Country Validators")
  class SupportedCountryValidators {

    @Test
    @DisplayName("Should return Spanish validator for Spain")
    void testGetSpanishValidator() {
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(CountryCode.ES);

      assertNotNull(validator, "Spanish validator should exist");
      // Java 11 compatible instanceof check
      assertTrue(
          validator instanceof SpanishNationalCheckDigitValidator,
          "Should be Spanish validator instance");
      assertEquals(
          CountryCode.ES, validator.getSupportedCountry(), "Validator should support Spain");
    }

    @Test
    @DisplayName("Should return French validator for France")
    void testGetFrenchValidator() {
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(CountryCode.FR);

      assertNotNull(validator, "French validator should exist");
      // Java 11 compatible instanceof check
      assertTrue(
          validator instanceof FrenchNationalCheckDigitValidator,
          "Should be French validator instance");
      assertEquals(
          CountryCode.FR, validator.getSupportedCountry(), "Validator should support France");
    }

    @Test
    @DisplayName("Should return Italian validator for Italy")
    void testGetItalianValidator() {
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(CountryCode.IT);

      assertNotNull(validator, "Italian validator should exist");
      // Java 11 compatible instanceof check
      assertTrue(
          validator instanceof ItalianNationalCheckDigitValidator,
          "Should be Italian validator instance");
      assertEquals(
          CountryCode.IT, validator.getSupportedCountry(), "Validator should support Italy");
    }

    @Test
    @DisplayName("Should return Belgian validator for Belgium")
    void testGetBelgianValidator() {
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(CountryCode.BE);

      assertNotNull(validator, "Belgian validator should exist");
      // Java 11 compatible instanceof check
      assertTrue(
          validator instanceof BelgianNationalCheckDigitValidator,
          "Should be Belgian validator instance");
      assertEquals(
          CountryCode.BE, validator.getSupportedCountry(), "Validator should support Belgium");
    }

    @Test
    @DisplayName("Should return Portuguese validator for Portugal")
    void testGetPortugueseValidator() {
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(CountryCode.PT);

      assertNotNull(validator, "Portuguese validator should exist");
      // Java 11 compatible instanceof check
      assertTrue(
          validator instanceof PortugueseNationalCheckDigitValidator,
          "Should be Portuguese validator instance");
      assertEquals(
          CountryCode.PT, validator.getSupportedCountry(), "Validator should support Portugal");
    }

    @ParameterizedTest
    @DisplayName("Should return correct validator types for all supported countries")
    @CsvSource({
      "ES, org.iban4j.validator.impl.SpanishNationalCheckDigitValidator",
      "FR, org.iban4j.validator.impl.FrenchNationalCheckDigitValidator",
      "IT, org.iban4j.validator.impl.ItalianNationalCheckDigitValidator",
      "BE, org.iban4j.validator.impl.BelgianNationalCheckDigitValidator",
      "PT, org.iban4j.validator.impl.PortugueseNationalCheckDigitValidator"
    })
    void testCorrectValidatorTypes(String countryCode, String expectedClassName) {
      CountryCode country = CountryCode.valueOf(countryCode);
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(country);

      assertNotNull(validator, () -> "Validator for " + countryCode + " should exist");
      assertEquals(
          expectedClassName,
          validator.getClass().getName(),
          () -> "Validator for " + countryCode + " should be of correct type");
    }
  }

  @Nested
  @DisplayName("Unsupported Country Validators")
  class UnsupportedCountryValidators {

    @ParameterizedTest
    @DisplayName("Should return null for unsupported countries")
    @EnumSource(
        value = CountryCode.class,
        names = {"DE", "GB", "US", "NL", "AT", "CH"})
    void testGetValidatorForUnsupportedCountries(CountryCode countryCode) {
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(countryCode);

      assertNull(
          validator,
          () -> "Validator for unsupported country " + countryCode + " should not exist");
    }

    @Test
    @DisplayName("Should return null for Germany")
    void testGetValidatorForGermany() {
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(CountryCode.DE);

      assertNull(validator, "German validator should not exist");
    }

    @Test
    @DisplayName("Should return null for United States")
    void testGetValidatorForUnitedStates() {
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(CountryCode.US);

      assertNull(validator, "US validator should not exist");
    }
  }

  @Nested
  @DisplayName("Country Support Detection")
  class CountrySupportDetection {

    @ParameterizedTest
    @DisplayName("Should correctly identify supported countries")
    @CsvSource({
      "ES, true", // Spain
      "FR, true", // France
      "IT, true", // Italy
      "BE, true", // Belgium
      "PT, true" // Portugal
    })
    void testIsSupportedForSupportedCountries(String countryCode, boolean expectedSupport) {
      CountryCode country = CountryCode.valueOf(countryCode);
      boolean actualSupport = NationalCheckDigitValidatorFactory.isSupported(country);

      assertEquals(
          expectedSupport,
          actualSupport,
          () -> String.format("Country %s support should be %s", countryCode, expectedSupport));
    }

    @ParameterizedTest
    @DisplayName("Should correctly identify unsupported countries")
    @CsvSource({
      "DE, false", // Germany
      "GB, false", // United Kingdom
      "US, false", // United States
      "NL, false", // Netherlands
      "AT, false", // Austria
      "CH, false" // Switzerland
    })
    void testIsSupportedForUnsupportedCountries(String countryCode, boolean expectedSupport) {
      CountryCode country = CountryCode.valueOf(countryCode);
      boolean actualSupport = NationalCheckDigitValidatorFactory.isSupported(country);

      assertEquals(
          expectedSupport,
          actualSupport,
          () -> String.format("Country %s support should be %s", countryCode, expectedSupport));
    }

    @Test
    @DisplayName("Should handle null country code gracefully")
    void testIsSupportedForNullCountry() {
      boolean isSupported = NationalCheckDigitValidatorFactory.isSupported(null);

      assertFalse(isSupported, "Null country should not be supported");
    }
  }

  @Nested
  @DisplayName("Supported Countries Map")
  class SupportedCountriesMap {

    @Test
    @DisplayName("Should return map with all supported countries")
    void testGetSupportedCountries() {
      Map<CountryCode, NationalCheckDigitValidator> supportedCountries =
          NationalCheckDigitValidatorFactory.getSupportedCountries();

      assertNotNull(supportedCountries, "Supported countries map should not be null");

      // Check that all expected countries are present
      assertTrue(supportedCountries.containsKey(CountryCode.ES), "Should contain Spain");
      assertTrue(supportedCountries.containsKey(CountryCode.FR), "Should contain France");
      assertTrue(supportedCountries.containsKey(CountryCode.IT), "Should contain Italy");
      assertTrue(supportedCountries.containsKey(CountryCode.BE), "Should contain Belgium");
      assertTrue(supportedCountries.containsKey(CountryCode.PT), "Should contain Portugal");

      // Check that unsupported countries are not present
      assertFalse(supportedCountries.containsKey(CountryCode.DE), "Should not contain Germany");
      assertFalse(
          supportedCountries.containsKey(CountryCode.GB), "Should not contain United Kingdom");
      assertFalse(
          supportedCountries.containsKey(CountryCode.US), "Should not contain United States");

      // Verify exact count
      assertEquals(5, supportedCountries.size(), "Should contain exactly 5 supported countries");
    }

    @Test
    @DisplayName("Should return unmodifiable map")
    void testSupportedCountriesMapIsUnmodifiable() {
      Map<CountryCode, NationalCheckDigitValidator> supportedCountries =
          NationalCheckDigitValidatorFactory.getSupportedCountries();

      // Test that returned map is unmodifiable
      assertThrows(
          UnsupportedOperationException.class,
          () -> supportedCountries.put(CountryCode.DE, new SpanishNationalCheckDigitValidator()),
          "Should not be able to modify the supported countries map");

      assertThrows(
          UnsupportedOperationException.class,
          () -> supportedCountries.remove(CountryCode.ES),
          "Should not be able to remove from the supported countries map");

      assertThrows(
          UnsupportedOperationException.class,
          () -> supportedCountries.clear(),
          "Should not be able to clear the supported countries map");
    }

    @Test
    @DisplayName("Should return validators that match factory methods")
    void testSupportedCountriesMapConsistency() {
      Map<CountryCode, NationalCheckDigitValidator> supportedCountries =
          NationalCheckDigitValidatorFactory.getSupportedCountries();

      for (Map.Entry<CountryCode, NationalCheckDigitValidator> entry :
          supportedCountries.entrySet()) {
        CountryCode country = entry.getKey();
        NationalCheckDigitValidator mapValidator = entry.getValue();
        NationalCheckDigitValidator factoryValidator =
            NationalCheckDigitValidatorFactory.getValidator(country);

        assertNotNull(factoryValidator, () -> "Factory should return validator for " + country);
        assertEquals(
            mapValidator.getClass(),
            factoryValidator.getClass(),
            () -> "Map and factory should return same validator type for " + country);
        assertEquals(
            country,
            mapValidator.getSupportedCountry(),
            () -> "Validator should support correct country " + country);
      }
    }
  }

  @Nested
  @DisplayName("Validator Consistency")
  class ValidatorConsistency {

    @Test
    @DisplayName("Should return consistent validator instances")
    void testValidatorConsistency() {
      // Test that multiple calls return validators of the same type
      NationalCheckDigitValidator validator1 =
          NationalCheckDigitValidatorFactory.getValidator(CountryCode.ES);
      NationalCheckDigitValidator validator2 =
          NationalCheckDigitValidatorFactory.getValidator(CountryCode.ES);

      assertNotNull(validator1, "First validator should not be null");
      assertNotNull(validator2, "Second validator should not be null");
      assertEquals(
          validator1.getClass(), validator2.getClass(), "Validators should be of same class");
      assertEquals(
          validator1.getSupportedCountry(),
          validator2.getSupportedCountry(),
          "Country codes should match");
    }

    @ParameterizedTest
    @DisplayName("Should return working validators for all supported countries")
    @EnumSource(
        value = CountryCode.class,
        names = {"ES", "FR", "IT", "BE", "PT"})
    void testAllSupportedValidatorsWork(CountryCode countryCode) {
      NationalCheckDigitValidator validator =
          NationalCheckDigitValidatorFactory.getValidator(countryCode);

      assertNotNull(validator, () -> "Validator for " + countryCode + " should not be null");
      assertEquals(
          countryCode,
          validator.getSupportedCountry(),
          () -> "Validator should support correct country " + countryCode);

      // Test that validator methods don't throw exceptions
      assertDoesNotThrow(
          () -> validator.getSupportedCountry(),
          () -> "getSupportedCountry should not throw for " + countryCode);
    }
  }

  @Nested
  @DisplayName("Thread Safety")
  class ThreadSafety {

    @Test
    @DisplayName("Should be thread-safe for concurrent access")
    void testThreadSafety() throws InterruptedException {
      int threadCount = 10;
      int iterationsPerThread = 100;

      Thread[] threads = new Thread[threadCount];

      for (int t = 0; t < threadCount; t++) {
        threads[t] =
            new Thread(
                () -> {
                  for (int i = 0; i < iterationsPerThread; i++) {
                    // Test concurrent access to different validators
                    NationalCheckDigitValidator esValidator =
                        NationalCheckDigitValidatorFactory.getValidator(CountryCode.ES);
                    NationalCheckDigitValidator frValidator =
                        NationalCheckDigitValidatorFactory.getValidator(CountryCode.FR);
                    NationalCheckDigitValidator deValidator =
                        NationalCheckDigitValidatorFactory.getValidator(CountryCode.DE);

                    assertNotNull(esValidator);
                    assertNotNull(frValidator);
                    assertNull(deValidator);

                    assertTrue(NationalCheckDigitValidatorFactory.isSupported(CountryCode.ES));
                    assertTrue(NationalCheckDigitValidatorFactory.isSupported(CountryCode.FR));
                    assertFalse(NationalCheckDigitValidatorFactory.isSupported(CountryCode.DE));
                  }
                });
      }

      // Start all threads
      for (Thread thread : threads) {
        thread.start();
      }

      // Wait for all threads to complete
      for (Thread thread : threads) {
        thread.join();
      }

      // If we get here without exceptions, the test passed
      assertTrue(true, "Factory should be thread-safe");
    }
  }

  @Nested
  @DisplayName("Performance Tests")
  class PerformanceTests {

    @Test
    @DisplayName("Should have fast validator lookup")
    void testValidatorLookupPerformance() {
      // Warm up
      for (int i = 0; i < 1000; i++) {
        NationalCheckDigitValidatorFactory.getValidator(CountryCode.ES);
        NationalCheckDigitValidatorFactory.isSupported(CountryCode.ES);
      }

      // Measure performance
      long startTime = System.nanoTime();
      for (int i = 0; i < 100000; i++) {
        NationalCheckDigitValidatorFactory.getValidator(CountryCode.ES);
        NationalCheckDigitValidatorFactory.isSupported(CountryCode.ES);
      }
      long endTime = System.nanoTime();

      long averageTimeNanos = (endTime - startTime) / 200000; // 2 operations per iteration
      double averageTimeMicros = averageTimeNanos / 1000.0;

      assertTrue(
          averageTimeMicros < 10.0,
          String.format(
              "Average lookup time should be under 10 microseconds, was %.3f", averageTimeMicros));
    }
  }
}
