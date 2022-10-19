/*
 * Copyright 2021 L&aacute;szl&oacute;-R&oacute;bert Albert (robert@albertlr.ro)
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
package org.iban4j.spi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ServiceLoader;
import org.iban4j.CountryCode;
import org.iban4j.IbanUtil;
import org.iban4j.UnsupportedCountryException;
import org.iban4j.bban.BbanStructure;
import org.junit.Test;

/**
 * Testing an extension point implementing the Iraq {@link org.iban4j.bban.BbanStructure}.
 *
 * @author L&aacute;szl&oacute;-R&oacute;bert Albert (robert@albertlr.ro)
 * @see IraqBbanStructureProvider
 */
public class IraqBbanStructureTest {

    @Test
    public void shouldValidateAnIbanFromIraq() {
        // if you remove or rename the org.iban4j.spi.BbanStructureProvider from src/test/resources/META-INF/services
        // this test will fail

        try {
            IbanUtil.validate("IQ98NBIQ850123456789012");
        } catch (UnsupportedCountryException exception) {
            // expect to fail with this error if IraqBbanStructureProvider is not present
            fail(exception.getMessage());
        } catch (Exception exception) {
            // not expected at all
            fail("Not expected: " + exception.getMessage());
        }
    }

    @Test
    public void shouldSeeIraqBbanStructureProviderThroughSpi() {
        ServiceLoader<BbanStructureProvider> providers = ServiceLoader.load(BbanStructureProvider.class);

        boolean tested = false;
        for (BbanStructureProvider provider : providers) {
            if (provider instanceof IraqBbanStructureProvider) {
                assertEquals(1, provider.supportedCountries().size());
                assertEquals(CountryCode.IQ, provider.supportedCountries().iterator().next());
                for (CountryCode countryCode : CountryCode.values()) {
                    switch (countryCode) {
                        case IQ:
                            assertTrue(provider.supportsCountry(CountryCode.IQ));
                            break;
                        default:
                            assertFalse(provider.supportsCountry(countryCode));
                            break;
                    }

                }
                tested = true;
            }
        }
        assertTrue("IraqBbanStructureProvider not loaded", tested);
    }

    @Test
    public void shouldHaveIraqAsSupportedCountry() {
        assertTrue(BbanStructure.supportedCountries().contains(CountryCode.IQ));
    }
}
