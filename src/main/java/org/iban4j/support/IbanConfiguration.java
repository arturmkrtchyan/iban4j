package org.iban4j.support;

import org.iban4j.CountryCode;

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
