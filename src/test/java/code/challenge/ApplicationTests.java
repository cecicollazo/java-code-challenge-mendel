package code.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import java.util.List;

@SpringBootTest
public class ApplicationTests {

	@Autowired
	private TransactionService transactionService;

	@MockBean
	private TransactionRepository transactionRepository;

	@Test
	public void transactionId10() {
		String bodyRq = "{ \"amount\": 5000, \"type\": \"cars\" }";
		when(transactionRepository.save(any())).thenReturn(new Transaction(10, 5000, "cars", null));
		TransactionResponse response = transactionService.createTransaction(bodyRq);
		assertEquals("ok", response.getStatus());
	}

	@Test
	public void transactionId11() {
		String bodyRq = "{ \"amount\": 10000, \"type\": \"shopping\", \"parent_id\": 10 }";
		when(transactionRepository.save(any())).thenReturn(new Transaction(11, 10000, "shopping", 10));
		TransactionResponse response = transactionService.createTransaction(bodyRq);
		assertEquals("ok", response.getStatus());
	}

	@Test
	public void transactionId12() {
		String bodyRq = "{ \"amount\": 5000, \"type\": \"shopping\", \"parent_id\": 11 }";
		when(transactionRepository.save(any())).thenReturn(new Transaction(12, 10000, "shopping", 10));
		TransactionResponse response = transactionService.createTransaction(bodyRq);
		assertEquals("ok", response.getStatus());
	}

	@Test
	public void transactionTypeCars() {
		when(transactionRepository.findByType("cars")).thenReturn(List.of(new Transaction(10, 5000, "cars", null)));
		List<Integer> transactionIds = transactionService.getTransactionsByType("cars");
		assertEquals(List.of(10), transactionIds);
	}

	@Test
	public void sumTransactionsId10() {
		when(transactionRepository.findByParentId(10)).thenReturn(List.of(new Transaction(11, 10000, "shopping", 10)));
		TransactionSumResponse sumResponse = transactionService.getTransactionSum(10);
		assertEquals(20000, sumResponse.getSum());
	}

	@Test
	public void sumTransactionsId11() {
		when(transactionRepository.findByParentId(11)).thenReturn(List.of(new Transaction(12, 5000, "shopping", 11)));
		TransactionSumResponse sumResponse = transactionService.getTransactionSum(11);
		assertEquals(15000, sumResponse.getSum());
	}
}

