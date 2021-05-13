package com.ss.sbank.user.service;


import com.ss.sbank.user.dao.UserDao;
import com.ss.sbank.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User saveOrUpdate(User user) {
        return userDao.saveAndFlush(user);
    }

    public Collection<User> findAll() {
        return userDao.findAll();
    }

}
