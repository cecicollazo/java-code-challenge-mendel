package code.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
    private Double amount;
    private String type;
    private Long parentId;

    public TransactionRequest(Double amount, String type, Long parentId) {
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
    }
}
