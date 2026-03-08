package org.iban4j.countryrules.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Iso7064Test {

    @ParameterizedTest
    @CsvSource({
            "13,13",
            "97,0",
            "12345,26",
            "1234a5678,-1",
            "\"\",-1",
            ",-1"
    })
    void mod97_10(String input, int mod97) {
        Assertions.assertEquals(mod97, Iso7064.mod97_10(input));
    }

    @ParameterizedTest
    @CsvSource({
            "1,94",
            "123,19",
            "123a4546,",
            "\"\",",
            ","
    })
    void ribCheckDigits(String input, String expectedDigits) {
        Assertions.assertEquals(expectedDigits, Iso7064.ribCheckDigits(input));
    }
}