package com.ss.sbank.user.service;


import com.ss.sbank.user.dao.UserDao;
import com.ss.sbank.user.dao.UserRoleDao;
import com.ss.sbank.user.entity.AuthResponse;
import com.ss.sbank.user.entity.ProfileUser;
import com.ss.sbank.user.entity.User;
import com.ss.sbank.user.entity.UserRole;
import com.ss.sbank.user.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private UserRoleDao roleDao;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse saveOrUpdate(User user) throws DataIntegrityViolationException {
        if (user.getRole() == null) {
            user.setRole(roleDao.findById(2).get());
        }

        System.out.println(user);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        User createdUser =  userDao.save(user);
        Collection<UserRole> role = new ArrayList<>();
        role.add(createdUser.getRole());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(createdUser.getId());
        authResponse.setUsername(createdUser.getUsername());
        authResponse.setAuthorities(role);
        authResponse.setToken(tokenProvider.generateToken(createdUser.getId(), createdUser.getUsername(), roleDao.findById(2).get()));
        return authResponse;
    }

    public Collection<User> findAll() {
        return userDao.findAll();
    }

    public User findOne(Integer userId) {
        User user =  userDao.findById(userId).orElseThrow();
        user.setPassword(null);
        return user;
    }

    public User update(Integer userId, ProfileUser user) throws Exception {
        User currentUser = userDao.findById(userId).orElseThrow();
        if (user.getEmail() != null) {

            currentUser.setEmail(user.getEmail());
            User updatedUser = userDao.save(currentUser);
            return updatedUser;
        }
        else if (user.getPassword() != null) {
            boolean isPasswordMatch = passwordEncoder.matches(user.getPassword(), currentUser.getPassword());
            if (isPasswordMatch) {

                currentUser.setPassword(passwordEncoder.encode(user.getNewPassword()));
                User updatedUser = userDao.save(currentUser);
                return updatedUser;
            }
            else {
                throw new Exception();
            }


        }
        return null;
    }

}
