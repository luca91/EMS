package com.ems.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ems.dao.UserDao;
import com.ems.model.User;

/**
 * Tests for {@link UserDato}.
 *
 * @author LucaBa)
 */
@RunWith(JUnit4.class)
public class UserDaoTest {
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
	public void loadMockData() throws NamingException, SQLException{
		System.out.println("loadMockData() - START");
	    try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			  
			//STEP 4: Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			  
			String sql = 	
					"insert " +
					" into user(fname,lname,date_of_birth,email,password,role)" +
					" values ('Luca', 'Be', '19910101','lucabelles@gmail.com' ,'password','admin');";
			System.out.println("Inserting record 1...");
			stmt.executeUpdate(sql);
			
			sql = 	"insert " +
					" into user(fname,lname,date_of_birth,email,password,role)" +
					" values ('Luca', 'Ba', '19710703','luca.barazzuol@gmail.com' ,'password','event_mng');";
			System.out.println("Inserting record 2...");
			stmt.executeUpdate(sql);
			
			sql = 	"insert " +
					" into user(fname,lname,date_of_birth,email,password,role)" +
					" values ('Alex', 'Stan','19910202','alexstannumberone@gmail.com' ,'password','group_mng');";
			System.out.println("Inserting record 3...");
			stmt.executeUpdate(sql);
			System.out.println("Executed queries");
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
		System.out.println("loadMockData() - END");
	}
	
	@After
	public void reloadMockData(){
		System.out.println("reloadMockData() - START");
	    try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			
			//STEP 4: Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			  
			String sql =
					"DELETE FROM user";
			stmt.executeUpdate(sql);
			
			sql = 	
					"insert " +
					" into user(fname,lname,date_of_birth,email,password,role)" +
					" values ('Luca', 'Be', '19910101','lucabelles@gmail.com' ,'password','admin');";
			System.out.println("Inserting a record...");
			stmt.executeUpdate(sql);
			
			sql = 	"insert " +
					" into user(fname,lname,date_of_birth,email,password,role)" +
					" values ('Luca', 'Ba', '19710703','luca.barazzuol@gmail.com' ,'password','event_mng');";
			System.out.println("Inserting a record...");
			stmt.executeUpdate(sql);
			
			sql = 	"insert " +
					" into user(fname,lname,date_of_birth,email,password,role)" +
					" values ('Alex', 'Stan','19910202','alexstannumberone@gmail.com' ,'password','group_mng');";
			System.out.println("Inserting a record...");
			stmt.executeUpdate(sql);
			System.out.println("Executed queries");
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
		System.out.println("reloadMockData() - END");
	}
	
