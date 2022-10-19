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

import java.util.Arrays;
import java.util.Collection;
import org.iban4j.CountryCode;
import org.iban4j.bban.BbanStructure;
import org.iban4j.bban.BbanStructureEntry;

/**
 * Example {@link BbanStructure} provider for Iraq.
 *
 * @author L&aacute;szl&oacute;-R&oacute;bert Albert (robert@albertlr.ro)
 * @see <a href="https://www.swift.com/resource/iban-registry-pdf">SWIFT IBAN Registry</a>
 */
public class IraqBbanStructureProvider implements BbanStructureProvider {
    @Override
    public boolean supportsCountry(CountryCode countryCode) {
        return CountryCode.IQ == countryCode;
    }

    @Override
    public BbanStructure forCountry(CountryCode countryCode) {
        if (CountryCode.IQ == countryCode) {
            // '4!a3!n12!n'
            return
                    new BbanStructure(
                            BbanStructureEntry.bankCode(4, 'a'),
                            BbanStructureEntry.branchCode(3, 'n'),
                            BbanStructureEntry.accountNumber(12, 'n')
                    );
        }
        return null;
    }

    @Override
    public Collection<CountryCode> supportedCountries() {
        return Arrays.asList(CountryCode.IQ);
    }
}
