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

package org.iban4j.bban;

import static org.iban4j.IbanFormatException.IbanFormatViolation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import org.iban4j.CountryCode;
import org.iban4j.IbanFormatException;
import org.iban4j.UnsupportedCountryException;

/**
 * Class that represents BBAN structure
 */
public class BbanStructure {

  private static final String INVALID_ENTRY_TYPE = "Entry type [%s] does not exist for country [%s]";
  private static final String ASSERT_UPPER_LETTERS = "[%s] must contain only upper case letters.";
  private static final String ASSERT_DIGITS_AND_LETTERS = "[%s] must contain only digits or letters.";
  private static final String ASSERT_DIGITS = "[%s] must contain only digits.";

  private static final EnumMap<CountryCode, BbanStructure> structures;

  /**
   * French sub-territories may use their own country code (BL,RE,NC,...) or FR for their IBAN.
   * Structure is the same, only the IBAN checksum differs.
   */
  private static final BbanStructure FRENCH_STRUCTURE =
      new BbanStructure(
          BbanStructureEntry.bankCode(5, 'n'),
          BbanStructureEntry.branchCode(5, 'n'),
          BbanStructureEntry.accountNumber(11, 'c'),
          BbanStructureEntry.nationalCheckDigit(2, 'n'));

  private static final BbanStructure UNITED_KINGDOM_STRUCTURE =
      new BbanStructure(
          BbanStructureEntry.bankCode(4, 'a'),
          BbanStructureEntry.branchCode(6, 'n'),
          BbanStructureEntry.accountNumber(8, 'n'));
  private static final BbanStructure FINLAND_STRUCTURE =
      new BbanStructure(
          BbanStructureEntry.bankCode(6, 'n'),
          BbanStructureEntry.accountNumber(7, 'n'),
          BbanStructureEntry.nationalCheckDigit(1, 'n'));

  static {
    structures = new EnumMap<>(CountryCode.class);

    structures.put(
        CountryCode.AL,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.nationalCheckDigit(1, 'n'),
            BbanStructureEntry.accountNumber(16, 'c')));

