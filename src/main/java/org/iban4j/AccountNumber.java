package org.iban4j;

import java.io.Serializable;

public final class AccountNumber implements Serializable {

    private static final long serialVersionUID = -8429928953300587111L;

    private final String value;

    public AccountNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AccountNumber{" +
                "value='" + value + '\'' +
                '}';
    }
}
