//Code inspired by http://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/

package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.ems.model.Group;



/**
* GroupDao is the class that performs actions on the Group of the database
* 
* @author Luca Barazzuol
*/
public class GroupDao {
	
	// commons logging references
	static Logger log = Logger.getLogger(GroupDao.class.getName());

    private Connection connection;

    /**
     * Constructor with no parameters
     * used by JUnit
     * 
     * @param  c A connection object used to access database by test units
     */
    public GroupDao(Connection c){
    	connection = c;
    }
    
    /**
     * Constructor with no parameters
     * It initializes the connection to the database
     * 
     */
    public GroupDao() {
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
     * Add a record to the table
     * 
     * @param id_event The event for which the group is created
     * @param aRecord A record
     */
    public void addRecord(int id_event, Group aRecord) {
    	log.trace("START");
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into ems.group(id_event,id_group_referent, name, max_group_number,blocked) values (?,?, ?, ?, ? )");
            preparedStatement.setInt(1, id_event);
            preparedStatement.setInt(2, aRecord.getId_group_referent());
            preparedStatement.setString(3, aRecord.getName());
            preparedStatement.setInt(4, aRecord.getMax_group_number());
            preparedStatement.setBoolean(5, aRecord.isBlocked());
        	log.debug("add record");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    /**
     * Delete a record from table using its id
     * 
     * @param id It is the id of the record to delete
     */
    public void deleteRecord(int id) {
    	log.trace("START");
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from ems.group where id=?");
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
    public void updateRecord(Group aRecord) {
    	log.trace("START");
    	log.debug(aRecord.toString());
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update ems.group set id_event=?, id_group_referent=?, name=?, max_group_number=?,blocked=? " +
                            "where id=?");
            
            preparedStatement.setInt(1, aRecord.getId_event());
            preparedStatement.setInt(2, aRecord.getId_group_referent());
            preparedStatement.setString(3, aRecord.getName());
            preparedStatement.setInt(4, aRecord.getMax_group_number());
            preparedStatement.setBoolean(5, aRecord.isBlocked());
            
            preparedStatement.setInt(6, aRecord.getId());
            preparedStatement.executeUpdate();
        	log.debug("update done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    /**
     * Returns the list of all records stored in the table Group and associated with an event
     * 
     * @param id_event Event to which belong the groups
     * @return List<Group> List of objects Group
     */
    public List<Group> getAllRecordsById_event(int id_event) {
        log.trace("START");
    	List<Group> list = new ArrayList<Group>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from ems.group where id_event=?");
            preparedStatement.setInt(1, id_event);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Group aRecord = new Group();
                aRecord.setId(rs.getInt("id"));
                aRecord.setId_event(rs.getInt("id_event"));
                aRecord.setId_group_referent(rs.getInt("id_group_referent"));
                aRecord.setName(rs.getString("name"));                
                aRecord.setMax_group_number(rs.getInt("max_group_number"));
                aRecord.setBlocked(rs.getBoolean("blocked"));
                list.add(aRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return list;
    }

    /**
     * Returns the list of all records stored in the table Group and associated with an event
     * 
     * @param id_event Event to which belong the groups
     * @return List<Group> List of objects Group
     */
    public List<Group> getAllRecords() {
        log.trace("START");
    	List<Group> list = new ArrayList<Group>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from ems.group");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Group aRecord = new Group();
                aRecord.setId(rs.getInt("id"));
                aRecord.setId_event(rs.getInt("id_event"));
                aRecord.setId_group_referent(rs.getInt("id_group_referent"));
                aRecord.setName(rs.getString("name"));                
                aRecord.setMax_group_number(rs.getInt("max_group_number"));
                aRecord.setBlocked(rs.getBoolean("blocked"));
                list.add(aRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return list;
    }
    
    /**
     * Returns the record passing its id
     * 
     * @param id Identifier of the record to get
     * @return group An object Group
     */
    public Group getRecordById(int id) {
    	log.trace("START");
    	Group aRecord = new Group();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from ems.group where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                aRecord.setId(rs.getInt("id"));
                aRecord.setId_event(rs.getInt("id_event"));
                aRecord.setId_group_referent(rs.getInt("id_group_referent"));
                aRecord.setName(rs.getString("name"));
                aRecord.setMax_group_number(rs.getInt("max_group_number"));
                aRecord.setBlocked(rs.getBoolean("blocked"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return aRecord;
    }
}