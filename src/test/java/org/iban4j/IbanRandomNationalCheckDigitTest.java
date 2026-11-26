/*
 * Copyright 2013 Artur Mkrtchyan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iban4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * Verifies that {@link Iban#random(CountryCode)} produces a national check digit that actually
 * satisfies the country's registered {@link org.iban4j.countryrules.CountryRulesAlgorithm},
 * instead of an arbitrary value that happens to have the right shape.
 *
 * <p>Regression test for https://github.com/arturmkrtchyan/iban4j/issues/46: random IBANs for
 * countries with a national check digit (e.g. France) used to fail country-specific validation
 * essentially 100% of the time.
 */
final class IbanRandomNationalCheckDigitTest {

  // North Macedonia is intentionally excluded from this list: its registered algorithm has a
  // pre-existing bug unrelated to random generation (it assumes a purely numeric BBAN, but the
  // BBAN structure allows an alphanumeric account number).
  @ParameterizedTest
  @EnumSource(
      value = CountryCode.class,
      names = {"BE", "ES", "BA", "FI", "FR", "IT", "ME", "NL", "NO", "PT", "RS", "SK", "SI", "TN"})
  void randomIbanSatisfiesCountryRules(final CountryCode countryCode) {
    final Random random = new Random(42);
    for (int i = 0; i < 50; i++) {
      final Iban iban = new Iban.Builder(random).countryCode(countryCode).buildRandom();
      assertTrue(
          IbanUtil.isValidWithCountryRules(iban.toString()),
          () -> countryCode + " random iban " + iban + " failed its own country-specific rule");
    }
  }

  // In the Netherlands and Slovakia the check is inside the account number itself, and in Norway
  // some bank/account pairs have no valid check digit, so the random parts must be drawn again.
  @ParameterizedTest
  @EnumSource(value = CountryCode.class, names = {"NL", "NO", "SK"})
  void randomIbanKeepsGivenFieldsAndSatisfiesCountryRules(final CountryCode countryCode) {
    final Random random = new Random(7);
    final String bankCode =
        new Iban.Builder(new Random(1)).countryCode(countryCode).buildRandom().getBankCode();
    for (int i = 0; i < 50; i++) {
      final Iban iban =
          new Iban.Builder(random).countryCode(countryCode).bankCode(bankCode).buildRandom();
      assertEquals(bankCode, iban.getBankCode());
      assertTrue(
          IbanUtil.isValidWithCountryRules(iban.toString()),
          () -> countryCode + " random iban " + iban + " failed its own country-specific rule");
    }
  }
}
