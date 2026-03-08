package org.iban4j.countryrules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.junit.jupiter.api.Test;

public class NationalCheckDigitRegistryTest {

    @Test
    public void ensureInitializedRegistersBuiltins() {
        CountryRulesAlgorithms.ensureInitialized();
        assertNotNull(CountryRulesRegistry.get(CountryCode.BE));
        assertNotNull(CountryRulesRegistry.get(CountryCode.ES));
    }

    @Test
    public void registerOverridesExistingAndCanBeRestored() {
        CountryRulesAlgorithms.ensureInitialized();

        final CountryRulesAlgorithm original = CountryRulesRegistry.get(CountryCode.BE);
        final CountryRulesAlgorithm custom = new CountryRulesAlgorithm() {
            @Override
            public CountryCode getCountry() {
                return CountryCode.BE;
            }

            @Override
            public boolean validate(Iban iban) {
                return false; // deterministic custom behavior
            }
        };

        CountryRulesRegistry.register(custom);
        assertEquals(custom, CountryRulesRegistry.get(CountryCode.BE));

        // restore original to avoid side effects on other tests
        CountryRulesRegistry.register(original);
        assertEquals(original, CountryRulesRegistry.get(CountryCode.BE));
    }
}


