package com.oracle.financeproject.api.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.oracle.financeproject.api.ClerkAPI;
import com.oracle.financeproject.exceptionhandler.ExceptionHandler;

public class ClerkAPITest extends JerseyTest{
	@Override
	protected Application configure() {
		return new ResourceConfig(ClerkAPI.class,ExceptionHandler.class);
	}
	
	@Test
	public void testSearchByLoanIdPositive() {
		String url="/clerk/901/searchByLoanId/301";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}
	
	@Test
	public void testSearchByLoanTypePositive() {
		String url="/clerk/901/searchByLoanType/Home";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}
	
	@Test
	public void testSearchByDatePositive() {
		String url="/clerk/901/searchByDate/2021-08-27";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}
	
	
	@Test
	public void testSearchByLoanIdNegative() {
		String url="/clerk/901/searchByLoanId/300";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 404: ", Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}
	
	@Test
	public void testSearchByLoanTypeNegative() {
		String url="/clerk/901/searchByLoanType/Hom";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 404: ", Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}
	
	@Test
	public void testSearchByDateNegative() {
		String url="/clerk/901/searchByDate/2021-08-26";
		Response response=target(url).request().accept(MediaType.APPLICATION_JSON).get();
     	assertEquals("Http Response should be 404: ", Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}
}
