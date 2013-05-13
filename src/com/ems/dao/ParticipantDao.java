//Code inspired by http://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/

package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.ems.model.Participant;
import com.ems.model.User;


/**
* ParticipantDao is the class that performs actions on the table Participant of the database
* 
* @author Luca Barazzuol
*/
public class ParticipantDao {
	
	// commons logging references
	static Logger log = Logger.getLogger(ParticipantDao.class.getName());

    private Connection connection;

    /**
     * Constructor with no parameters
     * used by JUnit
     * 
     * @param  c A connection object used to access database by test units
     */
    public ParticipantDao(Connection c){
    	connection = c;
    }
    
    /**
     * Constructor with no parameters
     * It initializes the connection to the database
     * 
     */
    public ParticipantDao(){
        Context initialContext;
		try {
			initialContext = new InitialContext();
	        Context envContext;
			try {
				envContext = (Context)initialContext.lookup("java:/comp/env");
		        DataSource ds = (DataSource)envContext.lookup("jdbc/ems");
		        connection = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Add a record to table 
     * 
     * @param user A user
     */
    public void addRecord(int anId_group, Participant aRecord) {
    	log.trace("START");
        try {
        	
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into participant(id_group,fname,lname,date_of_birth,registration_date,approved,blocked) " +
                    					"values (?, ?, ?, ?, ?, ?,? )");
            preparedStatement.setInt(1, anId_group);
            preparedStatement.setString(2, aRecord.getFname());
            preparedStatement.setString(3, aRecord.getLname());
            preparedStatement.setString(4, aRecord.getDate_of_birth());
            preparedStatement.setString(5, aRecord.getRegistration_date());
            preparedStatement.setBoolean(6, false);
            preparedStatement.setBoolean(7, false);
            log.debug(preparedStatement.toString());
        	log.debug("addRecord Execute Update");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    /**
     * Delete a record using its id
     * 
     * @param id It is the id of the record to delete
     */
    public void deleteRecord(int id) {
    	log.trace("START");
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from participant where id=?");
            preparedStatement.setInt(1, id);
            log.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    /**
     * Update the fields of a record
     * 
     * @param aRecord The record to update
     */
    public void updateRecord(Participant aRecord) {
    	log.debug("START");
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update participant " +
                    					"set " +
                    					"id_group=?, " +
                    					"fname=?, " +
                    					"lname=?, " +
                    					"date_of_birth=?," +
                    					"registration_date=?, " +
                    					"approved=?, " +
                    					"blocked=? " +
                            			"where id=?");
            log.debug(aRecord.getDate_of_birth()); 
            log.debug(aRecord.getRegistration_date());
            preparedStatement.setInt(1, aRecord.getId_group());
            preparedStatement.setString(2, aRecord.getFname());
            preparedStatement.setString(3, aRecord.getLname());
            preparedStatement.setString(4, aRecord.getDate_of_birth());
            preparedStatement.setString(5, aRecord.getRegistration_date());
            preparedStatement.setBoolean(6, aRecord.isApproved());
            preparedStatement.setBoolean(7, aRecord.isBlocked());
            preparedStatement.setInt(8, aRecord.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    /**
     * Returns the list of all records stored in table
     * 
     * @return List<User> List of objects Participant
     */
    public List<Participant> getAllRecords() {
        log.trace("START");
    	List<Participant> records = new ArrayList<Participant>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from participant");
            while (rs.next()) {
                Participant record = new Participant();
                record.setId(rs.getInt("id"));
                record.setId_group(rs.getInt("id_group"));
                record.setFname(rs.getString("fname"));
                record.setLname(rs.getString("lname"));
                record.setDate_of_birth(rs.getString("date_of_birth"));
                record.setRegistration_date(rs.getString("registration_date"));
                record.setApproved(rs.getBoolean("approved"));
                record.setBlocked(rs.getBoolean("blocked"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return records;
    }
    
    /**
     * Returns the list of all records stored in table associated with a group
     * 
     * @return List<User> List of objects Participant
     */
    public List<Participant> getAllRecordsById_group(int anId_group) {
        log.trace("START");
    	List<Participant> records = new ArrayList<Participant>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from participant where id_group=?");
            preparedStatement.setInt(1, anId_group);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Participant record = new Participant();
                record.setId(rs.getInt("id"));
                record.setId_group(rs.getInt("id_group"));
                record.setFname(rs.getString("fname"));
                record.setLname(rs.getString("lname"));
                record.setDate_of_birth(rs.getString("date_of_birth"));
                record.setRegistration_date(rs.getString("registration_date"));
                record.setApproved(rs.getBoolean("approved"));
                record.setBlocked(rs.getBoolean("blocked"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return records;
    }

    /**
     * Returns the record passing its id
     * 
     * @param id Identifier of the record to get
     * @return Participant An object Participant
     */
    public Participant getRecordById(int id) {
    	log.trace("START");
    	Participant record = new Participant();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from participant where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                record.setId(rs.getInt("id"));
                record.setId_group(rs.getInt("id_group"));
                record.setFname(rs.getString("fname"));
                record.setLname(rs.getString("lname"));
                record.setDate_of_birth(rs.getString("date_of_birth"));
                record.setRegistration_date(rs.getString("registration_date"));
                record.setApproved(rs.getBoolean("approved"));
                record.setBlocked(rs.getBoolean("blocked"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return record;
    }
    
}