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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.iban4j.TestDataHelper.defaultExceptionMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("BIC Util Test class")
public class BicUtilTest {

        @Test
        @DisplayName("validate BIC with null value")
        public void bicValidationWithNullShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () -> BicUtil.validate(null),
                    defaultExceptionMessage);
            assertThat(thrown.getMessage(), containsString("Null can't be a valid Bic"));
        }

        @Test
        @DisplayName("validate BIC with empty value")
        public void bicValidationWithEmptyStringShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate(""),
                    defaultExceptionMessage);
            assertThat(thrown.getMessage(), containsString("Empty string can't be a valid Bic"));
        }

        @Test
        @DisplayName("validate BIC with  less characters")
        public void bicValidationWithLessCharactersShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTFF"),
                    defaultExceptionMessage);
            assertEquals("Bic length must be 8 or 11", thrown.getMessage());
        }

        @Test
        @DisplayName("validate BIC with more characters")
        public void bicValidationWithMoreCharactersShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTFFDEUTFF"),
                    defaultExceptionMessage);
            assertEquals("Bic length must be 8 or 11", thrown.getMessage());
        }

        @Test
        @DisplayName("validate BIC with lowercase")
        public void bicValidationWithLowercaseShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTdeFF"),
                    defaultExceptionMessage);
            assertThat(thrown.getMessage(), containsString("Bic must contain only upper case letters"));
        }

        @Test
        @DisplayName("validate BIC with invalid bank code")
        public void bicValidationWithInvalidBankCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEU_DEFF"),
                    defaultExceptionMessage);
            assertThat(thrown.getMessage(), containsString("Bank code must contain only alphanumeric"));
        }

        @Test
        @DisplayName("validate BIC with non existing country code")
        public void bicValidationWithNonExistingCountryCodeShouldThrowException() {
            UnsupportedCountryException thrown = assertThrows(
                    UnsupportedCountryException.class,
                    () -> BicUtil.validate("DEUTDDFF"),
                    defaultExceptionMessage);
            assertThat(thrown.getMessage(), containsString("Country code is not supported"));
        }

        @Test
        @DisplayName("validate BIC with invalid country code")
        public void bicValidationWithInvalidCountryCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUT_1FF"),
                    defaultExceptionMessage);
            assertEquals("Bic country code must contain upper case letters", thrown.getMessage());
        }

        @Test
        @DisplayName("validate BIC with invalid location code")
        public void bicValidationWithInvalidLocationCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTDEF "),
                    defaultExceptionMessage);
            assertThat(thrown.getMessage(), containsString("Location code must contain only letters or digits"));
        }

        @Test
        @DisplayName("validate BIC with invalid branch code")
        public void bicValidationWithInvalidBranchCodeShouldThrowException() {
            BicFormatException thrown = assertThrows(
                    BicFormatException.class,
                    () ->             BicUtil.validate("DEUTDEFF50_"),
                    defaultExceptionMessage);
            assertThat(thrown.getMessage(), containsString("Branch code must contain only letters or digits"));
        }
        @Test
        @DisplayName("validate BIC with alphanumeric bank code")
        public void bicValidationWithAlphanumericBankCode() {
            BicUtil.validate("1234DEFFXXX");
        }

}
