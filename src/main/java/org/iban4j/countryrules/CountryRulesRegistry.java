package org.iban4j.countryrules;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.iban4j.CountryCode;
import org.iban4j.countryrules.algorithms.BaNationalCheckDigit;
import org.iban4j.countryrules.algorithms.BeNationalCheckDigit;
import org.iban4j.countryrules.algorithms.EsNationalCheckDigit;
import org.iban4j.countryrules.algorithms.FiNationalCheckDigit;
import org.iban4j.countryrules.algorithms.FrNationalCheckDigit;
import org.iban4j.countryrules.algorithms.ItNationalCheckDigit;
import org.iban4j.countryrules.algorithms.MeNationalCheckDigit;
import org.iban4j.countryrules.algorithms.MkNationalCheckDigit;
import org.iban4j.countryrules.algorithms.NlNationalCheckDigit;
import org.iban4j.countryrules.algorithms.NoNationalCheckDigit;
import org.iban4j.countryrules.algorithms.PtNationalCheckDigit;
import org.iban4j.countryrules.algorithms.RsNationalCheckDigit;
import org.iban4j.countryrules.algorithms.SiNationalCheckDigit;
import org.iban4j.countryrules.algorithms.SkNationalCheckDigit;
import org.iban4j.countryrules.algorithms.TnNationalCheckDigit;

/**
 * Registry of country specific algorithms for {@link org.iban4j.Iban} validation
 */
public final class CountryRulesRegistry {

  private CountryRulesRegistry() {}

/**
   * Register algorithm with country specific IBAN validations.
   * Overrides existing algorithm for that country if any exist
   * @param algorithm algorithm implementation to register.
   */
  public static void register(CountryRulesAlgorithm algorithm) {
    if (algorithm == null) {
        return;
    }
    Holder.COUNTRY_TO_ALGORITHM.put(algorithm.getCountry(), algorithm);
  }

/**
   * Retrieve algorithm with country specific IBAN validations for a given country
   * @param countryCode which country's algorithm to use
   * @return algorithm for given country or {@code null} if none is registered
   */
  public static CountryRulesAlgorithm get(CountryCode countryCode) {
    return Holder.COUNTRY_TO_ALGORITHM.get(countryCode);
  }

/**
   * Clears all registered algorithms
   */
  public static void clear() { Holder.COUNTRY_TO_ALGORITHM.clear(); }

  /**
   * Using private nested class with static field insures that algorithms are initialized in a thread-safe and lazy manner.
   */
  private static class Holder {
    private static final Map<CountryCode, CountryRulesAlgorithm> COUNTRY_TO_ALGORITHM = new ConcurrentHashMap<>();
    static {
      List.of(
              new BeNationalCheckDigit(),
              new EsNationalCheckDigit(),
              new BaNationalCheckDigit(),
              new FiNationalCheckDigit(),
              new FrNationalCheckDigit(),
              new ItNationalCheckDigit(),
              new MkNationalCheckDigit(),
              new MeNationalCheckDigit(),
              new NlNationalCheckDigit(),
              new NoNationalCheckDigit(),
              new PtNationalCheckDigit(),
              new RsNationalCheckDigit(),
              new SkNationalCheckDigit(),
              new SiNationalCheckDigit(),
              new TnNationalCheckDigit()
      ).forEach(rule -> COUNTRY_TO_ALGORITHM.put(rule.getCountry(), rule));
    }
  }
}
