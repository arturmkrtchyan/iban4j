package org.iban4j.countryrules;

import java.util.concurrent.atomic.AtomicBoolean;
import org.iban4j.countryrules.algorithms.*;

public final class CountryRulesAlgorithms {
  private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);
  private CountryRulesAlgorithms() {}
  public static void ensureInitialized() {
    if (INITIALIZED.compareAndSet(false, true)) {
      CountryRulesRegistry.register(new BeNationalCheckDigit());
      CountryRulesRegistry.register(new EsNationalCheckDigit());
      CountryRulesRegistry.register(new BaNationalCheckDigit());
      CountryRulesRegistry.register(new FiNationalCheckDigit());
      CountryRulesRegistry.register(new FrNationalCheckDigit());
      CountryRulesRegistry.register(new ItNationalCheckDigit());
      CountryRulesRegistry.register(new MkNationalCheckDigit());
      CountryRulesRegistry.register(new MeNationalCheckDigit());
      CountryRulesRegistry.register(new NlNationalCheckDigit());
      CountryRulesRegistry.register(new NoNationalCheckDigit());
      CountryRulesRegistry.register(new PtNationalCheckDigit());
      CountryRulesRegistry.register(new RsNationalCheckDigit());
      CountryRulesRegistry.register(new SkNationalCheckDigit());
      CountryRulesRegistry.register(new SiNationalCheckDigit());
      CountryRulesRegistry.register(new TnNationalCheckDigit());
    }
  }
}
