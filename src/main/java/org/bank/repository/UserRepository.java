package org.bank.repository;

import org.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {



    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET u.balance = u.balance + ?1 WHERE u.id = ?2", nativeQuery = true)
    void changeBalanceByAmount(float amount, int userId);

    @Modifying
    @Query(value = "UPDATE users u SET u.logged_out = TRUE WHERE u.id = ?1", nativeQuery = true)
    void logOut(int userId);
}
