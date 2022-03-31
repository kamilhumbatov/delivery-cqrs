package com.delivery.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class DeliveryOrderCoordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    private String latitude;
    private String longitude;

    @ManyToOne
    private DeliveryOrder order;
}
