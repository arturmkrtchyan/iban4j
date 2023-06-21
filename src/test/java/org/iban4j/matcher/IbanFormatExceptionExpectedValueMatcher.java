package org.iban4j.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.iban4j.IbanFormatException;

public class IbanFormatExceptionExpectedValueMatcher extends TypeSafeMatcher<IbanFormatException> {

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

