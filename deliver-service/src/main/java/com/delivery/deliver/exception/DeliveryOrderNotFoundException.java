package com.delivery.deliver.exception;

public class DeliveryOrderNotFoundException extends IllegalArgumentException {

    private static final long serialVersionUID = -3042686055658047285L;

    public DeliveryOrderNotFoundException(String id) {
        super(String.format("Invalid DeliveryOrder id %s", id));
    }
}
