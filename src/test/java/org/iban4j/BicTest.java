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


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("BIC Test class")
public class BicTest {

    @Nested
    public  class BicCreationTest {

        @Test
        @DisplayName("Invalid country code")
        public void bicConstructionWithInvalidCountryCodeShouldThrowException() {

            Assertions.assertThrows(UnsupportedCountryException.class,()->Bic.valueOf("DEUTAAFF500"));
        }

        @Test
        public void bicsWithSameDataShouldBeEqual() {
            Bic bic1 = Bic.valueOf("DEUTDEFF500");
            Bic bic2 = Bic.valueOf("DEUTDEFF500");
            Assertions.assertEquals(bic1, bic2);
        }

        @Test
        public void bicsWithDifferentDataShouldNotBeEqual() {
            Bic bic1 = Bic.valueOf("DEUTDEFF500");
            Bic bic2 = Bic.valueOf("DEUTDEFF501");
            Assertions.assertNotEquals(bic1, bic2);
        }

        @Test
        public void bicsWithStringValueAndBicShouldNotBeEqual() {
            Bic bic = Bic.valueOf("DEUTDEFF500");
            Assertions.assertNotEquals(bic, "DEUTDEFF500");
        }

        @Test
        public void bicsWithSameDataShouldHaveSameHashCode() {
            Bic bic1 = Bic.valueOf("DEUTDEFF500");
            Bic bic2 = Bic.valueOf("DEUTDEFF500");
            Assertions.assertEquals(bic1.hashCode(), bic2.hashCode());

        }

        @Test
        public void bicsWithDifferentDataShouldNotHaveSameHashCode() {
            Bic bic1 = Bic.valueOf("DEUTDEFF500");
            Bic bic2 = Bic.valueOf("DEUTDEFF501");
            Assertions.assertNotEquals(bic1.hashCode(), bic2.hashCode());
        }

        @Test
        public void bicShouldReturnBankCode() {
            Bic bic = Bic.valueOf("DEUTDEFF500");
            Assertions.assertEquals(bic.getBankCode(),"DEUT");
        }

        @Test
        public void bicShouldReturnCountryCode() {
            Bic bic = Bic.valueOf("DEUTDEFF500");
            Assertions.assertEquals(bic.getCountryCode(), CountryCode.DE);
        }

        @Test
        public void bicShouldReturnBranchCode() {
            Bic bic = Bic.valueOf("DEUTDEFF500");
            Assertions.assertEquals(bic.getBranchCode(), "500");
        }

        @Test
        public void bicWithoutBrnachCodeShouldReturnNull() {
            Bic bic = Bic.valueOf("DEUTDEFF");
            Assertions.assertNull(bic.getBranchCode());
        }

        @Test
        public void bicShouldReturnLocationCode() {
            Bic bic = Bic.valueOf("DEUTDEFF500");
            Assertions.assertEquals(bic.getLocationCode(), "FF");
        }

        @Test
        public void bicToStringShouldReturnString() {
            Bic bic = Bic.valueOf("DEUTDEFF500");
            Assertions.assertEquals(bic.toString(), "DEUTDEFF500");
        }
    }

}
