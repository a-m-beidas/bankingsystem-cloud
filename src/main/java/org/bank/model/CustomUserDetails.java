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
    private boolean currentTokenExpired;
    private final int databaseToken;

    /**
     * Calls super {@link User} <br>
     * works with {@link TokenUtility#generateToken(CustomUserDetails)}
     * @param username username
     * @param password password
     * @param userId
     * @param authorities
     */
    public CustomUserDetails(String username, String password, int userId, boolean currentTokenExpired, int databaseToken, Collection<? extends GrantedAuthority> authorities) {
        //TODO use the fields inside the superclass
        super(username, password, authorities);
        id = userId;
        this.currentTokenExpired = currentTokenExpired;
        this.databaseToken = databaseToken;
    }

    public boolean isCurrentTokenExpired() {
        return currentTokenExpired;
    }

    public int getDatabaseToken() {
        return databaseToken;
    }

    public int getId() {
        return id;
    }
}
