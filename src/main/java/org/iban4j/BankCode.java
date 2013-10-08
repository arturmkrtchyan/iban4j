package org.iban4j;

import java.io.Serializable;

public final class BankCode implements Serializable {

    private static final long serialVersionUID = 7812773067120054507L;

    private final String value;

    public BankCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BankCode{" +
                "value='" + value + '\'' +
                '}';
    }
}
