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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Random;

@DisplayName("Iban general test")
public class IbanTest {

    public static class IbanGenerationTest {

        @DisplayName("IBANs With Same Data Should Be Equal")
        @Test
        public void ibansWithSameDataShouldBeEqual() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19041")
                    .accountNumber("02345732012")
                    .build();
            Iban iban2 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19041")
                    .accountNumber("02345732012")
                    .build();

            assertThat(iban1, is(equalTo(iban2)));
        }

        @DisplayName("ibansWithDifferentDataShouldNotBeEqual")
        @Test
        public void ibansWithDifferentDataShouldNotBeEqual() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19041")
                    .accountNumber("02345732012")
                    .build();
            Iban iban2 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19041")
                    .accountNumber("02345732011")
                    .build();

            assertThat(iban1, is(not(equalTo(iban2))));
        }

        @DisplayName("ibansWithStringValueAndIbanShouldNotBeEqual")
        @Test
        public void ibansWithStringValueAndIbanShouldNotBeEqual() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertNotEquals(iban1, "AT611904300234573201");
        }

        @DisplayName("ibanShouldReturnValidCountryCode")
        @Test
        public void ibanShouldReturnValidCountryCode() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertThat(iban.getCountryCode(), is(equalTo(CountryCode.AT)));
        }

        @DisplayName("ibanShouldReturnValidBankCode")
        @Test
        public void ibanShouldReturnValidBankCode() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertThat(iban.getBankCode(), is(equalTo("19043")));
        }

        @DisplayName("ibanShouldReturnValidAccountNumber")
        @Test
        public void ibanShouldReturnValidAccountNumber() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertThat(iban.getAccountNumber(), is(equalTo("00234573201")));
        }

        @DisplayName("ibanShouldReturnValidBranchCode")
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

        @DisplayName("ibanShouldReturnValidNationalCheckDigit")
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

        @DisplayName("ibanShouldReturnValidAccountType")
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

        @DisplayName("ibanShouldReturnValidOwnerAccountType")
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

        @DisplayName("ibanShouldReturnValidIdentificationNumber")
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

        @DisplayName("ibanShouldReturnValidBban")
        @Test
        public void ibanShouldReturnValidBban() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertThat(iban.getBban(), is(equalTo("1904300234573201")));
        }

        @DisplayName("ibanShouldReturnValidCheckDigit")
        @Test
        public void ibanShouldReturnValidCheckDigit() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();

            assertThat(iban.getCheckDigit(), is(equalTo("61")));
        }

        @DisplayName("ibansWithSameDataShouldHaveSameHashCode")
        @Test
        public void ibansWithSameDataShouldHaveSameHashCode() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19041")
                    .accountNumber("02345732012")
                    .build();
            Iban iban2 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19041")
                    .accountNumber("02345732012")
                    .build();

            assertThat(iban1.hashCode(), is(equalTo(iban2.hashCode())));
        }

        @DisplayName("ibansWithDifferentDataShouldNotHaveSameHashCode")
        @Test
        public void ibansWithDifferentDataShouldNotHaveSameHashCode() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19041")
                    .accountNumber("02345732012")
                    .build();
            Iban iban2 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19041")
                    .accountNumber("02345732011")
                    .build();
            assertThat(iban1.hashCode(), is(not(equalTo(iban2.hashCode()))));
        }

        @DisplayName("ibanToFormattedStringShouldHaveSpacesAfterEach4Character")
        @Test
        public void ibanToFormattedStringShouldHaveSpacesAfterEach4Character() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19041")
                    .accountNumber("02345732012")
                    .build();
            assertThat(iban.toFormattedString(), is(equalTo("AT14 1904 1023 4573 2012")));
        }

        @DisplayName("ibanConstructionWithShortBankCodeShouldNotThrowExceptionIfValidationIsDisabled")
        @Test
        public void ibanConstructionWithShortBankCodeShouldNotThrowExceptionIfValidationIsDisabled() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("A0234573201")
                    .build(false);
            assertThat(iban.toFormattedString(), is(equalTo("AT40 1904 A023 4573 201")));
        }

        @DisplayName("ibanConstructionWithNoneFormattingShouldReturnIban")
        @Test
        public void ibanConstructionWithNoneFormattingShouldReturnIban() {
            Iban iban = Iban.valueOf("AT611904300234573201", IbanFormat.None);
            assertThat(iban.toFormattedString(), is(equalTo("AT61 1904 3002 3457 3201")));
        }

        @DisplayName("ibanConstructionWithDefaultFormattingShouldReturnIban")
        @Test
        public void ibanConstructionWithDefaultFormattingShouldReturnIban() {
            Iban iban = Iban.valueOf("AT61 1904 3002 3457 3201", IbanFormat.Default);
            assertThat(iban.toFormattedString(), is(equalTo("AT61 1904 3002 3457 3201")));
        }

        @Test
        public void ibanConstructionRandom() {
            for (int i = 0; i < 100; i++) {
                new Iban.Builder().buildRandom();
                Iban.random();
            }
        }

        @Test
        public void ibanConstructionSeeded() {
            assertIbanUtilRandomWithSeedEquals("TR77 8734 4EAA SPP1 RIYK UO5K 8M", 1);
            assertIbanUtilRandomWithSeedEquals("CH33 2079 06R7 O22Y UE87 R", 2);
            assertIbanUtilRandomWithSeedEquals("BE95 0018 2949 1527", 3);
        }

        private static void assertIbanUtilRandomWithSeedEquals(
            String expected,
            int seed
        ) {
            Iban generated = Iban.random(new Random(seed));
            assertEquals(
                "expect that creating an IBAN with seed '" + seed + "' is deterministic",
                expected,
                generated.toFormattedString()
            );
        }

        @Test
        public void ibanBuilderConstructionSeeded() {
            assertIbanBuilderRandomWithSeedEquals("TR77 8734 4EAA SPP1 RIYK UO5K 8M", 1);
            assertIbanBuilderRandomWithSeedEquals("CH33 2079 06R7 O22Y UE87 R", 2);
            assertIbanBuilderRandomWithSeedEquals("BE95 0018 2949 1527", 3);
        }

        private static void assertIbanBuilderRandomWithSeedEquals(
            String expected,
            int seed
        ) {
            Iban generated = new Iban.Builder(new Random(seed)).buildRandom();
            assertEquals(
                "expect that creating an IBAN with seed '" + seed + "' is deterministic",
                expected,
                generated.toFormattedString()
            );
        }

        @Test
        public void ibanSeededExpectUtilAndBuilderGenerateTheSame() {
            for (int i = 0; i < 100; i++) {
                Iban util = Iban.random(new Random(i));
                Iban builder = new Iban.Builder(new Random(i)).buildRandom();
                assertEquals(
                    "expect that the same random IBAN is generated from both util and builder methods",
                    util,
                    builder
                );
            }
        }

        @Test
        public void ibanConstructionRandomAcctRetainsSpecifiedCountry() {
            Iban iban = new Iban.Builder().countryCode(CountryCode.AT).buildRandom();
            assertThat(iban.getCountryCode(), is(equalTo(CountryCode.AT)));

            iban = Iban.random(CountryCode.AT);
            assertThat(iban.getCountryCode(), is(equalTo(CountryCode.AT)));
        }

        @Test
        public void ibanConstructionRandomRetainsSpecifiedBankCode() {
            Iban iban = new Iban.Builder()
                    .leftPadding(true)
                    .countryCode(CountryCode.DE)
                    .bankCode("66280099")
                    .accountNumber("123456700")
                    .build();
            assertThat(iban.toFormattedString(), is(equalTo("DE90 6628 0099 0123 4567 00")));
        }

        @DisplayName("ibanConstruction with padding non default character")
        @Test
        public void ibanConstructionRandomDoesNotOverwriteBankAccount() {
            Iban iban = new Iban.Builder()
                    .leftPadding(true)
                    .paddingCharacter('1')
                    .countryCode(CountryCode.DE)
                    .bankCode("66280099")
                    .accountNumber("123456700")
                    .build();
            assertThat(iban.toFormattedString(), is(equalTo("DE45 6628 0099 1123 4567 00")));
        }

        @Test
        public void ibanConstructionRandomDoesNotOverwriteBranchCode() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AL)
                    .branchCode("1234")
                    .buildRandom();
            assertThat(iban.getBranchCode(), is(equalTo("1234")));
        }

        @Test
        public void ibanConstructionRandomDoesNotOverwriteNationalCheckDigit() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AL)
                    .nationalCheckDigit("1")
                    .buildRandom();
            assertThat(iban.getNationalCheckDigit(), is(equalTo("1")));
        }

        @Test
        public void ibanConstructionRandomDoesNotOverwriteAccountType() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.BR)
                    .accountType("A")
                    .buildRandom();
            assertThat(iban.getAccountType(), is(equalTo("A")));
        }

        @Test
        public void ibanConstructionRandomDoesNotOverwriteOwnerAccountType() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.BR)
                    .ownerAccountType("C")
                    .buildRandom();
            assertThat(iban.getOwnerAccountType(), is(equalTo("C")));
        }

        @Test
        public void ibanConstructionRandomDoesNotOverwriteIdentificationNumber() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.IS)
                    .identificationNumber("1234567890")
                    .buildRandom();
            assertThat(iban.getIdentificationNumber(), is(equalTo("1234567890")));
        }
    }
}
