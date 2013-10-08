package org.iban4j;

import org.iban4j.support.IbanConfiguration;
import org.iban4j.support.IbanStructure;
import org.iban4j.support.PropertiesLoaderUtils;
import org.iban4j.support.PropertyBasedIbanConfiguration;

import java.io.IOException;
import java.util.Properties;

public class DefaultIbanGenerator implements IbanGenerator {

    private static final String DEFAULT_PROPERTIES_FILE = "iban.properties";
    private static final String DEFAULT_CHECK_DIGIT = "00";

    private IbanConfiguration configuration;

    private CountryCode coutryCode;
    private String bankCode;
    private String accountNumber;

    public DefaultIbanGenerator() {
    }

    @Override
    public Iban generate(String accountNumber) throws IllegalStateException {
        if(configuration == null) {
            configure();
        }

        IbanStructure structure = configuration.getStructure(coutryCode);
        Bban bban = constructBban(structure);
        String checkDigit = IbanUtil.calculateCheckDigit(new Iban(coutryCode, DEFAULT_CHECK_DIGIT, bban));
        return new Iban(coutryCode, checkDigit, bban);
    }

    private Bban constructBban(IbanStructure structure) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private String constructIbanString(IbanStructure structure, String checkDigit) {
        // TODO implement
        return "testtodo";
    }

    public void setCountryCode(CountryCode countryCode) {
        this.coutryCode = countryCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    protected void setConfiguration(IbanConfiguration configuration) {
        this.configuration = configuration;
    }

    private void configure() throws IllegalStateException {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(DEFAULT_PROPERTIES_FILE);
        } catch (IOException e) {
            throw new IllegalStateException("Can not configure IbanGenerator", e);
        }
        configuration = new PropertyBasedIbanConfiguration(properties);
    }

    public static void main(String[] args) {
        DefaultIbanGenerator generator = new DefaultIbanGenerator();
        generator.setCountryCode(CountryCode.AT);
        generator.setBankCode("19043");
        Iban iban = generator.generate("00234573201");
        System.out.println(iban);
    }

}
