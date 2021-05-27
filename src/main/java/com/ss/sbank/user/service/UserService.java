package com.ss.sbank.user.service;


import com.ss.sbank.user.dao.UserDao;
import com.ss.sbank.user.dao.UserRoleDao;
import com.ss.sbank.user.entity.AuthResponse;
import com.ss.sbank.user.entity.User;
import com.ss.sbank.user.entity.UserRole;
import com.ss.sbank.user.security.JwtTokenProvider;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private UserRoleDao roleDao;

    @Autowired
    JwtTokenProvider tokenProvider;

    public AuthResponse saveOrUpdate(User user) throws DataIntegrityViolationException {
        if (user.getRole() == null) {
            user.setRole(roleDao.findById(2).get());
        }


        var createdUser =  userDao.save(user);
        Collection<UserRole> role = new ArrayList<>();
        role.add(createdUser.getRole());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(createdUser.getUsername());
        authResponse.setAuthorities(role);
        authResponse.setToken(tokenProvider.generateToken(createdUser.getUsername(), roleDao.findById(2).get()));
        return authResponse;
    }

    public Collection<User> findAll() {
        return userDao.findAll();
    }

//    public User createUser(User user) {
//
//    }

}
