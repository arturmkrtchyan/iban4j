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
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("InvalidIbanValidationTest")
public class InvalidIbanValidationTest {
    @Test
    public void ibanValidationWithNullShouldThrowException() {
        Matcher<IbanFormatException> ibanNotNullException = is(new IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.IBAN_NOT_NULL));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate(null));
        assertThat(thrown.getMessage(), containsString("Null can't be a valid Iban"));
        assertThat(thrown, ibanNotNullException);
    }

    @Test
    public void ibanValidationWithEmptyShouldThrowException() {
        Matcher<IbanFormatException> ibanNotNullException = is(new IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.IBAN_NOT_EMPTY));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate(""));
        assertThat(thrown.getMessage(), containsString("Empty string can't be a valid Iban"));
        assertThat(thrown, ibanNotNullException);
    }

    @Test
    public void ibanValidationWithOneCharStringShouldThrowException() {
        Matcher<IbanFormatException> countryCodeException = is(new IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.COUNTRY_CODE_TWO_LETTERS));
        Matcher<IbanFormatException> actualMatcher = is(new IbanFormatExceptionActualValueMatcher("A"));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("A"));
        assertThat(thrown.getMessage(), containsString("Iban must contain 2 char country code."));
        assertThat(thrown, countryCodeException);
        assertThat(thrown, actualMatcher);
    }

    @Test
    public void ibanValidationWithCountryCodeOnlyShouldThrowException() {
        Matcher<IbanFormatException> checkDigitMatcher = is(new IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.CHECK_DIGIT_TWO_DIGITS));
        Matcher<IbanFormatException> actualMatcher = is(new IbanFormatExceptionActualValueMatcher(""));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("AT"));
        assertThat(thrown.getMessage(), containsString("Iban must contain 2 digit check digit."));
        assertThat(thrown, checkDigitMatcher);
        assertThat(thrown, actualMatcher);
    }

    @Test
    public void ibanValidationWithNonDigitCheckDigitShouldThrowException() {
        Matcher<IbanFormatException> checkDigitMatcher = is(new IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.CHECK_DIGIT_ONLY_DIGITS));
        Matcher<IbanFormatException> actualMatcher = is(new IbanFormatExceptionActualValueMatcher("4T"));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("AT4T"));
        assertThat(thrown.getMessage(), containsString("Iban's check digit should contain only digits."));
        assertThat(thrown, checkDigitMatcher);
        assertThat(thrown, actualMatcher);
    }

    @Test
    public void ibanValidationWithCountryCodeAndCheckDigitOnlyShouldThrowException() {
        Matcher<IbanFormatException> bbanMatcher = is(new IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.BBAN_LENGTH));
        Matcher<IbanFormatException> actualMatcher = is(new IbanFormatExceptionActualValueMatcher(0));
        Matcher<IbanFormatException> expectedMatcher = is(new IbanFormatExceptionExpectedValueMatcher(16));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("AT48"));
        assertThat(thrown, bbanMatcher);
        assertThat(thrown, actualMatcher);
        assertThat(thrown, expectedMatcher);
    }

    @Test
    public void ibanValidationWithLowercaseCountryShouldThrowException() {
        Matcher<IbanFormatException> countryCodeMatcher = is(new IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.COUNTRY_CODE_UPPER_CASE_LETTERS));
        Matcher<IbanFormatException> actualMatcher = is(new IbanFormatExceptionActualValueMatcher("at"));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("at611904300234573201"));
        assertThat(thrown.getMessage(), containsString("Iban country code must contain upper case letters"));
        assertThat(thrown, countryCodeMatcher);
        assertThat(thrown, actualMatcher);
    }

    @Test
    public void ibanValidationWithEmptyCountryShouldThrowException() {
        Matcher<IbanFormatException> countryCodeMatcher = is(new IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.COUNTRY_CODE_UPPER_CASE_LETTERS));
        Matcher<IbanFormatException> actualMatcher = is(new IbanFormatExceptionActualValueMatcher(" _"));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate(" _611904300234573201"));
        assertThat(thrown.getMessage(), containsString("Iban country code must contain upper case letters"));
        assertThat(thrown, countryCodeMatcher);
        assertThat(thrown, actualMatcher);
    }

    @Test
    public void ibanValidationWithNonSupportedCountryShouldThrowException() {
        assertThrows(UnsupportedCountryException.class,
                () -> IbanUtil.validate("AM611904300234573201"));
    }

    @Test
    public void ibanValidationWithNonExistingCountryShouldThrowException() {
        Matcher<IbanFormatException> countryCodeMatcher = is(new IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.COUNTRY_CODE_EXISTS));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("JJ611904300234573201"));
        assertThat(thrown.getMessage(), containsString("Iban contains non existing country code."));
        assertThat(thrown, countryCodeMatcher);
    }

    @Test
    public void ibanValidationWithInvalidCheckDigitShouldThrowException() {
        InvalidCheckDigitException thrown = assertThrows(InvalidCheckDigitException.class,
                () -> IbanUtil.validate("AT621904300234573201"));
        assertThat(thrown.getMessage(), containsString("AT621904300234573201"));
        assertThat(thrown.getMessage(), containsString("expected check digit is: 61"));
        assertThat(thrown.getMessage(), containsString("invalid check digit: 62"));
    }

    @Test
    public void ibanValidationWithSpaceShouldThrowException() {
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("AT61 1904300234573201"));
        assertThat(thrown.getMessage(), containsString("length is 17"));
        assertThat(thrown.getMessage(), containsString("expected BBAN length is: 16"));
    }

    @Test
    public void ibanValidationWithInvalidLengthShouldThrowException() {
        assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("AT621904300"));
    }

    @Test
    public void ibanValidationWithInvalidBbanLengthShouldThrowException() {
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("AT61190430023457320"));
        assertThat(thrown.getMessage(), containsString("expected BBAN length is:"));
    }

    @Test
    public void ibanValidationWithInvalidBankCodeShouldThrowException() {
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("AT611C04300234573201"));
        assertThat(thrown.getMessage(), containsString("must contain only digits"));
    }

    @Test
    public void ibanValidationWithInvalidAccountNumberShouldThrowException() {
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("DE8937040044053201300A"));
        assertThat(thrown.getMessage(), containsString("must contain only digits"));
    }

    @Test
    public void ibanValidationWithInvalidNationalCheckDigitShouldThrowException() {
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("IT6010542811101000000123456"));
        assertThat(thrown.getMessage(), containsString("must contain only upper case letters"));
    }

    @Test
    public void unformattedIbanValidationWithDefaultFormattingShouldThrowException() {
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("AT611904300234573201", IbanFormat.Default));
        assertThat(thrown.getMessage(), containsString("Iban must be formatted using 4 characters and space"));
    }

    @Test
    public void formattedIbanValidationWithNoneFormattingShouldThrowException() {
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("AT61 1904 3002 3457 3201"));
        assertThat(thrown.getMessage(), containsString("expected BBAN length is: 16"));
    }
}
