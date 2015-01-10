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

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.iban4j.IbanFormatException.IbanFormatViolation;

@RunWith(Enclosed.class)
public class IbanUtilTest {

    @RunWith(Parameterized.class)
    public static class ValidCheckDigitCalculationTest {

        private final Iban iban;
        private final String expectedIbanString;

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


        private final Character invalidCharacter;

        public InvalidCheckDigitCalculationTest(Character invalidCharacter) {
            this.invalidCharacter = invalidCharacter;
        }

        @Test(expected = IbanFormatException.class)
        public void checkDigitCalculationWithNonNumericBbanShouldThrowException() {

            IbanUtil.calculateCheckDigit("AT000159260" + invalidCharacter + "076545510730339");
        }

        @Parameterized.Parameters
        public static Collection<Character[]> invalidCharacters() {
            return Arrays.asList(new Character[][]{ {'\u216C'}, {'+'} });

        }
    }

    public static class DefaultIbanUtilTest {

        @Test
        public void ibanCountrySupportCheckWithNullShouldReturnFalse() {
            assertThat(IbanUtil.isSupportedCountry(null), is(equalTo(false)));
        }

        @Test
        public void ibanCountrySupportCheckWithSupportedCountryShouldReturnTrue() {
            assertThat(IbanUtil.isSupportedCountry(CountryCode.BE), is(equalTo(true)));
        }

        @Test
        public void ibanCountrySupportCheckWithUnsupportedCountryShouldReturnFalse() {
            assertThat(IbanUtil.isSupportedCountry(CountryCode.AM), is(equalTo(false)));
        }
    }

    public static class InvalidIbanValidationTest {

        @Rule
        public ExpectedException expectedException = ExpectedException.none();


