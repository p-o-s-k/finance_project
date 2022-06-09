package com.oracle.financeproject.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;



public class LoanApplication {
	private int loanApplicationNo;
	private int loanId;
	private int customerId;
	private int clerkId;
	private double loanAmount;
	private int loanTenureInMonths;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@JsonDeserialize(using= LocalDateDeserializer.class)
	@JsonSerialize(using= LocalDateSerializer.class)
	private LocalDate applicationDate;
	private String loanApplicationStatus;
	
	public LoanApplication() {
		
	}
	

	public LoanApplication(int loanApplicationNo, int loanId,int customerId, int clerkId,  double loanAmount,
			int loanTenureInMonths, LocalDate applicationDate, String loanApplicationStatus) {
		super();
		this.loanApplicationNo = loanApplicationNo;
		this.loanId = loanId;
		this.customerId = customerId;
		this.clerkId = clerkId;
		
		this.loanAmount = loanAmount;
		this.loanTenureInMonths = loanTenureInMonths;
		this.applicationDate = applicationDate;
		this.loanApplicationStatus = loanApplicationStatus;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
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
	public int getClerkId() {
		return clerkId;
	}
	public void setClerkId(int clerkId) {
		this.clerkId = clerkId;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	
	public int getLoanTenureInMonths() {
		return loanTenureInMonths;
	}
	public void setLoanTenureInMonths(int loanTenureInMonths) {
		this.loanTenureInMonths = loanTenureInMonths;
	}
	public LocalDate getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getLoanApplicationStatus() {
		return loanApplicationStatus;
	}
	public void setLoanApplicationStatus(String loanApplicationStatus) {
		this.loanApplicationStatus = loanApplicationStatus;
	}
	
	
	
	

}
