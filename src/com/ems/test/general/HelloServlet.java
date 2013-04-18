package com.ems.test.general;

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
 
    private Log log = LogFactory.getLog(HelloServlet.class);
 
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
        log.info("doGet");
 
        String name = request.getParameter("name");
        String value = request.getParameter("value");
 
        log.debug(name);
        log.debug(value);
 
        response.getWriter().write("name:" + name + ",value:" + value);
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("doPost");
 
        String name = request.getParameter("name");
        String value = request.getParameter("value");
 
        log.debug(name);
        log.debug(value);
 
        response.getWriter().write("name:" + name + ",value:" + value);
 
    }
 
}