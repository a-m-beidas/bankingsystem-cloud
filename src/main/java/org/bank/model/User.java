package org.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String username;
    private String password;
    private float balance;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_has_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_type"))
    private List<Role> roles;

    public User() {
    }

    public User(String username, String password, float balance, ArrayList<Role> roles) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.roles = roles;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

