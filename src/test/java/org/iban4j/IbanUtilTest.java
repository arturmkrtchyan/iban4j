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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("IbanUtilTest")
public class IbanUtilTest {
    
    @DisplayName("ibanCountrySupportCheckWithNullShouldReturnFalse")
    @Test
    public void ibanCountrySupportCheckWithNullShouldReturnFalse() {
        assertThat(IbanUtil.isSupportedCountry(null), is(equalTo(false)));
    }

    @DisplayName("ibanCountrySupportCheckWithSupportedCountryShouldReturnTrue")
    @Test
    public void ibanCountrySupportCheckWithSupportedCountryShouldReturnTrue() {
        assertThat(IbanUtil.isSupportedCountry(CountryCode.BE), is(equalTo(true)));
    }

    @DisplayName("ibanCountrySupportCheckWithUnsupportedCountryShouldReturnFalse")
    @Test
        public void ibanCountrySupportCheckWithUnsupportedCountryShouldReturnFalse() {
            assertThat(IbanUtil.isSupportedCountry(CountryCode.AM), is(equalTo(false)));
        }

    @DisplayName("unformattedIbanValidationWithNoneFormattingShouldNotThrowException")
    @Test
        public void unformattedIbanValidationWithNoneFormattingShouldNotThrowException() {
            IbanUtil.validate("AT611904300234573201", IbanFormat.None);
        }
    @DisplayName("unformattedIbanValidationWithNoneFormattingShouldNotThrowException")
    @Test
        public void unformattedIbanIsValidWithNoneFormattingShouldNotThrowException() {
            assertTrue(IbanUtil.isValid("AT611904300234573201", IbanFormat.None));
        }

    @DisplayName("formattedIbanValidationWithDefaultFormattingShouldNotThrowException")
    @Test
        public void formattedIbanValidationWithDefaultFormattingShouldNotThrowException() {
            IbanUtil.validate("AT61 1904 3002 3457 3201", IbanFormat.Default);
        }
    @DisplayName("formattedIbanValidationWithDefaultFormattingShouldNotThrowException")
    @Test
        public void formattedIbanIsValidWithDefaultFormattingShouldNotThrowException() {
            assertTrue(IbanUtil.isValid("AT61 1904 3002 3457 3201", IbanFormat.Default));
        }
    }







