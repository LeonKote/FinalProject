package com.leonkote.service;

import com.leonkote.configuration.OperationProperties;
import com.leonkote.domain.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;

@Component
@RequiredArgsConstructor
public class AsyncInputOperationService
{
	private Queue<Operation> operations;

	private final StatementService statementService;
	private final OperationProperties operationProperties;

	public boolean addOperation(Operation operation)
	{
		System.out.println("Operation added for processing " + operation);
		return operations.offer(operation);
	}

	public void startProcessing()
	{
		Thread t = new Thread(() ->
		{
			processQueue();
		});
		t.start();
	}

	private void processQueue()
	{
		while (true)
		{
			Operation operation = operations.poll();
			if (operation == null)
			{
				try
				{
					System.out.println("No operations");
					Thread.sleep(operationProperties.getSleepMilliSeconds());
				}
				catch (InterruptedException e)
				{
					System.out.println(e);
				}
				//TODO wait next operation
			}
			else
			{
				System.out.println("Processing operation " + operation);
				processOperation(operation);
			}
		}
	}

	private void processOperation(Operation operation)
	{
		statementService.saveOperation(operation);
	}

	public Queue<Operation> getQueue()
	{
		return operations;
	}

	public void init()
	{
		operations = new LinkedList<>();
	}

	@PostConstruct
	public void initPost()
	{
		this.init();
		this.startProcessing();
	}
}
