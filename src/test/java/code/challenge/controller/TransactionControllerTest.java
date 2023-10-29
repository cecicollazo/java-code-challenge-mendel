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
import static org.mockito.Mockito.when;
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
	public void testtransactionId10() {
		TransactionRequest transactionRequest = new TransactionRequest(5000.0, "cars", null);
		ResponseEntity<?> response = transactionController.createTransaction(transactionRequest, 10L);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testtransactionId11() {
		TransactionRequest transactionRequest = new TransactionRequest(10000.0,"shopping", 10L);
		ResponseEntity<?> response = transactionController.createTransaction(transactionRequest, 11L);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testtransactionId12() {
		TransactionRequest transactionRequest = new TransactionRequest(5000.0, "shopping", 11L);
		ResponseEntity<?> response = transactionController.createTransaction(transactionRequest, 12L);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testtransactionTypeCars() {
		when(transactionService.getTransactionsByType("cars")).thenReturn(List.of(10L));
		List<Long> response = transactionController.getTransactionsByType("cars");
		assertEquals(List.of(10L), response);
	}

	@Test
	public void testsumTransactionsId10() {
		when(transactionService.getTransactionSum(10L)).thenReturn(10000.0);
		TransactionSumResponse response = transactionController.getTransactionSum(10L);
		assertEquals(10000.0, response.getSum());
	}

	@Test
	public void testsumTransactionsId11() {
		when(transactionService.getTransactionSum(11L)).thenReturn(15000.0);
		TransactionSumResponse response = transactionController.getTransactionSum(11L);
		assertEquals(15000.0, response.getSum());
	}
}
