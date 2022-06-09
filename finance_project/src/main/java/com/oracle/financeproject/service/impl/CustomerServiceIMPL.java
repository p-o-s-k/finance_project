package com.oracle.financeproject.service.impl;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.oracle.financeproject.entity.Customer;
import com.oracle.financeproject.entity.LoanApplication;
import com.oracle.financeproject.entity.Payment;
import com.oracle.financeproject.entity.SanctionLoan;
import com.oracle.financeproject.repository.CustomerDAO;
import com.oracle.financeproject.repository.iml.CustomerDAO_IMPL;
import com.oracle.financeproject.service.CustomerService;

public class CustomerServiceIMPL implements CustomerService{

	@Override
	public int setCustomerDetails(Customer c1) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.setCustomerDetails(c1);
	}

	@Override
	public Customer getCustomerDetailsByCustomerId(int customerId) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.getCustomerDetailsByCustomerId(customerId);
	}

	@Override
	public List<LoanApplication> viewApplicationBycustId(int customerId) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.viewApplicationBycustId(customerId);
	}

	@Override
	public boolean customerAuth(String uname, String password) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.customerAuth(uname, password);
	}

	@Override
	public int applyForLoan(LoanApplication l) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.applyForLoan(l);
	}

	@Override
	public LoanApplication viewLoanApplication(int LoanApplicationNo) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.viewLoanApplication(LoanApplicationNo);
	}

	@Override
	public double calculateEmi(double principle, double roi, int tenure) {
		double r=(roi/12)/100;
		double emi = (principle * r * Math.pow(1 + r,tenure))/(Math.pow(1 + r,tenure) - 1); 
		return emi;
	}

	@Override
	public Status customerdeleteApplication(int custId, int applicationno) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.customerdeleteApplication(custId, applicationno);
	}

	@Override
	public int loanRepayment(Payment p) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.loanRepayment(p);
	}

	@Override
	public List<SanctionLoan> viewLoanBycustId(int customerId) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.viewLoanBycustId(customerId);
	}

	@Override
	public double getBalance(int custId, int loanAccountNo) {
		CustomerDAO dao=new CustomerDAO_IMPL();
		return dao.getBalance(custId, loanAccountNo);
	}

}
