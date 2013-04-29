package com.ems.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.dao.UserDao;
import com.ems.model.User;

public class LoginController extends HttpServlet {
	
	static Logger log = Logger.getLogger(LoginController.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The username of the user.
	 */
	private String username;
	
	/**
	 * The password of the user.
	 */
	private String password;
	
	/**
	 * The user database object.
	 */
	private UserDao dao;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/ems";
    //  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn = null;

	/**
	 * 
	 * @param username the username inserted (the email)
	 * @param password the corresponding password
	 * @throws SQLException 
	 */
	public LoginController(String username, String password) throws SQLException {
		this.username = username;
		this.password = password;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		dao = new UserDao(conn);
	}
	
    public LoginController() {
        super();
        try {
			dao = new UserDao();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Login: START");
		username = request.getParameter("username");
        password = request.getParameter("password");
        log.debug("Check user validity");
        if(checkValidity()){
    		log.debug("Valid user: redirect to /home.jsp");
        	String forward = "/home.jsp";
        	RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
        else{
    		log.debug("Valid user: redirect to /login.jsp with an error");
        	String forward = "/login.jsp";
        	RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
    	log.trace("END");
    }
	
	public String getUserName(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public boolean checkValidity(){
		log.debug("username: " + this.username);
		log.debug("password: " + this.password);
		boolean flag = false;
		flag = this.dao.isUserValid(this.username, this.password);
		
		log.debug("flag: " + flag);
		return flag;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
