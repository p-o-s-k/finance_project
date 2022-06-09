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

import com.oracle.financeproject.api.CustomerAPI;
import com.oracle.financeproject.entity.Customer;
import com.oracle.financeproject.entity.LoanApplication;
import com.oracle.financeproject.exceptionhandler.ExceptionHandler;

public class CustomerAPITest extends JerseyTest {
	
	@Override
	protected Application configure() {
		return new ResourceConfig(CustomerAPI.class,ExceptionHandler.class);
	}
	
	
	@Test
	public void testCustomerbyCustIdPositive() {
		String url="/customer/101/details";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}
	
	@Test
	public void testCustomerbyCustIdNegative() {
		String url="/customer/100/details";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 404: ", Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}

	@Test
	public void testViewApplicationPositive() {
		String url="/customer/105/viewApplication";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}
	
	@Test
	public void testViewApplicationNegative() {
		String url="/customer/100/viewApplication";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
		assertEquals("Http Response should be 404: ", Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}
	
	

}
