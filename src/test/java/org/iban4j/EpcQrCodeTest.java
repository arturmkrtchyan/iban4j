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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Currency;
import org.junit.jupiter.api.Test;

public class EpcQrCodeTest {

  private static final Iban DE_IBAN = Iban.valueOf("DE71110220330123456789");

  @Test
  public void shouldBuildVersionOneExampleFromSpecification() {
    String payload = EpcQrCode.builder()
        .version(EpcQrCode.Version.V1)
        .bic(Bic.valueOf("BHBLDEHHXXX"))
        .beneficiaryName("Franz Mustermänn")
        .iban(DE_IBAN)
        .amount(new BigDecimal("12.30"))
        .purposeCode("GDDS")
        .remittanceReference("RF18539007547034")
        .buildPayload();

    assertEquals("BCD\n001\n1\nSCT\nBHBLDEHHXXX\nFranz Mustermänn\n"
        + "DE71110220330123456789\nEUR12.3\nGDDS\nRF18539007547034", payload);
    assertEquals(96, payload.getBytes(StandardCharsets.UTF_8).length);
  }

  @Test
  public void shouldKeepEmptyFieldsBeforeLastPopulatedField() {
    String payload = EpcQrCode.builder()
        .beneficiaryName("Franz Mustermann")
        .iban(DE_IBAN)
        .remittanceText("Invoice 123")
        .buildPayload();

    assertEquals("BCD\n002\n1\nSCT\n\nFranz Mustermann\nDE71110220330123456789"
        + "\n\n\n\nInvoice 123", payload);
  }

  @Test
  public void shouldRejectBothKindsOfRemittanceInformation() {
    EpcQrCode.Builder builder = validBuilder()
        .remittanceReference("RF18539007547034")
        .remittanceText("Invoice 123");

    assertThrows(IllegalArgumentException.class, builder::build);
  }

  @Test
  public void shouldRequireBicForVersionOne() {
    EpcQrCode.Builder builder = validBuilder().version(EpcQrCode.Version.V1);

    assertThrows(IllegalArgumentException.class, builder::build);
  }

  @Test
  public void shouldRequireBicForNonEeaBeneficiary() {
    EpcQrCode.Builder builder = EpcQrCode.builder()
        .beneficiaryName("Swiss Beneficiary")
        .iban(Iban.valueOf("CH9300762011623852957"));

    assertThrows(IllegalArgumentException.class, builder::build);
  }

  @Test
  public void shouldRejectUnsupportedCurrencyAndInvalidAmounts() {
    assertThrows(IllegalArgumentException.class,
        () -> validBuilder().amount(BigDecimal.ONE, Currency.getInstance("USD")).build());
    assertThrows(IllegalArgumentException.class,
        () -> validBuilder().amount(new BigDecimal("0.001")).build());
    assertThrows(IllegalArgumentException.class,
        () -> validBuilder().amount(new BigDecimal("1000000000")).build());
  }

  @Test
  public void shouldRejectCharactersUnsupportedBySelectedEncoding() {
    EpcQrCode.Builder builder = validBuilder()
        .beneficiaryName("Beneficiary €")
        .characterSet(EpcQrCode.CharacterSet.ISO_8859_1);

    assertThrows(IllegalArgumentException.class, builder::build);
  }

  @Test
  public void shouldEnforcePayloadByteLimitUsingSelectedEncoding() {
    String text = "ä".repeat(140);
    EpcQrCode.Builder builder = validBuilder()
        .beneficiaryName("Beneficiary €")
        .remittanceText(text);

    assertThrows(IllegalArgumentException.class, builder::build);
  }

  @Test
  public void shouldRejectLineSeparatorsAndOverlongFields() {
    assertThrows(IllegalArgumentException.class,
        () -> validBuilder().beneficiaryName("First\nSecond").build());
    assertThrows(IllegalArgumentException.class,
        () -> validBuilder().purposeCode("ABCDE").build());
  }

  private static EpcQrCode.Builder validBuilder() {
    return EpcQrCode.builder().beneficiaryName("Franz Mustermann").iban(DE_IBAN);
  }
}
