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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CharacterUtilTest")
public class CharacterUtilTest {

    @Test
    @DisplayName("isAsciiDigit should return true for ASCII digits")
    public void isAsciiDigitShouldReturnTrueForAsciiDigits() {
        assertTrue(CharacterUtil.isAsciiDigit('0'));
        assertTrue(CharacterUtil.isAsciiDigit('5'));
        assertTrue(CharacterUtil.isAsciiDigit('9'));
    }

    @Test
    @DisplayName("isAsciiDigit should return false for non-ASCII digits")
    public void isAsciiDigitShouldReturnFalseForNonAsciiDigits() {
        assertFalse(CharacterUtil.isAsciiDigit('A'));
        assertFalse(CharacterUtil.isAsciiDigit('a'));
        assertFalse(CharacterUtil.isAsciiDigit('٠')); // Arabic-Indic digit zero
        assertFalse(CharacterUtil.isAsciiDigit('០')); // Khmer digit zero
    }

    @Test
    @DisplayName("isAsciiUppercaseLetter should return true for ASCII uppercase letters")
    public void isAsciiUppercaseLetterShouldReturnTrueForAsciiUppercaseLetters() {
        assertTrue(CharacterUtil.isAsciiUppercaseLetter('A'));
        assertTrue(CharacterUtil.isAsciiUppercaseLetter('M'));
        assertTrue(CharacterUtil.isAsciiUppercaseLetter('Z'));
    }

    @Test
    @DisplayName("isAsciiUppercaseLetter should return false for non-ASCII uppercase letters")
    public void isAsciiUppercaseLetterShouldReturnFalseForNonAsciiUppercaseLetters() {
        assertFalse(CharacterUtil.isAsciiUppercaseLetter('a'));
        assertFalse(CharacterUtil.isAsciiUppercaseLetter('0'));
        assertFalse(CharacterUtil.isAsciiUppercaseLetter('Е')); // Cyrillic capital E
        assertFalse(CharacterUtil.isAsciiUppercaseLetter('А')); // Cyrillic capital A
    }

    @Test
    @DisplayName("isValidAlphanumeric should return true for valid characters")
    public void isValidAlphanumericShouldReturnTrueForValidCharacters() {
        assertTrue(CharacterUtil.isValidAlphanumeric('A'));
        assertTrue(CharacterUtil.isValidAlphanumeric('Z'));
        assertTrue(CharacterUtil.isValidAlphanumeric('0'));
        assertTrue(CharacterUtil.isValidAlphanumeric('9'));
    }

    @Test
    @DisplayName("isValidAlphanumeric should return false for invalid characters")
    public void isValidAlphanumericShouldReturnFalseForInvalidCharacters() {
        assertFalse(CharacterUtil.isValidAlphanumeric('a'));
        assertFalse(CharacterUtil.isValidAlphanumeric('_'));
        assertFalse(CharacterUtil.isValidAlphanumeric(' '));
        assertFalse(CharacterUtil.isValidAlphanumeric('٠')); // Arabic-Indic digit zero
        assertFalse(CharacterUtil.isValidAlphanumeric('០')); // Khmer digit zero
        assertFalse(CharacterUtil.isValidAlphanumeric('Е')); // Cyrillic capital E
        assertFalse(CharacterUtil.isValidAlphanumeric('А')); // Cyrillic capital A
    }
}
