package org.bank.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "INT UNSIGNED")
    private int id;
    private String username;
    private String password;
    private float balance;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_has_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_type"))
    private List<Role> roles;
    @Column(name = "token_in_database")
    /**
     * If negative this means that the token has expired, if positive then it has not
     */
    private int databaseToken;

    public User() {
    }

    public User(String username, String password, float balance, ArrayList<Role> roles) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.roles = roles;
    }

    public int getDatabaseToken() {
        return databaseToken;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

