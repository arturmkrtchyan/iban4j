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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("BIC Util Test class")
public class BicUtilTest {

    public static class InvalidBicValidationTest {

//        @Rule
//        public ExpectedException expectedException = ExpectedException.none();


        @Test
        public void bicValidationWithNullShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate(null),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Null can't be a valid Bic"));
        }

        @Test
        public void bicValidationWithEmptyStringShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate(""),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Empty string can't be a valid Bic"));
        }

        @Test
        public void bicValidationWithLessCharactersShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTFF"),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Bic length must be 8 or 11"));
        }

        @Test
        public void bicValidationWithMoreCharactersShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTFFDEUTFF"),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Bic length must be 8 or 11"));
        }

        @Test
        public void bicValidationWithLowercaseShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTdeFF"),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Bic must contain only upper case letters"));
        }

        @Test
        public void bicValidationWithInvalidBankCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEU1DEFF"),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Bank code must contain only letters"));

        }

        @Test
        public void bicValidationWithNonExistingCountryCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTDDFF"),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Country code is not supported"));
        }

        @Test
        public void bicValidationWithInvalidCountryCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUT_1FF"),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Bic country code must contain upper case letters"));
        }

        @Test
        public void bicValidationWithInvalidLocationCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTDEF "),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Location code must contain only letters or digits"));

        }

        @Test
        public void bicValidationWithInvalidBranchCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTDEFF50_"),
                    "Expected doThing() to throw, but it didn't"
            );
            assertTrue(thrown.getMessage().contentEquals("Branch code must contain only letters or digits"));

        }
    }

}
