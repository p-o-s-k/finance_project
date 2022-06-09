package com.oracle.financeproject.entity;

public class SanctionLoan {
	private int loanAccountNo;
	private int loanApplicationNo;
	private int customerId;
	private double loanAmount;
	private int custBankAccountNo;
	private double monthlyEMI;
	private double loanPrincipal;
	private double loanInterest;
	private double loanRepayable;
	
	public SanctionLoan() {
		
	}
	
	
	public SanctionLoan(int loanAccountNo, int loanApplicationNo, int customerId, double loanAmount,
			int custBankAccountNo, double monthlyEMI, double loanPrincipal, double loanInterest, double loanRepayable) {
		super();
		this.loanAccountNo = loanAccountNo;
		this.loanApplicationNo = loanApplicationNo;
		this.customerId = customerId;
		this.loanAmount = loanAmount;
		this.custBankAccountNo = custBankAccountNo;
		this.monthlyEMI = monthlyEMI;
		this.loanPrincipal = loanPrincipal;
		this.loanInterest = loanInterest;
		this.loanRepayable = loanRepayable;
	}


	public int getLoanAccountNo() {
		return loanAccountNo;
	}


	public void setLoanAccountNo(int loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}


	public int getLoanApplicationNo() {
		return loanApplicationNo;
	}


	public void setLoanApplicationNo(int loanApplicationNo) {
		this.loanApplicationNo = loanApplicationNo;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public double getLoanAmount() {
		return loanAmount;
	}


	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}


	public int getCustBankAccountNo() {
		return custBankAccountNo;
	}


	public void setCustBankAccountNo(int custBankAccountNo) {
		this.custBankAccountNo = custBankAccountNo;
	}


	public double getMonthlyEMI() {
		return monthlyEMI;
	}


	public void setMonthlyEMI(double monthlyEMI) {
		this.monthlyEMI = monthlyEMI;
	}


	public double getLoanPrincipal() {
		return loanPrincipal;
	}


	public void setLoanPrincipal(double loanPrincipal) {
		this.loanPrincipal = loanPrincipal;
	}


	public double getLoanInterest() {
		return loanInterest;
	}


	public void setLoanInterest(double loanInterest) {
		this.loanInterest = loanInterest;
	}


	public double getLoanRepayable() {
		return loanRepayable;
	}


	public void setLoanRepayable(double loanRepayable) {
		this.loanRepayable = loanRepayable;
	}
	
	
	
	

}
