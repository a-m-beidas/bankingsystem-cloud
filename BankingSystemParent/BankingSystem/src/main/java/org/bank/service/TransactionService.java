package org.bank.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.bank.model.TransferTransactionRequestDetails;
import org.bank.repository.TransactionRepository;
import org.bank.repository.UserRepository;
import org.bank.security.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TokenUtility tokenUtility;

    /**
     * Finds out the the id of the user and called for the repository to perform the deposit
     * @param requestBody amount as JsonNode
     * @param authorizationHeader authorizationHeader that is used to find out the id of the user using jwt
     * @return return the id of the user to display in the http response
     * @throws ClassNotFoundException
     */

    public String deposit(JsonNode requestBody, String authorizationHeader) throws ClassNotFoundException {
        float amountToDeposit = requestBody.get("amount").floatValue();
        validateAmount(amountToDeposit);
        int userId = tokenUtility.getUserIdFromHeader(authorizationHeader);
        changeBalanceByAmount(amountToDeposit, userId);
        transactionRepository.addNonTransferTransaction(userId, "DEPOSIT", amountToDeposit, new java.sql.Date(new Date().getTime()));
//        transactionRepository.save(new NonTransferTransaction(user, Transaction.TransactionType.DEPOSIT, amountToDeposit, new Date()));
        return "Deposit to user " + userId + " of amount " + amountToDeposit;
    }
    /**
     * see {@link #changeBalanceByAmount(float, int)}
     * @see #changeBalanceByAmount(float, int)
     * @param requestBody amount as JsonNode
     * @param authorizationHeader authorizationHeader that is used to find out the id of the user using jwt
     * @return the userId that used retrieved to display to the http response
     * @throws ClassNotFoundException
     */

    public String withdraw(JsonNode requestBody, String authorizationHeader) throws ClassNotFoundException {
        float amountToWithdraw = requestBody.get("amount").floatValue();
        validateAmount(amountToWithdraw);
        int userId = tokenUtility.getUserIdFromHeader(authorizationHeader);
        changeBalanceByAmount(-1 * amountToWithdraw, userId);
        transactionRepository.addNonTransferTransaction(userId, "WITHDRAW", amountToWithdraw, new java.sql.Date(new Date().getTime()));
//        transactionRepository.save(new NonTransferTransaction(user, Transaction.TransactionType.WITHDRAW, amountToWithdraw, new Date()));
        return "Withdraw to user " + userId + " of amount " + amountToWithdraw;
    }

    /**
     * see {@link #changeBalanceByAmount(float, int)}
     * @throws IllegalArgumentException if the user tries to transfer non other than himself
     * @param authorizationHeader authorizationHeader that is used to find out the id of the transferer using jwt
     * @param requestBody Contains the transfereeId and the amount to transfer
     * @see #changeBalanceByAmount(float, int)
     * @return the transfererId that used retrieved to display to the http response
     * @throws ClassNotFoundException
     */
    public String transfer(String authorizationHeader, TransferTransactionRequestDetails requestBody) throws ClassNotFoundException {
        validateTransferRequestDetails(requestBody);
        int transfererId = tokenUtility.getUserIdFromHeader(authorizationHeader);
        int transfereeId = requestBody.getTransfereeId();
        float amount  = requestBody.getAmount();
        if (transfereeId == transfererId) {
            throw new IllegalArgumentException("Cannot transfer from and to same account");
        }
        changeBalanceByAmount(amount, transfereeId);
        changeBalanceByAmount(-1 * amount, transfererId);
//        transactionRepository.save(new TransferTransaction(byUser, toUser, Transaction.TransactionType.TRANSFER,amount, new Date()));
        transactionRepository.addTransferTransaction(transfererId, transfereeId, "TRANSFER", amount, new Date());
        return "Transfer from user with id " + transfererId + " to user with id " + requestBody.getTransfereeId();
    }

    /**
     * Internal method to actually to the transaction be it deposit or withdraw
     * @param amount amount to do transaction with
     * @param userId userId
     * @return return the id of the user to display in the http response
     * @throws ClassNotFoundException
     */
    @Transactional
    private void changeBalanceByAmount(float amount, int userId) throws ClassNotFoundException {
        userRepository.changeBalanceByAmount(amount, userId);
    }

    private void validateAmount(float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Amount");
        }
    }

    private void validateTransferRequestDetails(TransferTransactionRequestDetails requestBody) {
        validateAmount(requestBody.getAmount());
        if (requestBody.getTransfereeId() <= 0) {
            throw new IllegalArgumentException("Invalid Transferee Id");
        }
    }
}
