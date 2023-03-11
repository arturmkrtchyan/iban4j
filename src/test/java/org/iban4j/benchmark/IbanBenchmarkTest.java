
package org.iban4j.benchmark;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.IbanUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class IbanBenchmarkTest {

    private static final long LOOPS_COUNT = 1000000;

//    @Rule
//    public TestRule benchmarkRun = new BenchmarkRule();

    @BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
    @Test
    @Disabled
    public void ibanConstruction() {

        for(int i = 0; i < LOOPS_COUNT; i++) {
            Iban iban = new Iban.Builder()
                            .countryCode(CountryCode.DE)
                            .bankCode("52060170")
                            .accountNumber("0012335785")
                            .build();
            Assertions.assertNotNull(iban);
        }
    }

    @BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
    @Test
    @Disabled
    public void ibanValidation() {

        for(int i = 0; i < LOOPS_COUNT; i++) {
            IbanUtil.validate("DE89370400440532013000");
        }
    }
}
