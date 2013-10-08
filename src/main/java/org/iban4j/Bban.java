package org.iban4j;

import java.io.Serializable;

public final class Bban implements Serializable {

    private static final long serialVersionUID = 8748285872603350092L;

    private final String value;

    public Bban(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Bban{" +
                "value='" + value + '\'' +
                '}';
    }
}
