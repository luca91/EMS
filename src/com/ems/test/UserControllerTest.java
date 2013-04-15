package com.ems.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.ems.controller.UserController;


public class UserControllerTest {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private UserController servlet;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("setUp - START");
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		System.out.println("instantiate servlet");
		servlet = new UserController();
		System.out.println("setUp - END");
	}

	
	
	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		System.out.println("testDoGetHttpServletRequestHttpServletResponse");
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

