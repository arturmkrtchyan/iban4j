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

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import org.iban4j.CountryCode;
import org.iban4j.validator.impl.BelgianNationalCheckDigitValidator;
import org.iban4j.validator.impl.FrenchNationalCheckDigitValidator;
import org.iban4j.validator.impl.ItalianNationalCheckDigitValidator;
import org.iban4j.validator.impl.PortugueseNationalCheckDigitValidator;
import org.iban4j.validator.impl.SpanishNationalCheckDigitValidator;

/**
 * Factory class for creating appropriate national check digit validators
 * based on country codes.
 *
 * <p>This factory manages all available national check digit validators and
 * provides thread-safe access to validator instances.
 *
 * <h3>Supported Countries:</h3>
 * <ul>
 *   <li><strong>Spain (ES)</strong>: 2-digit check digit using MOD 11 algorithm</li>
 *   <li><strong>France (FR)</strong>: 2-digit check digit using MOD 97 algorithm</li>
 *   <li><strong>Italy (IT)</strong>: 1-letter check digit using CIN algorithm</li>
 *   <li><strong>Belgium (BE)</strong>: 2-digit check digit using MOD 97 algorithm</li>
 *   <li><strong>Portugal (PT)</strong>: 2-digit check digit using MOD 97 algorithm</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * // Check if a country supports national check digit validation
 * if (NationalCheckDigitValidatorFactory.isSupported(CountryCode.ES)) {
 *     NationalCheckDigitValidator validator =
 *         NationalCheckDigitValidatorFactory.getValidator(CountryCode.ES);
 *     boolean isValid = validator.validate(bban, checkDigit);
 * }
 * }</pre>
 *
 * <h3>Adding New Countries:</h3>
 * <p>To add support for a new country:
 * <ol>
 *   <li>Implement {@link NationalCheckDigitValidator}</li>
 *   <li>Add the validator to the {@code validators} map in the static initializer</li>
 *   <li>Add comprehensive tests</li>
 *   <li>Update documentation</li>
 * </ol>
 *
 * @author iban4j contributors
 * @since 3.3.0
 * @see NationalCheckDigitValidator
 */
public final class NationalCheckDigitValidatorFactory {

  private static final Map<CountryCode, NationalCheckDigitValidator> validators;

  static {
    Map<CountryCode, NationalCheckDigitValidator> temp = new EnumMap<>(CountryCode.class);

    // Register validators for countries that have national check digits
    temp.put(CountryCode.ES, new SpanishNationalCheckDigitValidator());
    temp.put(CountryCode.FR, new FrenchNationalCheckDigitValidator());
    temp.put(CountryCode.IT, new ItalianNationalCheckDigitValidator());
    temp.put(CountryCode.BE, new BelgianNationalCheckDigitValidator());
    temp.put(CountryCode.PT, new PortugueseNationalCheckDigitValidator());

    validators = Collections.unmodifiableMap(temp);
  }

  /**
   * Private constructor to prevent instantiation.
   */
  private NationalCheckDigitValidatorFactory() {
    // Utility class
  }

  /**
   * Returns the appropriate validator for the given country code.
   *
   * @param countryCode the country code
   * @return the validator for the country, or {@code null} if not supported
   */
  public static NationalCheckDigitValidator getValidator(CountryCode countryCode) {
    return validators.get(countryCode);
  }

  /**
   * Checks if a country has national check digit validation support.
   *
   * @param countryCode the country code
   * @return {@code true} if validation is supported, {@code false} otherwise
   */
  public static boolean isSupported(CountryCode countryCode) {
    return validators.containsKey(countryCode);
  }

  /**
   * Returns a read-only map of all supported countries and their validators.
   *
   * <p>This method is primarily intended for debugging and introspection purposes.
   *
   * @return unmodifiable map of supported countries
   */
  public static Map<CountryCode, NationalCheckDigitValidator> getSupportedCountries() {
    return validators;
  }
}