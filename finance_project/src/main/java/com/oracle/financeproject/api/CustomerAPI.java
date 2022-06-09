package com.oracle.financeproject.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.oracle.financeproject.entity.Customer;
import com.oracle.financeproject.entity.LoanApplication;
import com.oracle.financeproject.entity.Payment;
import com.oracle.financeproject.entity.SanctionLoan;
import com.oracle.financeproject.exception.ApplicationException;
import com.oracle.financeproject.repository.CustomerDAO;
import com.oracle.financeproject.repository.iml.ClerkDAO_IMPL;
import com.oracle.financeproject.repository.iml.CustomerDAO_IMPL;
import com.oracle.financeproject.service.CustomerService;
import com.oracle.financeproject.service.impl.CustomerServiceIMPL;

@Path("/customer")
public class CustomerAPI {
	private final static Logger log=Logger.getLogger(CustomerAPI.class.getName());

	@PermitAll
	@POST
	@Path("/register")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public int addCustomer(Customer c) {
		CustomerService serv=new CustomerServiceIMPL();
		log.info("Registering Customer");
		int res=serv.setCustomerDetails(c);
		return res;
	}
	
	@PermitAll
	@GET
	@Path("/calculateEmi")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public double CalculateCustEmi(@QueryParam("principle") double principle, @QueryParam("roi") double roi, @QueryParam("tenure") int tenure)
	{
		CustomerService serv=new CustomerServiceIMPL();
		log.info("Calculating EMI");
		double emi= serv.calculateEmi(principle, roi, tenure);
		return emi;
		
	}
	

	@RolesAllowed("CUSTOMER")
	@GET
	@Path("/{customerId}/details")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getCustByCustId(@PathParam("customerId") int customerId) {
		CustomerService serv=new CustomerServiceIMPL();	
		log.info("Customer "+customerId+" Logging in");
		 Customer c=serv.getCustomerDetailsByCustomerId(customerId);
		if(c.getCustomerFirstName()!="0") {
			return Response.status(200).entity(c).header("contentType", "application/json").build();
		}
		else {
			//return Response.status(500);
			throw new ApplicationException("The customer "+customerId+" is not found");
		}
	}
	
	@RolesAllowed("CUSTOMER")
	@GET
	@Path("/{customerId}/viewApplication")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response viewApplicationbyCustId(@PathParam("customerId") int customerId) {
		CustomerService serv=new CustomerServiceIMPL();	
		log.info("Viewing loan applications of customer "+customerId);
		List<LoanApplication> l=serv.viewApplicationBycustId(customerId);
		if(l.isEmpty())
			throw new ApplicationException("No data found");
		return Response.status(200).entity(l).header("contentType", "application/json").build();
	}
	
	@RolesAllowed("CUSTOMER")
	@GET
	@Path("/{customerId}/viewLoan")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response viewLoanbyCustId(@PathParam("customerId") int customerId) {
		CustomerService serv=new CustomerServiceIMPL();	
		log.info("Viewing loans of customer "+ customerId);
		List<SanctionLoan> l=serv.viewLoanBycustId(customerId);
		if(l.isEmpty())
			throw new ApplicationException("No data found");
		return Response.status(200).entity(l).header("contentType", "application/json").build();
		//return l;
	}
	
	@RolesAllowed("CUSTOMER")
	@GET
	@Path("/{customerId}/viewApplication/{loanApplicationNo}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public LoanApplication viewApplication(@PathParam("loanApplicationNo") int loanApplicationNo ) {
		CustomerService serv=new CustomerServiceIMPL();	
		LoanApplication l=serv.viewLoanApplication(loanApplicationNo);
		return l;
	}
	
	@RolesAllowed("CUSTOMER")
	@POST
	@Path("/{customerId}/loanApplication")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLoanApplication(LoanApplication l) {
		CustomerService serv=new CustomerServiceIMPL();
		log.info("Adding a new loan application");
		int rowNum=serv.applyForLoan(l);
		if(rowNum>0)
		   return Response.status(200).entity("Added successfully").header("contentType", "application/json").build();
		else
			return Response.status(404).entity("Application not added").header("contentType", "application/json").build();
	}
	
	@RolesAllowed("CUSTOMER")
	@DELETE
	@Path("/{custId}/deleteLoanApplication/{loanApplicationNo}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteLoanApplication(@PathParam("loanApplicationNo") int loanAppNo,@PathParam("custId")int custId) {
		CustomerService serv=new CustomerServiceIMPL();
		log.info("Deleting loan application "+loanAppNo);
		Status s=serv.customerdeleteApplication(custId, loanAppNo);
		return Response.status(s).build();
	}
	
	@RolesAllowed("CUSTOMER")
	@POST
	@Path("/{custId}/repayment")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RepayLoan(@PathParam("loanAccNo") int loanAccNo,Payment p) {
		System.out.println("test loan repayment");
		log.info("Paying loan");
		CustomerService serv=new CustomerServiceIMPL();
		p.setDateOfTransaction(LocalDate.now());
		int status=serv.loanRepayment(p);
		if(status==0)
			throw new ApplicationException("Not found");
		return Response.status(200).build();
	}
	
	
	@RolesAllowed("CUSTOMER")
	@GET
	@Path("/{custId}/balance/{loanAccountNo}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response viewBalance(@PathParam("custId") int custId,@PathParam("loanAccountNo") int loanAccountNo) {
		CustomerService serv=new CustomerServiceIMPL();
		log.info("Viewing balance loan amount ");
		double balance=serv.getBalance(custId, loanAccountNo);
		if(balance<0) {
			throw new ApplicationException("No data found");
		}
		return Response.status(200).entity(balance).header("contentType", "application/json").build();

	}

}
