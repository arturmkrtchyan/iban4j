package org.iban4j;

import java.io.Serializable;

public final class AccountType implements Serializable {

    private static final long serialVersionUID = 1600689515601756104L;

    private final String value;

    public AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "value='" + value + '\'' +
                '}';
    }
}
