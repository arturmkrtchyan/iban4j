package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;

/** Slovak Republic: Mod 11 two-part validation on account number (prefix and basic). */
public final class SkNationalCheckDigit implements CountryRulesAlgorithm {

  @Override
  public CountryCode getCountry() { return CountryCode.SK; }

  @Override
  public boolean validate(Iban iban) {
    final String accountNumber = iban.getAccountNumber();
    final String first = accountNumber.substring(0, 6);
    final String second = accountNumber.substring(6);
    final int firstRem = mod11WithWeights(first, new int[]{10, 5, 8, 4, 2, 1});
    if (firstRem != 0) return false;
    final int secondRem = mod11WithWeights(second, new int[]{6, 3, 7, 9, 10, 5, 8, 4, 2, 1});
    return secondRem == 0;
  }

  private static int mod11WithWeights(String digits, int[] weights) {
    int sum = 0;
    for (int i = 0; i < digits.length(); i++) {
      int d = digits.charAt(i) - '0';
      int w = weights[i % weights.length];
      sum += d * w;
    }
    return sum % 11;
  }
}
