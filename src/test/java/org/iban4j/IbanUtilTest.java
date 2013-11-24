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

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class IbanUtilTest {

    @RunWith(Parameterized.class)
    public static class ValidCheckDigitCalculationTest {

        private Iban iban;
        private String expectedIbanString;

        public ValidCheckDigitCalculationTest(Iban iban, String expectedIbanString) {
            this.iban = iban;
            this.expectedIbanString = expectedIbanString;
        }

        @Test
        public void checkDigitCalculationWithCountryCodeAndBbanShouldReturnCheckDigit() {
            String checkDigit = IbanUtil.calculateCheckDigit(iban);
            assertThat(checkDigit, is(equalTo(expectedIbanString.substring(2, 4))));
        }

        @Parameterized.Parameters
        public static Collection<Object[]> ibanParameters() {
            return TestDataHelper.getIbanData();
        }
    }

    @RunWith(Parameterized.class)
    public static class InvalidCheckDigitCalculationTest {


        private Character invalidCharacter;

        public InvalidCheckDigitCalculationTest(Character invalidCharacter) {
            this.invalidCharacter = invalidCharacter;
        }

        @Test(expected = IllegalArgumentException.class)
        public void checkDigitCalculationWithNonNumericBbanShouldThrowException() {

            IbanUtil.calculateCheckDigit("AT000159260" + invalidCharacter + "076545510730339");
        }

        @Parameterized.Parameters
        public static Collection<Character[]> invalidCharacters() {
            return Arrays.asList(new Character[][]{ {'\u216C'}, {'+'} });

        }
    }

}
