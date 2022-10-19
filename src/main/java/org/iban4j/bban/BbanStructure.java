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
package org.iban4j.bban;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;
import org.iban4j.CountryCode;
import org.iban4j.spi.BbanStructureProvider;


/**
 * Class which represents bban structure
 */
public class BbanStructure {

    private static final Iterable<BbanStructureProvider> providers;

    static {
        providers = ServiceLoader.load(BbanStructureProvider.class);
    }

    private final BbanStructureEntry[] entries;

    public BbanStructure(final BbanStructureEntry... entries) {
        this.entries = entries;
    }

    /**
     * @param countryCode the country code.
     * @return BbanStructure for specified country or null if country is not supported.
     */
    public static BbanStructure forCountry(final CountryCode countryCode) {
        for (BbanStructureProvider provider : providers) {
            if (provider.supportsCountry(countryCode)) {
                return provider.forCountry(countryCode);
            }
        }
        return null;
    }

    public List<BbanStructureEntry> getEntries() {
        return Collections.unmodifiableList(Arrays.asList(entries));
    }

    public static List<CountryCode> supportedCountries() {
        final Set<CountryCode> countryCodes = new TreeSet<>();
        for (BbanStructureProvider provider : providers) {
            countryCodes.addAll(provider.supportedCountries());
        }
        return Collections.unmodifiableList(new ArrayList<>(countryCodes));
    }

    /**
     * Returns the length of bban.
     *
     * @return int length
     */
    public int getBbanLength() {
        int length = 0;

        for (BbanStructureEntry entry : entries) {
            length += entry.getLength();
        }

        return length;
    }
}
