package com.oracle.financeproject.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static void main(String[] args) {
		getConnect();
		
	}
public static Connection getConnect() {
	Connection con=null;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver loaded successfully .....");
		//to establish connection with a nw
		//need url, username and pwd
		String url="jdbc:oracle:thin:@localhost:1521:ORCL";
		String uname="proj";
		String pwd="proj";
		con=DriverManager.getConnection(url,uname,pwd);
		System.out.println("Connected successfully with oracle .....");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return con;
	
}
}
