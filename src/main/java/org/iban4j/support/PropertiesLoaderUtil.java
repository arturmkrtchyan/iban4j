package org.iban4j.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesLoaderUtil {

    private PropertiesLoaderUtil() {
    }

    static Properties loadProperties(String path) throws IOException {
        Properties properties = new Properties();
        InputStream is = PropertiesLoaderUtil.class.getClassLoader().getResourceAsStream(path);

        try {
            properties.load(is);
        } finally {
            is.close();
        }

        return properties;

    }
}
