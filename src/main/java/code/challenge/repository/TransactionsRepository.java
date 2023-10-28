package code.challenge.repository;

import code.challenge.model.Transaction;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class TransactionsRepository {
    List<Transaction> transactions= new ArrayList<>();

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
