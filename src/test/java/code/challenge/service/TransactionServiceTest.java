package code.challenge.service;

import code.challenge.model.Transaction;
import code.challenge.model.TransactionRequest;
import code.challenge.repository.TransactionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @Mock
    private TransactionsRepository transactionsRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTransactionWithoutParent() {
        TransactionRequest request = new TransactionRequest(5000.0, "cars", null);
        when(transactionsRepository.getTransactions()).thenReturn(new ArrayList<>());
        transactionService.createTransaction(10L, request);
        verify(transactionsRepository, times(1)).addTransaction(any(Transaction.class));
    }

    @Test
    public void testCreateTransactionWithParent() {
        Transaction parent = new Transaction(10L, 5000.0, "cars", null);
        TransactionRequest request = new TransactionRequest(10000.0, "shopping", 10L);
        when(transactionsRepository.getTransactions()).thenReturn(new ArrayList<>(List.of(parent)));
        transactionService.createTransaction(11L, request);
        verify(transactionsRepository, times(1)).addTransaction(any(Transaction.class));
    }

    @Test
    public void testCreateTransactionWithNonExistentParent() {
        TransactionRequest request = new TransactionRequest(5000.0, "shopping", 13L);
        when(transactionsRepository.getTransactions()).thenReturn(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> { transactionService.createTransaction(12L, request); });
        verify(transactionsRepository, never()).addTransaction(any(Transaction.class));
    }

    @Test
    public void testGetTransactionsByTypeWithNoMatchingTransactions() {
        when(transactionsRepository.getTransactionsByType("nonExistentType")).thenReturn(new ArrayList<>());
        List<Long> result = transactionService.getTransactionsByType("nonExistentType");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetTransactionsByType() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(10L, 5000.0, "cars", null));
        when(transactionsRepository.getTransactionsByType("cars")).thenReturn(transactions);
        List<Long> result = transactionService.getTransactionsByType("cars");
        assertTrue(result.contains(10L));
    }

    @Test
    public void testGetTransactionSumWithNoFamilyTransactions() {
        when(transactionsRepository.getTransactionFamilyById(1L)).thenReturn(new ArrayList<>());
        double result = transactionService.getTransactionSum(1L);
        assertEquals(0.0, result);
    }

    @Test
    public void testGetTransactionSumWithFamilyTransactions() {
        List<Transaction> family = new ArrayList<>();
        family.add(new Transaction(10L, 5000.0, "cars", null));
        family.add(new Transaction(11L, 10000.0, "shopping", 10L));
        when(transactionsRepository.getTransactionFamilyById(11L)).thenReturn(family);
        double result = transactionService.getTransactionSum(11L);
        assertEquals(15000.0, result);
    }
}
