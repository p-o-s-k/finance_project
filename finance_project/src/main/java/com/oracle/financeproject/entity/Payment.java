package com.oracle.financeproject.entity;

import java.time.LocalDate;

public class Payment {

	private int transactionId;
	private double transactionAmount;
	private int loanAccountNo;
	private String transactionMethod;
	LocalDate dateOfTransaction;
	String stringdateOfTransaction;
	public double interestPaid;
	public double principlePaid;
	
	public Payment() {}
	

	public Payment(int transactionId, double transactionAmount, int loanAccountNo, String transactionMethod,
			LocalDate dateOfTransaction,String stringdateOfTransaction, double interestPaid , double principlePaid) {
		super();
		this.transactionId = transactionId;
		this.transactionAmount = transactionAmount;
		this.loanAccountNo = loanAccountNo;
		this.transactionMethod = transactionMethod;
		this.dateOfTransaction = dateOfTransaction;
		this.stringdateOfTransaction= stringdateOfTransaction;
		this.interestPaid = interestPaid;
		this.principlePaid = principlePaid;
	}

	public String getStringdateOfTransaction() {
		return stringdateOfTransaction;
	}


	public void setStringdateOfTransaction(String stringdateOfTransaction) {
		this.stringdateOfTransaction = stringdateOfTransaction;
	}


	public double getInterestPaid() {
		return interestPaid;
	}


	public void setInterestPaid(double interestPaid) {
		this.interestPaid = interestPaid;
	}


	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public int getLoanAccountNo() {
		return loanAccountNo;
	}

	public void setLoanAccountNo(int loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}

	public String getTransactionMethod() {
		return transactionMethod;
	}

	public void setTransactionMethod(String transactionMethod) {
		this.transactionMethod = transactionMethod;
	}

	public LocalDate getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDate dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	
	public double getIntrestPaid() {
		return interestPaid;
	}


	public void setIntrestPaid(double intrestPaid) {
		this.interestPaid = intrestPaid;
	}


	public double getPrinciplePaid() {
		return principlePaid;
	}


	public void setPrinciplePaid(double principlePaid) {
		this.principlePaid = principlePaid;
	}

}
