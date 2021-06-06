package com.ss.sbank.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;



@Data
public class ProfileUser {

    private Integer id;

    private String username;

    private String password;

    private String newPassword;

    private String email;


}
