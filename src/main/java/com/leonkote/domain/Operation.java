package com.leonkote.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Operation
{
	private int id;
	private int customerId;
	private int sum;
	private String currency;
	private String merchant;
}
