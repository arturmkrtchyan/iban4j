package org.iban4j.countryrules;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

/**
 * SPI for country-specific rules validation (formerly national check digits).
 */
public interface CountryRulesAlgorithm {
  CountryCode getCountry();
  boolean validate(Iban iban);
}
