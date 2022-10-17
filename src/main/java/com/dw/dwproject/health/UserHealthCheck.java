package com.dw.dwproject.health;

import java.util.Objects;

import com.codahale.metrics.health.HealthCheck;
import com.dw.dwproject.core.UserService;

public class UserHealthCheck extends HealthCheck{
	private static final String HEALTHY = "The user service is healthy for read and write data";
	private static final String UNHEALTHY = "The user service is not healthy";
	private static final String MESSAGE_PLACEHOLDER = "{}";
	private final UserService userService;
	
	public UserHealthCheck(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected Result check() throws Exception {
		String mysqlHealthStatus = this.userService.performHealthCheck();
		return Objects.isNull(mysqlHealthStatus) ? Result.healthy(HEALTHY) : Result.unhealthy(UNHEALTHY + MESSAGE_PLACEHOLDER, mysqlHealthStatus);
	}

}
