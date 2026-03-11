package org.iban4j.countryrules;

import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanFormatException.IbanFormatViolation;
import org.iban4j.ValidationConfig;

/**
 * Class with static methods to validate {@link Iban} against country specific rules.
 */
public final class CountrySpecificRules {
  private CountrySpecificRules() {}

  /**
   * Validates the {@link Iban} against country specific rules if enabled.
   * @param iban IBAN to validate
   * @param config validation configuration
   * @return {@code true} if IBAN passes country specific validation or if they are disabled
   */
  public static boolean isValid(final Iban iban, final ValidationConfig config) {
    if (config == null || !config.isEnabled()) return true;
    final CountryRulesAlgorithm algorithm = CountryRulesRegistry.get(iban.getCountryCode());
    return algorithm == null || algorithm.validate(iban);
  }

  /**
   * Validates {@link Iban} against country specific rules and throws exception when it is not valid.
   * @see CountrySpecificRules#isValid
   * @param iban IBAN to validate
   * @param config validation configuration
   */
  public static void validate(final Iban iban, final ValidationConfig config) {
    if (!isValid(iban, config)) {
      throw new IbanFormatException(
              IbanFormatViolation.COUNTRY_RULES_FAILED,
              iban,
              "Country-specific rules validation failed for " + iban
      );
    }
  }
}
