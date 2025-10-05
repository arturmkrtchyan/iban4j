package org.iban4j;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for IbanUtil country rules static methods.
 */
public class IbanUtilCountryRulesTest {

    @Test
    public void testValidateWithCountryRulesWithValidIban() {
        String validIban = "AT611904300234573201";
        assertDoesNotThrow(() -> IbanUtil.validateWithCountryRules(validIban));
    }

    @Test
    public void testValidateWithCountryRulesWithInvalidIban() {
        String invalidIban = "AT621904300234573201";
        assertThrows(InvalidCheckDigitException.class, () -> IbanUtil.validateWithCountryRules(invalidIban));
    }

    @Test
    public void testValidateWithCountryRulesWithInvalidCountryRules() {
        String ibanWithInvalidNationalCheckDigit = "PT52000201231234567850154";

        assertThrows(IbanFormatException.class, () -> IbanUtil.validateWithCountryRules(ibanWithInvalidNationalCheckDigit));
    }

    @Test
    public void testIsValidWithCountryRulesWithValidIban() {
        String validIban = "AT611904300234573201";

        assertTrue(IbanUtil.isValidWithCountryRules(validIban));
    }

    @Test
    public void testIsValidWithCountryRulesWithInvalidIban() {
        String invalidIban = "AT621904300234573201"; // Invalid check digit

        assertFalse(IbanUtil.isValidWithCountryRules(invalidIban));
    }

    @Test
    public void testIsValidWithCountryRulesWithInvalidCountryRules() {
        String ibanWithInvalidNationalCheckDigit = "PT52000201231234567850154";

        assertFalse(IbanUtil.isValidWithCountryRules(ibanWithInvalidNationalCheckDigit));
    }

    @Test
    public void testIsValidWithCountryRulesWithCountryWithoutRegisteredAlgorithm() {
        String ibanWithoutCountryRules = "GB82WEST12345698765432";

        // When & Then - Should pass validation (no algorithm registered)
        assertTrue(IbanUtil.isValidWithCountryRules(ibanWithoutCountryRules));
    }

    @Test
    public void testComparisonBetweenBasicAndCountryRulesValidation() {
        String ibanWithInvalidNationalCheckDigit = "PT52000201231234567850154";

        // Basic validation should pass
        assertTrue(IbanUtil.isValid(ibanWithInvalidNationalCheckDigit));
        
        // Country rules validation should fail
        assertFalse(IbanUtil.isValidWithCountryRules(ibanWithInvalidNationalCheckDigit));
    }
}
