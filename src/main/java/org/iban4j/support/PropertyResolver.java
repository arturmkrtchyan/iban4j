package org.iban4j.support;

public interface PropertyResolver {

    String getProperty(String key);

    int getIntProperty(String key) throws NumberFormatException;

    String[] getProperties(String key);

}
