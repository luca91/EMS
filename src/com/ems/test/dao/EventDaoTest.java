package com.ems.test.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ems.dao.EventDao;
import com.ems.model.Event;

/**
 * Tests for {@link UserDato}.
 *
 * @author LucaBa)
 */
@RunWith(JUnit4.class)
public class EventDaoTest {
	
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
			log.debug("Create statement");
			stmt = conn.createStatement();
			  
			log.debug("Delete old records");
			String sql =
					"DELETE FROM event";
			stmt.executeUpdate(sql);
			
			
			sql = 	
					"insert " +
					" into event(id_manager,name,description,start,end,enrollment_start,enrollment_end)" + 
					" values (28, 'event1','description1','20130507', '20130607', '20130430', '20130506' );";
			log.debug("Inserting record 1...");
			stmt.executeUpdate(sql);
			
			sql = 	
					"insert " +
					" into event(id_manager,name,description,start,end,enrollment_start,enrollment_end)" + 
					" values (28, 'event2','description2','20130507', '20130607', '20130430', '20130506' );";
			log.debug("Inserting record 2...");
			stmt.executeUpdate(sql);
			
			sql = 	
					"insert " +
					" into event(id_manager,name,description,start,end,enrollment_start,enrollment_end)" + 
					" values (28, 'event3','description3','20130507', '20130607', '20130430', '20130506' );";

