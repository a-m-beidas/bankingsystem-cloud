package com.example.testsecurity.repository;

import com.example.testsecurity.model.Users;
import com.example.testsecurity.security.config.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Integer> {



    Users findByUsername(String username);

    @Query(value = "UPDATE users u SET u.balance = u.balance + ?1: WHERE u.user_id = ?2", nativeQuery = true)
    void changeBalanceByAmount(float amount, int userId);
}
