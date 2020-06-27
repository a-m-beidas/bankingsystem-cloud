package org.bank.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "non_transfer_transactions")
/**
 * Represents the table non_transfer_transactions that stores transfer transactions, extends {@link Transaction}
 */
public class NonTransferTransaction extends Transaction {

    public NonTransferTransaction(User user, TransactionType transactionType, float amount, Date date) {
        super(user, transactionType, amount, date);
    }

}
