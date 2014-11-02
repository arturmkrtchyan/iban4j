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
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotEquals;
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

        @Test
        public void ibanConstructionWithValueOfShouldReturnIban() {
            assertThat(Iban.valueOf(expectedIbanString), is(equalTo(iban)));
        }

        @Parameterized.Parameters
        public static Collection<Object[]> ibanParameters() {
              return TestDataHelper.getIbanData();
        }
    }

    public static class IbanGenerationTest2 {

        @Test
        public void ibansWithSameDataShouldBeEqual() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732012")
                    .build();
            Iban iban2 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732012")
                    .build();

            assertThat(iban1, is(equalTo(iban2)));
        }

        @Test
        public void ibansWithDifferentDataShouldNotBeEqual() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732012")
                    .build();
            Iban iban2 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732011")
                    .build();

            assertThat(iban1, is(not(equalTo(iban2))));
        }

        @Test
        public void ibansWithStringValueAndIbanShouldNotBeEqual() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();

            assertNotEquals(iban1, "AT611904300234573201");
        }

        @Test
        public void ibanShouldReturnValidCountryCode() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();

            assertThat(iban.getCountryCode(), is(equalTo(CountryCode.AT)));
        }

        @Test
        public void ibanShouldReturnValidBankCode() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();

            assertThat(iban.getBankCode(), is(equalTo("19043")));
        }

        @Test
        public void ibanShouldReturnValidAccountNumber() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();

            assertThat(iban.getAccountNumber(), is(equalTo("00234573201")));
        }

        @Test
        public void ibanShouldReturnValidBranchCode() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AD)
                    .bankCode("0001")
                    .branchCode("2030")
                    .accountNumber("200359100100")
                    .build();

            assertThat(iban.getBranchCode(), is(equalTo("2030")));
        }

        @Test
        public void ibanShouldReturnValidNationalCheckDigit() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AL)
                    .bankCode("212")
                    .branchCode("1100")
                    .nationalCheckDigit("9")
                    .accountNumber("0000000235698741")
                    .build();
            assertThat(iban.getNationalCheckDigit(), is(equalTo("9")));
        }

        @Test
        public void ibanShouldReturnValidAccountType() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.BR)
                    .bankCode("00360305")
                    .branchCode("00001")
                    .accountNumber("0009795493")
                    .accountType("P")
                    .ownerAccountType("1")
                    .build();
            assertThat(iban.getAccountType(), is(equalTo("P")));
        }

        @Test
        public void ibanShouldReturnValidOwnerAccountType() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.BR)
                    .bankCode("00360305")
                    .branchCode("00001")
                    .accountNumber("0009795493")
                    .accountType("P")
                    .ownerAccountType("1")
                    .build();
            assertThat(iban.getOwnerAccountType(), is(equalTo("1")));
        }

        @Test
        public void ibanShouldReturnValidIdentificationNumber() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.IS)
                    .bankCode("0159")
                    .branchCode("26")
                    .accountNumber("007654")
                    .identificationNumber("5510730339")
                    .build();
            assertThat(iban.getIdentificationNumber(), is(equalTo("5510730339")));
        }

        @Test
        public void ibanShouldReturnValidBban() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();

            assertThat(iban.getBban(), is(equalTo("1904300234573201")));
        }

        @Test
        public void ibanShouldReturnValidCheckDigit() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();

            assertThat(iban.getCheckDigit(), is(equalTo("61")));
        }

        @Test
        public void ibansWithSameDataShouldHaveSameHashCode() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732012")
                    .build();
            Iban iban2 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732012")
                    .build();

            assertThat(iban1.hashCode(), is(equalTo(iban2.hashCode())));
        }

        @Test
        public void ibansWithDifferentDataShouldNotHaveSameHashCode() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732012")
                    .build();
            Iban iban2 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732011")
                    .build();

            assertThat(iban1.hashCode(), is(not(equalTo(iban2.hashCode()))));
        }

        @Test
        public void ibanToFormattedStringShouldHaveSpacesAfterEach4Character() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732012")
                    .build();
            assertThat(iban.toFormattedString(), is(equalTo("AT14 1904 1023 4573 2012")));
        }
    }

    public static class IbanGenerationExceptionalTest {

        @Test(expected = UnsupportedCountryException.class)
        public void ibanConstructionWithNonSupportedCountryShouldThrowException() {
            new Iban.Builder()
                    .countryCode(CountryCode.AM)
                    .bankCode("0001")
                    .branchCode("2030")
                    .accountNumber("200359100100")
                    .build();
        }

        @Test(expected = IbanFormatException.class)
        public void ibanConstructionWithInvalidCountryShouldThrowException() {
            Iban.valueOf("ZZ018786767");
        }

        @Test(expected = IbanFormatException.class)
        public void ibanConstructionWithNullStringShouldThrowException() {
            Iban.valueOf(null);
        }

        @Test(expected = InvalidCheckDigitException.class)
        public void ibanConstructionWithInvalidCheckDigitShouldThrowException() {
            Iban.valueOf("AT621904300234573201");
        }

        @Test(expected = IbanFormatException.class)
        public void ibanConstructionWithoutCountryShouldThrowException() {
            new Iban.Builder()
                    .bankCode("0001")
                    .branchCode("2030")
                    .accountNumber("200359100100")
                    .build();
        }

        @Test(expected = IbanFormatException.class)
        public void ibanConstructionWithoutBankCodeShouldThrowException() {
            new Iban.Builder()
                    .countryCode(CountryCode.AM)
                    .branchCode("2030")
                    .accountNumber("200359100100")
                    .build();
        }

        @Test(expected = IbanFormatException.class)
        public void ibanConstructionWithoutAccountNumberShouldThrowException() {
            new Iban.Builder()
                    .countryCode(CountryCode.AM)
                    .bankCode("0001")
                    .branchCode("2030")
                    .build();
        }

        @Test(expected = IbanFormatException.class)
        public void ibanConstructionWithInvalidCharacterShouldThrowException() {
            new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("A0234573201")
                    .build();
        }

        @Test(expected = IbanFormatException.class)
        public void ibanConstructionWithShortBankCodeShouldThrowException() {
            new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("A0234573201")
                    .build();
        }
    }
}
