package org.iban4j;

import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
