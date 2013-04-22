package com.ems.prototype.test;


import com.meterware.httpunit.*;
import com.meterware.servletunit.*;
import junit.framework.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;

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


public class EntityControllerTest {
	
	// commons logging references
	static Logger log = Logger.getLogger(EntityControllerTest.class.getName());

	HttpServletRequest request;
	HttpServletResponse response;
	
	EntityController servlet;
	EntityDao dao = mock(EntityDao.class);
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/ems";
    //  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn = null;
	
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
    public void testGetForm() throws Exception {
        ServletRunner sr = new ServletRunner( "WebContent/WEB-INF/web.xml" );       // (1) use the web.xml file to define mappings
        ServletUnitClient client = sr.newClient();               					// (2) create a client to invoke the application

        try {
            client.getResponse( "http://localhost:8080/ems/prototype/index.jsp" ); 	// (3) invoke the servlet w/o authorization
            fail( "servlet is not protected" );
        } catch (AuthorizationRequiredException e) {            					// (4) verify that access is denied
        }

     }


}

