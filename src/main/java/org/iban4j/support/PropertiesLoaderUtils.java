package org.iban4j.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesLoaderUtils {

    private PropertiesLoaderUtils() {
    }

    public static Properties loadProperties(String path) throws IOException {
        Properties properties = new Properties();
        InputStream is = PropertiesLoaderUtils.class.getClassLoader().getResourceAsStream(path);

        try {
            properties.load(is);
        } finally {
            is.close();
        }

        return properties;

    }
}
