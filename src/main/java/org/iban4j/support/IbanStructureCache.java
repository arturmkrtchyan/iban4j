package org.iban4j.support;

import org.iban4j.CountryCode;

import java.io.IOException;
import java.util.Properties;

public class IbanStructureCache {

    private static final String DEFAULT_PROPERTIES_FILE = "iban.properties";

    private static IbanConfiguration configuration = doConfigure();

    private static IbanConfiguration doConfigure() throws IllegalStateException {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(DEFAULT_PROPERTIES_FILE);
        } catch (IOException e) {
            throw new IllegalStateException("Can not find configuration file", e);
        }
        return new PropertyBasedIbanConfiguration(properties);
    }

    public static IbanStructure getStructure(CountryCode countryCode) {
        return configuration.getStructure(countryCode);
    }


}
