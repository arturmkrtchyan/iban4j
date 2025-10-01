package org.iban4j.countryrules.util;

import java.math.BigInteger;

/**
 * Utilities for ISO 7064 MOD 97-10 calculations, implemented in a streaming manner
 * to avoid overflows for long numeric strings.
 */
public final class Iso7064 {

  private static final BigInteger NINETY_SEVEN = BigInteger.valueOf(97);

  private Iso7064() {
  }

  /**
   * Compute the MOD 97-10 remainder for a numeric string. Returns -1 for invalid input.
   */
  public static int mod97_10(final String numeric) {
    if (numeric == null || numeric.length() == 0) {
      return -1;
    }
    BigInteger remainder = BigInteger.ZERO;
    for (int i = 0; i < numeric.length(); i++) {
      final char ch = numeric.charAt(i);
      if (ch < '0' || ch > '9') {
        return -1;
      }
      remainder = remainder.multiply(BigInteger.TEN).add(BigInteger.valueOf(ch - '0'));
      remainder = remainder.mod(NINETY_SEVEN);
    }
    return remainder.intValue();
  }

  /**
   * Compute French/Tunisian style RIB check digits: (number * 100) % 97 and 97 - remainder.
   * Returns the two-digit string or null on invalid input.
   */
  public static String ribCheckDigits(final String numeric) {
    if (numeric == null || numeric.length() == 0) {
      return null;
    }
    for (int i = 0; i < numeric.length(); i++) {
      final char ch = numeric.charAt(i);
      if (ch < '0' || ch > '9') {
        return null;
      }
    }
    BigInteger number = new BigInteger(numeric);
    number = number.multiply(BigInteger.valueOf(100));
    BigInteger remainder = number.mod(NINETY_SEVEN);
    long value = 97 - remainder.longValue();
    return String.format("%02d", value);
  }
}
