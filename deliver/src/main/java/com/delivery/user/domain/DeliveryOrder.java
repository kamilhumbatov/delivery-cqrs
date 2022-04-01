package com.delivery.user.domain;

import com.delivery.enums.DeliveryOrderStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    private String owner;

    private String assignee;

    @Enumerated(EnumType.STRING)
    private DeliveryOrderStatus status;

    @OneToOne
    @JoinColumn(name="DESTINATION_ID")
    private DeliveryOrderDestination destination;
}
