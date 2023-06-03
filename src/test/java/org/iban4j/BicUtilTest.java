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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("BIC Util Test class")
public class BicUtilTest {

    public static class InvalidBicValidationTest {

        String defaultExceptionMessage ="Expected doThing() to throw, but it didn't";

        @Test
        public void bicValidationWithNullShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () -> BicUtil.validate(null),
                    defaultExceptionMessage);
            assertEquals("Null can't be a valid Bic", thrown.getMessage());
        }

        @Test
        public void bicValidationWithEmptyStringShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate(""),
                    defaultExceptionMessage);
            assertEquals("Empty string can't be a valid Bic", thrown.getMessage());
        }

        @Test
        public void bicValidationWithLessCharactersShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTFF"),
                    defaultExceptionMessage);
            assertEquals("Bic length must be 8 or 11", thrown.getMessage());
        }

        @Test
        public void bicValidationWithMoreCharactersShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTFFDEUTFF"),
                    defaultExceptionMessage);
            assertEquals("Bic length must be 8 or 11", thrown.getMessage());
        }

        @Test
        public void bicValidationWithLowercaseShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTdeFF"),
                    defaultExceptionMessage);
            assertEquals("Bic must contain only upper case letters", thrown.getMessage());
        }

        @Test
        public void bicValidationWithInvalidBankCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEU1DEFF"),
                    defaultExceptionMessage);
            assertEquals("Bank code must contain only letters", thrown.getMessage());
        }

        @Test
        public void bicValidationWithNonExistingCountryCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTDDFF"),
                    defaultExceptionMessage);
            assertEquals("Country code is not supported", thrown.getMessage());
        }

        @Test
        public void bicValidationWithInvalidCountryCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUT_1FF"),
                    defaultExceptionMessage);
            assertEquals("Bic country code must contain upper case letters", thrown.getMessage());
        }

        @Test
        public void bicValidationWithInvalidLocationCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTDEF "),
                    defaultExceptionMessage);
            assertEquals("Location code must contain only letters or digits", thrown.getMessage());
        }

        @Test
        public void bicValidationWithInvalidBranchCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTDEFF50_"),
                    defaultExceptionMessage);
            assertEquals("Branch code must contain only letters or digits", thrown.getMessage());
        }
    }

}