        @Test
        public void ibanValidationWithNullShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("Null can't be a valid Iban"));
            expectedException.expect(new IbanFormatViolationMatcher(IbanFormatViolation.IBAN_NOT_NULL));
            IbanUtil.validate(null);
        }

        @Test
        public void ibanValidationWithEmptyShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("Empty string can't be a valid Iban"));
            expectedException.expect(new IbanFormatViolationMatcher(IbanFormatViolation.IBAN_NOT_EMPTY));
            IbanUtil.validate("");
        }

        @Test
        public void ibanValidationWithOneCharStringShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("Iban must contain 2 char country code."));
            expectedException.expect(new IbanFormatViolationMatcher(IbanFormatViolation.COUNTRY_CODE_TWO_LETTERS));
            expectedException.expect(new IbanFormatExceptionActualValueMatcher("A"));
            IbanUtil.validate("A");
        }

        @Test
        public void ibanValidationWithCountryCodeOnlyShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("Iban must contain 2 digit check digit."));
            expectedException.expect(new IbanFormatViolationMatcher(IbanFormatViolation.CHECK_DIGIT_TWO_DIGITS));
            expectedException.expect(new IbanFormatExceptionActualValueMatcher(""));
            IbanUtil.validate("AT");
        }

        @Test
        public void ibanValidationWithNonDigitCheckDigitShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("Iban's check digit should contain only digits."));
            expectedException.expect(new IbanFormatViolationMatcher(IbanFormatViolation.CHECK_DIGIT_ONLY_DIGITS));
            expectedException.expect(new IbanFormatExceptionActualValueMatcher("4T"));
            IbanUtil.validate("AT4T");
        }

        @Test
        public void ibanValidationWithCountryCodeAndCheckDigitOnlyShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expect(new IbanFormatViolationMatcher(IbanFormatViolation.BBAN_LENGTH));
            expectedException.expect(new IbanFormatExceptionActualValueMatcher(0));
            expectedException.expect(new IbanFormatExceptionExpectedValueMatcher(16));
            IbanUtil.validate("AT48");
        }

        @Test
        public void ibanValidationWithLowercaseCountryShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("Iban country code must contain upper case letters"));
            expectedException.expect(new IbanFormatViolationMatcher(IbanFormatViolation.COUNTRY_CODE_UPPER_CASE_LETTERS));
            expectedException.expect(new IbanFormatExceptionActualValueMatcher("at"));
            IbanUtil.validate("at611904300234573201");
        }

        @Test
        public void ibanValidationWithEmptyCountryShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("Iban country code must contain upper case letters"));
            expectedException.expect(new IbanFormatViolationMatcher(IbanFormatViolation.COUNTRY_CODE_UPPER_CASE_LETTERS));
            expectedException.expect(new IbanFormatExceptionActualValueMatcher(" _"));
            IbanUtil.validate(" _611904300234573201");
        }

        @Test(expected = UnsupportedCountryException.class)
        public void ibanValidationWithNonSupportedCountryShouldThrowException() {
            IbanUtil.validate("AM611904300234573201");
        }

        @Test
        public void ibanValidationWithNonExistingCountryShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("Iban contains non existing country code."));
            expectedException.expect(new IbanFormatViolationMatcher(IbanFormatViolation.COUNTRY_CODE_EXISTS));
            IbanUtil.validate("JJ611904300234573201");
        }

        @Test
        public void ibanValidationWithInvalidCheckDigitShouldThrowException() {
            expectedException.expect(InvalidCheckDigitException.class);
            expectedException.expectMessage("invalid check digit: 62");
            expectedException.expectMessage("expected check digit is: 61");
            expectedException.expectMessage("AT621904300234573201");
            IbanUtil.validate("AT621904300234573201");
        }

        @Test
        public void ibanValidationWithInvalidLengthShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            IbanUtil.validate("AT621904300");
        }

        @Test
        public void ibanValidationWithInvalidBbanLengthShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("expected BBAN length is:"));
            IbanUtil.validate("AT61190430023457320");
        }

        @Test
        public void ibanValidationWithInvalidBankCodeShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("must contain only digits"));
            IbanUtil.validate("AT611C04300234573201");
        }

        @Test
        public void ibanValidationWithInvalidAccountNumberShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("must contain only digits"));
            IbanUtil.validate("DE8937040044053201300A");
        }

        @Test
        public void ibanValidationWithInvalidNationalCheckDigitShouldThrowException() {
            expectedException.expect(IbanFormatException.class);
            expectedException.expectMessage(containsString("must contain only upper case letters"));
            IbanUtil.validate("IT6010542811101000000123456");
        }
    }

    @RunWith(Parameterized.class)
    public static class ValidIbanValidationTest {

        private final String ibanString;

        public ValidIbanValidationTest(Iban iban, String ibanString) {
            this.ibanString = ibanString;
        }

        @Test
        public void ibanValidationWithValidIbanShouldNotThrowException() {
            IbanUtil.validate(ibanString);
        }

        @Parameterized.Parameters
        public static Collection<Object[]> ibanParameters() {
            final Collection<Object[]> data = new ArrayList<Object[]>(TestDataHelper.getIbanData());
            data.addAll(nonStandardButValidIbans());
            return data;
        }

        private static Collection<Object[]> nonStandardButValidIbans() {
            final Collection<Object[]> data = new ArrayList<Object[]>();
            // adding custom validation cases.
            // iban with 01 check digit
            data.add(new Object[]{new Iban.Builder()
                    .countryCode(CountryCode.TR)
                    .bankCode("00123")
                    .accountNumber("0882101517977799")
                    .nationalCheckDigit("0")
                    .build(), "TR010012300882101517977799"});
            // iban with 98 check digit
            data.add(new Object[]{new Iban.Builder()
                    .countryCode(CountryCode.TR)
                    .bankCode("00123")
                    .accountNumber("0882101517977799")
                    .nationalCheckDigit("0")
                    .build(), "TR980012300882101517977799"});

            return data;
        }
    }

    @RunWith(Parameterized.class)
    public static class IbanLengthTest {

        private final Iban iban;
        private final String expectedIbanString;

        public IbanLengthTest(Iban iban, String expectedIbanString) {
            this.iban = iban;
            this.expectedIbanString = expectedIbanString;
        }

        @Test
        public void getIbanLengthShouldReturnValidLength() {
            assertThat(IbanUtil.getIbanLength(iban.getCountryCode()),
                    is(equalTo(expectedIbanString.length())));
        }

        @Parameterized.Parameters
        public static Collection<Object[]> ibanParameters() {
            return TestDataHelper.getIbanData();
        }
    }

    @Ignore
    public static class IbanFormatViolationMatcher extends TypeSafeMatcher<IbanFormatException> {

        private final IbanFormatViolation expectedViolation;
        private IbanFormatViolation actualViolation;

        public IbanFormatViolationMatcher(IbanFormatViolation violation) {
            expectedViolation = violation;
        }

        @Override
        protected boolean matchesSafely(IbanFormatException e) {
            actualViolation = e.getFormatViolation();
            return expectedViolation.equals(actualViolation);
        }

        public void describeTo(Description description) {
            description.appendText("expected ")
                    .appendValue(expectedViolation)
                    .appendText(" but found ")
                    .appendValue(actualViolation);
        }
    }

    @Ignore
    public static class IbanFormatExceptionActualValueMatcher extends TypeSafeMatcher<IbanFormatException> {

        private final Object expectedValue;
        private Object actualValue;

        public IbanFormatExceptionActualValueMatcher(Object expectedValue) {
            this.expectedValue = expectedValue;
        }

        @Override
        protected boolean matchesSafely(IbanFormatException e) {
            actualValue = e.getActual();
            return expectedValue.equals(actualValue);
        }

        public void describeTo(Description description) {
            description.appendText("expected ")
                    .appendValue(expectedValue)
                    .appendText(" but found ")
                    .appendValue(actualValue);
        }
    }

    @Ignore
    public static class IbanFormatExceptionExpectedValueMatcher extends TypeSafeMatcher<IbanFormatException> {

        private final Object expectedValue;
        private Object actualValue;

        public IbanFormatExceptionExpectedValueMatcher(Object expectedValue) {
            this.expectedValue = expectedValue;
        }

        @Override
        protected boolean matchesSafely(IbanFormatException e) {
            actualValue = e.getExpected();
            return expectedValue.equals(actualValue);
        }

        public void describeTo(Description description) {
            description.appendText("expected ")
                    .appendValue(expectedValue)
                    .appendText(" but found ")
                    .appendValue(actualValue);
        }
    }

}
