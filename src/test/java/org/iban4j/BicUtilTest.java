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

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;

@RunWith(Enclosed.class)
public class BicUtilTest {

    public static class InvalidBicValidationTest {

        @Rule
        public ExpectedException expectedException = ExpectedException.none();


        @Test
        public void bicValidationWithNullShouldThrowException() {
            expectedException.expect(BicFormatException.class);
            expectedException.expectMessage(containsString("Null can't be a valid Bic"));
            BicUtil.validate(null);
        }

        @Test
        public void bicValidationWithEmptyStringShouldThrowException() {
            expectedException.expect(BicFormatException.class);
            expectedException.expectMessage(containsString("Empty string can't be a valid Bic"));
            BicUtil.validate("");
        }

        @Test
        public void bicValidationWithLessCharactersShouldThrowException() {
            expectedException.expect(BicFormatException.class);
            expectedException.expectMessage(containsString("Bic length must be 8 or 11"));
            BicUtil.validate("DEUTFF");
        }

        @Test
        public void bicValidationWithMoreCharactersShouldThrowException() {
            expectedException.expect(BicFormatException.class);
            expectedException.expectMessage(containsString("Bic length must be 8 or 11"));
            BicUtil.validate("DEUTFFDEUTFF");
        }

        @Test
        public void bicValidationWithLowercaseShouldThrowException() {
            expectedException.expect(BicFormatException.class);
            expectedException.expectMessage(containsString("Bic must contain only upper case letters"));
            BicUtil.validate("DEUTdeFF");
        }

        @Test
        public void bicValidationWithInvalidBankCodeShouldThrowException() {
            expectedException.expect(BicFormatException.class);
            expectedException.expectMessage(containsString("Bank code must contain only letters"));
            BicUtil.validate("DEU1DEFF");
        }

        @Test
        public void bicValidationWithNonExistingCountryCodeShouldThrowException() {
            expectedException.expect(UnsupportedCountryException.class);
            expectedException.expectMessage(containsString("Country code is not supported"));
            BicUtil.validate("DEUTDDFF");
        }

        @Test
        public void bicValidationWithInvalidCountryCodeShouldThrowException() {
            expectedException.expect(BicFormatException.class);
            expectedException.expectMessage(containsString("Bic country code must contain upper case letters"));
            BicUtil.validate("DEUT_1FF");
        }

        @Test
        public void bicValidationWithInvalidLocationCodeShouldThrowException() {
            expectedException.expect(BicFormatException.class);
            expectedException.expectMessage(containsString("Location code must contain only letters or digits"));
            BicUtil.validate("DEUTDEF ");
        }

        @Test
        public void bicValidationWithInvalidBranchCodeShouldThrowException() {
            expectedException.expect(BicFormatException.class);
            expectedException.expectMessage(containsString("Branch code must contain only letters or digits"));
            BicUtil.validate("DEUTDEFF50_");
        }
    }

}
