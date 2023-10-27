package code.challenge.controller;

import code.challenge.model.TransactionRequest;
import code.challenge.model.TransactionSumResponse;
import code.challenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
    private TransactionService transactionService;

    @PutMapping("/{transactionId}")
    public ResponseEntity<Void> createTransaction(
            @RequestBody TransactionRequest transactionRequest,
            @PathVariable Long transactionId) {
        transactionService.createTransaction(transactionRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/types/{type}")
    public List<Long> getTransactionsByType(
            @PathVariable String type) {
        return transactionService.getTransactionsByType(type);
    }

    @GetMapping("/sum/{transactionId}")
    public TransactionSumResponse getTransactionSum(
            @PathVariable Long transactionId) {
        return transactionService.getTransactionSum(transactionId);
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}