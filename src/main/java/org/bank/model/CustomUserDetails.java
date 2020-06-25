package org.bank.model;

import org.bank.security.TokenUtility;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

/**
 * Extends {@link User} with an id field
 * works with {@link TokenUtility#generateToken(CustomUserDetails)}
 */
public class CustomUserDetails extends User {

    private final int id;
    private boolean loggedOut;

    /**
     * Calls super {@link User} <br>
     * works with {@link TokenUtility#generateToken(CustomUserDetails)}
     * @param username username
     * @param password password
     * @param userId
     * @param authorities
     */
    public CustomUserDetails(String username, String password, int userId, boolean loggedOut, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        id = userId;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public int getId() {
        return id;
    }
}
