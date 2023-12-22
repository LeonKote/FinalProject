package com.leonkote.controller;

import com.leonkote.OperationHistoryApiApplicationTest;
import com.leonkote.controller.dto.OperationDTO;
import com.leonkote.controller.dto.OperationsGetResponse;
import com.leonkote.domain.Operation;
import com.leonkote.service.AsyncInputOperationService;
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
	@Autowired
	private AsyncInputOperationService operationService;

	@Test
	public void getOperationsTest()
	{
		statementService.init();

		assertEquals(2, operationsController.getOperations(0).getOperations().size());

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

		assertEquals(2, operationsController.getOperations(0).getOperations().size());
	}

	@Test
	public void addOperationTest()
	{
		operationService.init();

		assertEquals(0, operationService.getQueue().size());

		operationsController.addOperation(0, new OperationDTO(-1, 0, 500, "USD", "LETHAL"));

		assertEquals(1, operationService.getQueue().size());

		operationsController.addOperation(1, new OperationDTO(-1, 1, 550, "USD", "COMPANY"));

		assertEquals(2, operationService.getQueue().size());
	}

	@Test
	public void deleteOperationTest()
	{
		statementService.init();

		statementService.saveOperation(new Operation(-1, 1, 500, "EUR", "CHIKIBAMBONI"));

		assertEquals(2, operationsController.getOperations(0).getOperations().size());
		assertEquals(1, operationsController.getOperations(1).getOperations().size());

		operationsController.deleteOperation(0, 1);
		operationsController.deleteOperation(1, 0);

		assertEquals(1, operationsController.getOperations(0).getOperations().size());
		assertEquals(0, operationsController.getOperations(1).getOperations().size());
	}
}
