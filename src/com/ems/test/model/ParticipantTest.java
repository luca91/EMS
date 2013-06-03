package com.ems.test.model;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.model.Participant;
import com.ems.test.general.JavaBeanTester;

/**
 * Tests for {@link  Participant}.
 *
 * @author Luca Barazzuol)
 */
public class ParticipantTest {

	/**
	 * Test the Bean Participant.class
	 * 
	 */
	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(Participant.class);
	}

}
