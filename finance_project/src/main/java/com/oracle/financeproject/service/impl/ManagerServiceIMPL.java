package com.oracle.financeproject.service.impl;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.oracle.financeproject.entity.LoanApplication;
import com.oracle.financeproject.entity.Manager;
import com.oracle.financeproject.repository.ManagerDAO;
import com.oracle.financeproject.repository.iml.ManagerDAO_IMPL;
import com.oracle.financeproject.service.ManagerService;

public class ManagerServiceIMPL implements ManagerService {

	@Override
	public List<LoanApplication> viewAllLoanApplications() {
		ManagerDAO dao=new ManagerDAO_IMPL();
		return dao.viewAllLoanApplications();
	}

	@Override
	public boolean managerAuth(String uname, String password) {
		ManagerDAO dao=new ManagerDAO_IMPL();
		return dao.managerAuth(uname, password);
	}

	@Override
	public Status ApproveLoanApplication(int applicationId, String approvalResponse) {
		ManagerDAO dao=new ManagerDAO_IMPL();
		return dao.ApproveLoanApplication(applicationId, approvalResponse);
	}

	@Override
	public int sanctionLoan(int applicationId, int custBankacc) {
		ManagerDAO dao=new ManagerDAO_IMPL();
		return dao.sanctionLoan(applicationId, custBankacc);
	}

	@Override
	public Manager getManagerDetails(int managerId) {
		ManagerDAO dao=new ManagerDAO_IMPL();
		return dao.getManagerDetails(managerId);
	}

}
