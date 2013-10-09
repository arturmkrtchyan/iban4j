package org.iban4j.support;

import org.iban4j.CountryCode;

import java.util.Properties;

class PropertyBasedIbanConfiguration implements IbanConfiguration {
    
    private static final String IBAN_STRUCTURE_SUFFIX = ".structure";
    
    private PropertyResolver propertyResolver;
    
    public PropertyBasedIbanConfiguration(Properties properties) {
        propertyResolver = new DefaultPropertyResolver(properties);
    }

    public PropertyBasedIbanConfiguration(PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
    }

    public IbanStructure getStructure(CountryCode countryCode) {
        String key = countryCode.getAlpha2().toLowerCase() + IBAN_STRUCTURE_SUFFIX;
        String structure = propertyResolver.getProperty(key);
        return IbanStructure.valueOf(structure);
    }
}
