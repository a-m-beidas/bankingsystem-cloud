package com.example.testsecurity.controller.model;

import com.example.testsecurity.controller.MainController;
/**
 * Used for mapping Request bodies in {@link MainController}
 * {@link #toUser} is null for the transactions deposit and withdraw
 *
 */
public class TransactionRequestBody {

    private final String toUser;
    private final float amount;

    public TransactionRequestBody() {
        throw new IllegalArgumentException("Constructor must not be used");
    }

    public TransactionRequestBody(String toUser, float amount) {
        this.toUser = toUser;
        this.amount = amount;
    }

    public TransactionRequestBody(float amount) {
        this.amount = amount;
        this.toUser = null;
    }

    public String getToUser() {
        return toUser;
    }

    public float getAmount() {
        return amount;
    }
}
