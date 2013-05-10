package com.ems.test.model;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.ems.model.Participant;
import com.ems.test.general.JavaBeanTester;

public class ParticipantTest {

	@Test
	public void testBeanProperties() throws IntrospectionException{
	    JavaBeanTester.test(Participant.class);
	}

}
