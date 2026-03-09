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

public final class CountryRulesRegistry {

  private CountryRulesRegistry() {}

  public static void register(CountryRulesAlgorithm algorithm) {
    if (algorithm == null) {
        return;
    }
    Holder.COUNTRY_TO_ALGORITHM.put(algorithm.getCountry(), algorithm);
  }

  public static CountryRulesAlgorithm get(CountryCode countryCode) {
    return Holder.COUNTRY_TO_ALGORITHM.get(countryCode);
  }

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
