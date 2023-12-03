package com.leonkote.service;

import com.leonkote.OperationHistoryApiApplicationTest;
import com.leonkote.domain.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementServiceTest extends OperationHistoryApiApplicationTest
{
	@Autowired
	private StatementService statementService;

	@Test
	public void getOperationsTest()
	{
		int count = statementService.getOperations(0).size();

		assertEquals(count, statementService.getOperations(0).size());

		List<Operation> operations = statementService.getOperations(0);
		Operation operation1 = operations.get(0);
		Operation operation2 = operations.get(1);

		assertEquals(0, operation1.getId());
		assertEquals(0, operation1.getCustomerId());
		assertEquals(100, operation1.getSum());
		assertEquals("RUB", operation1.getCurrency());
		assertEquals("SAGA", operation1.getMerchant());
		assertEquals(1, operation2.getId());
		assertEquals(0, operation2.getCustomerId());
		assertEquals(1000, operation2.getSum());
		assertEquals("EUR", operation2.getCurrency());
		assertEquals("LETA", operation2.getMerchant());

		assertEquals(count, statementService.getOperations(0).size());
	}

	@Test
	public void saveOperationTest()
	{
		int count1 = statementService.getOperations(0).size();
		int count2 = statementService.getOperations(1).size();

		assertEquals(count1, statementService.getOperations(0).size());
		assertEquals(count2, statementService.getOperations(1).size());

		statementService.saveOperation(new Operation(-1, 0, 500, "USD", "LETHAL"));
		statementService.saveOperation(new Operation(-1, 1, 550, "USD", "COMPANY"));

		assertEquals(count1 + 1, statementService.getOperations(0).size());
		assertEquals(count2 + 1, statementService.getOperations(1).size());
	}

	@Test
	public void deleteOperationTest()
	{
		statementService.saveOperation(new Operation(-1, 1, 500, "EUR", "CHIKIBAMBONI"));

		int count1 = statementService.getOperations(0).size();
		int count2 = statementService.getOperations(1).size();

		assertEquals(count1, statementService.getOperations(0).size());
		assertEquals(count2, statementService.getOperations(1).size());

		statementService.deleteOperation(0, 1);
		statementService.deleteOperation(1, 0);

		assertEquals(count1 - 1, statementService.getOperations(0).size());
		assertEquals(count2 - 1, statementService.getOperations(1).size());
	}
}