	@Test
    public void testAddUser() throws NamingException, ClassNotFoundException {
		System.out.println("testAddUser() - START");
		
		String fname = "FakeFname";
		String lname = "FakeLname";
		String date_of_birth = "20130416";
		String email = "FakeEmail";
		String password = "FakePassword";
		String role = "admin";
		
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	UserDao obj = new UserDao(conn);
	    	User record = new User();
	    	
	    	record.setFname(fname);
	    	record.setLname(lname);
	    	record.setDate_of_birth(date_of_birth);
	    	record.setEmail(email);
	    	record.setPassword(password);
	    	record.setRole(role);
	    	obj.addUser(record);
	    	
	    	String sql = 
	    			"SELECT COUNT(*) AS nr_rows" +
	    			" FROM user" +
	    			" WHERE fname = '" + fname + "';";
	    	
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	rs.next();
	    	int  count = rs.getInt("nr_rows");
	    	
	    	Assert.assertEquals("failure - record not added - ", 1, count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		System.out.println("testAddUser() - END");

    }
	
	
	@Test
    public void testDeleteUser() throws NamingException, ClassNotFoundException {
		System.out.println("testDeleteUser() - START");
		String fname = "FakeFname";
		String lname = "FakeLname";
		String date_of_birth = "20130416";
		String email = "FakeEmail";
		String password = "FakePassword";
		String role = "admin";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
	    	String sql = 
	    			"INSERT" +
	    			" INTO user(fname,lname,date_of_birth,email, password,role) " +
	    			" VALUES ('" + fname +"', '" + lname + "', '" + date_of_birth + "', '" +  email + "', '" + password + "', '" + role + "');";
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	UserDao obj = new UserDao(conn);
	    	obj.deleteUser(last_id);
	    	
	    	sql = 	"SELECT COUNT(*) AS nr_rows" +
	    			" FROM user" +
	    			" WHERE id = " + last_id;
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  count = rs.getInt("nr_rows");
	    	
	    	Assert.assertEquals("failure - record not deleted", 0, count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		System.out.println("testDeleteUser() - END");
    }
	
	
	@Test
    public void testGetAllUsers() throws ClassNotFoundException {
		System.out.println("testGetAllUsers() - START");	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			UserDao obj = new UserDao(conn);
	
	    	List<User> list = obj.getAllUsers();
	
	    	String sql = 	
	    			"SELECT COUNT(*) AS nr_rows" +
	    			" FROM user";
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	rs.next();
	    	int nr_rows = rs.getInt("nr_rows");
	    	
	    	System.out.println("nr_rows: " + nr_rows);
	    	System.out.println("list.size(): " + list.size());
	    	Assert.assertEquals("failure - getAllUsers returns a different list of record", nr_rows, list.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
    	
		System.out.println("testGetAllUsers() - END");	
    }
	
	@Test
    public void testGetUserById() throws ClassNotFoundException {
		System.out.println("testGetUserById() - START");
		String fname = "FakeFname";
		String lname = "FakeLname";
		String date_of_birth = "20130416";
		String email = "FakeEmail";
		String password = "FakePassword";
		String role = "admin";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
	    	String sql = 
	    			"INSERT" +
					" INTO user(fname,lname,date_of_birth,email, password,role) " +
	    			" VALUES ('" + fname +"', '" + lname + "', '" + date_of_birth + "', '" +  email + "', '" + password + "', '" + role + "');";
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	UserDao obj = new UserDao(conn);
	
	    	User aRecord = obj.getUserById(last_id);
	    	
	    	Assert.assertEquals("failure - record returned by ID is different from record inserted", aRecord.getFname(), fname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		System.out.println("testGetUserById() - END");
    }
	
	@Test
    public void testUpdateUser() throws ClassNotFoundException {
		System.out.println("testUpdateUser() - START");
		String fname = "FakeFname";
		String lname = "FakeLname";
		String date_of_birth = "20130416";
		String email = "FakeEmail";
		String password = "FakePassword";
		String role = "admin";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
	    	String sql = 
	    			"INSERT" +
					" INTO user(fname,lname,date_of_birth,email, password,role) " +
	    			" VALUES ('" + fname +"', '" + lname + "', '" + date_of_birth + "', '" +  email + "', '" + password + "', '" + role + "');";

	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	System.out.println(last_id);
	    	
	    	UserDao obj = new UserDao(conn);
	
	    	User aRecord = new User();
	    	
	    	String newFname = "FnameUpdated";
	    	String newLname = "LnameUpdated";
	    	String newDate_of_birth = "20330416";
	    	String newEmail = "EmailUpdated";
	    	String newPassword = "PasswordUpdated";
	    	String newRole = "admin";
	    	
	    	aRecord.setId(last_id);
	    	aRecord.setFname(newFname);
	    	aRecord.setLname(newLname);
	    	aRecord.setDate_of_birth(newDate_of_birth);
	    	aRecord.setEmail(newEmail);
	    	aRecord.setPassword(newPassword);
	    	aRecord.setRole(newRole);
	    	
	    	obj.updateUser(aRecord);
	    	
	    	sql = 	"SELECT *, DATE_FORMAT(date_of_birth,'%Y%m%d') AS date_of_birth_formatted" +
	    			" FROM user" +
	    			" WHERE id = " + last_id;
	    	
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	String upFname = rs.getString("fname");
	    	String upLname = rs.getString("lname");
	    	String upDate_of_birth = rs.getString("date_of_birth_formatted");
	    	String upEmail = rs.getString("email");
	    	String upPassword = rs.getString("password");
	    	String upRole = rs.getString("role");
	    	
	    	System.out.println("###### " + upFname + " - " + upLname + " - " + upDate_of_birth + " - " + upEmail + " - " + upPassword + " - " + upRole);
	    	Assert.assertEquals("failure - field fname has not been correctly updated", newFname, upFname);
	    	Assert.assertEquals("failure - field lname has not been correctly updated", newLname, upLname);
	    	Assert.assertEquals("failure - field date_of_birth has not been correctly updated", newDate_of_birth, upDate_of_birth);
	    	Assert.assertEquals("failure - field email has not been correctly updated", newEmail, upEmail);
	    	Assert.assertEquals("failure - field password has not been correctly updated", newPassword, upPassword);
	    	Assert.assertEquals("failure - field role has not been correctly updated", newRole, upRole);
	    	
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		System.out.println("testUpdateUser() - END");
    }
	
	
}
