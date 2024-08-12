package com.alphabot.register.integration.alphabot.dto;

import java.util.List;

public class Register {
    Boolean success;
    RegisterData data;

    List<Error> errors;
    public Register() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public RegisterData getRegisterData() {
        return data;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
