package org.bank.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "non_transfer_transactions")
public class NonTransferTransaction extends Transaction {

    public NonTransferTransaction(User user, TransactionType transactionType, float amount, Date date) {
        super(user, transactionType, amount, date);
    }

}
