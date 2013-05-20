package com.ems.test.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ems.dao.ParticipantDao;
import com.ems.model.Participant;
import com.ems.tools.Population;

/**
 * Tests for {@link ParticipantDao}.
 *
 * @author LucaBa)
 */
@RunWith(JUnit4.class)
public class ParticipantDaoTest {
	
	// commons logging references
	static Logger log = Logger.getLogger(ParticipantDaoTest.class.getName());
	
	// JDBC driver name and database URL
	static String DB_JDBC_DRIVER;  
	static String DB_URL;
    //  Database credentials
	static String DB_USER;
	static String DB_PASSWORD;
	
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	int id_manager;
	int id_event;
	int id_group;

	
	MockData md = new MockData();
	
	public ParticipantDaoTest(){
		DbConfig dbc = new DbConfig();
		DB_JDBC_DRIVER = dbc.getDB_JDBC_DRIVER();
		DB_URL = dbc.getDB_URL();
		DB_USER = dbc.getDB_USER();
		DB_PASSWORD = dbc.getDB_PASSWORD();
	}
	
	@Before
	public void loadMockData() {
		log.debug("loadMockData() - START");
		md.createMock();
		
		id_manager = md.getId_manager();
		id_event = md.getId_event();
		id_group = md.getId_group();
		
		log.debug("loadMockData() - END");
	}
	
	@After
	public void removeMockData(){
		log.debug("removeMockData() - START");
		md.removeMock();
		log.debug("removeMockData() - END");
		Population p = new Population();
		p.doPopulation();
	}
	
	@AfterClass
	public static void repopulate(){
		log.debug("repopulate() - START");
		Population p = new Population();
		p.doPopulation();
		log.debug("repopulate() - END");
	}
	
