package org.iban4j.support;

import org.iban4j.CountryCode;

interface IbanConfiguration {

    IbanStructure getStructure(CountryCode countryCode);

}
