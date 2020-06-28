package org.bank.repository;

import org.bank.model.transaction.TransactionSuper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;


public interface TransactionRepository extends JpaRepository<TransactionSuper, Integer> {

    /**
     * In order to add a transaction in hibernate one needs the complete persisted object as follows:
     * {@code transactionRepository.save(new Transaction(persistedUser, ... otherFeilds)} <br>
     * fetching the "persistedUser" to save the transaction will reduce performance, another alternative<br>
     * would be to store the User object inside the CustomUserDetails Object but that will take up memory
     * therefore native query was used
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="insert into transfer_transactions (by_user, to_user, transaction_type, amount, date) value (?,?,?,?,?)")
    void addTransferTransaction(int byUser, int toUser, String transactionType, float amount, Date date);

    /**
     * In order to add a transaction in hibernate one needs the complete persisted object as follows:
     * {@code transactionRepository.save(new Transaction(persistedUser, ... otherFeilds)} <br>
     * fetching the "persistedUser" to save the transaction will reduce performance, another alternative<br>
     * would be to store the User object inside the CustomUserDetails Object but that will take up memory
     * therefore native query was used
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="insert into non_transfer_transactions (by_user, transaction_type, amount, date) value (?,?,?,?)")
    void addNonTransferTransaction(int byUser, String transactionType, float amount, java.sql.Date date);
}
