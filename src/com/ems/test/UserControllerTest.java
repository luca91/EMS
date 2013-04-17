package com.ems.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.ems.controller.UserController;
import com.ems.dao.UserDao;


public class UserControllerTest {
	
	// commons logging references
	static Logger log = Logger.getLogger(UserControllerTest.class.getName());

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private UserController servlet;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		log.debug("setUp - START");
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		log.debug("instantiate servlet");
		servlet = new UserController();
		log.debug("setUp - END");
	}

	
	
	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		log.debug("testDoGetHttpServletRequestHttpServletResponse");
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

	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse()
			throws ServletException, IOException {

		Mockito.when(request.getParameter("name")).thenReturn("name");
		Mockito.when(request.getParameter("value")).thenReturn("value");

		PrintWriter writer = new PrintWriter("response.txt");
		Mockito.when(response.getWriter()).thenReturn(writer);

		servlet.doPost(request, response);

		writer.flush();
		Mockito.verify(request, Mockito.atLeast(1)).getParameter("name");

		assertTrue(FileUtils
				.readFileToString(new File("response.txt"), "UTF-8").contains(
						"name:name,value:value"));
	}

}

