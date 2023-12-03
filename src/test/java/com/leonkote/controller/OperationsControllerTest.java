package com.leonkote.controller;

import com.leonkote.OperationHistoryApiApplicationTest;
import com.leonkote.controller.dto.OperationDTO;
import com.leonkote.controller.dto.OperationsGetResponse;
import com.leonkote.domain.Operation;
import com.leonkote.service.StatementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationsControllerTest extends OperationHistoryApiApplicationTest
{
	@Autowired
	private OperationsController operationsController;
	@Autowired
	private StatementService statementService;

	@Test
	public void getOperationsTest()
	{
		int count = operationsController.getOperations(0).getOperations().size();

		assertEquals(count, operationsController.getOperations(0).getOperations().size());

		OperationsGetResponse operations = operationsController.getOperations(0);
		OperationDTO operation1 = operations.getOperations().get(0);
		OperationDTO operation2 = operations.getOperations().get(1);

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

		assertEquals(count, operationsController.getOperations(0).getOperations().size());
	}

	@Test
	public void addOperationTest()
	{
		// Private field, how to test?
		//
		// assertEquals(0, operationService.operations.size());
		//
		operationsController.addOperation(0, new OperationDTO(-1, 0, 500, "USD", "LETHAL"));
		//
		// assertEquals(1, operationService.operations.size());
		//
		operationsController.addOperation(1, new OperationDTO(-1, 1, 550, "USD", "COMPANY"));
		//
		// assertEquals(2, operationService.operations.size());
	}

	@Test
	public void deleteOperationTest()
	{
		statementService.saveOperation(new Operation(-1, 1, 500, "EUR", "CHIKIBAMBONI"));

		int count1 = operationsController.getOperations(0).getOperations().size();
		int count2 = operationsController.getOperations(1).getOperations().size();

		assertEquals(count1, operationsController.getOperations(0).getOperations().size());
		assertEquals(count2, operationsController.getOperations(1).getOperations().size());

		operationsController.deleteOperation(0, 1);
		operationsController.deleteOperation(1, 0);

		assertEquals(count1 - 1, operationsController.getOperations(0).getOperations().size());
		assertEquals(count2 - 1, operationsController.getOperations(1).getOperations().size());
	}
}
