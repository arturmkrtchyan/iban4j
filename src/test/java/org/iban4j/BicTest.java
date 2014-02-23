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

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class BicTest {

    public static class BicCreationTest1 {

        @Test(expected = BicFormatException.class)
        public void bicConstructionWithInvalidCountryCodeShouldThrowException() {
            Bic.valueOf("DEUTAAFF500");
        }

        @Test
        public void bicsWithSameDataShouldBeEqual() {
            Bic bic1 = Bic.valueOf("DEUTDEFF500");
            Bic bic2 = Bic.valueOf("DEUTDEFF500");

            assertThat(bic1, is(equalTo(bic2)));
        }

        @Test
        public void bicsWithDifferentDataShouldNotBeEqual() {
            Bic bic1 = Bic.valueOf("DEUTDEFF500");
            Bic bic2 = Bic.valueOf("DEUTDEFF501");

            assertThat(bic1, is(not(equalTo(bic2))));
        }

        @Test
        public void bicsWithStringValueAndBicShouldNotBeEqual() {
            Bic bic = Bic.valueOf("DEUTDEFF500");

            assertNotEquals(bic, "DEUTDEFF500");
        }

        @Test
        public void bicsWithSameDataShouldHaveSameHashCode() {
            Bic bic1 = Bic.valueOf("DEUTDEFF500");
            Bic bic2 = Bic.valueOf("DEUTDEFF500");

            assertThat(bic1.hashCode(), is(equalTo(bic2.hashCode())));
        }

        @Test
        public void ibansWithDifferentDataShouldNotHaveSameHashCode() {
            Bic bic1 = Bic.valueOf("DEUTDEFF500");
            Bic bic2 = Bic.valueOf("DEUTDEFF501");

            assertThat(bic1.hashCode(), is(not(equalTo(bic2.hashCode()))));
        }

    }

    @RunWith(Parameterized.class)
    public static class BicCreationTest2 {

        private String bicString;

        public BicCreationTest2(String bicString) {
            this.bicString = bicString;
        }

        @Test
        public void bicConstructionWithValueOfShouldReturnBic() {
            assertThat(Bic.valueOf(bicString), is(notNullValue()));
        }

        @Parameterized.Parameters
        public static Collection<Object[]> ibanParameters() {
            return TestDataHelper.getBicData();
        }

    }

}
