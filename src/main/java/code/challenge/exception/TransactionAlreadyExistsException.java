package code.challenge.exception;

public class TransactionAlreadyExistsException extends RuntimeException {
    public TransactionAlreadyExistsException(Long transactionId) {
        super("Transaction Id " + transactionId + " already exists.");
    }
}

