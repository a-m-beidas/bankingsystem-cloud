package com.example.testsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_type")
    private String roleType;

    public Role() { }

    public Role(String roleType) {
        this.roleType = roleType;
    }

    public String getRole() {
        return roleType;
    }
}