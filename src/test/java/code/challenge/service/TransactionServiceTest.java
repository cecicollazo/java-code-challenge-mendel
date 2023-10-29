package code.challenge.service;

import code.challenge.model.*;
import code.challenge.repository.TransactionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionsRepository transactionsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTransaction() {
        TransactionRequest request = new TransactionRequest(5000.0, "cars", null);
        when(transactionsRepository.getTransactions()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> {transactionService.createTransaction(10L, request);});
    }

    @Test
    public void testCreateTransactionWithParent() {
        TransactionRequest request = new TransactionRequest(10000.0, "shopping", 10L);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(10L, 5000.0, "cars"));
        when(transactionsRepository.getTransactions()).thenReturn(transactions);
        assertDoesNotThrow(() -> {transactionService.createTransaction(11L, request);});
    }

    @Test
    public void testCreateTransactionWithNonExistingParent() {
        TransactionRequest request = new TransactionRequest(5000.0, "shopping", 11L);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(10L, 5000.0, "cars"));
        when(transactionsRepository.getTransactions()).thenReturn(transactions);
        assertThrows(RuntimeException.class, () -> {transactionService.createTransaction(12L, request);});
    }

    //TODO: Fix this test

    @Test
    public void testGetTransactionsByType() {
        List<Transaction> transactions = new ArrayList<>(List.of(
                new Transaction(10L, 5000.0, "cars"),
                new Transaction(11L, 10000.0, "shopping"),
                new Transaction(12L, 5000.0, "shopping")));
        when(transactionsRepository.getTransactions()).thenReturn(transactions);
        List<Long> result = transactionService.getTransactionsByType("cars");
        assertEquals(1, result.size());
        assertEquals(10L, result.get(0));
    }

    //TODO: Fix this test

    @Test
    public void testGetTransactionSum() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(10L, 5000.0, "cars"));
        transactions.add(new Transaction(11L, 10000.0, "shopping", 10L));
        transactions.add(new Transaction(12L, 5000.0, "shopping", 11L));
        when(transactionsRepository.getTransactions()).thenReturn(transactions);
        Double result = transactionService.getTransactionSum(12L);
        assertEquals(20000.0, result, 0.001);
    }

    @Test
    public void test() {
        assertEquals(true, true);
    }
}
