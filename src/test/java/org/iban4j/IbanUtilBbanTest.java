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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.iban4j.bban.BbanEntryType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("IbanUtil BBAN public API")
public class IbanUtilBbanTest {

    @Nested
    @DisplayName("validateBban")
    class ValidateBban {

        @Test
        @DisplayName("accepts a valid German BBAN")
        void acceptsValidGermanBban() {
            // DE: bankCode(8,n) + accountNumber(10,n) — total 18
            IbanUtil.validateBban(CountryCode.DE, "370400440532013000");
        }

        @Test
        @DisplayName("accepts a valid Norwegian BBAN with national check digit")
        void acceptsValidNorwegianBban() {
            // NO: bankCode(4,n) + accountNumber(6,n) + nationalCheckDigit(1,n) — total 11
            IbanUtil.validateBban(CountryCode.NO, "86011117947");
        }

        @Test
        @DisplayName("accepts a valid Spanish BBAN where national check digit is mid-structure")
        void acceptsValidSpanishBban() {
            // ES: bankCode(4,n) + branchCode(4,n) + nationalCheckDigit(2,n) + accountNumber(10,n) — total 20
            IbanUtil.validateBban(CountryCode.ES, "21000418450200051332");
        }

        @Test
        @DisplayName("accepts a valid Brazilian BBAN with all character classes")
        void acceptsValidBrazilianBban() {
            // BR: bankCode(8,n) + branchCode(5,n) + accountNumber(10,n) + accountType(1,a) + ownerAccountNumber(1,c) — total 25
            IbanUtil.validateBban(CountryCode.BR, "00360305000010009795493P1");
        }

        @Test
        @DisplayName("accepts a valid GB BBAN with letter bank code")
        void acceptsValidGbBban() {
            // GB: bankCode(4,a) + branchCode(6,n) + accountNumber(8,n) — total 18
            IbanUtil.validateBban(CountryCode.GB, "WEST12345698765432");
        }

        @Test
        @DisplayName("rejects BBAN that is too short")
        void rejectsTooShortBban() {
            IbanFormatException ex = assertThrows(IbanFormatException.class,
                    () -> IbanUtil.validateBban(CountryCode.DE, "12345"));
            assertEquals(IbanFormatException.IbanFormatViolation.BBAN_LENGTH,
                    ex.getFormatViolation());
        }

        @Test
        @DisplayName("rejects BBAN that is too long")
        void rejectsTooLongBban() {
            IbanFormatException ex = assertThrows(IbanFormatException.class,
                    () -> IbanUtil.validateBban(CountryCode.DE, "3704004405320130001234567890"));
            assertEquals(IbanFormatException.IbanFormatViolation.BBAN_LENGTH,
                    ex.getFormatViolation());
        }

        @Test
        @DisplayName("rejects BBAN with non-digit in numeric slot")
        void rejectsNonDigitInNumericSlot() {
            // DE expects all digits — inject a letter
            IbanFormatException ex = assertThrows(IbanFormatException.class,
                    () -> IbanUtil.validateBban(CountryCode.DE, "370400440A32013000"));
            assertEquals(IbanFormatException.IbanFormatViolation.BBAN_ONLY_DIGITS,
                    ex.getFormatViolation());
        }

        @Test
        @DisplayName("rejects BBAN with digit in letters-only slot")
        void rejectsDigitInLettersOnlySlot() {
            // GB bankCode is 'a' — inject a digit
            IbanFormatException ex = assertThrows(IbanFormatException.class,
                    () -> IbanUtil.validateBban(CountryCode.GB, "WE1T12345698765432"));
            assertEquals(IbanFormatException.IbanFormatViolation.BBAN_ONLY_UPPER_CASE_LETTERS,
                    ex.getFormatViolation());
        }

        @Test
        @DisplayName("rejects BBAN with non-alphanumeric in 'c' slot")
        void rejectsNonAlphanumericInCSlot() {
            // BR ownerAccountNumber is 'c' — last char. Replace with '!'.
            IbanFormatException ex = assertThrows(IbanFormatException.class,
                    () -> IbanUtil.validateBban(CountryCode.BR, "00360305000010009795493P!"));
            assertEquals(IbanFormatException.IbanFormatViolation.BBAN_ONLY_DIGITS_OR_LETTERS,
                    ex.getFormatViolation());
        }

        @Test
        @DisplayName("rejects null BBAN")
        void rejectsNullBban() {
            assertThrows(IbanFormatException.class,
                    () -> IbanUtil.validateBban(CountryCode.DE, null));
        }

        @Test
        @DisplayName("rejects null country")
        void rejectsNullCountry() {
            assertThrows(IbanFormatException.class,
                    () -> IbanUtil.validateBban(null, "370400440532013000"));
        }

        @Test
        @DisplayName("rejects unsupported country")
        void rejectsUnsupportedCountry() {
            // US is a valid CountryCode enum but has no BBAN structure registered
            assertThrows(UnsupportedCountryException.class,
                    () -> IbanUtil.validateBban(CountryCode.US, "12345678"));
        }
    }

