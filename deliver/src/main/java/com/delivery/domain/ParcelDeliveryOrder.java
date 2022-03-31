package com.delivery.domain;

import com.delivery.enums.ParcelDeliveryOrderStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class ParcelDeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    private String owner;

    private String assignee;

    @Enumerated(EnumType.STRING)
    private ParcelDeliveryOrderStatus status;
}
