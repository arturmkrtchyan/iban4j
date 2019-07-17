package com.swift;

public class RegistryFormat {

    private String bbanStructure;
    private String countryCode;
    private String countryName;
    private String ibanElectronicFormatExample;

    public RegistryFormat(
        String bbanStructure,
        String countryCode,
        String countryName,
        String ibanElectronicFormatExample
    ) {
        this.bbanStructure = bbanStructure;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.ibanElectronicFormatExample = ibanElectronicFormatExample;
    }

    public String getBbanStructure() {
        return bbanStructure;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getIbanElectronicFormatExample() {
        return ibanElectronicFormatExample;
    }
}