    @Nested
    @DisplayName("extractBbanEntry")
    class ExtractBbanEntry {

        @Test
        @DisplayName("extracts bank code from DE BBAN")
        void extractsBankCodeFromDeBban() {
            String result = IbanUtil.extractBbanEntry(CountryCode.DE,
                    "370400440532013000", BbanEntryType.bank_code);
            assertEquals("37040044", result);
        }

        @Test
        @DisplayName("extracts account number from DE BBAN")
        void extractsAccountNumberFromDeBban() {
            String result = IbanUtil.extractBbanEntry(CountryCode.DE,
                    "370400440532013000", BbanEntryType.account_number);
            assertEquals("0532013000", result);
        }

        @Test
        @DisplayName("extracts national check digit from NO BBAN")
        void extractsNationalCheckDigitFromNoBban() {
            String result = IbanUtil.extractBbanEntry(CountryCode.NO,
                    "86011117947", BbanEntryType.national_check_digit);
            assertEquals("7", result);
        }

        @Test
        @DisplayName("extracts mid-structure national check digit from ES BBAN")
        void extractsMidStructureNationalCheckDigitFromEsBban() {
            String result = IbanUtil.extractBbanEntry(CountryCode.ES,
                    "21000418450200051332", BbanEntryType.national_check_digit);
            assertEquals("45", result);
        }

        @Test
        @DisplayName("extracts owner account number from BR BBAN")
        void extractsOwnerAccountNumberFromBrBban() {
            String result = IbanUtil.extractBbanEntry(CountryCode.BR,
                    "00360305000010009795493P1", BbanEntryType.owner_account_number);
            assertEquals("1", result);
        }

        @Test
        @DisplayName("rejects request for entry type the country does not have")
        void rejectsMissingEntryType() {
            // DE has no branch_code
            IbanFormatException ex = assertThrows(IbanFormatException.class,
                    () -> IbanUtil.extractBbanEntry(CountryCode.DE,
                            "370400440532013000", BbanEntryType.branch_code));
            assertEquals(IbanFormatException.IbanFormatViolation.BBAN_INVALID_ENTRY_TYPE,
                    ex.getFormatViolation());
        }

        @Test
        @DisplayName("rejects BBAN with wrong length")
        void rejectsWrongLengthBban() {
            IbanFormatException ex = assertThrows(IbanFormatException.class,
                    () -> IbanUtil.extractBbanEntry(CountryCode.DE,
                            "12345", BbanEntryType.bank_code));
            assertEquals(IbanFormatException.IbanFormatViolation.BBAN_LENGTH,
                    ex.getFormatViolation());
        }

        @Test
        @DisplayName("rejects null bban")
        void rejectsNullBban() {
            assertThrows(IbanFormatException.class,
                    () -> IbanUtil.extractBbanEntry(CountryCode.DE,
                            null, BbanEntryType.bank_code));
        }

        @Test
        @DisplayName("rejects null country")
        void rejectsNullCountry() {
            assertThrows(IbanFormatException.class,
                    () -> IbanUtil.extractBbanEntry(null,
                            "370400440532013000", BbanEntryType.bank_code));
        }

        @Test
        @DisplayName("rejects null entry type")
        void rejectsNullEntryType() {
            assertThrows(IbanFormatException.class,
                    () -> IbanUtil.extractBbanEntry(CountryCode.DE,
                            "370400440532013000", null));
        }

        @Test
        @DisplayName("rejects unsupported country")
        void rejectsUnsupportedCountry() {
            assertThrows(UnsupportedCountryException.class,
                    () -> IbanUtil.extractBbanEntry(CountryCode.US,
                            "12345678", BbanEntryType.bank_code));
        }
    }

    @Nested
    @DisplayName("Internal extractor regression — Iban getters keep returning null")
    class InternalExtractorRegression {

        @Test
        @DisplayName("Iban.getBranchCode() returns null on DE (no branch code)")
        void deBranchCodeIsNull() {
            assertNull(Iban.valueOf("DE89370400440532013000").getBranchCode());
        }

        @Test
        @DisplayName("Iban.getNationalCheckDigit() returns null on DE (no national check digit)")
        void deNationalCheckDigitIsNull() {
            assertNull(Iban.valueOf("DE89370400440532013000").getNationalCheckDigit());
        }

        @Test
        @DisplayName("Iban.getAccountType() returns null on DE (no account type)")
        void deAccountTypeIsNull() {
            assertNull(Iban.valueOf("DE89370400440532013000").getAccountType());
        }

        @Test
        @DisplayName("Iban.getOwnerAccountType() returns null on DE (no owner account number)")
        void deOwnerAccountTypeIsNull() {
            assertNull(Iban.valueOf("DE89370400440532013000").getOwnerAccountType());
        }

        @Test
        @DisplayName("Iban.getIdentificationNumber() returns null on DE (no identification number)")
        void deIdentificationNumberIsNull() {
            assertNull(Iban.valueOf("DE89370400440532013000").getIdentificationNumber());
        }
    }
}
