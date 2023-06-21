package org.iban4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvalidCheckDigitCalculationTest {
    public static Collection<Character[]> invalidCharacters() {
        return Arrays.asList(new Character[][]{{'\u216C'}, {'+'}});

    }

    @DisplayName("checkDigitCalculationWithNonNumericBbanShouldThrowException")
    @ParameterizedTest(name = "{index} ==> the iban ''{0}'' and expected String is {1}")
    @MethodSource("invalidCharacters")

    public void checkDigitCalculationWithNonNumericBbanShouldThrowException(Character invalidCharacter) {
        assertThrows(IbanFormatException.class, () ->
                IbanUtil.calculateCheckDigit("AT000159260" + invalidCharacter + "076545510730339"));
    }

}
