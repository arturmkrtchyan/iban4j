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

import org.iban4j.bban.BbanEntryType;
import org.iban4j.bban.BbanStructure;
import org.iban4j.bban.BbanStructureEntry;
import org.iban4j.validator.NationalCheckDigitValidator;
import org.iban4j.validator.NationalCheckDigitValidatorFactory;

/**
 * Enhanced IBAN validation utilities with national check digit support.
 *
 * <p>This class provides additional validation capabilities beyond the standard IBAN check digit
 * validation available in {@link IbanUtil}. It includes validation of national check digits for
 * countries that include them in their BBAN structure.
 *
 * <p><strong>Design Philosophy:</strong> This class uses composition instead of inheritance since
 * {@code IbanUtil} is final. This approach follows the composition-over-inheritance principle and
 * maintains backward compatibility.
 *
 * <h3>Key Features:</h3>
 *
 * <ul>
 *   <li><strong>Backward Compatible:</strong> All existing {@code IbanUtil} functionality unchanged
 *   <li><strong>Enhanced Validation:</strong> Additional national check digit validation
 *   <li><strong>Country Support:</strong> Spain, France, Italy, Belgium, Portugal
 *   <li><strong>Performance:</strong> Zero overhead for countries without national check digits
 * </ul>
 *
 * <h3>Usage Examples:</h3>
 *
 * <pre>{@code
 * // Enhanced validation with national check digit
 * try {
 *     IbanValidationUtil.validateWithNationalCheckDigit("ES9121000418450200051332");
 *     System.out.println("✅ IBAN is fully valid");
 * } catch (InvalidNationalCheckDigitException e) {
 *     System.out.printf("❌ National check digit error: Expected %s, got %s%n",
 *         e.getExpectedCheckDigit(), e.getActualCheckDigit());
 * }
 *
 * // Simple boolean validation
 * boolean isFullyValid = IbanValidationUtil.isValidWithNationalCheckDigit("ES9121000418450200051332");
 *
 * // Check if country supports national check digit validation
 * boolean hasSupport = IbanValidationUtil.supportsNationalCheckDigit(CountryCode.ES);
 * }</pre>
 *
 * <h3>Migration from IbanUtil:</h3>
 *
 * <pre>{@code
 * // Before: Standard validation only
 * boolean isValid = IbanUtil.isValid(iban);
 *
 * // After: Enhanced validation with national check digits
 * boolean isValid = IbanValidationUtil.isValidWithNationalCheckDigit(iban);
 *
 * // Both approaches can coexist:
 * boolean standardValid = IbanUtil.isValid(iban);                    // Standard IBAN validation
 * boolean enhancedValid = IbanValidationUtil.isValidWithNationalCheckDigit(iban); // Enhanced validation
 * }</pre>
 *
 * @author iban4j contributors
 * @since 3.3.0
 * @see IbanUtil
 * @see InvalidNationalCheckDigitException
 * @see org.iban4j.validator.NationalCheckDigitValidator
 */
public final class IbanValidationUtil {

  /** Private constructor to prevent instantiation. */
  private IbanValidationUtil() {
    // Utility class
  }

  /**
   * Validates IBAN including national check digit validation when applicable.
   *
   * <p>This method performs all standard IBAN validations via {@link IbanUtil#validate(String)} and
   * additionally validates national check digits for supported countries.
   *
   * <p><strong>Validation Flow:</strong>
   *
   * <ol>
   *   <li>Standard IBAN validation (format, length, MOD-97 check digit)
   *   <li>National check digit validation (if country supports it)
   * </ol>
   *
   * <p><strong>Performance:</strong> Countries without national check digits have virtually no
   * additional overhead beyond standard IBAN validation.
   *
   * @param iban the IBAN to validate (unformatted, e.g., "ES9121000418450200051332")
   * @throws IbanFormatException if IBAN format is invalid
   * @throws InvalidCheckDigitException if IBAN check digit is invalid
   * @throws InvalidNationalCheckDigitException if national check digit is invalid
   * @throws UnsupportedCountryException if country is not supported
   * @example
   *     <pre>{@code
   * try {
   *     IbanValidationUtil.validateWithNationalCheckDigit("ES9121000418450200051332");
   *     System.out.println("✅ IBAN is fully valid");
   * } catch (InvalidNationalCheckDigitException e) {
   *     System.out.printf("❌ National check digit error: %s%n", e.getMessage());
   * } catch (InvalidCheckDigitException e) {
   *     System.out.printf("❌ IBAN check digit error: %s%n", e.getMessage());
   * } catch (IbanFormatException e) {
   *     System.out.printf("❌ IBAN format error: %s%n", e.getMessage());
   * }
   * }</pre>
   */
  public static void validateWithNationalCheckDigit(final String iban)
      throws IbanFormatException,
          InvalidCheckDigitException,
          InvalidNationalCheckDigitException,
          UnsupportedCountryException {

    // First perform standard IBAN validation using existing IbanUtil
    IbanUtil.validate(iban);

    // Then perform national check digit validation if supported
    validateNationalCheckDigit(iban);
  }

