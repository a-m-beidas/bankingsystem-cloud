package org.bank.repository;

import org.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {



    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET u.balance = u.balance + ?1 WHERE u.id = ?2", nativeQuery = true)
    void changeBalanceByAmount(float amount, int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET u.token_in_database=ABS(u.token_in_database)+1 WHERE u.username=?1", nativeQuery = true)
    void login(String username);

    /**
     * Sets the token_in_database to a negative value to denote that it has expired
     * @param userId
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET u.token_in_database=ABS(u.token_in_database) * -1, u.token_in_database=u.token_in_database-1 WHERE u.id=?1", nativeQuery = true)
    void logout(int userId);

}
