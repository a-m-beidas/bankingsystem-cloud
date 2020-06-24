package org.bank.controller;

import org.bank.model.TransferTransactionRequestDetails;
import org.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {


    @Autowired
    TransactionService transactionService;

    @RequestMapping(path = "/deposit", method = RequestMethod.POST)
    public ResponseEntity<String> deposit(@RequestHeader("Authorization") String authorizationHeader, @RequestBody float amount) throws ClassNotFoundException {
        int userId = transactionService.deposit(amount, authorizationHeader);
        return new ResponseEntity<String>("Deposit to user " + userId + " of amount " + amount, HttpStatus.OK);
    }

    @RequestMapping(path = "/withdraw", method = RequestMethod.POST)
    public ResponseEntity<String> withdraw(@RequestHeader("Authorization") String authorizationHeader, @RequestBody float amount) throws ClassNotFoundException {
        int userId = transactionService.withdraw(amount, authorizationHeader);
        return new ResponseEntity<String>("Withdraw to user " + userId + " of amount " + amount, HttpStatus.OK);
    }

    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public ResponseEntity<String> transfer(@RequestHeader("Authorization") String authorizationHeader, @RequestBody TransferTransactionRequestDetails requestBody) throws ClassNotFoundException {
        validateTransferRequestDetails(requestBody);
        int transfererId = transactionService.transfer(authorizationHeader, requestBody.getTransfereeId(), requestBody.getAmount());
        return new ResponseEntity<String>("Transfer from user with id " + transfererId + " to user with id " + requestBody.getTransfereeId(), HttpStatus.OK);
    }

    private void validateTransferRequestDetails(TransferTransactionRequestDetails requestBody) {
        if (requestBody.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid Amount");
        }
        if (requestBody.getTransfereeId() <= 0) {
            throw new IllegalArgumentException("Invalid Transferee Id");
        }
    }
}
