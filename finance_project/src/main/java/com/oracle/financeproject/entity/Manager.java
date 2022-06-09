package com.oracle.financeproject.entity;

public class Manager {
	private int managerId;
	private String managerFirstName;
	private String managerLastName;
	private String managerPassword;
	private String branch;
	
	public Manager() {
		
	}
	public Manager(int managerId, String managerFirstName, String managerLastName, String managerPassword,
			String branch) {
		super();
		this.managerId = managerId;
		this.managerFirstName = managerFirstName;
		this.managerLastName = managerLastName;
		this.managerPassword = managerPassword;
		this.branch = branch;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerFirstName() {
		return managerFirstName;
	}
	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}
	public String getManagerLastName() {
		return managerLastName;
	}
	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	

}
