package code.challenge.service;

import code.challenge.model.Transaction;
import code.challenge.model.TransactionRequest;
import code.challenge.model.TransactionSumResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    List<Transaction> transactions= new ArrayList<>();

    private void getTransactionFamilyMemberById(Long transactionId, List<Transaction> transactionsFamily) {
        for (Transaction transaction : transactions) {
            if (transaction.getParentId() != null && transaction.getParentId().equals(transactionId)) {
                transactionsFamily.add(transaction);
                getTransactionFamilyMemberById(transaction.getTransactionId(), transactionsFamily);
            }
        }
    }

    private List<Transaction> getTransactionFamilyById(Long transactionId) {
        List<Transaction> transactionsFamily = new ArrayList<>();
        getTransactionFamilyMemberById(transactionId, transactionsFamily);
        return transactionsFamily;
    }

    public void createTransaction(Long transactionId, TransactionRequest transactionRequest) {
        Transaction transaction;
        if(transactionRequest.getParentId() != null) {
            transaction = new Transaction(transactionId, transactionRequest.getAmount(), transactionRequest.getType(), transactionRequest.getParentId());
        } else {
            transaction = new Transaction(transactionId, transactionRequest.getAmount(), transactionRequest.getType());
        }
        transactions.add(transaction);
    }

    public List<Long> getTransactionsByType(String type) {
        List<Long> transactionsByTypeResponse = new ArrayList<>();
        for (Transaction transaction: transactions) {
            if (type.equals(transaction.getType())) {
                transactionsByTypeResponse.add(transaction.getTransactionId());
            }
        }
        return transactionsByTypeResponse;
    }

    public TransactionSumResponse getTransactionSum(Long transactionId) {
        Double transactionsSum = getTransactionFamilyById(transactionId).stream().mapToDouble(Transaction::getAmount).sum();
        return new TransactionSumResponse(transactionsSum);
    }

}
