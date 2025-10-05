/**
 * Defines the iban4j module, which provides functionality for
 * validating, generating, and working with International Bank Account Numbers (IBANs)
 * and Business Identifier Codes (BICs).
 *
 * <p>This module exports the core API package {@code org.iban4j}
 * to allow other modules to use its classes, such as {@code Iban}, {@code Bic},
 * and related exception types.
 *
 * <p>The module also opens the {@code org.iban4j} package,
 * primarily to allow for reflection-based operations by frameworks or tools
 * that might need to access non-public members for serialization,
 * dependency injection, or other advanced use cases,
 * particularly for classes within the module (e.g., custom serialization).
 */
module org.iban4j {
  exports org.iban4j; // makes the public types in org.iban4j available to other modules

  opens org.iban4j;   // allows reflective access to all types (public and non-public) in org.iban4j for other modules

  // Country rules
  exports org.iban4j.countryrules;
  exports org.iban4j.countryrules.algorithms;
  exports org.iban4j.countryrules.util;
}
