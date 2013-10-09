package org.iban4j.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertyResolver {

    private Properties properties;

    PropertyResolver(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) throws NumberFormatException {
        String value = properties.getProperty(key);
        return Integer.parseInt(value);
    }

    public String[] getProperties(String key) {
        String value = properties.getProperty(key);
        return value.split("[,]");
    }
}
