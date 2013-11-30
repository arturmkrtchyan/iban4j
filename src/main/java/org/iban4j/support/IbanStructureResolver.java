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
package org.iban4j.support;

import java.io.IOException;
import java.util.Properties;

/**
 * Iban Structure Resolver.
 */
public class IbanStructureResolver {

    private static final String DEFAULT_PROPERTIES_FILE = "iban.properties";

    private static final String IBAN_STRUCTURE_SUFFIX = ".structure";

    private static PropertyResolver propertyResolver = configurePropertyResolver();

    private IbanStructureResolver() {
    }

    /**
     * Returns iban structure for specified country code.
     *
     * @param countryCode
     * @return iban structure for specified country code.
     * @throws IllegalArgumentException if there is no structure defined for specified country.
     */
    public static IbanStructure getStructure(final String countryCode) {
        String key = countryCode.toLowerCase() + IBAN_STRUCTURE_SUFFIX;
        String structure = propertyResolver.getProperty(key);
        Assert.notNull(structure, "Structure is not defined for specified country.");
        return IbanStructure.valueOf(structure);
    }

    private static PropertyResolver configurePropertyResolver() throws IllegalStateException {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtil.loadProperties(DEFAULT_PROPERTIES_FILE);
        } catch (IOException e) {
            throw new IllegalStateException("Can not find configuration file", e);
        }
        return new PropertyResolver(properties);
    }
}
