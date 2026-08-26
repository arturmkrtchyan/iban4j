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

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.Currency;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * An EPC069-12 payload used to initiate a SEPA Credit Transfer (also known as a Girocode).
 *
 * <p>This class only creates the text encoded by a QR code. It does not render the QR code itself.
 */
public final class EpcQrCode {

  private static final int MAX_PAYLOAD_BYTES = 331;
  private static final Currency EURO = Currency.getInstance("EUR");

  private final String payload;

  private EpcQrCode(final String payload) {
    this.payload = payload;
  }

  /**
   * Creates a builder using EPC payload version 002 and UTF-8.
   *
   * @return a new builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Returns the plain-text EPC payload.
   *
   * @return the payload, with fields separated by line feeds
   */
  public String getPayload() {
    return payload;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return payload;
  }

  /** EPC069-12 payload versions. */
  public enum Version {
    /** Version 001, in which a BIC is mandatory. */
    V1("001"),
    /** Version 002, in which a BIC is optional for beneficiary PSPs in the EEA. */
    V2("002");

    private final String value;

    Version(final String value) {
      this.value = value;
    }
  }

  /** Character sets defined by EPC069-12. */
  public enum CharacterSet {
    /** UTF-8. */
    UTF_8("1", "UTF-8"),
    /** ISO 8859-1. */
    ISO_8859_1("2", "ISO-8859-1"),
    /** ISO 8859-2. */
    ISO_8859_2("3", "ISO-8859-2"),
    /** ISO 8859-4. */
    ISO_8859_4("4", "ISO-8859-4"),
    /** ISO 8859-5. */
    ISO_8859_5("5", "ISO-8859-5"),
    /** ISO 8859-7. */
    ISO_8859_7("6", "ISO-8859-7"),
    /** ISO 8859-10. */
    ISO_8859_10("7", "ISO-8859-10"),
    /** ISO 8859-15. */
    ISO_8859_15("8", "ISO-8859-15");

    private final String code;
    private final String charsetName;

    CharacterSet(final String code, final String charsetName) {
      this.code = code;
      this.charsetName = charsetName;
    }

    /**
     * Returns the standard charset name represented by this EPC character-set identifier.
     *
     * <p>ISO-8859-10 is not included in every Java runtime's charset providers.
     *
     * @return the standard charset name
     */
    public String getCharsetName() {
      return charsetName;
    }
  }

  /** Builder for an {@link EpcQrCode}. */
  public static final class Builder {

    private static final Set<CountryCode> EEA_COUNTRIES = EnumSet.of(
        CountryCode.AT, CountryCode.BE, CountryCode.BG, CountryCode.HR, CountryCode.CY,
        CountryCode.CZ, CountryCode.DE, CountryCode.DK, CountryCode.EE, CountryCode.ES,
        CountryCode.FI, CountryCode.FR, CountryCode.GR, CountryCode.HU, CountryCode.IE,
        CountryCode.IS, CountryCode.IT, CountryCode.LI, CountryCode.LT, CountryCode.LU,
        CountryCode.LV, CountryCode.MT, CountryCode.NL, CountryCode.NO, CountryCode.PL,
        CountryCode.PT, CountryCode.RO, CountryCode.SE, CountryCode.SI, CountryCode.SK);
    private static final String ISO_8859_10_NON_ASCII =
        "\u00a0ĄĒĢĪĨĶ§ĻĐŠŦŽ\u00adŪŊ°ąēģīĩķ·ļđšŧž―ūŋ"
            + "ĀÁÂÃÄÅÆĮČÉĘËĖÍÎÏÐŅŌÓÔÕÖŨØŲÚÛÜÝÞß"
            + "āáâãäåæįčéęëėíîïðņōóôõöũøųúûüýþĸ";

    private Version version = Version.V2;
    private CharacterSet characterSet = CharacterSet.UTF_8;
    private Bic bic;
    private String beneficiaryName;
    private Iban iban;
    private BigDecimal amount;
    private Currency currency = EURO;
    private String purposeCode;
    private String remittanceReference;
    private String remittanceText;
    private String beneficiaryToOriginatorInformation;

    private Builder() {
    }

    /** Sets the EPC payload version. */
    public Builder version(final Version version) {
      this.version = Objects.requireNonNull(version, "version");
      return this;
    }

    /** Sets the character set used to encode the payload. */
    public Builder characterSet(final CharacterSet characterSet) {
      this.characterSet = Objects.requireNonNull(characterSet, "characterSet");
      return this;
    }

    /** Sets the BIC of the beneficiary PSP. */
    public Builder bic(final Bic bic) {
      this.bic = bic;
      return this;
    }

    /** Sets the beneficiary name. */
    public Builder beneficiaryName(final String beneficiaryName) {
      this.beneficiaryName = beneficiaryName;
      return this;
    }

    /** Sets the beneficiary IBAN. */
    public Builder iban(final Iban iban) {
      this.iban = iban;
      return this;
    }

    /** Sets an amount in euros. The amount may have at most two fractional digits. */
    public Builder amount(final BigDecimal amount) {
      return amount(amount, EURO);
    }

    /** Sets the transfer amount and currency. EPC069-12 only permits EUR. */
    public Builder amount(final BigDecimal amount, final Currency currency) {
      this.amount = Objects.requireNonNull(amount, "amount");
      this.currency = Objects.requireNonNull(currency, "currency");
      return this;
    }

