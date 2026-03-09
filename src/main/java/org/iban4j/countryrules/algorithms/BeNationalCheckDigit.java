package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;
import org.iban4j.countryrules.util.Iso7064;

/** Belgium: modulus 97 check on bankCode+accountNumber, 00 => 97 rule. */
public final class BeNationalCheckDigit implements CountryRulesAlgorithm {

  @Override
  public CountryCode getCountry() {
    return CountryCode.BE;
  }

  @Override
  public boolean validate(final Iban iban) {
    final String bankCode = iban.getBankCode();
    final String accountNumber = iban.getAccountNumber();
    final String checkDigits = iban.getNationalCheckDigit();

    if (bankCode == null || accountNumber == null || checkDigits == null) {
      return false;
    }
    final String first10 = bankCode + accountNumber;
    final long remainder = Iso7064.mod97_10(first10);
    if (remainder < 0) return false;
    final long expected = (remainder == 0L) ? 97L : remainder;
    try {
      final long actual = Long.parseLong(checkDigits);
      return expected == actual;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
