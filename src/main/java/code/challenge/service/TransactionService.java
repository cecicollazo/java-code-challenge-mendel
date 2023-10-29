package code.challenge.service;

import code.challenge.model.TransactionRequest;

import java.util.List;

public interface TransactionService {

    Boolean transactionIdExists(Long transactionId);
    void createTransaction(Long transactionId, TransactionRequest transactionRequest);
    List<Long> getTransactionsByType(String type);
    Double getTransactionSum(Long transactionId);
}
