/*
 * Copyright 2013 Artur Mkrtchyan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
 * <p>
 * All custom exceptions within the iban4j library extend this abstract class.
 * This provides a common base for handling specific issues related to IBAN and BIC processing.
 *
 * @see org.iban4j.IbanFormatException
 * @see org.iban4j.InvalidCheckDigitException
 * @see org.iban4j.UnsupportedCountryException
 * @see org.iban4j.BicFormatException
 */
public abstract class Iban4jException extends RuntimeException {

  /**
   * Constructs a new {@code Iban4jException} with no detail message.
   */
  public Iban4jException() {
    super();
  }

  /**
   * Constructs a new {@code Iban4jException} with the specified detail message.
   *
   * @param message The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
   */
  public Iban4jException(final String message) {
    super(message);
  }

  /**
   * Constructs a new {@code Iban4jException} with the specified detail message and cause.
   *
   * @param message The detail message.
   * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
   * (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
   */
  public Iban4jException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new {@code Iban4jException} with the specified cause and a detail message
   * of {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
   *
   * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
   * (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
   */
  public Iban4jException(final Throwable cause) {
    super(cause);
  }
}
