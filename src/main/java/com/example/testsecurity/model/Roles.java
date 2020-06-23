package com.example.testsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Roles {
    @Id
    @Column(name = "role_type")
    private String roleType;

    public Roles() { }

    public Roles(String roleType) {
        this.roleType = roleType;
    }

    public String getRole() {
        return roleType;
    }
}