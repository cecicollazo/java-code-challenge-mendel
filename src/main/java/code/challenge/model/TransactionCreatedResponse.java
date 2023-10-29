package code.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionCreatedResponse {
    private String status;

    public TransactionCreatedResponse(String status) {
        this.status = status;
    }
}
