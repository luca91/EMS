package com.ems.prototype;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.model.User;
import com.ems.test.general.JavaBeanTester;

public class UserTest {

	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(User.class);
	}

}
