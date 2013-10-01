package org.iban4j.support;

import org.iban4j.CountryCode;

public class PropertyBasedIbanConfiguration implements IbanConfiguration {
    
    private static final String IBAN_PROPERTIES_FILE = "iban.properties";
    
    private PropertyResolver propertyResolver;
    
    public PropertyBasedIbanConfiguration(PropertyResolver propertyResolver) {
        this.propertyResolver = propertyResolver;
    }

    public String getStructure(CountryCode countryCode) {
        return null;
    }
}
