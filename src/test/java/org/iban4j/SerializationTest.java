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

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tests Java serialization of {@link Iban} and {@link Bic}: round trips, re-validation of
 * tampered streams (deserialization bypasses the private constructor), and fixed streams
 * from a previous build that guard the wire format against accidental breaks.
 */
@DisplayName("Serialization test")
final class SerializationTest {

  private static byte[] serialize(final Object object) throws IOException {
    final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    try (ObjectOutputStream out = new ObjectOutputStream(bytes)) {
      out.writeObject(object);
    }
    return bytes.toByteArray();
  }

  private static Object deserialize(final byte[] bytes) throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
      return in.readObject();
    }
  }

  /**
   * Simulates tampering by replacing the serialized value string in place. Asserts that
   * the original occurs in the stream and that the lengths match, so the stream's
   * structure (length prefixes) stays intact.
   */
  private static byte[] replaceInStream(
      final byte[] stream, final String original, final String replacement) {
    assertEquals(
        original.length(),
        replacement.length(),
        "Replacement must have the same length as the original to keep the stream intact");
    final String streamAsAscii = new String(stream, US_ASCII);
    final int index = streamAsAscii.indexOf(original);
    assertNotEquals(-1, index, "Original value not found in the serialized stream");
    final byte[] tampered = stream.clone();
    System.arraycopy(replacement.getBytes(US_ASCII), 0, tampered, index, replacement.length());
    return tampered;
  }

  @Nested
  @DisplayName("Iban serialization")
  final class IbanSerializationTest {

    private static final String VALID_IBAN = "DE89370400440532013000";

    /**
     * {@link #VALID_IBAN} as serialized when serialization support was introduced. If
     * deserializing this fails, previously stored instances are broken.
     */
    private static final String SERIALIZED_IBAN_BASE64 =
        "rO0ABXNyAA9vcmcuaWJhbjRqLkliYW4AAAAAAAAAAQIAAUwABXZhbHVldAASTGphdmEvbGFuZy9TdHJpbmc7"
            + "eHB0ABZERTg5MzcwNDAwNDQwNTMyMDEzMDAw";

    @Test
    @DisplayName("Serialization round trip should preserve equality")
    public void serializationRoundTripShouldPreserveEquality() throws Exception {
      final Iban iban = Iban.valueOf(VALID_IBAN);

      final Iban deserialized = (Iban) deserialize(serialize(iban));

      assertEquals(iban, deserialized);
      assertEquals(iban.toString(), deserialized.toString());
    }

    @Test
    @DisplayName("Deserializing a tampered stream should throw")
    public void deserializingTamperedStreamShouldThrow() throws Exception {
      final byte[] tampered =
          replaceInStream(
              serialize(Iban.valueOf(VALID_IBAN)), VALID_IBAN, "DE89370400440532013001");

      final InvalidObjectException thrown =
          assertThrows(InvalidObjectException.class, () -> deserialize(tampered));

      assertInstanceOf(InvalidCheckDigitException.class, thrown.getCause());
    }

    @Test
    @DisplayName("Deserializing a stream from a previous build should succeed")
    public void deserializingStreamFromPreviousBuildShouldSucceed() throws Exception {
      final Object deserialized =
          deserialize(Base64.getDecoder().decode(SERIALIZED_IBAN_BASE64));

      assertEquals(Iban.valueOf(VALID_IBAN), deserialized);
    }
  }

  @Nested
  @DisplayName("Bic serialization")
  final class BicSerializationTest {

    private static final String VALID_BIC = "DEUTDEFF500";

    /**
     * {@link #VALID_BIC} as serialized when serialization support was introduced. If
     * deserializing this fails, previously stored instances are broken.
     */
    private static final String SERIALIZED_BIC_BASE64 =
        "rO0ABXNyAA5vcmcuaWJhbjRqLkJpYwAAAAAAAAABAgABTAAFdmFsdWV0ABJMamF2YS9sYW5nL1N0cmluZzt4"
            + "cHQAC0RFVVRERUZGNTAw";

    @Test
    @DisplayName("Serialization round trip should preserve equality")
    public void serializationRoundTripShouldPreserveEquality() throws Exception {
      final Bic bic = Bic.valueOf(VALID_BIC);

      final Bic deserialized = (Bic) deserialize(serialize(bic));

      assertEquals(bic, deserialized);
      assertEquals(bic.toString(), deserialized.toString());
    }

    @Test
    @DisplayName("Deserializing a tampered stream should throw")
    public void deserializingTamperedStreamShouldThrow() throws Exception {
      final byte[] tampered =
          replaceInStream(serialize(Bic.valueOf(VALID_BIC)), VALID_BIC, "DEUTDEF%500");

      final InvalidObjectException thrown =
          assertThrows(InvalidObjectException.class, () -> deserialize(tampered));

      assertInstanceOf(BicFormatException.class, thrown.getCause());
    }

    @Test
    @DisplayName("Deserializing a stream from a previous build should succeed")
    public void deserializingStreamFromPreviousBuildShouldSucceed() throws Exception {
      final Object deserialized =
          deserialize(Base64.getDecoder().decode(SERIALIZED_BIC_BASE64));

      assertEquals(Bic.valueOf(VALID_BIC), deserialized);
    }
  }
}
