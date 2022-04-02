package com.delivery.deliver.domain;

import com.delivery.deliver.enums.DeliveryOrderStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class DeliveryOrder {

    @Id
    @Basic(optional = false)
    private String id;

    private String owner;

    private String assignee;

    @Enumerated(EnumType.STRING)
    private DeliveryOrderStatus status;

    @OneToOne
    @JoinColumn(name = "DESTINATION_ID")
    private DeliveryOrderDestination destination;
}
