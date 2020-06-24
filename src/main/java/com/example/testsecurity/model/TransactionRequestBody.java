package com.example.testsecurity.model;

import com.example.testsecurity.controller.MainController;
/**
 * Used for mapping Request bodies in {@link MainController}
 * {@link #toUser} is null for the transactions deposit and withdraw
 *
 */
public class TransactionRequestBody {

    private final int toUser;
    private final float amount;


    public TransactionRequestBody(int toUser, float amount) {
        //TODO throw custom exception
        this.toUser = toUser;
        this.amount = amount;
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be a positive decimal");
        }
    }

    public int getToUser() {
        return toUser;
    }

    public float getAmount() {
        return amount;
    }
}
