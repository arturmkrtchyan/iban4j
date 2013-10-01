package org.iban4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CountryCodeTest {
    @Test
    public void getByCodeWithAlpha2CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha2());
            assertThat(code, is(equalTo(newCode)));

        }
    }

    @Test
    public void getByCodeWithAlpha3CodeShouldReturnCountryCode() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha3());
            assertThat(code, is(equalTo(newCode)));

        }
    }

    @Test
    public void getByCodeWithNullCodeShouldReturnNull() {
        CountryCode code = CountryCode.getByCode(null);
        assertThat(code, is(nullValue()));
    }

    @Test
    public void getByCodeWith4DigitCodeShouldReturnNull() {
        CountryCode code = CountryCode.getByCode("XXXX");
        assertThat(code, is(nullValue()));
    }

    @Test
    public void getByCodeWithWrongAlpha2CodeShouldReturnNull() {
        CountryCode code = CountryCode.getByCode("XX");
        assertThat(code, is(nullValue()));
    }

    @Test
    public void getByCodeWithWrongAlpha3CodeShouldReturnNull() {
        CountryCode code = CountryCode.getByCode("XXX");
        assertThat(code, is(nullValue()));
    }

    @Test
    public void getNameWithDECodeShouldReturnGermany() {
        assertThat("Germany", is(equalTo(CountryCode.DE.getName())));
    }

    @Test
    public void getAlpha2WithDECodeShouldReturnGermany() {
        assertThat("DE", is(equalTo(CountryCode.DE.getAlpha2())));
    }

    @Test
    public void getAlpha3WithDECodeShouldReturnGermany() {
        assertThat("DEU", is(equalTo(CountryCode.DE.getAlpha3())));
    }
}
