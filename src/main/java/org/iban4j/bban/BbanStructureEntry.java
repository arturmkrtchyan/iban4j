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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Bban Structure Entry representation.
 */
public class BbanStructureEntry {

    private final BbanEntryType entryType;
    private final EntryCharacterType characterType;
    private final int length;

    private static final Map<EntryCharacterType, char[]> charByCharacterType;

    static {
        charByCharacterType = new HashMap<>();
        StringBuilder charTypeN = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ch++) {
            charTypeN.append(ch);
        }
        charByCharacterType.put(EntryCharacterType.n, charTypeN.toString().toCharArray());

        StringBuilder charTypeA = new StringBuilder();
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            charTypeA.append(ch);
        }
        charByCharacterType.put(EntryCharacterType.a, charTypeA.toString().toCharArray());

        charByCharacterType.put(EntryCharacterType.c, (charTypeN.toString() + charTypeA.toString()).toCharArray());
    }

    private BbanStructureEntry(final BbanEntryType entryType,
                       final EntryCharacterType characterType,
                       final int length) {
        this.entryType = entryType;
        this.characterType = characterType;
        this.length = length;
    }

    public static BbanStructureEntry bankCode(final int length, final char characterType) {
        return new BbanStructureEntry(BbanEntryType.bank_code,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry branchCode(final int length, final char characterType) {
        return new BbanStructureEntry(BbanEntryType.branch_code,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry accountNumber(final int length, final char characterType) {
        return new BbanStructureEntry(BbanEntryType.account_number,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry nationalCheckDigit(final int length, final char characterType) {
        return new BbanStructureEntry(BbanEntryType.national_check_digit,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry accountType(final int length, final char characterType) {
        return new BbanStructureEntry(BbanEntryType.account_type,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry ownerAccountNumber(final int length, final char characterType) {
        return new BbanStructureEntry(BbanEntryType.owner_account_number,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry identificationNumber(final int length, final char characterType) {
        return new BbanStructureEntry(BbanEntryType.identification_number,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public BbanEntryType getEntryType() {
        return entryType;
    }

    public EntryCharacterType getCharacterType() {
        return characterType;
    }

    public int getLength() {
        return length;
    }

    public enum EntryCharacterType {
        /**
         * Numerical digits (0-9 only)
         */
        n,
        /**
         * Alphabetical characters (A-Z only)
         */
        a,
        /**
         * Combined alphabetical (uppercase) and numeric characters (A-Z and 0-9)
         */
        c
    }

    public String getRandom() {
        return getRandom(new Random());
    }

    public String getRandom(Random random) {

        // Create a new seeded Random, so it doesn't matter how this Random is used, it won't affect subsequent usages
        // of the original Random. (which can impact seeded behaviour when many IBANs are generated or the number of
        // IBAN entries change).
        random = new Random(random.nextInt());

        StringBuilder s = new StringBuilder();
        char[] charChoices = charByCharacterType.get(characterType);
        if (charChoices == null) {
            throw new RuntimeException(String.format("programmer has not implemented choices for character type %s",
                    characterType.name()));
        }
        for (int i = 0; i < getLength(); i++) {
            s.append(charChoices[random.nextInt(charChoices.length)]);
        }
        return s.toString();
    }

    @Override
    public String toString() {
        return "BbanStructureEntry{" +
            "entryType=" + entryType +
            ", characterType=" + characterType +
            ", length=" + length +
            '}';
    }
}
