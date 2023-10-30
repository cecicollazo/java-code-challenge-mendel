package code.challenge.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Long parentId) {
        super("Transaction Id " + parentId + " does not exist.");
    }
}

