package com.leonkote.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "operation.processing")
public class OperationProperties
{
	public int sleepMilliSeconds;
}
