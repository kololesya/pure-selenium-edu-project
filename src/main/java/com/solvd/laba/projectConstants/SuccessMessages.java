package com.solvd.laba.projectConstants;

public enum SuccessMessages {
    CONTACT_FORM_SUBMITTED("Success! Your details have been submitted successfully."),
    ACCOUNT_CREATED("Account Created!"),
    ORDER_PLACED("Your order has been placed successfully!");

    private final String text;

    SuccessMessages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
