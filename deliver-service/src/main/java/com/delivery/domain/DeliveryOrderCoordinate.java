package com.delivery.domain;

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
