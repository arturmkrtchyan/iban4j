/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.iban4j.support;

public class IbanStructureEntry {

    private EntryType entryType;
    private EntryCharacterType characterType;
    private int length;
    private String defaultValue;

    IbanStructureEntry(EntryType entryType, EntryCharacterType characterType, int length) {
        this(entryType, characterType, length, null);
    }

    IbanStructureEntry(EntryType entryType, EntryCharacterType characterType, int length, String defaultValue) {
        this.entryType = entryType;
        this.characterType = characterType;
        this.length = length;
        this.defaultValue = defaultValue;
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

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean hasDefaultValue() {
        return defaultValue != null;
    }

    public enum EntryType {
        k, // check digit
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
        c,  // upper and lower case alphanumeric characters (A-Z, a-z and 0-9)
        e;  // blank space
    }
}

