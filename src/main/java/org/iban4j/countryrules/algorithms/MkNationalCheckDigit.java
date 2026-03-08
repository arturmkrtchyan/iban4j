package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.util.Iso7064;
import org.iban4j.countryrules.CountryRulesAlgorithm;

/** Macedonia: ISO 7064 MOD 97-10 over BBAN. */
public final class MkNationalCheckDigit implements CountryRulesAlgorithm {
  @Override
  public CountryCode getCountry() { return CountryCode.MK; }
  @Override
  public boolean validate(Iban iban) { return Iso7064.mod97_10(iban.getBban()) == 1; }
}
