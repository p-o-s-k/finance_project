package com.oracle.financeproject.service;

import java.util.List;

import javax.ws.rs.core.Response.Status;
import com.oracle.financeproject.entity.LoanApplication;
import com.oracle.financeproject.entity.Manager;

public interface ManagerService {
public List<LoanApplication> viewAllLoanApplications();
	
	public boolean managerAuth(String uname, String password);
	
	public Status ApproveLoanApplication(int applicationId, String approvalResponse);
	
	public int sanctionLoan(int applicationId,int custBankacc);

	public Manager getManagerDetails(int managerId);
}
