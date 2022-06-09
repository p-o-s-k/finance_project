package com.oracle.financeproject.repository.iml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.Response;

import com.oracle.financeproject.exception.ApplicationException;
import com.oracle.financeproject.jdbc.DBConnection;



public class SmtpMail {
	
	private String Subject;
	private String Body;
	
	public SmtpMail()
	{}
	
	 public SmtpMail(int appNo, String body) {
			this.Subject = "Your status on loanApplication has changed";
			this.Body = body;
		}

    public SmtpMail(int appNo, String subject, String body) {
		this.Subject = subject;
		this.Body = body;
	}

	public Response sendMail(int appNo) {
		
		Connection con=DBConnection.getConnect();
		
		String sql= "select custEmail,loanApplicationStatus from customer natural join loanapplication where loanapplicationno=?";
				
		try {
			
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, appNo);
			System.out.println("hello");
			ResultSet resultSet=pstmt.executeQuery();
			
			String to=null;
			String appStatus=null;
			
			String from = "ganeshfinance.team3@gmail.com";

	        String host = "smtp.gmail.com";
			
			while(resultSet.next()) {
				 to = resultSet.getString("custEmail");
				 appStatus = resultSet.getString("loanapplicationstatus");
			}
			to="poskarthik9@gmail.com";
	        

	        // Get system properties
	        Properties properties = System.getProperties();

	        // Setup mail server
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "25");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	  
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {

	                return new PasswordAuthentication("ganeshfinance.team3@gmail.com", "team3@123");

	            }

	        });
			
    

        // Used to debug SMTP issues
        session.setDebug(true);
//        Transport transport = session.getTransport("smtps");
//        transport.connect (smtp_host, smtp_port, smtp_username, smtp_password);
//        transport.sendMessage(msg, msg.getAllRecipients());
//        transport.close();
            
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Loan Application Status has changed");

            message.setText("Your loan Applicaton No:"+appNo+" has been "+appStatus);

            System.out.println("sending...");
          
            Transport.send(message);
            System.out.println("Sent message successfully....");
            
            return Response.status(200).build();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("SQL Exception");
			
		}
         catch (MessagingException ex) {
            throw new ApplicationException("Mail Not Sent, please check receipient mail or contact administrator");
        }

    }

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

}