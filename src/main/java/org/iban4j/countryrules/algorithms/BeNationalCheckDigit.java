package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;

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
    try {
      final long number = Long.parseLong(first10);
      final long remainder = number % 97L;
      final long expected = (remainder == 0L) ? 97L : remainder;
      final long actual = Long.parseLong(checkDigits);
      return expected == actual;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
