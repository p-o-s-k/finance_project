package com.oracle.financeproject.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.oracle.financeproject.entity.Clerk;
import com.oracle.financeproject.entity.LoanApplication;
import com.oracle.financeproject.entity.Manager;
import com.oracle.financeproject.exception.ApplicationException;
import com.oracle.financeproject.repository.ManagerDAO;
import com.oracle.financeproject.repository.iml.ClerkDAO_IMPL;
import com.oracle.financeproject.repository.iml.ManagerDAO_IMPL;
import com.oracle.financeproject.repository.iml.SmtpMail;
import com.oracle.financeproject.service.ManagerService;
import com.oracle.financeproject.service.impl.ManagerServiceIMPL;

@Path("/Manager")
public class ManagerAPI {
	private final static Logger log=Logger.getLogger(ManagerAPI.class.getName());

	
	@RolesAllowed("MANAGER")
	@GET
	@Path("/{managerId}/getDetails")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getManagerInfo(@PathParam("managerId") int managerId) {
		ManagerService serv=new ManagerServiceIMPL();	
		log.info("Manager "+managerId+" logging in");
		Manager m=serv.getManagerDetails(managerId);
		if(m==null)
			throw new ApplicationException("No data found");
		else
		 return Response.status(200).entity(m).header("contentType", "application/json").build();

	}

	@RolesAllowed("MANAGER")
	@GET
	@Path("/view/allLoanApplications")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response viewApplication()
	{
		ManagerService serv=new ManagerServiceIMPL();	
		log.info("View all applications");
		List<LoanApplication> l=serv.viewAllLoanApplications();
		if(l.isEmpty())
			throw new ApplicationException("No data found");
		return Response.status(200).entity(l).header("contentType", "application/json").build();
	}
	
	//approve loan application
	@RolesAllowed("MANAGER")
	@GET
	@Path("/view/allLoanApplications/{loanApplicationNumber}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response ApproveLoanApplication(@PathParam("loanApplicationNumber")int loanApplicationNumber, @QueryParam("approvalResponse") String approvalResponse)
	{
		ManagerService serv=new ManagerServiceIMPL();	
		log.info("Updating status of "+loanApplicationNumber);
		Status resp =serv.ApproveLoanApplication( loanApplicationNumber , approvalResponse);
		return Response.status(resp).build();
	}

}
