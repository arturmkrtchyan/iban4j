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
 * Thrown to indicate that the requested country is not supported by the IBAN4j library.
 * This can occur when attempting to validate or generate an IBAN/BIC for a country
 * that is not part of the currently recognized ISO 3166-1 country codes or
 * does not have a defined BBAN structure within the library.
 */
public class UnsupportedCountryException extends Iban4jException {

  private static final long serialVersionUID = -3733353745417164234L;

  /**
   * The country code that was requested but is not supported.
   */
  private String countryCode;

  /**
   * Constructs a new {@code UnsupportedCountryException} with no detail message.
   */
  public UnsupportedCountryException() {
    super();
  }

  /**
   * Constructs a new {@code UnsupportedCountryException} with the specified detail message.
   *
   * @param s The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
   */
  public UnsupportedCountryException(final String s) {
    super(s);
  }

  /**
   * Constructs a new {@code UnsupportedCountryException} with the specified country code and
   * detail message.
   *
   * @param countryCode The two-letter (alpha-2) country code that is not supported.
   * @param s The detail message.
   */
  public UnsupportedCountryException(String countryCode, final String s) {
    super(s);
    this.countryCode = countryCode;
  }

  /**
   * Constructs a new {@code UnsupportedCountryException} with the specified detail message and cause.
   *
   * @param s The detail message.
   * @param t The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
   * (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
   */
  public UnsupportedCountryException(final String s, final Throwable t) {
    super(s, t);
  }

  /**
   * Constructs a new {@code UnsupportedCountryException} with the specified cause and a detail message
   * of {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
   *
   * @param t The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
   * (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
   */
  public UnsupportedCountryException(final Throwable t) {
    super(t);
  }

  /**
   * Returns the country code that was deemed unsupported.
   *
   * @return A {@link String} representing the unsupported country code.
   */
  public String getCountryCode() {
    return countryCode;
  }
}
