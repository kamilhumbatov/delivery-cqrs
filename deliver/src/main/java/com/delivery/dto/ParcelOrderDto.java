package com.delivery.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table
public class ParcelOrderDto {

    private Long id;

    private String assignee;
}
