package com.delivery.deliver.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinationChangedEvent {
    private String orderId;
    private String latitude;
    private String longitude;
}
