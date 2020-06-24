package com.example.testsecurity.repository;

import com.example.testsecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    JpaRepository jparepository = null;
}
