package org.iban4j.countryrules;

import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanFormatException.IbanFormatViolation;
import org.iban4j.ValidationConfig;

public final class CountrySpecificRules {
  private CountrySpecificRules() {}

  public static boolean isValid(final Iban iban, final ValidationConfig config) {
    if (config == null || !config.isEnabled()) return true;
    final CountryRulesAlgorithm algorithm = CountryRulesRegistry.get(iban.getCountryCode());
    return algorithm == null || algorithm.validate(iban);
  }

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
