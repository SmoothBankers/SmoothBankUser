package com.ss.sbank.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserRole {

    @Id
    private Integer id;

    @Column(name = "role")
    private String role;
}
