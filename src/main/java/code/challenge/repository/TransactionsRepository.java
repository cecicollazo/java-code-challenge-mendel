package code.challenge.repository;

import code.challenge.model.Transaction;

import java.util.List;

public interface TransactionsRepository {
    List<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactionsByType(String type);
    void getTransactionFamilyMemberById(Long transactionId, List<Transaction> transactionsFamily);
    List<Transaction> getTransactionFamilyById(Long transactionId);
}
