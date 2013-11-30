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
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class IbanTest {

    @RunWith(Parameterized.class)
    public static class IbanGenerationTest1 {

        private Iban iban;
        private String expectedIbanString;

        public IbanGenerationTest1(Iban iban, String expectedIbanString) {
            this.iban = iban;
            this.expectedIbanString = expectedIbanString;
        }

        @Test
        public void ibanConstructionWithSupportedCountriesShouldReturnIban() {
            assertThat(iban.toString(), is(equalTo(expectedIbanString)));
        }

        @Parameterized.Parameters
        public static Collection<Object[]> ibanParameters() {
              return TestDataHelper.getIbanData();
        }
    }

    public static class IbanGenerationTest2 {

        @Test(expected = UnsupportedCountryException.class)
        public void ibanConstructionWithNonSupportedCountryShouldThrowException() {
            new Iban.Builder()
                    .countryCode(CountryCode.AM)
                    .bankCode("0001")
                    .branchCode("2030")
                    .accountNumber("200359100100")
                    .build();
        }

        @Test(expected = IllegalArgumentException.class)
        public void ibanConstructionWithoutCountryShouldThrowException() {
            new Iban.Builder()
                    .bankCode("0001")
                    .branchCode("2030")
                    .accountNumber("200359100100")
                    .build();
        }

        @Test(expected = IllegalArgumentException.class)
        public void ibanConstructionWithoutBankCodeShouldThrowException() {
            new Iban.Builder()
                    .countryCode(CountryCode.AM)
                    .branchCode("2030")
                    .accountNumber("200359100100")
                    .build();
        }

        @Test(expected = IllegalArgumentException.class)
        public void ibanConstructionWithoutAccountNumberShouldThrowException() {
            new Iban.Builder()
                    .countryCode(CountryCode.AM)
                    .bankCode("0001")
                    .branchCode("2030")
                    .build();
        }

    }
}
