package org.bank.model;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
/**
 * Transaction is a super class for the two classes TransferTransaction and NonTransferTransaction, annotated with @MappedSuperclass
 * will not create a table in the database but provides functionality and fields to the subclasses
 */
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", columnDefinition = "UNSIGNED")
    private int transactionId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "by_user", referencedColumnName = "id", nullable = false)
    private User byUser;
    @Column(name = "transaction_type", columnDefinition = "UNSIGNED")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private float amount;
    private Date date;

}