package org.iban4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testing expected exceptions")
public class IbanGenerationExceptionTest {

    @Test()
    public void ibanConstructionWithNonSupportedCountryShouldThrowException() {
        assertThrows(UnsupportedCountryException.class, () -> new Iban.Builder()
                .countryCode(CountryCode.AM)
                .bankCode("0001")
                .branchCode("2030")
                .accountNumber("200359100100")
                .build());
    }

    @Test()
    public void ibanConstructionWithInvalidCountryShouldThrowException() {
        assertThrows(IbanFormatException.class, () ->
                Iban.valueOf("ZZ018786767"));
    }

    @Test()
    public void ibanConstructionWithNullStringShouldThrowException() {
        assertThrows(IbanFormatException.class, () ->
                Iban.valueOf(null));
    }

    @Test()
    public void ibanConstructionWithInvalidCheckDigitShouldThrowException() {
        assertThrows(InvalidCheckDigitException.class, () ->
                Iban.valueOf("AT621904300234573201"));
    }

    @Test()
    public void ibanConstructionWithInvalidBbanCharacterShouldThrowException() {
        assertThrows(IbanFormatException.class, () ->
                Iban.valueOf("AZ21NABZ000000001370100_1944"));
    }

    @Test()
    public void ibanConstructionWithDefaultButInvalidFormatShouldThrowException() {
        assertThrows(IbanFormatException.class, () ->
                Iban.valueOf("AT61 1904 3002 34573201", IbanFormat.Default));
    }

    @Test()
    public void formattedIbanConstructionWithNoneFormatShouldThrowException() {
        assertThrows(IbanFormatException.class, () ->
                Iban.valueOf("AT61 1904 3002 3457 3201", IbanFormat.None));
    }

    @Test()
    public void unformattedIbanConstructionWithDefaultFormatShouldThrowException() {
        assertThrows(IbanFormatException.class, () ->
                Iban.valueOf("AT611904300234573201", IbanFormat.Default));
    }

    @Test()
    public void ibanConstructionWithoutCountryShouldThrowException() {
        assertThrows(IbanFormatException.class, () -> new Iban.Builder()
                .bankCode("0001")
                .branchCode("2030")
                .accountNumber("200359100100")
                .build());
    }

    @Test()
    public void ibanConstructionWithoutBankCodeShouldThrowException() {
        assertThrows(IbanFormatException.class, () -> new Iban.Builder()
                .countryCode(CountryCode.AM)
                .branchCode("2030")
                .accountNumber("200359100100")
                .build());
    }

    @Test()
    public void ibanConstructionWithoutAccountNumberShouldThrowException() {
        assertThrows(IbanFormatException.class, () -> new Iban.Builder()
                .countryCode(CountryCode.AM)
                .bankCode("0001")
                .branchCode("2030")
                .build());
    }

    @Test()
    public void ibanConstructionWithInvalidCharacterShouldThrowException() {
        assertThrows(IbanFormatException.class, () -> new Iban.Builder()
                .countryCode(CountryCode.AT)
                .bankCode("19043")
                .accountNumber("A0234573201")
                .build());
    }

    @Test()
    public void ibanConstructionWithShortBankCodeShouldThrowException() {
        assertThrows(IbanFormatException.class, () -> new Iban.Builder()
                .countryCode(CountryCode.AT)
                .bankCode("1904")
                .accountNumber("A0234573201")
                .build());
    }

    @Test()
    public void ibanConstructionWithShortBankCodeShouldThrowExceptionIfValidationRequested() {
        assertThrows(IbanFormatException.class, () -> new Iban.Builder()
                .countryCode(CountryCode.AT)
                .bankCode("1904")
                .accountNumber("A0234573201")
                .build(true));
    }

    @Test
    public void ibanConstructionRandomAcctRetainsSpecifiedCountry() {
        Iban iban = new Iban.Builder().countryCode(CountryCode.AT).buildRandom();
        assertEquals(CountryCode.AT, iban.getCountryCode());

        iban = Iban.random(CountryCode.AT);
        assertEquals(CountryCode.AT, iban.getCountryCode());
    }

    @Test
    public void ibanConstructionRandomRetainsSpecifiedBankCode() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.AT)
                .bankCode("12345")
                .buildRandom();
        assertEquals("12345", iban.getBankCode());
    }

    @Test
    public void ibanConstructionRandomDoesNotOverwriteBankAccount() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.AT)
                .accountNumber("12345678901")
                .buildRandom();
        assertEquals("12345678901", iban.getAccountNumber());
    }

    @Test
    public void ibanConstructionRandomDoesNotOverwriteBranchCode() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.AL)
                .branchCode("1234")
                .buildRandom();
        assertEquals("1234", iban.getBranchCode());
    }

    @Test
    public void ibanConstructionRandomDoesNotOverwriteNationalCheckDigit() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.AL)
                .nationalCheckDigit("1")
                .buildRandom();
        assertEquals("1", iban.getNationalCheckDigit());
    }

    @Test
    public void ibanConstructionRandomDoesNotOverwriteAccountType() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.BR)
                .accountType("A")
                .buildRandom();
        assertEquals("A", iban.getAccountType());
    }

    @Test
    public void ibanConstructionRandomDoesNotOverwriteOwnerAccountType() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.BR)
                .ownerAccountType("C")
                .buildRandom();
        assertEquals("C", iban.getOwnerAccountType());
    }

    @Test
    public void ibanConstructionRandomDoesNotOverwriteIdentificationNumber() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.IS)
                .identificationNumber("1234567890")
                .buildRandom();
        assertEquals("1234567890", iban.getIdentificationNumber());
    }
}
