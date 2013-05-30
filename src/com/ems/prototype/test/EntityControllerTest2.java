package com.ems.prototype.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Matchers.any;

import com.ems.prototype.Entity;
import com.ems.prototype.EntityController;
import com.ems.prototype.EntityDao;


public class EntityControllerTest2 {
	
	// commons logging references
	static Logger log = Logger.getLogger(EntityControllerTest2.class.getName());

	/**
	 * @uml.property  name="request"
	 * @uml.associationEnd  
	 */
	HttpServletRequest request;
	/**
	 * @uml.property  name="response"
	 * @uml.associationEnd  
	 */
	HttpServletResponse response;
	
	/**
	 * @uml.property  name="servlet"
	 * @uml.associationEnd  
	 */
	EntityController servlet;
	/**
	 * @uml.property  name="dao"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	EntityDao dao = mock(EntityDao.class);
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/ems";
    //  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn = null;
	
	/**
	 * @uml.property  name="pageSource"
	 */
	private StringWriter pageSource = new StringWriter();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.trace("START");
		log.trace("END");

	}

	@Before
	public void setUp() throws Exception {
		log.trace("START");
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			servlet = new EntityController(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.trace("END");
	}

	@After
	public void closeConnection() throws Exception {
		log.trace("START");
		conn.close();
		log.trace("END");
	}	
	
	
	@Test
	public void shouldCreatePerson() throws Exception {
		httpRequest("POST", "/prototype/PrototypeController");
		when(request.getParameter("fname")).thenReturn("Pippo");
		when(request.getParameter("lname")).thenReturn("Disney");
		when(request.getParameter("email")).thenReturn("pippo@disney.com");
		when(request.getParameter("password")).thenReturn("password");
		when(request.getParameter("role")).thenReturn("admin");
		
		servlet.service(request, response);
	
		verify(response).sendRedirect(anyString());
		
		Entity ent = new Entity();
		ent.setFname("Pippo");
		ent.setLname("Disney");
		ent.setEmail("pippo@disney.com");
		ent.setPassword("password");
		ent.setRole("admin");
		
		verify(dao).addRecord(new Entity());
	}
	
	
	private void httpRequest(String method, String pathInfo) {
		when(request.getMethod()).thenReturn(method);
		when(request.getPathInfo()).thenReturn(pathInfo);
	}



}

