package com.oracle.financeproject.repository;

import java.time.LocalDate;
import java.util.List;

import com.oracle.financeproject.entity.Clerk;
import com.oracle.financeproject.entity.Customer;
import com.oracle.financeproject.entity.LoanApplication;

public interface ClerkDAO {	
	public List<LoanApplication> getAllLoanApplications();

	public boolean clerkAuth(String uname,String password);
	
	public List<LoanApplication> searchByLoanType(int clerkId,String loanType);
	
	public List<LoanApplication> searchByDate(int clerkId,LocalDate applicationDate);

	public int addNewCustomer(Customer c);
	
	public int applyForLoan(int clerkId,LoanApplication l);
	
	public Clerk getClerkDetails(int clerkId);

	List<LoanApplication> searchByLoanId(int clerkId, int loanId);
}
