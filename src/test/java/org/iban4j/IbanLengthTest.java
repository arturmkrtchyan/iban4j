package org.iban4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

final class IbanLengthTest {
    public static Collection<Object[]> ibanParameters() {
        return TestDataHelper.getIbanData();
    }

    @MethodSource("ibanParameters")
    @DisplayName("Test IBAN length")
    @ParameterizedTest(name = "{index} ==> the iban ''{0}'' and expected String is {1}")
    public void getIbanLengthShouldReturnValidLength(final Iban iban, final String expectedIbanString) {
        assertThat(IbanUtil.getIbanLength(iban.getCountryCode()),
                is(equalTo(expectedIbanString.length())));
    }
}
