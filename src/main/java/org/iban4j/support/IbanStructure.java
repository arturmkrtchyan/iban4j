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

public class IbanStructure {

    private static final String DEFAULT_VALUE_SPLITTER = ":";
    private static final String CHARACTER_TYPE_SPLITTER = "!";
    private static final String ENTRY_SPLITTER = " ";

    private Map<String, IbanStructureEntry> entries;
    private String rawStructure;

    private IbanStructure(Map<String, IbanStructureEntry> entries, String rawStructure) {
        this.entries = entries;
        this.rawStructure = rawStructure;
    }

    public static IbanStructure valueOf(final String structure) throws IllegalArgumentException {
        return parseStructure(structure);
    }

    public boolean hasEntry(final IbanStructureEntry.EntryType entryType) {
        return entries.containsKey(entryType.name());
    }

    public List<IbanStructureEntry> getEntries() {
        return Collections.unmodifiableList(new LinkedList<IbanStructureEntry>(entries.values()));
    }

    public Collection<IbanStructureEntry> getBbanEntries() {
        LinkedList<IbanStructureEntry> tmpEntries = new LinkedList<IbanStructureEntry>(entries.values());
        return Collections.unmodifiableList(tmpEntries.subList(1, tmpEntries.size()));
    }

    public IbanStructureEntry getEntry(final IbanStructureEntry.EntryType entryType) {
        return entries.get(entryType.name());
    }

    public String getEntryDefaultValue(final IbanStructureEntry.EntryType entryType) {
        return entries.get(entryType.name()).getDefaultValue();
    }

    private static IbanStructure parseStructure(final String structure) throws IllegalArgumentException {
        Map<String, IbanStructureEntry> entries = new LinkedHashMap<String, IbanStructureEntry>();
        try {
            String[] entriesStr = structure.split(ENTRY_SPLITTER);
            for (String entryStr : entriesStr) {
                IbanStructureEntry entry = parseStructureEntry(entryStr);
                entries.put(entry.getEntryType().name(), entry);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid IBAN Structure is passed: " + structure, e);
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
