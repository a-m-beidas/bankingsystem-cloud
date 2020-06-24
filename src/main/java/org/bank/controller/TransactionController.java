package org.bank.controller;

import org.bank.model.TransactionRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {


    @RequestMapping(path = "/transactions/deposit", method = RequestMethod.POST)
    public ResponseEntity<String> deposit(@RequestHeader("Authorization") String authorizationHeader, @RequestBody TransactionRequestBody requestBody) throws ClassNotFoundException {
        assertEqualZero(requestBody.getToUser());
        float amount = requestBody.getAmount();
        checkAmountIsPositive(amount);
        int userId = transactionService.deposit(amount, authorizationHeader);
        return new ResponseEntity<String>("Deposit to user " + userId + " of amount " + amount, HttpStatus.OK);
    }

    @RequestMapping(path = "/transactions/withdraw", method = RequestMethod.POST)
    public ResponseEntity<String> withdraw(@RequestHeader("Authorization") String authorizationHeader, @RequestBody TransactionRequestBody requestBody) throws ClassNotFoundException {
        assertEqualZero(requestBody.getToUser());
        float amount = requestBody.getAmount();
        checkAmountIsPositive(amount);
        int userId = transactionService.withdraw(amount, authorizationHeader);
        return new ResponseEntity<String>("Withdraw to user " + userId + " of amount " + amount, HttpStatus.OK);
    }

    @RequestMapping(path = "/transactions/transfer", method = RequestMethod.POST)
    public ResponseEntity<String> transfer(@RequestHeader("Authorization") String authorizationHeader, @RequestBody TransactionRequestBody requestBody) throws ClassNotFoundException {
        int transfereeId = requestBody.getToUser();
        assertNotEqualZero(transfereeId);
        float amount = requestBody.getAmount();
        checkAmountIsPositive(amount);
        int transfererId = transactionService.transfer(authorizationHeader, transfereeId, amount);
        return new ResponseEntity<String>("Transfer from user with id " + transfererId + " to user with id " + transfereeId, HttpStatus.OK);
    }
}
