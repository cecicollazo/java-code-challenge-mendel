package code.challenge.controller;

import code.challenge.exception.TransactionAlreadyExistsException;
import code.challenge.exception.TransactionNotFoundException;
import code.challenge.model.TransactionCreatedResponse;
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
    public ResponseEntity<TransactionCreatedResponse> createTransaction(
            @RequestBody TransactionRequest transactionRequest,
            @PathVariable Long transactionId) {
        try { transactionService.createTransaction(transactionId, transactionRequest); }
        catch (TransactionNotFoundException e) { return new ResponseEntity<TransactionCreatedResponse>(new TransactionCreatedResponse(e.getMessage()), HttpStatus.BAD_REQUEST); }
        catch (TransactionAlreadyExistsException e) { return new ResponseEntity<TransactionCreatedResponse>(new TransactionCreatedResponse(e.getMessage()), HttpStatus.CONFLICT); }
        return new ResponseEntity<TransactionCreatedResponse>(new TransactionCreatedResponse("ok"), HttpStatus.OK);
    }

    @GetMapping("/types/{type}")
    public List<Long> getTransactionsByType(
            @PathVariable String type) {
        return transactionService.getTransactionsByType(type);
    }

    @GetMapping("/sum/{transactionId}")
    public TransactionSumResponse getTransactionSum(
            @PathVariable Long transactionId) {
    return new TransactionSumResponse(transactionService.getTransactionSum(transactionId)) ;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
