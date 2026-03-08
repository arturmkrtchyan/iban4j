package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;

/** Italy: odd/even positional value mapping over bank+branch+account produces a letter. */
public final class ItNationalCheckDigit implements CountryRulesAlgorithm {

  @Override
  public CountryCode getCountry() {
    return CountryCode.IT;
  }

  @Override
  public boolean validate(Iban iban) {
    final String data = iban.getBankCode() + iban.getBranchCode() + iban.getAccountNumber();
    int sum = 0;
    for (int i = 0; i < data.length(); i++) {
      char c = data.charAt(i);
      int value = ((i + 1) % 2 == 1) ? oddValue(c) : evenValue(c);
      sum += value;
    }
    int remainder = sum % 26;
    char expected = (char) ('A' + remainder);
    return expected == iban.getNationalCheckDigit().charAt(0);
  }

  private static int oddValue(char c) {
    switch (c) {
      case 'A':
      case '0':
        return 1;
      case 'B':
      case '1':
        return 0;
      case 'C':
      case '2':
        return 5;
      case 'D':
      case '3':
        return 7;
      case 'E':
      case '4':
        return 9;
      case 'F':
      case '5':
        return 13;
      case 'G':
      case '6':
        return 15;
      case 'H':
      case '7':
        return 17;
      case 'I':
      case '8':
        return 19;
      case 'J':
      case '9':
        return 21;
      case 'K':
        return 2;
      case 'L':
        return 4;
      case 'M':
        return 18;
      case 'N':
        return 20;
      case 'O':
        return 11;
      case 'P':
        return 3;
      case 'Q':
        return 6;
      case 'R':
        return 8;
      case 'S':
        return 12;
      case 'T':
        return 14;
      case 'U':
        return 16;
      case 'V':
        return 10;
      case 'W':
        return 22;
      case 'X':
        return 25;
      case 'Y':
        return 24;
      case 'Z':
        return 23;
      default:
        return 0;
    }
  }

  private static int evenValue(char c) {
    if (Character.isDigit(c)) return Character.getNumericValue(c);
    return c - 'A' + 10;
  }
}
