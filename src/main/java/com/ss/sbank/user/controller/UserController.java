package com.ss.sbank.user.controller;



import com.ss.sbank.user.dao.UserDao;

import com.ss.sbank.user.entity.User;
import com.ss.sbank.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Collection<User>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


}