	@Test
    public void testAddRecord() throws NamingException, ClassNotFoundException {
		log.trace("START");
		
		UUID randomFname = UUID.randomUUID();
		log.debug("######## -> " + randomFname.toString());
		String fname = randomFname.toString();
		String lname = "fakeLname";
		String email = "fakeEmail";
		String date_of_birth = "29991231";
		String registration_date = "20991231";
		boolean approved = false;
		boolean blocked = false;
		
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			ParticipantDao obj = new ParticipantDao(conn);
			Participant record = new Participant();
	    	
            record.setId_group(id_group);
            record.setFname(fname);
            record.setLname(lname);
            record.setEmail(email);
            record.setDate_of_birth(date_of_birth);
            record.setRegistration_date(registration_date);
            record.setApproved(approved);
            record.setBlocked(blocked);
            
	    	obj.addRecord(id_group, record);
	    	
	    	String sql = 
	    			"SELECT COUNT(*) AS nr_rows" +
	    			" FROM participant" +
	    			" WHERE fname = '" + fname + "';";
	    	log.debug(sql);
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	rs.next();
	    	int  count = rs.getInt("nr_rows");
	    	log.debug("row count: " + count);
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
		
		String fname = "fakeGroupFname";
		String lname = "fakeGroupLname";
		String email = "fakeEmail@fake2domain.fake1domain";
		String date_of_birth = "29991231";
		//String registration_date = "20991231";
		boolean approved = false;
		boolean blocked = false;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			
	    	String sql = 
					"insert " +
					" into participant(id_group,fname,lname,email,date_of_birth,approved,blocked) " +
	    			" VALUES (" + id_group + ", '" + fname + "', '" + lname + "', '" + email  + "', '" + date_of_birth +"', " + approved +", " + blocked + ");";
	    	log.debug(sql);
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	ParticipantDao obj = new ParticipantDao(conn);
	    	obj.deleteRecord(last_id);
	    	
	    	sql = 	"SELECT COUNT(*) AS nr_rows" +
	    			" FROM participant" +
	    			" WHERE id = " + last_id;
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  count = rs.getInt("nr_rows");
	    	log.debug("rows count: " + count);
	    	
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
		log.trace("START");	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			ParticipantDao obj = new ParticipantDao(conn);
	
	    	List<Participant> list = obj.getAllRecords();
	
	    	String sql = 	
	    			"SELECT COUNT(*) AS nr_rows" +
	    			" FROM participant";
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	rs.next();
	    	int nr_rows = rs.getInt("nr_rows");
	    	
	    	log.debug("nr_rows: " + nr_rows);
	    	log.debug("list.size(): " + list.size());
	    	Assert.assertEquals("failure - getAllRecords returns a different list of record", nr_rows, list.size());
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
    public void testGetRecordById() throws ClassNotFoundException {
		log.trace("START");

		String fname = "fakeFname";
		String lname = "fakeLname";
		String email = "fakeEmail";
		String date_of_birth = "29991231";
		//String registration_date = "20991231";
		boolean approved = false;
		boolean blocked = false;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			
	    	String sql = 
					"insert " +
							" into participant(id_group,fname,lname,email,date_of_birth,approved,blocked) " +
			    			" VALUES (" + id_group + ", '" + fname + "', '" + lname + "', '" + email  + "', '" + date_of_birth +"', " + approved +", " + blocked + ");";	    	
	    	log.debug(sql);
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	log.debug("last_id: " + last_id);
	    	ParticipantDao obj = new ParticipantDao(conn);
	
	    	Participant aRecord = obj.getRecordById(last_id);
	    	log.debug("record inserted: " + aRecord.toString());
	    	
	    	Assert.assertEquals("failure - record returned by ID is different from record inserted", aRecord.getFname(), fname);
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

		String fname = "fakeFname";
		String lname = "fakeLname";
		String email = "fakeEmail";
		String date_of_birth = "29991231";
		//String registration_date = "20991231";
		boolean approved = false;
		boolean blocked = false;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			
	    	String sql = 
					"insert " +
							" into participant(id_group,fname,lname,email,date_of_birth,approved,blocked) " +
			    			" VALUES (" + id_group + ", '" + fname + "', '" + lname + "', '" + email  + "', '" + date_of_birth +"', " + approved +", " + blocked + ");";
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			log.debug("Get id");
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	log.debug(last_id);
	    	
	    	ParticipantDao obj = new ParticipantDao(conn);
	    	Participant record = new Participant();
    	
	    	String newFname = "fakeGroupFnameUpdated";
	    	String newLname = "fakeGroupLnameUpdated";
	    	String newEmail = "fakeEmailUpdated";
	    	String newDate_of_birth = "19991231";

	    	boolean newApproved = true;
	    	boolean newBlocked = true;
	    	
            record.setId(last_id);
	    	record.setId_group(id_group);
            record.setFname(newFname);
            record.setLname(newLname);
            record.setEmail(newEmail);            
            record.setDate_of_birth(newDate_of_birth);

            record.setApproved(newApproved);
            record.setBlocked(newBlocked);
	    	
	    	obj.updateRecord(record);
	    	
	    	sql = 	"SELECT * " +
	    			" ,DATE_FORMAT(date_of_birth,'%Y%m%d') AS date_of_birth_formatted" +
	    			" ,DATE_FORMAT(registration_date,'%Y%m%d') AS registration_date_formatted" +
	    			" FROM participant" +
	    			" WHERE id = " + last_id;
	    	
	    	rs = stmt.executeQuery(sql);
	    	rs.next();
	    	
			String upFname = rs.getString("fname");
			String upLname = rs.getString("lname");
			String upEmail = rs.getString("email");
			String upDate_of_birth = rs.getString("date_of_birth_formatted");
			boolean upApproved = rs.getBoolean("approved");
			boolean upBlocked = rs.getBoolean("blocked");
	    	
	
	    	log.debug("###### " + upDate_of_birth );
	    	Assert.assertEquals("failure - field fname has not been correctly updated", newFname, upFname);
	    	Assert.assertEquals("failure - field lname has not been correctly updated", newLname, upLname);
	    	Assert.assertEquals("failure - field email has not been correctly updated", newEmail, upEmail);
	    	Assert.assertEquals("failure - field date_of_birth has not been correctly updated", newDate_of_birth, upDate_of_birth);
	    	Assert.assertEquals("failure - field approved has not been correctly updated", newApproved, upApproved);
	    	Assert.assertEquals("failure - field bblocked has not been correctly updated", newBlocked, upBlocked);
	    	
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
