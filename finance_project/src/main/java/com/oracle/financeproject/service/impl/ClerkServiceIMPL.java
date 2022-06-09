package com.oracle.financeproject.service.impl;
import java.time.LocalDate;
import java.util.List;

import com.oracle.financeproject.entity.Clerk;
import com.oracle.financeproject.entity.Customer;
import com.oracle.financeproject.entity.LoanApplication;
import com.oracle.financeproject.repository.ClerkDAO;
import com.oracle.financeproject.repository.iml.ClerkDAO_IMPL;
import com.oracle.financeproject.service.ClerkService;

public class ClerkServiceIMPL implements ClerkService {

	@Override
	public List<LoanApplication> getAllLoanApplications() {
		ClerkDAO dao=new ClerkDAO_IMPL();
		return dao.getAllLoanApplications();
	}

	@Override
	public boolean clerkAuth(String uname, String password) {
		ClerkDAO dao=new ClerkDAO_IMPL();
		return dao.clerkAuth(uname, password);
	}

	@Override
	public List<LoanApplication> searchByLoanType(int clerkId, String loanType) {
		ClerkDAO dao=new ClerkDAO_IMPL();
		return dao.searchByLoanType(clerkId, loanType);
	}

	@Override
	public List<LoanApplication> searchByDate(int clerkId, LocalDate applicationDate) {
		ClerkDAO dao=new ClerkDAO_IMPL();
		return dao.searchByDate(clerkId, applicationDate);
	}

	@Override
	public int addNewCustomer(Customer c) {
		ClerkDAO dao=new ClerkDAO_IMPL();
		return dao.addNewCustomer(c);
	}

	@Override
	public int applyForLoan(int clerkId, LoanApplication l) {
		ClerkDAO dao=new ClerkDAO_IMPL();
		return dao.applyForLoan(clerkId, l);
	}

	@Override
	public Clerk getClerkDetails(int clerkId) {
		ClerkDAO dao=new ClerkDAO_IMPL();
		return dao.getClerkDetails(clerkId);
	}

	@Override
	public List<LoanApplication> searchByLoanId(int clerkId, int loanId) {
		ClerkDAO dao=new ClerkDAO_IMPL();
		return dao.searchByLoanId(clerkId, loanId);
	}

}
