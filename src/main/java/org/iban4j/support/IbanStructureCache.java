package org.iban4j.support;

import java.io.IOException;
import java.util.Properties;

public class IbanStructureCache {

    private static final String DEFAULT_PROPERTIES_FILE = "iban.properties";

    private static IbanConfiguration configuration = doConfigure();

    private IbanStructureCache() {
    }

    public static IbanStructure getStructure(String countryCode) {
        return configuration.getStructure(countryCode);
    }

    private static IbanConfiguration doConfigure() throws IllegalStateException {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtil.loadProperties(DEFAULT_PROPERTIES_FILE);
        } catch (IOException e) {
            throw new IllegalStateException("Can not find configuration file", e);
        }
        return new IbanConfiguration(properties);
    }


}