    structures.put(
        CountryCode.AD,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(12, 'c')));

    structures.put(
        CountryCode.AO,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(11, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.AT,
        new BbanStructure(
            BbanStructureEntry.bankCode(5, 'n'), BbanStructureEntry.accountNumber(11, 'n')));

    structures.put(
        CountryCode.AZ,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(20, 'c')));

    structures.put(
        CountryCode.BH,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(14, 'c')));

    structures.put(
        CountryCode.BE,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.accountNumber(7, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.BA,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.branchCode(3, 'n'),
            BbanStructureEntry.accountNumber(8, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.BR,
        new BbanStructure(
            BbanStructureEntry.bankCode(8, 'n'),
            BbanStructureEntry.branchCode(5, 'n'),
            BbanStructureEntry.accountNumber(10, 'n'),
            BbanStructureEntry.accountType(1, 'a'),
            BbanStructureEntry.ownerAccountNumber(1, 'c')));

    structures.put(
        CountryCode.BG,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountType(2, 'n'),
            BbanStructureEntry.accountNumber(8, 'c')));

    structures.put(
        CountryCode.BY,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'c'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(16, 'c')));

    structures.put(
        CountryCode.CR,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'), BbanStructureEntry.accountNumber(14, 'n')));

    structures.put(
        CountryCode.DE,
        new BbanStructure(
            BbanStructureEntry.bankCode(8, 'n'), BbanStructureEntry.accountNumber(10, 'n')));

    structures.put(
        CountryCode.HR,
        new BbanStructure(
            BbanStructureEntry.bankCode(7, 'n'), BbanStructureEntry.accountNumber(10, 'n')));

    structures.put(
        CountryCode.CY,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.branchCode(5, 'n'),
            BbanStructureEntry.accountNumber(16, 'c')));

    structures.put(
        CountryCode.CZ,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'), BbanStructureEntry.accountNumber(16, 'n')));

    structures.put(
        CountryCode.DK,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'), BbanStructureEntry.accountNumber(10, 'n')));

    structures.put(
        CountryCode.DO,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'c'), BbanStructureEntry.accountNumber(20, 'n')));

    structures.put(
        CountryCode.EE,
        new BbanStructure(
            BbanStructureEntry.bankCode(2, 'n'),
            BbanStructureEntry.branchCode(2, 'n'),
            BbanStructureEntry.accountNumber(11, 'n'),
            BbanStructureEntry.nationalCheckDigit(1, 'n')));

    structures.put(
        CountryCode.EG,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(17, 'n')));

    structures.put(
        CountryCode.FO,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'),
            BbanStructureEntry.accountNumber(9, 'n'),
            BbanStructureEntry.nationalCheckDigit(1, 'n')));

    // Finland and its sub-territory (see https://www.iban.com/structure)
    structures.put(CountryCode.FI, BbanStructure.FINLAND_STRUCTURE);
    structures.put(CountryCode.AX, BbanStructure.FINLAND_STRUCTURE);

    // France and its sub-territories (see https://www.iban.com/structure)
    structures.put(CountryCode.FR, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.GF, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.GP, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.MQ, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.RE, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.PF, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.TF, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.YT, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.NC, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.BL, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.MF, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.PM, BbanStructure.FRENCH_STRUCTURE);
    structures.put(CountryCode.WF, BbanStructure.FRENCH_STRUCTURE);

    structures.put(
        CountryCode.GA,
        new BbanStructure(
            BbanStructureEntry.bankCode(5, 'n'),
            BbanStructureEntry.branchCode(5, 'n'),
            BbanStructureEntry.accountNumber(13, 'c')));

    structures.put(
        CountryCode.GE,
        new BbanStructure(
            BbanStructureEntry.bankCode(2, 'a'), BbanStructureEntry.accountNumber(16, 'n')));

    structures.put(
        CountryCode.GI,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(15, 'c')));

    structures.put(
        CountryCode.GL,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'), BbanStructureEntry.accountNumber(10, 'n')));

    structures.put(
        CountryCode.GR,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(16, 'c')));

    structures.put(
        CountryCode.GT,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'c'), BbanStructureEntry.accountNumber(20, 'c')));

    structures.put(
        CountryCode.HU,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(16, 'n'),
            BbanStructureEntry.nationalCheckDigit(1, 'n')));

    structures.put(
        CountryCode.IS,
        new BbanStructure(
            BbanStructureEntry.bankCode(2, 'n'),
            BbanStructureEntry.branchCode(2, 'n'),
            BbanStructureEntry.accountType(2, 'n'),
            BbanStructureEntry.accountNumber(6, 'n'),
            BbanStructureEntry.identificationNumber(10, 'n')));

    structures.put(
        CountryCode.IE,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'),
            BbanStructureEntry.branchCode(6, 'n'),
            BbanStructureEntry.accountNumber(8, 'n')));

    structures.put(
        CountryCode.IL,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.branchCode(3, 'n'),
            BbanStructureEntry.accountNumber(13, 'n')));

    structures.put(
        CountryCode.IR,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'), BbanStructureEntry.accountNumber(19, 'n')));

    structures.put(
        CountryCode.IT,
        new BbanStructure(
            BbanStructureEntry.nationalCheckDigit(1, 'a'),
            BbanStructureEntry.bankCode(5, 'n'),
            BbanStructureEntry.branchCode(5, 'n'),
            BbanStructureEntry.accountNumber(12, 'c')));

    structures.put(
        CountryCode.JO,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(18, 'c')));

    structures.put(
        CountryCode.KZ,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'), BbanStructureEntry.accountNumber(13, 'c')));

    structures.put(
        CountryCode.KW,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(22, 'c')));

    structures.put(
        CountryCode.LC,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(24, 'c')));

    structures.put(
        CountryCode.LV,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(13, 'c')));

    structures.put(
        CountryCode.LB,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'), BbanStructureEntry.accountNumber(20, 'c')));

    structures.put(
        CountryCode.LI,
        new BbanStructure(
            BbanStructureEntry.bankCode(5, 'n'), BbanStructureEntry.accountNumber(12, 'c')));

    structures.put(
        CountryCode.LT,
        new BbanStructure(
            BbanStructureEntry.bankCode(5, 'n'), BbanStructureEntry.accountNumber(11, 'n')));

    structures.put(
        CountryCode.LU,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'), BbanStructureEntry.accountNumber(13, 'c')));

    structures.put(
            CountryCode.MA,
            new BbanStructure(
                    BbanStructureEntry.bankCode(3, 'n'),
                    BbanStructureEntry.branchCode(5, 'n'),
                    BbanStructureEntry.accountNumber(16, 'n')));

    structures.put(
            CountryCode.MC,
            new BbanStructure(
                    BbanStructureEntry.bankCode(5, 'n'),
                    BbanStructureEntry.branchCode(5, 'n'),
                    BbanStructureEntry.accountNumber(11, 'c'),
                    BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
            CountryCode.MD,
            new BbanStructure(
                    BbanStructureEntry.bankCode(2, 'c'), BbanStructureEntry.accountNumber(18, 'c')));

    structures.put(
            CountryCode.ME,
            new BbanStructure(
                    BbanStructureEntry.bankCode(3, 'n'),
                    BbanStructureEntry.accountNumber(13, 'n'),
                    BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.MK,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.accountNumber(10, 'c'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
            CountryCode.MR,
            new BbanStructure(
                    BbanStructureEntry.bankCode(5, 'n'),
                    BbanStructureEntry.branchCode(5, 'n'),
                    BbanStructureEntry.accountNumber(11, 'n'),
                    BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.MT,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'),
            BbanStructureEntry.branchCode(5, 'n'),
            BbanStructureEntry.accountNumber(18, 'c')));

    structures.put(
        CountryCode.MU,
        new BbanStructure(
            BbanStructureEntry.bankCode(6, 'c'),
            BbanStructureEntry.branchCode(2, 'n'),
            BbanStructureEntry.accountNumber(18, 'c')));
    structures.put(
            CountryCode.MZ,
            new BbanStructure(
                    BbanStructureEntry.bankCode(4, 'n'),
                    BbanStructureEntry.branchCode(4, 'n'),
                    BbanStructureEntry.accountNumber(11, 'n'),
                    BbanStructureEntry.nationalCheckDigit(2, 'n')));
    structures.put(
        CountryCode.NL,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(10, 'n')));

    structures.put(
        CountryCode.NO,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'),
            BbanStructureEntry.accountNumber(6, 'n'),
            BbanStructureEntry.nationalCheckDigit(1, 'n')));

    structures.put(
        CountryCode.PK,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'c'), BbanStructureEntry.accountNumber(16, 'n')));

    structures.put(
        CountryCode.PS,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(21, 'c')));

    structures.put(
        CountryCode.PL,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.nationalCheckDigit(1, 'n'),
            BbanStructureEntry.accountNumber(16, 'n')));

    structures.put(
        CountryCode.PT,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(11, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.RO,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(16, 'c')));

    structures.put(
        CountryCode.QA,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(21, 'c')));

    structures.put(
        CountryCode.SC,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'),
            BbanStructureEntry.bankCodeExt(2, 'n'),
            BbanStructureEntry.branchCode(2, 'n'),
            BbanStructureEntry.accountNumber(16, 'n'),
            BbanStructureEntry.accountType(3, 'a')));

    structures.put(
        CountryCode.SM,
        new BbanStructure(
            BbanStructureEntry.nationalCheckDigit(1, 'a'),
            BbanStructureEntry.bankCode(5, 'n'),
            BbanStructureEntry.branchCode(5, 'n'),
            BbanStructureEntry.accountNumber(12, 'c')));

    structures.put(
        CountryCode.ST,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(13, 'n')));

    structures.put(
        CountryCode.SA,
        new BbanStructure(
            BbanStructureEntry.bankCode(2, 'n'), BbanStructureEntry.accountNumber(18, 'c')));

    structures.put(
        CountryCode.RS,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.accountNumber(13, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.RU,
        new BbanStructure(
            BbanStructureEntry.bankCode(9, 'n'),
            BbanStructureEntry.branchCode(5, 'n'),
            BbanStructureEntry.accountNumber(15, 'c')));

    structures.put(
        CountryCode.SK,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'), BbanStructureEntry.accountNumber(16, 'n')));

    structures.put(
        CountryCode.SI,
        new BbanStructure(
            BbanStructureEntry.bankCode(2, 'n'),
            BbanStructureEntry.branchCode(3, 'n'),
            BbanStructureEntry.accountNumber(8, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.SV,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(20, 'n')));

    structures.put(
        CountryCode.ES,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n'),
            BbanStructureEntry.accountNumber(10, 'n')));

    structures.put(
        CountryCode.SE,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'), BbanStructureEntry.accountNumber(17, 'n')));

    structures.put(
        CountryCode.CH,
        new BbanStructure(
            BbanStructureEntry.bankCode(5, 'n'), BbanStructureEntry.accountNumber(12, 'c')));

    structures.put(
        CountryCode.TN,
        new BbanStructure(
            BbanStructureEntry.bankCode(2, 'n'),
            BbanStructureEntry.branchCode(3, 'n'),
            BbanStructureEntry.accountNumber(13, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.TR,
        new BbanStructure(
            BbanStructureEntry.bankCode(5, 'n'),
            BbanStructureEntry.nationalCheckDigit(1, 'c'),
            BbanStructureEntry.accountNumber(16, 'c')));

    structures.put(
        CountryCode.UA,
        new BbanStructure(
            BbanStructureEntry.bankCode(6, 'n'), BbanStructureEntry.accountNumber(19, 'n')));

    // UK and its sub-territories (see https://www.iban.com/structure)
    structures.put(CountryCode.GB, BbanStructure.UNITED_KINGDOM_STRUCTURE);
    structures.put(CountryCode.IM, BbanStructure.UNITED_KINGDOM_STRUCTURE);
    structures.put(CountryCode.GG, BbanStructure.UNITED_KINGDOM_STRUCTURE);
    structures.put(CountryCode.JE, BbanStructure.UNITED_KINGDOM_STRUCTURE);

    structures.put(
        CountryCode.AE,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'), BbanStructureEntry.accountNumber(16, 'c')));

    structures.put(
        CountryCode.VA,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'), BbanStructureEntry.accountNumber(15, 'n')));

    structures.put(
        CountryCode.VG,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'), BbanStructureEntry.accountNumber(16, 'n')));

    structures.put(
        CountryCode.TL,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'),
            BbanStructureEntry.accountNumber(14, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.XK,
        new BbanStructure(
            BbanStructureEntry.bankCode(2, 'n'),
            BbanStructureEntry.branchCode(2, 'n'),
            BbanStructureEntry.accountNumber(10, 'n'),
            BbanStructureEntry.nationalCheckDigit(2, 'n')));

    structures.put(
        CountryCode.IQ,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'a'),
            BbanStructureEntry.branchCode(3, 'n'),
            BbanStructureEntry.accountNumber(12, 'n')));

    structures.put(
        CountryCode.CV,
        new BbanStructure(
            BbanStructureEntry.bankCode(4, 'n'),
            BbanStructureEntry.branchCode(4, 'n'),
            BbanStructureEntry.accountNumber(13, 'c')));

    structures.put(
        CountryCode.OM,
        new BbanStructure(
            BbanStructureEntry.bankCode(3, 'n'), BbanStructureEntry.accountNumber(16, 'c')));

    structures.put(
        CountryCode.BI,
        new BbanStructure(
            BbanStructureEntry.bankCode(5, 'n'),
            BbanStructureEntry.branchCode(5, 'n'),
            BbanStructureEntry.accountNumber(13, 'n')));
  }

  private final BbanStructureEntry[] entries;

  private BbanStructure(final BbanStructureEntry... entries) {
    this.entries = entries;
  }

  /**
   * @param countryCode the country code.
   * @return BbanStructure for specified country or null if country is not supported.
   */
  public static BbanStructure forCountry(final CountryCode countryCode) {
    return structures.get(countryCode);
  }

  /**
   * Checks whether national Check digit is mandatory for specific country
   *
   * @param countryCode the country code
   * @return true/false
   */
  public static boolean hasNationalCheckDigit(final CountryCode countryCode) {
    final Optional<BbanStructure> bbanStructure = Optional.ofNullable(forCountry(countryCode));
    return bbanStructure
        .map(
            structure ->
                structure.getEntries().stream()
                    .anyMatch(e -> BbanEntryType.national_check_digit.equals(e.getEntryType())))
        .orElse(false);
  }

  public static List<CountryCode> supportedCountries() {
    final List<CountryCode> countryCodes = new ArrayList<CountryCode>(structures.size());
    countryCodes.addAll(structures.keySet());
    return Collections.unmodifiableList(countryCodes);
  }

  /**
   * Validates a specific BBAN entry based on the country code, entry type, and value.
   *
   * @param countryCode the country code for which the validation should be performed.
   * @param entryType the type of the BBAN entry (e.g., bank code, branch code).
   * @param entryValue the value of the BBAN entry to validate.
   * @throws UnsupportedCountryException if country is not supported.
   * @throws IbanFormatException if the entry type does not exist for the given country or if the entry value is invalid.
   */
  public static void validateBbanEntry(final CountryCode countryCode,
                                       final BbanEntryType entryType,
                                       final String entryValue) {
    final BbanStructure bbanStructure = forCountry(countryCode);

    if (bbanStructure == null) {
      throw new UnsupportedCountryException(countryCode.toString(),
              String.format("Country code [%s] is not supported.", countryCode));
    }

    final BbanStructureEntry entry = bbanStructure.getEntries().stream()
            .filter(e -> e.getEntryType().equals(entryType))
            .findFirst()
            .orElseThrow(() -> new IbanFormatException(BBAN_INVALID_ENTRY_TYPE,
                    String.format(INVALID_ENTRY_TYPE,
                            entryType.name(), countryCode)));

    validateBbanEntryLength(entry, entryValue);
    validateBbanEntryCharacterType(entry, entryValue);
  }

  /**
   * Validates the length of a BBAN entry value.
   *
   * @param entry the BBAN structure entry defining the expected length.
   * @param entryValue the value of the BBAN entry to validate.
   * @throws IbanFormatException if the entry value length is incorrect.
   */
  private static void validateBbanEntryLength(final BbanStructureEntry entry,
                                              final String entryValue) {
    if (entryValue.length() != entry.getLength()) {
      throw new IbanFormatException(BBAN_LENGTH,
              String.format("Entry value [%s] must be exactly %d characters long.",
                      entryValue, entry.getLength()));
    }
  }

  /**
   * Validates the character type of BBAN entry value.
   *
   * @param entry the BBAN structure entry defining the expected character type.
   * @param entryValue the value of the BBAN entry to validate.
   * @throws IbanFormatException if the entry value contains invalid characters.
   */
  private static void validateBbanEntryCharacterType(final BbanStructureEntry entry,
                                                     final String entryValue) {
    switch (entry.getCharacterType()) {
      case a:
        for (char ch: entryValue.toCharArray()) {
          if (!Character.isUpperCase(ch)) {
            throw new IbanFormatException(BBAN_ONLY_UPPER_CASE_LETTERS,
                    entry.getEntryType(), entryValue, ch,
                    String.format(ASSERT_UPPER_LETTERS, entryValue));
          }
        }
        break;
      case c:
        for (char ch: entryValue.toCharArray()) {
          if (!Character.isLetterOrDigit(ch)) {
            throw new IbanFormatException(BBAN_ONLY_DIGITS_OR_LETTERS,
                    entry.getEntryType(), entryValue, ch,
                    String.format(ASSERT_DIGITS_AND_LETTERS, entryValue));
          }
        }
        break;
      case n:
        for (char ch: entryValue.toCharArray()) {
          if (!Character.isDigit(ch)) {
            throw new IbanFormatException(BBAN_ONLY_DIGITS,
                    entry.getEntryType(), entryValue, ch,
                    String.format(ASSERT_DIGITS, entryValue));
          }
        }
        break;
    }
  }

  /**
   * <p>Getter for the field <code>entries</code>.</p>
   *
   * @return a {@link java.util.List} object
   */
  public List<BbanStructureEntry> getEntries() {
    return Collections.unmodifiableList(Arrays.asList(entries));
  }

  /**
   * Returns the length of bban.
   *
   * @return int length
   */
  public int getBbanLength() {
    int length = 0;

    for (final BbanStructureEntry entry : entries) {
      length += entry.getLength();
    }

    return length;
  }
}
