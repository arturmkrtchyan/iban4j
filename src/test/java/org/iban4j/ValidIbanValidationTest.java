package org.iban4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidIbanValidationTest {
    public static Collection<Object[]> ibanParameters() {
        final Collection<Object[]> data = new ArrayList<>(TestDataHelper.getIbanData());
        data.addAll(nonStandardButValidIbans());
        return data;
    }

    private static Collection<Object[]> nonStandardButValidIbans() {
        final Collection<Object[]> data = new ArrayList<>();
        // adding custom validation cases.
        // iban with 01 check digit
        data.add(new Object[]{new Iban.Builder()
                .countryCode(CountryCode.TR)
                .bankCode("00123")
                .accountNumber("0882101517977799")
                .nationalCheckDigit("0")
                .build(), "TR010012300882101517977799"});
        // iban with 98 check digit
        data.add(new Object[]{new Iban.Builder()
                .countryCode(CountryCode.TR)
                .bankCode("00123")
                .accountNumber("0882101517977799")
                .nationalCheckDigit("0")
                .build(), "TR980012300882101517977799"});

        return data;
    }

    @DisplayName("ibanValidationWithValidIbanShouldNotThrowException")
    @ParameterizedTest(name = "{index} ==> the iban ''{0}'' and expected String is {1}")
    @MethodSource("ibanParameters")
    public void ibanValidationWithValidIbanShouldNotThrowException(Iban iban, String ibanString) {
        IbanUtil.validate(ibanString);
    }
    @DisplayName("ibanValidationWithValidIbanShouldNotThrowException")
    @ParameterizedTest(name = "{index} ==> the iban ''{0}'' and expected String is {1}")
    @MethodSource("ibanParameters")
    public void ibanIsValidWithValidIbanShouldNotThrowException(Iban iban, String ibanString) {
        assertTrue(IbanUtil.isValid(ibanString));
    }

}
