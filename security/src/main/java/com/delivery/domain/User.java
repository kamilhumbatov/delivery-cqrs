package com.delivery.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table
public class User {

    @Column(unique = true, updatable = false)
    private String username;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Role role;
}
