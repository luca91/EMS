package com.ems.test;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.model.User;

public class UserTest {

	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(User.class);
	}

}
