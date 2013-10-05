package org.iban4j.support;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IbanStructure {

    private Map<IbanStructureEntry.EntryType, IbanStructureEntry> entries;

    private IbanStructure(Map<IbanStructureEntry.EntryType, IbanStructureEntry> entries) {
        this.entries = entries;
    }

    public static IbanStructure valueOf(final String structure) throws IllegalArgumentException {
        return parseStructure(structure);
    }

    public boolean hasEntry(final IbanStructureEntry.EntryType entryType) {
        return entries.containsKey(entryType);
    }

    public Collection<IbanStructureEntry> getEntries() {
        return entries.values();
    }

    public IbanStructureEntry getEntry(final IbanStructureEntry.EntryType entryType) {
        return entries.get(entryType);
    }

    private static IbanStructure parseStructure(final String structure) throws IllegalArgumentException {
        Map<IbanStructureEntry.EntryType, IbanStructureEntry> entries =
                new HashMap<IbanStructureEntry.EntryType, IbanStructureEntry>();
        try {
            String[] entriesStr = structure.split(" ");
            for (String entryStr : entriesStr) {
                IbanStructureEntry entry = parseStructureEntry(entryStr);
                entries.put(entry.getEntryType(), entry);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid IBAN Structure is passed: " + structure, e);
        }
        return new IbanStructure(entries);
    }

    private static IbanStructureEntry parseStructureEntry(final String entry) {
        String[] parts = entry.split("!");
        IbanStructureEntry.EntryCharacterType characterType = parseCharacterType(parts[1]);
        IbanStructureEntry.EntryType entryType = parseEntryType(parts[0]);
        int length = parseEntryLength(parts[0]);
        return new IbanStructureEntry(entryType, characterType, length);
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
}
