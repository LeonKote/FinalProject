package com.leonkote.controller.dto;

import lombok.Data;

@Data
public class OperationDTO
{
	private final int id;
	private final int customerId;
	private final int sum;
	private final String currency;
	private final String merchant;
}
