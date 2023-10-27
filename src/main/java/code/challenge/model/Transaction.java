package code.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Long transactionId;
    private Double amount;
    private String type;
    private Long parentId;

    public Transaction(Long transactionId, Double amount, String type, Long parentId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
    }

    public Transaction(Long transactionId, Double amount, String type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
    }

}