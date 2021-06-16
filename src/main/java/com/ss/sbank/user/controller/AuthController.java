package com.ss.sbank.user.controller;

import com.ss.sbank.user.dao.UserDao;
import com.ss.sbank.user.entity.AuthRequest;
import com.ss.sbank.user.entity.AuthResponse;
import com.ss.sbank.user.entity.User;
import com.ss.sbank.user.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private UserDao userDao;


    @PostMapping(value= "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            User user = userDao.findByUsername(authRequest.getUsername());
            AuthResponse authResponse = new AuthResponse(
                    user.getId(),
                    authentication.getName(),
                    authentication.getAuthorities(),
                    tokenProvider.generateToken(user.getId(), authRequest.getUsername(), userDao.findByUsername(authRequest.getUsername()).getRole())
            );
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Error: Username " + authRequest.getUsername() + " does not exist", HttpStatus.BAD_REQUEST);
        }

    }
}
