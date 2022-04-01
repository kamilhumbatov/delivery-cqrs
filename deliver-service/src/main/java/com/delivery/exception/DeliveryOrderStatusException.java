package com.delivery.exception;

import com.delivery.enums.DeliveryOrderStatus;

public class DeliveryOrderStatusException extends IllegalArgumentException {

    private static final long serialVersionUID = -3042686055658047285L;

    public DeliveryOrderStatusException(DeliveryOrderStatus status) {
        super(String.format("Can not change status to %s.", status.name()));
    }

    public DeliveryOrderStatusException(DeliveryOrderStatus status, String reason) {
        super(String.format("Can not change status to %s. %s", status.name(), reason));
    }
}
