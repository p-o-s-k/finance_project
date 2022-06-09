package com.oracle.financeproject.service;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.oracle.financeproject.entity.Customer;
import com.oracle.financeproject.entity.LoanApplication;
import com.oracle.financeproject.entity.Payment;
import com.oracle.financeproject.entity.SanctionLoan;

public interface CustomerService {
    public int setCustomerDetails(Customer c1);
	
	public Customer getCustomerDetailsByCustomerId(int customerId);
		
	public List<LoanApplication> viewApplicationBycustId(int customerId);
	
	public boolean customerAuth(String uname,String password);
	
	public int applyForLoan(LoanApplication l);
	
	public LoanApplication viewLoanApplication(int LoanApplicationNo);

	double calculateEmi(double principle, double roi, int tenure);

	Status customerdeleteApplication(int custId, int applicationno);

	int loanRepayment(Payment p);

	List<SanctionLoan> viewLoanBycustId(int customerId);
	
	double getBalance(int custId, int loanAccountNo);

}
