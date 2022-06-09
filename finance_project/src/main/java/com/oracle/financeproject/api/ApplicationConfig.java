package com.oracle.financeproject.api;

import org.glassfish.jersey.server.ResourceConfig;

import com.oracle.financeproject.exceptionhandler.ExceptionHandler;
import com.oracle.financeproject.provider.AuthenticationFilter;

public class ApplicationConfig extends ResourceConfig {
	public ApplicationConfig() {
		register(ExceptionHandler.class);
		register(AuthenticationFilter.class);
	}

}
