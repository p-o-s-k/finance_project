package com.oracle.financeproject.entity;

public class Customer {
	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerGender;
	private String customerMobile;
	private String customerEmail;
	private String customerAddress;
	private String customerPassword;
	
	public Customer() {
		
	}
	
	
	
	public Customer(int customerId, String customerFirstName, String customerLastName, String customerGender,
			String customerMobile, String customerEmail, String customerAddress, String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerGender = customerGender;
		this.customerMobile = customerMobile;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerPassword = customerPassword;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerGender() {
		return customerGender;
	}
	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	
	

}
