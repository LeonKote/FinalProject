package com.leonkote.service;

import com.leonkote.OperationHistoryApiApplicationTest;
import com.leonkote.domain.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest
{
	@Autowired
	private AsyncInputOperationService operationService;

	@Test
	public void addOperationTest()
	{
		// Private field, how to test?
		//
		// assertEquals(0, operationService.operations.size());
		//
		operationService.addOperation(new Operation(-1, 0, 500, "USD", "LETHAL"));
		//
		// assertEquals(1, operationService.operations.size());
		//
		operationService.addOperation(new Operation(-1, 1, 550, "USD", "COMPANY"));
		//
		// assertEquals(2, operationService.operations.size());
	}
}
