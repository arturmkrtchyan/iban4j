package org.iban4j;

import java.io.Serializable;

public final class NationalCheckDigit implements Serializable {

    private static final long serialVersionUID = 5738272885542458513L;

    private final String value;

    public NationalCheckDigit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NationalCheckDigit{" +
                "value='" + value + '\'' +
                '}';
    }
}
