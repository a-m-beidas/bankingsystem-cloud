package org.bank.repository;

import org.bank.model.NonTransferTransaction;
import org.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
