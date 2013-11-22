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

/**
 * Property resolver class.
 */
class PropertyResolver {

    private Properties properties;

    PropertyResolver(final Properties properties) {
        this.properties = properties;
    }

    /**
     * Reads string property from properties object.
     *
     * @param key
     * @return the value of specified key.
     */
    String getProperty(final String key) {
        return properties.getProperty(key);
    }

    /**
     * Reads int property from properties object.
     *
     * @param key
     * @return the value of specified key.
     */
    int getIntProperty(final String key) throws NumberFormatException {
        String value = properties.getProperty(key);
        return Integer.parseInt(value);
    }

    /**
     * Reads string array property from properties object.
     *
     * @param key
     * @return the values of specified key.
     */
    String[] getProperties(final String key) {
        String value = properties.getProperty(key);
        return value.split("[,]");
    }
}
