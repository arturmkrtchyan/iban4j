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

/**
 * Base Runtime Exception Class for the library exceptions.
 * @see org.iban4j.IbanFormatException
 * @see org.iban4j.InvalidCheckDigitException
 * @see org.iban4j.UnsupportedCountryException
 * @see org.iban4j.BicFormatException
 */
public abstract class Iban4jException extends RuntimeException {

    public Iban4jException() {
        super();
    }

    public Iban4jException(final String message) {
        super(message);
    }

    public Iban4jException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public Iban4jException(final Throwable cause) {
        super(cause);
    }
}
