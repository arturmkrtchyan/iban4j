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

    private EntryType entryType;
    private EntryCharacterType characterType;
    private int length;

    private BbanStructureEntry(final EntryType entryType,
                       final EntryCharacterType characterType,
                       final int length) {
        this.entryType = entryType;
        this.characterType = characterType;
        this.length = length;
    }

    public static BbanStructureEntry bankCode(final int length, final char characterType) {
        return new BbanStructureEntry(EntryType.b,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry branchCode(final int length, final char characterType) {
        return new BbanStructureEntry(EntryType.s,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry accountNumber(final int length, final char characterType) {
        return new BbanStructureEntry(EntryType.c,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry nationalCheckDigit(final int length, final char characterType) {
        return new BbanStructureEntry(EntryType.x,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry accountType(final int length, final char characterType) {
        return new BbanStructureEntry(EntryType.t,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry ownerAccountNumber(final int length, final char characterType) {
        return new BbanStructureEntry(EntryType.n,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public static BbanStructureEntry identificationNumber(final int length, final char characterType) {
        return new BbanStructureEntry(EntryType.i,
                EntryCharacterType.valueOf(String.valueOf(characterType)), length);
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public EntryCharacterType getCharacterType() {
        return characterType;
    }

    public int getLength() {
        return length;
    }

    public enum EntryType {
        b, // bank code, national bank code
        s, // branch code
        c, // account number
        x, // national check digit
        t, // account type (Cheque account, Savings account etc)
        n, // owner account number ("1", "2" etc)
        i; // identification number
    }

    public enum EntryCharacterType {
        n,  // Digits (numeric characters 0 to 9 only)
        a,  // Upper case letters (alphabetic characters A-Z only)
        c;  // upper and lower case alphanumeric characters (A-Z, a-z and 0-9)
    }
}

