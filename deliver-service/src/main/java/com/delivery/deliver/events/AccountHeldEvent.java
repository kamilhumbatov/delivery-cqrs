package com.delivery.deliver.events;


import com.delivery.deliver.aggregates.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountHeldEvent {

    private String id;

    private Status status;

}
