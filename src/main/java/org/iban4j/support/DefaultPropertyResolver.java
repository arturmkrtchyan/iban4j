package org.iban4j.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DefaultPropertyResolver implements PropertyResolver {

    private Properties properties;

    public DefaultPropertyResolver(String propertyFileName) throws IOException {
        resolve(propertyFileName);
    }

    private void resolve(String propertyFileName) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(propertyFileName);
        properties = new Properties();
        properties.load(is);
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
