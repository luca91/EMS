package com.ems.prototype.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.ems.controller.UserController;
import com.ems.dao.UserDao;


public class EntityControllerTest {
	
	// commons logging references
	static Logger log = Logger.getLogger(EntityControllerTest.class.getName());

	HttpServletRequest request;
	HttpServletResponse response;
	
	UserController servlet;

	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/ems";
    //  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn = null;
	
	
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
		log.debug("instantiate servlet");
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			servlet = new UserController(conn);

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
	public void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		log.trace("START");
		Mockito.when(request.getParameter("name")).thenReturn("name");
		Mockito.when(request.getParameter("value")).thenReturn("value");

		PrintWriter writer = new PrintWriter("response.txt");
		Mockito.when(response.getWriter()).thenReturn(writer);

		servlet.doGet(request, response);

		writer.flush();
		Mockito.verify(request, Mockito.atLeast(1)).getParameter("name");

		assertTrue(FileUtils
				.readFileToString(new File("response.txt"), "UTF-8").contains(
						"name:name,value:value"));
		log.trace("END");
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse()
			throws ServletException, IOException {
		log.trace("START");

		Mockito.when(request.getParameter("fname")).thenReturn("John");
		Mockito.when(request.getParameter("lname")).thenReturn("McCain");
		Mockito.when(request.getParameter("date_of_birth")).thenReturn("20130101");
		Mockito.when(request.getParameter("email")).thenReturn("john@mccain.com");
		Mockito.when(request.getParameter("password")).thenReturn("password");		
		Mockito.when(request.getParameter("role")).thenReturn("admin");
		
		PrintWriter writer = new PrintWriter("response.txt");
		Mockito.when(response.getWriter()).thenReturn(writer);

		servlet.doPost(request, response);

		writer.flush();
		Mockito.verify(request, Mockito.atLeast(1)).getParameter("fname");

		assertTrue(FileUtils
				.readFileToString(new File("response.txt"), "UTF-8").contains(
						"fname:John,lname:McCain"));
		log.trace("END");
	}

}

