package org.iban4j.countryrules;

/**
 * Helper class to register default country specific rules
 * @deprecated default rule initialization has been moved to {@link CountryRulesRegistry}
 */
@Deprecated(forRemoval = true)
public final class CountryRulesAlgorithms {
  private CountryRulesAlgorithms() {}

  /**
   * Ensures that default country specific rules are registered in {@link CountryRulesRegistry}
   */
  public static void ensureInitialized() {
    // NO op
  }
}
