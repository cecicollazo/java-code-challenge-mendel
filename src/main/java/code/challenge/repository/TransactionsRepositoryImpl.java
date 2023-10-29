package code.challenge.repository;

import code.challenge.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionsRepositoryImpl implements TransactionsRepository {
    List<Transaction> transactions = new ArrayList<>(List.of(
            new Transaction(1L, 1000.0, "cars", null),
            new Transaction(2L, 1500.0, "shopping", 1L),
            new Transaction(3L, 500.0, "entertainment", 2L),
            new Transaction(4L, 2000.0, "shopping", 3L),
            new Transaction(5L, 800.0, "cars", 1L),
            new Transaction(6L, 1200.0, "cars", null),
            new Transaction(7L, 700.0, "shopping", 6L),
            new Transaction(8L, 300.0, "entertainment", 3L),
            new Transaction(9L, 1000.0, "cars", 6L),
            new Transaction(13L, 600.0, "shopping", null)
    ));

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactionsByType(String type) {
        List<Transaction> transactionsByType = new ArrayList<>();
        for (Transaction transaction: transactions) {
            if (type.equals(transaction.getType())) {
                transactionsByType.add(transaction);
            }
        }
        return transactionsByType;
    }

    public void getTransactionFamilyMemberById(Long transactionId, List<Transaction> transactionsFamily) {
        for (Transaction transaction : transactions) {
            if (transaction.getParentId() != null && transaction.getParentId().equals(transactionId)) {
                transactionsFamily.add(transaction);
                getTransactionFamilyMemberById(transaction.getTransactionId(), transactionsFamily);
            }
        }
    }

    public List<Transaction> getTransactionFamilyById(Long transactionId) {
        List<Transaction> transactionsFamily = new ArrayList<>();
        getTransactionFamilyMemberById(transactionId, transactionsFamily);
        return transactionsFamily;
    }

}
