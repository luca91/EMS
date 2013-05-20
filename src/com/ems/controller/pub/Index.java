package com.ems.controller.pub;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.controller.priv.UserController;

/**
 * Servlet implementation class Index
 */
@WebServlet("/public/index.html")
public class Index extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(UserController.class.getName());
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("START");
		try {
			//request.setAttribute ("servletName", "MyServlet");
			getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/jsp/public/index.jsp").forward(request, response);
			} 
		catch (Exception ex) {
				ex.printStackTrace();
			}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
