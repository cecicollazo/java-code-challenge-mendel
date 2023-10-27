package code.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionSumResponse {
    private Double sum;

    public TransactionSumResponse(Double sum) {
        this.sum = sum;
    }
}
