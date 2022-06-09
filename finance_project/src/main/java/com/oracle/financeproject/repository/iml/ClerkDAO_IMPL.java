package com.oracle.financeproject.repository.iml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.oracle.financeproject.entity.Clerk;
import com.oracle.financeproject.entity.Customer;
import com.oracle.financeproject.entity.LoanApplication;
import com.oracle.financeproject.exception.ApplicationException;
import com.oracle.financeproject.jdbc.DBConnection;
import com.oracle.financeproject.repository.ClerkDAO;

public class ClerkDAO_IMPL implements ClerkDAO {
	@Override
	public List<LoanApplication> searchByLoanId(int clerkId,int loanId) {
		
		String sql="select * from loanapplication natural join loan where clerkId=? AND loanId= ? order by loanApplicationStatus";
		try (Connection con=DBConnection.getConnect()){
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, clerkId);
			pstmt.setInt(2, loanId);
			ResultSet resultSet=pstmt.executeQuery();
			
			int loanAppNo=0,loanid=0,custId=0,clerkId2=0,loanTenure=0;
			LocalDate applicationDate = null;
			double loanamt=0;
			String status=null;
			
			List<LoanApplication> alist=new ArrayList<LoanApplication>();
			
			while(resultSet.next()) {
				loanAppNo=resultSet.getInt("loanApplicationNo");
				loanid=resultSet.getInt("loanId");
			    custId=resultSet.getInt("customerId");
				clerkId2=resultSet.getInt("clerkId");
			    loanamt=resultSet.getDouble("loanAmount");
			    loanTenure=resultSet.getInt("loanTenureInMonths");
				applicationDate=resultSet.getDate("applicationDate").toLocalDate();
				status=resultSet.getString("loanApplicationStatus");
				alist.add(new LoanApplication(loanAppNo,loanid,custId,clerkId2,loanamt,loanTenure,applicationDate,status));
				}
				return alist;
			 
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}	
		return null;
	}

	@Override
	public List<LoanApplication> searchByLoanType(int clerkId,String loanType) {
		
		String sql="select * from loanapplication natural join loan where clerkId=? AND loanType= ? order by loanApplicationStatus";
		try(Connection con=DBConnection.getConnect()) {
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, clerkId);
			pstmt.setString(2, loanType);
			ResultSet resultSet=pstmt.executeQuery();
			
			int loanAppNo=0,loanid=0,custId=0,clerkId2=0,loanTenure=0;
			LocalDate applicationDate = null;
			double loanamt=0;
			String status=null;
			
			List<LoanApplication> alist=new ArrayList<LoanApplication>();
			
			while(resultSet.next()) {
				loanAppNo=resultSet.getInt("loanApplicationNo");
				loanid=resultSet.getInt("loanId");
			    custId=resultSet.getInt("customerId");
				clerkId2=resultSet.getInt("clerkId");
			    loanamt=resultSet.getDouble("loanAmount");
			    loanTenure=resultSet.getInt("loanTenureInMonths");
				applicationDate=resultSet.getDate("applicationDate").toLocalDate();
				status=resultSet.getString("loanApplicationStatus");
				alist.add(new LoanApplication(loanAppNo,loanid,custId,clerkId2,loanamt,loanTenure,applicationDate,status));
				}
				return alist;
			 
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}	
		return null;
	}

	@Override
	public List<LoanApplication> searchByDate(int clerkId,LocalDate applicationDate) {
		
		String sql="select * from loanapplication where clerkId=? AND applicationDate=? order by loanApplicationStatus";
		try(Connection con=DBConnection.getConnect()){
			PreparedStatement pstmt=con.prepareStatement(sql);
			//pstmt.setString(1, );
			pstmt.setInt(1,clerkId);
			pstmt.setDate(2, java.sql.Date.valueOf(applicationDate));
			ResultSet resultSet=pstmt.executeQuery();
			
			int loanAppNo=0,loanid=0,custId=0,clerkId2=0,loanTenure=0;
			LocalDate applicationDate2 = null;
			double loanamt=0;
			String status=null;
			
			List<LoanApplication> alist=new ArrayList<LoanApplication>();
			while(resultSet.next()) {
				loanAppNo=resultSet.getInt("loanApplicationNo");
				loanid=resultSet.getInt("loanId");
			    custId=resultSet.getInt("customerId");
				clerkId2=resultSet.getInt("clerkId");
			    loanamt=resultSet.getDouble("loanAmount");
			    loanTenure=resultSet.getInt("loanTenureInMonths");
				applicationDate2=resultSet.getDate("applicationDate").toLocalDate();
				status=resultSet.getString("loanApplicationStatus");
			
				alist.add(new LoanApplication(loanAppNo,loanid,custId,clerkId2,loanamt,loanTenure,applicationDate2,status));
				}
				
				return alist;
			 
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}	
		return null;
	}

	@Override
	public int addNewCustomer(Customer c1) {
		
		String sql="insert into customer values(customerseq.nextval,?,?,?,?,?,?,?)";
		try(Connection con=DBConnection.getConnect()) {
			PreparedStatement pstmt=con.prepareStatement(sql);
			//pstmt.setInt(1, c1.getCustomerId());
			pstmt.setString(1, c1.getCustomerFirstName());
			pstmt.setString(2, c1.getCustomerLastName());
			pstmt.setString(3, c1.getCustomerGender());
			pstmt.setString(4, c1.getCustomerMobile());
			pstmt.setString(5, c1.getCustomerAddress());
			pstmt.setString(6, c1.getCustomerPassword());
			pstmt.setString(7, c1.getCustomerEmail());
			int c=pstmt.executeUpdate();
			pstmt=con.prepareStatement("commit");
			pstmt.executeUpdate();
			System.out.println(c+" no of rows affected");
			
			sql="select customerId from customer where custFirstName=? AND custPassword=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, c1.getCustomerFirstName());
			pstmt.setString(2, c1.getCustomerPassword());
			ResultSet resultSet=pstmt.executeQuery();
			int custId=0;
			while(resultSet.next())
			{
				custId=resultSet.getInt("customerId");
			}
			return custId;
			//return c;
		} catch (SQLException e) {
			e.printStackTrace();
			//return -1;
		}
		return -1;
	}

	@Override
	public int applyForLoan(int clerkId,LoanApplication l) {
	
		//Customer c=new Customer();
		String sql="insert into loanapplication values (loanAppliseq.NEXTVAL,?,?,?,?,?,?,'Pending')";
		try(Connection con=DBConnection.getConnect()) {
			PreparedStatement pstmt=con.prepareStatement(sql);
			//pstmt.setInt(1, l.getLoanApplicationNo());
			pstmt.setInt(1, l.getLoanId());
			pstmt.setInt(2, l.getCustomerId());
			pstmt.setInt(3, clerkId);
			pstmt.setDouble(4, l.getLoanAmount());
			pstmt.setInt(5, l.getLoanTenureInMonths());
			pstmt.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
			//pstmt.setString(7, "awaiting approval");
			int r=pstmt.executeUpdate();
//			pstmt=con.prepareStatement("commit");
//			pstmt.executeUpdate();
			System.out.println(r+" no of rows affected");		
			return r;
		}
		catch(SQLException e){
			e.printStackTrace();
			//row new ApplicationException(" ");
		//	return -1;
			
		}
		return -1;
	}

	@Override
	public List<LoanApplication> getAllLoanApplications() {
		
		String sql="select * from loanapplication";
		try(Connection con=DBConnection.getConnect()) {
			PreparedStatement pstmt=con.prepareStatement(sql);
			ResultSet resultSet=pstmt.executeQuery();
			int loanAppNo=0,loanid=0,custId=0,clerkId=0,loanTenure=0;
			LocalDate applicationDate = null;
			double loanamt=0;
			String status=null;
			
			List<LoanApplication> alist=new ArrayList<LoanApplication>();
			
			while(resultSet.next()) {
				loanAppNo=resultSet.getInt("loanApplicationNo");
				loanid=resultSet.getInt("loanId");
			    custId=resultSet.getInt("customerId");
				clerkId=resultSet.getInt("clerkId");
			    loanamt=resultSet.getDouble("loanAmount");
			    loanTenure=resultSet.getInt("loanTenureInMonths");
				applicationDate=resultSet.getDate("applicationDate").toLocalDate();
				status=resultSet.getString("loanApplicationStatus");
				alist.add(new LoanApplication(loanAppNo,loanid,custId,clerkId,loanamt,loanTenure,applicationDate,status));
				//System.out.println("Added to list");
				}
				return alist;
			 
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}	
		return null;
	}



	@Override
	public boolean clerkAuth(String uname, String password) {
		 String sql="select clerkId,clerkPassword from clerk WHERE clerkId=? AND clerkPassword=?";
		  try( Connection con=DBConnection.getConnect()) {
	        	 PreparedStatement pstmt=con.prepareStatement(sql);
	        	 pstmt.setInt(1,Integer.valueOf(uname));
	        	 pstmt.setString(2,password);
	 	         ResultSet resultSet=pstmt.executeQuery();
	 	         if(resultSet.next()) {
	 	        	return true;
	 	         }
	        }catch(SQLException e) {
	        	e.printStackTrace();
	        	throw new ApplicationException("Invalid login");
	        }
		return false;
	}

	@Override
	public Clerk getClerkDetails(int clerkId) {
		
		String sql="select * from clerk where clerkId=?";
		 try(Connection con=DBConnection.getConnect()) {
        	 PreparedStatement pstmt=con.prepareStatement(sql);
        	 pstmt.setInt(1, clerkId);
 	         ResultSet resultSet=pstmt.executeQuery();
 	         while(resultSet.next()) {
 	        	int clerk_Id=resultSet.getInt("clerkId");
 	        	String clerkFName=resultSet.getString("clerkFirstName");
 	        	String clerkLName=resultSet.getString("clerkLastName");
 	        	String clerkPassword=resultSet.getString("clerkPassword");
 	        	String branch=resultSet.getString("branch");
 	        	
 	        	return new Clerk(clerk_Id,clerkFName,clerkLName,clerkPassword,branch);
 	         }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
		return null;
	}

}
