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
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tests Java serialization of {@link Iban} and {@link Bic}.
 *
 * <p>Both classes guard their invariant (every instance holds a validated value) with a
 * {@code readResolve()} method, because deserialization bypasses the private constructor.
 * The tampering tests here pin that behavior so a future refactoring cannot silently
 * remove the re-validation and reopen the door to invalid instances entering a program
 * through a crafted byte stream.</p>
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
   * Simulates a tampered byte stream by replacing the serialized value string in place,
   * preserving the stream's structure (the replacement must have the same length).
   */
  private static byte[] replaceInStream(
      final byte[] stream, final String original, final String replacement) {
    final String streamAsAscii = new String(stream, US_ASCII);
    final int index = streamAsAscii.indexOf(original);
    final byte[] tampered = stream.clone();
    System.arraycopy(replacement.getBytes(US_ASCII), 0, tampered, index, replacement.length());
    return tampered;
  }

  @Nested
  @DisplayName("Iban serialization")
  final class IbanSerializationTest {

    private static final String VALID_IBAN = "DE89370400440532013000";

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
  }

  @Nested
  @DisplayName("Bic serialization")
  final class BicSerializationTest {

    private static final String VALID_BIC = "DEUTDEFF500";

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
  }
}
