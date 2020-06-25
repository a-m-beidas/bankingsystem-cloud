package org.bank.repository;

import org.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="insert into transfer_transactions (by_user, to_user, transaction_type, amount, date) value (?,?,?,?,?)")
    void addTransferTransaction(int byUser, int toUser, String transactionType, float amount, Date date);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="insert into non_transfer_transactions (by_user, transaction_type, amount, date) value (?,?,?,?)")
    void addNonTransferTransaction(int byUser, String transactionType, float amount, java.sql.Date date);
}
