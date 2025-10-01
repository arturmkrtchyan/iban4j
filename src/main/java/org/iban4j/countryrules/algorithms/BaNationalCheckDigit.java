package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;
import org.iban4j.countryrules.util.Iso7064;

/** Bosnia and Herzegovina: ISO 7064 MOD 97-10 over entire BBAN. */
public final class BaNationalCheckDigit implements CountryRulesAlgorithm {

  @Override
  public CountryCode getCountry() {
    return CountryCode.BA;
  }

  @Override
  public boolean validate(Iban iban) {
    final String bban = iban.getBban();
    final int remainder = Iso7064.mod97_10(bban);
    return remainder == 1;
  }
}
