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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Iban general test")
public class IbanTest {

    public static class IbanGenerationTest2 {

        @DisplayName("IBANs With Same Data Should Be Equal")
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

        @DisplayName("ibansWithDifferentDataShouldNotBeEqual")
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

        @DisplayName("ibansWithStringValueAndIbanShouldNotBeEqual")
        public void ibansWithStringValueAndIbanShouldNotBeEqual() {
            Iban iban1 = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertNotEquals(iban1, "AT611904300234573201");
        }

        @DisplayName("ibanShouldReturnValidCountryCode")
        public void ibanShouldReturnValidCountryCode() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertThat(iban.getCountryCode(), is(equalTo(CountryCode.AT)));
        }

        @DisplayName("ibanShouldReturnValidBankCode")
        public void ibanShouldReturnValidBankCode() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertThat(iban.getBankCode(), is(equalTo("19043")));
        }

        @DisplayName("ibanShouldReturnValidAccountNumber")
        public void ibanShouldReturnValidAccountNumber() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertThat(iban.getAccountNumber(), is(equalTo("00234573201")));
        }

        @DisplayName("ibanShouldReturnValidBranchCode")
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
        public void ibanShouldReturnValidBban() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();
            assertThat(iban.getBban(), is(equalTo("1904300234573201")));
        }

        @DisplayName("ibanShouldReturnValidCheckDigit")
        public void ibanShouldReturnValidCheckDigit() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("19043")
                    .accountNumber("00234573201")
                    .build();

            assertThat(iban.getCheckDigit(), is(equalTo("61")));
        }

        @DisplayName("ibansWithSameDataShouldHaveSameHashCode")
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

        @DisplayName("ibansWithDifferentDataShouldNotHaveSameHashCode")
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

        @DisplayName("ibanToFormattedStringShouldHaveSpacesAfterEach4Character")
        public void ibanToFormattedStringShouldHaveSpacesAfterEach4Character() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("102345732012")
                    .build();
            assertThat(iban.toFormattedString(), is(equalTo("AT14 1904 1023 4573 2012")));
        }

        @DisplayName("ibanConstructionWithShortBankCodeShouldNotThrowExceptionIfValidationIsDisabled")
        public void ibanConstructionWithShortBankCodeShouldNotThrowExceptionIfValidationIsDisabled() {
            Iban iban = new Iban.Builder()
                    .countryCode(CountryCode.AT)
                    .bankCode("1904")
                    .accountNumber("A0234573201")
                    .build(false);
            assertThat(iban.toFormattedString(), is(equalTo("AT40 1904 A023 4573 201")));
        }

        @DisplayName("ibanConstructionWithNoneFormattingShouldReturnIban")
        public void ibanConstructionWithNoneFormattingShouldReturnIban() {
            Iban iban = Iban.valueOf("AT611904300234573201", IbanFormat.None);
            assertThat(iban.toFormattedString(), is(equalTo("AT61 1904 3002 3457 3201")));
        }

        @DisplayName("ibanConstructionWithDefaultFormattingShouldReturnIban")
        public void ibanConstructionWithDefaultFormattingShouldReturnIban() {
            Iban iban = Iban.valueOf("AT61 1904 3002 3457 3201", IbanFormat.Default);
            assertThat(iban.toFormattedString(), is(equalTo("AT61 1904 3002 3457 3201")));
        }
    }

}
