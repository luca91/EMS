package com.ems.test.controller;

import org.junit.*;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class UserControllerTest2 {

    @Before
    public void prepare() {
        setBaseUrl("http://localhost:8080/ems");
    }

    @Test
    public void testLogin() {
        beginAt("/index.jsp");
        clickLink("login");
        assertTitleEquals("TITLE");
        setTextField("username", "test");
        setTextField("password", "test123");
        submit();
        assertTitleEquals("Welcome, test!");
    }
}