package org.iban4j.countryrules;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.iban4j.CountryCode;

public final class CountryRulesRegistry {
  private static final Map<CountryCode, CountryRulesAlgorithm> COUNTRY_TO_ALGORITHM =
      new ConcurrentHashMap<CountryCode, CountryRulesAlgorithm>();
  private CountryRulesRegistry() {}
  public static void register(final CountryRulesAlgorithm algorithm) {
    if (algorithm == null) return;
    COUNTRY_TO_ALGORITHM.put(algorithm.getCountry(), algorithm);
  }
  public static CountryRulesAlgorithm get(final CountryCode countryCode) {
    return COUNTRY_TO_ALGORITHM.get(countryCode);
  }
  public static void clear() { COUNTRY_TO_ALGORITHM.clear(); }
}
