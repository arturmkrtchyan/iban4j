package org.iban4j.countryrules.util;

/**
 * Utilities for ISO 7064 MOD 97-10 calculations, implemented in a streaming manner
 * to avoid overflows for long numeric strings.
 */
public final class Iso7064 {

  private Iso7064() {
  }

  /**
   * Compute the MOD 97-10 remainder for a numeric string. Returns -1 for invalid input.
   */
  public static int mod97_10(final String numeric) {
    if (numeric == null || numeric.isEmpty()) {
      return -1;
    }
    int remainder = 0;
    for (int i = 0; i < numeric.length(); i++) {
      final char ch = numeric.charAt(i);
      if (ch < '0' || ch > '9') {
        return -1;
      }
      remainder = remainder * 10 + (ch - '0');
      remainder = remainder % 97;
    }
    return remainder;
  }

  /**
   * Compute French/Tunisian style RIB check digits: (number * 100) % 97 and 97 - remainder.
   * Returns the two-digit string or null on invalid input.
   */
  public static String ribCheckDigits(final String numeric) {
    int remainder = mod97_10(numeric);
    if (remainder < 0) {
      return null;
    }
    int value = 97 - ((remainder * 100) % 97);
    return String.format("%02d", value);
  }
}
