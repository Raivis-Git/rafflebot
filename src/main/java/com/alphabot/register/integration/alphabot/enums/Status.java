package com.alphabot.register.integration.alphabot.enums;

public enum Status {
    DRAFT("draft"),
    PRESTART("prestart"),
    ACTIVE("active"),
    ENDED("ended"),
    FINALIZED("finalized");

    private final String statusName;

    private Status(String statusName) {
        this.statusName = statusName;
    }

    public boolean equalsName(String statusName) {
        return this.statusName.equals(statusName);
    }

    public String toString() {
        return this.statusName;
    }
}
