package com.delivery.domain;

import com.delivery.enums.ParcelOrderStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class ParcelOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    private String assignee;

    @Enumerated(EnumType.STRING)
    private ParcelOrderStatus status;
}
