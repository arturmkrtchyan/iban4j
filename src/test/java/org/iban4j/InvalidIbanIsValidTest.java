package org.iban4j;

import org.hamcrest.Matcher;
import org.iban4j.matcher.IbanFormatExceptionActualValueMatcher;
import org.iban4j.matcher.IbanFormatExceptionExpectedValueMatcher;
import org.iban4j.matcher.IbanFormatViolationMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("InvalidIbanIsValidTest")
public class InvalidIbanIsValidTest {
    @Test
    public void ibanValidationWithNullShouldThrowException() {
        assertFalse(IbanUtil.isValid(null));
    }

    @Test
    public void ibanValidationWithEmptyShouldThrowException() {
        assertFalse(IbanUtil.isValid(""));
    }

    @Test
    public void ibanValidationWithOneCharStringShouldThrowException() {
        assertFalse(IbanUtil.isValid("A"));
    }

    @Test
    public void ibanValidationWithCountryCodeOnlyShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT"));
    }

    @Test
    public void ibanValidationWithNonDigitCheckDigitShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT4T"));
    }

    @Test
    public void ibanValidationWithCountryCodeAndCheckDigitOnlyShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT48"));
    }

    @Test
    public void ibanValidationWithLowercaseCountryShouldThrowException() {
        assertFalse(IbanUtil.isValid("at611904300234573201"));
    }

    @Test
    public void ibanValidationWithEmptyCountryShouldThrowException() {
        assertFalse(IbanUtil.isValid("_611904300234573201"));
    }

    @Test
    public void ibanValidationWithNonSupportedCountryShouldThrowException() {
        assertFalse(IbanUtil.isValid("AM611904300234573201"));
    }

    @Test
    public void ibanValidationWithNonExistingCountryShouldThrowException() {
        assertFalse(IbanUtil.isValid("JJ611904300234573201"));
    }

    @Test
    public void ibanValidationWithInvalidCheckDigitShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT621904300234573201"));
    }

    @Test
    public void ibanValidationWithSpaceShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT61 1904300234573201"));
    }

    @Test
    public void ibanValidationWithInvalidLengthShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT621904300"));
    }

    @Test
    public void ibanValidationWithInvalidBbanLengthShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT61190430023457320"));
    }

    @Test
    public void ibanValidationWithInvalidBankCodeShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT611C04300234573201"));
    }

    @Test
    public void ibanValidationWithInvalidAccountNumberShouldThrowException() {
        assertFalse(IbanUtil.isValid("DE8937040044053201300A"));
    }

    @Test
    public void ibanValidationWithInvalidNationalCheckDigitShouldThrowException() {
        assertFalse(IbanUtil.isValid("IT6010542811101000000123456"));
    }

    @Test
    public void unformattedIbanValidationWithDefaultFormattingShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT611904300234573201",IbanFormat.Default));
    }

    @Test
    public void formattedIbanValidationWithNoneFormattingShouldThrowException() {
        assertFalse(IbanUtil.isValid("AT61 1904 3002 3457 3201"));
    }
}
