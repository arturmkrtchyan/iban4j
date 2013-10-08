package org.iban4j;

import java.io.Serializable;

public final class CheckDigit implements Serializable {

    private static final long serialVersionUID = 3555874372527816045L;

    private final String value;

    public CheckDigit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CheckDigit{" +
                "value='" + value + '\'' +
                '}';
    }
}
