package com.alphabot.register.controller.dto;

public class TelegramRegisterResponse {

    Boolean success;
    String message;

    public Boolean getSuccess() {
        return success;
    }

    public TelegramRegisterResponse setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TelegramRegisterResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public TelegramRegisterResponse setErrorWithMessage(String errorMessage) {
        this.success = false;
        this.message = errorMessage;
        return this;
    }
}
