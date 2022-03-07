package org.bank.model;

import org.bank.controller.AuthenticationController;
/**
 * Used for mapping Request bodies in {@link AuthenticationController}
 *
 */
public class TransferTransactionRequestDetails {

    private final int transfereeId;
    private final float amount;

    public TransferTransactionRequestDetails(int transfereeId, float amount) {
        this.transfereeId = transfereeId;
        this.amount = amount;
    }

    public int getTransfereeId() {
        return transfereeId;
    }

    public float getAmount() {
        return amount;
    }
}
