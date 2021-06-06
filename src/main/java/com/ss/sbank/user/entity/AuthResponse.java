package com.ss.sbank.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    @NotNull
    private Integer userId;

    @NotNull
    private String username;

    @NotNull
    private Collection<?> authorities;

    @NotNull
    private String token;
}
