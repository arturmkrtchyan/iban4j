package org.iban4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;

public class BicCreationTest {

    public static Collection<Object[]> bicParameters() {
        return TestDataHelper.getBicData();
    }

    @DisplayName("Test bicConstructionWithValueOfShouldReturnBic")
    @ParameterizedTest(name = "{index} ==> the bic string ''{0}''")
    @MethodSource("bicParameters")
    public void bicConstructionWithValueOfShouldReturnBic(String bicString) {
        Assertions.assertNotNull(Bic.valueOf(bicString));
    }

}
