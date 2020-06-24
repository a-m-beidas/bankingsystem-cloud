package org.bank.controller;

import com.fasterxml.jackson.databind.JsonNode;
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
    public ResponseEntity<String> deposit(@RequestHeader("Authorization") String authorizationHeader, @RequestBody JsonNode requestBody) throws ClassNotFoundException {
        String result = transactionService.deposit(requestBody, authorizationHeader);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/withdraw", method = RequestMethod.POST)
    public ResponseEntity<String> withdraw(@RequestHeader("Authorization") String authorizationHeader, @RequestBody JsonNode requestBody) throws ClassNotFoundException {
        String result = transactionService.withdraw(requestBody, authorizationHeader);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public ResponseEntity<String> transfer(@RequestHeader("Authorization") String authorizationHeader, @RequestBody TransferTransactionRequestDetails requestBody) throws ClassNotFoundException {
        String result = transactionService.transfer(authorizationHeader, requestBody);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }


}
