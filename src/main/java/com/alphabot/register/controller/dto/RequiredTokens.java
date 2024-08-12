package com.alphabot.register.controller.dto;

public class RequiredTokens {

    String address;
    String name;
    String openseaSlug;
    Boolean isMultiplier;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenseaSlug() {
        return openseaSlug;
    }

    public void setOpenseaSlug(String openseaSlug) {
        this.openseaSlug = openseaSlug;
    }

    public Boolean getMultiplier() {
        return isMultiplier;
    }

    public void setMultiplier(Boolean multiplier) {
        isMultiplier = multiplier;
    }
}
