package org.iban4j.support;

import org.iban4j.CountryCode;

public interface IbanConfiguration {

    String getStructure(CountryCode countryCode);

}
