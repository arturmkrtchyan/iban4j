package org.iban4j;

import java.io.Serializable;

public final class Iban implements Serializable {

    private static final long serialVersionUID = 3507561504372065317L;

    private final String value;

    public Iban(String value) {
        this.value = value;
    }

    private static Iban valueOf(String value) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Iban) {
            return value.equals(((Iban)obj).value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
