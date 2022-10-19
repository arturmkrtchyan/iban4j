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

import java.util.Collection;
import org.iban4j.CountryCode;
import org.iban4j.bban.BbanStructure;

/**
 * SPI support for adding and extending the library to support additional {@link BbanStructure}s for countries that does
 * not yet support it.
 *
 * @author L&aacute;szl&oacute;-R&oacute;bert Albert (robert@albertlr.ro)
 */
public interface BbanStructureProvider {

    /**
     * Determine if this provider supports a given country.
     *
     * @param countryCode the country code.
     * @return Returns <tt>true</tt> if and only if this provider supports {@link BbanStructure} for a given country.
     */
    boolean supportsCountry(final CountryCode countryCode);

    /**
     * @param countryCode the country code.
     * @return BbanStructure for specified country or null if country is not supported.
     */
    BbanStructure forCountry(final CountryCode countryCode);

    Collection<CountryCode> supportedCountries();
}
