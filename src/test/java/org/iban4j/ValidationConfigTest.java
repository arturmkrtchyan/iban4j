package org.iban4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contract test for ValidationConfig.
 * 
 * This test verifies the contract defined in contracts/ValidationConfig.java
 * and ensures the configuration behaves correctly according to the specification.
 */
public class ValidationConfigTest {

    @Test
    public void testBuilderPatternFunctionality() {
        ValidationConfig config = ValidationConfig.builder()
            .enableNationalCheckDigitValidation(true)
            .build();

        assertNotNull(config);
        assertTrue(config.isEnabled());
    }

    @Test
    public void testDefaultConfigurationValues() {
        ValidationConfig config = ValidationConfig.builder().build();

        assertNotNull(config);
        assertFalse(config.isEnabled());
    }


    @Test
    public void testDisabledValidation() {
        ValidationConfig config = ValidationConfig.builder()
            .enableNationalCheckDigitValidation(false)
            .build();

        assertFalse(config.isEnabled());
    }


    @Test
    public void testBuilderChaining() {
        ValidationConfig config = ValidationConfig.builder()
            .enableNationalCheckDigitValidation(true)
            .build();

        assertTrue(config.isEnabled());
    }

    @Test
    public void testEqualsAndHashCode() {
        ValidationConfig config1 = ValidationConfig.builder()
            .enableNationalCheckDigitValidation(true)
            .build();

        ValidationConfig config2 = ValidationConfig.builder()
            .enableNationalCheckDigitValidation(true)
            .build();

        ValidationConfig config3 = ValidationConfig.builder()
            .enableNationalCheckDigitValidation(false)
            .build();

        assertEquals(config1, config2);
        assertEquals(config1.hashCode(), config2.hashCode());
        assertFalse(config1.equals(config3));
    }

    @Test
    public void testToString() {
        ValidationConfig config = ValidationConfig.builder()
            .enableNationalCheckDigitValidation(true)
            .build();

        String toString = config.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("ValidationConfig"));
        assertTrue(toString.contains("enabled=true"));
    }
}