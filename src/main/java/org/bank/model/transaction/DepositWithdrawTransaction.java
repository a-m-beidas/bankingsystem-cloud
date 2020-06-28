package org.bank.model.transaction;

import org.bank.model.User;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "deposit_withdraw_transactions")
/**
 * Represents the table non_transfer_transactions that stores transfer transactions, extends {@link TransactionSuper}
 */
public class DepositWithdrawTransaction extends TransactionSuper {

    public DepositWithdrawTransaction(User user, TransactionType transactionType, float amount, Date date) {
        super(user, transactionType, amount, date);
    }

}
