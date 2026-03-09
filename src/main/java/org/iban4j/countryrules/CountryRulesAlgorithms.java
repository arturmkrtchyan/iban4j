package org.iban4j.countryrules;

/**
 * @deprecated default rule initialization has been moved to {@link CountryRulesRegistry}
 */
@Deprecated(forRemoval = true)
public final class CountryRulesAlgorithms {
  private CountryRulesAlgorithms() {}
  public static void ensureInitialized() {
    // NO op
  }
}
