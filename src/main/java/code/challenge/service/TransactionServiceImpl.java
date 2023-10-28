package code.challenge.service;

import code.challenge.model.Transaction;
import code.challenge.model.TransactionRequest;
import code.challenge.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionsRepository transactionsRepository;

    private List<Long> getListTransactionsIds(List<Transaction> transactions) {
        return transactions.stream().map(Transaction::getTransactionId).toList();
    }

    private Boolean parentExists(Long parentId) {
        return getListTransactionsIds(transactionsRepository.getTransactions()).contains(parentId);
    }

    public void createTransaction(Long transactionId, TransactionRequest transactionRequest) {
        Transaction transaction;
        if(transactionRequest.getParentId() != null) {
            if (!parentExists(transactionRequest.getParentId())) { throw new RuntimeException("Parent Id " + transactionRequest.getParentId() + " does not exist.");}
            transaction = new Transaction(transactionId, transactionRequest.getAmount(), transactionRequest.getType(), transactionRequest.getParentId());
        } else {
            transaction = new Transaction(transactionId, transactionRequest.getAmount(), transactionRequest.getType());
        }
        transactionsRepository.addTransaction(transaction);
    }

    public List<Long> getTransactionsByType(String type) {
        return getListTransactionsIds(transactionsRepository.getTransactionsByType(type));
    }

    public Double getTransactionSum(Long transactionId) {
        return transactionsRepository.getTransactionFamilyById(transactionId).stream().mapToDouble(Transaction::getAmount).sum();
    }

    @Autowired
    public void setTransactionsRepository(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

}
