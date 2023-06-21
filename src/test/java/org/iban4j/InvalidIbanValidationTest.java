package org.iban4j;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.iban4j.IbanFormatException.IbanFormatViolation.COUNTRY_CODE_TWO_LETTERS;
import static org.iban4j.IbanFormatException.IbanFormatViolation.IBAN_NOT_EMPTY;
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
        Matcher<Throwable> ibanNotNullException = is(new IbanFormatException(IBAN_NOT_EMPTY, ""));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate(""));
        assertThat(thrown.getMessage(), containsString("Empty string can't be a valid Iban"));
        assertThat(thrown.getCause(), ibanNotNullException);
    }

    @Test
    public void ibanValidationWithOneCharStringShouldThrowException() {
        Matcher<Throwable> countryCodeException = is(new IbanFormatException(COUNTRY_CODE_TWO_LETTERS, "A", ""));
        Matcher<Object> actualMatcher = is(new IbanFormatException(COUNTRY_CODE_TWO_LETTERS, "A", ""));
        IbanFormatException thrown = assertThrows(IbanFormatException.class,
                () -> IbanUtil.validate("A"));
        assertThat(thrown.getMessage(), containsString("Iban must contain 2 char country code."));
        assertThat(thrown.getCause(), countryCodeException);
        assertThat(thrown.getActual(), actualMatcher);
    }

//    @Test
//    public void ibanValidationWithCountryCodeOnlyShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("Iban must contain 2 digit check digit."));
//        expectedException.expect(new org.iban4j.IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.CHECK_DIGIT_TWO_DIGITS));
//        expectedException.expect(new IbanFormatExceptionActualValueMatcher(""));
//        IbanUtil.validate("AT");
//    }
//
//    @Test
//    public void ibanValidationWithNonDigitCheckDigitShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("Iban's check digit should contain only digits."));
//        expectedException.expect(new org.iban4j.IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.CHECK_DIGIT_ONLY_DIGITS));
//        expectedException.expect(new IbanFormatExceptionActualValueMatcher("4T"));
//        IbanUtil.validate("AT4T");
//    }
//
//    @Test
//    public void ibanValidationWithCountryCodeAndCheckDigitOnlyShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expect(new org.iban4j.IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.BBAN_LENGTH));
//        expectedException.expect(new IbanFormatExceptionActualValueMatcher(0));
//        expectedException.expect(new IbanFormatExceptionExpectedValueMatcher(16));
//        IbanUtil.validate("AT48");
//    }
//
//    @Test
//    public void ibanValidationWithLowercaseCountryShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("Iban country code must contain upper case letters"));
//        expectedException.expect(new org.iban4j.IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.COUNTRY_CODE_UPPER_CASE_LETTERS));
//        expectedException.expect(new IbanFormatExceptionActualValueMatcher("at"));
//        IbanUtil.validate("at611904300234573201");
//    }
//
//    @Test
//    public void ibanValidationWithEmptyCountryShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("Iban country code must contain upper case letters"));
//        expectedException.expect(new org.iban4j.IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.COUNTRY_CODE_UPPER_CASE_LETTERS));
//        expectedException.expect(new IbanFormatExceptionActualValueMatcher(" _"));
//        IbanUtil.validate(" _611904300234573201");
//    }
//
//    @Test(expected = UnsupportedCountryException.class)
//    public void ibanValidationWithNonSupportedCountryShouldThrowException() {
//        IbanUtil.validate("AM611904300234573201");
//    }
//
//    @Test
//    public void ibanValidationWithNonExistingCountryShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("Iban contains non existing country code."));
//        expectedException.expect(new org.iban4j.IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation.COUNTRY_CODE_EXISTS));
//        IbanUtil.validate("JJ611904300234573201");
//    }
//
//    @Test
//    public void ibanValidationWithInvalidCheckDigitShouldThrowException() {
//        expectedException.expect(InvalidCheckDigitException.class);
//        expectedException.expectMessage("invalid check digit: 62");
//        expectedException.expectMessage("expected check digit is: 61");
//        expectedException.expectMessage("AT621904300234573201");
//        IbanUtil.validate("AT621904300234573201");
//    }
//
//    @Test
//    public void ibanValidationWithSpaceShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage("length is 17");
//        expectedException.expectMessage("expected BBAN length is: 16");
//        IbanUtil.validate("AT61 1904300234573201");
//    }
//
//    @Test
//    public void ibanValidationWithInvalidLengthShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        IbanUtil.validate("AT621904300");
//    }
//
//    @Test
//    public void ibanValidationWithInvalidBbanLengthShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("expected BBAN length is:"));
//        IbanUtil.validate("AT61190430023457320");
//    }
//
//    @Test
//    public void ibanValidationWithInvalidBankCodeShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("must contain only digits"));
//        IbanUtil.validate("AT611C04300234573201");
//    }
//
//    @Test
//    public void ibanValidationWithInvalidAccountNumberShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("must contain only digits"));
//        IbanUtil.validate("DE8937040044053201300A");
//    }
//
//    @Test
//    public void ibanValidationWithInvalidNationalCheckDigitShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("must contain only upper case letters"));
//        IbanUtil.validate("IT6010542811101000000123456");
//    }
//
//    @Test
//    public void unformattedIbanValidationWithDefaultFormattingShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("Iban must be formatted using 4 characters and space"));
//        IbanUtil.validate("AT611904300234573201", IbanFormat.Default);
//    }
//
//    @Test
//    public void formattedIbanValidationWithNoneFormattingShouldThrowException() {
//        expectedException.expect(IbanFormatException.class);
//        expectedException.expectMessage(containsString("expected BBAN length is: 16"));
//        IbanUtil.validate("AT61 1904 3002 3457 3201", IbanFormat.None);
//    }


}
