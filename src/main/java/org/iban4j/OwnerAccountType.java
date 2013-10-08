package org.iban4j;

import java.io.Serializable;

public final class OwnerAccountType implements Serializable {

    private static final long serialVersionUID = -5226459710818375754L;

    private final String value;

    public OwnerAccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "OwnerAccountType{" +
                "value='" + value + '\'' +
                '}';
    }
}
