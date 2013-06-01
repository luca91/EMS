package com.ems.prototype.test;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.prototype.Entity;
import com.ems.test.general.JavaBeanTester;

public class EntityTest {

	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(Entity.class);
	}

}
