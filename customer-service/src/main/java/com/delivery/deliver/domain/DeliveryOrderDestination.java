package com.delivery.deliver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    private String latitude;
    private String longitude;

    @OneToOne(mappedBy = "destination")
    private DeliveryOrder order;
}
