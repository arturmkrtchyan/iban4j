package org.iban4j.support;

public interface PropertyResolver {

    public String getProperty(String key);

    public int getIntProperty(String key) throws NumberFormatException;

    public String[] getProperties(String key);

}
