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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ems.dao.GroupDao;
import com.ems.model.Group;
import com.ems.tools.Population;

/**
 * Tests for {@link GroupDao}.
 *
 * @author LucaBa)
 */
@RunWith(JUnit4.class)
public class GroupDaoTest {
	
	// commons logging references
	static Logger log = Logger.getLogger(GroupDaoTest.class.getName());
	
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

	MockData md = new MockData();
	
	public GroupDaoTest(){
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
		
		String name = "nameGroup1";
		int max_group_number = 999;
		boolean blocked = false;
		
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    	GroupDao obj = new GroupDao(conn);
	    	Group aRecord = new Group();
	    	
	    	aRecord.setName(name);
	    	aRecord.setId_group_referent(id_manager);
	    	aRecord.setMax_group_number(max_group_number);
	    	aRecord.setBlocked(blocked);
	    	obj.addRecord(id_event, aRecord);
	    	
	    	String sql = 
	    			"SELECT COUNT(*) AS nr_rows" +
	    			" FROM ems.group" +
	    			" WHERE max_group_number = " + max_group_number + ";";
	    	
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
		
		String name = "nameGroup1";
		int max_group_number = 9999;
		boolean blocked = false; //false

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			
	    	String sql = 
					"insert " +
					" into ems.group(id_event,id_group_referent,name,max_group_number,blocked)" + 
	    			" VALUES (" + id_event + ", " + id_manager + ", '" + name + "', " + max_group_number + ", " + blocked +");";
	    	log.debug(sql);
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	GroupDao obj = new GroupDao(conn);
	    	obj.deleteRecord(last_id);
	    	
	    	sql = 	"SELECT COUNT(*) AS nr_rows" +
	    			" FROM ems.group" +
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
    public void testGetAllRecordsById_event() throws ClassNotFoundException {
		log.debug("START");	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			GroupDao obj = new GroupDao(conn);
	
	    	List<Group> list = obj.getAllRecordsById_event(id_event);
	
	    	String sql = 	
	    			"SELECT COUNT(*) AS nr_rows" +
	    			" FROM ems.group" +
	    			" WHERE id_event = " + id_event;
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);
	    	rs.next();
	    	int nr_rows = rs.getInt("nr_rows");
	    	
	    	log.debug("nr_rows: " + nr_rows);
	    	log.debug("list.size(): " + list.size());
	    	Assert.assertEquals("failure - getAllRecordsById_event returns a different list of record", nr_rows, list.size());
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
    	
		log.debug("END");	
    }

	@Test
    public void testGetAllRecords() throws ClassNotFoundException {
		log.debug("START");	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			GroupDao obj = new GroupDao(conn);
	
	    	List<Group> list = obj.getAllRecords();
	
	    	String sql = 	
	    			"SELECT COUNT(*) AS nr_rows" +
	    			" FROM ems.group" +
	    			" WHERE id_event = " + id_event;
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
    	
		log.debug("testGetAllUsers() - END");	
    }
	
	@Test
    public void testGetRecordById() throws ClassNotFoundException {
		log.trace("START");
		
		String name = "nameGroup1";
		int max_group_number = 99999;
		boolean blocked = false;
		

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			log.debug("id_event: " + id_event);
			log.debug("id_manager: " + id_manager);
			
	    	String sql = 
					"insert " +
					" into ems.group(id_event,id_group_referent,name,max_group_number,blocked)" + 
	    			" VALUES (" + id_event + ", " + id_manager + ", '" + name + "', " + max_group_number + ", " + blocked +");";
	    			
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	log.debug("last_id: " + last_id);
	    	GroupDao obj = new GroupDao(conn);
	
	    	Group aRecord = obj.getRecordById(last_id);
	    	log.debug("record inserted: " + aRecord.toString());
	    	
	    	Assert.assertEquals("failure - record returned by ID is different from record inserted", aRecord.getMax_group_number(), max_group_number);
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

		String name = "nameGroup1";
		int max_group_number = 888;
		boolean blocked = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			
	    	String sql = 
					"insert " +
					" into ems.group(id_event,id_group_referent,name,max_group_number,blocked)" + 
	    			" VALUES (" + id_event + ", " + id_manager + ", '" + name + "', " + max_group_number + ", " + blocked +");";

	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(sql);
	    	
			log.debug("Get id");
	    	sql = "SELECT LAST_INSERT_ID() AS last_id";
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	    	int  last_id = rs.getInt("last_id");
	    	
	    	log.debug(last_id);
	    	
	    	GroupDao obj = new GroupDao(conn);
	
	    	Group aRecord = new Group();
	    	
			String newName = "nameGroupNEW";
			int newMax_group_number = 8888;
			boolean newBlocked = true;
				
	    	aRecord.setId(last_id);
	    	aRecord.setId_event(id_event);
	    	aRecord.setName(newName);
	    	aRecord.setId_group_referent(id_manager);
	    	aRecord.setMax_group_number(newMax_group_number);
	    	aRecord.setBlocked(newBlocked);
    	
	    	
	    	obj.updateRecord(aRecord);
	    	
	    	sql = 	"SELECT * " +
	    			" FROM ems.group" +
	    			" WHERE id = " + last_id;
	    	
	    	rs = stmt.executeQuery(sql);
			
	    	rs.next();
	

	    	String upId_event = rs.getString("id_event");
	    	String upId_group_referent = rs.getString("id_group_referent");
	    	String upName = rs.getString("name");
	    	int upMax_group_number = rs.getInt("max_group_number");
	    	boolean upBlocked = rs.getBoolean("blocked");
	    	
	    	log.debug("newMax_group_number: " + newMax_group_number);
	    	log.debug("upMax_group_number: " + upMax_group_number);
	    	
	    	log.debug("newBlocked: " + newBlocked);
	    	log.debug("upBlocked: " + upBlocked);
	    	
	    	log.debug("###### " + upId_event + " - " +  upId_group_referent + " - " + upMax_group_number + " - " + upBlocked);
	    	Assert.assertEquals("failure - field name has not been correctly updated", newName, upName);
	    	Assert.assertEquals("failure - field max_group_number has not been correctly updated", newMax_group_number, upMax_group_number);
	    	Assert.assertEquals("failure - field blocked has not been correctly updated", newBlocked, upBlocked);
	    	
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
