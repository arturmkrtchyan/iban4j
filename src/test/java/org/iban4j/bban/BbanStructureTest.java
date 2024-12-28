package org.iban4j.bban;

import org.iban4j.CountryCode;
import org.iban4j.IbanFormatException;
import org.iban4j.UnsupportedCountryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BbanStructureTest")
public class BbanStructureTest {

    @Test
    @DisplayName("Validates correct bank code for supported country")
    public void validateCorrectBankCode() {
        assertDoesNotThrow(() -> BbanStructure.validateBbanEntry(
                CountryCode.FR,
                BbanEntryType.bank_code,
                "12345"
        ));
    }

    @Test
    @DisplayName("Throws exception for incorrect bank code")
    public void validateIncorrectBankCode() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.FR,
                        BbanEntryType.bank_code,
                        "1234"
                )
        );
        assertThat(thrown.getMessage(), containsString("must be exactly 5 characters long."));
    }

    @Test
    @DisplayName("Throws exception for bank code with invalid characters")
    public void validateBankCodeWithInvalidCharacters() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.FR,
                        BbanEntryType.bank_code,
                        "12A45"
                )
        );
        assertThat(thrown.getMessage(), containsString("must contain only digits."));
    }

    @Test
    @DisplayName("Validates correct branch code for supported country")
    public void validateCorrectBranchCode() {
        assertDoesNotThrow(() -> BbanStructure.validateBbanEntry(
                CountryCode.FR,
                BbanEntryType.branch_code,
                "54321"
        ));
    }

    @Test
    @DisplayName("Throws exception for incorrect branch code")
    public void validateIncorrectBranchCode() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.FR,
                        BbanEntryType.branch_code,
                        "5432"
                )
        );
        assertThat(thrown.getMessage(), containsString("must be exactly 5 characters long."));
    }

    @Test
    @DisplayName("Throws exception for branch code with invalid characters")
    public void validateBranchCodeWithInvalidCharacters() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.FR,
                        BbanEntryType.branch_code,
                        "5432B"
                )
        );
        assertThat(thrown.getMessage(), containsString("must contain only digits."));
    }

    @Test
    @DisplayName("Validates correct account number for supported country")
    public void validateCorrectAccountNumber() {
        assertDoesNotThrow(() -> BbanStructure.validateBbanEntry(
                CountryCode.FR,
                BbanEntryType.account_number,
                "ABCDEFGHIJK"
        ));
    }

    @Test
    @DisplayName("Throws exception for incorrect account number")
    public void validateIncorrectAccountNumber() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.FR,
                        BbanEntryType.account_number,
                        "123456"
                )
        );
        assertThat(thrown.getMessage(), containsString("must be exactly 11 characters long."));
    }

    @Test
    @DisplayName("Throws exception for account number with invalid characters")
    public void validateAccountNumberWithInvalidCharacters() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.FR,
                        BbanEntryType.account_number,
                        "ABC@DEFGHIJ"
                )
        );
        assertThat(thrown.getMessage(), containsString("must contain only digits or letters."));
    }

    @Test
    @DisplayName("Validates correct national check digit")
    public void validateCorrectNationalCheckDigit() {
        assertDoesNotThrow(() -> BbanStructure.validateBbanEntry(
                CountryCode.FR,
                BbanEntryType.national_check_digit,
                "12"
        ));
    }

    @Test
    @DisplayName("Throws exception for incorrect national check digit")
    public void validateIncorrectNationalCheckDigit() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.FR,
                        BbanEntryType.national_check_digit,
                        "1A"
                )
        );
        assertThat(thrown.getMessage(), containsString("must contain only digits."));
    }

    @Test
    @DisplayName("Throws exception for national check digit with invalid characters")
    public void validateNationalCheckDigitWithInvalidCharacters() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.FR,
                        BbanEntryType.national_check_digit,
                        "1C"
                )
        );
        assertThat(thrown.getMessage(), containsString("must contain only digits."));
    }

    @Test
    @DisplayName("Validates correct account type")
    public void validateCorrectAccountType() {
        assertDoesNotThrow(() -> BbanStructure.validateBbanEntry(
                CountryCode.BR,
                BbanEntryType.account_type,
                "A"
        ));
    }

    @Test
    @DisplayName("Throws exception for incorrect account type")
    public void validateIncorrectAccountType() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.BR,
                        BbanEntryType.account_type,
                        "AA"
                )
        );
        assertThat(thrown.getMessage(), containsString("must be exactly 1 characters long."));
    }

    @Test
    @DisplayName("Throws exception for account type with invalid characters")
    public void validateAccountTypeWithInvalidCharacters() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.BR,
                        BbanEntryType.account_type,
                        "b"
                )
        );
        assertThat(thrown.getMessage(), containsString("must contain only upper case letters."));
    }

    @Test
    @DisplayName("Validates correct owner account number")
    public void validateCorrectOwnerAccountNumber() {
        assertDoesNotThrow(() -> BbanStructure.validateBbanEntry(
                CountryCode.BR,
                BbanEntryType.owner_account_number,
                "1"
        ));
    }

    @Test
    @DisplayName("Throws exception for incorrect owner account number")
    public void validateIncorrectOwnerAccountNumber() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.BR,
                        BbanEntryType.owner_account_number,
                        "12"
                )
        );
        assertThat(thrown.getMessage(), containsString("must be exactly 1 characters long."));
    }

    @Test
    @DisplayName("Throws exception for owner account number with invalid characters")
    public void validateOwnerAccountNumberWithInvalidCharacters() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.BR,
                        BbanEntryType.owner_account_number,
                        "!"
                )
        );
        assertThat(thrown.getMessage(), containsString("must contain only digits or letters."));
    }

    @Test
    @DisplayName("Validates correct identification number")
    public void validateCorrectIdentificationNumber() {
        assertDoesNotThrow(() -> BbanStructure.validateBbanEntry(
                CountryCode.IS,
                BbanEntryType.identification_number,
                "1234567890"
        ));
    }

    @Test
    @DisplayName("Throws exception for incorrect identification number")
    public void validateIncorrectIdentificationNumber() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.IS,
                        BbanEntryType.identification_number,
                        "ABCDEFGHIJ"
                )
        );
        assertThat(thrown.getMessage(), containsString("must contain only digits."));
    }

    @Test
    @DisplayName("Throws exception for identification number with invalid characters")
    public void validateIdentificationNumberWithInvalidCharacters() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.IS,
                        BbanEntryType.identification_number,
                        "12345ABCDE"
                )
        );
        assertThat(thrown.getMessage(), containsString("must contain only digits."));
    }

    @Test
    @DisplayName("Throws exception for unsupported country")
    public void throwsExceptionForUnsupportedCountry() {
        UnsupportedCountryException thrown = assertThrows(
                UnsupportedCountryException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.AM,
                        BbanEntryType.bank_code,
                        "12345"
                )
        );
        assertThat(thrown.getMessage(), containsString("Country code [AM] is not supported."));
    }

    @Test
    @DisplayName("Throws exception for invalid entry type")
    public void throwsExceptionForInvalidEntryType() {
        IbanFormatException thrown = assertThrows(
                IbanFormatException.class,
                () -> BbanStructure.validateBbanEntry(
                        CountryCode.FR,
                        BbanEntryType.account_type,
                        "A"
                )
        );
        assertThat(thrown.getMessage(), containsString("Entry type [account_type] does not exist for country [FR]"));
    }
}

