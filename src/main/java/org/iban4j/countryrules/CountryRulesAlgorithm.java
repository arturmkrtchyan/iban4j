package org.iban4j.countryrules;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

/**
 * SPI for country-specific rules validation (formerly national check digits).
 */
public interface CountryRulesAlgorithm {
  /**
   * Country to which the algorithm should be applied to
   * @return {@link CountryCode} for which country the rules can be applied
   */
  CountryCode getCountry();

  /**
   * Validates {@link Iban} against rules specific for the country returned by {@link CountryRulesAlgorithm#getCountry()}
   * @param iban IBAN to validate
   * @return {@code true} if IBAN passes validations against country-specific rules
   */
  boolean validate(Iban iban);
}
