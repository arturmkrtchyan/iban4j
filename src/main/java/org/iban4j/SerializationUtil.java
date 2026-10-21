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

import java.io.InvalidObjectException;

/** Internal serialization helpers shared between {@link Iban} and {@link Bic}. */
final class SerializationUtil {

  private SerializationUtil() {}

  /**
   * Wraps a validation failure into the {@link InvalidObjectException} required by the
   * serialization contract, preserving it as the cause.
   */
  static InvalidObjectException invalidValue(final String type, final Iban4jException cause) {
    final InvalidObjectException invalidObjectException =
        new InvalidObjectException("Deserialized " + type + " is invalid: " + cause.getMessage());
    // InvalidObjectException has no (String, Throwable) constructor until Java 19.
    invalidObjectException.initCause(cause);
    return invalidObjectException;
  }
}
