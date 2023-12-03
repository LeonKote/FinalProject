package com.leonkote.controller;

import com.leonkote.controller.dto.OperationDTO;
import com.leonkote.controller.dto.OperationsGetResponse;
import com.leonkote.domain.Operation;
import com.leonkote.service.AsyncInputOperationService;
import com.leonkote.service.StatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/operations")
@RequiredArgsConstructor
public class OperationsController
{
	private final AsyncInputOperationService operationService;
	private final StatementService statementService;

	@GetMapping("{customerId}")
	public OperationsGetResponse getOperations(@PathVariable(name = "customerId") int customerId)
	{
		List<Operation> operations = statementService.getOperations(customerId);
		List<OperationDTO> operationDTOS = operations.stream()
				.map(operation -> new OperationDTO(operation.getId(), operation.getCustomerId(), operation.getSum(), operation.getCurrency(), operation.getMerchant()))
				.collect(Collectors.toList());
		return new OperationsGetResponse(operationDTOS);
	}

	@PostMapping("{customerId}")
	public void addOperation(@PathVariable(name = "customerId") int customerId, @RequestBody OperationDTO operationDTO)
	{
		operationService.addOperation(new Operation(-1, customerId, operationDTO.getSum(), operationDTO.getCurrency(), operationDTO.getMerchant()));
	}

	@DeleteMapping("{customerId}/{id}")
	public void deleteOperation(@PathVariable(name = "customerId") int customerId, @PathVariable(name = "id") int id)
	{
		statementService.deleteOperation(customerId, id);
	}
}
