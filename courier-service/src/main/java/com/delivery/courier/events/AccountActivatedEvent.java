package com.delivery.courier.events;


import com.delivery.courier.aggregates.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountActivatedEvent {

    private String id;
    private Status status;

}