  /**
   * Validates IBAN with format support including national check digit validation.
   *
   * <p>This method supports formatted IBANs (with spaces) as defined by {@link IbanFormat#Default}.
   *
   * @param iban the IBAN to validate (can be formatted with spaces)
   * @param format the IBAN format to use
   * @throws IbanFormatException if IBAN format is invalid
   * @throws InvalidCheckDigitException if IBAN check digit is invalid
   * @throws InvalidNationalCheckDigitException if national check digit is invalid
   * @throws UnsupportedCountryException if country is not supported
   * @example
   *     <pre>{@code
   * // Validate formatted IBAN
   * IbanValidationUtil.validateWithNationalCheckDigit("ES91 2100 0418 4502 0005 1332", IbanFormat.Default);
   * }</pre>
   */
  public static void validateWithNationalCheckDigit(final String iban, final IbanFormat format)
      throws IbanFormatException,
          InvalidCheckDigitException,
          InvalidNationalCheckDigitException,
          UnsupportedCountryException {

    // Use existing IbanUtil for format-aware validation
    IbanUtil.validate(iban, format);

    // Extract unformatted IBAN for national check digit validation
    String unformattedIban = (format == IbanFormat.Default) ? iban.replace(" ", "") : iban;

    // Perform national check digit validation
    validateNationalCheckDigit(unformattedIban);
  }

  /**
   * Validates the national check digit of an IBAN.
   *
   * <p>This method only validates the national check digit component. It assumes the IBAN has
   * already passed standard validation.
   *
   * <p><strong>Algorithm:</strong>
   *
   * <ol>
   *   <li>Extract country code and check if national validation is supported
   *   <li>Get the appropriate validator for the country
   *   <li>Extract the national check digit from the BBAN structure
   *   <li>Validate using the country-specific algorithm
   * </ol>
   *
   * @param iban the IBAN to validate (must be unformatted)
   * @throws InvalidNationalCheckDigitException if national check digit is invalid
   * @throws UnsupportedCountryException if country is not supported
   */
  public static void validateNationalCheckDigit(final String iban)
      throws InvalidNationalCheckDigitException, UnsupportedCountryException {

    CountryCode countryCode = CountryCode.getByCode(IbanUtil.getCountryCode(iban));
    if (countryCode == null) {
      throw new UnsupportedCountryException(
          IbanUtil.getCountryCode(iban), "Country code is not supported.");
    }

    // Check if country has national check digit validation
    if (!NationalCheckDigitValidatorFactory.isSupported(countryCode)) {
      // No national check digit validation for this country - that's fine
      return;
    }

    BbanStructure structure = BbanStructure.forCountry(countryCode);
    if (structure == null || !hasNationalCheckDigit(structure)) {
      // No national check digit in BBAN structure
      return;
    }

    NationalCheckDigitValidator validator =
        NationalCheckDigitValidatorFactory.getValidator(countryCode);
    if (validator == null) {
      return; // No validator available
    }

    String bban = IbanUtil.getBban(iban);
    String nationalCheckDigit = extractNationalCheckDigit(bban, structure);

    if (nationalCheckDigit == null) {
      return; // No national check digit found in structure
    }

    if (!validator.validate(bban, nationalCheckDigit)) {
      String expectedCheckDigit = validator.calculate(bban);
      throw new InvalidNationalCheckDigitException(
          String.format(
              "National check digit validation failed for country %s. Expected: %s, Actual: %s",
              countryCode, expectedCheckDigit, nationalCheckDigit),
          nationalCheckDigit,
          expectedCheckDigit,
          countryCode);
    }
  }

