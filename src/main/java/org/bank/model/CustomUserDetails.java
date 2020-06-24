package com.example.testsecurity.model;

import com.example.testsecurity.security.TokenUtility;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

/**
 * Extends {@link User} with an id field
 * works with {@link TokenUtility#generateToken(CustomUserDetails)}
 */
public class CustomUserDetails extends User {

    private final int id;

    /**
     * Calls super {@link User} <br>
     * works with {@link TokenUtility#generateToken(CustomUserDetails)}
     * @param username username
     * @param password password
     * @param userId
     * @param authorities
     */
    public CustomUserDetails(String username, String password, int userId, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        id = userId;
    }

    public int getId() {
        return id;
    }
}
