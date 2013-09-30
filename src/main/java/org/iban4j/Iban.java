package org.iban4j;

public final class Iban {

    private final String value;

    public Iban(String value) {
        this.value = value;
    }

    private static Iban valueOf(String value) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Integer) {
            return value.equals(((Iban)obj).toString());
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
