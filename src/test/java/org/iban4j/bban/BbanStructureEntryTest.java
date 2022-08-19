package org.iban4j.bban;

import org.junit.Test;

import java.nio.CharBuffer;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BbanStructureEntryTest {

    @Test
    public void expectRandomAccountNumberIsDeterministicWhenSeeded() {
        BbanStructureEntry entry = BbanStructureEntry.accountNumber(10, 'a');

        assertSeededRandomBbanStructureEntryEquals(entry, "RAHJMYUWWK", 1);
        assertSeededRandomBbanStructureEntryEquals(entry, "SGAVREIZNE", 2);
        assertSeededRandomBbanStructureEntryEquals(entry, "SMMHQUVGJX", 3);
    }

    @Test
    public void expectRandomOwnerAccountNumberIsDeterministicWhenSeeded() {
        BbanStructureEntry entry = BbanStructureEntry.ownerAccountNumber(11, 'n');

        assertSeededRandomBbanStructureEntryEquals(entry, "58734446889", 1);
        assertSeededRandomBbanStructureEntryEquals(entry, "82079069784", 2);
        assertSeededRandomBbanStructureEntryEquals(entry, "40018294915", 3);
    }

    @Test
    public void expectRandomBankCodeIsDeterministicWhenSeeded() {
        BbanStructureEntry entry = BbanStructureEntry.bankCode(12, 'c');

        assertSeededRandomBbanStructureEntryEquals(entry, "XSJXQ4EAASPP", 1);
        assertSeededRandomBbanStructureEntryEquals(entry, "4OKJX66R7O22", 2);
        assertSeededRandomBbanStructureEntryEquals(entry, "EK6POILGJDLA", 3);
    }

    private static void assertSeededRandomBbanStructureEntryEquals(
        BbanStructureEntry entry,
        String expected,
        int seed
    ) {
        String generated = entry.getRandom(new Random(seed));
        assertEquals(
            "expect that creating " + entry + " with seed '" + seed + "' is deterministic",
            expected,
            generated
        );
    }

    @Test
    public void expectRandomAlphanumericBranchCodeIsValid() {
        for (int i = 0; i < 100; i++) {
            BbanStructureEntry entry = BbanStructureEntry.branchCode(5, 'c');

            String generated = entry.getRandom();

            assertTrue(
                "expect '" + generated + "' is alphabetic. (" + entry + ")",
                generated.matches("[a-zA-Z0-9]+")
            );
            assertEquals(5, entry.getLength());
        }
    }

    @Test
    public void expectRandomAlphabeticNationalCheckDigitIsValid() {
        for (int i = 0; i < 100; i++) {
            BbanStructureEntry entry = BbanStructureEntry.nationalCheckDigit(6, 'a');

            String generated = entry.getRandom();

            assertTrue(
                "expect '" + generated + "' only has capitalized letters. (" + entry + ")",
                generated.matches("[A-Z]+")
            );
            assertEquals(6, entry.getLength());
        }
    }

    @Test
    public void expectRandomNumericIdentificationNumberIsValid() {
        for (int i = 0; i < 100; i++) {
            BbanStructureEntry entry = BbanStructureEntry.identificationNumber(7, 'n');

            String generated = entry.getRandom();

            assertTrue(
                "expect '" + generated + "' only has numbers. (" + entry + ")",
                generated.matches("[0-9]+")
            );
            assertEquals(7, entry.getLength());
        }
    }

    @Test
    public void expectZeroLengthEntryGeneratesZeroLengthStrings() {
        BbanStructureEntry entry = BbanStructureEntry.accountType(0, 'n');
        for (int i = 0; i < 100; i++) {
            String generated = entry.getRandom();

            assertEquals(
                "expect that entry with length=0 generates zero-length strings. (" + entry + ")",
                "",
                generated
            );
        }
    }

    @Test
    public void expectAlphabeticEntryGeneratesAllUppercaseLetters() {
        // make sure that when generating random entries, all letters of the alphabet are used
        BbanStructureEntry entry = BbanStructureEntry.accountNumber(10_000, 'a');
        String generated = entry.getRandom();

        String distinctChars = getDistinctSortedChars(generated);

        assertEquals(
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            distinctChars
        );
    }

    @Test
    public void expectNumericEntryGeneratesAllNumbers() {
        // make sure that when generating random entries, all numbers are used
        BbanStructureEntry entry = BbanStructureEntry.accountNumber(10_000, 'n');
        String generated = entry.getRandom();

        String distinctChars = getDistinctSortedChars(generated);

        assertEquals(
            "0123456789",
            distinctChars
        );
    }

    @Test
    public void expectAlphanumericEntryGeneratesAllLettersAndNumbers() {
        // make sure that when generating random entries, all letters and numbers are used
        BbanStructureEntry entry = BbanStructureEntry.accountNumber(10_000, 'c');
        String generated = entry.getRandom();

        String distinctChars = getDistinctSortedChars(generated);

        assertEquals(
            "0123456789"
//                + "abcdefghijklmnopqrstuvwxyz"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            distinctChars
        );
    }

    private static String getDistinctSortedChars(String s) {
        Stream<Character> chars = CharBuffer.wrap(s.toCharArray()).chars()
            .mapToObj(ch -> (char) ch);
        return chars.distinct()
            .sorted()
            .map(Objects::toString)
            .collect(Collectors.joining(""));
    }
}
