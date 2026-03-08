package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;

/** Netherlands: Mod 11 with weights 10..1 on account number; allow Postbank starting with 000. */
public final class NlNationalCheckDigit implements CountryRulesAlgorithm {

  @Override
  public CountryCode getCountry() { return CountryCode.NL; }

  @Override
  public boolean validate(Iban iban) {
    final String accountNumber = iban.getAccountNumber();
    if (accountNumber.startsWith("000")) return true; // Postbank heuristic
    final int[] weights = {10,9,8,7,6,5,4,3,2,1};
    int sum = 0;
    for (int i = 0; i < accountNumber.length(); i++) {
      char d = accountNumber.charAt(i);
      if (!Character.isDigit(d)) return false;
      sum += (d - '0') * weights[i];
    }
    return (sum % 11) == 0;
  }
}
