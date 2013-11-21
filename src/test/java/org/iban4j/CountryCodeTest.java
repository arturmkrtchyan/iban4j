/*
 * Copyright 2013 Artur Mkrtchyan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iban4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CountryCodeTest {
    @Test
    public void getByCodeWithAlpha2CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha2());
            assertThat(newCode, is(equalTo(code)));
        }
    }

    @Test
    public void getByCodeWithLowerCaseAlpha2CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha2().toLowerCase());
            assertThat(newCode, is(equalTo(code)));
        }
    }

    @Test
    public void getByCodeWithUpperCaseAlpha2CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha2().toUpperCase());
            assertThat(newCode, is(equalTo(code)));
        }
    }

    @Test
    public void getByCodeWithAlpha3CodeShouldReturnCountryCode() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha3());
            assertThat(newCode, is(equalTo(code)));
        }
    }

    @Test
    public void getByCodeWithLowerCaseAlpha3CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha3().toLowerCase());
            assertThat(newCode, is(equalTo(code)));
        }
    }

    @Test
    public void getByCodeWithUpperCaseAlpha3CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha3().toUpperCase());
            assertThat(newCode, is(equalTo(code)));
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
        assertThat(CountryCode.DE.getName(), is(equalTo("Germany")));
    }

    @Test
    public void getAlpha2WithDECodeShouldReturnGermany() {
        assertThat(CountryCode.DE.getAlpha2(), is(equalTo("DE")));
    }

    @Test
    public void getAlpha3WithDECodeShouldReturnGermany() {
        assertThat(CountryCode.DE.getAlpha3(), is(equalTo("DEU")));
    }
}