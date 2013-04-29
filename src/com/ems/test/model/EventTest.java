package com.ems.test.model;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.model.Event;
import com.ems.test.general.JavaBeanTester;

public class EventTest {

	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(Event.class);
	}

}
