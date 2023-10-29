package code.challenge.controller;

import code.challenge.model.*;
import code.challenge.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

@SpringBootTest
public class TransactionControllerTest {

	@InjectMocks
	private TransactionController transactionController;

	@Mock
	private TransactionService transactionService;

	@BeforeEach
	public void setUp() { MockitoAnnotations.initMocks(this); }

	@Test
	public void testCreateTransaction() {
		TransactionRequest transactionRequest = new TransactionRequest(5000.0, "cars", null);
		ResponseEntity<TransactionCreatedResponse> response = transactionController.createTransaction(transactionRequest, 10L);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals("ok", response.getBody().getStatus());
	}

	@Test
	public void testCreateTransactionWithParentId() {
		TransactionRequest transactionRequest = new TransactionRequest(10000.0,"shopping", 10L);
		ResponseEntity<TransactionCreatedResponse> response = transactionController.createTransaction(transactionRequest, 11L);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals("ok", response.getBody().getStatus());
	}

	@Test
	public void testCreateTransactionWithInvalidParent() {
		TransactionRequest request = new TransactionRequest(10000.0, "shopping", 11L);
		doThrow(new RuntimeException("Parent Id 11 does not exist.")).when(transactionService).createTransaction(11L, request);
		ResponseEntity<TransactionCreatedResponse> response = transactionController.createTransaction(request, 11L);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Parent Id 11 does not exist.", response.getBody().getStatus());
		verify(transactionService, times(1)).createTransaction(any(Long.class), any(TransactionRequest.class));
	}

	@Test
	public void testGetTransactionsByType() {
		when(transactionService.getTransactionsByType("cars")).thenReturn(List.of(10L));
		List<Long> response = transactionController.getTransactionsByType("cars");
		assertEquals(List.of(10L), response);
		verify(transactionService, times(1)).getTransactionsByType(any(String.class));
	}

	@Test
	public void testGetTransactionsSum() {
		when(transactionService.getTransactionSum(11L)).thenReturn(15000.0);
		TransactionSumResponse response = transactionController.getTransactionSum(11L);
		assertEquals(15000.0, response.getSum());
		verify(transactionService, times(1)).getTransactionSum(any(Long.class));
	}
}

