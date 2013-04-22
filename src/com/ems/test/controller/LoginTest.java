package com.ems.test.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

import com.ems.controller.LoginController;
import com.ems.test.dao.UserDaoTest;

public class LoginTest {
	
	private LoginController toTest;
	
	// commons logging references
	static Logger log = Logger.getLogger(UserDaoTest.class.getName());
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/ems";
    //  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;;
	
	@Before
	public void setUp(){
		toTest = new LoginController("name.surname@unibz.it", "admin");
	}

	@Before
	public void loadMockData() throws NamingException, SQLException{
		log.debug("loadMockData() - START");
	    try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 3: Open a connection
			log.debug("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			log.debug("Connected database successfully...");
			  
			//STEP 4: Execute a query
			log.debug("Inserting records into the table...");
			stmt = conn.createStatement();
			  
			String sql = 	
					"insert " +
					" into user(fname,lname,date_of_birth,email,password,role)" +
					" values ('Luca', 'Be', '19910101','lucabelles@gmail.com' ,'password','admin');";
			log.debug("Inserting record 1...");
			stmt.executeUpdate(sql);
			
			sql = 	"insert " +
					" into user(fname,lname,date_of_birth,email,password,role)" +
					" values ('Luca', 'Ba', '19710703','luca.barazzuol@gmail.com' ,'password','event_mng');";
			log.debug("Inserting record 2...");
			stmt.executeUpdate(sql);
			
			sql = 	"insert " +
					" into user(fname,lname,date_of_birth,email,password,role)" +
					" values ('Alex', 'Stan','19910202','alexstannumberone@gmail.com' ,'password','group_mng');";
			log.debug("Inserting record 3...");
			stmt.executeUpdate(sql);
			log.debug("Executed queries");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    catch (SQLException e) {
            e.printStackTrace();
        }
	    finally{
	        //finally block used to close resources
	        try{
	           if(stmt!=null)
	              conn.close();
	        }catch(SQLException se){
	        }// do nothing
	        try{
	           if(conn!=null)
	              conn.close();
	        }catch(SQLException se){
	           se.printStackTrace();
	        }//end finally try
	    }
		log.debug("loadMockData() - END");
	}
	
	@Test
	public void testGetUsername(){
		Assert.assertEquals("name.surname@unibz.it", toTest.getUserName());
	}
	
	@Test
	public void testGetPassword(){
		Assert.assertEquals("admin", toTest.getPassword());
	}
	
	
	

}
