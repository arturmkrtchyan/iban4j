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
package org.iban4j.support;

import java.util.*;

/**
 * Class which represents iban structure
 */
public class IbanStructure {

    private static final String DEFAULT_VALUE_SPLITTER = ":";
    private static final String CHARACTER_TYPE_SPLITTER = "!";
    private static final String ENTRY_SPLITTER = " ";
    private static final int COUNTRY_CODE_LENGTH = 2;

    private Map<String, IbanStructureEntry> entries;
    private String rawStructure;

    private IbanStructure(final Map<String, IbanStructureEntry> entries, final String rawStructure) {
        this.entries = entries;
        this.rawStructure = rawStructure;
    }

    /**
     * Creates instance of iban structure.
     *
     * @param structure string representation.
     * @return iban structure instance.
     * @throws IllegalStateException if structure can't be parsed.
     */
    public static IbanStructure valueOf(final String structure) throws IllegalStateException {
        return parseStructure(structure);
    }

    /**
     * Checks if structure has specified entry.
     *
     * @param entryType
     * @return true if structure has specified entry.
     */
    public boolean hasEntry(final IbanStructureEntry.EntryType entryType) {
        return entries.containsKey(entryType.name());
    }

    /**
     * Returns list of structure entries.
     *
     * @return list of structure entries
     */
    public List<IbanStructureEntry> getEntries() {
        return Collections.unmodifiableList(new LinkedList<IbanStructureEntry>(entries.values()));
    }

    /**
     * Returns the length of iban.
     *
     * @return int length
     */
    public int getIbanLength() {
        int length = COUNTRY_CODE_LENGTH;

        for(IbanStructureEntry entry : entries.values()) {
            length += entry.getLength();
        }

        return length;
    }

    /**
     * Returns entry for specified entry type.
     *
     * @param entryType
     * @return entry for specified entry type.
     */
    public IbanStructureEntry getEntry(final IbanStructureEntry.EntryType entryType) {
        return entries.get(entryType.name());
    }

    /**
     * Returns entry's default value.
     *
     * @param entryType
     * @return entry's default value.
     */
    public String getEntryDefaultValue(final IbanStructureEntry.EntryType entryType) {
        return entries.get(entryType.name()).getDefaultValue();
    }

    private static IbanStructure parseStructure(final String structure) throws IllegalStateException {
        // it's important to have linked hash map to keep insertion order
        Map<String, IbanStructureEntry> entries = new LinkedHashMap<String, IbanStructureEntry>();
        try {
            String[] entriesStr = structure.split(ENTRY_SPLITTER);
            for (String entryStr : entriesStr) {
                IbanStructureEntry entry = parseStructureEntry(entryStr);
                entries.put(entry.getEntryType().name(), entry);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Invalid IBAN Structure: " + structure, e);
        }
        return new IbanStructure(entries, structure);
    }

    private static IbanStructureEntry parseStructureEntry(final String entry) {
        String[] parts = entry.split(CHARACTER_TYPE_SPLITTER);
        IbanStructureEntry.EntryCharacterType characterType = parseCharacterType(parts[1]);
        String entryDefaultValue = null;

        if(hasDefaultValue(parts[0])) {
            String[] valueParts = parts[0].split(DEFAULT_VALUE_SPLITTER);
            entryDefaultValue = parts[1];
            parts[0] = valueParts[0];
        }

        IbanStructureEntry.EntryType entryType = parseEntryType(parts[0]);
        int length = parseEntryLength(parts[0]);
        return new IbanStructureEntry(entryType, characterType, length, entryDefaultValue);
    }

    private static IbanStructureEntry.EntryCharacterType parseCharacterType(final String type) {
        return IbanStructureEntry.EntryCharacterType.valueOf(type);
    }

    private static IbanStructureEntry.EntryType parseEntryType(final String entry) {
        String structureEntryStr = entry.substring(entry.length() - 1);
        return IbanStructureEntry.EntryType.valueOf(structureEntryStr);
    }

    private static int parseEntryLength(final String entry) {
        String lengthStr = entry.substring(0, entry.length() - 1);
        return Integer.valueOf(lengthStr);
    }

    private static boolean hasDefaultValue(final String entry) {
        return entry.contains(DEFAULT_VALUE_SPLITTER);
    }

    @Override
    public String toString() {
        return "IbanStructure{" +
                "rawStructure='" + rawStructure + '\'' +
                '}';
    }
}
