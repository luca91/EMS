package com.ems.test.model;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.model.User;
import com.ems.test.general.JavaBeanTester;


/**
 * Tests for {@link  User}.
 *
 * @author Luca Barazzuol)
 */
public class UserTest {

	/**
	 * Test the Bean User.class
	 * 
	 */
	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(User.class);
	}

}
