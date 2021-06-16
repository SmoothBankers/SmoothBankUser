package com.ss.sbank.user.service;

import com.ss.sbank.user.dao.UserRoleDao;
import com.ss.sbank.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    UserRoleDao roleDao;

    public Optional<UserRole> findById(Integer id) {
        return roleDao.findById(id);
    }
}
