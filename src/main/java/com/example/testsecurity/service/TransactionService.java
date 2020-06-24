package com.example.testsecurity.service;

import com.example.testsecurity.repository.UserRepository;
import com.example.testsecurity.security.config.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtility tokenUtility;

    /**
     * Finds out the the id of the user and called for the repository to perform the deposit
     * @param amountToDeposit amount to deposit
     * @param authorizationHeader authorizationHeader that is used to find out the id of the user using jwt
     * @return return the id of the user to display in the http response
     * @throws ClassNotFoundException
     */

    public int deposit(float amountToDeposit, String authorizationHeader) throws ClassNotFoundException {
        int userId = getUserIdFromHeader(authorizationHeader);
        return changeBalanceByAmount(amountToDeposit, userId);
    }
    /**
     * see {@link #changeBalanceByAmount(float, int)}
     * @see #changeBalanceByAmount(float, int)
     * @param amountToWithdraw amount
     * @param authorizationHeader authorizationHeader that is used to find out the id of the user using jwt
     * @return the userId that used retrieved to display to the http response
     * @throws ClassNotFoundException
     */

    public int withdraw(float amountToWithdraw, String authorizationHeader) throws ClassNotFoundException {
        int userId = getUserIdFromHeader(authorizationHeader);
        userRepository.changeBalanceByAmount(-1 * amountToWithdraw, userId);
        return userId;
    }

    /**
     * see {@link #changeBalanceByAmount(float, int)}
     * @throws IllegalArgumentException if the user tries to transfer non other than himself
     * @param authorizationHeader authorizationHeader that is used to find out the id of the transferer using jwt
     * @param transfereeId the id of the transferee that the transferer has provided
     * @param amount amount
     * @see #changeBalanceByAmount(float, int)
     * @return the transfererId that used retrieved to display to the http response
     * @throws ClassNotFoundException
     */
    public int transfer(String authorizationHeader, int transfereeId, float amount) throws ClassNotFoundException {
        int transfererId = getUserIdFromHeader(authorizationHeader);
        if (transfereeId == transfererId) {
            throw new IllegalArgumentException("Cannot transfer from and to same account");
        }
        changeBalanceByAmount(amount, transfereeId);
        changeBalanceByAmount(-1 * amount, transfererId);
        return transfererId;
    }

    /**
     * Internal method to actually to the transaction be it deposit or withdraw
     * @param amount amount to do transaction with
     * @param userId userId
     * @return return the id of the user to display in the http response
     * @throws ClassNotFoundException
     */
    private int changeBalanceByAmount(float amount, int userId) throws ClassNotFoundException {
        userRepository.changeBalanceByAmount(amount, userId);
        return userId;
    }


    /**
     * Cuts the authorization request header and gets the token then query the token to JWTs to retrieve the userId
     * @param authorizationHeader the "Authorization" header as recieve from the controller
     * @return the userId retrieved
     * @throws ClassNotFoundException
     */
    private int getUserIdFromHeader(String authorizationHeader) throws ClassNotFoundException {
        String token = authorizationHeader.substring(7);
        return (int) tokenUtility.getUserIdFromToken(token);
    }

}
