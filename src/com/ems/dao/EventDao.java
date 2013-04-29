//Code inspired by http://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/

package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ems.model.Event;
import com.ems.model.User;

//import com.daniel.util.DbUtil;

public class EventDao {
	
	// commons logging references
	static Logger log = Logger.getLogger(EventDao.class.getName());

    private Connection connection;

    //used to load the connection in JUnit tests
    public EventDao(Connection c){
    	connection = c;
    }
    
    public EventDao() throws NamingException, SQLException {
        Context initialContext = new InitialContext();
        Context envContext  = (Context)initialContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/ems");
        connection = ds.getConnection();
    }

    public void addRecord(Event aRecord) {
    	log.trace("START");
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into event(id_manager,name,description,start,end,enrollment_start,enrollment_end) values (?, ?, ?, ?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setInt(1, aRecord.getId_manager());
            preparedStatement.setString(2, aRecord.getName());
            preparedStatement.setString(3, aRecord.getDescription());
            preparedStatement.setString(4, aRecord.getStart());
            preparedStatement.setString(5, aRecord.getEnd());
            preparedStatement.setString(6, aRecord.getEnrollment_start());
            preparedStatement.setString(7, aRecord.getEnrollment_end());            
        	log.debug("add record");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    public void deleteRecord(int id) {
    	log.trace("START");
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from event where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    public void updateRecord(Event aRecord) {
    	log.trace("START");
    	log.debug(aRecord.toString());
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update event set id_manager=?, name=?, description=?,start=?, end=?, enrollment_start=?, enrollment_end=? " +
                            "where id=?");
            
            preparedStatement.setInt(1, aRecord.getId_manager());
            preparedStatement.setString(2, aRecord.getName());
            preparedStatement.setString(3, aRecord.getDescription());
            preparedStatement.setString(4, aRecord.getStart());
            preparedStatement.setString(5, aRecord.getEnd());
            preparedStatement.setString(6, aRecord.getEnrollment_start());
            preparedStatement.setString(7, aRecord.getEnrollment_end()); 
            
            preparedStatement.setInt(8, aRecord.getId());
            preparedStatement.executeUpdate();
        	log.debug("update done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    public List<Event> getAllRecords() {
        log.trace("START");
    	List<Event> list = new ArrayList<Event>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from event");
            while (rs.next()) {
                Event aRecord = new Event();
                aRecord.setId(rs.getInt("id"));
                aRecord.setId_manager(rs.getInt("id_manager"));
                aRecord.setDescription(rs.getString("description"));
                aRecord.setStart(rs.getString("start"));
                aRecord.setEnd(rs.getString("end"));
                aRecord.setEnrollment_start(rs.getString("enrollment_start"));
                aRecord.setEnrollment_end(rs.getString("enrollment_end"));
                list.add(aRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return list;
    }

    public Event getRecordById(int id) {
    	log.trace("START");
        Event aRecord = new Event();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from event where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                aRecord.setId(rs.getInt("id"));
                aRecord.setId_manager(rs.getInt("id_manager"));
                aRecord.setDescription(rs.getString("description"));
                aRecord.setStart(rs.getString("start"));
                aRecord.setEnd(rs.getString("end"));
                aRecord.setEnrollment_start(rs.getString("enrollment_start"));
                aRecord.setEnrollment_end(rs.getString("enrollment_end"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return aRecord;
    }    
}
