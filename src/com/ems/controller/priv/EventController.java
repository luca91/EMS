package com.ems.controller.priv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ems.dao.EventDao;
import com.ems.model.Event;


/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {
		"/private/eventList.html", 
		"/private/event.jsp", 
		"/private/eventAdd",
		"/private/eventDelete"
		})
public class EventController extends HttpServlet {
	
	// commons logging references
	static Logger log = Logger.getLogger(EventController.class.getName());
	
	private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/event.jsp";
    private static String LIST_USER = "/eventList.jsp";
    private EventDao dao;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventController() {
        super();
        log.debug("###################################");
    	log.trace("START");
		dao = new EventDao();
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
            dao.deleteRecord(id);
            forward = LIST_USER;
            request.setAttribute("records", dao.getAllRecords());    
        } else if (action.equalsIgnoreCase("edit")){
            log.debug("action: EDIT - " + action);
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            Event record = dao.getRecordById(id);
            request.setAttribute("record", record);
        } else if (action.equalsIgnoreCase("insert")){
            log.debug("action: INSERT - " + action);
            request.removeAttribute("record");
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("listRecord")){
            log.debug("action: listRecord - " + action);
            forward = LIST_USER;
            request.setAttribute("records", dao.getAllRecords());
        } else {
            log.debug("action: ELSE - " + action);
            forward = LIST_USER;
            request.setAttribute("records", dao.getAllRecords());
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
    	Event record = new Event();

		record.setId_manager(Integer.parseInt(request.getParameter("id_manager")));
    	record.setName(request.getParameter("name"));
    	record.setDescription(request.getParameter("description"));
    	record.setStart(request.getParameter("start"));
    	record.setEnd(request.getParameter("end"));
        record.setEnrollment_start(request.getParameter("enrollment_start"));
        record.setEnrollment_end(request.getParameter("enrollment_end"));
        String id = request.getParameter("id");
        
    	log.debug("id: " + id);
    	
        if(id == null || id.isEmpty()) {
        	log.debug("INSERT");
            dao.addRecord(record);
        }
        else
        {
        	log.debug("UPDATE");
        	record.setId(Integer.parseInt(id));
            dao.updateRecord(record);
        }
        
        
        String forward = "/WEB-INF/jsp/private" + LIST_USER;
        log.debug("forward: " + forward);
        request.setAttribute("records", dao.getAllRecords());
		try {
			getServletConfig().getServletContext().getRequestDispatcher(forward).forward(request, response);
			} 
		catch (Exception ex) {
				ex.printStackTrace();
			}
    	log.trace("END");
	}

}
