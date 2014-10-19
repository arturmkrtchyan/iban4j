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

/**
 * Bban Structure Entry representation.
 */
public class BbanStructureEntry {

    private final BbanEntryType entryType;
    private final EntryCharacterType characterType;
    private final int length;

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
        n,  // Digits (numeric characters 0 to 9 only)
        a,  // Upper case letters (alphabetic characters A-Z only)
        c  // upper and lower case alphanumeric characters (A-Z, a-z and 0-9)
    }
}

