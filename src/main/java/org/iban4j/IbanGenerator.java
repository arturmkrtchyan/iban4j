package org.iban4j;

public interface IbanGenerator {

    Iban generate(String accountNumber);
}
