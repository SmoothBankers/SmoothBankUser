package com.ss.sbank.user.dao;

import com.ss.sbank.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
}
