package com.leonkote.service;

import com.leonkote.domain.Operation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatementService
{
	private Map<Integer, Map<Integer, Operation>> storage;
	private Map<Integer, Integer> nextIds;

	public void saveOperation(Operation operation)
	{
		int customerId = operation.getCustomerId();
		Map<Integer, Operation> operations = storage.get(customerId);
		if (operations == null)
		{
			operation.setId(0);
			Map<Integer, Operation> customerOperations = new HashMap<>();
			customerOperations.put(0, operation);
			incNextId(customerId);
			storage.put(operation.getCustomerId(), customerOperations);
		}
		else
		{
			int nextId = getNextId(customerId);
			operation.setId(nextId);
			operations.put(nextId, operation);
			incNextId(customerId);
		}
	}

	private int getNextId(int customerId)
	{
		return nextIds.get(customerId);
	}

	private void incNextId(int customerId)
	{
		nextIds.put(customerId, nextIds.getOrDefault(customerId, 0) + 1);
	}

	public List<Operation> getOperations(int customerId)
	{
		return new ArrayList<>(storage.getOrDefault(customerId, new HashMap<>()).values());
	}

	public void deleteOperation(int customerId, int id)
	{
		storage.get(customerId).remove(id);
	}

	@PostConstruct
	public void init()
	{
		storage = new HashMap<>();
		nextIds = new HashMap<>();

		saveOperation(new Operation(0, 0, 100, "RUB", "SAGA"));
		saveOperation(new Operation(1, 0, 1000, "EUR", "LETA"));
	}
}
