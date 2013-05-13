package com.ems.controller.priv;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ems.dao.UserDao;
import com.ems.model.User;

/**
 * Servlet implementation class Index
 */
@WebServlet("/private/index.html")
public class Index extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(Index.class.getName());
	
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
    
		String role = "";
		if (request.isUserInRole("admin")) {
     	   role = "admin";
     	} else if (request.isUserInRole("staff")) {
     		role = "staff";
     	} else if (request.isUserInRole("student")) {
     		role = "student";
     	}
		
		User u = new User();
		UserDao ud = new UserDao();
		log.debug(request.getUserPrincipal().getName());
		u = ud.getUserByEmail(request.getUserPrincipal().getName());
		
		HttpSession session = request.getSession(true);
		session.setAttribute("systemUser", u);
		
		log.debug("--> " + u.getEmail());
		log.debug("--> " + u.getRole());

		try {
			getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/jsp/private/index.jsp").forward(request, response);
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
