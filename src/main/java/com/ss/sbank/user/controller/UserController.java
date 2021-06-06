package com.ss.sbank.user.controller;



import com.ss.sbank.user.dao.UserDao;

import com.ss.sbank.user.entity.ProfileUser;
import com.ss.sbank.user.entity.User;
import com.ss.sbank.user.service.UserService;
import org.apache.coyote.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Collection<User>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.saveOrUpdate(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Username already in use!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{userId}")
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<User> getUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.findOne(userId), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<?> update(@PathVariable Integer userId, @RequestBody ProfileUser user) {
        try {
            User updatedUser = userService.update(userId, user);
            return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<String>("Incorrect Password", HttpStatus.BAD_REQUEST);
        }
    }


}