			log.debug("Inserting record 3...");
			stmt.executeUpdate(sql);
			log.debug("Executed queries");
		} catch (ClassNotFoundException e1) {
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
	
	@After
	public void removeMockData(){
		log.debug("reloadMockData() - START");
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
					"DELETE FROM event";
			stmt.executeUpdate(sql);
			
			
		} catch (ClassNotFoundException e1) {
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
		log.debug("reloadMockData() - END");
	}
	
	@Test
    public void testAddRecord() throws NamingException, ClassNotFoundException {
		log.trace("START");
		
		int id_manager = 28;
		String name = "nameTest";
		String description = "descriptiontest";
		String start = "20130601";
		String end = "20130630";
		String enrollment_start = "20130515";
		String enrollment_end = "20130530";
		
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	EventDao obj = new EventDao(conn);
	    	Event aRecord = new Event();
	    	
	    	aRecord.setId_manager(id_manager);
	    	aRecord.setName(name);
	    	aRecord.setDescription(description);
	    	aRecord.setStart(start);
	    	aRecord.setEnd(end);
	    	aRecord.setEnrollment_start(enrollment_start);
	    	aRecord.setEnrollment_end(enrollment_end);
	    	obj.addRecord(aRecord);
	    	
	    	String sql = 
	    			"SELECT COUNT(*) AS nr_rows" +
	    			" FROM event" +
	    			" WHERE name = '" + name + "';";
	    	
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	rs.next();
	    	int  count = rs.getInt("nr_rows");
	    	
	    	Assert.assertEquals("failure - record not added - ", 1, count);
		} catch (SQLException e) {
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
		
		log.debug("testAddUser() - END");

    }
	
	
	@Test
    public void testDeleteUser() throws NamingException, ClassNotFoundException {
		log.debug("START");
		
		int id_manager = 28;
		String name = "nameTest";
		String description = "descriptiontest";
		String start = "20130601";
		String end = "20130630";
		String enrollment_start = "20130515";
		String enrollment_end = "20130530";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
	    	String sql = 
					"insert " +
					" into event(id_manager,name,description,start,end,enrollment_start,enrollment_end)" + 
	    			" VALUES (" + id_manager+", '" + name + "', '" + description + "', '" +  start + "', '" + end + "', '" + enrollment_start + "','" + enrollment_end +"');";
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	EventDao obj = new EventDao(conn);
	    	obj.deleteRecord(last_id);
	    	
	    	sql = 	"SELECT COUNT(*) AS nr_rows" +
	    			" FROM event" +
	    			" WHERE id = " + last_id;
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  count = rs.getInt("nr_rows");
	    	
	    	Assert.assertEquals("failure - record not deleted", 0, count);
		} catch (SQLException e) {
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
		log.trace("END");
    }
	
	
	@Test
    public void testGetAllRecords() throws ClassNotFoundException {
		log.debug("START");	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			EventDao obj = new EventDao(conn);
	
	    	List<Event> list = obj.getAllRecords();
	
	    	String sql = 	
	    			"SELECT COUNT(*) AS nr_rows" +
	    			" FROM event";
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	rs.next();
	    	int nr_rows = rs.getInt("nr_rows");
	    	
	    	log.debug("nr_rows: " + nr_rows);
	    	log.debug("list.size(): " + list.size());
	    	Assert.assertEquals("failure - getAllUsers returns a different list of record", nr_rows, list.size());
		} catch (SQLException e) {
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
    	
		log.debug("testGetAllUsers() - END");	
    }
	
	
	@Test
    public void testGetUserById() throws ClassNotFoundException {
		log.trace("START");

		int id_manager = 28;
		String name = "nameTest";
		String description = "descriptiontest";
		String start = "20130601";
		String end = "20130630";
		String enrollment_start = "20130515";
		String enrollment_end = "20130530";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
	    	String sql = 
   					"insert " +
   					" into event(id_manager,name,description,start,end,enrollment_start,enrollment_end)" + 
   	    			" VALUES (" + id_manager+", '" + name + "', '" + description + "', '" +  start + "', '" + end + "', '" + enrollment_start + "','" + enrollment_end +"');";
	    			
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	log.debug("last_id: " + last_id);
	    	EventDao obj = new EventDao(conn);
	
	    	Event aRecord = obj.getRecordById(last_id);
	    	
	    	Assert.assertEquals("failure - record returned by ID is different from record inserted", aRecord.getName(), name);
		} catch (SQLException e) {
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
		log.trace("END");
    }	
	
	@Test
    public void testUpdateRecord() throws ClassNotFoundException {
		log.trace("START");

		int id_manager = 28;
		String name = "nameTest";
		String description = "descriptiontest";
		String start = "20130601";
		String end = "20130630";
		String enrollment_start = "20130515";
		String enrollment_end = "20130530";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
	    	String sql = 
  					"insert " +
  		   			" into event(id_manager,name,description,start,end,enrollment_start,enrollment_end)" + 
  		   	    	" VALUES (" + id_manager+", '" + name + "', '" + description + "', '" +  start + "', '" + end + "', '" + enrollment_start + "','" + enrollment_end +"');";

	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			log.debug("Get id");
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	log.debug(last_id);
	    	
	    	EventDao obj = new EventDao(conn);
	
	    	Event aRecord = new Event();
	    	
	    	int newId_manager = 28;
	    	String newName = "NameUpdated";
	    	String newDescription = "DescriptionUpdated";
	    	String newStart = "20991231";
	    	String newEnd = "20991231";
	    	String newEnrollment_start = "20991231";
	    	String newEnrollment_end = "20991231";
	    	
	    	aRecord.setId(last_id);
	    	aRecord.setId_manager(newId_manager);
	    	aRecord.setName(newName);
	    	aRecord.setDescription(newDescription);
	    	aRecord.setStart(newStart);
	    	aRecord.setEnd(newEnd);
	    	aRecord.setEnrollment_start(newEnrollment_start);
	    	aRecord.setEnrollment_end(newEnrollment_end);	    	
	    	
	    	obj.updateRecord(aRecord);
	    	
	    	sql = 	"SELECT * " +
	    			" FROM event" +
	    			" WHERE id = " + last_id;
	    	
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int upId_manager = rs.getInt("id_manager");
	    	String upName = rs.getString("name");
	    	String upDescription = rs.getString("description");
	    	String upStart = rs.getString("start");
	    	String upEnd = rs.getString("end");
	    	String upEnrollment_start = rs.getString("enrollment_start");
	    	String upEnrollment_end = rs.getString("enrollment_end");
	    	
	    	log.debug("###### " + upId_manager + " - " + upName + " - " + upDescription + " - " + upStart + " - " + upEnd + " - " + upEnrollment_start + " - " + upEnrollment_end);
	    	Assert.assertEquals("failure - field id_manager has not been correctly updated", newId_manager, upId_manager);
	    	Assert.assertEquals("failure - field name has not been correctly updated", newName, upName);
	    	Assert.assertEquals("failure - field description has not been correctly updated", newDescription, upDescription);
	    	Assert.assertEquals("failure - field start has not been correctly updated", newStart, upStart);
	    	Assert.assertEquals("failure - field end has not been correctly updated", newEnd, upEnd);
	    	Assert.assertEquals("failure - field enrollment_start has not been correctly updated", newEnrollment_start, upEnrollment_start);
	    	Assert.assertEquals("failure - field enrollment_end has not been correctly updated", newEnrollment_end, upEnrollment_end);
	    	
		} catch (SQLException e) {
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
		log.trace("END");
    }
}
