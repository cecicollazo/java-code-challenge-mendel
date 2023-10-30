package code.challenge.service;

import code.challenge.exception.TransactionAlreadyExistsException;
import code.challenge.exception.TransactionNotFoundException;
import code.challenge.model.Transaction;
import code.challenge.model.TransactionRequest;
import code.challenge.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionsRepository transactionsRepository;

    private List<Long> getListTransactionsIds(List<Transaction> transactions) {
        return transactions.stream().map(Transaction::getTransactionId).toList();
    }

    private Boolean transactionIdExists(Long transactionId) {
        return getListTransactionsIds(transactionsRepository.getTransactions()).contains(transactionId);
    }

    private void checkTransactionIdExist(Long transactionId) {
        if (!transactionIdExists(transactionId)) {throw new TransactionAlreadyExistsException(transactionId); }
    }

    public void createTransaction(Long transactionId, TransactionRequest transactionRequest) {
        Transaction transaction;
        checkTransactionIdExist(transactionId);
        if(transactionRequest.getParentId() != null) {
            if (!transactionIdExists(transactionRequest.getParentId())) { throw new TransactionNotFoundException(transactionRequest.getParentId()); }
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
        checkTransactionIdExist(transactionId);
        return transactionsRepository.getTransactionFamilyById(transactionId).stream().mapToDouble(Transaction::getAmount).sum();
    }

    @Autowired
    public void setTransactionsRepository(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

}
