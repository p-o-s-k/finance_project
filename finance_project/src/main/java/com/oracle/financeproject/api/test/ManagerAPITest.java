package com.oracle.financeproject.api.test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import static org.junit.Assert.assertEquals;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.oracle.financeproject.api.ManagerAPI;
import com.oracle.financeproject.exceptionhandler.ExceptionHandler;

public class ManagerAPITest extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(ManagerAPI.class,ExceptionHandler.class);
	}
	
	@Test
	public void testManagerfetchPositive() {
		String url="/Manager/201/getDetails";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}	
	
	@Test
	public void testManagerfetchNegative() {
		String url="/Manager/205/getDetails";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}	
	
	
	@Test
	public void testViewAllLoansPositive() {
		String url="/Manager/view/allLoanApplications";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}	
	
	@Test
	public void testViewAllLoansNegative() {
		String url="/Manager/view/allLoanApplication";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 404: ", Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}	
	
	
}
