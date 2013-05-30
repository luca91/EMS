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

public class LogoutController extends HttpServlet {
	
	static Logger log = Logger.getLogger(LogoutController.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	/**
	 * The user database object.
	 * @uml.property  name="dao"
	 * @uml.associationEnd  readOnly="true"
	 */
	private UserDao dao;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/ems";
    //  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn = null;
	/**
	 * @uml.property  name="session"
	 * @uml.associationEnd  
	 */
	private HttpSession session;

	
    public LogoutController() {
        super();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.trace("START");
    	String forward="";
    	session = request.getSession();
        session.invalidate();
        log.debug("session invalidated");
        forward = "/index.jsp";
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    	log.trace("END");
    }
    
	
	public void logout(String username){
		session.removeAttribute(username);
	}
}
