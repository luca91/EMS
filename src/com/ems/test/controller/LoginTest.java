package com.ems.test.controller;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

import com.ems.controller.LoginController;

public class LoginTest {
	
	private LoginController toTest;

	@Before
	public void setUp(){
		toTest = new LoginController("name.surname@unibz.it", "admin");
	}
	
	@Test
	public void testGetUsername(){
		Assert.assertEquals("name.surname@unibz.it", toTest.getUserName());
	}
	
	@Test
	public void testGetPassword(){
		Assert.assertEquals("admin", toTest.getPassword());
	}
	

}
