package org.iban4j.benchmark;


import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class IbanBenchmark {

    public static final long LOOPS_COUNT = 100000;

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
    @Test
    public void ibanConstruction() {

        for(int i = 0; i < LOOPS_COUNT; i++) {
            Iban iban = new Iban.Builder()
                            .countryCode(CountryCode.DE)
                            .bankCode("52060170")
                            .accountNumber("0012335785")
                            .build();
        }
    }
}
