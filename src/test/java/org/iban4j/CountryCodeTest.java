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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DisplayName("Country code Test class")
public class CountryCodeTest {
    @Test
    public void getByCodeWithAlpha2CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha2());
            assertEquals(code,newCode);
        }
    }

    @Test
    public void getByCodeWithLowerCaseAlpha2CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha2().toLowerCase());
            assertEquals(code,newCode);
        }
    }

    @Test
    public void getByCodeWithUpperCaseAlpha2CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha2().toUpperCase());
            assertEquals(code,newCode);
        }
    }

    @Test
    public void getByCodeWithAlpha3CodeShouldReturnCountryCode() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha3());
            assertEquals(code,newCode);
        }
    }

    @Test
    public void getByCodeWithLowerCaseAlpha3CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha3().toLowerCase());
            assertEquals(code,newCode);
        }
    }

    @Test
    public void getByCodeWithUpperCaseAlpha3CodeShouldReturnCountry() {
        for (CountryCode code : CountryCode.values()) {
            CountryCode newCode = CountryCode.getByCode(code.getAlpha3().toUpperCase());
            assertEquals(code,newCode);
        }
    }

    @Test
    public void getByCodeWithNullCodeShouldReturnNull() {
        assertNull(CountryCode.getByCode(null));
    }

    @Test
    public void getByCodeWith4DigitCodeShouldReturnNull() {
        CountryCode code = CountryCode.getByCode("XXXX");
        assertNull(code);
    }

    @Test
    public void getByCodeWithWrongAlpha2CodeShouldReturnNull() {
        CountryCode code = CountryCode.getByCode("XX");
        assertNull(code);
    }

    @Test
    public void getByCodeWithWrongAlpha3CodeShouldReturnNull() {
        CountryCode code = CountryCode.getByCode("XXX");
        assertNull(code);
    }

    @Test
    public void getNameWithDECodeShouldReturnGermany() {
        assertEquals("Germany",CountryCode.DE.getName());
    }

    @Test
    public void getAlpha2WithDECodeShouldReturnGermany() {
        assertEquals("DE",CountryCode.DE.getAlpha2());
    }

    @Test
    public void getAlpha3WithDECodeShouldReturnGermany() {
        assertEquals("DEU",CountryCode.DE.getAlpha3());
    }
}