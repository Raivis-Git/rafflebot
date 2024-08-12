package com.alphabot.register.integration.alphabot.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Error {
//    String[] errors;
    String message;

    Logger LOGGER = LoggerFactory.getLogger(Error.class);

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void handleErrors(Exception exception) {
//        String[] errors = exception.getMessage().split(":", 2);
//                String errorStatus = exception.getMessage().substring(0, 3);
//                String error = errors[1].trim().substring(1);
//                JSONObject errorObject = new JSONObject(error);
//                return new Register(errorObject);

//        return new T(Arrays.asList(exception.getMessage()));
//        this.errors.add(exception.getMessage());
//        LOGGER.error(exception.getMessage());
//        return exception.getMessage();
    }

}
