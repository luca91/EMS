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
import javax.servlet.http.HttpSession;

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
	 * @uml.property  name="username"
	 */
	private String username;
	
	/**
	 * The password of the user.
	 * @uml.property  name="password"
	 */
	private String password;
	
	/**
	 * The user database object.
	 * @uml.property  name="dao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private UserDao dao;
	
	/**
	 * @uml.property  name="role"
	 */
	private String role;
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ems";
    //  Database credentials
	private static final String USER = "root";
	private static final String PASS = "";
	private static Connection conn = null;
	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  readOnly="true"
	 */
	private HttpSession session;

	/**
	 * 
	 * @param username the username inserted (the email)
	 * @param password the corresponding password
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public LoginController(String username, String password, String role) throws SQLException {
		this.username = username;
		this.password = password;
		this.role = role;
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		dao = new UserDao(conn);
	}
	
    public LoginController() throws ClassNotFoundException {
        super();      
		dao = new UserDao();
	
    }

	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Login: START");
		username = request.getParameter("username");
        password = request.getParameter("password");
        role = request.getParameter("role");
        HttpSession session = request.getSession(true);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("role", "role");
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
	
	/**
	 * @return
	 * @uml.property  name="password"
	 */
	public String getPassword(){
		return this.password;
	}
	
	public boolean checkValidity(){
		System.out.println("Username: "+username);
		System.out.println("Password: "+password);
		return this.dao.isUserValid(this.username, this.password);
	}
	
	/**
	 * @param username
	 * @uml.property  name="username"
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * @param password
	 * @uml.property  name="password"
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * @param role
	 * @uml.property  name="role"
	 */
	public void setRole(String role){
		this.role = role;
	}
}
