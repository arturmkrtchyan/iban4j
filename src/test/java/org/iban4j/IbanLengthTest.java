package org.iban4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class IbanLengthTest {
    public static Collection<Object[]> ibanParameters() {
        return TestDataHelper.getIbanData();
    }

    @DisplayName("Test IBAN length")
    @ParameterizedTest(name = "{index} ==> the iban ''{0}'' and expected String is {1}")
    @MethodSource("ibanParameters")
    public void getIbanLengthShouldReturnValidLength(Iban iban, String expectedIbanString) {
        assertThat(IbanUtil.getIbanLength(iban.getCountryCode()),
                is(equalTo(expectedIbanString.length())));
    }

}