    /** Sets the ISO 20022 purpose code. */
    public Builder purposeCode(final String purposeCode) {
      this.purposeCode = purposeCode;
      return this;
    }

    /** Sets structured remittance information, such as an ISO 11649 creditor reference. */
    public Builder remittanceReference(final String remittanceReference) {
      this.remittanceReference = remittanceReference;
      return this;
    }

    /** Sets unstructured remittance information. */
    public Builder remittanceText(final String remittanceText) {
      this.remittanceText = remittanceText;
      return this;
    }

    /** Sets information from the beneficiary to the originator. */
    public Builder beneficiaryToOriginatorInformation(final String information) {
      this.beneficiaryToOriginatorInformation = information;
      return this;
    }

    /**
     * Validates the configured fields and builds the payload.
     *
     * @return an immutable EPC QR code payload
     * @throws IllegalArgumentException if any field violates EPC069-12
     */
    public EpcQrCode build() {
      Objects.requireNonNull(iban, "iban");
      validateField("beneficiaryName", beneficiaryName, 70, true);
      validateField("purposeCode", purposeCode, 4, false);
      validateField("remittanceReference", remittanceReference, 35, false);
      validateField("remittanceText", remittanceText, 140, false);
      validateField(
          "beneficiaryToOriginatorInformation", beneficiaryToOriginatorInformation, 70, false);

      if (isPopulated(purposeCode) && !purposeCode.matches("[A-Za-z0-9]{1,4}")) {
        throw new IllegalArgumentException("purposeCode must contain 1 to 4 alphanumeric characters");
      }
      if (isPopulated(remittanceReference) && isPopulated(remittanceText)) {
        throw new IllegalArgumentException(
            "remittanceReference and remittanceText are mutually exclusive");
      }
      if (version == Version.V1 && bic == null) {
        throw new IllegalArgumentException("bic is mandatory for EPC payload version 001");
      }
      if (version == Version.V2 && bic == null && !EEA_COUNTRIES.contains(iban.getCountryCode())) {
        throw new IllegalArgumentException("bic is mandatory for a beneficiary PSP outside the EEA");
      }

      final String amountValue = formatAmount();
      final String[] fields = {
          "BCD", version.value, characterSet.code, "SCT", valueOf(bic), beneficiaryName,
          iban.toString(), amountValue, valueOf(purposeCode), valueOf(remittanceReference),
          valueOf(remittanceText), valueOf(beneficiaryToOriginatorInformation)
      };
      int lastField = fields.length - 1;
      while (lastField >= 0 && fields[lastField].isEmpty()) {
        lastField--;
      }
      final String payload = String.join("\n", Arrays.copyOf(fields, lastField + 1));
      validateEncoding(payload);
      return new EpcQrCode(payload);
    }

    /** Builds and directly returns the plain-text payload. */
    public String buildPayload() {
      return build().getPayload();
    }

    private String formatAmount() {
      if (amount == null) {
        return "";
      }
      if (!EURO.equals(currency)) {
        throw new IllegalArgumentException("currency must be EUR");
      }
      final BigDecimal normalized = amount.stripTrailingZeros();
      if (normalized.compareTo(new BigDecimal("0.01")) < 0
          || normalized.compareTo(new BigDecimal("999999999.99")) > 0) {
        throw new IllegalArgumentException("amount must be between 0.01 and 999999999.99 EUR");
      }
      if (Math.max(normalized.scale(), 0) > 2) {
        throw new IllegalArgumentException("amount must have at most two fractional digits");
      }
      return "EUR" + normalized.toPlainString();
    }

    private void validateEncoding(final String payload) {
      if (!canEncode(payload)) {
        throw new IllegalArgumentException(
            "payload contains characters not supported by " + characterSet.charsetName);
      }
      final int payloadBytes = characterSet == CharacterSet.ISO_8859_10
          ? payload.codePointCount(0, payload.length())
          : payload.getBytes(Charset.forName(characterSet.charsetName)).length;
      if (payloadBytes > MAX_PAYLOAD_BYTES) {
        throw new IllegalArgumentException(
            "payload must not exceed 331 bytes; encoded payload is " + payloadBytes + " bytes");
      }
    }

    private boolean canEncode(final String payload) {
      if (characterSet != CharacterSet.ISO_8859_10) {
        final CharsetEncoder encoder = Charset.forName(characterSet.charsetName).newEncoder();
        return encoder.canEncode(payload);
      }
      return payload.codePoints().allMatch(
          codePoint -> codePoint < 128
              || ISO_8859_10_NON_ASCII.indexOf(codePoint) >= 0);
    }

    private static void validateField(
        final String name, final String value, final int maxCharacters, final boolean mandatory) {
      if (value == null || value.isEmpty()) {
        if (mandatory) {
          throw new IllegalArgumentException(name + " is mandatory");
        }
        return;
      }
      if (value.indexOf('\n') >= 0 || value.indexOf('\r') >= 0) {
        throw new IllegalArgumentException(name + " must not contain line separators");
      }
      if (value.codePointCount(0, value.length()) > maxCharacters) {
        throw new IllegalArgumentException(
            name + " must not exceed " + maxCharacters + " characters");
      }
    }

    private static String valueOf(final Object value) {
      return value == null ? "" : value.toString();
    }

    private static boolean isPopulated(final String value) {
      return value != null && !value.isEmpty();
    }
  }
}
