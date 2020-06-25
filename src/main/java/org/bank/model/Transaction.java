package org.bank.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class Transaction {

    protected Transaction(User byUser, TransactionType transactionType, float amount, Date date) {
        this.byUser = byUser;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAW, TRANSFER
    }

    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private int transactionId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "by_user", referencedColumnName = "id")
    private User byUser;
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private float amount;
    private Date date;

}