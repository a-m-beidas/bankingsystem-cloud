package com.example.testsecurity.repository;

import com.example.testsecurity.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    JpaRepository jparepository = null;
}
