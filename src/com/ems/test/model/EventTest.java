package com.ems.test.model;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.model.Event;
import com.ems.test.general.JavaBeanTester;

/**
 * Tests for {@link  Event}.
 *
 * @author Luca Barazzuol)
 */
public class EventTest {

	
	/**
	 * Test the Bean Event.class
	 * 
	 */
	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(Event.class);
	}

}
