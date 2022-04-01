package com.delivery.user.exception;

public class UserNotFoundException extends IllegalArgumentException {

    private static final long serialVersionUID = -3042686055658047285L;

    public UserNotFoundException() {
        super("User not found");
    }
}
