package com.delivery.user.exception;

public class UnUhtorizedException extends IllegalArgumentException {

    private static final long serialVersionUID = -3042686055658047285L;

    public UnUhtorizedException() {
        super("Username or password failed");
    }
}