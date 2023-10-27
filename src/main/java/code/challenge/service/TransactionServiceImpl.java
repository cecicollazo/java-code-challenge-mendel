package code.challenge.service;

import code.challenge.model.TransactionRequest;
import code.challenge.model.TransactionSumResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    public void createTransaction(TransactionRequest transactionRequest) {

    }
    public List<Long> getTransactionsByType(String type) {
        return null;
    }
    public TransactionSumResponse getTransactionSum(Long transactionId) {
        return null;
    }

}
