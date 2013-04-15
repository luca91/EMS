package com.ems.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
/**
* Servlet implementation class HelloServlet
*/
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private Log logging = LogFactory.getLog(HelloServlet.class);
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        response.setContentType("text/html");
        logging.info("doGet");
 
        System.out.println("go get");
 
        String name = request.getParameter("name");
        String value = request.getParameter("value");
 
        System.out.println(name);
        System.out.println(value);
 
        response.getWriter().write("name:" + name + ",value:" + value);
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        logging.info("do post");
        System.out.println("doPost");
 
        String name = request.getParameter("name");
        String value = request.getParameter("value");
 
        System.out.println(name);
        System.out.println(value);
 
        response.getWriter().write("name:" + name + ",value:" + value);
 
    }
 
}