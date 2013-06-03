package com.ems.test.model;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.model.Group;
import com.ems.test.general.JavaBeanTester;


/**
 * Tests for {@link  Group}.
 *
 * @author Luca Barazzuol)
 */
public class GroupTest {

	/**
	 * Test the Bean Group.class
	 * 
	 */
	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(Group.class);
	}

}
