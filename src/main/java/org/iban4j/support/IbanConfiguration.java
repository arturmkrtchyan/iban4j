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

import java.util.Properties;

class IbanConfiguration {
    
    private static final String IBAN_STRUCTURE_SUFFIX = ".structure";
    
    private PropertyResolver propertyResolver;
    
    public IbanConfiguration(Properties properties) {
        propertyResolver = new PropertyResolver(properties);
    }

    public IbanConfiguration(PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
    }

    public IbanStructure getStructure(String countryCode) {
        String key = countryCode.toLowerCase() + IBAN_STRUCTURE_SUFFIX;
        String structure = propertyResolver.getProperty(key);
        return IbanStructure.valueOf(structure);
    }
}
