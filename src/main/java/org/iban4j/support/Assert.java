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
package org.iban4j.support;

public final class Assert {

    private Assert() {
    }

    /**
     * Assert a boolean expression, throwing {@code IllegalArgumentException}
     * if the test result is {@code false}.
     *
     * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     *
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if expression is {@code false}
     */
    public static void isTrue(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that an object is not null.
     *
     * @param object to check
     * @param message to use if the assertion fails
     * @throws IllegalArgumentException if the object is null
     */
    public static void notNull(final Object object, final String message) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that the given String has expected length.
     * <pre class="code">Assert.hasLength(name, 8, "The length of name must be 8");</pre>
     *
     * @param text the String to check
     * @param length the expected length
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the string doesn't have expected length.
     */
    public static void hasLength(final String text, final int length, final String message) {
        Assert.notNull(text, message);
        if (text.length() != length) {
            throw new IllegalArgumentException(message);
        }
    }

}
