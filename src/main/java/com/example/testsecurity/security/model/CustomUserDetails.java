package com.example.testsecurity.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails extends User {

    private final int id;

    public CustomUserDetails(String username, String password, int userId, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        id = userId;
    }

    public int getId() {
        return id;
    }
}
