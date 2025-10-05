package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;

/** Norway: Mod 11 with weights 5,4,3,2,7,6,5,4,3,2 on bank+account vs 1-digit check. */
public final class NoNationalCheckDigit implements CountryRulesAlgorithm {
  @Override
  public CountryCode getCountry() { return CountryCode.NO; }
  @Override
  public boolean validate(Iban iban) {
    final String data = iban.getBankCode() + iban.getAccountNumber();
    final String ncd = iban.getNationalCheckDigit();
    final int[] weights = {5,4,3,2,7,6,5,4,3,2};
    int sum = 0;
    for (int i = 0; i < data.length(); i++) {
      char d = data.charAt(i);
      sum += (d - '0') * weights[i];
    }
    int remainder = sum % 11;
    int expected = (remainder == 0) ? 0 : (11 - remainder);
    int actual = ncd.charAt(0) - '0';
    return expected == actual;
  }
}
