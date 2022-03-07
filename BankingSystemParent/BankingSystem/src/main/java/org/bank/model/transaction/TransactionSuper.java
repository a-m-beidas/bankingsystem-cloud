package org.bank.model.transaction;

import org.bank.model.User;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
/**
 * Transaction is a super class for the two classes TransferTransaction and NonTransferTransaction, annotated with @MappedSuperclass
 * will not create a table in the database but provides functionality and fields to the subclasses
 */
public class TransactionSuper {



    public enum TransactionType {
        DEPOSIT, WITHDRAW, TRANSFER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", columnDefinition = "INT UNSIGNED")
    private int transactionId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "by_user", referencedColumnName = "id", nullable = false)
    private User byUser;
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column(columnDefinition = "INT UNSIGNED")
    private float amount;
    private Date date;

    protected TransactionSuper(User byUser, TransactionType transactionType, float amount, Date date) {
        this.byUser = byUser;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

}