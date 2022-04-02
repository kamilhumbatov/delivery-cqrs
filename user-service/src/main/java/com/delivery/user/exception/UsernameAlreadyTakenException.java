package com.delivery.user.exception;

public class UsernameAlreadyTakenException extends IllegalArgumentException {

    private static final long serialVersionUID = -3042686055658047285L;

    public UsernameAlreadyTakenException() {
        super("Username already taken");
    }
}