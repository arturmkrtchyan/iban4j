package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;

/** Finland: Mod 10 with weights 2,1,2,1 from right to left over bank+account. */
public final class FiNationalCheckDigit implements CountryRulesAlgorithm {

  @Override
  public CountryCode getCountry() {
    return CountryCode.FI;
  }

  @Override
  public boolean validate(Iban iban) {
    final String dataDigits = iban.getBankCode() + iban.getAccountNumber();
    final String ncd = iban.getNationalCheckDigit();
    final int[] weights = {2, 1, 2, 1};
    int sum = 0;
    for (int i = 0; i < dataDigits.length(); i++) {
      char digit = dataDigits.charAt(dataDigits.length() - 1 - i);
      int weight = weights[i % 4];
      int product = Character.getNumericValue(digit) * weight;
      if (product >= 10) product = (product / 10) + (product % 10);
      sum += product;
    }
    int expected = (10 - (sum % 10)) % 10;
    int actual = Character.getNumericValue(ncd.charAt(0));
    return expected == actual;
  }
}
