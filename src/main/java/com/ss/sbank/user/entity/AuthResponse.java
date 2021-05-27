package com.ss.sbank.user.entity;

import lombok.Data;
import java.util.Collection;

@Data
public class AuthResponse {


    private String username;

    private Collection<?> authorities;

    private String token;
}
