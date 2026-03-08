package org.iban4j;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for IbanValidator.
 */
public class IbanValidatorTest {

    @Test
    public void testDefaultValidatorWithValidIban() {
        IbanValidator validator = IbanValidator.builder().build();
        String validIban = "AT611904300234573201";

        assertDoesNotThrow(() -> validator.validate(validIban));
        assertTrue(validator.isValid(validIban));
    }

    @Test
    public void testDefaultValidatorWithInvalidIban() {
        IbanValidator validator = IbanValidator.builder().build();
        String invalidIban = "AT621904300234573201"; // Invalid check digit

        assertThrows(InvalidCheckDigitException.class, () -> validator.validate(invalidIban));
        assertFalse(validator.isValid(invalidIban));
    }

    @Test
    public void testValidatorWithCountryRulesEnabled() {
        IbanValidator validator = IbanValidator.builder()
            .enableCountryRules()
            .build();
        String validIban = "AT611904300234573201";

        assertDoesNotThrow(() -> validator.validate(validIban));
        assertTrue(validator.isValid(validIban));
    }

    @Test
    public void testValidatorWithCountryRulesEnabledAndInvalidCountryRules() {
        IbanValidator validator = IbanValidator.builder()
            .enableCountryRules()
            .build();
        // This IBAN has valid basic format but invalid national check digit
        String ibanWithInvalidNationalCheckDigit = "PT52000201231234567850154";

        assertThrows(IbanFormatException.class, () -> validator.validate(ibanWithInvalidNationalCheckDigit));
        assertFalse(validator.isValid(ibanWithInvalidNationalCheckDigit));
    }

    @Test
    public void testValidatorWithValidationConfig() {
        ValidationConfig config = ValidationConfig.builder()
            .enableCountryRules(true)
            .build();
        IbanValidator validator = IbanValidator.builder()
            .config(config)
            .build();
        String validIban = "AT611904300234573201";

        assertDoesNotThrow(() -> validator.validate(validIban));
        assertTrue(validator.isValid(validIban));
    }

    @Test
    public void testValidatorReusability() {
        IbanValidator validator = IbanValidator.builder()
            .enableCountryRules()
            .build();
        String validIban1 = "AT611904300234573201";
        String validIban2 = "DE89370400440532013000";

        // When & Then - Multiple calls should work
        assertTrue(validator.isValid(validIban1));
        assertTrue(validator.isValid(validIban2));
        assertDoesNotThrow(() -> validator.validate(validIban1));
        assertDoesNotThrow(() -> validator.validate(validIban2));
    }

    @Test
    public void testValidatorWithNullConfig() {
        // Given & When
        IbanValidator validator = IbanValidator.builder()
            .config(null)
            .build();

        // Then - Should not throw and should use default config
        assertNotNull(validator);
        String validIban = "AT611904300234573201";
        assertTrue(validator.isValid(validIban));
    }

    @Test
    public void testCountryRulesFailedExceptionType() {
        IbanValidator validator = IbanValidator.builder()
            .enableCountryRules()
            .build();
        String ibanWithInvalidNationalCheckDigit = "PT52000201231234567850154";

        IbanFormatException exception = assertThrows(IbanFormatException.class, 
            () -> validator.validate(ibanWithInvalidNationalCheckDigit));
        
        // Verify the exception has the correct violation type
        assertTrue(exception.getFormatViolation() == IbanFormatException.IbanFormatViolation.COUNTRY_RULES_FAILED);
    }

    @Test
    public void testValidatorWithCountryWithoutRegisteredAlgorithm() {
        IbanValidator validator = IbanValidator.builder()
            .enableCountryRules()
            .build();
        // Use a country that doesn't have registered country-specific rules
        String ibanWithoutCountryRules = "GB82WEST12345698765432";

        // When & Then - Should pass validation (no algorithm registered)
        assertDoesNotThrow(() -> validator.validate(ibanWithoutCountryRules));
        assertTrue(validator.isValid(ibanWithoutCountryRules));
    }
}
