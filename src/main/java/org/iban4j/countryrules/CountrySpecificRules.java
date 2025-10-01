package org.iban4j.countryrules;

import org.iban4j.Iban;
import org.iban4j.ValidationConfig;

public final class CountrySpecificRules {
  private CountrySpecificRules() {}

  public static boolean isValid(final Iban iban, final ValidationConfig config) {
    if (config == null || !config.isEnabled()) return true;
    CountryRulesAlgorithms.ensureInitialized();
    final CountryRulesAlgorithm algorithm = CountryRulesRegistry.get(iban.getCountryCode());
    if (algorithm == null) {
      return true;
    }
    return algorithm.validate(iban);
  }

  public static void validate(final Iban iban, final ValidationConfig config) {
    if (!isValid(iban, config)) {
      throw new IllegalArgumentException("Country-specific rules check failed for " + iban); 
    }
  }
}
