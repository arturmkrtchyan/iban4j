package org.iban4j.countryrules;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.iban4j.CountryCode;

/**
 * Registry of country specific algorithms for {@link org.iban4j.Iban} validation
 */
public final class CountryRulesRegistry {
  private static final Map<CountryCode, CountryRulesAlgorithm> COUNTRY_TO_ALGORITHM =
      new ConcurrentHashMap<CountryCode, CountryRulesAlgorithm>();
  private CountryRulesRegistry() {}

  /**
   * Register algorithm with country specific IBAN validations.
   * Overrides existing algorithm for that country if any exist
   * @param algorithm algorithm implementation to register.
   */
  public static void register(final CountryRulesAlgorithm algorithm) {
    if (algorithm == null) return;
    COUNTRY_TO_ALGORITHM.put(algorithm.getCountry(), algorithm);
  }

  /**
   * Retrieve algorithm with country specific IBAN validations for a given country
   * @param countryCode which country's algorithm to use
   * @return algorithm for given country or {@code null} if none is registered
   */
  public static CountryRulesAlgorithm get(final CountryCode countryCode) {
    return COUNTRY_TO_ALGORITHM.get(countryCode);
  }

  /**
   * Clears all registered algorithms
   */
  public static void clear() { COUNTRY_TO_ALGORITHM.clear(); }
}
