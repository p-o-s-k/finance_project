package com.oracle.financeproject.provider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.oracle.financeproject.jdbc.DBConnection;
import com.oracle.financeproject.repository.iml.ClerkDAO_IMPL;
import com.oracle.financeproject.repository.iml.CustomerDAO_IMPL;
import com.oracle.financeproject.repository.iml.ManagerDAO_IMPL;
import com.sun.jersey.core.util.Base64;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
	@Context
    private ResourceInfo resourceInfo;
	
   private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("in filter ....");
	     Method method = resourceInfo.getResourceMethod();
	     System.out.println("the method u r invoking is "+method.getName());
	        //Access allowed for all
	        if( ! method.isAnnotationPresent(PermitAll.class))
	        {
	            //Access denied for all
	            if(method.isAnnotationPresent(DenyAll.class))
	            {
	              // requestContext.abortWith(ACCESS_DENIED);
	            	 requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
								.entity("Access blocked for all users !!").build());
	                return;
	            }
	             
	            //Get request headers
	            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
	             
	            //Fetch authorization header
	            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
	             
	            //If no authorization information present; block access
	            if(authorization == null || authorization.isEmpty())
	            {
	            	System.out.println("No authorization ...");
	            	 requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
								.entity("You cannot access this resource****").build());
	                return;
	            }
	             
	            //Get encoded username and password
	            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
	             
	            //Decode username and password
	            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));;
	 
	            //Split username and password tokens
	            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
	            final String username = tokenizer.nextToken();
	            final String password = tokenizer.nextToken();
	             
	            //Verifying Username and password
	            System.out.println(username);
	            System.out.println(password);
	             
	            //Verify user access
	            if(method.isAnnotationPresent(RolesAllowed.class))
	            {
	                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
	                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
	                 
	                //Is user valid?
	                if( ! isUserAllowed(username, password, rolesSet))
	                {
	                	System.out.println("Not allowed");
	                 
	                Response unAuthorizedStatus=Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource....").build();
	                requestContext.abortWith(unAuthorizedStatus);
	                return;
	                }
	                System.out.println("Allowed ..");
	            }
	        }
	    }
	    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
	    {
	        boolean isAllowed = false;
	        CustomerDAO_IMPL dao1=new CustomerDAO_IMPL();
	        ClerkDAO_IMPL dao2=new ClerkDAO_IMPL();
	        ManagerDAO_IMPL dao3=new ManagerDAO_IMPL();
	     
	        if(dao1.customerAuth(username, password)) {
	        	String userRole="CUSTOMER";
	       		 if(rolesSet.contains(userRole))
		         {
		            System.out.println("Customer user");
		            isAllowed = true;
		         }
	        }    
	        
	       if(dao2.clerkAuth(username, password)){
	    	  String userRole="CLERK";
       		 if(rolesSet.contains(userRole))
	            {
	            	System.out.println("Clerk user");
	                isAllowed = true;
	            }
	         }
	       
	       if(dao3.managerAuth(username, password)) {
	    	   String userRole="MANAGER";
	       		 if(rolesSet.contains(userRole))
		            {
		            	System.out.println("Manager user");
		                isAllowed = true;
		            }
	         }
	         
	        return isAllowed;
	    }
}
