package com.ss.sbank.user.controller;

import com.ss.sbank.user.security.JwtTokenProvider;
import com.ss.sbank.user.dao.UserDao;
import com.ss.sbank.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/users")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private UserDao userDao;


    @PostMapping(value= "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        var json = new HashMap<String, Object>();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            json.put("user", authentication.getName());
            json.put("authorities", authentication.getAuthorities());
            json.put("token", tokenProvider.generateToken(user.getUsername(), userDao.findUserByUsername(user.getUsername()).getRole()));
            return new ResponseEntity<>(json, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Error: Username " + user.getUsername() + " does not exist", HttpStatus.BAD_REQUEST);
        }

    }
}
