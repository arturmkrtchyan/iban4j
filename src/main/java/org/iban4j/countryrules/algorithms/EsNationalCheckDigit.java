package org.iban4j.countryrules.algorithms;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.countryrules.CountryRulesAlgorithm;

/** Spain: dual modulus 11 with weights 1,2,4,8,5,10,9,7,3,6 over 00+bank+branch and account. */
public final class EsNationalCheckDigit implements CountryRulesAlgorithm {

  private static final int[] WEIGHTS = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};

  /**
   * Created instance of Spain national check digit validator
   */
  public EsNationalCheckDigit() {
  }

  @Override
  public CountryCode getCountry() {
    return CountryCode.ES;
  }

  @Override
  public boolean validate(final Iban iban) {
    final String bankCode = iban.getBankCode();
    final String branchCode = iban.getBranchCode();
    final String accountNumber = iban.getAccountNumber();
    final String checkDigits = iban.getNationalCheckDigit();

    if (bankCode == null || branchCode == null || accountNumber == null || checkDigits == null) {
      return false;
    }

    final String firstData = "00" + bankCode + branchCode;
    final int firstCd = calculateCheckDigit(firstData);
    if (firstCd < 0) {
        return false;
    }

    final int secondCd = calculateCheckDigit(accountNumber);
    if (secondCd < 0) {
        return false;
    }

    final String expected = String.format("%d%d", firstCd, secondCd);
    return expected.equals(checkDigits);
  }

  private static int calculateCheckDigit(String firstData) {
    final int firstSum = weightedSum(firstData);
    if (firstSum < 0) {
      return -1;
    }
    int firstRem = firstSum % 11;
    int firstCd = (firstRem == 0) ? 0 : (11 - firstRem);
    switch (firstCd) {
      case 10:
        return 1;
      case 11:
        return 2;
      default:
        return firstCd;
    }
  }

  private static int weightedSum(final String digits) {
    int sum = 0;
    for (int i = 0; i < digits.length(); i++) {
      final char ch = digits.charAt(i);
      if (ch < '0' || ch > '9') {
        return -1;
      }
      final int weight = WEIGHTS[i % WEIGHTS.length];
      sum += (ch - '0') * weight;
    }
    return sum;
  }
}
