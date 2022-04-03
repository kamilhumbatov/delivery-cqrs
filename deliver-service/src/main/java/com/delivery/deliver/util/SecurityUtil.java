package com.delivery.deliver.util;

import com.delivery.CurrentUserService;
import com.delivery.deliver.domain.DeliveryOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final CurrentUserService currentUserService;

    public void checkUserAccess(DeliveryOrder deliveryOrder) {
        if (!deliveryOrder.getAssignee().equals(currentUserService.getCurrentUser())) {
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
