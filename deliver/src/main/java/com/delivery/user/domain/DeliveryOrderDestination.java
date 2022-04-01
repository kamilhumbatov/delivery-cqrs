package com.delivery.user.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class DeliveryOrderDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    private String latitude;
    private String longitude;

    @OneToOne(mappedBy = "destination")
    private DeliveryOrder order;

    @ManyToOne
    private DeliveryOrderCoordinate lastLocation;
}
