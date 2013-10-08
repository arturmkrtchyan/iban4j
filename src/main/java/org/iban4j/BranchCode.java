package org.iban4j;

import java.io.Serializable;

public final class BranchCode implements Serializable {

    private static final long serialVersionUID = 1422460615174116001L;

    private final String value;

    public BranchCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BranchCode{" +
                "value='" + value + '\'' +
                '}';
    }
}