  /**
   * Checks if IBAN is valid including national check digit validation.
   *
   * <p>This is a convenience method that returns {@code true} if the IBAN passes all validations,
   * {@code false} otherwise. No exceptions are thrown.
   *
   * <p><strong>Use Case:</strong> Ideal for validation in contexts where you need a simple boolean
   * result without exception handling.
   *
   * @param iban the IBAN to check
   * @return {@code true} if valid (including national check digit), {@code false} otherwise
   * @example
   *     <pre>{@code
   * String[] ibans = {"ES9121000418450200051332", "DE89370400440532013000"};
   * for (String iban : ibans) {
   *     boolean isValid = IbanValidationUtil.isValidWithNationalCheckDigit(iban);
   *     System.out.printf("%s: %s%n", iban, isValid ? "✅ Valid" : "❌ Invalid");
   * }
   * }</pre>
   */
  public static boolean isValidWithNationalCheckDigit(final String iban) {
    try {
      validateWithNationalCheckDigit(iban);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks if IBAN is valid including national check digit validation with format support.
   *
   * @param iban the IBAN to check
   * @param format the IBAN format
   * @return {@code true} if valid (including national check digit), {@code false} otherwise
   * @example
   *     <pre>{@code
   * boolean isValid = IbanValidationUtil.isValidWithNationalCheckDigit(
   *     "ES91 2100 0418 4502 0005 1332", IbanFormat.Default);
   * }</pre>
   */
  public static boolean isValidWithNationalCheckDigit(final String iban, final IbanFormat format) {
    try {
      validateWithNationalCheckDigit(iban, format);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks if a country supports national check digit validation.
   *
   * <p>This method is useful for applications that want to provide different validation behavior
   * based on whether enhanced validation is available.
   *
   * @param countryCode the country code to check
   * @return {@code true} if national check digit validation is supported, {@code false} otherwise
   * @example
   *     <pre>{@code
   * if (IbanValidationUtil.supportsNationalCheckDigit(CountryCode.ES)) {
   *     // Use enhanced validation
   *     boolean isValid = IbanValidationUtil.isValidWithNationalCheckDigit(iban);
   * } else {
   *     // Fall back to standard validation
   *     boolean isValid = IbanUtil.isValid(iban);
   * }
   * }</pre>
   */
  public static boolean supportsNationalCheckDigit(final CountryCode countryCode) {
    return NationalCheckDigitValidatorFactory.isSupported(countryCode);
  }

  /**
   * Returns the country code from an IBAN.
   *
   * <p>Convenience method that delegates to {@link IbanUtil#getCountryCode(String)}.
   *
   * @param iban the IBAN
   * @return the country code
   */
  public static String getCountryCode(final String iban) {
    return IbanUtil.getCountryCode(iban);
  }

  /**
   * Returns the BBAN from an IBAN.
   *
   * <p>Convenience method that delegates to {@link IbanUtil#getBban(String)}.
   *
   * @param iban the IBAN
   * @return the BBAN (Basic Bank Account Number)
   */
  public static String getBban(final String iban) {
    return IbanUtil.getBban(iban);
  }

  /**
   * Checks if a BBAN structure contains a national check digit entry.
   *
   * <p>Helper method to determine if the country's BBAN structure includes a national check digit
   * component.
   *
   * @param structure the BBAN structure to check
   * @return {@code true} if structure contains national check digit, {@code false} otherwise
   */
  private static boolean hasNationalCheckDigit(BbanStructure structure) {
    if (structure == null) {
      return false;
    }

    for (BbanStructureEntry entry : structure.getEntries()) {
      if (entry.getEntryType() == BbanEntryType.national_check_digit) {
        return true;
      }
    }
    return false;
  }

  /**
   * Extracts the national check digit from BBAN based on country structure.
   *
   * <p>This method parses the BBAN according to the country's structure definition and extracts the
   * national check digit portion.
   *
   * @param bban the BBAN
   * @param structure the BBAN structure
   * @return the national check digit, or {@code null} if not found
   */
  private static String extractNationalCheckDigit(String bban, BbanStructure structure) {
    int currentIndex = 0;

    for (BbanStructureEntry entry : structure.getEntries()) {
      int entryLength = entry.getLength();

      if (entry.getEntryType() == BbanEntryType.national_check_digit) {
        if (currentIndex + entryLength <= bban.length()) {
          return bban.substring(currentIndex, currentIndex + entryLength);
        }
      }

      currentIndex += entryLength;
    }

    return null; // No national check digit found
  }
}
