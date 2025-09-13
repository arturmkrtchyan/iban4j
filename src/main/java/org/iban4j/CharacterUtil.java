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

/**
 * Utility class for ASCII-only character validation in financial code processing.
 * <p>
 * Financial standards like IBAN and BIC expect only ASCII characters: digits 0-9 and uppercase letters A-Z.
 * Java's Character.isDigit() and Character.isLetter() are too flexible as they accept
 * Unicode characters which are not valid in financial code formats and can lead to security vulnerabilities.
 * <p>
 * This utility prevents Unicode homograph attacks by ensuring only ASCII characters are accepted.
 */
public final class CharacterUtil {

    private CharacterUtil() {
    }

    /**
     * Checks if character is ASCII digit (0-9 only).
     * Rejects Unicode digits like Arabic-Indic digits (٠-٩) or other Unicode digit characters.
     */
    public static boolean isAsciiDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    /**
     * Checks if character is ASCII uppercase letter (A-Z only).
     * Rejects Unicode letters like Cyrillic (А, Е) or other Unicode letter characters.
     */
    public static boolean isAsciiUppercaseLetter(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * Checks if character is valid for financial code alphanumeric fields (A-Z, 0-9).
     * Used for IBAN and BIC validation where only ASCII alphanumeric characters are allowed.
     */
    public static boolean isValidAlphanumeric(char ch) {
        return isAsciiUppercaseLetter(ch) || isAsciiDigit(ch);
    }
}
