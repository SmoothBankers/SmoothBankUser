package com.ss.sbank.user.dao;

import com.ss.sbank.user.entity.User;
import com.ss.sbank.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    List<User> findByRole(UserRole role);
}
