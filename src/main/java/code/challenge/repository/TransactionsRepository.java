package code.challenge.repository;

import code.challenge.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository {
    List<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
    Transaction getTransactionById(Long transactionId);
    List<Transaction> getTransactionsByType(String type);
    void getTransactionFamilyMemberById(Long transactionId, List<Transaction> transactionsFamily);
    List<Transaction> getTransactionFamilyById(Long transactionId);
}
