package com.delivery.deliver.exception;

public class DeliveryOrderStatusException extends IllegalArgumentException {

    private static final long serialVersionUID = -3042686055658047285L;

    public DeliveryOrderStatusException(String reason) {
        super(reason);
    }
}
