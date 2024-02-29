package org.iban4j;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

final class IbanGenerationTest {
    public static Collection<Object[]> ibanParameters() {
        return TestDataHelper.getIbanData();
    }

    @DisplayName("Test iban.toString")
    @ParameterizedTest(name = "{index} ==> the iban ''{0}'' and expected String is {1}")
    @MethodSource("ibanParameters")
    public void ibanConstructionWithSupportedCountriesShouldReturnIban(
            final Iban iban,
            final String expectedIbanString
    ) {
        assertThat(iban.toString(), is(equalTo(expectedIbanString)));
    }

    @DisplayName("Test iban.valueOf()")
    @ParameterizedTest(name = "{index} ==> the iban ''{0}'' and expected String is {1}")
    @MethodSource("ibanParameters")
    public void ibanConstructionWithValueOfShouldReturnIban(final Iban iban, final String expectedIbanString) {
        assertThat(Iban.valueOf(expectedIbanString), is(equalTo(iban)));
    }
}
