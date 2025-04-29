package com.solvd.laba.projectConstants;

public enum ErrorMessages {
    EMAIL_ALREADY_EXISTS("Email Address already exist!"),
    INVALID_LOGIN("Your email or password is incorrect!");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getText() {
        return message;
    }
}
