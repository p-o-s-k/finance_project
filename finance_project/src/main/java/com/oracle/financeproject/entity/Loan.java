package com.oracle.financeproject.entity;

public class Loan {
	private int loanId;
	private String loanType;
	private float loanInterestRate;
	public Loan(int loanId, String loanType, float loanInterestRate) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.loanInterestRate = loanInterestRate;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public float getLoanInterestRate() {
		return loanInterestRate;
	}
	public void setLoanInterestRate(float loanInterestRate) {
		this.loanInterestRate = loanInterestRate;
	}
	
	
	
	

}
