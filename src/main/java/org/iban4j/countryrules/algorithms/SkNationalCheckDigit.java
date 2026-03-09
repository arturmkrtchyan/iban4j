package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;

/** Slovak Republic: Mod 11 two-part validation on account number (prefix and basic). */
public final class SkNationalCheckDigit implements CountryRulesAlgorithm {

  private static final int[] FIRST_PART_WEIGHTS = {10, 5, 8, 4, 2, 1};
  private static final int[] SECOND_PART_WEIGHTS = {6, 3, 7, 9, 10, 5, 8, 4, 2, 1};

  @Override
  public CountryCode getCountry() { return CountryCode.SK; }

  @Override
  public boolean validate(Iban iban) {
    final String accountNumber = iban.getAccountNumber();
    final String first = accountNumber.substring(0, 6);
    final String second = accountNumber.substring(6);
    final int firstRem = mod11WithWeights(first, FIRST_PART_WEIGHTS);
    if (firstRem != 0) return false;
    final int secondRem = mod11WithWeights(second, SECOND_PART_WEIGHTS);
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
