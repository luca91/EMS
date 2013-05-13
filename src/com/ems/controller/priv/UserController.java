package com.ems.controller.priv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.dao.UserDao;
import com.ems.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {
		"/private/userList.html", 
		"/private/user.jsp", 
		"/private/userAdd",
		"/private/userDelete"
		})
public class UserController extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(UserController.class.getName());
	
	private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/userList.jsp";
    private UserDao dao;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        log.debug("UserController ###################################");
    	log.trace("START");
		dao = new UserDao();
        log.debug("Dao object instantiated");
        log.trace("END");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.trace("START");
    	String forward="";
        String action = request.getParameter("action");
        if (action == null){
        	log.debug("action is NULL");
        	action="";
        }
        
        if (action.equalsIgnoreCase("delete")){
            log.debug("action: DELETE - " + action);
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteUser(id);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("edit")){
            log.debug("action: EDIT - " + action);
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            User user = dao.getUserById(id);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("insert")){
            log.debug("action: INSERT - " + action);
            request.removeAttribute("user");
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("listUser")){
            log.debug("action: listUser - " + action);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        } else {
            log.debug("action: ELSE - " + action);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        }
    	
        log.debug("forward: " + forward);
        log.debug("action: " + action);
    	/*
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    	log.trace("END");
    	 */	
    	
        forward = "/WEB-INF/jsp/private" + forward;
        log.debug("forward: " + forward);
        
		try {
			getServletConfig().getServletContext().getRequestDispatcher(forward).forward(request, response);
			} 
		catch (Exception ex) {
				ex.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.trace("START");
    	User user = new User();
        user.setFname(request.getParameter("fname"));
        user.setLname(request.getParameter("lname"));
        user.setDate_of_birth(request.getParameter("date_of_birth"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setRole(request.getParameter("role"));
        String id = request.getParameter("id");
        
    	log.debug("id: " + id);
    	
        if(id == null || id.isEmpty()) {
        	log.debug("INSERT");
            dao.addUser(user);
        }
        else
        {
        	log.debug("UPDATE");
            user.setId(Integer.parseInt(id));
            dao.updateUser(user);
        }
        
        /*
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
        
        */
        
        String forward = "/WEB-INF/jsp/private" + LIST_USER;
        log.debug("forward: " + forward);
        request.setAttribute("users", dao.getAllUsers());
		try {
			getServletConfig().getServletContext().getRequestDispatcher(forward).forward(request, response);
			} 
		catch (Exception ex) {
				ex.printStackTrace();
			}
    	log.trace("END");
	}

}