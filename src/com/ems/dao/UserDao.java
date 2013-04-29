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

import com.ems.model.User;

//import com.daniel.util.DbUtil;

public class UserDao {
	
	// commons logging references
	static Logger log = Logger.getLogger(UserDao.class.getName());

    private Connection connection;

    //used to load the connection in JUnit tests
    public UserDao(Connection c){
    	connection = c;
    }
    
    public UserDao() throws NamingException, SQLException {
        Context initialContext = new InitialContext();
        Context envContext  = (Context)initialContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/ems");
        connection = ds.getConnection();
    }

    public void addUser(User user) {
    	log.trace("START");
        try {
        	
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into user(fname,lname,date_of_birth,email,password,role) values (?, ?, ?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(3, user.getDate_of_birth());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole());
        	log.debug("addUser Execute Update");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    public void deleteUser(int id) {
    	log.trace("START");
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from user where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    public void updateUser(User user) {
    	log.debug("START");
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update user set fname=?, lname=?, date_of_birth=?,email=?, password=?, role=? " +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getLname());
            preparedStatement.setString(3, user.getDate_of_birth());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    }

    public List<User> getAllUsers() {
        log.trace("START");
    	List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from user");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setDate_of_birth(rs.getString("date_of_birth"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return users;
    }

    public User getUserById(int id) {
    	log.trace("START");
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from user where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setDate_of_birth(rs.getString("date_of_birth"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return user;
    }
    
    // email is the username
    public boolean isUserValid(String email, String password) {
    	log.trace("START");
    	
    	boolean isValid = false;
    	
        try {
        	log.debug("Query to check validity of the user");
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from user where email=? and password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            
            ResultSet rs = preparedStatement.executeQuery();
                 	
        	User user = new User();
        	
        	if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setDate_of_birth(rs.getString("date_of_birth"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            	isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
    	if (isValid){
    		log.debug("user is valid");
    	}
    	else {
    		log.debug("user is INvalid");
    	}
        return isValid;
    }
 
    public User getUserByEmail(String email) {
    	log.trace("START");
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from user where email=?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setDate_of_birth(rs.getString("date_of_birth"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	log.trace("END");
        return user;
    }
    
    
}
