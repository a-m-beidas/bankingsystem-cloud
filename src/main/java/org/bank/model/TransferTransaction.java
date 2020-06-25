package org.bank.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transfer_transactions")
public class TransferTransaction extends Transaction {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user", referencedColumnName = "id")
    private User toUser;

    public TransferTransaction(User byUser, User toUser, TransactionType transactionType, float amount, Date date) {
        super(toUser, transactionType, amount, date);
        this.toUser = toUser;
    }

}