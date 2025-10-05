package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;
import org.iban4j.countryrules.util.Iso7064;

/** Tunisia: RIB check-digits (numeric RIB from bank+branch+account). */
public final class TnNationalCheckDigit implements CountryRulesAlgorithm {
  @Override
  public CountryCode getCountry() { return CountryCode.TN; }

  @Override
  public boolean validate(Iban iban) {
    final String numericRIB = iban.getBankCode() + iban.getBranchCode() + iban.getAccountNumber();
    final String expected = Iso7064.ribCheckDigits(numericRIB);
    if (expected == null) return false;
    return expected.equals(iban.getNationalCheckDigit());
  }
}
