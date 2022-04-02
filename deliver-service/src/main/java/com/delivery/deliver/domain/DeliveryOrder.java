package com.delivery.deliver.domain;

import com.delivery.deliver.enums.DeliveryOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
