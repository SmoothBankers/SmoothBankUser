package com.ss.sbank.user.service;

import com.ss.sbank.user.dao.UserDao;
import com.ss.sbank.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getGrantedAuthority(user)
        );
    }

    private Collection<GrantedAuthority> getGrantedAuthority(User user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // If user is "ADMIN" it gets the ROLE_ADMIN granted
        if (user.getRole().getRole().equals("ADMIN")) {
            authorities.add( new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        // ADMIN will get ROLE_USER in addition to ROLE_ADMIN
        // USER will get ROLE_USER
        authorities.add( new SimpleGrantedAuthority("ROLE_USER"));

        return authorities;

    }
}
