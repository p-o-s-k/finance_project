package com.oracle.financeproject.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.log4j.Logger;

import com.oracle.financeproject.api.ManagerAPI;
import com.oracle.financeproject.exception.ApplicationException;

public class ExceptionHandler implements ExceptionMapper<ApplicationException> {
	private final static Logger log=Logger.getLogger(ExceptionHandler.class.getName());

	public Response toResponse(ApplicationException e) {
		log.error(e.getMessage());
		String jsonText="{ \"msg\":\" "+e.getMessage()+"\"}";
		return Response.status(404).entity(jsonText).build();
	}
}
