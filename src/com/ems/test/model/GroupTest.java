package com.ems.test.model;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.model.Group;
import com.ems.test.general.JavaBeanTester;

public class GroupTest {

	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(Group.class);
	}

}
