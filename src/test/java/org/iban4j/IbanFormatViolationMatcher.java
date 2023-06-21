package org.iban4j;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IbanFormatViolationMatcher extends TypeSafeMatcher<IbanFormatException> {

    private final IbanFormatException.IbanFormatViolation expectedViolation;
    private IbanFormatException.IbanFormatViolation actualViolation;

    public IbanFormatViolationMatcher(IbanFormatException.IbanFormatViolation violation) {
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
