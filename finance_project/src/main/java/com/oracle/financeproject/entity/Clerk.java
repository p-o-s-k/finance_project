package com.oracle.financeproject.entity;

public class Clerk {
	private int clerkId;
	private String clerkFirstName;
	private String clerkLastName;
	private String clerkPassword;
	private String branch;
	public Clerk(int clerkId, String clerkFirstName, String clerkLastName, String clerkPassword, String branch) {
		super();
		this.clerkId = clerkId;
		this.clerkFirstName = clerkFirstName;
		this.clerkLastName = clerkLastName;
		this.clerkPassword = clerkPassword;
		this.branch = branch;
	}
	public int getClerkId() {
		return clerkId;
	}
	public void setClerkId(int clerkId) {
		this.clerkId = clerkId;
	}
	public String getClerkFirstName() {
		return clerkFirstName;
	}
	public void setClerkFirstName(String clerkFirstName) {
		this.clerkFirstName = clerkFirstName;
	}
	public String getClerkLastName() {
		return clerkLastName;
	}
	public void setClerkLastName(String clerkLastName) {
		this.clerkLastName = clerkLastName;
	}
	public String getClerkPassword() {
		return clerkPassword;
	}
	public void setClerkPassword(String clerkPassword) {
		this.clerkPassword = clerkPassword;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	

}
