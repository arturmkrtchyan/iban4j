package org.iban4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValidCheckDigitCalculationTest {
    public static Collection<Object[]> ibanParameters() {
        return TestDataHelper.getIbanData();
    }

    @DisplayName("checkDigitCalculationWithCountryCodeAndBbanShouldReturnCheckDigit")
    @ParameterizedTest(name = "{index} ==> the iban ''{0}'' and expected String is {1}")
    @MethodSource("ibanParameters")
    public void checkDigitCalculationWithCountryCodeAndBbanShouldReturnCheckDigit(Iban iban, String expectedIbanString) {
        String checkDigit = IbanUtil.calculateCheckDigit(iban);
        assertThat(checkDigit, is(equalTo(expectedIbanString.substring(2, 4))));
    }

}
